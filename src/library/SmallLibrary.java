package library;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import library.BookCopy.Condition;


/** 
 * SmallLibrary represents a small collection of books, like a single person's home collection.
 */
public class SmallLibrary implements Library {

    // This rep is required! 
    // Do not change the types of inLibrary or checkedOut, 
    // and don't add or remove any other fields.
    // (BigLibrary is where you can create your own rep for
    // a Library implementation.)

    // rep
    private Set<BookCopy> inLibrary;
    private Set<BookCopy> checkedOut;
    
    // rep invariant:
    //    the intersection of inLibrary and checkedOut is the empty set
    //
    // abstraction function:
    //    represents the collection of books inLibrary union checkedOut,
    //      where if a book copy is in inLibrary then it is available,
    //      and if a copy is in checkedOut then it is checked out

    // TODO: safety from rep exposure argument
    
    public SmallLibrary() {
        
        this.inLibrary = new HashSet<BookCopy>();
        this.checkedOut = new HashSet<BookCopy>();
        
    }
    
    // assert the rep invariant
    private void checkRep() {
        
        //the intersection of inLibrary and checkedOut is the empty set
        for(BookCopy book_copy : this.inLibrary)
        {
          assert this.checkedOut.contains(book_copy) == false;  
        }
        //throw new RuntimeException("not implemented yet");
    }

    @Override
    public BookCopy buy(Book book) {
        
        //create BookCopy instance of book(i.e adding condition)
        BookCopy newBook = new BookCopy(book);
        
        //add that copy to this.inLibrary
        this.inLibrary.add(newBook);
        
        checkRep();
        
        return newBook;
        
        //throw new RuntimeException("not implemented yet");
    }
    
    @Override
    public void checkout(BookCopy copy) {
        
        //first check if the copy is available in inLibrary
        if(this.inLibrary.contains(copy))
        {
            this.checkedOut.add(copy);
            
            this.inLibrary.remove(copy);
        }
        
        checkRep();
        
        //throw new RuntimeException("not implemented yet");
    }
    
    @Override
    public void checkin(BookCopy copy) {
        
        //first verify that copy is not available in inLibrary
        if(this.inLibrary.contains(copy) == false)
        {
            this.inLibrary.add(copy);
            
            this.checkedOut.remove(copy);
        }
        
        checkRep();
        //throw new RuntimeException("not implemented yet");
    }
    
    @Override
    public boolean isAvailable(BookCopy copy) {
        
        return this.inLibrary.contains(copy);
        //throw new RuntimeException("not implemented yet");
    }
    
    @Override
    public Set<BookCopy> allCopies(Book book) {
        
        //copy of input book to be compared with evry inLibrary and Checkout
        BookCopy input_book = new BookCopy(book);
        
        //set<BookCopy> to return
        Set<BookCopy> all_copies= new HashSet<>();
        
        for(BookCopy each : this.inLibrary)
        {
            if(each.getTitle().equals(input_book.getTitle()) && (each.getYear() == input_book.getYear())) 
            {
                all_copies.add(each);
            }
        }
        
        for(BookCopy Each : this.checkedOut)
        {
            if(Each.getTitle().equals(input_book.getTitle()) && (Each.getYear() == input_book.getYear())) 
            {
                all_copies.add(Each);
            }
        }
        
        return all_copies;
        //throw new RuntimeException("not implemented yet");
    }
    
    @Override
    public Set<BookCopy> availableCopies(Book book) {
        
        BookCopy input_book = new BookCopy(book);
        
        Set<BookCopy> availCopies = new HashSet<>();
        
        for(BookCopy each : this.inLibrary)
        {
            if(each.getTitle().equals(input_book.getTitle()) && (each.getYear() == input_book.getYear())) 
            {
                availCopies.add(each);
            }
        }
        
        return availCopies;
        //throw new RuntimeException("not implemented yet");
    }

    /*
     * Keyword matching and ranking is underdetermined, but at the very least must support: 
     *     - exact matching of title and author: i.e., if a copy of a book is in the library's 
     *           collection, then find(book.getTitle()) and find(book.getAuthors().get(i)) 
     *           must include book among the results.
     *     - date ordering: if two matching books have the same title and author but different
     *           publication dates, then the newer book should appear earlier on the list.
     * 
     */
    @Override
    public List<Book> find(String query) {
        
        //Set<Book> found contains sorted books according to year
        Set<Book> found = new TreeSet<>(new Comparator<Book>() {
            @Override public int compare(Book b1, Book b2) {
                if (b1.getYear() >= b2.getYear()) {
                    return b1.getYear();
                }
                return b2.getYear();
            }});;
        
        //iterate through inLibrary collection
        for(BookCopy insideLib : this.inLibrary)
        {
            //find out every matching title or author
            if(insideLib.getTitle().contentEquals(query) || insideLib.getAuthors().contains(query))
            {
                //and add it to List<Book>
                found.add(insideLib.getBook());
            }
        }
        
        
        List<Book> foundList = new ArrayList<>();
        
        //copy the found into foundList
        for(Book book: found)
        {
            foundList.add(book);
        }
        
        return foundList;
        
    }
    
    
    /**
     * Declare a copy of a book as lost from the library.  A copy can be declared lost if it is stolen
     * without being checked out, or if a borrower checks it out but never returns it. 
     * @param copy BookCopy to declare lost.  Must have been previously returned from buy() on this library.
     */
    @Override
    public void lose(BookCopy copy) {
        
        //change condition of copy to LOST when it is stolen
        //i.e not checkout, but not present in inLibrary and Checkout
        if((!this.inLibrary.contains(copy)) && (!this.checkedOut.contains(copy))) {
            
            //change condition of copy
            copy.setCondition(Condition.LOST);
        }
        
        //if a borrower checks it out but never returns it
        else if(!this.inLibrary.contains(copy)) {
            if(this.checkedOut.contains(copy)) {
                copy.setCondition(Condition.LOST);
            }
        }
        
    }


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
