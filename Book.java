/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author frith
 */

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;
import javafx.scene.control.Button;
import view.Library2Controller;


/**
 * An dummy implementation of a book model, to 
 * demonstrate a JavaFX form.
 * 
 * @author anderslm@kth.se
 */
public class Book {
    
    public enum Genre { Crime, Mystery, Romance, Drama, Memoir, Fantasy,
    Learning}

    private String title;
    private String isbn; // check format
    private Genre genre;
    private String publisher;
    private ArrayList<Author> theAuthors;
    private Button deleteButton;
    private Button reviewButton;
    

    // A simplified regexp for isbn, 10 digit number, 
    // last digit may also be 'X' 
    private static final Pattern ISBN_PATTERN = 
            Pattern.compile("^\\d{12}[\\d|X]$"); 
    
    public static boolean isValidIsbn(String isbn) {
        return ISBN_PATTERN.matcher(isbn).matches();
        
    }
    
    public Book(String isbn, Genre genre, String title, String publisher,
            ArrayList<Author> authorsInput) {
        if(!isValidIsbn(isbn)) 
            throw new IllegalArgumentException("not a valid isbn");
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        
        this.theAuthors = new ArrayList<Author>();
        //How do we add more authors when creating a book like during deSer?
        for (Author authors : authorsInput) {
                this.theAuthors.add(authors);
                  
        } 
        
        this.deleteButton = new Button("Delete Book");
        
       /* this.deleteButton.setOnAction(e->{
            System.out.println("Deleting Book with ISBN: " + this.isbn);
            Library2Controller.handleDeleteBookButton();
        });*/
                
                
        this.reviewButton = new Button("See Reviews");
        
    }
    
    public void setIsbn(String newISBN){ 
        this.isbn = newISBN;
    }
    
    public void setGenre(Genre newGenre){ 
        this.genre = newGenre;
    }
    
    public void setTitle(String newTitle){
        this.title = newTitle;
    }
    
    public void setPublisher(String newPublisher){ 
        this.publisher = newPublisher;
    }
    
    public void setDeleteButton(Button newDeleteButton){
        this.deleteButton = newDeleteButton;
    }
    
    public void setReviewButton(Button newReviewButton){
        this.reviewButton = newReviewButton;
    }    
    
    
    
    public String getIsbn()     { return isbn; }
    public String getTitle()    { return title; }
    public Genre getGenre()     { return genre; }
    public String getPublisher() { return publisher; }
    public ArrayList<Author> getTheAuthors() { return theAuthors; }
    public Button getDeleteButton(){ return this.deleteButton; }
    public Button getReviewButton(){ return this.reviewButton; }
    

    
        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book other = (Book) o;
        return isbn.equals(other.isbn) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }    
    
    @Override
    public String toString() {
        return "| " + isbn + "  | " + genre +"  | " + title + "   |  " 
                + publisher + "   |" + "   |   \n Author/s: " + theAuthors;
    }
}