import { NativeModules } from "react-native";

const { LocalHttpServer } = NativeModules;

export default {
  startServer: () => LocalHttpServer.startServer(),
  stopServer: () => LocalHttpServer.stopServer(),
};
