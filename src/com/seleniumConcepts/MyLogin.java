package com.seleniumConcepts;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class MyLogin {
	
	

	public WebDriver driver;
	public String Browser="chrome";
	
	@Test
	//lauching through different browsers
	public void workwithbrow() throws Throwable
	{
	    if(Browser.equalsIgnoreCase("chrome"))
	    {
	    	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver=new ChromeDriver();
	    }
	    else if(Browser.equalsIgnoreCase("ie"))
	    {
	    	System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver=new InternetExplorerDriver();	
	    }
	    else if(Browser.equalsIgnoreCase("mozilla"))
	    {
	     	System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver=new FirefoxDriver();	
	    }
	         
		
		driver.get("https://www.flipkart.com");  //open flipkart main page
		driver.manage().window().maximize();  //Maximize browser
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//enter username
		WebElement email=driver.findElement(By.xpath("//div[@class='_1XBjg- row']//form//input[@type='text']"));
		email.sendKeys("abc@gmail.com");		
		//enter password
		WebElement password=driver.findElement(By.xpath("//div[@class='_1XBjg- row']//form//input[@type='password']"));
		password.sendKeys("abcd");
		//submit login
		driver.findElement(By.xpath("//div[@class='_1XBjg- row']//form//button[@type='submit']")).click();
	
		Thread.sleep(5000);
		//searching for camera in search box
	    driver.findElement(By.name("q")).sendKeys("camera" + Keys.ENTER);
		 Thread.sleep(5000);
		    //selecting the random camera from list 
		      //selecting camera
		  driver.findElement(By.xpath("  (//div[@class='_3wU53n'][contains(text(),'Canon PowerShot SX430 IS')]")).click();
			  
		  Set<String> allids = driver.getWindowHandles();
			Iterator<String> it = allids.iterator();
			String mid = it.next();
			String tabid = it.next();
			
			driver.switchTo().window(tabid);   //Switching to tab 
			
			
			String expectedprice = driver.findElement(By.xpath("//div[@class='_1vC4OE _3qQ9m1']")).getText();
			 String expecteditem = driver.findElement(By.xpath(" //span[@class='_35KyD6']")).getText();
			 
			//String expecteddescription = driver.findElement(By.xpath("//span[@class='_35KyD6']")).getText();
			
			 
			//add to cart 
			// driver.findElement(By.xpath("//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")).click();
			driver.findElement(By.xpath("//li[@class='col col-6-12']/button")).click();
			  Thread.sleep(5000);
			
                      
       	String actualprice = driver.findElement(By.xpath("//span[@class='pMSy0p XU9vZa']")).getText();
        String actualname = driver.findElement(By.xpath("//a[@class='_325-ji _3ROAwx']")).getText();
        String actualdescription = driver.findElement(By.xpath("//div[@class='v7-Wbf']")).getText();
        
       
        String actualitem = actualname + " (" + actualdescription + ")";
        
        System.out.println("Actual Price" + actualprice);
        System.out.println("Actual Item " + actualitem);
        //checking item in view cart through assertions 
           
           Assert.assertEquals(actualprice,expectedprice);  
           Assert.assertEquals(actualitem,expecteditem);  
           
           //place order in flipkart
           
           driver.findElement(By.xpath("//span[contains(text(),'Place Order')]")).click();
           
           //for logout
           
           driver.findElement(By.xpath("//div[@class='_2aUbKa']"));
           
           driver.findElement(By.xpath("//div[contains(text(),'Logout')]")).click();
       
               
		   }

};