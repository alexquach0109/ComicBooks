/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicbook;

import static comicbook.ManageComicBook.cbList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class ComicBookStore {

    public static Scanner cin = new Scanner(System.in);
    private static ManageComicBook cbm;
    private static ArrayList<ComicBook> searchListByTitle;
    private static ArrayList<ComicBook> searchListByAuthor;

    public static void main(String[] args) {
        try {
            cbm = new ManageComicBook("src/data/comicbooks.txt");
            cbm.loadComicBooks();

            int function;

            do {
                System.out.println("\n<------ COMICBOOKRENTAL SHOP ------>");
                System.out.println("|   1. Add new comic book.         |");
                System.out.println("|   2. Search book by title.       |");
                System.out.println("|   3. Search book of an author.   |");
                System.out.println("|   4. Update book rental price.   |");
                System.out.println("|   5. Delete comic book.          |");
                System.out.println("|   6. Quit.                       |");
                System.out.println("<---------------------------------->");
                System.out.print("     Please select a funtion: ");

                function = cin.nextInt();
                cin.nextLine();

                switch (function) {
                    case 1:
                        addComicBook();
                        cbm.showAllComicBooks();
                        break;
                    case 2:
                        searchComicBookByTitle();
                        break;
                    case 3:
                        searchComicBookByAuthor();
                        break;
                    case 4:
                        updateRentalPrice();
                        break;
                    case 5:
                        deleteByID();
                        break;
                    case 6:
                        System.out.println("\n_______________________________________________");
                        System.out.println("\n^^^^^ Thank you for using our software <3 ^^^^^");
                        System.out.println("_______________________________________________\n");
                        break;
                }
            } while (function != 6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                cbm.saveComicBooks();
            } catch (Exception e) {
                System.out.println("Exception: Can't save comic books!");
            }
        }
    }

    public static boolean isIdValid(int id) {
        for (ComicBook cmb : ManageComicBook.cbList) {
            if (cmb.getID() == id) {
                return false;
            }
        }
        return true;
    }

    public static boolean isVolValid(String title, int vol) {
        for (ComicBook comicBook : ManageComicBook.cbList) {
            if (comicBook.getTitle().equals(title) && comicBook.getVolume() == vol) {
                return false;
            }
        }
        return true;
    }

    public static void addComicBook() throws ComicBookException, InterruptedException {
        System.out.println("---- COMIC BOOK MANAGEMENT [ADD NEW COMIC BOOK] ----");
        boolean wannaEntryMore = true;
        String agreeToEntryMore = "";
        do {
            int id = 0;
            boolean validInputID = false;
            do {
                try {
                    System.out.print("Please enter ID of comic book: ");
                    id = Integer.parseInt(cin.nextLine());
                    if (id <= 0) {
                        System.out.println("Error: ID must be a positive number!");
                    } else if (!isIdValid(id)) {
                        System.out.println("Error: ID already exists!");
                    } else {
                        validInputID = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid input. Please enter a valid integer ID.");
                }
            } while (!validInputID);

            String title = "";
            do {
                System.out.print("Please enter title of comic book: ");
                title = cin.nextLine();
                if (title.equals("")) {
                    System.out.println("Error: Title of comic book can't be empty");
                }
            } while (title.equals(""));

            double rentalPrice = 0;
            boolean validInputRentalPrice = false;
            do {
                try {
                    System.out.print("Please enter rental price of comic book: ");
                    rentalPrice = Double.parseDouble(cin.nextLine());
                    if (rentalPrice <= 0) {
                        System.out.println("Error: The rental price must be greater than 0!");
                    } else {
                        validInputRentalPrice = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid input. Please enter a valid rental price.");
                }
            } while (!validInputRentalPrice);

            String author = "";
            do {
                System.out.print("Please enter author's name of comic book: ");
                author = cin.nextLine();
                if (author.equals("")) {
                    System.out.println("Error: Author's name of comic book can't be empty");
                }
            } while (author.equals(""));

            int volume = 0;
            boolean validInputVol = false;
            do {
                try {
                    System.out.print("Please enter volume of comic book: ");
                    volume = Integer.parseInt(cin.nextLine());
                    if (volume < 0) {
                        System.out.println("Error: Volume must be a positive number!");
                    } else if (!isVolValid(title, volume)) {
                        System.out.println("Error: This volume of the " + title + " comic book already exists");
                    } else {
                        validInputVol = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid input. Please enter a valid integer.");
                }
            } while (!validInputVol);

            cbm.addComicBook(id, title, rentalPrice, author, volume);
            do {
                System.out.print("Do you want add more comic book? (YES/NO): ");
                agreeToEntryMore = cin.nextLine();
                if (!(agreeToEntryMore.equals("YES") || agreeToEntryMore.equals("NO"))) {
                    System.out.println("Error: You must type 'YES' or 'NO'");
                } else if (agreeToEntryMore.equals("NO")) {
                    System.out.println("\nSaving the comicbooks you imported ...\n");
                    Thread.sleep(1750);
                    wannaEntryMore = false;
                } else if (agreeToEntryMore.equals("YES")) {
                    wannaEntryMore = true;
                }
            } while (!(agreeToEntryMore.equals("YES") || agreeToEntryMore.equals("NO")));
        } while (wannaEntryMore);
    }

    public static void searchComicBookByTitle() throws InterruptedException {
        searchListByTitle = new ArrayList<>();
        String query = "";
        boolean isqueryValid = true;
        do {
            System.out.print("Please enter the title of the comic book you want to search: ");
            query = cin.nextLine();

            if (query.matches("[a-zA-Z\\s]+")) {
                isqueryValid = false;
            } else {
                System.out.println("Invalid input! Please enter only alphabetic characters.");
            }
        } while (isqueryValid);

        for (ComicBook comicBook : ManageComicBook.cbList) {
            if (comicBook.getTitle().toUpperCase().contains(query.toUpperCase())) {
                searchListByTitle.add(comicBook);
            }
        }

        if (searchListByTitle.isEmpty()) {

            System.out.println("\n### Sorry :( , but we couldn't find any results that match the title you're looking for ###");
        } else {
            System.out.printf("\n### We found %d results matching the title you were looking for ###\n", searchListByTitle.size());
            ComicBookTablePrinter printer = new ComicBookTablePrinter();
            printer.printTable(searchListByTitle);
            Thread.sleep(1500);
        }

    }

    public static void searchComicBookByAuthor() throws InterruptedException {
        searchListByAuthor = new ArrayList<>();
        String query = "";
        boolean isqueryValid = true;
        do {
            System.out.print("Please enter author of the comic book you want to search: ");
            query = cin.nextLine();

            if (query.matches("[a-zA-Z\\s]+")) {
                isqueryValid = false;
            } else {
                System.out.println("Invalid input! Please enter only alphabetic characters.");
            }
        } while (isqueryValid);

        for (ComicBook comicBook : cbList) {
            if (comicBook.getAuthor().toLowerCase().equals(query.toLowerCase())) {
                searchListByAuthor.add(comicBook);
            }
        }
        if (searchListByAuthor.isEmpty()) {

            System.out.println("\n### Sorry :( , but we couldn't find any results that match the author you're looking for ###");
        } else {
            System.out.printf("\n### We found %d results matching the author you were looking for ###\n", searchListByAuthor.size());
            ComicBookTablePrinter printer = new ComicBookTablePrinter();
            printer.printTable(searchListByAuthor);
            Thread.sleep(1500);
        }
    }

    public static boolean isExistID(int ID) {
        for (ComicBook comicBook : cbList) {
            if (comicBook.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    public static void updateRentalPrice() throws ComicBookException {
        int userIdEntry = 0;
        double newRentalPrice = 0;

        boolean validInputID = false;
        do {
            try {
                System.out.print("Enter the ID you want to update the rental price for: ");
                userIdEntry = Integer.parseInt(cin.nextLine());
                if (userIdEntry < 0) {
                    System.out.println("Error: ID must be a positive number!");
                } else if (!isExistID(userIdEntry)) {
                    System.out.println("Error: The ID you just entered does not exist!! Try again.");
                } else {
                    validInputID = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid integer ID.");
            }
        } while (!validInputID);

        boolean validInputRentalPrice = false;
        do {
            try {
                System.out.print("Enter the rental price each you want to update: ");
                newRentalPrice = Double.parseDouble(cin.nextLine());
                if (newRentalPrice <= 0) {
                    System.out.println("Error: The rental price must be greater than 0!");
                } else {
                    validInputRentalPrice = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid rental price.");
            }
        } while (!validInputRentalPrice);

        System.out.println("##### Update successful #####\n");

        for (ComicBook comicBook : cbList) {
            if (comicBook.getID() == userIdEntry) {
                comicBook.setBookRentalPrice(newRentalPrice);
                break;
            }
        }

        ComicBookTablePrinter printer = new ComicBookTablePrinter();
        printer.printTable(cbList);
    }

    public static void deleteByID() {
        ComicBookTablePrinter printer = new ComicBookTablePrinter();
        printer.printTable(cbList);
        
        int userIdEntry = 0;
        boolean validInputID = false;
        do {
            try {
                System.out.print("Enter the ID you want to delete: ");
                userIdEntry = Integer.parseInt(cin.nextLine());
                if (userIdEntry < 0) {
                    System.out.println("Error: ID must be a positive number!");
                } else if (!isExistID(userIdEntry)) {
                    System.out.println("Error: The ID you just entered does not exist!! Try again.");
                } else {
                    validInputID = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid integer ID.");
            }
        } while (!validInputID);

        Iterator<ComicBook> iterator = cbList.iterator(); //su dung interface interator
        while (iterator.hasNext()) {
            ComicBook comicBook = iterator.next();
            if (comicBook.getID() == userIdEntry) {
                iterator.remove();
            }
        }

        printer.printTable(cbList);
    }
}
