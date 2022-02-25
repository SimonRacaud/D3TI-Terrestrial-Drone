
import RPi.GPIO as GPIO
from time import sleep

# SETUP

GPIO.setmode(GPIO.BCM)

BUZZER_GPIO = 12

# CODE


def buzzer_init():
    GPIO.setup(BUZZER_GPIO, GPIO.OUT)


def buzzer_play(time: float):
    GPIO.output(BUZZER_GPIO, GPIO.HIGH)
    print("Beep")
    sleep(time)  # Delay in seconds
    GPIO.output(BUZZER_GPIO, GPIO.LOW)
