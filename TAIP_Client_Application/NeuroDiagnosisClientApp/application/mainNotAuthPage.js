import React from 'react';
import { View, Text, StyleSheet, ImageBackground } from 'react-native';
import { Button } from "@react-native-material/core";

function MainNotAuthPage({navigation}) {
    return (
      <View style={styles.container}>
      <ImageBackground
      source={require('../resources/10610.jpg')}
      style={{width: '100%', height:'90%', marginTop:'100%'} }>
        <View style={styles.innerContainer}>
        <Text style={styles.title}>NeuroDiagnosis</Text>
        <Button title="Take the test of the day" 
            style = {{ height: 40}}
            color = '#025977'
            onPress={() => navigation.navigate('TestPage')}/>
        <Text style={styles.label}>- Or -</Text>
        <Button title="Scan a MRI"
            style = {{ height: 40 }}
            color = '#025977'
            onPress={() => navigation.navigate('ScanPage')}/>
        </View>
      </ImageBackground>
      </View>
    );
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
      marginBottom:'30%',
      marginTop: '-30%',
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

export default MainNotAuthPage;