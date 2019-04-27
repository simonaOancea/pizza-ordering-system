/**
 * Created by simona on 04/10/18.
 */

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import model.*;

public class PizzaBuilderTest {

    @Test
    public void shouldBuildSmallMargheritaPizza(){
        Pizza pizza = new PizzaBuilder().margherita().build();
        pizza.setPriceAccordingToSize(Size.SMALL);
        assertEquals("Margherita", pizza.getReceipe());
        assertEquals(15.0, pizza.gettotalPrice(), 0);
    }

    @Test
    public void shouldBuildMeatFeastPizza(){
        Pizza pizza = new PizzaBuilder().meatFeast().build();
        assertEquals("Meat Feast", pizza.getReceipe());
        assertEquals(19, pizza.gettotalPrice(), 0);
    }

    @Test
    public void shouldBuildCustomPizza(){
        Pizza pizza = new PizzaBuilder().withCheese(Cheese.MOZZARELA).withMeat(Meat.SALAMI).build();
        assertEquals("Custom Pizza", pizza.getReceipe());
        assertEquals(7, pizza.gettotalPrice(), 0);

    }




}
