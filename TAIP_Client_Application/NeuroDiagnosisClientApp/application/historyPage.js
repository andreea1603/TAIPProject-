import React, { Component } from 'react';
import { StyleSheet, View, ImageBackground, Text } from 'react-native';
import { Table, Row, Rows } from 'react-native-table-component';

export default class HistoryPage extends Component {
    constructor(props) {
    super(props);
    this.state = {
      HeadTable: ['Test Date', 'Test Result'],
      DataTable: [
        ['10-10-2022', '9.97'],
        ['11-10-2022', '9.86'],
        ['12-10-2022', '9.90'],
        ['13-10-2022', '9.74'],
        ['14-10-2022', '9.89']
      ]
    }
  }

  render() {
    const state = this.state;
    return (
    <ImageBackground
        source={require('../resources/10610.jpg')}
        style={{width: '100%', height:'100%'} }>
            <View style={styles.container}>
            <Text style={styles.title}>History</Text>
            <Table borderStyle={{borderWidth: 1, borderColor: '#025977'}} style={styles.tableStyle}>
              <Row data={state.HeadTable} style={styles.HeadStyle} textStyle={styles.TableText}/>
              <Rows data={state.DataTable} textStyle={styles.TableText}/>
            </Table>
        </View>
      </ImageBackground>
    )
  }
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