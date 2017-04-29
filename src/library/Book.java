package library;

import java.util.List;


/**
 * Book is an immutable type representing an edition of a book -- not the physical object, 
 * but the combination of words and pictures that make up a book.  Each book is uniquely
 * identified by its title, author list, and publication year.  Alphabetic case and author 
 * order are significant, so a book written by "Fred" is different than a book written by "FRED".
 */
public class Book {

    // TODO: rep
    private final String title;
    private final List<String> authors;
    private final int year;
    
    // rep invariant :
    // Title must contain at least one non-space character
    // authors name is case - sensitive
    // Year belongs to Common Era calendar
    
    // abstraction function:
    // Given a title,author list,publication year
    // creates Book object 
    
    // safety from rep exposure argument:
    // all fields are private.
    // title and year are immutable
    // For list to be immutable, will make defensive copies while returning
    
    /**
     * Make a Book.
     * @param title Title of the book. Must contain at least one non-space character.
     * @param authors Names of the authors of the book.  Must have at least one name, and each name must contain 
     * at least one non-space character.
     * @param year Year when this edition was published in the conventional (Common Era) calendar.  Must be nonnegative. 
     */
    public Book(String title, List<String> authors, int year) {
        
        this.title = title;
        this.year = year;
        this.authors = authors;
        
        checkRep();
        //throw new RuntimeException("not implemented yet");
    }
    
    // assert the rep invariant
    private void checkRep() {
        
        //title contains atleast one non-space character
        assert this.title.contentEquals("\\s+") == false;
        
        //list<authors> must have atleast one name
        assert this.authors.size() > 0;
        
        //Year belongs to conventional calender
        assert this.year > 0;
        //throw new RuntimeException("not implemented yet");
    }
    
    /**
     * @return the title of this book
     */
    public String getTitle() {
        
        return this.title;
        //throw new RuntimeException("not implemented yet");
    }
        
    /**
     * @return the authors of this book
     */
    public List<String> getAuthors() {
        
        //make a defensive copy because authors is mutable data type
        List<String> list_of_authors = this.authors;
        
        return list_of_authors;
        //throw new RuntimeException("not implemented yet");
    }

    /**
     * @return the year that this book was published
     */
    public int getYear() {
        
        return this.year;
       // throw new RuntimeException("not implemented yet");
    }

    /**
     * @return human-readable representation of this book that includes its title,
     *    authors, and publication year
     */
    public String toString() {
        
        return "(" + this.title + " " + this.authors.toString() + " " +this.year + ")";
        //throw new RuntimeException("not implemented yet");
    }

    @Override
    public boolean equals(Object that) {
        
        if(!(that instanceof Book)){
            return false;
        }
        Book thats = (Book) that;
        return thats.title == this.title;
        
        //throw new RuntimeException("not implemented yet");
    }
    
    @Override
    public int hashCode() {
        
        
        return   this.title.hashCode();
    }



    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
