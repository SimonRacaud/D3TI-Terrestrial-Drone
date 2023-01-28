# D3TI

The D3TI for "Drone Tout-terrain Intelligent" (english: Smart All-terrain Drone) is a remote controlled vehicule. It has been realized by myself alone as a independent personal project.

# Preview

![Preview1](/github/final1.jpg)
![Preview2](/github/final2.jpg)

### Electronic circuit plan:
![circuitPlan](/github/fritzing_schema_bb.png)

![circuitInBody](/github/global_circuit_in_case.jpg)

![cricuitPlanInBody](/github/draw_circuit_position.png)

![divider_bridge](/github/divider_bridge.jpg)

### Car body assembly simulation:
[![Video thumbnail](https://img.youtube.com/vi/i-M1x1rulGU/0.jpg)](https://www.youtube.com/watch?v=i-M1x1rulGU)

![concept_3dmodel](/github/concept_3dmodel.png)


# Specification

The specification is the following:
- The vehicule can move on 2 axis (Yaw and longitudinal)
- The vehicule movements can be remote controlled
- A graphical user interface allows to access vehicule control functions.
- Posseses a turret moving on 2 axis (Yaw and pitch)
- The turret support an Infrared camera.
- Anti-collision sytem at the front and back of the vehicule detecting close obstacles.

User interface functions:
- Managing vehicule movements (Yaw and longitudinal)
- Video streaming from the vehicule's camera
- Playing audio on the vehicule
- Managing vehicule's turret movement (2 axis).

# Car body & Electronic circuit
The car body have mainly been 3D printed.
The electronic circuit and car body plan have been specifically designed by myself for this project.

# Software

Three applications have been developed for this project:
- On-board command server (core application to manage vehicule electronics)
  - Technology: Python
- Video streaming server
  - Technology: NodeJS / JS
- Android client application
  - Technology: Kotlin
