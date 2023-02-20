package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private TqsStack<String> wordsStack;

    @BeforeEach // executa antes de cada teste
    public void setUp() {
        //wordsStack = new DraftStack<>();
        wordsStack = new TqsStack<>();
    }

    @AfterEach // executa depois de cada teste
    public void tearDown() {
        wordsStack= null;
    }
    /**
     * Rigorous Test :-)
     */
    @DisplayName("A stack is empty on construction.")
    @Test
    public void isEmpty()
    {
        assertTrue( wordsStack.isEmpty(), "The stack should be empty" );
    }

    @DisplayName(" A stack has size 0 on construction.")
    @Test
    public void size(){
        assertEquals(0, wordsStack.size(), "The stack does not have size 0 on construction.");
    }

    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    public void pushToEmpty(){
        // primeiro verifica se está vazia
        assertTrue( wordsStack.isEmpty() );
        // fazer pushs aleatórios e verificar o seu tamanho
        wordsStack.push("a");
        wordsStack.push("b");
        wordsStack.push("c");
        wordsStack.push("d");
        assertEquals(4, wordsStack.size(), "The push a,b,c,d did not increase the size of the stack.");
    }

    @DisplayName("If one pushes x then pops, the value popped is x.")
    @Test
    //@Disabled //desliga o teste
    public void pushThanPop(){
        wordsStack.push("a");
        String l="b";
        wordsStack.push(l);
        
        assertTrue(wordsStack.pop().equals(l), "The pop did not return the last pushed element ('b').");
    }

    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    @Test
    public void pushThanPeek(){
        
        String l = "b";
        wordsStack.push(l);
        int size = wordsStack.size();

        assertTrue((wordsStack.peek().equals(l)) && (wordsStack.size().equals(size)), "The peek did not return the last pushed element ('b').");
        
    }

    @DisplayName(" If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    public void popN(){
        wordsStack.push("a");
        wordsStack.push("b");
        wordsStack.push("c");
        wordsStack.push("d");

        //validar se o tamanho do stack ficou igual a 4 e se inseriu corretamente
        assertEquals(4, wordsStack.size());
        assertFalse(wordsStack.isEmpty());

        wordsStack.pop();
        wordsStack.pop();
        wordsStack.pop();
        wordsStack.pop();
        assertTrue(wordsStack.isEmpty(), "TThe stack should be empty after 4 pops, because the size of the stack is 4.");
    }


    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    @Test
    public void popEmpty(){
        assertThrows(NoSuchElementException.class, () -> wordsStack.pop(), "Popping from an empty stack should throw a NoSuchElementException");
    }

    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    public void peekEmpty(){
        assertThrows(NoSuchElementException.class, () -> wordsStack.peek(), "Peeking into an empty stack should throw a NoSuchElementException");
    }

    @DisplayName("For bounded stacks only: pushing onto a full stack does throw an IllegalStateException")
    @Test
    public void pushFull(){
        wordsStack = new TqsStack<String>(2);
        wordsStack.push("a");
        wordsStack.push("b");
        assertThrows(IllegalStateException.class, () -> wordsStack.push("c"), "Pushing onto a full stack should throw an IllegalStateException");
    }


}
