from fastapi import FastAPI
import uvicorn

from network.models import Position, Movement, Duration, ServiceStatus
from module.motor import motor_set_movement
from module.collision import startCollisionSystem, stopCollisionSystem

app = FastAPI()

if __name__ == '__main__':
    uvicorn.run("main:app", port=80, host='0.0.0.0')

@app.on_event("shutdown")
def shutdown_event():
    stopCollisionSystem()

@app.on_event("startup")
async def startup_event():
    startCollisionSystem()

####
##      ENDPOINTS
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
    if body.position1 < -100:
        raise HTTPException(status_code=400, detail="Error")
    # set position servo
    return {}

@app.post("/turret/movement")
async def turret_set_movement(body: Movement):
    if False:
        raise HTTPException(status_code=400, detail="Error")
    # set movement servo
    return {}

@app.post("/turret/fire")
async def turret_fire():
    if False:
        raise HTTPException(status_code=400, detail="Error")
    # fire
    return {}

@app.post("/wheel/movement")
async def wheel_movement(body: Movement):
    try:
        motor_set_movement(body.throttle1, body.throttle2)
    except ValueError as err:
        raise HTTPException(status_code=400, detail=err)
    return {}

@app.post("/audio/buzzer")
async def audio_buzzer():
    # trigger
    return {}

@app.put("/camera")
async def service_camera(body: ServiceStatus):
    # apply
    return {}

@app.put("/collision")
async def service_camera(body: ServiceStatus):
    try:
        if body.status:
            startCollisionSystem()
        else:
            stopCollisionSystem()
    except ValueError as err:
        raise HTTPException(status_code=400, detail=err)
    return {}