from adafruit_motorkit import MotorKit
import board

kit = MotorKit(i2c=board.I2C(), address=0x60)
Throttle1 = 0
Throttle2 = 0

def motor_set_movement(throttle1: int, throttle2: int):
    global Throttle1, Throttle2
    if throttle1 < -100 or throttle1 > 100 \
        or throttle2 < -100 or throttle2 > 100:
        raise ValueError('motor_set_movement: invalid throttle')
    Throttle1 = (throttle1 / 100)
    Throttle2 = (throttle2 / 100)
    kit.motor1.throttle = Throttle1
    kit.motor2.throttle = Throttle2

def motor_get_movement():
    return (Throttle1, Throttle2)
