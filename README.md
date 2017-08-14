# clotter

A twitter-like application

## Prerequisites

- [Leiningen](https://github.com/technomancy/leiningen) 2.0 or above
- MySQL client

## Setup

### setup MySQL
1. (other than linux) install docker app (ex. Docker for Mac) and run
1. `$ cd docker`
1. `$ docker-compose build`
1. `$ docker-compose start`

### migrate
1. `$ lein migratus`

### insert testdata
TODO: simplify

1. `$ mysql -h 127.0.0.1 -uroot --password= clotter < ./testdata/00_user.sql`
1. `$ mysql -h 127.0.0.1 -uroot --password= clotter < ./testdata/01_follw.sql`
1. `$ mysql -h 127.0.0.1 -uroot --password= clotter < ./testdata/02_tweet.sql`
1. `$ mysql -h 127.0.0.1 -uroot --password= clotter < ./testdata/03_favorite.sql`
1. `$ mysql -h 127.0.0.1 -uroot --password= clotter < ./testdata/04_retweet.sql`
1. `$ mysql -h 127.0.0.1 -uroot --password= clotter < ./testdata/05_reply.sql`

## Run
1. `$ lein figwheel`
1. `$ lein run`
1. access `http:localhost:3000`

## License

Copyright © 2017 FIXME
