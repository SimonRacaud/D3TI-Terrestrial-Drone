#!/usr/bin/env python

import asyncio
import websockets
import time

async def on_connect(websocket):
    print("start")

    #name = await websocket.recv()
    for x in range(0, 10):
        await websocket.send("hello world")

async def main():
    async with websockets.serve(on_connect, "localhost", 8001):
        await asyncio.Future()  # run forever

if __name__ == "__main__":
    asyncio.run(main())