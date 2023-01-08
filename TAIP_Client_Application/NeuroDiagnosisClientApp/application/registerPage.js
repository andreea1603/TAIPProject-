import { Button } from "@react-native-material/core";
import React, { useState } from "react";
import { TextInput, View, Text, StyleSheet, ImageBackground, ScrollView } from "react-native";
import {Picker} from '@react-native-picker/picker';

function RegisterPage({navigation}) {
  const [marriedStatus, setMarriedStatus] = useState('');
  const [emailAddress, setEmailAddress] = useState('');  
  const [phoneNumber, setPhoneNumber] = useState(''); 
  const [handedness, setHandedness] = useState();
  const [firstName, setFirstName] = useState('');
  const [ethnicity, setEthnicity] = useState('');
  const [province, setProvince] = useState('');
  const [lastName, setLastName] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [country, setCountry] = useState('');
  const [gender, setGender] = useState();
  const [city, setCity] = useState('');
  const [age, setAge] = useState('');

  return (
        <ImageBackground
        source={require('../resources/logo.jpg')}
        style={{width: '100%', height: '100%'}}>
        <View style={styles.container}>
        <ScrollView>
            <Text style={styles.formLabel} >Register</Text>
            <View>
                <TextInput placeholder="First Name" 
                style={styles.inputStyle}
                onChangeText={input => setFirstName(input)}/>
                <TextInput placeholder="Last Name" 
                style={styles.inputStyle}
                onChangeText={input => setLastName(input)}/>
                <TextInput placeholder="Country" 
                style={styles.inputStyle}
                onChangeText={input => setCountry(input)}/>
                <TextInput placeholder="Province" 
                style={styles.inputStyle}
                onChangeText={input => setProvince(input)}/>
                <TextInput placeholder="City" 
                style={styles.inputStyle}
                onChangeText={input => setCity(input)}/>
                <TextInput placeholder="Age" 
                style={styles.inputStyle}
                onChangeText={input => setAge(input)}/>
                <Picker 
                selectedValue={ethnicity}
                style={styles.inputStyle}
                    onValueChange={(itemValue, itemIndex) => setEthnicity(itemValue)}>
                        <Picker.Item value="Select an answer" label="Select ethnicity" />
                        <Picker.Item value="White" label="White" />
                        <Picker.Item value="Black" label="Black" />
                        <Picker.Item value="Asian" label="Asian" />
                        <Picker.Item value="More than one" label="More than one" />
                        <Picker.Item value="Other" label="Other" />
                  </Picker>
                  <Picker 
                  selectedValue={gender}
                  style={styles.inputStyle}
                    onValueChange={(itemValue, itemIndex) => setGender(itemValue)}>
                        <Picker.Item value="Select an answer" label="Select gender" />
                        <Picker.Item value={true} label="Female" />
                        <Picker.Item value={false} label="Male" />
                </Picker>
                  <Picker 
                selectedValue={handedness}
                style={styles.inputStyle}
                    onValueChange={(itemValue, itemIndex) => setHandedness(itemValue)}>
                        <Picker.Item value="Select an answer" label="Select handedness" />
                        <Picker.Item value={true} label="Right Hand" />
                        <Picker.Item value={false} label="Left Hand" />
                  </Picker> 
                  <Picker 
                  selectedValue={marriedStatus}
                  style={styles.inputStyle}
                    onValueChange={(itemValue) => setMarriedStatus(itemValue)}>
                        <Picker.Item value="Select marital status" label="Select marital status" />
                        <Picker.Item value="Married" label="Married" />
                        <Picker.Item value="Divorced" label="Divorced" />
                        <Picker.Item value="Widowed" label="Widowed" />
                        <Picker.Item value="Never married" label="Never married" />
                  </Picker>   
                <TextInput placeholder="Email Address" 
                style={styles.inputStyle}
                onChangeText={input => setEmailAddress(input)}/>
                <TextInput placeholder="Phone number" 
                style={styles.inputStyle}
                onChangeText={input => setPhoneNumber(input)}/>
                <TextInput placeholder="Username" 
                style={styles.inputStyle}
                onChangeText={input => setUsername(input)}/>
                <TextInput secureTextEntry={true} 
                placeholder="Password" 
                style={styles.inputStyle}
                onChangeText={input => setPassword(input)}/>
            </View>
            <Button title="Create Account"
              style = {{ height: 40, marginTop: '10%' }}
              color = '#025977'
            onPress={() => register(username, emailAddress, firstName, lastName, password, phoneNumber, marriedStatus, city, age, ethnicity, handedness, province, country, gender, navigation)}/>
        </ScrollView>
        </View>
        </ImageBackground>
    );
}

function register(username, emailAddress, firstName, lastName, password, phoneNumber, marriedStatus, city, age, ethnicity,  handedness, province, country, gender, navigation) {
  console.log(handedness);
  fetch('http://192.168.1.245:8080/NeuroDiagnosis-1.0-SNAPSHOT/api/auth/register', {
         method: 'POST',
         headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
         },
        body: JSON.stringify({
          marriedStatus: marriedStatus,
          emailAddress: emailAddress,
          phoneNumber: phoneNumber,
          handedness: handedness,
          firstName: firstName,
          ethnicity: ethnicity,
          username: username,
          lastName: lastName,
          password: password,
          province: province,
          country: country,
          gender: gender,
          age: age
       })
  })
  .then((response) => response.json())
  .then((result) => {
    navigation.navigate('LoginPage')
  })
  .catch((error) => {
      navigation.navigate('DefaultMessagePage', {message: error.toString()});
  })
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      alignItems: 'center',
      justifyContent: 'center',
      height: 100,
    },
  
    formLabel: {
      fontFamily: 'sans-serif-light',
      fontWeight: '800',
      fontSize: 40,
      marginLeft: 80,
      marginTop: 30,
      marginBottom: 30,
      color: '#025977',
    },

    inputStyle: {
      marginTop: 10,
      width: 300,
      height: 50,
      paddingHorizontal: 10,
      borderRadius: 50,
      backgroundColor: '#BAE3F7',
    }
  });

  export default RegisterPage;