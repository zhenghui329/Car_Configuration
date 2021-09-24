package util;
import exception.AutoException;
import model.Automobile;

import java.io.*;


public class FileIO{

    public Automobile buildAutoObject(String filename){

        try(FileReader file = new FileReader(filename);
            BufferedReader buff = new BufferedReader(file)) {
            // Read one line and throw exception
            String line = buff.readLine();
            try {
                if (line == null) {
                    throw new AutoException(201, "Empty input file");
                } else if (line.equals("")) {
                    throw new AutoException(1, "Missing model name");
                }
            }catch(AutoException ex){
                System.out.println("Error: " + ex.toString());
            }
            String name = line;

            // Read the second line and throw exception
            line = buff.readLine();
            float price;
            try {
                if (line == null || line.equals("")) {
                    throw new AutoException(2, "Missing base price");
                }
                price = Float.parseFloat(line);
            } catch(AutoException ex){
                System.out.println("Error: " + ex.toString());
                StringBuilder x = new StringBuilder();
                ex.fix(x);
                price = Float.parseFloat(x.toString());
            } catch(NumberFormatException e){
                try {
                    throw new AutoException(101, "Wrong format for base price");
                }catch(AutoException ex){
                    System.out.println("Error: " + ex.toString());
                    StringBuilder x = new StringBuilder();
                    ex.fix(x);
                    price = Float.parseFloat(x.toString());
                }
            }

            line = buff.readLine();
            String make = line;
            line = buff.readLine();
            int year = Integer.parseInt(line);
            line = buff.readLine();
            // We can throw missing line or missing value exception,
            // wrong format, negative size exception here
            int optionSetSize = Integer.parseInt(line);
            Automobile a1 = new Automobile(name, price, make,year,optionSetSize);

            // Read OptionSet and Option
            int optionSetCnt = 0;
            int optionCnt = 0;
            boolean eof = false;
            while (!eof) {
                line = buff.readLine();
                if (line == null) {
                    eof = true;
                }
                else {
                    String str[] = line.split(",");
                        if (str[0].equals("optSet")) {
                            try {
                                if (optionSetCnt != 0) {
                                    if (optionCnt != a1.getOptionListSize(optionSetCnt - 1)) {
                                        throw new AutoException(5, "Missing option(s): size does not match data");
                                    }
                                }
                            }catch(AutoException ex){
                                System.out.println("Error: " + ex.toString());
                            }
                            optionCnt = 0;
                        }
                    if(str[0].equals("optSet")){   // Read OptionSet
                            String optionSetName = str[1];
                            // Here we can throw invalid size, wrong format exception
                            // and catch ArrayIndexOutOfBoundsException exception
                            int optionArySize = Integer.parseInt(str[2]);
                            a1.createOptionSet(optionSetName, optionArySize);
                            optionSetCnt++;
                    }else{    // Read Option
                        String optionName = "";
                        float optionPrice = -1;
                        try {
                            // Here we can throw wrong format, invalid size exception in the same way
                            optionName = str[0];
                            try {
                                if (optionName.equals("")) {
                                    throw new AutoException(3, "Missing option name");
                                }
                            } catch (AutoException ex) {
                                System.out.println("Error: " + ex.toString());
                            }
                            optionPrice = Float.parseFloat(str[1]);
                        } catch(ArrayIndexOutOfBoundsException e){
                            try {
                                throw new AutoException(4, "Missing option price");
                            }catch(AutoException ex){
                                System.out.println("Error: " + ex.toString());
                            }
                        }
                            int index = optionSetCnt - 1;
                            int pos = optionCnt;
                            a1.setOption(index, pos, optionName, optionPrice);
                            optionCnt++;
                        }
                }
            }
            try {
                if (optionSetCnt < optionSetSize) {
                    throw new AutoException(6, "Missing optionSet(s): size does not match the data");
                }
            } catch(AutoException ex){
                System.out.println("Error: " + ex.toString());
            }
            return a1;
        } catch(FileNotFoundException e){
            try {
                throw new AutoException(202, "Input file not found");
            }catch(AutoException ex){
                System.out.println("Error: " + ex.toString());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
        }
        return null;
    }


    public void serializeAuto(String filename, Automobile autos[]){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(autos);
            out.close();
        } catch(Exception e) {
            System.out.print("Error: " + e.toString());
            System.exit(1); }
    }

    public Automobile[] deserializeAutoAry(String filename){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            Automobile autos[] = (Automobile[])in.readObject();
            in.close();
            return autos;
        } catch(Exception e) {
            System.out.print("Error: " + e.toString());
            System.exit(1);
        }
        return null;
    }

}