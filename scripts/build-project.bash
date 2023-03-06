cd ${HOME}/Desktop/selina
./gradlew build
cp build/libs/selina-0.0.1-SNAPSHOT.jar src/main/docker
cd src/main/docker
docker-compose up
