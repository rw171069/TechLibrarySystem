/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techlibrarysystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author rw171069
 */
public class TechLibrarySystem {

    public static ArrayList<Book> LibraryList = new ArrayList<>();
    public static ArrayList<Borrower> BorrowerList = new ArrayList<>();

    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static void printBook(Book book1) {
        System.out.println(book1.Title + " written by " + book1.Author + " and has the ISBN code of " + book1.ISBN);
    }

    public static void mainMenu() throws IOException {
        System.out.println("Please make a selection: ");
        System.out.println("1: See books");
        System.out.println("2: See borrowers ");
        System.out.println("3: Exit program");

        int menuSelection = Integer.parseInt(input.readLine());
        switch (menuSelection) {
            case (1): //Books
               BookMenu.bookMenu();

            case (2): //Borrowers
                BorrowerMenu.mainMenu();

            case (3): //Exit

            default:
                System.out.println("Not a valid choice");
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        mainMenu();
    }

}
