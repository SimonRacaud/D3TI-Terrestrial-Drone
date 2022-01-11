from pydantic import BaseModel

class Position(BaseModel):
    position1: int
    position2: int

class Movement(BaseModel):
    throttle1: int
    throttle2: int

class Duration(BaseModel):
    duration: int

class ServiceStatus(BaseModel):
    status: bool
