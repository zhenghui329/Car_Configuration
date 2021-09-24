package server;

public interface SocketProxyClient {
    void handleConnection();
    void closeConnection();
}
