package model; /**
 * Created by simona on 05/10/18.
 */

import java.util.ArrayList;
import java.util.Collections;


public class Ingredient {

    private ArrayList<String> cheeses = new ArrayList<>();
    private ArrayList<String> meats = new ArrayList<>();
    private ArrayList<String> veggies = new ArrayList<>();


    public void addCheese(Cheese cheese){

        cheeses.add(cheese.toString());
    }

    public void removeCheese(Cheese cheese){

        cheeses.remove(cheeses.toString());
    }

    public void addMeat(Meat meat){

        meats.add(meat.toString());
    }

    public void removeMeat(Meat meat){

        meats.remove(meat.toString());
    }

    public void addVeggie(Veggie veggie){

        veggies.add(veggie.toString());
    }

    public void removeVeggie(Veggie veggie){

        veggies.remove(veggie.toString());
    }

}
