package josephusdanielJmartFA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Vector;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

@SuppressWarnings("serial")
public class JsonTable<T> extends Vector<T> {
	public final String filepath;
	private static final Gson gson = new Gson();
	
	@SuppressWarnings("unchecked")
	public JsonTable(Class<T> clasz, String filepath) throws IOException {
		this.filepath = filepath;
		
		try {
		Class<T[]> genericArray = (Class<T[]>) Array.newInstance(clasz, 0).getClass();
		T[] tArray = (T[]) JsonTable.readJson(genericArray, filepath);
		
		if (tArray != null) {
			Collections.addAll(this, tArray);
		}
		
		} catch (FileNotFoundException e) {
			File file = new File(filepath);
			File parentFile = file.getParentFile();
			
			// buat directory file kalau ada specified directory di filepath
			parentFile.mkdirs();
			file.createNewFile();
		}
	}
	
	public static <T> T readJson(Class<T> clasz, String filepath) throws FileNotFoundException {
		JsonReader jr = new JsonReader(new FileReader(filepath));
		return gson.fromJson(jr, clasz);
		
	}
	
	public static void writeJson(Object object, String filepath) throws IOException {
		FileWriter writer = new FileWriter(filepath);
		writer.write(gson.toJson(object));
		writer.close();
	}
	
	public void writeJson() throws IOException {
		writeJson(this, this.filepath);
	}
}
