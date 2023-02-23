package com.example;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack<T> {

    private LinkedList<T> stack= new LinkedList<T>();
    private Integer n= null;

    public TqsStack() {
        this.stack = new LinkedList<T>();
    }

    public TqsStack(int n){
        this.stack = new LinkedList<T>();
        this.n = n;
    }

    public Integer size() {
        return this.stack.size();
    }

    public void push(T t) {
        stack.addLast(t);
    }

    public T pop() {
        return this.stack.pop();
    }

    public T peek() {
        return this.stack.getLast();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public void clear() {
        this.stack.clear();
    }

}
