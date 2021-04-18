package com.init;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

/*import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
*/
/**
 * Define Common Webdriver
 */
public class Common {
	Date date = new Date();
	protected static Wait<WebDriver> wait;
	public static String alerttext;
	public static com.aventstack.extentreports.ExtentTest test;


// --------------------- Start New code ---------------------------

	XSSFWorkbook wb;
	XSSFSheet sheet1;
	File src;

	public Common(String excelPath) {
		try {
			src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			sheet1 = wb.getSheetAt(0);
		} catch (Exception e) {

			System.out.println(e);
		}
	}

	public String getdata(int sheetNumber, int row, int colum) {

		try {
			sheet1 = wb.getSheetAt(sheetNumber);
			String data = wb.getSheetAt(sheetNumber).getRow(row).getCell(colum).getStringCellValue();
			return data;
		} catch (Exception e) {
			System.out.println(
					"\n\n\n\n Error:  May excel sheet's cell data type is not supported, please change the cell datatype in excel! \n\n\n\n\n ");
		}
		return null;

	}

	public double getdataNumeric(int sheetNumber, int row, int colum) throws Exception {
		sheet1 = wb.getSheetAt(sheetNumber);
		double data = wb.getSheetAt(sheetNumber).getRow(row).getCell(colum).getNumericCellValue();
		return data;
	}

	public int getNumberOfSheets() throws Exception {
		int scount = wb.getNumberOfSheets();
		return scount;

	}

	public void write(int sheetNumber, int row, int colum, String text) {
		try {

			sheet1 = wb.getSheetAt(sheetNumber);
			sheet1.createRow(row);
			sheet1.getRow(row).createCell(colum).setCellValue(text);
			FileOutputStream fout = new FileOutputStream(src);
			wb.write(fout);
			fout.flush();
			fout.close();
			// wb.close();

		} catch (IOException e) {

			System.out.println(e);
		}

	}

	public String date() throws Exception {
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		// get current date time with Date()
		Date date = new Date();
		// Now format the date
		String date1 = dateFormat.format(date);
		// System.out.println(date1);
		return date1;
	}

	public static String dateOnly() throws Exception {
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		// get current date time with Date()
		Date date = new Date();
		// Now format the date
		String date1 = dateFormat.format(date);
		// System.out.println(date1);
		return date1;
	}

	public static String currentDate() throws Exception {
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// get current date time with Date()
		Date date = new Date();
		// Now format the date
		String date1 = dateFormat.format(date);
		// System.out.println(date1);
		return date1;
	}

	public static String currentDate1() throws Exception {
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		// get current date time with Date()
		Date date = new Date();
		// Now format the date
		String date1 = dateFormat.format(date);
		// System.out.println(date1);
		return date1;
	}
	
	public int find(int sorceSheetNumber, int sorceRow, int sorceColumn, int findSheetNumber, int findRowNumber)
			throws Exception {

		int index = 0;
		sheet1 = wb.getSheetAt(sorceSheetNumber);
		for (int i = 1; i < 20; i++)
			if (wb.getSheetAt(sorceSheetNumber).getRow(sorceRow).getCell(sorceColumn).getStringCellValue()
					.equals(wb.getSheetAt(findSheetNumber).getRow(findRowNumber).getCell(i).getStringCellValue()))
				return wb.getSheetAt(findSheetNumber).getRow(findRowNumber).getCell(i).getColumnIndex();
		return index;
	}

	public void updateRusult(int sheetNumber, String methodName, String result) throws Exception {

		try {
		
		sheet1 = wb.getSheetAt(sheetNumber);
		int lastRowNumber = sheet1.getLastRowNum();

// ----------------------- start create column --------------	
		
		try {
			sheet1.getRow(0).createCell(0).setCellValue("Method Name");
			FileOutputStream fout = new FileOutputStream(src);
			wb.write(fout);
			fout.flush();
			fout.close();
//			 wb.close();

		} catch (IOException e) {

			System.out.println(e);
		}
		
		
		boolean flg = true;
		int incmnt = 0;
		while(flg==true) {
		
		try {
			 wb.getSheetAt(sheetNumber).getRow(0).getCell(incmnt).getStringCellValue();
		flg=true;	
		incmnt = incmnt +1;
		} catch (Exception e1) {
			flg=false;
			break;
		}
		
		}
		
		int columnNumber;
		for(columnNumber=0;columnNumber<=incmnt;columnNumber++) {
			
			try {
			wb.getSheetAt(sheetNumber).getRow(0).getCell(columnNumber).getStringCellValue();
			}catch (Exception e) {
				break;
			}
			
			if(wb.getSheetAt(sheetNumber).getRow(0).getCell(columnNumber).getStringCellValue().contentEquals(currentDate1())){
				break;
			}
			
		}
		
		
		if(columnNumber==0) {
			columnNumber=columnNumber+1;
		}
		
		
		
		try {
			sheet1.getRow(0).createCell(columnNumber).setCellValue(currentDate1());
			sheet1.getRow(0).createCell(columnNumber+1).setCellValue("Error Logs");
			FileOutputStream fout = new FileOutputStream(src);
			wb.write(fout);
			fout.flush();
			fout.close();
//			 wb.close();

		} catch (IOException e) {

			System.out.println(e);
		}
		

		
		// ----------------------- end create column --------------		
		
		
		int lastUpdatedRow = 1;
		boolean flag = false;
		
		Row row=null;


		for (int i = 1; i <= lastRowNumber ; i++) {
			
			 if(sheet1.getRow(i) != null) {
			        row = sheet1.getRow(i);
			        }
			    else {
			        row = sheet1.createRow(i);
			    }
			 
			 if(sheet1.getRow(i+1) != null) {
			        row = sheet1.getRow(i+1);}
			    else {
			        row = sheet1.createRow(i+1);
			    }
			 
			String excelmethod = "notmatched";
			try {
				excelmethod = wb.getSheetAt(sheetNumber).getRow(i).getCell(0).getStringCellValue();
				
			} catch (Exception e1) {
				lastUpdatedRow = i;
				break;
			}

			if (methodName.equals(excelmethod)) {

				try {
					sheet1.getRow(i).createCell(columnNumber).setCellValue(result);
					FileOutputStream fout = new FileOutputStream(src);
					wb.write(fout);
					fout.flush();
					fout.close();
//					 wb.close();

					lastUpdatedRow = i;
					flag = true;

//					break;

				} catch (IOException e) {

					System.out.println(e);
				}
			}

		} 

		if (flag == false) {


				try {
					sheet1.getRow(lastUpdatedRow).createCell(0).setCellValue(methodName);
					FileOutputStream fout = new FileOutputStream(src);
					wb.write(fout);
					fout.flush();
					fout.close();
//					 wb.close();

				} catch (IOException e) {

					System.out.println(e);
				}

				try {
					sheet1.getRow(lastUpdatedRow).createCell(columnNumber).setCellValue(result);
					FileOutputStream fout = new FileOutputStream(src);
					wb.write(fout);
					fout.flush();
					fout.close();
//					 wb.close();

				} catch (IOException e) {

					System.out.println(e);
				}


			
		}
		}catch (Exception e3) {
			System.out.println("----- excel updated-----");
		}

	}
	
	public void updateRusultWithFailerLog(int sheetNumber, String methodName, Throwable error) throws Exception {

		try {
		
		Throwable e = error;
		
		StringWriter sw = new StringWriter();
        // create a PrintWriter
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String errorLog = sw.toString();
        System.out.println("Error:\n" + errorLog);
		
		
		sheet1 = wb.getSheetAt(sheetNumber);
		int lastRowNumber = sheet1.getLastRowNum();
		
		
		
		// ----------------------- start create column --------------	
		
				try {
					sheet1.getRow(0).createCell(0).setCellValue("Method Name");
					FileOutputStream fout = new FileOutputStream(src);
					wb.write(fout);
					fout.flush();
					fout.close();
//					 wb.close();

				} catch (IOException e1) {

					System.out.println(e);
				}
				
				
				boolean flg = true;
				int incmnt = 0;
				while(flg==true) {
				
				try {
					 wb.getSheetAt(sheetNumber).getRow(0).getCell(incmnt).getStringCellValue();
				flg=true;	
				incmnt = incmnt +1;
				} catch (Exception e1) {
					flg=false;
					break;
				}
				
				}
				
				int columnNumber;
				for(columnNumber=0;columnNumber<=incmnt;columnNumber++) {
					
					try {
					wb.getSheetAt(sheetNumber).getRow(0).getCell(columnNumber).getStringCellValue();
					}catch (Exception e1) {
						break;
					}
					
					if(wb.getSheetAt(sheetNumber).getRow(0).getCell(columnNumber).getStringCellValue().contentEquals(currentDate1())){
						break;
					}
					
				}
				
				
				if(columnNumber==0) {
					columnNumber=columnNumber+1;
				}
				
				try {
					sheet1.getRow(0).createCell(columnNumber).setCellValue(currentDate1());
					FileOutputStream fout = new FileOutputStream(src);
					wb.write(fout);
					fout.flush();
					fout.close();
//					 wb.close();

				} catch (IOException e1) {

					System.out.println(e);
				}
				
				// ----------------------- end create column --------------

		int lastUpdatedRow = 1;
		boolean flag = false;
		
		Row row=null;


		for (int i = 0; i <= lastRowNumber ; i++) {
			
			 if(sheet1.getRow(i) != null) {
			        row = sheet1.getRow(i);}
			    else {
			        row = sheet1.createRow(i);
			    }
			 
			String excelmethod = "notmatched";
			try {
				excelmethod = wb.getSheetAt(sheetNumber).getRow(i).getCell(0).getStringCellValue();
				
			} catch (Exception e1) {
				System.out.println("-------------------------catch block run----------------------");
				lastUpdatedRow = i;
				break;
			}

			if (methodName.equals(excelmethod)) {

				try {
					sheet1.getRow(i).createCell(columnNumber+1).setCellValue(errorLog);
					FileOutputStream fout = new FileOutputStream(src);
					wb.write(fout);
					fout.flush();
					fout.close();
//					 wb.close();

					lastUpdatedRow = i;
					flag = true;

//					break;

				} catch (Exception e1) {

					System.out.println(e1);
				}
			}
			

		} 

		if (flag == false) {


//				try {
//					sheet1.getRow(lastUpdatedRow).createCell(0).setCellValue(methodName);
//					FileOutputStream fout = new FileOutputStream(src);
//					wb.write(fout);
//					fout.flush();
//					fout.close();
////					 wb.close();
//
//				} catch (IOException e) {
//
//					System.out.println(e);
//				}

				try {
					sheet1.getRow(lastUpdatedRow).createCell(2).setCellValue(errorLog);
					FileOutputStream fout = new FileOutputStream(src);
					wb.write(fout);
					fout.flush();
					fout.close();
//					 wb.close();

				} catch (Exception e1) {

					System.out.println(e1);
				}


			
		}
		
		}catch (Exception e3) {
			System.out.println("------ excel updated ---");
		}
		

	}

	private static String time() throws Exception {
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss ");
		// get current date time with Date()
		Date date = new Date();
		// Now format the date
		String date1 = dateFormat.format(date);
		// System.out.println(date1);
		return date1;
	}

	public static void Screenshot(WebDriver driver, String className, String screenshotName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file1 = new File("./ScreenShots/" + dateOnly());
			File fileq = new File("./ScreenShots/" + dateOnly() + "/" + className);
			if (!file1.exists()) {
				if (!fileq.exists()) {
					if (file1.mkdir()) {
						if (fileq.mkdir()) {

							File file2 = new File("./ScreenShots/" + dateOnly() + "/" + className + "/" + time() + " "
									+ screenshotName + ".png");
							FileUtils.copyFile(source, file2);
							System.out.println("Directory is created!");
						}
					} else {
						System.out.println("Already created..");
					}

				}
			} else {
				File file2 = new File(
						"./ScreenShots/" + dateOnly() + "/" + className + "/" + time() + " " + screenshotName + ".png");
				FileUtils.copyFile(source, file2);
			}

			System.out.println("Screenshot taken...");
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot " + e.getMessage());
		}

	}

	
	public static void Pause(int secs) {
		try {
			Thread.sleep(secs * 1000);
			// Thread.sleep(0 * 1000);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}
//	------------------------------End new code ----------------

}
