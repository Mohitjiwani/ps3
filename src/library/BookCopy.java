package library;

import java.util.List;

/**
 * BookCopy is a mutable type representing a particular copy of a book that is held in a library's
 * collection.
 */
public class BookCopy {

    Condition condition;
    private final String title;
    private final List<String> authors;
    private final int year;
   
    // rep invariant:
    // same rep invarients as applicable on books. 
    // TODO: abstraction function
    // TODO: safety from rep exposure argument
    
    public static enum Condition {
        GOOD, DAMAGED, LOST
    };
    
    /**
     * Make a new BookCopy, initially in good condition.
     * @param book the Book of which this is a copy
     */
    public BookCopy(Book book) {
        
        condition = Condition.GOOD;
        this.authors =  book.getAuthors();
        this.title = book.getTitle();
        this.year = book.getYear();
        
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
    }
    
    /**
     * @return the Book of which this is a copy
     */
    public Book getBook() {
        
        Book originalBook = new Book(this.title, this.authors, this.year);
        
        return originalBook;
        //throw new RuntimeException("not implemented yet");
    }
    
    public String getTitle() {
        
        return this.title;
        //throw new RuntimeException("not implemented yet");
    }
    
    public List<String> getAuthors() {
        
        //make a defensive copy because authors is mutable data type
        List<String> list_of_authors = this.authors;
        
        return list_of_authors;
        //throw new RuntimeException("not implemented yet");
    }
    
    public int getYear() {
        
        return this.year;
       // throw new RuntimeException("not implemented yet");
    }
    
    /**
     * @return the condition of this book copy
     */
    public Condition getCondition() {
        
        return this.condition;
        //throw new RuntimeException("not implemented yet");
    }

    /**
     * Set the condition of a book copy.  This typically happens when a book copy is returned and a librarian inspects it.
     * @param condition the latest condition of the book copy
     */
    public void setCondition(Condition condition) {
        
        this.condition = condition;
        
        checkRep();
        //throw new RuntimeException("not implemented yet");
    }
    
    /**
     * @return human-readable representation of this book that includes book.toString()
     *    and the words "good" or "damaged" depending on its condition
     */
    public String toString() {
        
        return "(" + this.condition.toString().toLowerCase() + this.title + " " + this.authors.toString() + " " + this.year + ")";
        //throw new RuntimeException("not implemented yet");
    }

    // uncomment the following methods if you need to implement equals and hashCode,
    // or delete them if you don't
    // @Override
    // public boolean equals(Object that) {
    //     throw new RuntimeException("not implemented yet");
    // }
    // 
    // @Override
    // public int hashCode() {
    //     throw new RuntimeException("not implemented yet");
    // }


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
