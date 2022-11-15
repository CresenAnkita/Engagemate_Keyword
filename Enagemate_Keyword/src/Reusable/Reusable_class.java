package Reusable;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Reusable_class {
	WebDriver driver;
	String b;
	static  String c;
	public String d;
	JavascriptExecutor js;
	static List<String> reviewerslist;
	Actions act ;
	WebDriverWait wait;
	
	
	public String[][] fetchDataFromExcel() {

		Workbook wb = null;
		String[][] data = null;
		try {
			String path = fetchprop("KEYWORD_PATH");
			File excel = new File(path);
			FileInputStream file = new FileInputStream(excel);

			System.out.println(path);
			String extn = path.substring(path.indexOf(".") + 1);

			System.out.println(extn);
			if (extn.equals("xlsx")) {
				wb = new XSSFWorkbook(file);
			} else {
				wb = new HSSFWorkbook(file);
			}
			Sheet sheet = wb.getSheet("CloseOut");
			int rownum = sheet.getLastRowNum();
			System.out.println("Rows: " + rownum);
			int column = sheet.getRow(0).getLastCellNum();

			data = new String[rownum][column];

			for (int i = 0; i < rownum; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < column; j++) {
					Cell cell = row.getCell(j);
					String value = cell.toString();
					data[i][j] = value;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return data;
	}

	public String fetchprop(String text) {
		Properties prop = new Properties();
		FileInputStream input;
		//C:\Users\Cresen\eclipse-workspace\Enagemate_Keyword\src\Reusable\object.properties
		try {
		//C:\Users\Cresen\eclipse-workspace\Enagemate_Keyword\src\Reusable\object.properties	
			input = new FileInputStream("C:\\Users\\Cresen\\eclipse-workspace\\Enagemate_Keyword\\src\\Reusable\\object.properties");
			prop.load(input);
		} catch (Exception ex) {

		}

		return prop.getProperty(text);
	}

	public void LaunchAppl() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\\\Users\\\\Cresen\\\\Desktop\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(fetchprop("URL"));
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
	//@SuppressWarnings("deprecation")
	public void FillText(String locatorBy, String locatorValue, String text) {
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		switch (locatorBy) {
		case "xpath":
			driver.findElement(By.xpath(locatorValue)).sendKeys(text);
			break;
		case "name":
			driver.findElement(By.name(locatorValue)).sendKeys(text);
			break;
		case "id":
			driver.findElement(By.id(locatorValue)).sendKeys(text);
			break;

		}
	}
	
	@SuppressWarnings("deprecation")
	public void FillNumber(String locatorBy, String locatorValue, String text) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		double d = Double.parseDouble(text);
		int i =(int) d;
		String str = Integer.toString(i);
		
		switch (locatorBy) {
		case "xpath":
			driver.findElement(By.xpath(locatorValue)).sendKeys(str);
			break;
		case "name":
			driver.findElement(By.name(locatorValue)).sendKeys(str);
			break;
		case "id":
			driver.findElement(By.id(locatorValue)).sendKeys(str);
			break;

		}
	}
	public void click1(String locatorBy, String locatorElement) throws InterruptedException {
   
		wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        switch (locatorBy) {
        case "xpath":
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorElement)));
            driver.findElement(By.xpath(locatorElement)).click();
            break;
        case "name":
            wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorElement)));
            driver.findElement(By.name(locatorElement)).click();
            break;
        case "id":
            wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorElement)));
            driver.findElement(By.id(locatorElement)).click();
            break;
        }
    }
	//@SuppressWarnings("deprecation")
	@Test
	public void click(String locatorBy, String locatorElement) throws InterruptedException {

		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		switch (locatorBy) {
		case "xpath":
			driver.findElement(By.xpath(locatorElement)).click();
			break;
		case "name":
			driver.findElement(By.name(locatorElement)).click();
			break;
		case "id":
			driver.findElement(By.id(locatorElement)).click();
			break;

		}
	}

	public void isPresent(String locatorBy, String locatorElement) {
		switch (locatorBy) {
		case "xpath":
			driver.findElement(By.xpath(locatorElement)).isDisplayed();
			break;
		case "name":
			driver.findElement(By.name(locatorElement)).isDisplayed();
			break;
		case "id":
			driver.findElement(By.id(locatorElement)).isDisplayed();
			break;
		}
	}

	public void Wait() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void Wait_5() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void Wait_7() {
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void Wait_10() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void Wait_15() {
		try {
			Thread.sleep(17000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void Scroll_Ele_click(String locatorBy, String locatorElement) {
		Actions act = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		switch (locatorBy) {
		case "xpath":
			WebElement c = driver.findElement(By.xpath(locatorElement));
			act.scrollToElement(c).click().build().perform();
			break;
		case "name":
			WebElement c1 = driver.findElement(By.name(locatorElement));
			act.scrollToElement(c1).click().build().perform();
			break;
		case "id":
			WebElement c2 = driver.findElement(By.id(locatorElement));
			act.scrollToElement(c2).click().build().perform();
			break;

		}}
		
	
	@Test
	public void validateValue(String locatorBy, String locatorElement, String content_param) {
		switch (locatorBy) {
		case "xpath":
			String Tmq=driver.findElement(By.xpath(locatorElement)).getAttribute("value");
			String A=content_param;
//			String  a=A;
//			String s=String.valueOf(a);  
//
//			s=s.replace(".", "");
//			s=s.replace(s.substring(s.length()-1), "");
			
			double d = Double.parseDouble(A);
			int i =(int) d;
			String str = Integer.toString(i);
			System.out.println( "Expected Number of Ques: "+ str);
			System.out.println( "Actual Number of Ques: "+ Tmq);

			Assert.assertEquals(str, Tmq);
			break;
		}
		
		}

	
	public void clickon(WebDriver d, WebElement locatorValue,Duration timeout) 
	{
        new WebDriverWait(d, timeout).ignoring(StaleElementReferenceException.class)
        .until(ExpectedConditions.elementToBeClickable(locatorValue));
        locatorValue.click();
    }
	public void scroll() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(200,1200)");
	}
	
	public void Implicit_wait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	public void explicit_wait(String locatorValue) {
	    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	 }
	 public void getActvityID() {
	    WebElement ActivityId = driver.findElement(By.xpath("//span[@class='font-weight-bold ng-star-inserted']"));
		String a=ActivityId.getText();
		System.out.println(a);	
		b=a.substring(13);
		System.out.println(b);
	 } 
     public void sendActivityId() {
    	WebElement sendActID = driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]"));
    	sendActID.sendKeys(b);
     }
     public void getActvityIDForCloseOut() {
    	 
    	WebElement ActivityId = driver.findElement(By.xpath("//span[@class='font-weight-bold ng-star-inserted']"));
		String c=ActivityId.getText();
		System.out.println(c);	
		d=c.substring(13);
		System.out.println(d);
		WebElement sendActID = driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]"));
		sendActID.sendKeys(this.d);
	}
     
     public void sendActivityIdOfCloseout1() {
    	WebElement sendActID = driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]"));
    	sendActID.sendKeys(this.d);
    	
     }
    public void sendActivityIdOfCloseout2(String locatorBy, String locatorValue) {
 	
 	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
 	     switch (locatorBy) {
 	
 	     case "xpath":
 		 driver.findElement(By.xpath(locatorValue)).sendKeys(d);
 		 break;
 	
 	}
 }
    
     public void escape() { 
    	 Actions act = new Actions(driver);
 	     act.sendKeys(Keys.ESCAPE).build().perform();
 	}
 
	public void enter(){ 
		Actions act = new Actions(driver);
	    act.sendKeys(Keys.ENTER).perform();
	}
	public void refresh_browser() { 
	   driver.navigate().refresh();
	}
	public void validateNominee() {
		WebElement CustStatus = driver.findElement(By.xpath("(//div[@col-id='customerStatusUniqueName'])[2]"));
		String actual=CustStatus.getText();
		String expected ="Nominated";
		Assert.assertEquals(actual, expected);
	}
	public void validate_Activity_Status() {
		WebElement ActStatus = driver.findElement(By.xpath("(//div[@col-id='activityStatus'])[2]"));
		String actual=ActStatus.getText();
		String expected ="Closed";
		Assert.assertEquals(actual, expected);
	}
	public void UploadDocuments() throws InterruptedException, AWTException {
		
		WebElement fileUpload = driver.findElement(By.xpath("//mat-label[contains(text(),'choose file')]/../../..//input[@formcontrolname='documentName']"));
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", fileUpload);

		Robot rb = new Robot();
		rb.delay(2000);
		StringSelection ss = new StringSelection("C:\\Users\\Cresen\\Pictures\\ABC.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.delay(1000);
		
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.delay(1000);
		
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
	}
	
	public void approval() throws InterruptedException
		{List<WebElement> listOfRev = driver.findElements(By.xpath("(//div[@tabindex='-1' and @aria-colindex='1' and @col-id='name'])"));

		reviewerslist=new ArrayList<String>(); 
		for(WebElement ReviwerList:listOfRev ) 
		{
			reviewerslist.add(ReviwerList.getText());  
		}
     
		for (int i=1;i<reviewerslist.size();i++ ){
			
		System.out.println(reviewerslist.get(i));
		driver.findElement(By.xpath("(//span[text()='Reviewers'])[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"hcpGridAPGRD\"]/div/div[2]/div[2]/div[1]/div[2]/div/div/div[1]/div[3]/span")).click();
	    driver.findElement(By.xpath("//span[@aria-label='filter']")).click();
	    WebElement searchFilter = driver.findElement(By.xpath("//input[@aria-label='Search filter values']"));
	    Actions actt = new Actions(driver);
		Thread.sleep(2000);	
        driver.findElement(By.xpath("(//div[@class='ag-wrapper ag-input-wrapper ag-checkbox-input-wrapper ag-checked'])[1]")).click();
		searchFilter.sendKeys(reviewerslist.get(i));
		actt.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		String FirstReviwer = driver.findElement(By.xpath("(//div[@tabindex='-1' and @aria-colindex='1' and @col-id='userName'])[2]")).getText();
		System.out.println(FirstReviwer);
		driver.findElement(By.xpath("//span[text()='X']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click(); 
		Thread.sleep(2000);	 
		driver.findElement(By.xpath("//b[text()=' LOG OUT ']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='userId']")).sendKeys(FirstReviwer);	
		driver.findElement(By.id("userPwd")).sendKeys("Cresen123!");
		Thread.sleep(2000);
		driver.findElement(By.id("loginBtn")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(text(),'General Reviewer')]")).click();
		Thread.sleep(4000);	 
		driver.findElement(By.xpath("//div[@class='card1-block p-3']")).click();	
		Thread.sleep(4000);	 
		driver.findElement(By.xpath("(//span[@class='ag-header-icon ag-header-cell-menu-button'])[1]")).click();	    		 
		driver.findElement(By.xpath("(//span[@class='ag-icon ag-icon-filter'])[11]")).click();    		 
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]")).sendKeys(b);
		driver.findElement(By.xpath("//button[@ref='applyFilterButton']")).click(); 	    		 
		Thread.sleep(4000);	   
		driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-activity/div/div[2]/div/ag-grid-angular/div/div[2]/div[2]/div[3]/div[1]/div[1]/div/div/a")).click(); 	    		  
        Thread.sleep(15000);
		driver.findElement(By.xpath("//span[contains(text(),'Nominees')]")).click(); 	    			    		 
		driver.findElement(By.xpath("//span[text()='Approve']")).click();
		Thread.sleep(1000);	 
		driver.findElement(By.xpath("//mat-label[text()='Leave a comment']/../../..//textarea[@rows='1']")).sendKeys("approve");
		Thread.sleep(1000);	 
		driver.findElement(By.xpath("(//span[text()='Approve'])[2]")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click(); 
		Thread.sleep(2000);	 
		driver.findElement(By.xpath("//b[text()=' LOG OUT ']")).click();
		Thread.sleep(2000);	
		driver.findElement(By.id("userId")).sendKeys("rnd_sub@mailinator.com");  
		driver.findElement(By.id("userPwd")).sendKeys("Cresen123!");    
		driver.findElement(By.id("loginBtn")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[@class='card1-block p-3'])[2]")).click();
		Thread.sleep(4000);	 
		driver.findElement(By.xpath("(//span[@class='ag-header-icon ag-header-cell-menu-button'])[1]")).click();	    		 
		driver.findElement(By.xpath("(//span[@class='ag-icon ag-icon-filter'])[11]")).click();    		 
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]")).sendKeys(b);
		Thread.sleep(2000);	
		driver.findElement(By.xpath("//button[@ref='applyFilterButton']")).click(); 	    		 
		Thread.sleep(4000);	
		driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-activity/div/div[2]/div/ag-grid-angular/div/div[2]/div[2]/div[3]/div[1]/div[1]/div/div/a")).click(); 	    		  
		Thread.sleep(10000);		
		driver.findElement(By.xpath("//span[text()='Audit Trail']")).click();
		driver.findElement(By.xpath("(//em[@class='fa fa-plus-circle'])[4]")).click();
	}
	   System.out.println("Approval is completed");
	   Thread.sleep(2000);
       driver.findElement(By.xpath("//span[text()='X']")).click();
       Thread.sleep(5000);	 		     	
}
	    	



}
