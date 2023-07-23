package org.example;

public class CartTotalPrice {
    private Cart cart;
    public CartTotalPrice(Cart cart){
        this.cart = cart;
    }
    public int getCartTotalPrice(){
        int cartTotalPrice = 0;
        //확장 for문을 사용하는 방법
        for(Product product:cart){
            cartTotalPrice+=product.getPrice();
        }
        return cartTotalPrice;
    }
}
