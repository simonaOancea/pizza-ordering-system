package model;

/**
 * Created by simona on 04/10/18.
 */
public enum Veggie {
    TOMATOES{
        @Override
        public float getVeggiePrice(){
            return 2;
        }
    },
    PEPPERS{
        @Override
        public float getVeggiePrice(){
            return 2;
        }
    },
    MUSHROOMS{
        @Override
        public float getVeggiePrice(){
            return 3;
        }
    },
    SWEETCORN{
        @Override
        public float getVeggiePrice(){
            return 3;
        }
    };

    public abstract float getVeggiePrice();

}
