package ott.j4jg_be.adapter.out.persistence.adapter.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.ScrapEntity;
import ott.j4jg_be.adapter.out.persistence.mapper.ScrapEntityMapper;
import ott.j4jg_be.adapter.out.persistence.repository.jpa.ScrapRepository;
import ott.j4jg_be.application.port.out.updateScrapPort;
import ott.j4jg_be.application.port.out.CreateScrapPort;
import ott.j4jg_be.application.port.out.GetScrapPort;
import ott.j4jg_be.domain.Scrap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ScrapPersistenceAdapter implements
        GetScrapPort,
        CreateScrapPort,
        updateScrapPort {

    private final ScrapEntityMapper scrapEntityMapper;
    private final ScrapRepository scrapRepository;


    @Override
    public Scrap getScrapByUserAndJobInfo(Long userId, int jobInfoId) {

        ScrapEntity scrapEntity = scrapRepository.findByUserIdAndJobInfoId(userId, jobInfoId);

        if(scrapEntity != null){
            return scrapEntityMapper.mapToDomain(scrapEntity);
        }else{
            return null;
        }
    }

    @Override
    public List<Scrap> getScrapList(Long userId) {

        List<ScrapEntity> entityList = scrapRepository.findByUserIdAndStatus(userId, true);

        List<Scrap> scrapList = new ArrayList<>();
        for (ScrapEntity entity : entityList) {
            scrapList.add(scrapEntityMapper.mapToDomain(entity));
        }
        return scrapList;
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
