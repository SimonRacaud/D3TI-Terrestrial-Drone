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

#sudo apt-get install -y python3-picamera
## Streaming server
#sudo dpkg -i rws_0.74.1_armhf.deb ## OLD
# config in : /opt/rws/etc 


## Service
# Set d3ti.service in => /lib/systemd/system/
sudo chmod 644 /lib/systemd/system/d3ti.service
sudo systemctl daemon-reload
sudo systemctl enable d3ti.service