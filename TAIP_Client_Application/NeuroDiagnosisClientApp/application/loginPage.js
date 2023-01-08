import React, { useState } from "react";
import { TextInput, View, Text, StyleSheet, ImageBackground } from "react-native";
import { Button } from "@react-native-material/core";
import AsyncStorage from '@react-native-async-storage/async-storage';

function LoginPage({navigation}) {
  const [usernameOrEmail, setUsernameOrEmail] = useState('');
  const [password, setPassword] = useState('');
      return (
        <ImageBackground
        source={require('../resources/logo.jpg')}
        style={{width: '100%', height: '100%'}}>
        <View style={styles.container}>
            <Text style={styles.formLabel}>Login</Text>
            <View>
                <TextInput 
                placeholder="Username | Email Address" 
                style={styles.inputStyle}
                onChangeText={input => setUsernameOrEmail(input)}
                />
                <TextInput secureTextEntry={true} 
                placeholder="Password" 
                style={styles.inputStyle}
                onChangeText={input => setPassword(input)}/>
            </View>
        <Button title="Login"
              style = {{ height: 40, marginTop: '10%' }}
              color = '#025977'
            onPress={() => login(usernameOrEmail, password, navigation)}/>
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
      backgroundColor: '#B3DBEE',
    },
  });

function login(usernameOrEmail, password, navigation) {
    fetch('http://192.168.1.245:8080/NeuroDiagnosis-1.0-SNAPSHOT/api/auth/login', {
           method: 'POST',
           headers: {
              Accept: 'application/json',
              'Content-Type': 'application/json'
           },
          body: JSON.stringify({
            userNameOrEmail: usernameOrEmail,
            password: password})
    })
    .then((response) => response.json())
    .then(async (result) => {
      await AsyncStorage.setItem("jwtToken", result["jwtToken"]);
      navigation.navigate('MainAuthPage');
    })
    .catch((error) => {
      if (error.toString().startsWith("SyntaxError")){
        navigation.navigate('DefaultMessagePage', {message: "Invalid Credentials"});
      }
      throw error;
    })
  }
  
  export default LoginPage;