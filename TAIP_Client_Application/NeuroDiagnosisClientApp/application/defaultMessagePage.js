import React from "react";
import { View, Text, StyleSheet, ImageBackground } from "react-native";


function DefaultMessagePage({ route, navigation }) {
    const {message} = route.params;
    return (
        <ImageBackground
        source={require('../resources/logo.jpg')}
        style={{width: '100%', height: '100%'}}>
        <View style={styles.container}>
            <Text style={styles.message}>{message}</Text>
        </View>
        </ImageBackground>
    );
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      alignItems: 'center',
      justifyContent: 'center',
      height: 50,
    },
  
    message: {
      textAlign: "center",
      fontFamily: 'sans-serif-light',
      fontWeight: '800',
      fontSize: 40,
      color: '#025977',
    },
  });

  export default DefaultMessagePage;