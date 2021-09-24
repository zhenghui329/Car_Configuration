package a​dapter;

public interface Choosable {
    void printAuto(Object auto);

    void setChoice(String modelName,String optionSetName, String optionName);

    void setChoice(Object auto, String optionSetName,String optionName);

    int getOptSetSize(Object auto);

    String getOptSetName(Object auto, int index);

    void displayChoiceName(String modelName, String optionSetName);

    void displayChoicePrice(String modelName,String optionSetName);

    void displayTotalPrice(String modelName);

    void displayTotalPrice(Object auto);

    void printChoice(String modelName);

    void printChoice(Object auto);

    void clearChoice(String modelName);

}
