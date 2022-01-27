package search.Searchers;

import search.DataBase;

import java.util.LinkedList;

public interface SearchMethod {
    String noFindText = "No matching people found.";

    LinkedList<String> searchDataBase(DataBase dataBase, String searchWord);
}
