from adafruit_motorkit import MotorKit
import board

kit = MotorKit(i2c=board.I2C(), address=0x60)
Throttle1 = 0
Throttle2 = 0


def convert_throttle(throttle: int):
    if throttle == 0:
        return 0
    elif throttle > 0:
        return (throttle / 100) * 0.6 + 0.4
    else:
        return (throttle / 100) * 0.6 - 0.4


def motor_set_movement(throttle1: int, throttle2: int):
    global Throttle1, Throttle2
    if throttle1 < -100 or throttle1 > 100 \
            or throttle2 < -100 or throttle2 > 100:
        raise ValueError('motor_set_movement: invalid throttle')
    Throttle1 = convert_throttle(throttle1)
    Throttle2 = convert_throttle(throttle2)
    kit.motor1.throttle = Throttle1
    kit.motor2.throttle = Throttle2


def motor_get_movement():
    return (Throttle1, Throttle2)
