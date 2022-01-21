import React from "react"
import { View, StyleSheet, FlatList, Text, Button } from "react-native";

interface IProps {
}

interface IState {
    list: string[];
}

export default class Terminal extends React.Component<IProps, IState> {

    constructor(props: IProps) {
        super(props);

        this.state = {
            list: [],
        }
    }

    pushLine(message: string) {
        this.setState((oldState: IState, props: IProps) => ({
            list: [...oldState.list, message]
        }));
    }
    clearList() {
        this.setState((oldState: IState, props: IProps) => ({
            list: []
        }));
    }

    render() {
        return (
            <View style={styles.container}>
                <FlatList
                    data={this.state.list}
                    renderItem={({ item }) => <Text style={styles.item}>$&gt; {item}</Text>}
                />
                <Button onPress={() => this.clearList()} title="Clear" />
            </View>
        );
    }
};

const styles = StyleSheet.create({
    container: {
        width: 500,
        height: 500,
        backgroundColor: 'black',
        position: 'absolute',
        right: 0,
        top: 40,
        padding: 10,
    },
    item: {
        color: 'white',
    }
});