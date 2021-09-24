package driver;

import a​dapter.*;
import java.util.Scanner;

public class Driver2 {

    public static void main(String[] args) {
        StringBuilder menu = new StringBuilder();
        menu.append("Enter a number to choose one test case: ");
        menu.append("\n1 Test Creatable and Updatable interface with no exception");
        menu.append("\n2 Test AutoException class - internal exception handling");
        menu.append("\n3 Test Fixable interface - print error message and fix it externally");
        System.out.println(menu);

        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        switch (x){
            case 1:
                // Test Creatable interface
                System.out.println("Create an instance of Automobile via Creatable interface:");
                Creatable a1 = new BuildAuto();
                a1.buildAuto("src/FW_ZTW.txt");
                a1.printAuto("Focus Wagon ZTW");
                System.out.println();

                // Test Updatable interface
                System.out.println("Update the instance of Automobile via Updatable interface:");
                Updatable a2 = new BuildAuto();
                System.out.println("Update OptionSetName 'color' to 'car color' ");
                a2.updateOptionSetName("Focus Wagon ZTW", "color", "car color");
                System.out.println("Update option price of 'Fort Knox Gold Clearcoat Metallic'  to 350 ");
                a2.updateOptionPrice("Focus Wagon ZTW", "car color", "Fort Knox Gold Clearcoat Metallic", 350);
                a1.printAuto("Focus Wagon ZTW");
                break;

            case 2:
                // Test AutoException class
                Creatable a3 = new BuildAuto();
                a3.buildAuto("src/FW_ZTW_ex.txt");
                a3.printAuto("Focus Wagon ZTW");
                break;

            case 3:
                // Test Fixable interface - print error message
                Creatable a4 = new BuildAuto();
                a4.buildAuto("src/FW_ZTW.txt");
                Updatable a5 = new BuildAuto();
                System.out.println("\nWe want to change 'colo' set to 'car color': ");
                a5.updateOptionSetName("Focus Wagon ZTW", "colo", "car color");
                System.out.println("\n\n\n\n");

                System.out.println("Please enter your error no: ");
                int errorNo = sc.nextInt();
                Fixable a6 = new BuildAuto();
                StringBuilder x2 = new StringBuilder();
                a6.fix(errorNo,x2);
                String str[] = x2.toString().split(",");
                String optionSetName = str[0];
                String newName = str[1];
                a5.updateOptionSetName("Focus Wagon ZTW",optionSetName,newName);
                System.out.println("\nNow the automobile has been updated: ");
                a4.printAuto("Focus Wagon ZTW");
        }
    }

}
