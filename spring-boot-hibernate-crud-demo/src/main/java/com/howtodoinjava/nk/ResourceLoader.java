package com.howtodoinjava.nk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ResourceLoader {
	
	private ResourceLoader() {
	}
	private static ResourceLoader resource;
	public static synchronized ResourceLoader getResourceLoader() {
		if(resource==null) {
			resource = new ResourceLoader();
		}
		return resource;
	}

	public String getResource(String basePath) {
		ClassLoader classLoader = this.getClass().getClassLoader();
		URL url = classLoader.getResource(basePath);
		String absolutePath=null;
		File file;
		try {
			file = Paths.get(url.toURI()).toFile();
			absolutePath = file.getAbsolutePath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return absolutePath;
		
	}

	public String readfile(String fileName) {
		StringBuilder output = new StringBuilder();
		try (java.io.FileReader reader = new FileReader(fileName); BufferedReader stream = new BufferedReader(reader);) {
			String line = null;
			while ((line = stream.readLine()) != null) {
				output.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
		return output.toString();
	}

	public String writefile(String fileName, String data) {
		try (FileOutputStream out = new FileOutputStream(fileName); BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));) {
			bw.write(data);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Data Saved";
	}
	
	
	public Map<String, Object> redResourceAsJson(String basePath) {
		String jsonStr=readfile(getResource(basePath));
		Gson gson = new Gson();
		Type mapType = new TypeToken<Map<String, Object>>() {}.getType();
		return gson.fromJson(jsonStr, mapType);
	}
}
