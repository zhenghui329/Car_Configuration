package scale;
import a​dapter.ProxyAutomobile;
import model.Automobile;

public class EditOption extends ProxyAutomobile implements Runnable {
    private Thread t;
    private Automobile a;
    private String[] str;
    private int threadNo;
    private boolean DEBUG = true;

    public EditOption(int x, String strAry[],String name){
        Automobile a = getLHM().findByModelName(strAry[0]);
        if( a != null) {
            threadNo = x;
            this.a = a;
            str = strAry;
            t = new Thread(this,name);
        }
    }

    public void start(){
        t.start();
    }

    public void run(){
        if(DEBUG) {
            switch (threadNo) {
                case 0:
                    System.out.println("Start thread " + threadNo + "(" + t.getName() + ") Update model name");
                    break;
                case 1:
                    System.out.println("Start thread " + threadNo + "(" + t.getName() + ") Update OptionSet name");
                    break;
                case 2:
                    System.out.println("Start thread " + threadNo + "(" + t.getName() + ") Update Option name");
                    break;
                case 3:
                    System.out.println("Start thread " + threadNo + "(" + t.getName() + ") Update Option price");
                    break;
                case 999:
                case 998:
                    System.out.println("Start thread " + threadNo + "(" + t.getName() + ") Update through async");
                    break;
            }
        }
        ops(a,str);
        if(DEBUG){
            System.out.println("Stopping thread " + threadNo + "(" + t.getName() + ")");
        }
    }

    public void ops(Automobile a, String[] str) {
        switch (threadNo) {
            case 1: ScaleHelper hp1 = new ScaleHelper();
                hp1.editOptSetName(a,str);
                break;
            case 2: ScaleHelper hp2 = new ScaleHelper();
                hp2.editOptName(a,str);
                break;
            case 999: ScaleHelper hp999 = new ScaleHelper();
                hp999.async_editOptSetName(a,str);
                break;
            case 998: ScaleHelper hp998 = new ScaleHelper();
                hp998.async_editOptName(a,str);
                break;
        }
    }
}
