#!/bin/bash

curr_dir=`dirname $0`
cd ${curr_dir}

git add . --all
git commit -m "init version"
git push master origin
