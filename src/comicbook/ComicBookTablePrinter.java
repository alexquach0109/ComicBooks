/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicbook;

/**
 *
 * @author admin
 */
import java.util.ArrayList;

public class ComicBookTablePrinter {

    public void printTable(ArrayList<ComicBook> comicBooks) {
        int maxIDLength = 3; // Minimum width for ID column
        int maxTitleLength = 5; // Minimum width for Title column
        int maxPriceLength = 12; // Minimum width for Rental Price column
        int maxAuthorLength = 6; // Minimum width for Author column
        int maxVolumeLength = 6; // Minimum width for Volume column

        for (ComicBook comicBook : comicBooks) {
            maxIDLength = Math.max(maxIDLength, String.valueOf(comicBook.getID()).length());
            maxTitleLength = Math.max(maxTitleLength, comicBook.getTitle().length());
            maxPriceLength = Math.max(maxPriceLength, String.format("%.4f", comicBook.getBookRentalPrice()).length());
            maxAuthorLength = Math.max(maxAuthorLength, comicBook.getAuthor().length());
            maxVolumeLength = Math.max(maxVolumeLength, String.valueOf(comicBook.getVolume()).length());
        }

        // Print table header
        printLine(maxIDLength, maxTitleLength, maxPriceLength, maxAuthorLength, maxVolumeLength);
        System.out.printf("| %-" + maxIDLength + "s | %-" + maxTitleLength + "s | %-" + maxPriceLength + "s | %-" + maxAuthorLength + "s | %-" + maxVolumeLength + "s |\n",
                "ID", "TITLE", "RENTAL PRICE", "AUTHOR", "VOLUME");
        printLine(maxIDLength, maxTitleLength, maxPriceLength, maxAuthorLength, maxVolumeLength);

        // Print table rows
        for (ComicBook comicBook : comicBooks) {
            System.out.printf("| %-" + maxIDLength + "d | %-" + maxTitleLength + "s | $%-" + (maxPriceLength - 1) + ".2f | %-" + maxAuthorLength + "s | %-" + maxVolumeLength + "d |\n",
                    comicBook.getID(), comicBook.getTitle(), comicBook.getBookRentalPrice(), comicBook.getAuthor(), comicBook.getVolume());
        }

        // Print table footer
        printLine(maxIDLength, maxTitleLength, maxPriceLength, maxAuthorLength, maxVolumeLength);
    }

    private void printLine(int maxIDLength, int maxTitleLength, int maxPriceLength, int maxAuthorLength, int maxVolumeLength) {
    int totalWidth = maxIDLength + maxTitleLength + maxPriceLength + maxAuthorLength + maxVolumeLength + 15;
    String line = "<" + repeatString("-", maxIDLength + 2) + "+"
                + repeatString("-", maxTitleLength + 2) + "+"
                + repeatString("-", maxPriceLength + 2) + "+"
                + repeatString("-", maxAuthorLength + 2) + "+"
                + repeatString("-", maxVolumeLength + 2) + ">";
    System.out.println(line);
}

    private String repeatString(String str, int n) {
        return new String(new char[n]).replace("\0", str);
    }
}

