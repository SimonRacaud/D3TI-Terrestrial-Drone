import time
import board
from adafruit_motorkit import MotorKit

kit = MotorKit(i2c=board.I2C(), address=0x60)

# Forward
kit.motor1.throttle = 1.0
kit.motor2.throttle = 1.0
time.sleep(2)
kit.motor1.throttle = 0
kit.motor2.throttle = 0

time.sleep(1)

# Backward
kit.motor1.throttle = -1.0
kit.motor2.throttle = -1.0
time.sleep(2)
kit.motor1.throttle = 0
kit.motor2.throttle = 0
