import React from 'react';
import { View, Text, ImageBackground, StyleSheet } from 'react-native';
import { Button } from "@react-native-material/core";

function LandingPage ({navigation}) {
  return (
      <ImageBackground
        source={require('../resources/logo.jpg')}
        style={{width: '100%', height: '100%'}}>
         <Button title="Use the app without an account >"
            style = {{ height: 35, marginTop:"3%", marginLeft:'16%'}}
            color = '#026800'
            onPress={() => navigation.navigate('MainNotAuthPage')}/>
        
        <View style={{flex: 1,
                      justifyContent: 'flex-end',
                      marginLeft: 25,
                      marginBottom: 75}}>
        <Text
                   style={{ fontFamily: 'sans-serif-light',
                            fontWeight: '200',
                            fontSize: 45,
                            color: '#C3DFEB'
                            }}>NeuroDiagnosis</Text>

        </View>
        
        <View style={[styles, { flexDirection: "row"}]}>
            <Button title="Register"
              style = {{ height: 40, width: '45%', marginRight:'5%', marginLeft:'2.5%'}}
              color = '#025977'
            onPress={() => navigation.navigate('RegisterPage')}/>
            <Button title="Login"
              style = {{ height: 40, width: '45%' }}
              color = '#025977'
            onPress={() => navigation.navigate('LoginPage')}/>
        </View>
      </ImageBackground>
);
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
  },
});

export default LandingPage;