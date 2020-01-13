Deployment
==========

## Prerequisites

* heroku cli installed
* create api-token `heroku authorizations:create`
* logged-in to heroku with token `heroku login`
    
## 1. build and push docker image to heroku container registry

    docker tag docker.pkg.github.com/skrapeit/pikture2text registry.heroku.com/pikture2text/web

## 2. release it

    heroku container:release web --app pikture2text 
    
    
## see logs

    heroku logs -a pikture2text --tail
