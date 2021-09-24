package exception;

import java.util.Scanner;

public class Fix401to500 {
    /*
     *  Error no.402 : Update failed -- optionSet name not found
     *  How I fix : get a new string from console
     * */
    public void fix402(StringBuilder x) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a correct optionSet name that you want to replace: ");
        x.append(sc.nextLine());
        x.append(",");
        System.out.print("Enter a new optionSet name that you want to update: ");
        x.append(sc.nextLine());
    }
}
