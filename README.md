# README

To start sonarqube in docker container, default username and password is admin/admin

docker run -d --name sonarqube -p 9000:9000 sonarqube

## api token
2b9e4113fab2792bb0606097ae039850d55d6cdb

## Running a sonar scan
mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=2b9e4113fab2792bb0606097ae039850d55d6cdb

## Postman collections are available:

sonarqube.postman_collection.json


# sonar-report
java sonar client to retrieve specific metrics from sonarqube 

