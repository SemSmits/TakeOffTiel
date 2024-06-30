package com.example.util;

import com.example.webservices.TakeOffTiel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataUtils {

    private static final String saveFilePath = "takeOffTielSaveV10.obj";

    public static void saveUserData(TakeOffTiel takeOffTiel) {
        try {

            Path path = Path.of(saveFilePath);
            OutputStream os = Files.newOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(takeOffTiel);
            oos.close();
            os.close();

            System.out.println("Data saved");

        } catch (IOException e) {
            System.out.println(String.format("Exception while saving data: %s", e.getMessage()));
        }

    }

    public static TakeOffTiel getUserData() {
        File file = new File(saveFilePath);
        if (!file.exists()) {
            return new TakeOffTiel();
        }

        try {
            String saveFilePath = "takeOffTielSaveV10.obj";
            Path path = Path.of(saveFilePath);

            InputStream is = Files.newInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(is);

            TakeOffTiel takeOffTiel = (TakeOffTiel) ois.readObject();

            System.out.println("Data retrieved");

            return takeOffTiel;
        } catch (IOException e) {

            for (Object t : e.getStackTrace()) {

            }
            System.out.println(String.format("Exception while retrieving data: %s", e.getMessage()));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
