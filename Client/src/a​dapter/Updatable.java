package a​dapter;

public interface Updatable {
    // Search ​the​ ​Model​ ​for​ ​a​ ​given​ ​OptionSet​ ​and​ ​sets​ ​the​ ​name​ ​of​ ​OptionSet​ ​to newName
    void updateOptionSetName(String modelName, String optionSetName, String newName);

    // Search​ ​the​ ​Model​ ​for​ ​a​ ​given​ ​OptionSet​ ​and​ ​Option​ ​name,​ ​and​ ​sets​ ​the​ ​price​ ​to newPrice.
    void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice);

}
