package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadingFromFile {

	public static Map<String, String> readXPaths() {

		Map<String, String> xPaths = new HashMap<>();
		File myObj = new File("xPath_fajl.txt");
		Scanner myReader;

		try {
			myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String values[] = data.split(" === ");
				// System.out.println(values[0] + " " + values[1]);
				xPaths.put(values[0], values[1]);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}

		return xPaths;

	}

}
