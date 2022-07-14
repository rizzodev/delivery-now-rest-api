## [DOC] Project deployment step by step (have done, deployed to EC2 instance) :

### For easy test, can access this link :
https://18.206.146.244/
or DNS : 
https://ec2-18-206-146-244.compute-1.amazonaws.com/

### All api can be seen thru swagger-ui:

https://18.206.146.244/swagger-ui/

### Jobrunr dashboard access using port 8000

https://18.206.146.244:8000/


### Example project current directory in local machine

D:gimmenow/delivery-now-rest-api

### Build docker images

docker build -t delivery-now-rest-api:latest .

### For testing docker run, use this settings

docker run -p <exposed:local> image

### So docker run will be like this :

docker run --rm --name delivery-now-rest-api -p 80:9091 -p 8000:8000 delivery-now-rest-api

added --rm to destroy after container stop, --name for naming and here we are exposing port 80 as our main application, and 8000 as jobrunr.

### Push docker image to docker hub

docker tag local-image:tagname new-repo:tagname
docker push new-repo:tagname

so :
docker tag delivery-now-rest-api:latest rizzoirfan/delivery-now-rest-api.jar:latest
docker push rizzoirfan/delivery-now-rest-api.jar:latest

After successfull pushing the images to docker hub, now we can connect to AWS EC2 instance.

### EC2Instance deployment 

connect through putty, use .pem / .ppk file.

### Inside putty require installation for docker : 

sudo -i
sudo yum install docker 
sudo service docker start
docker -v
docker pull rizzoirfan/delivery-now-rest-api.jar:latest
docker login

->before run the application, required credentials for aws, so need upload credential file to the server, well this just optional cause there is another way to use environmanet variable, but in this case we just use aws credential file, to do this in putty :

mkdir .aws
nano credentials

-and copy paste :
[default]

aws_access_key_id = AKIAZGTOBVK6XLCW44XT

aws_secret_access_key = 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


-press ctrl+x

next step now to run the docker images :
docker run --rm --name delivery-now-rest-api -p 80:9091 -p 8000:8000 rizzoirfan/delivery-now-rest-api.jar

==Application started===.

AWS Java SDK reference :

### [Quick Start]

https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html

