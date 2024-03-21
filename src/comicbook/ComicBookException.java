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
public class ComicBookException extends Exception{
    public ComicBookException(String message) {
        super(message);
    }
    
    @Override
    public String getMessage() {
        return "AnswerException: " + super.getMessage();
    }
}
