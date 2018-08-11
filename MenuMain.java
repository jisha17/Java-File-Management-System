package SCTPL;

import java.io.IOException;
import java.util.Scanner;

public class MenuMain 
{
	public static void main(String[] args) throws IOException
	{
	Scanner sc=new Scanner(System.in);
	int choice=0;
	String strInputFile = "";
    do {
        //display our selection menu
        System.out.println("Welcome To File Management System ");
        System.out.println("1. Create file");
        System.out.println("2. Rename file");
        System.out.println("3. Delete file");
        System.out.println("4. Create directory");
        System.out.println("5. Rename directory");
        System.out.println("6. Delete directory");
        System.out.println("7. View Files in a Directory");
        System.out.println("8. Copy File");
        System.out.println("9. Compress");
        System.out.println("10. Decompress");
        System.out.println("11. Exit");
        System.out.println("--------------------------");
        System.out.println("Please enter your choice:");
        
        choice = sc.nextInt();
        sc = new Scanner(System.in);
        switch(choice) 
        {
            case 1:
                System.out.println("Enter the file path where you want to create the file ");
                strInputFile = sc.nextLine();
                System.out.println("Enter the content :");
                String strContent = sc.nextLine();

                FileUtility.createFile(strInputFile, strContent);
                break;
       
            case 2:
                System.out.println("Enter the file path where you want to rename the file ");
                strInputFile = sc.nextLine();
                System.out.println("Enter the new name of a file with extension ex : testing.txt");
                String strNewFileName = sc.nextLine();

                FileUtility.reNameFile(strInputFile, strNewFileName);
                break;
                
            case 3:
                System.out.println("Enter the file path that you want to delete");
                strInputFile = sc.nextLine();
                FileUtility.deleteFile(strInputFile);
                break;
                	
            case 4:
                System.out.println("Enter the directory that you want to create");
                strInputFile = sc.nextLine();
                FileUtility.createDirectory(strInputFile);
                break;
            	            
            case 5:
                System.out.println("Enter the directory that you want to rename");
                strInputFile = sc.nextLine();
                System.out.println("Enter the new name(not path) of a directory  ex : Sid : ");
                String strNewDirectoryName = sc.nextLine();
                FileUtility.reNameDirectory(strInputFile, strNewDirectoryName);
                break;
                
            case 6:
                System.out.println("Enter the directory that you want to delete");
                strInputFile = sc.nextLine();

                FileUtility.deleteDirectory(strInputFile);
                break; 
               
            case 7:
                System.out.println("Enter the directory that you want to view");
                strInputFile = sc.nextLine();

                FileUtility.listFilesFromDirectory(strInputFile);
                break;
                
            case 8:
                System.out.println("Enter the file path that you want to copy");
                strInputFile = sc.nextLine();
                System.out.println("Enter the path where you want to paste the selected file ");
                String strOutputPath = sc.nextLine();
                FileUtility.copyFile(strInputFile, strOutputPath);
                break;
                
            case 9:
                //  prompt to get file path
                System.out.print("\nEnter the file path that you want to Compress : ");
                // get their input as a String
                strInputFile = sc.nextLine();
                FileUtility.compressFile(strInputFile);
                break;
                
            case 10:
                System.out.print("\nEnter the file path that you want to Decompress : ");
                strInputFile = sc.nextLine();
                FileUtility.decompress(strInputFile);
                break;
                
            case 11://exit
                System.out.println("Exit....!");
                break;
                
            default:
            	System.out.println("You entered an invalid choice");
            	break;              
        }
        }while (choice != 13);
sc.close();
	}
}
