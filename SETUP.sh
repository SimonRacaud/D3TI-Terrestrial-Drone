#!/bin/bash

## => adafruit hat requirements
sudo apt install pip -y \
    && sudo pip3 install adafruit-circuitpython-motorkit

sudo apt-get install python-smbus
sudo apt-get install i2c-tools

# servo hat detection
sudo i2cdetect -y 1

sudo pip3 install adafruit-circuitpython-servokit
