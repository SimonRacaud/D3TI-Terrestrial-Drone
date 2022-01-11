#Libraries
import RPi.GPIO as GPIO
from time import sleep

#Select GPIO mode
GPIO.setmode(GPIO.BCM)

buzzer=12

GPIO.setup(buzzer, GPIO.OUT)

#Run forever loop
while True:
    GPIO.output(buzzer,GPIO.HIGH)
    print ("Beep")
    sleep(0.5) # Delay in seconds
    GPIO.output(buzzer,GPIO.LOW)
    print ("No Beep")
    sleep(1)
