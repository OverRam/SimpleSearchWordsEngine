package search.Searchers;

import search.DataBase;

import java.util.LinkedList;

public class SequentialSearch implements SearchMethod {
    @Override
    public LinkedList<String> searchDataBase(DataBase dataBase, String searchWords) {
        LinkedList<String> res = new LinkedList<>();

        String[] searchWord = searchWords.split(" ");
        for (String s : searchWord) {
            if (dataBase.getInvertedIndex().containsKey(s.toLowerCase())) {
                dataBase.getInvertedIndex()
                        .get(s.toLowerCase())
                        .forEach(e -> res.add(dataBase.getData().get(e)));
            }
        }


        if (res.size() == 0) {
            res.add(noFindText);
        }
        return res;
    }
}
