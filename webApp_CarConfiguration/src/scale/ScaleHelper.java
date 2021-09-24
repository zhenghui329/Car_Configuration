package scale;
import model.Automobile;

class ScaleHelper{

    void editOptSetName(Automobile a, String[]str){
        int index = a.findOptionSetIndex(str[1]);
        try {
            Thread.currentThread().sleep(200);
        }catch(InterruptedException ex){ }
        if(index != -1) {
            // synchronized block
            // it locks the OptionSet object
            synchronized (a.getOptionSet(index)) {
                String optionSetName = a.getOptionSetName(index);
                if (optionSetName.equals(str[1])) {
                    a.updateOptionSetName(index, str[2]);
                    System.out.println("Thread " + Thread.currentThread().getName() + " succeed.");
                } else {
                    System.out.println("Thread " + Thread.currentThread().getName() + " failed. - Option Set was not found");
                }
            }
        }else{
            System.out.println("Thread " + Thread.currentThread().getName() + " failed. - Option Set was not found");
        }
    }

    void editOptName(Automobile a, String[]str){
        int index = a.findOptionSetIndex(str[1]);
        int pos = a.findOptionIndex(str[1],str[2]);
        try {
            Thread.currentThread().sleep(200);
        }catch(InterruptedException ex){ }
        if(index != -1 && pos != -1){
            // synchronized block
            // it locks the Option object
            synchronized (a.getOption(index,pos)){
                String optionName = a.getOptionName(index,pos);
                if(optionName.equals(str[2])){
                    a.updateOptionName(index,pos,str[3]);
                    System.out.println("Thread " + Thread.currentThread().getName() + " succeed.");
                }else{
                    System.out.println("Thread " + Thread.currentThread().getName() + " failed. - Option was not found");
                }
            }
        }else{
            System.out.println("Thread " + Thread.currentThread().getName() + " failed. - Option was not found");
        }
    }


    void async_editOptSetName(Automobile a, String[]str){
        int index = a.findOptionSetIndex(str[1]);
        try {
            Thread.currentThread().sleep(200);
        }catch(InterruptedException ex){ }
        if(index != -1) {
            a.updateOptionSetName(index,str[2]);
            System.out.println("Thread " + Thread.currentThread().getName() + " succeed.");
        }else{
            System.out.println("Thread " + Thread.currentThread().getName() + "Async failed. - Option Set was not found");
        }
    }

    void async_editOptName(Automobile a, String[]str){
        int index = a.findOptionSetIndex(str[1]);
        int pos = a.findOptionIndex(str[1],str[2]);
        try {
            Thread.currentThread().sleep(200);
        }catch(InterruptedException ex){ }
        if(index != -1 && pos != -1){
            a.updateOptionName(index,pos,str[3]);
            System.out.println("Thread " + Thread.currentThread().getName() + " succeed.");
        }else{
            System.out.println("Thread " + Thread.currentThread().getName() + "Async failed. - Option was not found");
        }
    }


}
