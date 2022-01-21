import React from "react"
import { View, StyleSheet, Button } from "react-native";
import { Joystick } from 'react-joystick-component';

export default class Control extends React.Component {

    handlerLeftJoystickMove() {

    }
    handlerLeftJoystickStop() {

    }
    handlerSound() {

    }
    handlerFire() {

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
                    <Joystick
                        size={100}
                        baseColor="#50505050"
                        stickColor="black"
                        move={this.handlerLeftJoystickMove}
                        stop={this.handlerLeftJoystickStop}
                    />
                </View>
                <View style={styles.rightContainer}>
                    <View style={styles.button}>
                        <Button
                            title="Fire"
                            onPress={this.handlerFire}
                        />
                    </View>
                    <Joystick
                        size={100}
                        baseColor="#50505050"
                        stickColor="black"
                        move={this.handlerLeftJoystickMove}
                        stop={this.handlerLeftJoystickStop}
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
        height: '50%',
        position: 'absolute',
        left: 0,
        top: '50%',
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
        height: 20,
        marginBottom: 50,
    }
});