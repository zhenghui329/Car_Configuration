
package server;

import a​dapter.*;
import model.Automobile;

import java.util.Properties;

// extends ProxyAutomobile implements Servable

public class BuildCarModelOptions {

	////////// PROPERTIES //////////

	private static final int WAITING = 0;
	private static final int REQUEST_BUILD_AUTO = 1;
	private static final int REQUEST_CONFIGURE_AUTO = 2;
	private static final int REQUEST_CONFIGURE_AUTO_SERVLET = 3;
	private static final int REQUEST_FIND_AUTO = 4;
	private static final int REQUEST_CONFIGURE_AUTO_SETVALUE = 5;

	private int state = WAITING;
	private Servable a1 = new BuildAuto();

	////////// CONSTRUCTORS //////////

	public BuildCarModelOptions() {
	}

	////////// INSTANCE METHODS //////////

	public Object processRequest(Object obj) {
		Object toClient = null;

		if (state == REQUEST_BUILD_AUTO) {
			if(isProperties(obj)){
				a1.buildAndAddAuto(obj,3);
				toClient = "Automobile object successfully added to database\n"
						+ "Press any key to return to main menu, 0 to terminate.";
			}else if(isStringBuffer(obj)){
				a1.buildAndAddAuto(obj,4);
				toClient = "Automobile object successfully added to database\n"
						+ "Press any key to return to main menu, 0 to terminate.";
			}else{
				toClient = "Invalid file path. Please try again.\n"
						+ "Press any key to return to main menu, 0 to terminate.";
			}
		}
		else if (state == REQUEST_CONFIGURE_AUTO) {
			// obj is an integer
			toClient = a1.findModel(Integer.parseInt(obj.toString()));
		}
		else if(state == REQUEST_FIND_AUTO){
			toClient = a1.findModelByName(obj.toString());
		}
		else if(state == REQUEST_CONFIGURE_AUTO_SETVALUE){
			String str = obj.toString();
			String select[] = str.split(",");
			Automobile a = a1.findModelByName(select[0]);
			configureAuto(a,select);
			toClient = a;
		}
		else {
		}
		this.state = WAITING;
		return toClient;
	}

	public String setRequest(int i) {
		String output = null;

		if (i == 1) {
			this.state = REQUEST_BUILD_AUTO;
			output = "Upload a file to create an Automobile";
		}
		else if (i == 2) {
			this.state = REQUEST_CONFIGURE_AUTO;
			output = "Select an Automobile from the following list to configure: \n"
					+ a1.getAllModels();
		}
		else if (i == 3) {
			this.state = REQUEST_CONFIGURE_AUTO_SERVLET;
			output = a1.getModelList();
		}else if(i == 4){
			this.state = REQUEST_FIND_AUTO;
			output = "Request for finding an Automobile";
		}else if(i == 5){
			this.state = REQUEST_CONFIGURE_AUTO_SETVALUE;
			output = "Request for configuring an Automobile";
		}
		else {
			output = "Invalid request. \n" + "Press any key to return to main menu, 0 to terminate.";
		}
		return output;
	}

	public boolean isProperties(Object obj) {
		boolean isProperties = false;

		try {
			Properties a1 = (Properties) obj;
			isProperties = true;
		}
		catch (ClassCastException e) {
			isProperties = false;
		}
		return isProperties;
	}

	public boolean isStringBuffer(Object obj) {
		boolean isStringBuffer = false;

		try {
			StringBuffer a1 = (StringBuffer) obj;
			isStringBuffer = true;
		}
		catch (ClassCastException e) {
			isStringBuffer = false;
		}
		return isStringBuffer;
	}

	public void configureAuto(Automobile auto,String select[]) {
		Choosable a2 = new BuildAuto();
		int optSetSize = a2.getOptSetSize(auto);
		for(int i = 0; i<optSetSize; i++){
			String optSetName = a2.getOptSetName(auto,i);
			a2.setChoice(auto,optSetName,select[i+1]);
		}
	}

}
