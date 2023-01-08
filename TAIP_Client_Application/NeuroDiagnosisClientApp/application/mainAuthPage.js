import React from 'react';
import { View, Text, StyleSheet, ImageBackground } from 'react-native';
import { Button } from "@react-native-material/core";
import AsyncStorage from '@react-native-async-storage/async-storage';


function MainAuthPage({navigation}) {
    return (
      <View style={styles.container}>
      <ImageBackground
      source={require('../resources/10610.jpg')}
      style={{width: '100%', height:'90%', marginTop:'100%'} }>
        <View style={styles.innerContainer}>
        <Text style={styles.title}>NeuroDiagnosis</Text>
        <Button title="See history" 
            style = {{ height: 40}}
            color = '#025977'
            onPress={() => navigation.navigate('HistoryPage')}/>
        <Text style={styles.label}>- Or -</Text>
        <Button title="Take the test of the day" 
            style = {{ height: 40}}
            color = '#025977'
            onPress={() => navigation.navigate('TestPage')}/>
        <Text style={styles.label}>- Or -</Text>
        <Button title="Scan a MRI"
            style = {{ height: 40 }}
            color = '#025977'
            onPress={() => navigation.navigate('ScanPage')}/>
        <Text style={styles.label}>- Or -</Text>
        <Button title="Prediction based on tests"
            style = {{ height: 40 }}
            color = '#025977'
            onPress={() => getTestPrediction(navigation)}/>
        </View>
      </ImageBackground>
      </View>
    );
}

async function getTestPrediction(navigation) {
  const jwtToken = await AsyncStorage.getItem("jwtToken");
  
  return fetch('http://192.168.1.245:8080/NeuroDiagnosis-1.0-SNAPSHOT/api/data/getML', {
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${jwtToken}`,
        'Content-Type': 'application/json', 
        'Accept': 'application/json'
     }})
    .then((response) => response.json())
    .then((result) => {
      console.log(result);
      navigation.navigate('DefaultMessagePage', {message: result});
    })
    .catch((error) => {
        if (error.toString().startsWith("SyntaxError")){
          navigation.navigate('DefaultMessagePage', {message: error.toString()});
        }
        throw error;
    });
  }

const styles = StyleSheet.create({
    container: {
      flex: 1,
      alignItems: 'center',
      justifyContent: 'center',
      backgroundColor: '#fff',
    },

    innerContainer: {
      marginTop: '10%',
      alignItems: 'center',
      justifyContent: 'center',
    },

    title: {
      fontFamily: 'sans-serif-light',
      fontWeight: '800',
      marginBottom:'10%',
      marginTop: '-45%',
      fontSize: 45,
      color: '#C3DFEB'
    },
  
    label: {
      fontFamily: 'sans-serif-light',
      fontWeight: '800',
      fontSize: 15,
      backgroundColor: '#025977',
      marginBottom: '10%',
      padding: '2.5%',
      borderRadius: 30,
      marginTop: '10%',
      color: '#fff',
    },
  });

export default MainAuthPage;