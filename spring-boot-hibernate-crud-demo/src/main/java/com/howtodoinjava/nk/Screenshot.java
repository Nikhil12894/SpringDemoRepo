package com.howtodoinjava.nk;
// Java Program to Capture full 
// Image of Screen 
import java.awt.AWTException; 
import java.awt.Rectangle; 
import java.awt.Toolkit; 
import java.awt.Robot; 
import java.awt.image.BufferedImage; 
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.File; 
import javax.imageio.ImageIO; 

public class Screenshot { 
	public static final long serialVersionUID = 1L; 
	public static void main(String[] args) 
	{ 
		
		
		ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Beyond Compare 3\\BCompare.exe",
		        "C:\\Users\\Nalin\\git\\SpringDemoRepo\\spring-boot-hibernate-crud-demo\\Test1.json", "C:\\Users\\Nalin\\git\\SpringDemoRepo\\spring-boot-hibernate-crud-demo\\test2.json","/fv=Text Compare", "/fileviewer=Text Compare");

	    Process ps = null;
	    try {
	        ps = processBuilder.start();
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    } 
	    try {
//          processBuilder.startPipeline()
            OutputStream os = ps.getOutputStream();
        	
			os.close();
			InputStream inputStream = ps.getInputStream();
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	        try { 
    			Thread.sleep(1000); 
    			Robot r = new Robot(); 

    			// It saves screenshot to desired path 
    			String path = "C:\\Users\\Nalin\\Downloads\\Shot.jpg"; 

    			// Used to get ScreenSize and capture image 
    			Rectangle capture = 
    			new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()); 
    			BufferedImage Image = r.createScreenCapture(capture); 
    			ImageIO.write(Image, "jpg", new File(path)); 
    			System.out.println("Screenshot saved"); 
    		} 
    		catch (AWTException | IOException | InterruptedException ex) { 
    			System.out.println(ex); 
    		} 
	        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
	        	System.out.println(line);
	        }
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
		try {
			ps.waitFor();
	        System.out.println("Exit value :" + ps.exitValue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        
	} 
} 
