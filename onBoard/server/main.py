from ftplib import error_reply
from poplib import error_proto
from fastapi import FastAPI, HTTPException
import uvicorn
import RPi.GPIO as GPIO

from network.models import Position, Movement, Duration, ServiceStatus
from module.motor import motor_set_movement
from module.servo import servo_set_position
from module.collision import startCollisionSystem, stopCollisionSystem
from module.audio import buzzer_init, buzzer_play

app = FastAPI()
BUZZER_TIME = 0.5  # seconds

if __name__ == '__main__':
    uvicorn.run("main:app", port=80, host='0.0.0.0')


@app.on_event("shutdown")
def shutdown_event():
    stopCollisionSystem()
    GPIO.cleanup()


@app.on_event("startup")
async def startup_event():
    startCollisionSystem()
    buzzer_init()

####
# ENDPOINTS
####


@app.get("/")
def read_root():
    return {"usage": [
        {"POST /turret/position": "Set turret position"},
        {"POST /turret/movement": "Set turret movement direction"},
        {"POST /turret/fire": "Gun activation"},
        {"POST /wheel/movement": "Vehicule movement direction"},
        {"POST /audio/buzzer": "Trigger buzzer"},
        {"UPDATE /camera": "Start or Stop streaming server"},
        {"UPDATE /collision": "Start or Stop collision system"},
    ]}


@app.post("/turret/position")
async def turret_set_position(body: Position):
    try:
        if body.position1 < 0 or body.position1 > 180:
            raise HTTPException(
                status_code=400, detail="Position 1 value out of range (0 - 180)")
        if body.position2 < 0 or body.position2 > 180:
            raise HTTPException(
                status_code=400, detail="Position 2 value out of range (0 - 180)")
        # set position servo
        servo_set_position(body.position1, body.position2)
    except ValueError as err:
        raise HTTPException(status_code=400, detail=err)
    except:
        raise HTTPException(status_code=400, detail="An error occurred")
    return {}


# @app.post("/turret/movement")
# async def turret_set_movement(body: Movement):
#     if False:
#         raise HTTPException(status_code=400, detail="Error")
#     # set movement servo
#     return {}


@app.post("/turret/fire")
async def turret_fire():
    if False:
        raise HTTPException(status_code=400, detail="Error")
    # fire TODO
    return {}


@app.post("/wheel/movement")
async def wheel_movement(body: Movement):
    try:
        motor_set_movement(body.throttle1, body.throttle2)
    except ValueError as err:
        raise HTTPException(status_code=400, detail=err)
    except:
        raise HTTPException(status_code=500, detail="An error occured")
    return {}


@app.post("/audio/buzzer")
async def audio_buzzer():
    # trigger
    try:
        buzzer_play(BUZZER_TIME)
    except ValueError as err:
        raise HTTPException(status_code=400, detail=err)
    except:
        raise HTTPException(status_code=500, detail="An error occurred")
    return {}


@app.put("/camera")
async def service_camera(body: ServiceStatus):
    # apply TODO
    return {}


@app.put("/collision")
async def service_collision(body: ServiceStatus):
    try:
        if body.status:
            startCollisionSystem()
        else:
            stopCollisionSystem()
    except ValueError as err:
        raise HTTPException(status_code=400, detail=err)
    return {}
