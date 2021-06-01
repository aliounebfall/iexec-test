# iexec-test

To run the application you will need : 

* **Docker** : Install Docker, to pull images of mongo and the application on the docker hub.

* **MongoDB Docker** : A Mongo docker container. Pull the docker image *mongo*.

* **Go-Ethereum** : An Ethereum node, running locally on your host, with imported unlocked accounts. Run the node on goerli (chainId : 5) and listen to connections on the Docker Host.

Pull the image **docker pull aliounebfall/iexec**, and run it with the following command : 

docker run --name iexec -p 9090:9090 -e HOST=YOUR_DOCKER_HOST --link YOUR_MONGO_CONTAINER:mongo -e MONGO_DATABASE=iexec -e MONGO_USERNAME=YOUR_MONGO_USERNAME -e MONGO_PASSWORD=YOUR_MONGO_PASSWORD aliounebfall/iexec:test
