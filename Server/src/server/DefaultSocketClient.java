
package server;

import java.net.*;
import java.io.*;
import a​dapter.Debuggable;

public class DefaultSocketClient extends Thread implements SocketProxyClient, Debuggable {

	////////// PROPERTIES //////////

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket clientSocket;
	private BuildCarModelOptions protocol;

	////////// CONSTRUCTORS //////////

	public DefaultSocketClient(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}


	////////// INSTANCE METHODS //////////
	@Override
	public void handleConnection() {
		if (DEBUG)
			System.out.println("Creating server object streams ... ");
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream()); // out: send obj to client
			in = new ObjectInputStream(clientSocket.getInputStream());	  // in: get obj from client
		}
		catch (IOException e) {
			System.err.println("Error creating server object streams ... ");
			System.exit(1);
		}

		protocol = new BuildCarModelOptions();
		String menu = "\nEnter 1 to upload a new Automobile\n"
				+ "Enter 2 to configure an Automobile\n"
				+ "Enter 0 to terminate connection\n";

		try {
			int request;
			String input = "";
			Object fromClient = null;
			do {
				// check if the client has been shut down
				if(fromClient!=null){
					input = fromClient.toString();
				}
				if(input.equals("0"))
					break;

				if (DEBUG)
					System.out.println("Sending client interaction choices ... ");
				sendOutput(menu);

				if (DEBUG)
					System.out.println("Reading client request ... ");

				// get a request number from client
				try {
					request = Integer.parseInt(in.readObject().toString());
				}catch(NumberFormatException e){
					request = -1;
				}
				if (DEBUG)
					System.out.println(request);
				if (request == 0)
					break;

				if (DEBUG)
					System.out.println("Sending client request follow-up ... ");

				sendOutput(protocol.setRequest(request));

				if (request >= 1 && request <= 5) {
					if (DEBUG)
						System.out.println("Entering handleInput session ... ");
					if (!handleInput(request))
						break;  // If client has been shut down in handleInput(), return false and break
				}
			} while ((fromClient = in.readObject()) != null);

			if (DEBUG)
				System.out.println("Closing server input stream for client " + clientSocket.getInetAddress() + " ... ");
			in.close();
		}
		catch (IOException e) {
			System.err.println("Error handling client connection ... ");
			e.printStackTrace();
			System.exit(1);
		}
		catch (ClassNotFoundException e) {
			System.err.println("Error in uploaded object, object may be corrupted ... ");
			System.exit(1);
		}
	}

	public void sendOutput(Object obj) {
		try {
			out.writeObject(obj);
		}
		catch (IOException e) {
			System.err.println("Error returning output to client ... ");
			System.exit(1);
		}
	}

	public boolean handleInput(int request) {
		Object fromClient = null;
		Object toClient = null;

		try {
			switch (request) {
				case 1: //Client request to build Automobile
					if (DEBUG)
						System.out.println("Waiting for client to upload file ... ");
					fromClient = in.readObject();
					if(!fromClient.toString().equals("0")) {
						if (DEBUG) {
							System.out.println(fromClient);
							System.out.println("Adding new Automobile to database ... ");
						}
						toClient = protocol.processRequest(fromClient);
						sendOutput(toClient);
						return true;
					}
					break;

				case 2: //Client request to configure Automobile
					if (DEBUG)
						System.out.println("Waiting for client to select Automobile ... ");
					int input = Integer.parseInt(in.readObject().toString());
					fromClient = input;
					if(input != 0) {
						if (DEBUG)
							System.out.println("Sending Automobile object to client ... ");
						toClient = protocol.processRequest(fromClient);
						if(toClient == null){
							toClient = "Invalid input. \n"
									+ "Press any key to return to main menu, 0 to terminate.";
						}
						sendOutput(toClient);
						return true;
					}
					break;
				case 3:
					if (DEBUG)
						System.out.println("Waiting for client to select Automobile by web... ");
					break;
				case 4:
					if (DEBUG)
						System.out.println("Getting the model name from the web ... ");
					fromClient = in.readObject();
					if(fromClient != null){
						fromClient = fromClient.toString();
						toClient = protocol.processRequest(fromClient);
					}
					sendOutput(toClient);
					return true;
				case 5:
					if (DEBUG)
						System.out.println("Configuring the model ... ");
					fromClient = in.readObject().toString();
					toClient = protocol.processRequest(fromClient);
					sendOutput(toClient);
					return true;

				default: //Invalid requests
			}
		}
		catch (ClassNotFoundException e) {
			System.err.println("Error in uploaded object, file may be corrupted ... ");
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("Error in retrieving object from client ... ");
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}

	@Override
	public void closeConnection() {
		//Close client output stream
		if (DEBUG)
			System.out.println("Closing server output stream for client " + clientSocket.getInetAddress() + " ... ");
		try {
			out.close();
		}
		catch (IOException e) {
			System.err.println("Error closing server output stream for client " + clientSocket.getInetAddress() + " ... ");
		}

		//Close client socket
		if (DEBUG)
			System.out.println("Closing client socket " + clientSocket.getInetAddress() + " ... ");
		try {
			clientSocket.close();
		}
		catch (IOException e) {
			System.err.println("Error closing client socket " + clientSocket.getInetAddress() + " ... ");
		}
	}

	@Override
	public void run() {
		handleConnection();
		closeConnection();
	}

}
