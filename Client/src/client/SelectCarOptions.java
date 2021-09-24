package client;

import a​dapter.*;
import model.*;
import java.util.*;

public class SelectCarOptions {

	////////// PROPERTIES //////////
	private Scanner in = new Scanner(System.in);
	private Choosable a1 = new BuildAuto();

	////////// CONSTRUCTORS //////////

	public SelectCarOptions() {

	}

	////////// INSTANCE METHODS //////////
// clientProtocol.configureAuto(fromServer)
	public void configureAuto(Object obj) {
		a1.printAuto(obj);
		int optSetSize = a1.getOptSetSize(obj);
		for(int i = 0; i<optSetSize; i++){
			String optSetName = a1.getOptSetName(obj,i);
			System.out.println("Please select a " + optSetName + ": ");
			String input = in.nextLine();
			a1.setChoice(obj,optSetName,input);
		}
		System.out.println();
		a1.printChoice(obj);
		a1.displayTotalPrice(obj);
		System.out.println();
		System.out.println("Configuration Completed. \n" +
				"Press any key to return to main menu, 0 to terminate.");
	}

	public boolean isAutomobile(Object obj) {
		boolean isAutomobile = false;

		try {
			Automobile a1 = (Automobile) obj;
			isAutomobile = true;
		}
		catch (ClassCastException e) {
			isAutomobile = false;
		}

		return isAutomobile;
	}

}
