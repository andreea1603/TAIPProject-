import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LandingPage  from './application/landingPage';
import RegisterPage from './application/registerPage';
import LoginPage from './application/loginPage';
import DefaultMessagePage from './application/defaultMessagePage';
import MainAuthPage from './application/mainAuthPage';
import ScanPage from './application/scanPage';
import HistoryPage from './application/historyPage';
import TestPage from './application/testPage';

const Stack = createNativeStackNavigator();
function App () {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="LandingPage" screenOptions={{ headerShown: false}}>
        <Stack.Screen name='LandingPage' component={LandingPage}/>
        <Stack.Screen name='RegisterPage' component={RegisterPage}/>
        <Stack.Screen name='LoginPage' component={LoginPage}/>
        <Stack.Screen name='MainAuthPage' component={MainAuthPage}/>
        <Stack.Screen name='DefaultMessagePage' component={DefaultMessagePage}/>
        <Stack.Screen name='ScanPage' component={ScanPage}/>
        <Stack.Screen name='HistoryPage' component={HistoryPage}/>
        <Stack.Screen name='TestPage' component={TestPage}/>
      </Stack.Navigator>
    </NavigationContainer>
  );
}


export default App;

