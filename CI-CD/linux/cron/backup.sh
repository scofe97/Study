#!/bin/bash

DATE=$(date +%m-%d-%Y)
BACKUP_DIR="/home/scofe/backups"

tar -cvzf $BACKUP_DIR/backup-$DATE.tar.gz /home/scofe/Study/CI-CD/linux/cron/tar

