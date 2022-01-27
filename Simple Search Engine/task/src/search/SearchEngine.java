package search;

import search.Searchers.SearchMethod;

import java.util.LinkedList;

public class SearchEngine {
    private SearchMethod method;

    public void setMethod(SearchMethod method) {
        this.method = method;
    }

    public LinkedList<String> search(DataBase dataBase, String searchWord) {
        return method.searchDataBase(dataBase, searchWord);
    }
}
