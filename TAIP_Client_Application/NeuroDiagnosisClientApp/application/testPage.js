import { Button } from "@react-native-material/core";
import React from "react";
import { TextInput, View, Text, StyleSheet, ImageBackground } from "react-native";

function TestPage({navigation}) {
    return (
        <ImageBackground
        source={require('../resources/10610.jpg')}
        style={{width: '100%', height: '100%'}}>
        <View style={styles.container}>
            <Text style={styles.formLabel} >Daily Test</Text>
            <View style={styles.innerContainer}>
                <Text style={styles.questionLabel}>1. Question</Text>
                <TextInput placeholder="Answer..." style={styles.inputStyle}/>
                <Text style={styles.questionLabel}>2. Question</Text>
                <TextInput placeholder="Answer..." style={styles.inputStyle}/>
                <Text style={styles.questionLabel}>3. Question</Text>
                <TextInput placeholder="Answer..." style={styles.inputStyle}/>
                <Text style={styles.questionLabel}>4. Question</Text>
                <TextInput placeholder="Answer..." style={styles.inputStyle}/>
            </View>
            <Button title="Submit"
              style = {{ height: 40, marginTop: '10%' }}
              color = '#025977'
            onPress={() => navigation.navigate('MainAuthPage')}/>
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
    innerContainer:{
        backgroundColor: "rgba(255, 255, 255, 0.80)",
        padding: 15,
        borderRadius: 30
    },
  
    formLabel: {
      fontFamily: 'sans-serif-light',
      fontWeight: '800',
      fontSize: 40,
      color: '#025977',
      backgroundColor: "rgba(255, 255, 255, 0.80)",
      padding: 15,
      borderRadius: 30
    },

    inputStyle: {
      width: 300,
      height: 50,
      paddingHorizontal: 10,
      borderRadius: 50,
      backgroundColor: '#BAE3F7',
    },
    questionLabel: {
        fontFamily: 'sans-serif-light',
        fontWeight: '800',
        fontSize: 20,
        color: '#025977',
        marginTop: 20,
        marginBottom: 5,
    }
  });

  export default TestPage;