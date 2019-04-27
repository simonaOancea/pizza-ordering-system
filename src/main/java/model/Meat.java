package model;

/**
 * Created by simona on 04/10/18.
 */
public enum Meat {
    HAM{
        public float getMeatPrice(){
            return 5;
        }
    },
    SALAMI{
        public float getMeatPrice(){
            return 4;
        }
    },
    SAUSAGE{
        public float getMeatPrice(){
            return 4;
        }
    },
    SPICYBEAF{
        public float getMeatPrice(){
            return 6;
        }
    };

    public abstract float getMeatPrice();

}
