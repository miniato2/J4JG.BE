#!/bin/bash

echo "Starting Elasticsearch..." > /usr/share/elasticsearch/logs/initialize-templates.log

# Start Elasticsearch in the background
/usr/share/elasticsearch/bin/elasticsearch &

# Wait for Elasticsearch to start up
echo "Waiting for Elasticsearch to start..." >> /usr/share/elasticsearch/logs/initialize-templates.log
until curl -s http://localhost:9200/_cat/health?h=status | grep -q 'green'; do
  sleep 1;
done

# Log the status
echo "Elasticsearch started. Initializing index templates..." >> /usr/share/elasticsearch/logs/initialize-templates.log

# Initialize index templates
curl -X PUT 'http://localhost:9200/_index_template/jobinfo' -H 'Content-Type: application/json' --data-binary @/usr/share/elasticsearch/config/jobinfo.json >> /usr/share/elasticsearch/logs/initialize-templates.log 2>&1
curl -X PUT 'http://localhost:9200/_index_template/newsinfo' -H 'Content-Type: application/json' --data-binary @/usr/share/elasticsearch/config/newsinfo.json >> /usr/share/elasticsearch/logs/initialize-templates.log 2>&1

# Log the completion
echo "Index templates initialized. Keeping the container running..." >> /usr/share/elasticsearch/logs/initialize-templates.log

# Keep the container running
tail -f /dev/null
