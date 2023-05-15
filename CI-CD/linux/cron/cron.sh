#!/bin/bash

crontab -e

* * * * * echo "Another Minute! Date is: $(date)" >> /home/scofe/time.log

sudo service cron restart