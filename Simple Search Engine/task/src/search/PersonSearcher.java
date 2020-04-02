package search;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/** Context to reference the strategy searching method implemented through SearchingMethod Class **/

class PersonSearcher {

    private SearchingMethod method;

    public void setMethod(SearchingMethod method) {
        this.method = method;
    }

    public void search(Scanner sc, String[] personData, Map<String, List<Integer>> invertedIndex) {
        this.method.search(sc, personData, invertedIndex);
    }

}

