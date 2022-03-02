import RPi.GPIO as GPIO
import threading
import time

from data import Direction
from module.motor import motor_set_movement, motor_get_movement

### CONFIG ###
TRIG1 = 26
ECHO1 = 19

TRIG2 = 16
ECHO2 = 20

CHECK_INTERVAL = 0.5  # second

MIN_DISTANCE = 10  # cm

### SETUP ###

GPIO.setmode(GPIO.BCM)

## SETUP ###

event = threading.Event()
collisionSystem = None


class CollisionSystem(threading.Thread):
    def __init__(self, event, callback):
        threading.Thread.__init__(self)
        self.event = event
        self._stop_event = threading.Event()
        self.callback = callback
        GPIO.setup(TRIG1, GPIO.OUT)
        GPIO.setup(ECHO1, GPIO.IN)
        GPIO.output(TRIG1, False)

        GPIO.setup(TRIG2, GPIO.OUT)
        GPIO.setup(ECHO2, GPIO.IN)
        GPIO.output(TRIG2, False)

    def stop(self):
        self._stop_event.set()
        print("Collision service : send stop event...")

    def stopped(self):
        return self._stop_event.is_set()

    def run(self):
        print("Start collision service...")
        while not self.stopped():
            if self._compute_distance(TRIG2, ECHO2) < MIN_DISTANCE:
                self.callback(Direction.FORWARD)
            if self._compute_distance(TRIG1, ECHO1) < MIN_DISTANCE:
                self.callback(Direction.BACKWARD)
            time.sleep(CHECK_INTERVAL)
        self.event.set()
        print("Collision service stopped.")

    def _compute_distance(self, trig, echo):
        GPIO.output(trig, True)
        time.sleep(0.00001)
        GPIO.output(trig, False)

        startTime = time.time()
        endTime = time.time()
        while GPIO.input(echo) == 0:
            startTime = time.time()
            if startTime - endTime > 5:
                print(
                    "Warning: collision system GPIO ${echo} doesn't respond.")
        while GPIO.input(echo) == 1:
            endTime = time.time()
        distance = round((endTime - startTime) * 34000 / 2, 1)  # DEBUG
        #print("Distance: ", distance)
        return distance


def callback_stop_motor(direction: Direction):
    (throttle1, throttle2) = motor_get_movement()
    if direction == Direction.BACKWARD and (throttle1 > 0 or throttle2 > 0):
        print("collision: stop backward movement")
        motor_set_movement(0, 0)
    elif direction == Direction.FORWARD and (throttle1 < 0 or throttle2 < 0):
        print("collision: stop forward movement")
        motor_set_movement(0, 0)


def startCollisionSystem():
    global collisionSystem
    if collisionSystem != None:
        stopCollisionSystem()

    event.clear()
    collision = CollisionSystem(event, callback_stop_motor)
    collision.start()
    collisionSystem = collision


def stopCollisionSystem():
    global collisionSystem
    if collisionSystem != None:
        collisionSystem.stop()
        event.wait()
        collisionSystem = None
