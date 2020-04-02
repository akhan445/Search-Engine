package search;
import java.util.*;

/** Strategy Pattern implementing various search strategies selected by user **/
public interface SearchingMethod {
    void search(Scanner sc, String[] personData, Map<String, List<Integer>> invertedIndex);
}

/** Search results in all terms that match all words entered in search query **/
class AllSearchingMethod implements SearchingMethod {

    @Override
    public void search(Scanner sc, String[] personData, Map<String, List<Integer>> invertedIndex) {

        System.out.println("\nEnter the name or email to search all suitable people.");

        String searchTerm = sc.nextLine().toLowerCase();

        if (invertedIndex.containsKey(searchTerm)) {
            System.out.println(invertedIndex.get(searchTerm).size() + " persons found:");
            for (Integer index: invertedIndex.get(searchTerm)) {
                System.out.println(personData[index]);
            }
        } else {
            System.out.println("No matching people found.");
        }
    }

}

/** Search results in all terms that match at least one of the words entered in search query **/
class AnySearchingMethod implements SearchingMethod {

    @Override
    public void search(Scanner sc, String[] personData, Map<String, List<Integer>> invertedIndex) {

        System.out.println("\nEnter the name or email to search all suitable people.");

        String[] searchTerms = sc.nextLine().toLowerCase().split(" ");

        Set<String> termsFound = new HashSet<>(); //set will only add unique elements any duplicate won't be added

        for (String term: searchTerms) {
            if (invertedIndex.containsKey(term.toLowerCase())) {
                for (Integer index: invertedIndex.get(term.toLowerCase())) {
                    termsFound.add(personData[index]); // add all that you find to set
                }
            }
        }

        if (termsFound.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            //print everything found included in set
            System.out.println(termsFound.size() + " persons found");
            for (String person: termsFound) {
                System.out.println(person);
            }
        }
    }

}

/** Search results in all terms that exclude all instances of words entered in search query **/
class NoneSearchingMethod implements SearchingMethod {

    @Override
    public void search(Scanner sc, String[] personData, Map<String, List<Integer>> invertedIndex) {

        System.out.println("\nEnter the name or email to search all suitable people.");

        String[] searchTerms = sc.nextLine().toLowerCase().split(" ");

        // create a copy of the map
        Map<String, List<Integer>> invertedIndexCopy = new HashMap<String, List<Integer>>();
        invertedIndexCopy.putAll(invertedIndex);

        // create a list of index values
        List<Integer> indexes = new ArrayList<>();

        for (String term: searchTerms) {
            for (String key: invertedIndex.keySet()) {
                // add indexes of all terms to be excluded to a list
                if (key.toLowerCase().equals(term)) {
                    indexes.addAll(invertedIndex.get(key));
                }
            }
        }

        // remove all occurences of search term indexes from map
        for (int index : indexes) {
            invertedIndexCopy.values().removeIf(value -> value.contains(index));

        }

        // create a set to include all the terms to be printed from search query
        Set<String> termsFound = new HashSet<>(); //set will only add unique elements any duplicate won't be added

        for (String term: invertedIndexCopy.keySet()) {
            for (Integer index: invertedIndexCopy.get(term)) {
                termsFound.add(personData[index]); // add all that you find to set
            }
        }

        if (termsFound.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            //print everything found included in set
            System.out.println(termsFound.size() + " persons found");
            for (String person: termsFound) {
                System.out.println(person);
            }
        }
    }

}