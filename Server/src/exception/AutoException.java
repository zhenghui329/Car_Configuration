package exception;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AutoException extends Exception {
    private int errorNo;
    private String errorMsg;


    public AutoException() {
        super();
        logException();
    }

    public AutoException(int errorNo) {
        super();
        this.errorNo = errorNo;
        logException();
    }

    public AutoException(String errorMsg) {
        super();
        this.errorMsg = errorMsg;
        logException();
    }

    public AutoException(int errorNo, String errorMsg) {
        super();
        this.errorNo = errorNo;
        this.errorMsg = errorMsg;
        logException();
    }

    public int getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(int errorNo) {
        this.errorNo = errorNo;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    public void logException(){
        try(FileWriter file = new FileWriter("src/error.log",true);
            PrintWriter pw = new PrintWriter(file)){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            pw.write(dtf.format(now) + "\n");
            printStackTrace(pw);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.toString());
            System.exit(1);
        }
    }

    public String toString() {
        StringBuilder e = new StringBuilder();
        e.append("AutoException [ error No.").append(errorNo).append(" ").append(errorMsg).append(" ]");
        return e.toString();
    }


    public void fix(StringBuilder x)
    {
        switch(errorNo)
        {
            case 2:   Fix1to100 f2 = new Fix1to100();
                      f2.fix2(x);
                      break;
            case 101: Fix101to200 f101 = new Fix101to200();
                      f101.fix101(x);
                      break;
            case 402: Fix401to500 f402 = new Fix401to500();
                      f402.fix402(x);
                      break;
        }
    }



}
