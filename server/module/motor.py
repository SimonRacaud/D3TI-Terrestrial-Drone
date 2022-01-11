import board
from adafruit_motorkit import MotorKit

kit = MotorKit(address=0x60)

def motor_set_movement(throttle1: int, throttle2: int):
    if throttle1 < -100 or throttle1 > 100
        or throttle2 < -100 or throttle2 > 100:
        raise ValueError('motor_set_movement: invalid throttle')
    kit.motor1.throttle = (throttle1 / 100)
    kit.motor2.throttle = (throttle2 / 100)
