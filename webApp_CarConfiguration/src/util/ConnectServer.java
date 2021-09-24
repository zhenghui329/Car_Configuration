package util;

public interface ConnectServer {
	boolean DEBUG = true;
	int PORT_CONFIGURE = 4444;
	String SERVER_IP = "192.168.0.45";

	void establishConnection();

	void handleConnection();

	void closeConnection();

	Object getFromServer();
}
