#!/bin/bash

res=`pwd`
docker run -v $res/../sql/:/docker-entrypoint-initdb.d -it --rm --net=dockerlpro_lpronet lpro/pg11 psql -h db -U td1
