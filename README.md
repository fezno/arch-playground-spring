# arch-playground-spring 

> "How can I die, when I have so much of the Universe left to explore?" - Stephen Hawking

Have fun hacking.

## Roadmap

* include font into service-worker

## Setup

> Use [IntelliJ](https://www.jetbrains.com/idea/download) for coding.
1. Install jdk10 on you machine 
2. Install and start mongodb on you machine or use docker mongo setup below
3. Before opening anything install the *Lombok Plugin*: **Settings => Plugins => Browse repositories... => search for "Lombok Plugin" => Install => Restart IntelliJ**
4. **Open** the cloned github folder and the gradle import wizard should pop up.
5. After gradle is done setting up the project enable annotation processing: **Settings => Build, Execution, Development => Compiler => Annotation Processors => Enable annotation processing**

### Docker mongodb setup

just run 

    $ docker run --rm -ti --mount type=bind,source="$(pwd)"/data/dump,target=/foo -p 27017:27017 mongo /bin/bash

then you get an shell where you can simply launch mongodb via:

    $ mongod --bind_ip_all &

finally the data import is as easy as

    $ mongorestore /foo

Please keep in mind: if you leave the base, the docker container stops and the data is gone! But maybe that's also the advantage ;)

### Dependencies

Install client dependencies:

    $ yarn install

### Data

You can setup your mongodb with our sample data by executing following command from project-root:

    $ mongorestore ./data/dump

## Production

Build client:

    $ yarn build

Build server:

    $ gradle build

Start server:

    $ java -jar build/libs/arch-playground-spring.jar

### Endpoints

* [Examples](http://localhost:4242/arch-playground-spring/examples)
* [Status](http://localhost:4242/arch-playground-spring/internal/status)
* [Loggers](http://localhost:4242/arch-playground-spring/internal/loggers)
* [Jobdefinitions](http://localhost:4242/arch-playground-spring/internal/jobdefinitions)
* [Jobs](http://localhost:4242/arch-playground-spring/internal/jobs)
* [Toggles](http://localhost:4242/arch-playground-spring/internal/toggles/console/index)
* [Health](http://localhost:4242/arch-playground-spring/internal/health)
* [Metrics](http://localhost:4242/arch-playground-spring/internal/metrics)
* [Httptrace](http://localhost:4242/arch-playground-spring/internal/httptrace)
* [Info](http://localhost:4242/arch-playground-spring/internal/info)
* [Mappings](http://localhost:4242/arch-playground-spring/internal/mappings)
* [Conditions](http://localhost:4242/arch-playground-spring/internal/conditions)
* [Env](http://localhost:4242/arch-playground-spring/internal/env)
* [Beans](http://localhost:4242/arch-playground-spring/internal/beans)

## Development

Start client watcher:

    $ yarn start

Start server:

    $ gradle bootRun [--debug-jvm]
    
You may need to set jvm args if using java 10

    $ --illegal-access=deny --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED

## Testing

### Test client

    $ yarn test

### Test server

    $ gradle test

### Test e2e

#### Test e2e on selenium grid

    $ yarn test:e2e
    
#### Test e2e locally

    $ yarn webdriver:install
    $ yarn webdriver:start
    $ yarn test:e2e:local

## Dependency Update

Client dependencies:

    $ yarn dependency-update

Server dependencies:

    $ gradle dependencyUpdates -Drevision=release

## Known java 10 problems

### Gradle

To make gradle work with jdk10 you have to run it with jdk8 and set the compiling jdk path to 10.
You can do this by putting this into your *~/.gradle/gradle.properties*: `org.gradle.java.home=/path_to_jdk10`

When running gradle tasks you then have to set `-Djava.endorsed.dirs=` as shown in this example:

    $ gradle bootRun -Djava.endorsed.dirs=
