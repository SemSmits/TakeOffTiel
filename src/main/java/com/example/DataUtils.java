package com.example;

import com.example.webservices.TakeOffTiel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataUtils {
	public static void saveUserData(TakeOffTiel takeOffTiel){
		try {

			String saveFilePath = "takeofftiel.obj";
			Path path = Path.of(saveFilePath);
			OutputStream os = Files.newOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(os);

			oos.writeObject(takeOffTiel);
			oos.close();
			os.close();


		} catch (IOException e){
			System.out.println(String.format("Exception tijdens het opslaan van de data: %s", e.getMessage()));
		}

	}

	public static TakeOffTiel getUserData(){
		try {
			String saveFilePath = "takeofftiel.obj";
			Path path = Path.of(saveFilePath);

			InputStream is = Files.newInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(is);

			TakeOffTiel takeOffTiel = (TakeOffTiel) ois.readObject();

			return takeOffTiel;
		} catch (IOException e){

			for (Object t : e.getStackTrace()){

			}
			System.out.println(String.format("Exception tijdens het openen van de data: %s", e.getMessage()));

		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
