import React from "react"
import { View, StyleSheet, Image } from "react-native";

export default class VideoPlayer extends React.Component {

    render() {
        return (
            <View style={styles.container}>
                <Image
                    source={require("../assets/camera_frame_5.png")}
                    style={styles.camera_frame}
                />
                {/* TODO video player */}
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        width: 'auto',
        height: '100%'
    },
    camera_frame: {
        width: 'auto',
        height: '100%',
        resizeMode: 'stretch',
    }
});