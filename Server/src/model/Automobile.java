package model;
import java.io.*;
import java.util.ArrayList;

public class Automobile implements Serializable {
    private String name;
    private String make;
    private int year;
    private float basePrice;
    private ArrayList<OptionSet> optSet;
    private ArrayList<OptionSet.Option> choice;

    /**************************
     *
     * Constructor
     *
     * ************************/
    public Automobile(String name, float basePrice, String make, int year, int optionSetSize){
        this.name = name;
        this.basePrice = basePrice;
        this.make = make;
        this.year = year;
        optSet = new ArrayList<OptionSet>(optionSetSize);
        choice = new ArrayList<OptionSet.Option>(optionSetSize);
        for(int i = 0; i < optionSetSize;i++){
            choice.add(null);
        }
    }



    /**************************
     *
     * Getter and Setter
     *
     * ************************/
    public String getName(){
        return name;
    }
    public String getMake(){
        return make;
    }
    public int getYear(){
        return year;
    }
    public float getBasePrice(){
        return basePrice;
    }
    public ArrayList<OptionSet> getOptionSetList(){
        return optSet;
    }
    private ArrayList<OptionSet.Option> getOptionList(int index){
        return optSet.get(index).getOptionList();
    }

    public OptionSet getOptionSet(int index) {
        if (optSet.size() != 0) {
            return optSet.get(index);
        }
        return null;
    }

    public String getOptionSetName(int index) {
        OptionSet o = getOptionSet(index);
        if (o != null) {
            return getOptionSet(index).getName();
        }
        return "";
    }

    public OptionSet.Option getOption(int index, int pos) {
        if (optSet.size() != 0) {
            return optSet.get(index).getOption(pos);
        }
        return null;
    }

    public String getOptionName(int index, int pos) {
        OptionSet.Option o = getOption(index, pos);
        if (o != null) {
            return getOption(index, pos).getName();
        }
        return "";
    }

    public String getHashKey(){
        return make + name + year;
    }
    public int getOptSetSize(){ return optSet.size(); }

    public void setName(String name){
        this.name = name;
    }
    public void setMake(String make){
        this.make = make;
    }
    public boolean setYear(int year){
        if(year > 0) {
            this.year = year;
            return true;
        }
        return false;
    }
    public boolean setBasePrice(float basePrice){
        if(basePrice > 0) {
            this.basePrice = basePrice;
            return true;
        }
        return false;
    }
    public void createOptionSet(String name, int optionSize){
        optSet.add(new OptionSet(name,optionSize));
    }

    public boolean setOptionSetName(int index, String optionSetName){
        if(!optionSetIsNull(index)) {
            optSet.get(index).setName(optionSetName);
            return true;
        }
        return false;
    }
    public boolean setOption(int index, int pos, String optionName, float price){
        if(!optionSetIsNull(index) && !optionIsNull(index,pos)) {
            getOption(index,pos).setOption(optionName, price);
            return true;
        }
        return false;
    }

//    public boolean setOptionName(int index, int pos, String optionName){
//        if(!optionSetIsNull(index) && !optionIsNull(index,pos)) {
//            getOption(index,pos).setOptionName(optionName);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean setOptionPrice(int index, int pos, float price){
//        if(!optionSetIsNull(index) && !optionIsNull(index,pos)) {
//            getOption(index,pos).setOptionPrice(price);
//            return true;
//        }
//        return false;
//    }

    private boolean optionSetIsNull(int index){
        return optSet.get(index) == null;
    }

    private boolean optionIsNull(int index, int pos){
        return getOptionList(index).get(pos) == null;
    }



    /**************************
     *
     * Display method
     *
     * ************************/
    public String toString(){
        StringBuilder auto = new StringBuilder("Model Name: ").append(getName());
        auto.append("\nBase Price: $").append(getBasePrice());
        auto.append("\nMake: ").append(make).append("\nYear: ").append(year);
        auto.append("\n---------------------\n");
        for(int i = 0; i < optSet.size(); i++){
            if(!optionSetIsNull(i)){
                auto.append(optSet.get(i).toString());
                auto.append("[Choice] ");
                if(choice.get(i) != null)
                    auto.append(choice.get(i).getName());
                else
                    auto.append("no choice");
                auto.append("\n\n");
            }else{
                auto.append("null\n");
            }
        }
        return auto.toString();
    }

    public void print(){
        System.out.println(this.toString());
    }

    public String choiceToString(){
        StringBuilder str = new StringBuilder("Choice of ").append(getName()).append("\n");
        for(int i = 0; i< optSet.size();i++){
            OptionSet.Option o = choice.get(i);
            str.append(optSet.get(i).getName()).append(": ");
            if(o!=null) {
                str.append(o.getName()).append(" $").append(o.getPrice()).append("\n");
            }else{
                str.append("no choice\n");
            }
        }
        return str.toString();
    }

    public void printChoice(){
        System.out.println(choiceToString());
    }



    /**************************
     *
     * Choice method
     *
     * ************************/
    public void setOptionChoice (String optionSetName, String optionName){
        // set option choice in OptionSet
        OptionSet s = findOptionSet(optionSetName);
        if(s != null){
            s.setOptionChoice(optionName);
        }
        // set choice list in Automobile
        int index = findOptionSetIndex(optionSetName);
        int pos = findOptionIndex(index,optionName);
        if(index != -1 && pos != -1){
            choice.set(index,getOption(index,pos));
        }
    }

    public String getOptionChoice(String optionSetName){
        int index = findOptionSetIndex(optionSetName);
        if(index != -1 && choice.get(index) != null) {
            return choice.get(index).getName();
        }else{
            return null;
        }
    }

    public float getOptionChoicePrice(String optionSetName){
        int index = findOptionSetIndex(optionSetName);
        if(index != -1 && choice.get(index) != null) {
            return choice.get(index).getPrice();
        }else{
            return 0;
        }
    }

    public float getTotalPrice(){
        float total = basePrice;
        for (OptionSet.Option o: choice) {
            if(o!=null){
                total += o.getPrice();
            }
        }
        return total;
    }

    public void clearChoice(){
        for (int i = 0; i< choice.size();i++) {
            choice.set(i,null);
        }
    }



    /**************************
     *
     * Find Method
     *
     * ************************/
    public int findOptionSetIndex(String optionSetName){
        for(int i = 0; i < optSet.size(); i++){
            if(!optionSetIsNull(i)) {
                if (optSet.get(i).getName().equals(optionSetName)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int findOptionIndex(int index, String optionName){
        if(index != -1){
            if(!optionSetIsNull(index)) {
                ArrayList<OptionSet.Option> optList = getOptionList(index);
                if(optList != null){
                    for (int i = 0; i < optList.size(); i++) {
                        if (!optionIsNull(index, i) && optList.get(i).getName().equals(optionName)) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public OptionSet findOptionSet(String optionSetName){
        for (OptionSet o: optSet) {
            if(o != null && o.getName().equals(optionSetName))
                return o;
        }
        return null;
    }

    public int findOptionIndex(String optionSetName, String optionName){
        int index = findOptionSetIndex(optionSetName);
        return findOptionIndex(index,optionName);
    }

    public OptionSet.Option findOption(String optionSetName, String optionName){
        int index = findOptionSetIndex(optionSetName);
        if(index != -1){
            if(!optionSetIsNull(index)) {
                ArrayList<OptionSet.Option> optList = getOptionList(index);
                if(optList != null){
                    for (OptionSet.Option opt: optList) {
                        if(opt != null && opt.getName().equals(optionName))
                            return opt;
                    }
                }
            }
        }
        return null;
    }



    /**************************
     *
     * Delete Method
     *
     * ************************/
    public boolean deleteOptionSet(int index){
        if(index != -1){
            optSet.set(index,null);
            return true;
        }
        return false;
    }
    private boolean deleteOption(int index, int pos){
        if(index != -1 && pos != -1 && !optionSetIsNull(index)){
            getOptionList(index).set(pos,null);
            return true;
        }
        return false;
    }
    public boolean deleteOptionSet(String optionSetName){
        int index = findOptionSetIndex(optionSetName);
        return deleteOptionSet(index);
    }
    public boolean deleteOption(String optionSetName, String optionName){
        int index = findOptionSetIndex(optionSetName);
        int pos = findOptionIndex(index,optionName);
        return deleteOption(index,pos);
    }



    /**************************
     *
     * Update Method
     *
     * ************************/
    public boolean updateOptionSetName(int index, String newName){
        if(index != -1){
            optSet.get(index).setName(newName);
            return true;
        }
        return false;
    }

    public boolean updateOptionSetName(String oldName, String newName){
        int index = findOptionSetIndex(oldName);
        return updateOptionSetName(index,newName);
    }

    public boolean updateOption(int index, int pos, OptionSet.Option opt){
        if(index != -1 && pos != -1 && !optionSetIsNull(index)){
            optSet.get(index).setOption(pos,opt);
            return true;
        }
        return false;
    }
    public boolean updateOption(int index, int pos, String optionName, float optionPrice){
        if(index != -1 && pos != -1 && !optionSetIsNull(index)){
            // the setOption method will check if the option is null
            return optSet.get(index).setOption(pos,optionName,optionPrice);
        }
        return false;
    }

    public boolean updateOptionName(int index, int pos, String newName){
        if(index != -1 && pos != -1 && !optionSetIsNull(index)){
            boolean success = optSet.get(index).setOptionName(pos,newName);
            return success;
        }
        return false;
    }
    public boolean updateOptionPrice(int index, int pos, float optionPrice){
        if(index != -1 && pos != -1 && !optionSetIsNull(index)){
            boolean success = optSet.get(index).setOptionPrice(pos,optionPrice);
            return success;
        }
        return false;
    }

    public boolean updateOption(String optionSetName, String oldOptionName, OptionSet.Option opt){
        int index = findOptionSetIndex(optionSetName);
        int pos = findOptionIndex(index, oldOptionName);
        return updateOption(index,pos,opt);
    }
    public boolean updateOption(String optionSetName, String oldOptionName, String optionName, float optionPrice){
        int index = findOptionSetIndex(optionSetName);
        int pos = findOptionIndex(index, oldOptionName);
        return updateOption(index,pos,optionName,optionPrice);
    }
    public boolean updateOptionName(String optionSetName, String optionName, String newName){
        int index = findOptionSetIndex(optionSetName);
        int pos = findOptionIndex(index, optionName);
        return updateOptionName(index,pos,newName);
    }
    public boolean updateOptionPrice(String optionSetName, String optionName, float optionPrice){
        int index = findOptionSetIndex(optionSetName);
        int pos = findOptionIndex(index, optionName);
        return updateOptionPrice(index,pos,optionPrice);
    }



    /**************************
     *
     * Actual Size Method
     *
     * ************************/
    public int getSizeOfOptionSet(){
        int opsetSize = 0;
        for ( OptionSet optionSet : optSet){
            if(optionSet != null){
                opsetSize++;
            }
        }
        return opsetSize;
    }

    private int getSizeOfOption(int index){
        int size = 0;
        if(index != -1 && !optionSetIsNull(index)){
            size = optSet.get(index).getSizeOfOption();
        }
        return size;
    }

    public int getSizeOfOption(String optionSetName){
        int index = findOptionSetIndex(optionSetName);
        return getSizeOfOption(index);
    }

    public int getOptionListSize(int index){
        return getOptionList(index).size();
    }


    /**************************
     *
     * Index Validation
     * These validation method should be called before passing a number
     * to the method that uses index as a parameter
     *
     * ************************/
    /*
    public boolean isValidIndex(int optionSetIndex){
        return optionSetIndex >= 0 && optionSetIndex < optSet.size();
    }
    public boolean isValidPos(int index, int optionPos){
        return optionPos >= 0 && optionPos < getOptionList(index).size();
    }
    */


}