package gui;

import java.util.EventListener;

/**
 * Created by simona on 06/10/18.
 */
public interface FormListener extends EventListener {
    public void formEventOccurred(PizzaOrderEvent e);
}
