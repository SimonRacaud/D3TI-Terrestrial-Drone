from adafruit_servokit import ServoKit

# By default, all HATs are address 0x40
kit = ServoKit(channels=16, address=0x42)


def position_to_angle(position: int):
    if position > 100 or position < -100:
        raise ValueError('position_to_angle: invalid position')
    elif position > 0:
        return (position / 100) * 180
    elif position < 0:
        return 360 - (((-position) / 100) * 180)
    return 0


def servo_set_position(position1: int, position2: int):
    print("servo: set angle ", position1, position2)
    kit.servo[0].angle = position1
    #kit.continuous_servo[0].throttle = 0
    if position2 > 150:
        position2 = 150  # Max position
    kit.servo[1].angle = position2
    #kit.continuous_servo[1].throttle = 0


"""
def servo_set_movement(throttle1: int, throttle2: int):
    if throttle1 < -100 or throttle1 > 100:
        raise ValueError('servo_set_movement: invalid throttle')
    kit.continuous_servo[0].throttle = (throttle1 / 100)
    kit.continuous_servo[1].throttle = (throttle2 / 100)
"""
