import React, { useState } from "react";
import { GestureResponderEvent, View } from "react-native";
import * as utils from "./utils";

export interface Position {
    x: number,
    y: number
}

export interface JoystickUpdateEvent {
    type: "move" | "stop" | "start";
    position: Position
}

interface IProps {
    onStart?: (e: JoystickUpdateEvent) => void;
    onMove?: (e: JoystickUpdateEvent) => void;
    onStop?: (e: JoystickUpdateEvent) => void;
    radius?: number;
    color?: string;
    positionX?: number;
    positionY?: number;
}

// interface IState {
//   x: number,
//   y: number
// }

// export default class AxisPad extends React.Component<IProps> {

//   constructor(props: IProps) {
//     super(props);
//   }
// }

const AxisPad: React.FC<IProps> = (props) => {
    const {
        onStart,
        onMove,
        onStop,
        color = "#000000",
        radius = 150,
        positionX = 0,
        positionY = 0
    } = props;

    const wrapperRadius = radius;
    const nippleRadius = wrapperRadius / 3;

    const [x, setX] = useState(wrapperRadius - nippleRadius);
    const [y, setY] = useState(wrapperRadius - nippleRadius);

    const absoluteToRelative = (posX: number, posY: number): Position => {
        let result = {
            x: posX - positionX - wrapperRadius,
            y: posY - positionY - wrapperRadius
        };

        if (result.x < -wrapperRadius)
            result.x = -wrapperRadius
        else if (result.x > wrapperRadius)
            result.x = wrapperRadius

        if (result.y < -wrapperRadius)
            result.y = -wrapperRadius
        else if (result.y > wrapperRadius)
            result.y = wrapperRadius

        return result;
    }

    const handleTouchMove = (e: GestureResponderEvent) => {
        /// Scope ([-radius; radius], [-radius; radius])
        const touchCoord = absoluteToRelative(e.nativeEvent.pageX, e.nativeEvent.pageY)

        setX(touchCoord.x + radius);
        setY(touchCoord.y + radius);

        /// Position => scope (-1; 1)
        const coord = {
            x: touchCoord.x / wrapperRadius,
            y: touchCoord.y / wrapperRadius
        }

        onMove &&
            onMove({
                position: coord,
                type: "move",
            });
    };

    const handleTouchEnd = (e: GestureResponderEvent) => {
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

    const handleTouchStart = (e: GestureResponderEvent) => {
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
                    position: "absolute",
                    left: positionX,
                    top: positionY
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
                        left: x,
                        top: y
                    },
                ]}
            />
        </View>
    );
};

export default AxisPad;
