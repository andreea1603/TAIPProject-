import React, { useState, useEffect } from 'react'
import { Image } from 'react-native'
import DocumentScanner from 'react-native-document-scanner-plugin'
import AsyncStorage from '@react-native-async-storage/async-storage';

export default ({navigation}) => {
  const [scannedImage, setScannedImage] = useState(null);
  const [jwtToken, setJwtToken] = useState('');

  const scanDocument = async () => {
    // start the document scanner
    const { scannedImages } = await DocumentScanner.scanDocument()
    const jwtToken_ = await AsyncStorage.getItem("jwtToken");
    setJwtToken(jwtToken_);
  
    // get back an array with scanned image file paths
    if (scannedImages.length > 0) {
      // set the img src, so we can view the first scanned image
      setScannedImage(scannedImages[0])
    }
  }

  useEffect(() => {
    // call scanDocument on load
    scanDocument();
  }, []);

  if( scannedImage != null){
    let formData = new FormData();
    formData.append("image", { uri: scannedImage, name: 'image.jpg', type: 'image/jpg' });
    fetch('http://192.168.1.245:8080/NeuroDiagnosis-1.0-SNAPSHOT/api/mris/submitMriScan',
     {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'multipart/form-data',
        'Authorization': `Bearer ${jwtToken}`,
      },
      method: 'POST',
      body: formData
    })
     .then((response) => response.json())
     .then((result) => {
        console.log(result);
        if (result["Mild_Demented"] > result["Moderate_Demented"] && result["Mild_Demented"] > result["Non_Demented"] && result["Mild_Demented"] > result["Very_Mild_Demented"]) {
              navigation.navigate('DefaultMessagePage', {message: "Result: Mild Demented"});
              } else if (result["Moderate_Demented"] > result["Mild_Demented"]  && result["Moderate_Demented"] > result["Non_Demented"] && result["Moderate_Demented"] > result["Very_Mild_Demented"]) {
              navigation.navigate('DefaultMessagePage', {message: "Result: Moderate Demented"}); 
              } else if (result["Non_Demented"] > result["Mild_Demented"]  && result["Non_Demented"] > result["Moderate_Demented"] && result["Non_Demented"] > result["Very_Mild_Demented"]) {
              navigation.navigate('DefaultMessagePage', {message: "Result: Non-Demented"}); 
              } else {
              navigation.navigate('DefaultMessagePage', {message: "Very Mild Demented"}); 
              }
     })
    .catch((error) => {
     console.log(error);
    });
  }


  return (
    <Image
      resizeMode="contain"
      style={{ width: '100%', height: '100%' }}
      source={{ uri: scannedImage }}
    />
  )
}