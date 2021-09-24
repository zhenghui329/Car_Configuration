package driver;
import a​dapter.*;
import scale.Scalable;

import java.util.Scanner;

/*
* In this lab, I used synchronized block to implement synchronization
* and put them in the helper class of EditOption.
* I pass the minimal object necessary to the synchronized block and only
* put critical codes that modify the object in it.
* For example, when the method is finding an object, I won't let it lock the object.
* Because whenever it changes something, it should lock the object as little time as possible
* to enable other threads to work normally
*
* */

public class Driver4 {
    public static void main(String[] args) {
        StringBuilder menu = new StringBuilder();
        menu.append(" ======= This is a test for multithreading and synchronization ========");
        menu.append("\nEnter a number to choose one test case: ");
        menu.append("\n1 Test multithreading operations with synchronization");
        menu.append("\n2 Test multithreading operations without synchronization");
        String answer;

        do {
            System.out.println(menu);

            Scanner sc = new Scanner(System.in);
            int x = sc.nextInt();

            switch (x) {
                case 1:
                    System.out.println("- Create a model through Creatable interface -");
                    Creatable a1 = new BuildAuto();
                    a1.buildAuto("src/FW_ZTW.txt");

                    System.out.println("- t1: change 'color' to 'Color' ");
                    System.out.println("- t2: change 'color' to 'External Color' ");
                    System.out.println("- t3: change color 'Twilight Blue Clearcoat Metallic' to 'NEW BLUE' ");
                    System.out.println("- t4: change color 'Twilight Blue Clearcoat Metallic' to 'BLUE' ");

                    String str1[] = {"Focus Wagon ZTW", "color", "Color"};
                    String str2[] = {"Focus Wagon ZTW", "color", "External Color"};
                    String str3[] = {"Focus Wagon ZTW", "color", "Twilight Blue Clearcoat Metallic", "NEW BLUE"};
                    String str4[] = {"Focus Wagon ZTW", "color", "Twilight Blue Clearcoat Metallic", "BLUE"};

                    Scalable a2 = new BuildAuto();
                    a2.startEditThread(1, str1, "t1");
                    a2.startEditThread(1, str2, "t2");
                    a2.startEditThread(2, str3, "t3");
                    a2.startEditThread(2, str4, "t4");

                    try {
                        Thread.currentThread().sleep(300);
                    } catch (InterruptedException ex) {
                    }

                    System.out.println("\n\nThe model after update:");
                    a1.printAuto("Focus Wagon ZTW");
                    break;

                case 2:
                    System.out.println("- Create a model through Creatable interface -");
                    Creatable a3 = new BuildAuto();
                    a3.buildAuto("src/FW_ZTW.txt");

                    System.out.println("- t5: change 'color' to 'Color' ");
                    System.out.println("- t6: change 'color' to 'External Color' ");
                    System.out.println("- t7: change color 'Twilight Blue Clearcoat Metallic' to 'NEW BLUE' ");
                    System.out.println("- t8: change color 'Twilight Blue Clearcoat Metallic' to 'BLUE' ");


                    String str5[] = {"Focus Wagon ZTW", "color", "async Color"};
                    String str6[] = {"Focus Wagon ZTW", "color", "async External Color"};
                    String str7[] = {"Focus Wagon ZTW", "color", "Twilight Blue Clearcoat Metallic", "NEW BLUE"};
                    String str8[] = {"Focus Wagon ZTW", "color", "Twilight Blue Clearcoat Metallic", "BLUE"};
                    Scalable a4 = new BuildAuto();
                    a4.startEditThread(999, str5, "t5");
                    a4.startEditThread(999, str6, "t6");
                    a4.startEditThread(998, str7, "t7");
                    a4.startEditThread(998, str8, "t8");

                    try {
                        Thread.currentThread().sleep(300);
                    } catch (InterruptedException ex) {
                    }

                    System.out.println("\n\n- The model after update -");
                    a3.printAuto("Focus Wagon ZTW");
                    break;
            }
            System.out.println("\nWould you want to try again? y/n");
            answer = sc.next();
        }while(answer.equals("Y") || answer.equals("y"));
    }
}
