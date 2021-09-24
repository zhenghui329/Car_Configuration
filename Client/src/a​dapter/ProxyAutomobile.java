package a​dapter;
import exception.AutoException;
import model.*;
import util.*;
import scale.*;

public abstract class ProxyAutomobile {
    private static myLHM<String,Automobile> autos = new myLHM<>();

    public myLHM<String,Automobile> getLHM(){
        return autos;
    }

    public void buildAuto(String fileName) {
        FileIO readFile = new FileIO();
        Automobile a = readFile.buildAutoObject(fileName);
        String key = a.getHashKey();
        autos.addAuto(key,a);
    }

    public void printAuto(String modelName){
        Automobile a = autos.findByModelName(modelName);
        if(a != null) {
            a.print();
        }
    }

    public void display(){
        autos.display();
    }

    public void updateOptionSetName(String modelName, String optionSetName, String newName) {
        if(newName.equals("")){
            System.out.println("Error no.401 : Update failed -- missing optionSet name");
            return;
        }
        Automobile a = autos.findByModelName(modelName);
        if(a != null) {
            boolean success = a.updateOptionSetName(optionSetName,newName);
            if(!success){
                System.out.println("Error no.402 : Update failed -- optionSet name not found");
            }
        }
    }

    public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice) {
        Automobile a = autos.findByModelName(modelName);
        if(a != null) {
            a.updateOptionPrice(optionSetName,optionName,newPrice);
        }
    }

    public void fix(int n,StringBuilder x){
        AutoException error = new AutoException(n);
        error.fix(x);
    }


    public void printAuto(Object auto){
        Automobile a = (Automobile)auto;
        if(a!=null) {
            a.print();
        }
    }


    public void setChoice(String modelName,String optionSetName, String optionName){
        Automobile a = autos.findByModelName(modelName);
        if(a != null) {
            a.setOptionChoice(optionSetName,optionName);
        }
    }

    public void setChoice(Object auto, String optionSetName,String optionName){
        Automobile a = (Automobile)auto;
        if(a != null) {
            a.setOptionChoice(optionSetName,optionName);
        }
    }

    public int getOptSetSize(Object auto){
        Automobile a = (Automobile)auto;
        if(a!=null) {
            return a.getOptSetSize();
        }
        return 0;
    }

    public String getOptSetName(Object auto, int index){
        Automobile a = (Automobile)auto;
        if(a!=null){
            return a.getOptionSetName(index);
        }
        return null;
    }

    public void displayChoiceName(String modelName, String optionSetName){
        Automobile a = autos.findByModelName(modelName);
        StringBuilder choice = new StringBuilder();
        if(a != null) {
            choice.append("Your choice of ").append(optionSetName).append(" is: ");
            String str = a.getOptionChoice(optionSetName);
            if(str!=null)
                choice.append(str);
            else
                choice.append("choice not set");
        }else{
            choice.append("wrong model name");
        }
        System.out.println(choice.toString());
    }

    public void displayChoicePrice(String modelName, String optionSetName){
        Automobile a = autos.findByModelName(modelName);
        StringBuilder price = new StringBuilder();
        if(a != null) {
            price.append("The price of ").append(optionSetName).append(" for your choice is: ");
            String str = a.getOptionChoice(optionSetName);
            if(str!=null)
                price.append(a.getOptionChoicePrice(optionSetName));
            else
                price.append("choice not set");
        }else{
            price.append("wrong model name");
        }
        System.out.println(price.toString());
    }

    public void printChoice(String modelName){
        Automobile a = autos.findByModelName(modelName);
        if(a != null) {
            a.printChoice();
        }
    }

    public void printChoice(Object auto){
        Automobile a = (Automobile)auto;
        if(a != null) {
            a.printChoice();
        }
    }

    public void displayTotalPrice(String modelName){
        Automobile a = autos.findByModelName(modelName);
        StringBuilder total = new StringBuilder();
        if(a != null) {
            total.append("The Total price for your ").append(modelName).append(" is: $");
            total.append(a.getTotalPrice());
        }else{
            total.append("wrong model name");
        }
        System.out.println(total.toString());
    }

    public void displayTotalPrice(Object auto){
        Automobile a = (Automobile)auto;
        StringBuilder total = new StringBuilder();
        if(a != null) {
            total.append("The Total price for your ").append(a.getName()).append(" is: $");
            total.append(a.getTotalPrice());
        }
        System.out.println(total.toString());
    }

    public void clearChoice(String modelName){
        Automobile a = autos.findByModelName(modelName);
        if(a != null) {
            a.clearChoice();
        }
    }

    public void startEditThread(int x, String strAry[], String name){
        EditOption edit = new EditOption(x,strAry,name);
        edit.start();
    }
}