package ott.j4jg_be.adapter.out.persistence.adapter.jpa.scrap;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.scrap.ScrapEntity;
import ott.j4jg_be.adapter.out.persistence.mapper.scrap.ScrapEntityMapper;
import ott.j4jg_be.adapter.out.persistence.repository.jpa.ScrapRepository;
import ott.j4jg_be.application.port.out.scrap.UpdateScrapPort;
import ott.j4jg_be.application.port.out.scrap.CreateScrapPort;
import ott.j4jg_be.application.port.out.scrap.GetScrapPort;
import ott.j4jg_be.domain.scrap.Scrap;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ScrapPersistenceAdapter implements
        GetScrapPort,
        CreateScrapPort,
        UpdateScrapPort {

    private final ScrapEntityMapper scrapEntityMapper;
    private final ScrapRepository scrapRepository;
    private final CacheManager cacheManager;


    @Cacheable(value = "scraps", key = "#userId + '_' + #jobInfoId", unless = "#result == null")
    @Override
    public Scrap getScrapByUserAndJobInfo(String userId, int jobInfoId) {
        ScrapEntity scrapEntity = scrapRepository.findByUserIdAndJobInfoId(userId, jobInfoId);
        return scrapEntity != null ? scrapEntityMapper.mapToDomain(scrapEntity) : null;
    }

    @Override
    public Page<Scrap> getScrapList(String userId, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<ScrapEntity> entityList = scrapRepository.findByUserIdAndStatus(userId, true, pageable);
        return entityList.map(scrapEntityMapper::mapToDomain);
    }

    @CacheEvict(value = "scraps", key = "#scrap.userId + '_' + #scrap.jobInfoId")
    @Override
    public void createScrap(Scrap scrap) {
        scrapRepository.save(scrapEntityMapper.mapToEntity(scrap));
    }

    @Override
    public void cancelScrap(int scrapId) {
        Optional<ScrapEntity> scrapEntityOpt = scrapRepository.findById(scrapId);
        if (scrapEntityOpt.isPresent()) {
            ScrapEntity entity = scrapEntityOpt.get();
            entity.updateStatus(false);
            scrapRepository.save(entity);
            // 캐시 무효화
            Objects.requireNonNull(cacheManager.getCache("scraps")).evict(entity.getUserId() + "_" + entity.getJobInfoId());
        }
    }

    @Override
    public void updateScrap(Scrap scrap, boolean status) {
        Optional<ScrapEntity> scrapEntityOpt = scrapRepository.findById(scrap.getScrapId());
        if (scrapEntityOpt.isPresent()) {
            ScrapEntity entity = scrapEntityOpt.get();
            entity.updateStatus(status);
            scrapRepository.save(entity);

            // 캐시 무효화
            Objects.requireNonNull(cacheManager.getCache("scraps")).evict(entity.getUserId() + "_" + entity.getJobInfoId());
        }
    }
}
