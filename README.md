# eshop - A micro services reference implementation
This is a reference implementation of an online shopping application using micro services concepts and its popular technologies - spring boot, docker, kubernetes, mongodb, apache kafka. This is an in progress work and I intend to inlcude other commong mS technologies like Auth Tokens, Prometheus, Graphana, ELK, APIGEE, User Interface in angluar 5/6. In its current shape will run following containers (built via docker) in kubernetes pods as deployments and services:
1. mongodb for data persistence
2. Product mS built in springboot for crud operations (and more) over product catalog of eshop (persistence in mongodb)
3. Shooping Cart mS built in springboot for crud operations (and more) over shopping cart of eshop (persistence in mongodb)
4. Payment mS built in springboot for payment. Nothing fancy in payment but it notifies cart to change its status and product to change the product Quantity in Stock 
5. Apache Kafka (Single broker) for communication between services via Producer/Consumer services.



#Put Following configuration in the settings.xml file to enable your docker hub user id password in maven setttings

<server>
  <id>docker-hub</id>
  <username>yourdockerhubuserid</username>
  <password>yourpassword</password>
  <configuration>
    <email>youremail@domain.com</email>
  </configuration>
</server>

#build product, cart and payment mS using the following maven command and push the image to docker hub. Remember to change the dockerhub user to your own dockerhub user
mvn clean install -X docker:build -DpushImage

#if you home directory doesnt have enough space you can point minkube to a different directory by setting this environment variable before starting minikube (This step is optional)

export MINIKUBE_HOME=/extras/minikube #(Diretory of your choice where extra sapce is available)

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
kubectl create -f ./kubernetes/kafka/zookeeper.yaml
kubectl create -f ./kubernetes/kafka/kafka.yaml
kubectl create -f ./kubernetes/mS/product.yaml
kubectl create -f ./kubernetes/mS/cart.yaml
kubectl create -f ./kubernetes/mS/payment.yaml

#use this command to get url to test your services from a rest client like postman:

minikube service eshop-product-ms-external --url
minikube service eshop-cart-ms-external --url
minikube service eshop-payment-ms-external --url


Creator: Apoorve Shrivastava
