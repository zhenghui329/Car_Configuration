package exception;

import java.util.Scanner;

public class Fix101to200 {

    /*
    *  error 101 : Wrong format for base price
    *  How I fix : get a new number from console
    * */
    void fix101(StringBuilder x) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number to fix it: ");
        x.append(sc.next());
    }
}
