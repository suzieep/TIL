package org.example;

public class CalculateCart extends Bill {
    private CartTotalPrice cartTotalPrice;
    public CalculateCart(CartTotalPrice cartTotalPrice){
        this.cartTotalPrice = cartTotalPrice;
    }
    @Override
    public int getTotalPriceInWon(){
        return cartTotalPrice.getCartTotalPrice()*1500;
    }
}
