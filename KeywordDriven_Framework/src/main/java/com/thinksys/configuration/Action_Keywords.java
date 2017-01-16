package com.thinksys.configuration;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class Action_Keywords {
	
	public static WebDriver driver;
	
	public  void performAction(String ActionKeyword, String locatorType, String locatorValue, String data) throws Exception
	{
		switch(ActionKeyword)
		{
		
		case "GetPage":
		{
			GetPage(data);
		}
		break;
		
		case "EnterText":
		{
			EnterText(locatorType,locatorValue,data);
		}
		break;
		
		case "Click":
		{
			click(locatorValue, locatorType);
		}
		break;
		
		case "Launch":
		{
			Launch(data);
		}
		break;
		
		case "selectItemByValue":
		{
			selectItemByValue(locatorValue, locatorType, data);
		}
		break;
		
		case "selectItemByIndex":
		{
			
			selectItemByIndex(locatorValue, locatorType, data);
		}
		break;
		
		case "selectItemByVisibleText":
		{
			selectItemByVisibleText(locatorValue, locatorType, data);
		}
		break;
		
		case "closebrowser":
		{
			closebrowser();
		}
		break;
		
		case "closeallbrowsers":
		{
			closeallbrowsers();
		}
		break;
		
		case "clearBrowserCookies":
		{
			clearBrowserCookies();
		}
		break;
		
		case "refreshbrowser":
		{
			refreshbrowser();
		}
		break;
		
		case "goforward":
		{
			goforward();
		}
		break;
		
		case "goback":
		{
			goback();
		}
		break;
		
		case "Verifypageproperty":
		{
			Verifypageproperty(data);
		}
		break;
		
		case "selectmultipleitems":
		{
			selectmultipleitems(locatorValue, locatorType, data);
		}
		break;
		
		case "verifytextinpagesource":
		{
			verifytextinpagesource(data);
		}
		break;
		
		case "verifytextnotinpagesource":
		{
			verifytextnotinpagesource(data);
		}
		break;
		
		case "verifytextcontained":
		{
			verifytextcontained(locatorValue, locatorType, data);
		}
		break;
		
		case "verifytextnotcontained":
		{
			verifytextnotcontained(locatorValue, locatorType, data);
		}
		break;
		
		case "verifytextonpage":
		{
			verifytextonpage(data);
		}
		break;
		
		case "verifytextnotonpage":
		{
			verifytextnotonpage(data);
		}
		break;
		
		case "verifylistitempresent":
		{
			verifylistitempresent(locatorValue, locatorType, data);
		}
		break;
		
		case "verifylistitemnotpresent":
		{
			verifylistitemnotpresent(locatorValue, locatorType, data);
		}
		break;
		
		case "verifyobjectdisplayed":
		{
			verifyobjectdisplayed(locatorValue, locatorType);
		}
		break;
		
	/*	case "verifyobjectproperty":
		{
			verifyobjectproperty(locatorValue, locatorType, data);
		}
		break;*/
		
		}
	} 
	
	/*public void verifyobjectproperty(String locatorValue, String locatorType, String data)
	{
		By valueOfLocator=locator(locatorType, locatorValue);
		WebElement w= driver.findElement(valueOfLocator);
		
		System.out.println(w.getText());
	}*/
	
	public void verifyobjectdisplayed(String locatorValue, String locatorType) 
	{
	
			try {
				By valueOfLocator=locator(locatorType, locatorValue);
				WebElement w= driver.findElement(valueOfLocator);
				
				  if(w==null || !w.isDisplayed())
				  {
					  System.out.println("Object is not present in the page.");
				  }
				  else
				  {
					  System.out.println("Object is present.");
				  }
				}
			catch(Exception e)
			{
				//If(e.getMessage().)
				System.out.println("Object is not found.");
			}
	}
	
	
	
	public void verifylistitempresent(String locatorValue, String locatorType, String data)
	{
		By valueOfLocator=locator(locatorType, locatorValue);		
		List<WebElement> w= driver.findElements(valueOfLocator);	
		
	//	System.out.println(w.size());
		boolean a=false;
		for(int i=0; i<w.size(); i++)
		{
			String s= w.get(i).getAttribute("value");
			if(s.equalsIgnoreCase(data))
			{
				System.out.println("Specified list item is present in the list.");
				a=true;
			}
		}
		if(a==false)
		{
			System.out.println("Specified list item is not present in the list.");
		}
	}
	
	
	public void verifylistitemnotpresent(String locatorValue, String locatorType, String data)
	{
		By valueOfLocator=locator(locatorType, locatorValue);		
		List<WebElement> w= driver.findElements(valueOfLocator);	
	//	System.out.println(w.size());
		boolean a=false;
		for(int i=0; i<w.size(); i++)
		{
			String s= w.get(i).getAttribute("value");
			if(s.equalsIgnoreCase(data))
			{
				System.out.println("Specified list item is present in the list.");
				a=true;
			}
		}
		if(a==false)
		{
			System.out.println("Specified list item is not present in the list.");
		}
	}
	
	
	public void verifytextnotonpage(String data)
	{
		WebElement w = driver.findElement(By.tagName("body"));
		boolean e = w.getText().toLowerCase().contains(data.toLowerCase());
		if(e!=true)
		{
			System.out.println("Text is not present on the page.");
		}
		else
		{
			System.out.println("Text is present on the page.");
		}
	}
	
	public void verifytextonpage(String data)
	{
		WebElement e = driver.findElement(By.tagName("body"));
		if(e.getText().toLowerCase().contains(data.toLowerCase()))
		{
			System.out.println("Text is present on the page.");
		}
		else
		{
			System.out.println("Text is not present on the page.");
		}
	}
	
	public void verifytextcontained(String locatorValue, String locatorType, String data)
	{
		By valueOfLocator=locator(locatorType, locatorValue);		
		WebElement w= driver.findElement(valueOfLocator);	
		WebElement w1=driver.findElement(By.tagName("body"));
	//	boolean e=w.getText().contains(data);
	//	System.out.println(w.getText());
		
		if(w.getText().toLowerCase().contains(data.toLowerCase()))
		{
			System.out.println("Text is present in the specified test object.");
		}
		else if(w1.getText().toLowerCase().contains(data.toLowerCase()))
		{
			System.out.println("Text is present in the page.");
		}
		else 
		{
			System.out.println("Text is not present.");
		}
	}
	
	public void verifytextnotcontained(String locatorValue, String locatorType, String data)
	{
		By valueOfLocator=locator(locatorType, locatorValue);		
		WebElement w= driver.findElement(valueOfLocator);	
		boolean e=w.getText().toLowerCase().contains(data.toLowerCase());
		if(e!=true)
		{
			System.out.println("Text is not present in the specified test object.");
		}
		else
		{
			System.out.println("Text is present in the test object.");
		}
	}
	
	public void verifytextinpagesource(String data)
	{
		if(driver.getPageSource().contains(data))
		{
			System.out.println("Text is present in page source.");
		}
		else
		{
			System.out.println("Specified text is not present");
		}
	}
	
	public void verifytextnotinpagesource(String data)
	{
		boolean e=driver.getPageSource().contains(data);
		if(e!=true)
		{
			System.out.println("Specified text is not present in the page source.");
		}
		else
		{
			System.out.println("Text is present in the page source.");
		}
	}
	
	public void selectmultipleitems(String locatorValue, String locatorType, String data)
	{
		By valueOfLocator=locator(locatorType, locatorValue);		
		WebElement w= driver.findElement(valueOfLocator);	
		Select select=new Select(w);
		
		String data_array[]= data.split(Pattern.quote("|"));
		
		for (String temp: data_array){   
	          select.selectByVisibleText(temp);
	       }	
	}	
	
	public void Verifypageproperty(String data)
	{
		if(data.equals(driver.getTitle()) || data.equals(driver.getCurrentUrl()))
		{
			System.out.println("Specified page property is coming correctly as: " +data);
		}
		else
		{
			System.out.println("Specified page property is incorrect.");
		}
			
	}
	
	public void Launch(String browserName)
	{
		if(browserName.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			System.out.println(browserName+" Launched Successfully");
		}
		
		else if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver",".\\Drivers\\chromedriver.exe");
			driver=new ChromeDriver();
			System.out.println(browserName+" Launched Successfully");
		}
		
		else if(browserName.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.IE.driver",".\\Drivers\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			System.out.println(browserName+" Launched Successfully");
		}
		
	}
	
	public void GetPage(String url)
	{
		driver.get(url);
		System.out.println("URL Launched Successfully");
	}
	
	public static By locator(String locatorTpye, String value) 
	{
		By by=null;
		try {
			switch (locatorTpye) {
			case "id":
				by = By.id(value);
				break;
			case "name":
				by = By.name(value);
				break;
			case "xpath":
				by = By.xpath(value);
				break;
			case "css":
				by = By.cssSelector(value);
				break;
			case "linkText":
				by = By.linkText(value);
				break;
			case "partialLinkText":
				by = By.partialLinkText(value);
				break;
			default:
				by = null;
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element Not Found");
		}
		return by;
	}
	
	public void EnterText(String locatorType,String locatorValue,String data)
	{
		try
		{
			By valueOfLocator=locator(locatorType, locatorValue);		
			driver.findElement(valueOfLocator).sendKeys(data);
		}
		catch(Exception e)
		{
			System.out.println("Enter text is not executed.");
		}
	}
	
	
	public void click(String locatorValue,String locatortype)
	{
		driver.findElement(By.xpath(locatorValue)).click();
	}
	
	public void selectItemByValue(String locatorValue, String locatorType, String data)
	{
		By valueOfLocator=locator(locatorType, locatorValue);		
		WebElement w= driver.findElement(valueOfLocator);	
		Select select=new Select(w);
		
		select.selectByValue(data);
	}
	
	public void selectItemByIndex(String locatorValue, String locatorType, String data)
	{
		int i=Integer.parseInt(data);
		By valueOfLocator=locator(locatorType, locatorValue);
		WebElement w= driver.findElement(valueOfLocator);	
		Select select=new Select(w);
		select.selectByIndex(i);
	}
	
	public void selectItemByVisibleText(String locatorValue, String locatorType, String data)
	{
		By valueOfLocator=locator(locatorType, locatorValue);
		WebElement webelement= driver.findElement(valueOfLocator);
		Select select=new Select(webelement);
		select.selectByVisibleText(data);
		
	}
	
	public void closebrowser()
	{
		driver.close();
		System.out.println("Browser Closed");
	}
	
	
	public void closeallbrowsers()
	{
		driver.quit();
		System.out.println("Browsers Closed");
	}
	
	public void clearBrowserCookies()
	{
		driver.manage().deleteAllCookies();
		System.out.println("Browser cache cleared");
	}
	
	public void refreshbrowser()
	{
		driver.navigate().refresh();
		System.out.println("Browser has been refreshed");
	}
	
	public void goforward()
	{
		driver.navigate().forward();
		System.out.println("Navigated to the next page");
	}
	
	public void goback()
	{
		driver.navigate().back();
		System.out.println("Navigated to the back page");
	}
}
