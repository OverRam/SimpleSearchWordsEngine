package search.Searchers;

import search.DataBase;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class LinearSearch implements SearchMethod {

    @Override
    public LinkedList<String> searchDataBase(DataBase dataBase, String searchWord) {
        String[] words = searchWord.split(" ");
        LinkedList<String> res = dataBase.getData().stream()
                .filter(e -> Arrays.stream(words).allMatch(e::contains))
                .collect(Collectors.toCollection(LinkedList::new));

        if (res.size() <= 0) {
            res.add(noFindText);
        }
        return res;
    }
}
