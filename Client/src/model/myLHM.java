package model;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class myLHM<String, T extends Automobile> {
    private LinkedHashMap<String, T> lHashMap;

    // constructor
    public myLHM() {
        lHashMap = new LinkedHashMap<String, T>();
    }

    LinkedHashMap<String, T> getlHashMap(){
        return lHashMap;
    }

    public int getSize(){
        return lHashMap.size();
    }

    public boolean addAuto(String s, T t){
        if(lHashMap.put(s,t) == null) {
            return true;
        }else{
            return false;
        }
        // If an existing key is passed then the previous value gets returned.
        // If a new pair is passed, then NULL is returned

    }

    public boolean removeAuto(String s){
        if(lHashMap.remove(s) == null) {
            return false;
        }else{
            return true;
        }
        // The method returns the value that was previously
        // mapped to the specified key if the key exists
        // else the method returns NULL
    }

    public T find(String key){
        boolean found = lHashMap.containsKey(key);
        if(found){
            return lHashMap.get(key);
        }
        return null;
    }

    public T findByModelName(String modelName){
        Collection<T> c = lHashMap.values();
         Iterator<T> itr = c.iterator();
         while(itr.hasNext()){
            T t = itr.next();
            if(t != null && t.getName().equals(modelName)){
                return t;
            }
         }
//         Using foreach loop
//        for ( T t : c) {
//            if (t != null && t.getName().equals(modelName)) {
//                return t;
//            }
//        }
        return null;
    }

    public void clear(){
        lHashMap.clear();
    }

    public void display(){
        Collection<T> c = lHashMap.values();
        Iterator<T> itr = c.iterator();
        while(itr.hasNext()){
            T t = itr.next();
            if(t != null){
                System.out.println(t.getMake()+ " " + t.getName() + " " + t.getYear());
            }
        }
    }

}
