package a​dapter;

public interface Creatable {
    // Given​ ​a​ ​text​ ​file​ ​name​, ​build​ ​an​ ​instance​ ​of Automobile
    void buildAndAddAuto(Object obj, int fileType);

    // Search ​and​ ​print ​the​ ​properties​ ​of​ ​a​ ​given​ ​Automobile
    void printAuto(String modelName);

    // display ​the​ ​​Automobile list​
    void display();
}
