#!/bin/sh
mvn clean package && docker build -t it.tss/rest .
docker rm -f rest || true && docker run -d -p 8080:8080 -p 4848:4848 --name rest it.tss/rest 
