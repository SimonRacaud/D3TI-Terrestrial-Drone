import React from "react"
import { StyleSheet, View, Text, Image, TouchableOpacity } from "react-native";
import Terminal from "./Terminal";

interface IProps {
}

interface IState {
    showTerminal: boolean;
    connected: boolean;
}

export default class Header extends React.Component<IProps, IState> {
    constructor(props: IProps) {
        super(props);

        this.state = {
            showTerminal: false,
            connected: false
        }
    }

    handlerTerminalButton() {
        this.setState((previousState: IState, props: IProps) => ({
            showTerminal: !previousState.showTerminal,
        }));
    }

    connection() {
        // TODO
        this.setState((previousState: IState, props: IProps) => ({
            connected: true,
        }));
    }

    disconnection() {
        // TODO
        this.setState((previousState: IState, props: IProps) => ({
            connected: false,
        }));
    }

    render() {
        return (
            <View style={styles.rootContainer}>
                <View
                    style={styles.container}
                >
                    <View style={styles.leftContainer}>
                        {this.state.connected &&
                            <View style={[styles.circle, styles.connectedCicle]}></View> ||
                            <View style={[styles.circle, styles.disconnectedCicle]}></View>
                        }
                        {this.state.connected &&
                            <Text style={styles.title}>Connected</Text> ||
                            <Text style={styles.title}>Disconnected</Text>
                        }
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
                {this.state.showTerminal && <Terminal />}
            </View >
        );
    }
}

const styles = StyleSheet.create({
    rootContainer: {
        zIndex: 1,
        position: 'absolute',
        top: 0,
        left: 0,
        width: '100%',
    },
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
        marginStart: 10
    },
    connectedCicle: {
        backgroundColor: "#00FF00",
    },
    disconnectedCicle: {
        backgroundColor: "#FF0000",
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
