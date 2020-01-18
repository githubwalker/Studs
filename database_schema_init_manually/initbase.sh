#/bin/bash

set -x

cat ./reinit_database.sql | mysql -u root -ppassword

