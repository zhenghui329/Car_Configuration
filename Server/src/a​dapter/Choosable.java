package a​dapter;

public interface Choosable {

    void setChoice(String modelName,String optionSetName, String optionName);

    void displayChoiceName(String modelName, String optionSetName);

    void displayChoicePrice(String modelName,String optionSetName);

    void displayTotalPrice(String modelName);

    void printChoice(String modelName);

    void clearChoice(String modelName);

    int getOptSetSize(Object a);

    String getOptSetName(Object a, int index);

    void setChoice(Object a, String optionSetName,String optionName);

}
