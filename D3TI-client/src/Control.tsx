import React from "react"
import { View, StyleSheet, Button } from "react-native";
import { SERVER_IP } from "@env";
import AxisPad, { JoystickUpdateEvent } from "./Joystick/Joystick";

export default class Control extends React.Component {

    async handlerLeftJoystickMove(data: JoystickUpdateEvent) {
        // Turret control
        const position = data.position;

        const angleX = position.x * 90 + 90; // (0 to 180)
        const angleY = position.y * 90 + 90; // (0 to 180)

        console.debug("Turret angle : X", angleX, "Y", angleY); // TODO: DEBUG
        try {
            const response = await fetch(`http://${SERVER_IP}/turret/position`, {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    position1: angleX,
                    position2: angleY
                })
            });
            this.responseHandler(response);
        } catch (err) { }
    }

    async handlerSound() {
        const response = await fetch(`http://${SERVER_IP}/audio/buzzer`, {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            }
        });
        this.responseHandler(response);
    }
    async handlerFire() {
        const response: Response = await fetch(`http://${SERVER_IP}/turret/fire`, {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            }
        });
        this.responseHandler(response);
    }
    async handlerRightJoystickMove(data: JoystickUpdateEvent) {
        // Movement control
        const position = data.position;

        const coefLeft = (position.x < 0) ? 1 : (1 - position.x);
        const coefRight = (position.x > 0) ? 1 : (-position.x);

        const throttleLeft = (position.y * 100) * coefLeft; // (-100 to 100)
        const throttleRight = (position.y * 100) * coefRight; // (-100 to 100)

        console.debug("Motors throttle : Left", throttleLeft, "Right", throttleRight); // TODO: DEBUG
        try {
            const response = await fetch(`http://${SERVER_IP}/wheel/movement`, {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    throttle1: 0,
                    throttle2: 0
                })
            });
            this.responseHandler(response);
        } catch (err) { }
    }

    responseHandler(response: Response) {
        if (!response.ok)
            console.error(`Network error: Code ${response.statusText}, ${response.body}.`)
        //TODO: Terminal log
    }


    render() {
        return (
            <View style={styles.container}>
                <View style={styles.leftContainer}>
                    <View style={styles.button}>
                        <Button
                            title="Sound"
                            onPress={() => this.handlerSound()}
                        />
                    </View>
                    <AxisPad
                        onMove={(e: JoystickUpdateEvent) => this.handlerLeftJoystickMove(e)}
                        radius={100}
                    />
                </View>
                <View style={styles.rightContainer}>
                    <View style={styles.button}>
                        <Button
                            title="Fire"
                            onPress={this.handlerFire}
                        />
                    </View>
                    <AxisPad
                        onMove={(e: JoystickUpdateEvent) => this.handlerRightJoystickMove(e)}
                        radius={100}
                    />
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        width: '100%',
        height: '100%',
        position: 'absolute',
        left: 0,
        bottom: 0,
        justifyContent: 'space-between',
        flexDirection: 'row'
    },
    leftContainer: {
        flex: 1,
        justifyContent: 'flex-end',
        paddingStart: 40,
        paddingBottom: 40,
        // backgroundColor: "#FF00FF",
        height: '100%',
    },
    rightContainer: {
        flex: 1,
        justifyContent: 'flex-end',
        alignItems: "flex-end",
        paddingEnd: 40,
        paddingBottom: 40,
        // backgroundColor: "#FFFF00",
    },
    button: {
        width: 100,
        height: 40,
        marginBottom: 40,
    }
});