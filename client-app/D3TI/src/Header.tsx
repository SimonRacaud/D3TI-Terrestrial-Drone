import React from "react"
import { StyleSheet, View, Text, Image, Alert, TouchableOpacity } from "react-native";

export default class Header extends React.Component {
    handlerTerminalButton() {
        // TODO
    }

    render() {
        return (
            <View
                style={styles.container}
            >
                <View style={styles.leftContainer}>
                    <View style={styles.circle}></View>
                    <Text style={styles.title}>Connected</Text>
                </View>
                <TouchableOpacity
                    onPress={() => this.handlerTerminalButton()}
                    style={styles.terminalButton}
                >
                    <Image
                        source={require('../assets/terminal_button.png')}
                        style={styles.terminalButtonImg}
                    />
                </TouchableOpacity>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        backgroundColor: '#000000',
        opacity: 0.8,
        height: 40,
        flexDirection: "row",
        justifyContent: "space-between",
        alignItems: "center",
    },
    leftContainer: {
        flexDirection: "row",
        alignItems: "center"
    },
    circle: {
        width: 25,
        height: 25,
        borderRadius: 44 / 2,
        backgroundColor: '#00FF00',
        marginStart: 10
    },
    title: {
        color: "#FFFFFF",
        marginStart: 10,
        fontWeight: "bold"
    },
    terminalButton: {
        height: 30,
        width: 30,
        marginEnd: 5
    },
    terminalButtonImg: {
        height: "100%",
        width: "100%",
    }
});
