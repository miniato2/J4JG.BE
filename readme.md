
docker 실행

mysql 실행
```text
mysql

docker-compose up -d

```

elasticsearch 실행
```text
elasticsearch

cd elasticsearch

docker-compose up -d
```

각 모듈경로 들어가서 build되는지 확인하기(backend, gateway)

그 전에 gradlew 생성 필요 root경로에 없을 경우
```text
gradlew wrapper

```

Backend 빌드
```text
./gradlew :backend:bootJar
```

Gateway 빌드
```text
./gradlew :gateway:bootJar
```
