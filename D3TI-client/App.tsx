import React from 'react';
import { Dimensions, StyleSheet, View } from 'react-native';
import Control from './src/Control';
import Header from './src/Header'
import VideoPlayer from './src/VideoPlayer';
import { OrientationLocker, LANDSCAPE } from 'react-native-orientation-locker';

export const ScreenHeight = Dimensions.get("window").height;
export const ScreenWidth = Dimensions.get("window").width;

export default class App extends React.Component {
  render() {
    return (
      <View style={styles.container} >
        <OrientationLocker orientation={LANDSCAPE} />
        <Header />
        <VideoPlayer />
        <Control />
      </View >
    );
  }
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#fff',
    height: ScreenHeight,
    width: ScreenWidth
  },
});
