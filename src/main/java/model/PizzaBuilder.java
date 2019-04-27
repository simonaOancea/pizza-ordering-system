package model;

/**
 * Created by simona on 04/10/18.
 */
public class PizzaBuilder {
    Pizza pizza = new Pizza();
    Ingredient ingredient = new Ingredient();


    public PizzaBuilder margherita(){
        pizza.setIngredient(ingredient);
        pizza.setReceipe("Margherita");
        pizza.ingredient.addVeggie(Veggie.TOMATOES);
        pizza.ingredient.addCheese(Cheese.MOZZARELA);
        pizza.addToPrice(Veggie.TOMATOES.getVeggiePrice());
        pizza.addToPrice(Cheese.MOZZARELA.getCheesePrice());

        return this;
    }

    public PizzaBuilder meatFeast(){
        pizza.setIngredient(ingredient);
        pizza.setReceipe("Meat Feast");
        pizza.ingredient.addMeat(Meat.HAM);
        pizza.ingredient.addMeat(Meat.SALAMI);
        pizza.ingredient.addMeat(Meat.SAUSAGE);
        pizza.ingredient.addMeat(Meat.SPICYBEAF);
        pizza.addToPrice(Meat.HAM.getMeatPrice());
        pizza.addToPrice(Meat.SALAMI.getMeatPrice());
        pizza.addToPrice(Meat.SAUSAGE.getMeatPrice());
        pizza.addToPrice(Meat.SPICYBEAF.getMeatPrice());
        return this;

    }

    public PizzaBuilder veggieFeast(){
        pizza.setIngredient(ingredient);
        pizza.setReceipe("Veggie Feast");
        pizza.ingredient.addVeggie(Veggie.TOMATOES);
        pizza.ingredient.addVeggie(Veggie.PEPPERS);
        pizza.ingredient.addVeggie(Veggie.MUSHROOMS);
        pizza.ingredient.addVeggie(Veggie.SWEETCORN);
        pizza.addToPrice(Veggie.TOMATOES.getVeggiePrice());
        pizza.addToPrice(Veggie.PEPPERS.getVeggiePrice());
        pizza.addToPrice(Veggie.MUSHROOMS.getVeggiePrice());
        pizza.addToPrice(Veggie.SWEETCORN.getVeggiePrice());
        return this;
    }

    public PizzaBuilder chesseFeast(){
        pizza.setIngredient(ingredient);
        pizza.setReceipe("Chesse Feast");
        pizza.ingredient.addCheese(Cheese.MOZZARELA);
        pizza.ingredient.addCheese(Cheese.CHEDDAR);
        pizza.ingredient.addCheese(Cheese.RICOTTA);
        pizza.addToPrice(Cheese.MOZZARELA.getCheesePrice());
        pizza.addToPrice(Cheese.CHEDDAR.getCheesePrice());
        pizza.addToPrice(Cheese.RICOTTA.getCheesePrice());
        return this;
    }


    public PizzaBuilder withCheese(Cheese cheese){
        pizza.setIngredient(ingredient);
        pizza.setReceipe("Custom Pizza");
        pizza.ingredient.addCheese(cheese);
        pizza.addToPrice(cheese.getCheesePrice());
        return this;
    }

    public PizzaBuilder withVeggie(Veggie veggie){
        pizza.setIngredient(ingredient);
        pizza.setReceipe("Custom Pizza");
        pizza.ingredient.addVeggie(veggie);
        pizza.addToPrice(veggie.getVeggiePrice());
        return this;
    }

    public PizzaBuilder withMeat(Meat meat){
        pizza.setIngredient(ingredient);
        pizza.setReceipe("Custom Pizza");
        pizza.ingredient.addMeat(meat);
        pizza.addToPrice(meat.getMeatPrice());
        return this;
    }

    public Pizza build(){
        return pizza;
    }

    public double finalPrice(){
        return pizza.gettotalPrice();
    }








}
