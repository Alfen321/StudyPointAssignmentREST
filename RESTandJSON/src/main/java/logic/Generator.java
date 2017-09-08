package logic;

import java.util.ArrayList;
import java.util.List;

public class Generator {

    public List<Person> generate(int numberOfData, int startFrom){
        ArrayList<String> generatedFirstNames = this.firstName();
        ArrayList<String> generatedLastNames = this.lastName();
        ArrayList<Person> generatedData = new ArrayList();
        
        int randomNumberFirst;
        int randomNumberLast;
        int index = startFrom;
        
        while(generatedData.size() <= numberOfData){
            randomNumberFirst = (int) (Math.random() * generatedFirstNames.size());
            System.out.println("RandomNumberFirst: " + randomNumberFirst);
            randomNumberLast = (int) (Math.random() * generatedLastNames.size());
            generatedData.add(new Person(index, generatedFirstNames.get(randomNumberFirst), generatedLastNames.get(randomNumberLast), (int) (Math.random() * 52) + 18));
            index++;
        }
        return generatedData;
    }
    
    private ArrayList<String> firstName() {
        ArrayList<String> firstNames = new ArrayList();
        firstNames.add("Tjalfe");
        firstNames.add("David");
        firstNames.add("Joachim");
        return firstNames;
    }

    private ArrayList<String> lastName() {
        ArrayList lastNames = new ArrayList();
        lastNames.add("Hansen");
        lastNames.add("Nielsen");
        lastNames.add("Post-it");
        return lastNames;
    }
}
