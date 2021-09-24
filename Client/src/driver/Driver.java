package driver;
import model.Automobile;
import util.FileIO;

public class Driver {

    public static void main(String[] args) {
        System.out.println("== This is a test for Automobile model class, file IO and serialization ==");
        System.out.println();
        Automobile a[] = new Automobile[3];
        FileIO readFile = new FileIO();
        // Build Automobile Object and print
        System.out.println("=========== Building objects ===========");
        a[0] = readFile.buildAutoObject("src/FW_ZTW.txt");
        a[0].print();
        a[1] = readFile.buildAutoObject("src/FW_ZTW_B.txt");
        a[1].print();
        a[2] = readFile.buildAutoObject("src/FW_ZTW_C.txt");
        a[2].print();

        // Test for method in Automobile Class
        System.out.println("=========== Test method in Automobile Class===========");
        System.out.println("1)Set model name and base price");
        StringBuilder before = new StringBuilder("Before: ").append(a[0].getName())
                .append(" $").append(a[0].getBasePrice());
        System.out.println(before.toString());
        a[0].setName("Focus Wagon ZTW_new");
        a[0].setBasePrice(18440);
        StringBuilder after = new StringBuilder("After: ").append(a[0].getName())
                .append(" $").append(a[0].getBasePrice());
        System.out.println(after.toString());
        System.out.println();

        System.out.println("2)Find and delete an OptionSet: Power Moonroof");
        int index_PM = a[0].findOptionSetIndex("Power Moonroof");
        System.out.println("index of Power Moonroof is " + index_PM);
        if(a[0].deleteOptionSet(index_PM)){
            System.out.println("The OptionSet has been deleted");
        }else{
            System.out.println("Delete failed");
        }
        System.out.println();

        System.out.println("3)Delete an Option: Fort Knox Gold Clearcoat Metallic");
        if(a[0].deleteOption("color","Fort Knox Gold Clearcoat Metallic")){
            System.out.println("The Option has been deleted");
        }else{
            System.out.println("Delete failed");
        }
        System.out.println();

        System.out.println("4)Update an Option price: update \"Infra-Red Clearcoat\" to $100 ");
        if(a[0].updateOptionPrice("color","Infra-Red Clearcoat",100)){
            System.out.println("The Option has been updated");
        }else{
            System.out.println("Update failed");
        }
        System.out.println();

        System.out.println("The model object after change:");
        a[0].print();
        System.out.println();

        FileIO ser = new FileIO();
        //Serialize the object
        System.out.println("=========== Serialize the 3 objects ============");
        ser.serializeAuto("FordZTW.ser", a);

        System.out.println("============ Deserialize the 3 objects ==========");
        Automobile newAutos[] = ser.deserializeAutoAry("FordZTW.ser");

        System.out.println("=========== Print the objects: ============");
        for(Automobile auto : newAutos){
            auto.print();
        }

    }
}