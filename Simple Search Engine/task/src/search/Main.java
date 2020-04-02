package search;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /**********          Upload data to be searched          **********/

        String[] personData = new String[0];
        try {
            personData = readAllData(args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**********            Create Inverted Index            **********/
        Map<String, List<Integer>> invertedIndex = createIndex(personData);

        /**********                 Menu Options                **********/
        boolean inProgram = true;
        while (inProgram) {
            printMenu();
            int option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:
                    PersonSearcher finder = new PersonSearcher();
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    String strategy = sc.nextLine().toUpperCase();

                    switch (strategy) {
                        case "ALL":
                            finder.setMethod(new AllSearchingMethod());
                            finder.search(sc, personData, invertedIndex);
                            break;
                        case "ANY":
                            finder.setMethod(new AnySearchingMethod());
                            finder.search(sc, personData, invertedIndex);
                            break;
                        case "NONE":
                            finder.setMethod(new NoneSearchingMethod());
                            finder.search(sc, personData, invertedIndex);
                            break;
                    }
                    break;
                case 2:
                    printAllPeople(personData);
                    break;
                case 0:
                    inProgram = false;
                    System.out.println("\nBye!");
                    break;
                default:
                    System.out.println("Incorrect option! Try again");
            }
        }

    }

    static Map<String, List<Integer>> createIndex(String[] personData) {

        Map<String, List<Integer>> invertedIndex = new HashMap<String, List<Integer>>();

        for (int i = 0; i < personData.length; i++) {
            String[] currPerson = personData[i].split(" ");

            // add detail of each person to the index
            for (int j = 0; j < currPerson.length; j++) {

                List<Integer> index = invertedIndex.get(currPerson[j].toLowerCase());

                if (index == null) {
                    index = new ArrayList<Integer>();
                    index.add(i);
                    invertedIndex.put(currPerson[j].toLowerCase(), index);
                }  else {
                    invertedIndex.get(currPerson[j].toLowerCase()).add(i);
                }
            }
        }

        return invertedIndex;
    }

    static String[] readAllData(String fileName) throws IOException {
        List<String> result = Files.readAllLines(Paths.get(fileName));
        return result.toArray(new String[result.size()]);

    }

    static void printAllPeople(String[] personData) {
        System.out.println("\n=== List of people ===");
        for (String person: personData) {
            System.out.println(person);
        }
    }

    static void printMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }
}
