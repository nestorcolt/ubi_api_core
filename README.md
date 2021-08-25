# DevOps Application Test

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

An API REST microservice built with Java EE + Gradle using the Jax-Rs REST framework. This repo holds a development
environment ready for development, deployment and testing using a containerized approach.

This application is intended to be purely practical with the purpose of testing the knowledge on some important
domains for the DevOps role.

Developed by [Nestor Colt] no further maintance [Augost 2021]

## Technical Design Document
For a more detailed and high level document please refer to:
https://nestorcolt.atlassian.net/wiki/spaces/~787455215/pages/18644993/Ub+-+Tech+DevOps+App+Technical+Document


## Requirements
- Ubuntu Server (20.04 LTS)
- Root access

## Tech Stack

- Java EE 11
- Docker 20.10.7
- Docker Compose 1.29.2

## Installation

Requires [ROOT] privileges to run.

Locate the root folder of the project in the user directory

```sh
cd /home/${USER}
git clone https://github.com/nestorcolt/ubi_api_core.git
```

#### Deployment

The deployment is intended to be the most automated possible, that's why is done running a shell script:

```sh
cd /Project_directory/
bash deploy.sh
```

### Usage parameters:

```
sh deploy.sh - Deploy the microservice on http://localhost/8080

Parameters not required:
-d, -detached        >> Run in "detached" mode
-n, -no_dependencies >> Compile and run only the api rest service (JAR).
-t, -test            >> Run the integration tests.
-f, -force           >> Force the deployment after the integration test falied.
```

### Local development:


HealthAPI
```
http://localhost:8080/health
```


OpenAPI
```
http://localhost:8080/openapi
```


Swagger UI
```
http://localhost:3000/?url=http://localhost:8080/openapi
```

### Deployed image on DockerHub:

```
https://hub.docker.com/repository/docker/nestorcolt/ubi-api-core/general
```