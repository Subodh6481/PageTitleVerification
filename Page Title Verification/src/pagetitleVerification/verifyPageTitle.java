package pagetitleVerification;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class verifyPageTitle {

	public static void main(String[] args) throws Exception {
	
	    //fetching all the links from sitemap XML Webpage

			System.setProperty("webdriver.chrome.driver","D:\\subodh\\CRAFT\\chromedriver_win32\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			    
			FileInputStream fout = new FileInputStream("D:\\subodh\\CRAFT\\pageurlandtitlesheet.xlsx");
		    
		    Workbook wb = new XSSFWorkbook(fout);
	       //get sheet from workbook
	        XSSFSheet sheet1 = (XSSFSheet) wb.getSheetAt(0);
	        Row row1=sheet1.getRow(1);
	        row1.createCell(1).setCellValue("Page url");
	        row1.createCell(2).setCellValue("Actual Page Title");
	        row1.createCell(3).setCellValue("Expected Page Title");
	        row1.createCell(4).setCellValue("Testing Status"); 
			Document doc =  Jsoup.connect("https://www.bebrcaware.com/sitemap.xml").get();
			//System.out.println(doc.toString());
			Elements urls = (doc).select("loc");
			String str = urls.text().toString();
			//System.out.println(str);
			
			String[] strArray = str.split(" ");
			
			System.out.println("Array :"+strArray.length);
			
		//	for(int i=0;i<strArray.length;i++)
				for(int i=0;i<4;i++)
			{	
				Row row =sheet1.getRow(i+2);
				System.out.println(strArray[i]);
				String pageurl= strArray[i];
				row.getCell(1).setCellValue(pageurl);
				driver.get(strArray[i]);
				//String page_title= driver.getTitle();
				row.getCell(2).setCellValue(driver.getTitle());
			}
			//wb.write(fileOut);
			//fileOut.close();
			//fis.close();
		//	FileOutputStream fout = new FileOutputStream("D:\\subodh\\CRAFT\\pageurlandtitlesheet.xlsx");
			//wb.write(fout);
			fout.close();
			driver.close();
			//comparision();
		}
	
	
	public static void comparision()
	{ 
		
	
	}
	}
	 
	
	
