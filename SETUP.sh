#!/bin/bash

## => adafruit hat requirements
sudo apt install pip -y \
    && sudo pip3 install adafruit-circuitpython-motorkit

sudo apt-get install python-smbus
sudo apt-get install i2c-tools

# servo hat detection
sudo i2cdetect -y 1

sudo pip3 install adafruit-circuitpython-servokit

## Server

pip install fastapi && pip install "uvicorn[standard]"

sudo apt-get install python3-picamera

## Streaming server
sudo dpkg -i rws_0.74.1_armhf.deb
# config in : /opt/rws/etc
