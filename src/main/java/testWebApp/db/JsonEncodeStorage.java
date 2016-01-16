package testWebApp.db;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

public class JsonEncodeStorage {
	public void writeToJsonFile(JSONObject joUser) {
		JSONObject obj = new JSONObject(joUser);
		System.out.print(obj);
		System.out.print(joUser);
		
	}
}
