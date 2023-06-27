package demo.models;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Database<T> {
        private Gson gson;
        private Type type;

        public Database(Class<T> typeClass) {
                gson = new GsonBuilder().setPrettyPrinting().create();
                type = TypeToken.getParameterized(List.class, typeClass).getType();
        }

        public void writeJsonToFile(List<T> items, String filePath) {
                try {
                        File file = new File(filePath);
                        file.createNewFile();

                        Writer writer = new FileWriter(filePath);
                        gson.toJson(items, writer);
                        writer.close();

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public void writeJsonToFile(T item, String filePath) {
                List<T> existingItems = readJsonFromFile(filePath);
                if (existingItems == null) {
                        existingItems = new ArrayList<>();
                }

                existingItems.add(item);
                writeJsonToFile(existingItems, filePath);
        }

        public List<T> readJsonFromFile(String filePath) {
                List<T> items = new ArrayList<>();

                File file = new File(filePath);
                if (file.exists()) {
                        try (Reader reader = new FileReader(filePath)) {
                                items = gson.fromJson(reader, type);

                        } catch (IOException e) { e.printStackTrace(); }
                }

                return items;
        }

        public void updateJsonItem(T newItem, int index, String filePath) {
                List<T> items = readJsonFromFile(filePath);
        
                if (index >= 0 && index < items.size()) {
                        items.set(index, newItem);
                        writeJsonToFile(items, filePath);
                }
        }
}
