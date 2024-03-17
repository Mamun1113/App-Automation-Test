package sqa.AppAutomationTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;

public class AppTest
{

	public AndroidDriver driver;
	String resourceId, className, xPath, toFind;

	@BeforeTest
	public void setDevice( ) throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("appium:deviceName", "Pixel_emu");
		desiredCapabilities.setCapability("platformName", "android");
		desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
		desiredCapabilities.setCapability("appium:udid", "emulator-5554");
		desiredCapabilities.setCapability("appPackage", "com.nopstation.nopcommerce.nopstationcart");
		desiredCapabilities.setCapability("appActivity", "com.bs.ecommerce.main.SplashScreenActivity");

		URL remoteUrl = new URL("http://127.0.0.1:4723/");

		driver = new AndroidDriver(remoteUrl, desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
	}

	// Scenario: 01 Customer add products in his shopping cart
	@Test(priority = 1)
	public void app_test_1() throws InterruptedException, IOException {
		Thread.sleep(5000);

		// Mike on home page after opening nopCart mobile app
		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnAccept")).click();
		Thread.sleep(1000);

		// Mike click "electronics" from "OUR CATEGORIES" list from home page
		resourceId = "com.nopstation.nopcommerce.nopstationcart:id/tvProductName";
		toFind = "Electronics";
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true))"
						+ ".setAsHorizontalList().scrollIntoView(new UiSelector().resourceId(\"" + resourceId + "\").text(\"" + toFind + "\"))"
				));

		xPath = "//android.widget.TextView[@resource-id=\"com.nopstation.nopcommerce.nopstationcart:id/tvProductName\" and @text=\"Electronics\"]";
		driver.findElement(By.xpath(xPath)).click();
		Thread.sleep(1000);

		// Mike click to "Mattress Bedroom" product details page
		toFind = "Mattress Bedroom";
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"" + toFind + "\"))"
				)).click();       
		Thread.sleep(1000);

		// Mike click plus button to increase Qty by "2"
		toFind = "+";
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"" + toFind + "\"))"
				)).click();
		Thread.sleep(1000);

		// Mike click add to cart button to add the product in his cart
		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnAddToCart")).click();
		Thread.sleep(1000);
	}

	// Scenario: 02 Customer successfully place order as a guest user
	@Test(priority = 2)
	public void app_test_2() throws InterruptedException, IOException {	
		Thread.sleep(1000);

		// Mike go to shopping cart by clicking top cart icon
		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/counterIcon")).click();
		Thread.sleep(1000);

		// Mike click checkout button from shopping cart page
		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnCheckOut")).click();
		Thread.sleep(1000);

		// Mike select checkout as guest from shopping cart page
		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnGuestCheckout")).click();
		Thread.sleep(1000);

		// Mike input all the details in checkout billing details page and click continue
		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etFirstName")).sendKeys(ReadCellData(1, 0));
		Thread.sleep(1000);

		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etLastName")).sendKeys(ReadCellData(1, 1));
		Thread.sleep(1000);

		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etEmail")).sendKeys(ReadCellData(1, 2));
		Thread.sleep(1000);

		toFind = ReadCellData(1, 3);
		className = "android.widget.Spinner";
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).className(\"" + className + "\"))"
						+ ".scrollIntoView(new UiSelector().text(\"" + toFind + "\"))"
				)).click();		
		Thread.sleep(1000);

		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etCompanyName")).sendKeys(ReadCellData(1, 4));
		Thread.sleep(1000);

		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etCity")).sendKeys(ReadCellData(1, 5));
		Thread.sleep(1000);

		toFind = "CONTINUE";
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"" + toFind + "\"))"
				));

		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etStreetAddress")).sendKeys(ReadCellData(1, 6));
		Thread.sleep(1000);

		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etStreetAddress2")).sendKeys(ReadCellData(1, 7));
		Thread.sleep(1000);

		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etZipCode")).sendKeys(ReadCellData(1, 8));
		Thread.sleep(1000);

		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etPhone")).sendKeys(ReadCellData(1, 9));
		Thread.sleep(1000);

		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etFax")).sendKeys(ReadCellData(1, 10));
		Thread.sleep(1000);

		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnContinue")).click();
		Thread.sleep(1000);

		// Mike select "Next Day Air" as shipping method and click continue
		resourceId = "com.nopstation.nopcommerce.nopstationcart:id/tvShippingMethodDescription";
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiSelector().resourceId(\"" + resourceId + "\").instance(2)"
				)).click();	
		Thread.sleep(1000);

		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).flingToEnd(10)"
				));

		// Mike click next button on payment information page
		resourceId = "com.nopstation.nopcommerce.nopstationcart:id/btnContinue";
		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnContinue")).click();
		Thread.sleep(1000);
		
		// Mike select "Check/Money Order" as payment method and click continue
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).flingToEnd(10)"
				));

		toFind = "Check / Money Order";
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"" + toFind + "\"))"
				)).click();
		Thread.sleep(1000);

		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnContinue")).click();
		Thread.sleep(3000);

		// Mike click next button on payment information page
		className = "android.widget.Button";
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiSelector().className(\"" + className + "\")"
				)).click();	
		Thread.sleep(3000);

		// Mike click confirm button to place the order
		driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnContinue")).click();
		Thread.sleep(1000);      

		// Verify order place successfully with popup message "Your order has been successfully processed!"
		String msgVerify = driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/md_text_message")).getText();
		Thread.sleep(1000);

		if(msgVerify.contains("Your order has been successfully processed!"))
			System.out.println("Verified successfully");

		Thread.sleep(2000);
	}

	@AfterTest
	public void closeApp() {
		driver.quit();
	}

	public String ReadCellData(int vRow, int vColumn)  
	{  
		String value=null; 
		Workbook wb=null;
		try  
		{  
			// Excel file location
			FileInputStream fis=new FileInputStream("D:\\eclipse-workspace\\ExcelValues.xlsx");  
			wb=new XSSFWorkbook(fis);  
		}  
		catch(FileNotFoundException e)  
		{  
			e.printStackTrace();  
		}  
		catch(IOException e1)  
		{  
			e1.printStackTrace();  
		}  
		Sheet sheet=wb.getSheetAt(0);   
		Row row=sheet.getRow(vRow); 
		Cell cell=row.getCell(vColumn); 
		value=cell.getStringCellValue(); 
		return value;
	}
}

