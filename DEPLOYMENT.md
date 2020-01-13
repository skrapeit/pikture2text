Deployment
==========

## Prerequisites

* heroku cli installed
* create api-token `heroku authorizations:create`
* logged-in to heroku with token `heroku login`
    
## 1. build and push docker image to heroku container registry

    cd build/docker
    heroku container:push web --app pikture2text 

## 2. release it

    heroku container:release web --app pikture2text 
