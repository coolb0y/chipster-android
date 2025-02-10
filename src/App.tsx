import React, { useEffect } from "react";
import { View, Text, Button } from "react-native";
import LocalHttpServer from "./native-modules/LocalHttpServer";  // Adjust the path if needed

const App = () => {
  useEffect(() => {
    LocalHttpServer.startServer()
      .then((message) => console.log(message))
      .catch((error) => console.error(error));

    return () => {
      LocalHttpServer.stopServer()
        .then((message) => console.log(message))
        .catch((error) => console.error(error));
    };
  }, []);

  return (
    <View style={{ padding: 20 }}>
      <Text>Local HTTP Server Running...</Text>
      <Button title="Start Server" onPress={() => LocalHttpServer.startServer()} />
      <Button title="Stop Server" onPress={() => LocalHttpServer.stopServer()} />
    </View>
  );
};

export default App;
