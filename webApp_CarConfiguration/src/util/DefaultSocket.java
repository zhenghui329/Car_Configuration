package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DefaultSocket extends Thread implements ConnectServer {
//////////PROPERTIES //////////

	private ObjectOutputStream out; // stream out: send data to server
	private ObjectInputStream in; // stream in: get data from Server
	private Socket sock;
	private String strHost;
	private int iPort;
	private int request;
	private Object fromServer;
	private Object toServer;

////////// CONSTRUCTORS //////////

	public DefaultSocket(String strHost, int iPort, int request, Object toServer) {
		this.strHost = strHost;
		this.iPort = iPort;
		this.request = request;
		this.toServer = toServer;
	}

////////// INSTANCE METHODS //////////

	public void establishConnection() {
		try {
			if (DEBUG)
				System.out.println("Connecting to host ... ");
			this.sock = new Socket(strHost, iPort);

			if (DEBUG)
				System.out.println("Connected to host, creating object streams ... ");
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
		} catch (IOException e) {
			System.err.println("Error obtaining I/O for connection to host ... ");
			System.exit(1);
		}
	}

	public void handleConnection() {
		fromServer = null;

		try {
			if (DEBUG)
				System.out.println("Communicating with host ... ");

			if ((fromServer = in.readObject()) != null) {
				if (DEBUG)
					System.out.println("Received server response ... ");
				System.out.println(fromServer.toString());

				System.out.println("Response to server: ");
				if (DEBUG)
					System.out.println("Sending " + request + " to server ... ");
				sendOutput(request);
				fromServer = in.readObject();
				if (toServer != null) {
					System.out.println("Sending " + toServer + " to server ... ");
					sendOutput(toServer);
					fromServer = in.readObject();
					if (fromServer != null)
						System.out.println(fromServer.toString());
				}
			}

			if (DEBUG)
				System.out.println("Closing client input stream ... ");
			sendOutput(0);
			in.close();

		} catch (ClassNotFoundException e) {
			System.err.println("Error in downloaded object, object may be corrupted ... ");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error in I/O stream ... ");
			e.printStackTrace();
			System.exit(1);
		}

	}

	public void sendOutput(Object obj) {
		try {
			out.writeObject(obj);
		} catch (IOException e) {
			System.err.println("Error in I/O stream while sending object to host ... ");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public Object getFromServer() {
		return fromServer;
	}

	public void closeConnection() {
		try {
			if (DEBUG)
				System.out.println("Closing client output stream ... ");
			out.close();

			if (DEBUG)
				System.out.println("Closing client socket ... ");
			sock.close();
		} catch (IOException e) {
			System.err.println("Error ending client connection ... ");
			System.exit(1);
		}

	}

	// Thread method
	@Override
	public void run() {
		establishConnection();
		handleConnection();
		closeConnection();
	}
}
