import React, { useState } from "react";
import { GestureResponderEvent, View } from "react-native";
import * as utils from "./utils";

export interface JoystickUpdateEvent {
  type: "move" | "stop" | "start";
  position: {
    x: number;
    y: number;
  };
}

interface IProps {
  onStart?: (e: JoystickUpdateEvent) => void;
  onMove?: (e: JoystickUpdateEvent) => void;
  onStop?: (e: JoystickUpdateEvent) => void;
  radius?: number;
  color?: string;
  positionX: number;
  positionY: number;
}

const AxisPad: React.FC<IProps> = (props) => {
  const {
    onStart,
    onMove,
    onStop,
    color = "#000000",
    radius = 150,
  } = props;

  const wrapperRadius = radius;
  const nippleRadius = wrapperRadius / 3;

  const [x, setX] = useState(wrapperRadius - nippleRadius);
  const [y, setY] = useState(wrapperRadius - nippleRadius);

  const handleTouchMove = (e: GestureResponderEvent) => {
    const fingerX = e.nativeEvent.locationX;
    const fingerY = e.nativeEvent.locationY;

    let coordinates = {
      x: fingerX - nippleRadius,
      y: fingerY - nippleRadius,
    };

    // Dirty ditry
    if (fingerX > wrapperRadius * 2 || fingerY > wrapperRadius * 2)
      return;

    setX(coordinates.x);
    setY(coordinates.y);

    /// Position => scope (-1; 1)
    const position = {
      x: (fingerX - wrapperRadius) / wrapperRadius,
      y: (fingerY - wrapperRadius) / wrapperRadius
    }

    onMove &&
      onMove({
        position: position,
        type: "move",
      });
  };

  const handleTouchEnd = () => {
    setX(wrapperRadius - nippleRadius);
    setY(wrapperRadius - nippleRadius);
    onStop &&
      onStop({
        position: {
          x: 0,
          y: 0,
        },
        type: "stop",
      });
  };

  const handleTouchStart = () => {
    onStart &&
      onStart({
        position: {
          x: 0,
          y: 0,
        },
        type: "start",
      });
  };

  return (
    <View
      onTouchMove={handleTouchMove}
      onTouchEnd={handleTouchEnd}
      onTouchStart={handleTouchStart}
      style={[
        {
          width: 2 * radius,
          height: 2 * radius,
          backgroundColor: `${color}55`,
        }
      ]}
    >
      <View
        pointerEvents="none"
        style={[
          {
            height: 2 * nippleRadius,
            width: 2 * nippleRadius,
            borderRadius: nippleRadius,
            backgroundColor: `${color}bb`,
            position: "absolute",
            top: y,
            left: x
          }
        ]}
      />
    </View>
  );
};

export default AxisPad;
