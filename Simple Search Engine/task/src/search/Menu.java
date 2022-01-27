package search;

import search.Searchers.LinearSearch;
import search.Searchers.NoneSearch;
import search.Searchers.SequentialSearch;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Menu {
    String[] initParams;
    SearchEngine searchEngine;
    Scanner sc;

    public Menu(String[] initParams) {
        this.initParams = initParams;
        searchEngine = new SearchEngine();
        sc = new Scanner(System.in);
    }

    private static HashMap<String, String> readParams(String[] args) {
        HashMap<String, String> params = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            params.put(args[i], args[i + 1]);
        }
        return params;
    }

    void run() {
        HashMap<String, String> params = readParams(initParams);

        try {
            DataBase dataBase = new DataBase(params.get("--data"));

            int optionMenu = -1;

            while (optionMenu != 0) {
                printMenu();

                String inpOption = sc.next();
                optionMenu = convertToInt(inpOption);

                System.out.println();

                if (!(optionMenu >= 0 && optionMenu < 3)) {
                    System.out.println("Incorrect option! Try again.\n");
                } else {
                    menuOptions(optionMenu, dataBase);
                    System.out.println();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void selectStrategy(String strategy) {
        switch (strategy) {
            case "NONE":
                searchEngine.setMethod(new NoneSearch());
                break;

            case "ALL":
                searchEngine.setMethod(new LinearSearch());
                break;

            case "ANY":
            default:
                searchEngine.setMethod(new SequentialSearch());
        }
    }

    void printMenu() {
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
    }

    int convertToInt(String strToInt) {
        try {
            return Integer.parseInt(strToInt);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return -1;
    }

    void menuOptions(int optionMenu, DataBase dataBase) {

        switch (optionMenu) {
            case 0:
                System.out.println("Bye!");
                break;
            case 1:
                System.out.println("Select a matching strategy: ALL, ANY, NONE");
                selectStrategy(sc.next());
                sc.skip("\\s");
                System.out.println();

                System.out.println("Enter a name or email to search all suitable people.");
                String searchWord = sc.nextLine();

                List<String> res = searchEngine.search(dataBase, searchWord);
                if (!(res.size() == 1 && res.get(0).equalsIgnoreCase("No matching people found."))) {
                    System.out.printf("%d persons found:\n", res.size());
                }
                res.forEach(System.out::println);
                break;

            case 2:
                dataBase.getData().forEach(System.out::println);
                break;

            default:
                System.out.println("Error");
        }
    }
}
