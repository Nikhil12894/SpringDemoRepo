package com.howtodoinjava.nk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.howtodoinjava.demo.model.BaseEntity;
import com.howtodoinjava.demo.repository.BaseRepository;
import com.howtodoinjava.demo.service.BaseEntityService;

@SuppressWarnings("hiding")
public class EntityModelSetup<T extends BaseEntity> {
	public <T> EntityModelSetup(Class<T> obj){
		System.out.println(obj);
	}
	public String id;
	
	public BaseRepository<T> repository;
	
	public Class<T> service;
	
	public Class<T> entityClass;
	
	public BaseEntityService<T> svcs;
	
	public static void main(String[] args) {
			ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Beyond Compare 3\\BCompare.exe",
			        "C:\\Users\\Nalin\\git\\SpringDemoRepo\\spring-boot-hibernate-crud-demo\\Test1.json", "C:\\Users\\Nalin\\git\\SpringDemoRepo\\spring-boot-hibernate-crud-demo\\test2.json","/fv=Text Compare", "/fileviewer=Text Compare");
	
		    Process ps;
		    try {
		        ps = processBuilder.start();
//		        processBuilder.startPipeline()
		        OutputStream os = ps.getOutputStream();
		        os.close();
	
		        InputStream inputStream = ps.getInputStream();
		        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
		        	System.out.println(line);
		        }
		        ps.waitFor();
		        System.out.println("Exit value :" + ps.exitValue());
		    } catch (IOException e) {
		        e.printStackTrace();
		    } catch (InterruptedException e1) {
		        e1.printStackTrace();
		    }
		}
	
	
}
