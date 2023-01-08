import React, { useState } from 'react';
import {  StyleSheet, View, ImageBackground, Text } from 'react-native';
import { Table, Row, Rows } from 'react-native-table-component';
import AsyncStorage from '@react-native-async-storage/async-storage';

function HistoryPage({navigation}){
  const [dataTable, setDataTable] = useState([]);

  getHistoryElements(navigation).then((results) => {setDataTable(results);});
  
  return (
    <ImageBackground
      source={require('../resources/10610.jpg')}
      style={{width: '100%', height:'100%'} }>
        <View style={styles.container}>
         <Text style={styles.title}>History</Text>
          <Table borderStyle={{borderWidth: 1, borderColor: '#025977'}} style={styles.tableStyle}>
            <Row data={['Test Result', 'Test Date']} style={styles.HeadStyle} textStyle={styles.TableText}/>
            <Rows data={dataTable} textStyle={styles.TableText}/>
          </Table>
        </View>
    </ImageBackground>
    );
  }


async function getHistoryElements(navigation) {
  const jwtToken = await AsyncStorage.getItem("jwtToken");
  
  return fetch('http://192.168.1.245:8080/NeuroDiagnosis-1.0-SNAPSHOT/api/mmse/history', {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${jwtToken}`,
        'Content-Type': 'application/json', 
        'Accept': 'application/json'
     }})
    .then((response) => response.json())
    .then((result) => {
      let tableParam = [];
      for(let i=0; i < result.length; i++) {
          tableParam.push([result[i]['testResult'], result[i]['testDate']]);
        }
        return tableParam;
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
      padding: 18,
      paddingTop: "20%",
      display: "flex",
      flexDirection: "column",
      alignContent: "center",
    },
    tableStyle: {
        backgroundColor: "rgba(255, 255, 255, 0.80)"
    },

    HeadStyle: { 
      height: 50,
      alignContent: "center",
      backgroundColor: '#C3DFEB'
    },
    TableText: { 
      margin: 10
    },
    
    title: {
        fontFamily: 'sans-serif-light',
        fontWeight: '800',
        textAlign: "center",
        marginBottom:'30%',
        fontSize: 45,
        color: '#025977'
      },
  });

  export default HistoryPage;