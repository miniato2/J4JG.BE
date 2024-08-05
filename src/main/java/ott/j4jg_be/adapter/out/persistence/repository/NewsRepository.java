package ott.j4jg_be.adapter.out.persistence.repository;

import org.springframework.stereotype.Repository;
import ott.j4jg_be.application.port.out.collection.NewsPort;
import ott.j4jg_be.domain.collection.NewsArticle;

import java.util.List;

@Repository
public class NewsRepository implements NewsPort {

    @Override
    public void saveAll(List<NewsArticle> articles) {
        // 구현 로직: 데이터베이스에 기사를 저장하는 로직을 여기에 추가합니다.
        // 예를 들어, JPA를 사용하면 EntityManager를 통해 저장할 수 있습니다.
    }
}
