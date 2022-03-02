#!/bin/bash

## => adafruit hat requirements
sudo apt install pip -y \
    && sudo pip3 install adafruit-circuitpython-motorkit \
    && sudo apt-get install -y python-smbus i2c-tools \
    \
    && sudo i2cdetect -y 1 \
    && sudo pip3 install adafruit-circuitpython-servokit

## Server
pip install fastapi && pip install "uvicorn[standard]"

# Update uri variable in ./onBoard/server_streaming/client/index.html

## Shutdown button

# Write in /etc/rc.local
# "sudo nohup python3 /home/pi/scripts/utils/shutdown.py > /tmp/shutdown.out 2> /tmp/shutdown.err < /dev/null &"

## AUTO-START BACKEND

# $> crontab -e
# @reboot cd /home/pi/scripts/server_streaming/ && /usr/bin/npm run start
# @reboot cd /home/pi/scripts/server && /usr/local/bin/uvicorn main:app --reload --port 8080 --host 0.0.0.0

## SETUP WIFI HOTSPOT
sudo cp /etc/wpa_supplicant/wpa_supplicant.conf /etc/wpa_supplicant/wpa_supplicant.conf.save # save
sudo cp /dev/null /etc/wpa_supplicant/wpa_supplicant.conf # empty
sudo emacs /etc/wpa_supplicant/wpa_supplicant.conf
"""
ctrl_interface=DIR=/var/run/wpa_supplicant GROUP=netdev
update_config=1
"""
wget -q https://git.io/voEUQ -O /tmp/raspap && bash /tmp/raspap # intall raspapt
# Wifi password : ChangeMe
# GUI account : admin / secret
# Default ip : 10.3.141.1

### OLD

#sudo apt-get install -y python3-picamera
## Streaming server
#sudo dpkg -i rws_0.74.1_armhf.deb ## OLD
# config in : /opt/rws/etc 