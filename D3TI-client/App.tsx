import React from 'react';
import { Dimensions, StyleSheet, Text, View } from 'react-native';
import Control from './src/Control';
import Header from './src/Header'
import VideoPlayer from './src/VideoPlayer';

const ScreenHeight = Dimensions.get("window").height;

export default function App() {
  return (
    <View style={styles.container}>
      <Header />
      <VideoPlayer />
      <Control />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#fff',
    height: ScreenHeight
  },
});
