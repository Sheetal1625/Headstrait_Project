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
public class final_project {
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
				if(prev.equals(" ")) {
					prev=" "+cell.getStringCellValue()+" ";
					continue;
				}
					replace=" "+cell.getStringCellValue()+" ";
				data=data.replaceAll(prev, replace);
			}
			
			}
			}
			catch(Exception e) {
				System.out.println(e+" ");
			}
		return data;
	}
	static void create_new_file(String new_data,String file_name) {
		String new_path="C:\\Users\\DELL\\Desktop\\processed\\"+file_name;
        File new_file=new File(new_path);
        FileWriter writer = null;
        try {
        	writer=new FileWriter(new_file);
        	Files.writeString(Path.of(new_path), new_data);
        }
        catch(Exception e) {
        	System.out.println(e+"");
        }
	}
	public static void main(String[] args) {
		try {
			File directoryPath = new File("C:\\Users\\DELL\\Desktop\\unprocessed");
		      String contents[] = directoryPath.list();
		      String xfile="C:\\Users\\DELL\\Downloads\\Word Substitutions.xlsx";
		      for(int i=0; i<contents.length; i++) {
		    	  String file="C:\\Users\\DELL\\Desktop\\unprocessed\\";
		    	  file=file+contents[i];
		    	  String data=read_file(file);
		    	  String new_data=extract_excel(data,xfile);
		    	  create_new_file(new_data,contents[i]);
		      }
		      System.out.println("done");
			}
			catch(Exception e) {
				System.out.println(e+"");
			}


	}
}
