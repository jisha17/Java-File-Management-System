package SCTPL;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


public class FileUtility {
	    public static void createFile(String inputFile, String content) throws IOException    //Function to create file in a path
	    {
	        try {
	            File file = new File(inputFile);   
	            if (!file.exists())
	            {
	                file.createNewFile();
	            }
	            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
	            BufferedWriter bufferWriter = new BufferedWriter(fileWriter); 
	            bufferWriter.write(content);
	            bufferWriter.flush();
	            bufferWriter.close();
	            System.out.println("File " + inputFile + " has been created successfully..!");
	            } catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	    } //create file function close
	    
	    public static void reNameFile(String inputFile, String newFileName)           //Function to rename file in the given path
	    {
	        File file = new File(inputFile);
	        if (file.isFile()) {
	            String fileDirectory = file.getParent();
	            File newName = new File(fileDirectory + "\\" + newFileName);
	            if (file.renameTo(newName)) {
	                System.out.println("File has been Renamed.");
	            } else {
	                System.out.println("Error in Renaming the file.");
	            }
	        } 
	        else{
	            System.out.println("Invalid file path");
	        }	        
	    } //renamefile function close
	   
	    public static void deleteFile(String inputFile) throws IOException
	    {
	        //get the file path in File object
	        File file = new File(inputFile);
	        //validate the file
	        if (file.isFile()) 
	        {
	            try
	            {
	            	file.delete();
	            	System.out.println("File Deleted Successfully");
	            }
	            catch (Exception e) 
		        {
		            e.printStackTrace();
		            System.out.println("Something went wrong");
		            
		        }
	        }
	        
	    } //deletefile function close
	            
	    public static void createDirectory(String inputDirectory) 
	    {
	        File file = new File(inputDirectory);
	        if (file.exists()) 
	        {
	            System.out.println("The directory is already present");
	        } else 
	        {
	            file.mkdirs();
	            System.out.println("New directory is created");
	        }
	    } //create directory function close
	    
	    public static void reNameDirectory(String inputFile, String newDirName) 
	    {
	        File file = new File(inputFile);
	        if (file.isDirectory())
	        {
	        	System.out.println("Directory found");
	            File newName = new File(file.getParent() + "\\" + newDirName);
	            System.out.println(file.getParent());
	            //use renameTo() and check its return value 
	            if (file.renameTo(newName))
	            {
	            	System.out.println("Directory has been Renamed.");
	            }else
	            {
	            	System.out.println("Error in Renaming the Directory.");
	            }
	            
	        }else
	        {
	        	System.out.println("Directory does not exist");
	        }
	    } //rename driectory function close
	    
	    
	    public static void deleteDirectory(String inputDirectory)
	    {
	        //get the file path in File object
	    	File directory = new File(inputDirectory);
	        //check if its a directory  or not
	    	if (directory.isDirectory()) 
	    	{
	        //check if the directory has childs or not
	            if (directory.list().length == 0) 
	            {
	                directory.delete();
	                System.out.println("Directory is deleted :"+ directory.getAbsolutePath());
	            } else
              {
	        //ask user whether he wants to delete the directory or not		
	            	System.out.println("Do you want to delete the Directory or not:Yes/No" );
	            	Scanner sc=new Scanner(System.in);
	            	String userRes = sc.nextLine();
	            	if(userRes.equalsIgnoreCase("yes"))
	            	{      	
	            		//delete files inside the directory one by one
	            		deleteFilesInsideDirectory(directory);
	            		//delete parent directory
	            		directory.delete();
	            		if (!directory.exists())
	            		{
	            			System.out.println("Directory has been deleted.");
	            		} else 
                  {
	            			System.out.println("Deletion failed");
	            		}
	            		deleteDirectory(inputDirectory);
	            	} else if (userRes.equalsIgnoreCase("no")) 
	            	{
	            		System.out.println("Delete directory request cancelled by user.");	
	            	} else {
	            		System.out.println("Invalid entry");        		
	            	}
	            	sc.close();
	            }
	    	} 
		   else {
	       System.out.println("Invalid file directory");
	       }
	    }

		// code this method in a recursive fashion
	    private static void deleteFilesInsideDirectory(File element) 
	    {
	        if (element.isDirectory()) 
	        {
	            if (element.listFiles().length == 0) 
	            {
	    //delete directory
	                element.delete();
	            } else 
	            {
	            	for (File sub: element.listFiles())
	            	{
	            	deleteFilesInsideDirectory(sub);
	            	}
	            }
	    //delete files one by one	
	        }else
	        {
	        	  element.delete();
	        	// end of else-part
	        } // end of outer-if      
		  // delete file    
	    }//Delete directory function close
	    
	    
	    public static void listFilesFromDirectory(String inputDirectory) {
	        File directory = new File(inputDirectory);
	        if (directory.isDirectory()) 
	        {
	            //check if the directory has childs or not
	        	if (directory.list().length == 0) 
	            {
	                System.out.println("No files inside the directory");
	            } else
	            {
	            	System.out.println("Following files are present");
		            //seperate the files and print [Folder] or [File]
	            	File lstFiles[] = directory.listFiles();
		        	for (int i = 0; i < lstFiles.length; i++) 
		        		{
		        		//show file names here by using file.getName()
		        		System.out.println(lstFiles[i].getName());
		        		}
	            }
	        }else 
	        {
	            System.out.println("Invalid file directory");
	        }
	    }    
	    public static void copyFile(String inputPath, String outputPath) throws IOException {
          //InputStream is = null;
	        //OutputStream os = null;
	        File inputFile = new File(inputPath);
	        File outputFile = new File(outputPath + "//" + inputFile.getName());
	        if(inputFile.isFile())
	        {
	        	InputStream is = new FileInputStream(inputFile);
	        	OutputStream os = new FileOutputStream(outputFile);
	        	byte[] buffer = new byte[1024];
	        	int length;
	        	//write the streams to the OutputStream to copy the data
	        	while ((length = is.read(buffer)) > 0) 
	        	{
	        	os.write(buffer, 0, length);
	        	}
	        	System.out.println("File has been copied");               	
	        	is.close();
	        	os.flush();
	        	os.close();
	        }
			else {
	            System.out.println("Please enter valid file path");
	        }	       
	    }//Copying from one path to another file
	    
	   
	    public static void compressFile(String inputFile) throws IOException {
	        File file = new File(inputFile);
	        //String filewithExtension=file.getName();
	        //String filewithoutextns=filewithExtension.substring(0,filewithExtension.lastIndexOf("."));
	        String fileNameWithExtension = file.getName();
	        String fileName = fileNameWithExtension.substring(0, fileNameWithExtension.lastIndexOf("."));   
	        //make directory if not exists
	        if (file.exists())
	        {
	            System.out.println("The file is present");
		        ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(fileName+".zip"));
	            ZipEntry zipEntry = new ZipEntry(fileNameWithExtension);
	            zip.putNextEntry(zipEntry);           
	            FileInputStream fis = new FileInputStream(file);
	            final int BUFFER = 2048;
	            byte buffer[] = new byte[BUFFER];
	            int length;
	            while ((length = fis.read(buffer)) > 0) 
	            {
	            zip.write(buffer, 0, length);
	            }
	            System.out.print("\nFile has been compressed successfully..!\n");        
	            zip.closeEntry();
	            zip.close();
	            fis.close();	            
	        }else
	        {
	        	System.out.println("File not found");
	        }
	        //perform zip - logic and code explained in the pdf doc 		        
	    }//compress funct close
	    
	    public static void decompress(String zipFile) throws IOException
	    {
	        //perform un-zip - logic and code explained in the pdf doc 
	    	ZipFile zip = new ZipFile(zipFile);
	    	Enumeration zipFileEntries = zip.entries();
	    	while (zipFileEntries.hasMoreElements()) 
	    	{
	    		//Decompress here
	    		ZipEntry entry=(ZipEntry)zipFileEntries.nextElement();
	            
	    		BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
	    		int currentByte;
	    		// establish buffer for writing file
	    		final int BUFFER=2048;
	    		byte data[] = new byte[BUFFER];
	    		// write the current file to disk
	    		FileOutputStream fos = new FileOutputStream(entry.getName());
	    		BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
	    		// read and write until last byte is encountered
	    		while ((currentByte = is.read(data, 0, BUFFER)) != -1) 
	    		{
	    		dest.write(data, 0, currentByte);
	    		}    		
		    	System.out.println("Zip file is decomprissed");
		    	is.close();
		    	dest.flush();
		    	dest.close();
		    	fos.close();
	    	}   
	    	zip.close();
	    } //decompress funct close
	} //class


