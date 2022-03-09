from enum import Enum


class Direction(Enum):
    FORWARD = 1
    BACKWARD = 2


class Collision(Enum):
    NONE = 0
    FRONT = 1
    BACK = 2
