package client;

public interface SocketClient {
    boolean DEBUG = true;
    int PORT_LOADANDCONFIGURE = 4444;

    void start();
    void establishConnection();
    void handleConnection();
    void closeConnection();
}
