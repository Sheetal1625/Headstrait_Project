package project;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;  
import java.io.FileInputStream;  
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 
public class to_compare {
	static String read_file(String file_path) {
		BufferedReader reader=null;
		String data="";
		try {
			reader=new BufferedReader(new FileReader(file_path));
			String line = reader.readLine();
            while (line != null) 
            {
                data = data + line + System.lineSeparator();
                line = reader.readLine();
            }
		}
		catch(Exception e) {
			System.out.println(e+"");
		}
		return data;
	}
	static String extract_excel(String data,String xfile_path) {
		try {
			FileInputStream fis=new FileInputStream(new File(xfile_path));
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0); 
			Iterator<Row> itr = sheet.iterator();
			Row row = itr.next();
			while (itr.hasNext())                 
			{  
			row=itr.next();
			Iterator<Cell> cellIterator = row.cellIterator();  
			String prev=" ",replace=" ";
			while (cellIterator.hasNext())   
			{    
				Cell cell = cellIterator.next();
				if(replace.equals(" ")) {
					replace=" "+cell.getStringCellValue()+" ";
					continue;
				}
					prev=" "+cell.getStringCellValue()+" ";
				data=data.replaceAll(prev, replace);
			}
			
			}
			}
			catch(Exception e) {
				System.out.println(e+" ");
			}
		return data;
	}
	public static void main(String[] args) {
		try {
			File directoryPath = new File("C:\\Users\\DELL\\Desktop\\processed");
			File olddirectoryPath = new File("C:\\Users\\DELL\\Desktop\\unprocessed");
		      String contents[] = directoryPath.list();
		      String old_contents[]=olddirectoryPath.list();
		      String xfile="C:\\Users\\DELL\\Downloads\\Word Substitutions.xlsx";
		      for(int i=0; i<contents.length; i++) {
		    	  String file="C:\\Users\\DELL\\Desktop\\processed\\";
		    	  file=file+contents[i];
		    	  String old_file="C:\\Users\\DELL\\Desktop\\unprocessed\\";
		    	  old_file=old_file+contents[i];
		    	  String data=read_file(file);
		    	  String new_data=extract_excel(data,xfile);
		    	  String old_data=read_file(old_file);
		    	  if(new_data.equals(old_data)) {
		    		  System.out.println("Both are same");
		    	  }
		    	  else
		    		  System.out.println("Both are not same");
		      }
		      System.out.println("done");
			}
			catch(Exception e) {
				System.out.println(e+"");
			}


	}
}
