package org.example.week1;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Cart cart = new Cart();
        cart.addProduct(new Product("apple",1));
        cart.addProduct(new Product("orange",2));
        cart.addProduct(new Product("wallet",100));
        cart.addProduct(new Product("umbrella",50));

        //명시적 iterator 사용
        Iterator<Product> iterator = cart.iterator();
        while(iterator.hasNext()){
            Product product = iterator.next();
            System.out.println(product.getName()+" $"+product.getPrice());
        }
        System.out.println();

        //확장 for문으로 전체 누계(Adaptor)
        CartTotalPrice cartTotalPrice = new CartTotalPrice(cart);
        System.out.println("$"+cartTotalPrice.getCartTotalPrice());
        Bill bill = new CalculateCart(cartTotalPrice);
        System.out.println(bill.getTotalPriceInWon()+"원");



    }
}