### NeuroDiagnosis Client App
React Native setup: `https://reactnative.dev/docs/environment-setup`

#### How to run the app when the setup (with a phisical device) is done from commandLine:
* make sure you're in NeuroDiagnosisClientApp directory
* run `npx react-native run-android`

### For rendering the app while modifying it:
* run `adb devices` -> Save your device name
* run `adb -s <device-name> reverse tcp:1234 tcp:1234`
* run `npx react-native run-android --port=1234`

-----------------------------------------------------------------------
Warning: The default port for ReactNative is 8081, you might want to try with this port first.
-----------------------------------------------------------------------