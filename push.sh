#!/bin/bash

curr_dir=`dirname $0`
cd ${curr_dir}


if [ $# -ne 1 ]
then
    echo "Usage: $0 commit_message"
    exit 255
fi
git add . --all
git commit -m "${1}"
git push origin master 
