package a​dapter;

import java.util.Properties;

import exception.AutoException;
import model.Automobile;
import model.myLHM;
import scale.EditOption;
import util.FileIO;

public abstract class ProxyAutomobile {
	private static myLHM<String, Automobile> autos = new myLHM<>();

	public myLHM<String, Automobile> getLHM() {
		return autos;
	}

	public void buildAndAddAuto(Object obj, int fileType) {
		FileIO readFile = new FileIO();
		Automobile a = null;
		switch (fileType) {
		case 1: // txt fileName
			a = readFile.buildAutoObject(obj.toString());
			break;
		case 2: // properties fileName
			a = readFile.buildAutoByProp(obj.toString());
			break;
		case 3: // properties obj
			Properties props = (Properties) obj;
			a = readFile.buildAutoByProp(props);
			break;
		case 4: // StringBuffer obj
			StringBuffer sbuff = (StringBuffer) obj;
			a = readFile.buildAutoBySbuff(sbuff);
			break;
		default:
		}
		if (a != null) {
			String key = a.getHashKey();
			autos.addAuto(key, a);
		}
	}

	public void printAuto(String modelName) {
		Automobile a = autos.findByModelName(modelName);
		if (a != null) {
			a.print();
		}
	}

	public void display() {
		autos.display();
	}

	public void updateOptionSetName(String modelName, String optionSetName, String newName) {
		if (newName.equals("")) {
			System.out.println("Error no.401 : Update failed -- missing optionSet name");
			return;
		}
		Automobile a = autos.findByModelName(modelName);
		if (a != null) {
			boolean success = a.updateOptionSetName(optionSetName, newName);
			if (!success) {
				System.out.println("Error no.402 : Update failed -- optionSet name not found");
			}
		}
	}

	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice) {
		Automobile a = autos.findByModelName(modelName);
		if (a != null) {
			a.updateOptionPrice(optionSetName, optionName, newPrice);
		}
	}

	public void fix(int n, StringBuilder x) {
		AutoException error = new AutoException(n);
		error.fix(x);
	}

	public void setChoice(String modelName, String optionSetName, String optionName) {
		Automobile a = autos.findByModelName(modelName);
		if (a != null) {
			a.setOptionChoice(optionSetName, optionName);
		}
	}

	public void displayChoiceName(String modelName, String optionSetName) {
		Automobile a = autos.findByModelName(modelName);
		StringBuilder choice = new StringBuilder();
		if (a != null) {
			choice.append("Your choice of ").append(optionSetName).append(" is: ");
			String str = a.getOptionChoice(optionSetName);
			if (str != null)
				choice.append(str);
			else
				choice.append("choice not set");
		} else {
			choice.append("wrong model name");
		}
		System.out.println(choice.toString());
	}

	public void displayChoicePrice(String modelName, String optionSetName) {
		Automobile a = autos.findByModelName(modelName);
		StringBuilder price = new StringBuilder();
		if (a != null) {
			price.append("The price of ").append(optionSetName).append(" for your choice is: ");
			String str = a.getOptionChoice(optionSetName);
			if (str != null)
				price.append(a.getOptionChoicePrice(optionSetName));
			else
				price.append("choice not set");
		} else {
			price.append("wrong model name");
		}
		System.out.println(price.toString());
	}

	public void printChoice(String modelName) {
		Automobile a = autos.findByModelName(modelName);
		if (a != null) {
			a.printChoice();
		}
	}

	public void displayTotalPrice(String modelName) {
		Automobile a = autos.findByModelName(modelName);
		StringBuilder total = new StringBuilder();
		if (a != null) {
			total.append("The Total price for your ").append(modelName).append(" is: $");
			total.append(a.getTotalPrice());
		} else {
			total.append("wrong model name");
		}
		System.out.println(total.toString());
	}

	public void clearChoice(String modelName) {
		Automobile a = autos.findByModelName(modelName);
		if (a != null) {
			a.clearChoice();
		}
	}

	public int getOptSetSize(Object a) {
		Automobile auto = (Automobile) a;
		if (auto != null) {
			return auto.getOptSetSize();
		}
		return 0;
	}

	public String getOptSetName(Object a, int index) {
		Automobile auto = (Automobile) a;
		if (auto != null) {
			return auto.getOptionSetName(index);
		}
		return null;
	}

	public void setChoice(Object a, String optionSetName, String optionName) {
		Automobile auto = (Automobile) a;
		if (auto != null) {
			auto.setOptionChoice(optionSetName, optionName);
		}
	}

	public void startEditThread(int x, String strAry[], String name) {
		EditOption edit = new EditOption(x, strAry, name);
		edit.start();
	}

}