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
public class ComicBook {
    private int ID;
    private String title;
    private double bookRentalPrice;
    private String author;
    private int volume;

    public ComicBook() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) throws ComicBookException {
        if (ID <= 0) {
            throw new ComicBookException("ID of comic book must be a positive integer");
        } else {
            this.ID = ID;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws ComicBookException {
        if (title.equals("")) {
            throw new ComicBookException("Title of comic book can't be empty");
        } else {
            this.title = title;
        }
    }

    public double getBookRentalPrice() {
        return bookRentalPrice;
    }

    public void setBookRentalPrice(double bookRentalPrice) throws ComicBookException {
        if (bookRentalPrice <= 0) {
            throw new ComicBookException("Rental price of comic book must be a positive number");
        } else {
            this.bookRentalPrice = bookRentalPrice;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) throws ComicBookException {
       
        if (title.equals("")) {
            throw new ComicBookException("Author of comic book can't be empty");
        } else {
            this.author = author;
        }
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) throws ComicBookException {
        
        if (bookRentalPrice <= 0) {
            throw new ComicBookException("Volime of comic book must be a positve number");
        } else {
            this.volume = volume;
        }
    }
    
    public ComicBook(int ID, String title, double bookRentalPrice, String author, int volume) throws ComicBookException {
        this.setID(ID);
        this.setTitle(title);
        this.setBookRentalPrice(bookRentalPrice);
        this.setAuthor(author);
        this.setVolume(volume);
    }
    
    public int printID() {
        return this.ID;
    }
    
    public String printTitle() {
        return this.title;
    }
    
    public double printBookRentalPrice() {
        return this.bookRentalPrice;
    }
    
    public String printAuthor() {
        return this.author;
    }
    
    public int printVolume() {
        return this.volume;
    }
    
    @Override
    public String toString() {
        return String.format("| %d | %s | %.2f | %s | %d |", this.ID, this.title, this.bookRentalPrice, this.author, this.volume);
    }

    void setRentalPrice(int newRentalPrice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
