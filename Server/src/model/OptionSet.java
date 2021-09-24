package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Formatter;

public class OptionSet implements Serializable {
    private String name;
    private ArrayList<Option> opt;
    private Option choice;

    protected OptionSet(String name, int size){
        this.name = name;
        choice = null;
        opt = new ArrayList<Option>(size);
        for(int i = 0; i < size; i++){
            opt.add(new Option());
        }
    }

    protected String getName(){
        return name;
    }
    protected ArrayList<Option> getOptionList(){
        return opt;
    }
    protected Option getOption(int pos){
        return opt.get(pos);
    }

    /****************************
     *
     * Choice Method
     *
     ***************************/
    protected Option getOptionChoice(){
        return choice;
    }

    protected void setOptionChoice(String optionName){
        for (Option o : opt) {
            if(o != null && o.getName() != null && o.getName().equals(optionName))
                choice = o;
        }
    }
    protected void setName(String name){
        this.name = name;
    }
    protected void setOption(int pos, Option a){
        opt.set(pos,a);
    }
    protected boolean setOption(int pos, String name, float price){
        if(!optionIsNull(pos)) {
            opt.get(pos).setOption(name, price);
            return true;
        }
        return false;
    }
    protected boolean setOptionName(int pos, String name){
        if(!optionIsNull(pos)) {
            opt.get(pos).setOptionName(name);
            return true;
        }
        return false;
    }
    protected boolean setOptionPrice(int pos, float price){
        if(!optionIsNull(pos)) {
            opt.get(pos).setOptionPrice(price);
            return true;
        }
        return false;
    }

    protected int getSizeOfOption(){
        int optSize = 0;
        for ( Option option : opt){
            if(option != null){
                optSize++;
            }
        }
        return optSize;
    }

    protected boolean optionIsNull(int pos){
        return opt.get(pos) == null;
    }

    public String toString(){
        StringBuilder optionSet = new StringBuilder(getName()).append(":\n");
        for (Option o: opt) {
            if(o != null)
                optionSet.append(o.toString()).append("\n");
            else
                optionSet.append("null\n");
        }
        return optionSet.toString();
    }


    class Option implements Serializable {
        private String name;
        private float price;

        protected Option(){
            name = "";
            price = 0.0f;
        }
        protected Option(String name, float price){
            this.name = name;
            this.price = price;
        }

        protected String getName(){
            return name;
        }
        protected float getPrice(){
            return price;
        }

        protected void setOptionName(String name){
            this.name = name;
        }
        protected void setOptionPrice(float price){
            this.price = price;
        }
        protected void setOption(String name, float price){
            this.name = name;
            this.price = price;
        }

        public String toString(){
            StringBuilder price = new StringBuilder();
            if(getPrice()>=0){
                price.append("$");
                price.append(getPrice());
            }else{
                price.append(getPrice());
                price.insert(1,"$");
            }
            Formatter fmt = new Formatter();
            fmt.format("%-40s %10s", getName(), price);
            return fmt.toString();
        }
    }

}
