import React from "react"
import { View, StyleSheet, Image } from "react-native";
import { WebView } from 'react-native-webview';
import { SERVER_IP, SERVER_VIDEO_PORT } from "@env";

export default class VideoPlayer extends React.Component {

    render() {
        // TODO: update IP addr
        return (
            <View style={styles.container}>
                {/* <WebView
                    style={styles.webview}
                    javaScriptEnabled={true}
                    domStorageEnabled={true}
                    originWhitelist={['*']}
                    source={{ uri: `http://${SERVER_IP}:${SERVER_VIDEO_PORT}/` }}
                    onError={(event) => alert(`Webview error: ${event.nativeEvent.description}`)}
                    onHttpError={(event) => alert(`Webview http error: ${event.nativeEvent.description}`)}
                    injectedJavaScriptBeforeContentLoaded={`
                        window.onerror = function(message, sourcefile, lineno, colno, error) {
                        alert("Message: " + message + " - Source: " + sourcefile + " Line: " + lineno + ":" + colno);
                        return true;
                        };
                        true;
                    `}
                /> */}
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
        height: '100%'
    }
});