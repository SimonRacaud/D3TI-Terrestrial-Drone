from adafruit_motorkit import MotorKit
import board
from data import Collision

kit = MotorKit(i2c=board.I2C(), address=0x60)


class MotorController:
    Throttle1 = 0
    Throttle2 = 0

    directionLock = Collision.NONE

    @staticmethod
    def _convert_throttle(throttle: int):
        if throttle == 0:
            return 0
        elif throttle > 0:
            return (throttle / 100) * 0.6 + 0.4
        else:
            return (throttle / 100) * 0.6 - 0.4

    @classmethod
    def motor_set_movement(cls, throttle1: int, throttle2: int):
        global Throttle1, Throttle2
        if throttle1 < -100 or throttle1 > 100 \
                or throttle2 < -100 or throttle2 > 100:
            raise ValueError('motor_set_movement: invalid throttle')
        cls.Throttle1 = cls._convert_throttle(throttle1)
        cls.Throttle2 = cls._convert_throttle(throttle2)
        cls._evalDirectionLock()
        kit.motor1.throttle = cls.Throttle1
        kit.motor2.throttle = cls.Throttle2

    @classmethod
    def motor_get_movement(cls):
        return (cls.Throttle1, cls.Throttle2)

    @classmethod
    def setDirectionLock(cls, value: Collision):
        cls.directionLock = value
        cls._evalDirectionLock()

    @classmethod
    def _evalDirectionLock(cls):
        if cls.directionLock == Collision.FRONT:
            if cls.Throttle1 > 0:
                cls.Throttle1 = 0
            if cls.Throttle2 > 0:
                cls.Throttle2 = 0
        elif cls.directionLock == Collision.BACK:
            if cls.Throttle1 < 0:
                cls.Throttle1 = 0
            if cls.Throttle2 < 0:
                cls.Throttle2 = 0
