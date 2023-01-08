import { Button } from "@react-native-material/core";
import React, { useState } from "react";
import { TextInput, View, Text, StyleSheet, ImageBackground, ScrollView } from "react-native";
import {Picker} from '@react-native-picker/picker';
import AsyncStorage from '@react-native-async-storage/async-storage';
function TestPage({navigation}) {
  const [inputFields, setInputFields] = useState([]);
  const [score, setScore] = useState(0);
  const answers = [];
  recieveQuestions(navigation).then((inputFields) => {
    setInputFields(inputFields);
  });


    for(let i=0; i <inputFields.length; i++) {
      answers.push( 
     <View key={i}>
        <Text style={styles.questionLabel}>{inputFields[i]['question']}</Text>
        {inputFields[i]['needsTextInputFromUser'] ? 
        <TextInput placeholder="Answer..." 
        onChangeText={(newText) => {
          setScore(addToScore(inputFields[i]['correctAnswer'], score, newText));
          console.log(score);
        }}
        style={styles.inputStyle}/> 
        : 
        <Picker 
        style={{ height: 50, width: 300 }}
        selectedValue="Select an answer"
        onValueChange={(itemValue, itemIndex) => setScore(addToScore(inputFields[i]['correctAnswer'], score, itemValue))}>
            <Picker.Item value="Select an answer" label="Select an answer" />
            <Picker.Item value={inputFields[i]['option1']} label={inputFields[i]['option1']} />
            <Picker.Item value={inputFields[i]['option2']} label={inputFields[i]['option2']} />
            <Picker.Item value={inputFields[i]['option3']} label={inputFields[i]['option3']} />
      </Picker> }
      </View>);
    } 

    return (
          <ImageBackground
          source={require('../resources/10610.jpg')}
          style={{width: '100%', height: '100%'}}>
            <View style={styles.container}>
            <ScrollView>
              <Text style={styles.formLabel} >Daily Test</Text>
              <View style={styles.innerContainer}>      
              {answers}
            </View>
            <Button title="Submit"
                style = {{ height: 40, marginTop: '10%' }}
                color = '#025977'
              onPress={() => submitTest(navigation, score)}/>
          </ScrollView>
          </View>
          </ImageBackground>
      );
}

async function recieveQuestions(navigation) {
  const jwtToken = await AsyncStorage.getItem("jwtToken");

  return fetch('http://192.168.1.245:8080/NeuroDiagnosis-1.0-SNAPSHOT/api/test/generate', {
         method: 'GET',
         headers: {
            'Authorization': `Bearer ${jwtToken}`,
            'Content-Type': 'multipart/form-data',
            'Accept': 'application/json'
         }})
  .then((response) => response.json())
  .then((result) => {
    console.log(result);
    return result.questions;    
  })
  .catch((error) => {
    if (error.toString().startsWith("SyntaxError")){
      navigation.navigate('DefaultMessagePage', {message: error.toString()});
    }
    throw error;
  });
}

function addToScore(correctAnswer, currentScore, value) {
  console.log(value);
  if(correctAnswer == value) {
    currentScore += 1;
  }

  return currentScore;
}

async function submitTest(navigation, testResult) {
  const jwtToken = await AsyncStorage.getItem("jwtToken");
  
  fetch('http://192.168.1.245:8080/NeuroDiagnosis-1.0-SNAPSHOT/api/mmse/submitTest', {
         method: 'POST',
         headers: {
            'Authorization': `Bearer ${jwtToken}`,
            Accept: 'application/json',
            'Content-Type': 'application/json'
         },
        body: JSON.stringify({
          testResult: testResult
        })
  })
  .then((response) => response.json())
  .then((result) => {
    navigation.navigate('MainAuthPage');
  })
  .catch((error) => {
    if (error.toString().startsWith("SyntaxError")){
      navigation.navigate('DefaultMessagePage', {message: error.toString()});
    }
    throw error;
  })
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