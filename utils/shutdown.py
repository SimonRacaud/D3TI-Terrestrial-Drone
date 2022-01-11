# !/bin/python

import RPi.GPIO as GPIO
import time
import os

# Setup the pin with internal pullups enabled and pin in reading mode.
GPIO.setmode(GPIO.BCM)
GPIO.setup(21, GPIO.IN, pull_up_down=GPIO.PUD_UP)

def Shutdown(channel):
    print("Shutting Down")
    time.sleep(1)
    os.system("sudo shutdown -h now")

# Add our function to execute when the button pressed event happens
GPIO.add_event_detect(21, GPIO.FALLING, callback=Shutdown, bouncetime=2000)

# Now wait!
while True:
    time.sleep(1)
