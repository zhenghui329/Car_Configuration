package driver;

import client.*;

import static client.SocketClient.PORT_LOADANDCONFIGURE;

public class ClientDriver {
    public static void main(String[] args) {
        SocketClient client = new DefaultSocketClient("192.168.0.45",PORT_LOADANDCONFIGURE);
        // IP for my home : "192.168.0.48"
        client.start();
    }
}
