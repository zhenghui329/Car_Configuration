package server;

import model.Automobile;

public interface Servable {
	int PORT_LOADANDCONFIGURE = 4444;

	void serve(int port); // implement in proxyAuto

	void buildAndAddAuto(Object obj, int fileType);

	String getAllModels();

	String getModelList();

	Automobile findModel(int select);

 	Automobile findModelByName(String modelName);

}
