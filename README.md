# eshop - A micro services reference implementation
This is a reference implementation of an online shopping application using micro services concepts and its popular technologies - spring boot, docker, kubernetes, mongodb. This is an in progress work and I intend to inlcude other commong mS technologies like Apache Kafka, Prometheus, Graphana, ELK, APIGEE, User Interface in angluar 5/6. In its current shape will run following containers (built via docker) in kubernetes pods as deployments and services:
1. mongodb
2. REST service built in springboot for crud operations over product datalog of eshop



#Put Following configuration in the settings.xml file to enable your docker hub user id password in maven setttings

<server>
  <id>docker-hub</id>
  <username>yourdockerhubuserid</username>
  <password>yourpassword</password>
  <configuration>
    <email>apoorve@gmail.com</email>
  </configuration>
</server>

#build product mS using the following maven command and push the image to docker hub. Remember to change the dockerhub user to your own dockerhub user
mvn clean install -X docker:build -DpushImage

#if you home directory doesnt have enough space you can point minkube to a different directory by setting this environment variable before starting minikube

export MINIKUBE_HOME=/extras/minikube

#start minikube with this command and allocate enought disk space
sudo minikube start --disk-size 100g

#Create directories in minikube so that they can be mapped from containers. An alternatibve is to use minikube --mount but i faced a few problems with it. 
minikube ssh
sudo mkdir /mnt/sda1/repos
sudo mkdir /mnt/sda1/repos/mongodb
sudo mkdir /mnt/sda1/repos/logs
exit
#The above directory is as per the allocations on my computer. they can change for a different computer.

#run following commands to create kubernetes deployment, service and pods for product service and mongodb
kubectl create -f ./kubernetes/mongodb/mongo.yaml
kubectl create -f ./kubernetes/mS/product.yaml

#use this command to get url to test your services from a rest client like postman:

minikube service eshop-product-ms-external --url


Creator: Apoorve Shrivastava
