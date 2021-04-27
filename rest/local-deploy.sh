#!/bin/sh
mvn clean package && docker build --rm -t dhtssdev/restuv .
docker rm -f restuv || true && docker run -d -p 8080:8080 -p 4848:4848 -p9990:9990 --name restuv --network db dhtssdev/restuv  && docker logs -f restuv
