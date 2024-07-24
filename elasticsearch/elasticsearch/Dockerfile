ARG ELASTIC_VERSION

FROM docker.elastic.co/elasticsearch/elasticsearch:${ELASTIC_VERSION}

WORKDIR /usr/share/elasticsearch/config
COPY /config/synonym.txt .
COPY /config/userdic.txt .

WORKDIR /usr/share/elasticsearch/bin

RUN elasticsearch-plugin install --batch analysis-nori
