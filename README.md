## Swagger:
http://localhost:8080/swagger-ui.html

## H2 database:
http://localhost:8080/h2-console/

## Elasticsearch
#### Powinien być pusty na początku
http://localhost:9200/caseindex/_search?pretty=true&q=*:*

## Uruchomienie:
### Umieścić trzy pliki w tym samym miejscu (Dockerfile, docker-compose.yaml, myapp-1.0-SNAPSHOT.jar)
### Uruchomić:
docker-compose up --build