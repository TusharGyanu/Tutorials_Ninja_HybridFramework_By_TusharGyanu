package com.tutorialsNinja.Qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class base {

	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	
	//This method is to load properties file 
	public  base() { //constructor
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\tutorialsNinja\\qa\\config\\Config.properties");
		
		dataprop = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\tutorialsNinja\\qa\\testdata\\testdata.properties");
		
		try {
		FileInputStream dataFis = new FileInputStream(dataPropFile);
		dataprop.load(dataFis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		try {
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver InitilizeBrowser_andOpenApplication(String browserName) {

		
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox")){
			driver = new FirefoxDriver();
		}

		else if(browserName.equals("edge")){
			driver = new EdgeDriver();
		}
		
		else if(browserName.equals("safari")){
			driver = new SafariDriver();
		}

		driver.manage().window().maximize();
		
		driver.get(prop.getProperty("url"));
		return driver;
	}
}
