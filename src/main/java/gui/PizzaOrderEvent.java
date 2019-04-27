package gui;

import java.util.EventObject;
import java.util.List;

/**
 * Created by simona on 06/10/18.
 */
public class PizzaOrderEvent extends EventObject {

    private String pizzaType;
    private String size;
    private int quantity;
    private String discount;

    private List<String> veggies;
    private List<String> cheeses;
    private List<String> meats;


    public PizzaOrderEvent(Object source) {

        super(source);
    }

    public PizzaOrderEvent(Object source, String pizzaType, String size, int quantity){
        super(source);
        this.pizzaType = pizzaType;
        this.size = size;
        this.quantity = quantity;
    }

    public PizzaOrderEvent(Object source, String pizzaType, String size, int quantity, String discount) {
        super(source);
        this.pizzaType = pizzaType;
        this.size = size;
        this.quantity = quantity;
        this.discount = discount;
    }

    public PizzaOrderEvent(Object source, String pizzaType, String size, int quantity,
                           List<String> veggies, List<String> cheeses, List<String> meats) {
        super(source);
        this.pizzaType = pizzaType;
        this.size = size;
        this.quantity = quantity;
        this.veggies = veggies;
        this.cheeses = cheeses;
        this.meats = meats;
    }

    public PizzaOrderEvent(Object source, String pizzaType, String size, int quantity, String discount,
                           List<String> veggies, List<String> cheeses, List<String> meats) {
        super(source);
        this.pizzaType = pizzaType;
        this.size = size;
        this.quantity = quantity;
        this.discount = discount;
        this.veggies = veggies;
        this.cheeses = cheeses;
        this.meats = meats;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public List<String> getVeggies() {
        return veggies;
    }

    public void setVeggies(List<String> veggies) {
        this.veggies = veggies;
    }

    public List<String> getCheeses() {
        return cheeses;
    }

    public void setCheeses(List<String> cheeses) {
        this.cheeses = cheeses;
    }

    public List<String> getMeats() {
        return meats;
    }

    public void setMeats(List<String> meats) {
        this.meats = meats;
    }

}
