import jodd.json.JsonSerializer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class People {


    public static void main(String[] args) throws FileNotFoundException {

       //call method to read from a file and return a HashMap
        HashMap<String, ArrayList<Person>> personMap = fileToHashMap();


        //loops over hashMap, sort list by last name.
        for (ArrayList<Person> personArray : personMap.values()) {  //go through all the arraylists in the map
            Collections.sort(personArray); //call custom sort method
        }

        System.out.println(personMap.values());

        //just a way to play around with nested loops a bit more and getting inside of multi-D-data-structures
        //Saw Alex messing around with .size and it made me think of it.
        final int[] assetCount = {0};  //variables used in Lambda's must be final and arrays. Don't know why.
        personMap.forEach(( k,v ) -> v.forEach(item-> assetCount[0] += 1));  //Lambda expression that runs to forEach's

       // for (ArrayList<Person> personArray : personMap.values()) for (Person p : personArray) assetCount[0] += 1;

        System.out.printf("There are a total of %d assets in %d countries", assetCount[0], personMap.keySet().size());

        try {
            writeToJson(personMap);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something has gone wrong with the file writing process. Please contact Doug.");
        }
    }


    //method to read in a file, store it as a hashmap, and return it.
    public static HashMap<String, ArrayList<Person>> fileToHashMap() throws FileNotFoundException {
        HashMap<String, ArrayList<Person>> personMap = new HashMap<>();
        File f = new File("people.csv"); //i tried to move this out of here and pass it to method but it broke it.
        Scanner fileScanner = new Scanner(f);
        fileScanner.nextLine(); //i just need to move the pointer past the first line.

        //while there is another line in the file (after skipping the first line)
        while (fileScanner.hasNext()) {
            //should be: [0]id [1]firstNam [2]lastName [3]email [4]country [5]ip
            String[] personSplit = fileScanner.nextLine().split(",");
            String key = personSplit[4]; //this is where the country is in our file. Just makes it easier to work with for me.

            //this is a bit long, but it's taking all of the fields in the array of a single ling from the file and creating a person object
            Person tempPerson = new Person(personSplit[1], personSplit[2], personSplit[3], personSplit[4], personSplit[5], Integer.parseInt(personSplit[0]));

            if (!personMap.containsKey(key)) {
                personMap.put(key, new ArrayList<>());
            }
            personMap.get(key).add(tempPerson);
        }

        return personMap;
    }

    public static void writeToJson(HashMap<String, ArrayList<Person>> personMap) throws IOException {
        File f = new File("peopleJson.json");
        JsonSerializer serializer = new JsonSerializer();
        FileWriter fw = new FileWriter(f);

        String toWrite = serializer.include("*").serialize(personMap);

        fw.write(toWrite);
        fw.close();
    }
}
