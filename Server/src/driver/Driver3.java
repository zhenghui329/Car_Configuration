package driver;

import a​dapter.*;

public class Driver3 {
    public static void main(String[] args) {
        String modelName = "Focus Wagon ZTW";

        System.out.println(" ============= Test for multiple models ==========");
        System.out.println("1) Create 3 Automobile objects via Creatable interface:");
        Creatable a1 = new BuildAuto();
        a1.buildAndAddAuto("src/FW_ZTW.txt",1);
        a1.buildAndAddAuto("src/FW_ZTW_B.txt",1);
        a1.buildAndAddAuto("src/FW_ZTW_C.txt",1);
        a1.display();
        System.out.println();

        System.out.println("2) Print one Automobile object information:");
        a1.printAuto(modelName);
        System.out.println();

        System.out.println("============= Test for making choices ==========");
        System.out.println("1) Choose a model: Focus Wagon ZTW");
        Choosable a2 = new BuildAuto();
        System.out.println("- Choose a color -");
        a2.setChoice(modelName,"color","French Blue Clearcoat Metallic");
        System.out.println("- Choose Brakes -");
        a2.setChoice(modelName,"Brakes/Traction Control","ABS with Advance Trac");
        System.out.println("- Choose Transmission -");
        a2.setChoice(modelName,"Transmission","manual");
        System.out.println();

        System.out.println("2) Get choice:");
        a2.displayChoiceName(modelName,"Transmission");
        System.out.println();

        System.out.println("3) Get choice price:");
        a2.displayChoicePrice(modelName,"Transmission");
        System.out.println();

        System.out.println("4) Display all the choices:");
        a2.printChoice(modelName);
        System.out.println();

        System.out.println("5) Display the total price:");
        a2.displayTotalPrice(modelName);
        System.out.println();

        System.out.println("6) Print the Auto object after selection:");
        a1.printAuto(modelName);

        System.out.println("7) Clear the choice list:");
        a2.clearChoice(modelName);
        a2.printChoice(modelName);
    }
}