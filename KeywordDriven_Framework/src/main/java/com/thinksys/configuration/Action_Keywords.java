package com.thinksys.configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class Action_Keywords {
	
	public static WebDriver driver;
	
	public  void performAction(String ActionKeyword, String locatorType, String locatorValue, String data)
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
		/*System.out.println("In method...Val of URL is:- "+url);
		System.out.println("In method...Val of locatorVal is:- "+pageName);*/
		driver.get(url);
		System.out.println("URL Launched Successfully");
	}
	
	public static By locator(String locatorTpye, String value) {
		By by;
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
	
/*	public void EnterText(String locatorType,String locatorValfromExcel,String textValue)
	{
		try
		{
		By valueOfLocator=locatorValue(locatorType, locatorValfromExcel);
		
		System.out.println("Locator Value in Action:- "+ valueOfLocator);
		System.out.println("Entered Text Value is:-"+textValue);
		driver.findElement(valueOfLocator).sendKeys(textValue);
		}
		catch(Exception e)
		{
			System.out.println("Enter text not executed.");
			e.printStackTrace();
		}
	}*/
	
	public void click(String locatorValue,String locatortype)
	{
		/*System.out.println("Locator Val for CLick:-" +locatorVal);
		System.out.println("Locator Val for CLickData:-" +data);*/
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
