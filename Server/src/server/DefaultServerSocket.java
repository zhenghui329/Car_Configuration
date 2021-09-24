package server;
import java.io.*;
import java.net.*;
import a​dapter.Debuggable;

public class DefaultServerSocket implements Debuggable {

	////////// PROPERTIES //////////

	private int port;
	private ServerSocket server;

	////////// CONSTRUCTORS //////////

	public DefaultServerSocket(int port) {
		this.port = port;
		try {
			this.server = new ServerSocket(port);
		}
		catch (IOException e) {
			System.err.println("Could not listen on port " + port + " ... ");
			System.exit(1);
		}
	}

	////////// INSTANCE METHODS //////////

	public void start() {
		Socket clientSocket = null;

		while (true) {
			//Accept client
			try {
				clientSocket = server.accept();
			}
			catch (IOException e) {
				System.err.println("Error establishing client connection ... ");
				System.exit(1);
			}

			//Handle client-server interaction
			if (DEBUG)
				System.out.println(clientSocket.getLocalAddress());
			new DefaultSocketClient(clientSocket).start(); // pass client socket and start its own thread
		}
	}

}
