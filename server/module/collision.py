import RPi.GPIO as GPIO
import threading
import time

from data import Direction

### CONFIG ###
TRIG1 = 26        
ECHO1 = 19

TRIG2 = 16
ECHO2 = 20

CHECK_INTERVAL = 0.5 # second

MIN_DISTANCE = 10 # cm

### SETUP ###

GPIO.setmode(GPIO.BCM)

## SETUP ###

event = threading.Event()

class CollisionSystem(threading.Thread):
    def __init__(self, event, callback):
        threading.Thread.__init__(self)
        self.event = event
        self._stop_event = threading.Event()
        self.callback = callback
        GPIO.setup(Trig1, GPIO.OUT)
        GPIO.setup(Echo1, GPIO.IN)
        GPIO.output(Trig1, False)

        GPIO.setup(Trig2, GPIO.OUT)
        GPIO.setup(Echo2, GPIO.IN)
        GPIO.output(Trig2, False)
    
    def stop(self):
        self._stop_event.set() 

    def stopped(self):
        return self._stop_event.is_set()

    def run(self):
        while not self.stopped():
            if self._compute_distance(TRIG1, ECHO1) < MIN_DISTANCE:
                self.callback(Direction.FORWARD)
            if self._compute_distance(TRIG2, ECHO2) < MIN_DISTANCE:
                self.callback(Direction.BACKWARD)
            time.sleep(CHECK_INTERVAL)
        self.event.set()
        GPIO.cleanup()

    def _compute_distance(trig, echo):
        GPIO.output(trig, True)
        time.sleep(0.00001)
        GPIO.output(trig, False)

        startTime = time.time()
        stopTime = time.time()
        while GPIO.input(echo) == 0:
            startTime = time.time()
        while GPIO.input(echo) == 1:
            endTime = time.time()
        return round((endTime - startTime) * 34000 / 2, 1)


def startCollisionSystem():
    event.clear()
    collision = CollisionSystem(event)
    collision.start()
    return collision

def stopCollisionSystem(system: CollisionSystem):
    system.stop()
    event.wait()