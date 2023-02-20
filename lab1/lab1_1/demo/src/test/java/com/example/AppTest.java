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
    void setUp() {
        //wordsStack = new DraftStack<>();
        wordsStack = new TqsStack<String>();
    }

    @AfterEach // executa depois de cada teste
    void tearDown() {
        wordsStack = null;
    }
    /**
     * Rigorous Test :-)
     */
    @DisplayName("A stack is empty on construction.")
    @Test
    public void isEmpty()
    {
        assertTrue( wordsStack.isEmpty() );
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


}
