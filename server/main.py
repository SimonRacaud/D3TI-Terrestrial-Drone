from fastapi import FastAPI

from network.models import Position, Movement, Duration, ServiceStatus
#from module.servo import todo

app = FastAPI()

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
        {"POST /audio/buzzer": "TRigger buzzer"},
        {"UPDATE /camera": "Start or Stop streaming server"},
        {"UPDATE /collision": "Start or Stop collision system"},
    ]}

@app.post("/turret/position")
async def turret_set_position(body: Position):
    if body.position1 < -100:
        raise HTTPException(status_code=400, detail="Error")
    # set position
    return {}

@app.post("/turret/movement")
async def turret_set_movement(body: Movement):
    if False:
        raise HTTPException(status_code=400, detail="Error")
    # set movement
    return {}

@app.post("/turret/fire")
async def turret_fire():
    if False:
        raise HTTPException(status_code=400, detail="Error")
    # fire
    return {}

@app.post("/wheel/movement")
async def wheel_movement(body: Movement):
    if False:
        raise HTTPException(status_code=400, detail="Error")
    # fire
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
    # apply
    return {}

###
##  MAIN
###

def main():
    pass

if __name__ == "__main__":
    main()