# Build
mvn clean package && docker build -t it.tss/rest .

# RUN

docker rm -f rest || true && docker run -d -p 8080:8080 -p 4848:4848 --name rest it.tss/rest 

# System Test

Switch to the "-st" module and perform:

mvn compile failsafe:integration-test