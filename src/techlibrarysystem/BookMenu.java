package techlibrarysystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import static techlibrarysystem.TechLibrarySystem.LibraryList;
import static techlibrarysystem.TechLibrarySystem.input;
import static techlibrarysystem.TechLibrarySystem.printBook;

public class BookMenu {

    private static String File;
    private static String FileText;

    public static void bookMenu() throws IOException {
        System.out.println("Please make a selection: ");
        System.out.println("1: New book");
        System.out.println("2: Edit book");
        System.out.println("3: Delete book");
        System.out.println("4: Main Menu");
        int menuSelection = Integer.parseInt(input.readLine());
        switch (menuSelection) {
            case (1): //New Books
                NewBook();

            case (2): //Edit Book
                EditBook();
            case (3): //Delete book
                DeleteBook();
            case (4):

            default:
                System.out.println("Not a valid choice");
                break;
        }
    }

    public static void NewBook() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        Book[] newBook = new Book[5];
        Scanner input = new Scanner(System.in);
        boolean BookInput;
        while (BookInput = true) {
            newBook[1] = new Book();
            System.out.println("Please enter a new book title: ");
            newBook[1].Title = input.nextLine();
            System.out.println("Please enter a new book author: ");
            newBook[1].Author = input.nextLine();
            System.out.println("Please enter a new book ISBN: ");
            newBook[1].ISBN = input.nextLine();
            System.out.println("Please enter the new books price: ");
            newBook[1].Price = input.nextLine();
            LibraryList.add(newBook[1]);
            printBook(newBook[1]);
            PrintWriter writer = new PrintWriter(newBook[1].Title + ".txt", "UTF-8");
            writer.println("Title: " + newBook[1].Title);
            writer.println("Author: " + newBook[1].Author);
            writer.println("ISBN: " + newBook[1].ISBN);
            writer.println("Author: " + newBook[1].Price);
            writer.close();
            System.out.println("Would you like to add another book?");
            String answer = input.next();
            if (answer.equalsIgnoreCase("yes")) {
                BookInput = false;
            } else {
                TechLibrarySystem.mainMenu();
            }

        }
    }

    public static void EditBook() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("What book would you like to edit? ");
        File = input.next();
        ReadFile();
        System.out.println(FileText);
        System.out.println("Would you like to edit this? Yes/No");
        String EditDecision =input.next();
        if(EditDecision.equalsIgnoreCase("Yes")){
            NewBook();
        }else if(EditDecision.equalsIgnoreCase("No")){
            TechLibrarySystem.mainMenu();
        } else{
            TechLibrarySystem.mainMenu();
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

    public static void DeleteBook(){
        Scanner input = new Scanner(System.in);
        System.out.println("Which book would you like to delete? Please enter the name");
        String FileDelete= input.next();
        File file = new File(FileDelete+".txt"); 
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
    }
    
}
