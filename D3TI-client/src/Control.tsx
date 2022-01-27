import React from "react"
import { View, StyleSheet, Button } from "react-native";
import { KorolJoystick } from "korol-joystick";

interface JoystickData {
    type: string;
    position: {
        x: number;
        y: number;
    }
    force: number;
    angle: {
        radian: number;
        degree: number;
    }
}

export default class Control extends React.Component {

    handlerLeftJoystickMove(data: JoystickData) {
        // TODO
    }
    handlerLeftJoystickStop() {
        // TODO
    }
    handlerSound() {
        // TODO
    }
    handlerFire() {
        // TODO
    }
    handlerRightJoystickMove(data: JoystickData) {
        // TODO
    }
    handlerRightJoystickStop() {
        // TODO
    }


    render() {
        return (
            <View style={styles.container}>
                <View style={styles.leftContainer}>
                    <View style={styles.button}>
                        <Button
                            title="Sound"
                            onPress={this.handlerSound}
                        />
                    </View>
                    <KorolJoystick
                        radius={50}
                        color="#000000"
                        onMove={this.handlerLeftJoystickMove}
                    />
                </View>
                <View style={styles.rightContainer}>
                    <View style={styles.button}>
                        <Button
                            title="Fire"
                            onPress={this.handlerFire}
                        />
                    </View>
                    <KorolJoystick
                        radius={50}
                        color="#000000"
                        onMove={this.handlerRightJoystickMove}
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
        height: 400,
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
    },
    rightContainer: {
        flex: 1,
        justifyContent: 'flex-end',
        alignItems: "flex-end",
        paddingEnd: 40,
        paddingBottom: 40,
    },
    button: {
        width: 100,
        height: 40,
        marginBottom: 40,
    }
});