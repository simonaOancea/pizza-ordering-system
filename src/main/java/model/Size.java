package model;

/**
 * Created by simona on 06/10/18.
 */
public enum Size {
    SMALL {
        @Override
        public float getSizePrice() {
            return 10;
        }
    }, MEDIUM {
        @Override
        public float getSizePrice() {
            return 15;
        }
    }, LARGE {
        @Override
        public float getSizePrice() {
            return 20;
        }
    };

    public abstract float getSizePrice();

}
