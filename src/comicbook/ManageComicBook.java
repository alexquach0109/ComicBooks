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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ManageComicBook {
    private String FILE;
    private int numberOfCBook;
    public static ArrayList<ComicBook> cbList;
    
    public ManageComicBook(String FILE) throws ComicBookException {
        if(FILE.equals("")) {
            throw new ComicBookException("The URL of comic book data file can't be empty!");
        } else {
            this.FILE = FILE;
            
            this.cbList = new ArrayList<ComicBook>(); 
            
            this.numberOfCBook = 0;
        }
    }
    
    public void loadComicBooks() throws IOException, ComicBookException {
        File cbFile = new File(FILE);
        
        if(!cbFile.exists()) {
            cbFile.createNewFile();
            System.out.println("The data file comicbooks.txt is not exits. "
                    + "Creating new data file comicbooks.txt..."
                    + "Done!");
            
            this.numberOfCBook = 0;
        } else {
            System.out.println("\nThe data file comicbooks.txt is found"
                    + "Data of comicbook list is loading...");
            
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            try {
                int id, vol;
                double rentalPrice;
                String title, author;
                this.numberOfCBook = Integer.parseInt(br.readLine());
                for(int i = 0; i < this.numberOfCBook; i++) {
                    id = Integer.parseInt(br.readLine());
                    title = br.readLine();
                    rentalPrice = Double.parseDouble(br.readLine());
                    author = br.readLine();
                    vol = Integer.parseInt(br.readLine());

                    this.cbList.add(new ComicBook(id, title, rentalPrice, author, vol));
                }
            }finally{
                br.close();
            }
                System.out.println("Done! [" + this.numberOfCBook + " ComicBooks]");
        }
    }
    
    
    public int getSize() {
        return this.numberOfCBook;
    }
    
    public void addComicBook(int id, String title, double rentalPrice, String author, int volume) throws ComicBookException {
        this.cbList.add(new ComicBook(id, title, rentalPrice, author, volume));
    }
    
    public void showAllComicBooks() {
        ComicBookTablePrinter printer = new ComicBookTablePrinter();
        printer.printTable(cbList);
    }
    
    public void saveComicBooks() throws IOException {
        FileWriter fw = new FileWriter(new File(FILE), false);
        try {
            System.out.println("Comic books is saving into data file comicbooks.txt...");
            
            fw.append(String.valueOf(cbList.size() + "\n"));
            for (ComicBook comicBook : cbList) {
                int id = comicBook.getID();
                String title = comicBook.getTitle();
                double rentalPrice = comicBook.getBookRentalPrice();
                String author = comicBook.getAuthor();
                int vol = comicBook.getVolume();
                
                fw.append(String.valueOf(id) + "\n");
                fw.append(title + "\n");
                fw.append(String.valueOf(rentalPrice) + "\n");
                fw.append(author + "\n");
                fw.append(vol + "\n");
            }
        }finally {
            fw.close();
            System.out.println("Save Successfully!");
        }
    }
}
