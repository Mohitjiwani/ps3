package library;

import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for Book ADT.
 */
public class BookTest {

    /*
     * Testing strategy
     * ==================
     * 
     * 1.Test hashCode() and Equals()
     * 
     */
    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
    @Test
    public void testhash_equalTest(){
        Book book1 = new Book("thinking", Arrays.asList("daniel", "rob"), 1990);
        Book book2 = new Book("Thinking", Arrays.asList("Daniel", "Rob"), 1990);
        assertEquals(true, book1.equals(book2));
        assertEquals(book1.hashCode(), book2.hashCode());
    }
    
    @Test
    public void testExampleTest() {
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        assertEquals("This Test Is Just An Example", book.getTitle());
        assertEquals(3, book.getAuthors().size());
        assertEquals(1990, book.getYear());
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
