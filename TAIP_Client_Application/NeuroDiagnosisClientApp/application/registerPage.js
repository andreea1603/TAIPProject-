import { Button } from "@react-native-material/core";
import React from "react";
import { TextInput, View, Text, StyleSheet, ImageBackground } from "react-native";

function RegisterPage({navigation}) {
    return (
        <ImageBackground
        source={require('../resources/logo.jpg')}
        style={{width: '100%', height: '100%'}}>
        <View style={styles.container}>
            <Text style={styles.formLabel} >Register</Text>
            <View>
                <TextInput placeholder="First Name" style={styles.inputStyle}/>
                <TextInput placeholder="Last Name" style={styles.inputStyle}/>
                <TextInput placeholder="Email Address" style={styles.inputStyle}/>
                <TextInput placeholder="Username" style={styles.inputStyle}/>
                <TextInput secureTextEntry={true} placeholder="Password" style={styles.inputStyle}/>
            </View>
            <Button title="Create Account"
              style = {{ height: 40, marginTop: '10%' }}
              color = '#025977'
            onPress={() => navigation.navigate('DefaultMessagePage', {message: "Check your inbox for the registration email."})}/>
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
  
    formLabel: {
      fontFamily: 'sans-serif-light',
      fontWeight: '800',
      fontSize: 40,
      color: '#025977',
    },

    inputStyle: {
      marginTop: 20,
      width: 300,
      height: 50,
      paddingHorizontal: 10,
      borderRadius: 50,
      backgroundColor: '#BAE3F7',
    },
  });

  export default RegisterPage;