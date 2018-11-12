/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techlibrarysystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import static techlibrarysystem.BookMenu.DeleteBook;
import static techlibrarysystem.BookMenu.EditBook;
import static techlibrarysystem.BookMenu.NewBook;
import static techlibrarysystem.BookMenu.ReadFile;
import static techlibrarysystem.TechLibrarySystem.BorrowerList;
import static techlibrarysystem.TechLibrarySystem.LibraryList;
import static techlibrarysystem.TechLibrarySystem.input;
import static techlibrarysystem.TechLibrarySystem.printBook;

/**
 *
 * @author rw171069
 */
public class BorrowerMenu {

    private static String File;
    private static String FileText;
    private static String fullDir;
    private static String Name;
    private static Borrower[] newBorrower = new Borrower[5];

    public static void mainMenu() throws IOException {
        System.out.println("Please make a selection: ");
        System.out.println("1: New Borrower");
        System.out.println("2: Edit Borrower ");
        System.out.println("3: Delete Borrower");

        int menuSelection = Integer.parseInt(input.readLine());
        switch (menuSelection) {
            case (1): //New Borrower
                NewBorrower();
                break;
            case (2): //Edit Borrower
                EditBorrower();
                break;
            case (3): //Delete Borrower

            default:
                System.out.println("Not a valid choice");
                break;
        }
    }

    public static void NewBorrower() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        Scanner input = new Scanner(System.in);
        boolean BorrowerInput;
        while (BorrowerInput = true) {
            newBorrower[0] = new Borrower();
            System.out.println("Please enter name: ");
            newBorrower[0].Name = input.nextLine();
            System.out.println("Please enter your date of birth DD/MM/YY: ");
            newBorrower[0].DOB = input.nextLine();
            System.out.println("Please enter your town of residence ");
            newBorrower[0].Town = input.nextLine();
            System.out.println("Please enter your customer number: ");
            newBorrower[0].Number = input.nextLine();
            BorrowerList.add(newBorrower[0]);
            getDirBorrower();
            try {
                FileWriter writeToFile = new FileWriter(fullDir, true);
                PrintWriter printToFile = new PrintWriter(writeToFile);
                printToFile.println("name: " + newBorrower[0].Name);
                printToFile.println("Date of Birth: " + newBorrower[0].DOB);
                printToFile.println("Town of residence: " + newBorrower[0].Town);
                printToFile.println("Customer Number: " + newBorrower[0].Number);
                printToFile.close();
                writeToFile.close();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
            System.out.println("Would you like to add another book?");
            String answer = input.next();
            if (answer.equalsIgnoreCase("yes")) {
                BorrowerInput = false;
            } else {
                TechLibrarySystem.mainMenu();
            }

        }
    }

    public static void EditBorrower() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("What borrower would you like to edit? ");
        File = input.next();
        ReadFile();
        System.out.println(FileText);
        System.out.println("Would you like to edit this? Yes/No");
        String EditDecision = input.next();
        if (EditDecision.equalsIgnoreCase("Yes")) {
            NewBook();
        } else if (EditDecision.equalsIgnoreCase("No")) {
            TechLibrarySystem.mainMenu();
        } else {
            TechLibrarySystem.mainMenu();
        }
    }

    public static void DeleteBorrower() {
        Scanner input = new Scanner(System.in);
        System.out.println("Which book would you like to delete? Please enter the name");
        String FileDelete = input.next();
        File file = new File(FileDelete + ".txt");
        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
    }

    public static void ReadFile() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(File + ".txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            FileText = sb.toString();
        } finally {
            br.close();
        }
    }

    public static void getDirBorrower(){
        fullDir = System.getProperty("user.dir")+"\\Borrowers\\"+newBorrower[0].Name+".txt";
}
}
