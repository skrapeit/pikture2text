Deployment
==========

## Prerequisites

* heroku cli installed
* create api-token `heroku authorizations:create`
* logged-in to heroku with token `heroku login`

## build docker

    cd build/docker
    heroku container:release web --app pikture2text 
    
## run tests

    cd build/docker
    heroku container:push web --app pikture2text 
