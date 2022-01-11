import RPi.GPIO as GPIO
import time
import signal

### CONFIG ###
Trig1 = 26        
Echo1 = 19

Trig2 = 16
Echo2 = 20

TIME_LOOP = 0.5

### SETUP ###

GPIO.setmode(GPIO.BCM)

GPIO.setup(Trig1, GPIO.OUT)
GPIO.setup(Echo1, GPIO.IN)
GPIO.output(Trig1, False)

GPIO.setup(Trig2, GPIO.OUT)
GPIO.setup(Echo2, GPIO.IN)
GPIO.output(Trig2, False)

## CODE ##

def compute_distance(trig, echo):
   GPIO.output(trig, True)
   time.sleep(0.00001)
   GPIO.output(trig, False)

   startTime = time.time()
   stopTime = time.time()
   while GPIO.input(echo) == 0:  ## Tant que de l'ultrason n'est pas reçu
     startTime = time.time()
   while GPIO.input(echo) == 1:   ## Retour de l'Echo
     endTime = time.time()

   distance = round((endTime - startTime) * 34000 / 2, 1)

   print("[T{}, E{}] La distance est de : {} cm".format(trig, echo, distance))

    
def main():
    print("+-----------------------------------------------------------+")
    print("|   Mesure de distance par le capteur ultrasonore HC-SR04   |")
    print("+-----------------------------------------------------------+")
    try:
        while True:
            time.sleep(TIME_LOOP)
            compute_distance(Trig1, Echo1)
            compute_distance(Trig2, Echo2)
    except KeyboardInterrupt:
        print("Bye")
    GPIO.cleanup()

if __name__ == "__main__":
    main()
