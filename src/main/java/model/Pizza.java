package model;

/**
 * Created by simona on 04/10/18.
 */

public class Pizza {

    public Ingredient ingredient;
    private float totalPrice = 0;
    private String receipe;
    private Size size;


    public void setReceipe(String receipe){

        this.receipe = receipe;
    }

    public String getReceipe(){

        return this.receipe;
    }

    public float gettotalPrice(){

        return totalPrice;
    }

    public void addToPrice(float price){

        totalPrice = totalPrice + price;
    }

    public Ingredient getIngredient(){

        return this.ingredient;
    }

    public void setIngredient(Ingredient ingredient){

        this.ingredient = ingredient;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setPriceAccordingToSize(Size size){

        if(size == Size.SMALL){
            this.addToPrice(Size.SMALL.getSizePrice());
        }else if(size == Size.MEDIUM){
            this.addToPrice(Size.MEDIUM.getSizePrice());
        }else if(size == Size.LARGE){
            this.addToPrice(Size.LARGE.getSizePrice());
        }
    }

}
