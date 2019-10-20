package com.syapp.todo.basic.datastructure;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<T> {

    private static final int INITIAL_CAPACITY = 10;

    private int elementCount = 0;

    private Object[] dataArray = new Object[INITIAL_CAPACITY];

    public void push(T data) {
        if (elementCount >= dataArray.length) {
            dataArray = Arrays.copyOf(dataArray, dataArray.length + INITIAL_CAPACITY);
        }

        dataArray[elementCount++] = data;
    }

    public T pop() {
        if (elementCount <= 0) {
            throw new EmptyStackException();
        }

        Object data = peek();
        dataArray[--elementCount] = null;

        return (T)data;
    }

    public T peek() {
        if (elementCount <= 0) {
            throw new EmptyStackException();
        }

        return (T)dataArray[elementCount - 1];
    }

    public boolean isEmpty() {
        return elementCount == 0;
    }

    public int indexOf(T data) {
        if (isEmpty()) return -1;

        if (data == null) {
            for (int i = 0; i < dataArray.length; i++) {
                if (dataArray[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < dataArray.length; i++) {
                if (data.equals(dataArray[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @NotNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Object o : dataArray) {
            if (o == null)
                break;

            builder.append(o).append(", ");
        }
        return "Stack : " + builder.toString();
    }
}
