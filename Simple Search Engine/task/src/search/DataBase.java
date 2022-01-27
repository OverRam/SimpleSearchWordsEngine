package search;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

public class DataBase {
    private final HashMap<String, LinkedList<Integer>> invertedIndex = new HashMap<>();
    private ArrayList<String> data;

    public DataBase(String patchFile) {
        saveDataFromFile(patchFile);
    }

    public Map<String, LinkedList<Integer>> getInvertedIndex() {
        return invertedIndex;
    }

    public ArrayList<String> getData() {
        return data;
    }

    private void saveDataFromFile(String patchFile) {
        data = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(patchFile))) {
            if (new File(patchFile).exists()) {
                stream.forEach(e -> {
                    data.add(e);

                    //creating inverted index
                    String[] splitLine = e.split(" ");
                    for (String s : splitLine) {
                        if (invertedIndex.containsKey(s.toLowerCase())) {
                            invertedIndex.get(s.toLowerCase()).add(data.indexOf(e));
                        } else {
                            LinkedList<Integer> newIndex = new LinkedList<>();
                            newIndex.add(data.indexOf(e));
                            invertedIndex.put(s.toLowerCase(), newIndex);
                        }
                    }
                });
            } else {
                System.out.println("File not found!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
