import React from "react"
import { View, StyleSheet, Image } from "react-native";
import { WebView } from 'react-native-webview';

export default class VideoPlayer extends React.Component {

    render() {
        return (
            <View style={styles.container}>
                <WebView
                    originWhitelist={['*']}
                    source={{ html: '<h1>Hello world</h1>' }}
                />
                {<Image
                    source={require("../assets/camera_frame_5.png")}
                    style={styles.camera_frame}
                />}
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        width: '100%',
        height: '100%',
        justifyContent: 'center',
        alignItems: 'center',
    },
    camera_frame: {
        width: '90%',
        height: '90%',
        resizeMode: 'stretch',
        position: 'absolute',
        bottom: '5%',
        left: '5%',
    },
    webview: {
        marginTop: 100,
        width: '50%',
        height: '50%',
    }
});