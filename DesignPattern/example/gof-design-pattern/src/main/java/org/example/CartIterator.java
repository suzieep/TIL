package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CartIterator implements Iterator<Product> {
    private Cart cart;
    private int index;

    public CartIterator(Cart cart){
        this.cart = cart;
        this.index = 0;
    }

    @Override
    public boolean hasNext(){
        return index< cart.getLength();
    }
    @Override
    public Product next(){
        if(!hasNext()) throw new NoSuchElementException();
        Product product = cart.getProductAt(index);
        index++;
        return product;
    }
}
