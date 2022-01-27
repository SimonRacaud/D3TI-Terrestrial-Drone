import React from "react"
import { View, StyleSheet, Image } from "react-native";
import { WebView } from 'react-native-webview';

export default class VideoPlayer extends React.Component {

    render() {
        return (

            <View style={styles.container}>
                <WebView
                    style={styles.webview}
                    originWhitelist={['*']}
                    source={{ html: '<h1>Hello world</h1>' }}
                    onError={(event) => alert(`Webview error: ${event.nativeEvent.description}`)}
                />
                <Image
                    source={require("../assets/camera_frame_5.png")}
                    style={styles.camera_frame}
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        width: '100%',
        height: '100%',
        flex: 1,
        justifyContent: 'center',
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
        width: '100%',
        height: '100%',
        backgroundColor: 'red'
    }
});