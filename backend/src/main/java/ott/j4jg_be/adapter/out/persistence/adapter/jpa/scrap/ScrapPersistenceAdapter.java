package ott.j4jg_be.adapter.out.persistence.adapter.jpa.scrap;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.ScrapEntity;
import ott.j4jg_be.adapter.out.persistence.mapper.ScrapEntityMapper;
import ott.j4jg_be.adapter.out.persistence.repository.jpa.ScrapRepository;
import ott.j4jg_be.application.port.out.scrap.UpdateScrapPort;
import ott.j4jg_be.application.port.out.scrap.CreateScrapPort;
import ott.j4jg_be.application.port.out.scrap.GetScrapPort;
import ott.j4jg_be.domain.scrap.Scrap;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ScrapPersistenceAdapter implements
        GetScrapPort,
        CreateScrapPort,
        UpdateScrapPort {

    private final ScrapEntityMapper scrapEntityMapper;
    private final ScrapRepository scrapRepository;


    @Override
    public Scrap getScrapByUserAndJobInfo(String userId, int jobInfoId) {

        ScrapEntity scrapEntity = scrapRepository.findByUserIdAndJobInfoId(userId, jobInfoId);

        if(scrapEntity != null){
            return scrapEntityMapper.mapToDomain(scrapEntity);
        }else{
            return null;
        }
    }

    @Override
    public Page<Scrap> getScrapList(String userId, int page) {

        int size = 10;

        Pageable pageable = PageRequest.of(page, size);

        Page<ScrapEntity> entityList = scrapRepository.findByUserIdAndStatus(userId, true, pageable);

        return entityList.map(scrapEntityMapper::mapToDomain);
    }

    @Override
    public void createScrap(Scrap scrap) {

        scrapRepository.save(scrapEntityMapper.mapToEntity(scrap));
    }

    @Override
    public void cancelScrap(int scrapId) {

        Optional<ScrapEntity> scrapEntity = scrapRepository.findById(scrapId);

        if (scrapEntity.isPresent()) {
            ScrapEntity entity = scrapEntity.get();

            entity.updateStatus(false);
        }
    }

    @Override
    public void updateScrap(Scrap scrap, boolean status) {

        Optional<ScrapEntity> scrapEntity = scrapRepository.findById(scrap.getScrapId());

        if (scrapEntity.isPresent()) {
            ScrapEntity entity = scrapEntity.get();

            entity.updateStatus(true);
        }
    }
}
