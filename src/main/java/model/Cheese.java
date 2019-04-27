package model;

/**
 * Created by simona on 04/10/18.
 */
public enum Cheese {

    MOZZARELA{
        public float getCheesePrice(){return 3;}
    },
    CHEDDAR{
        public float getCheesePrice(){
            return 4;
        }
    },
    RICOTTA{
        public float getCheesePrice(){
            return 4;
        }
    };

    public abstract float getCheesePrice();





}
