package exception;

import java.util.Scanner;

class Fix1to100 {

    /*
     *  error 2 : Missing base price
     *  How I fix : get a new number from console
     * */
    void fix2(StringBuilder x) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number to fix it: ");
        x.append(sc.next());
    }

}
