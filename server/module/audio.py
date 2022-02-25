
import RPi.GPIO as GPIO

# SETUP

GPIO.setmode(GPIO.BCM)

BUZZER_GPIO = 12

# CODE


def buzzer_init():
    GPIO.setup(BUZZER_GPIO, GPIO.OUT)


async def buzzer_play(time: int):
    GPIO.output(BUZZER_GPIO, GPIO.HIGH)
    print("Beep")
    sleep(time)  # Delay in seconds
    GPIO.output(BUZZER_GPIO, GPIO.LOW)
