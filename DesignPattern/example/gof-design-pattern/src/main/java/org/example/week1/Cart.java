package org.example.week1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart implements Iterable<Product> {
    private List<Product> products;

    public Cart(){
        this.products = new ArrayList<>();
    }
    public Product getProductAt(int index){
        return products.get(index);
    }
    public void addProduct(Product product){
        products.add(product);
    }
    public int getLength(){
        return products.size();
    }
    @Override
    public Iterator<Product> iterator(){
        return new CartIterator(this);
    }
}
