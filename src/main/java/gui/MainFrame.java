package gui; /**
 * Created by simona on 05/10/18.
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

import java.util.List;

import gui.FormListener;
import gui.FormPanel;
import model.*;


public class MainFrame extends JFrame {
    private TextPanel textPanel;
    private FormPanel formPanel;

    public MainFrame() {
        super("Express Pizza");

        setLayout(new BorderLayout());
        textPanel = new TextPanel();
        formPanel = new FormPanel();


      //sets form listener for regular pizza
        formPanel.setFormListener(new FormListener() {
            @Override
            public void formEventOccurred(PizzaOrderEvent e) {

                String pizzaName = e.getPizzaType();
                String size = e.getSize();
                int quantity = e.getQuantity();
                String discount = e.getDiscount();

                List<String> veggies = e.getVeggies();
                List<String> cheeses = e.getCheeses();
                List<String> meats = e.getMeats();

                float totalAmountToPay = 0;

                if(quantity >= 1) {
                    if (pizzaName.equalsIgnoreCase("Margherita")) {

                        Pizza pizza = new PizzaBuilder().margherita().build();
                        pizza.setPriceAccordingToSize(Size.valueOf(size.toUpperCase())); //converting a UserInput (String) into Enum

                        if(discount.equals("")) {
                            totalAmountToPay = pizza.gettotalPrice() * quantity; // get the total value for a pizza and multiply by quantity

                            textPanel.clearText();
                            textPanel.appendText("Your order is: " + quantity + " " + pizzaName + " " + size + "\n");
                            textPanel.appendText("Total amount to pay: " + totalAmountToPay + " RON" + "\n");

                        }else{
                            totalAmountToPay = pizza.gettotalPrice() * quantity;
                            float discountValue = (float) (totalAmountToPay  * 0.1); //apply a discount of 10%
                            totalAmountToPay = totalAmountToPay - discountValue;

                            textPanel.clearText();
                            textPanel.appendText("Your order is: " + quantity + " " + pizzaName + " " + size + "\n");
                            textPanel.appendText("You have a discount of: 10%" + "\n");
                            textPanel.appendText("Total amount to pay: " + totalAmountToPay + " RON" + "\n");
                        }

                    } else if (pizzaName.equalsIgnoreCase("Meat Feast")) {

                        Pizza pizza = new PizzaBuilder().meatFeast().build();
                        pizza.setPriceAccordingToSize(Size.valueOf(size.toUpperCase()));

                        if(discount.equals("")) {
                            totalAmountToPay = pizza.gettotalPrice() * quantity; // get the total value for a pizza and multiply by quantity

                            textPanel.clearText();
                            textPanel.appendText("Your order is: " + quantity + " " + pizzaName + " " + size + "\n");
                            textPanel.appendText("Total amount to pay: " + totalAmountToPay + " RON" + "\n");

                        }else{
                            totalAmountToPay = pizza.gettotalPrice() * quantity;
                            float discountValue = (float) (totalAmountToPay * 0.1); //apply a discount of 10%
                            totalAmountToPay = totalAmountToPay - discountValue;

                            textPanel.clearText();
                            textPanel.appendText("Your order is: " + quantity + " " + pizzaName + " " + size + "\n");
                            textPanel.appendText("You have a discount of: 10%" + "\n");
                            textPanel.appendText("Total amount to pay: " + totalAmountToPay + " RON" + "\n");
                        }


                    } else if (pizzaName.equalsIgnoreCase("Cheese Feast")) {

                        Pizza pizza = new PizzaBuilder().chesseFeast().build();
                        pizza.setPriceAccordingToSize(Size.valueOf(size.toUpperCase()));

                        if(discount.equals("")) {
                            totalAmountToPay = pizza.gettotalPrice() * quantity; // get the total value for a pizza and multiply by quantity

                            textPanel.clearText();
                            textPanel.appendText("Your order is: " + quantity + " " + pizzaName + " " + size + "\n");
                            textPanel.appendText("Total amount to pay: " + totalAmountToPay + " RON" + "\n");

                        }else{
                            totalAmountToPay = pizza.gettotalPrice() * quantity;
                            float discountValue = (float) (totalAmountToPay * 0.1); //apply a discount of 10%
                            totalAmountToPay = totalAmountToPay - discountValue;

                            textPanel.clearText();
                            textPanel.appendText("Your order is: " + quantity + " " + pizzaName + " " + size + "\n");
                            textPanel.appendText("You have a discount of: 10%" + "\n");
                            textPanel.appendText("Total amount to pay: " + totalAmountToPay + " RON" + "\n");
                        }
                    } else if(pizzaName.equalsIgnoreCase("Veggie Feast")) {

                        Pizza pizza = new PizzaBuilder().veggieFeast().build();
                        pizza.setPriceAccordingToSize((Size.valueOf(size.toUpperCase())));

                        if(discount.equals("")) {
                            totalAmountToPay = pizza.gettotalPrice() * quantity; // get the total value for a pizza and multiply by quantity

                            textPanel.clearText();
                            textPanel.appendText("Your order is: " + quantity + " " + pizzaName + " " + size + "\n");
                            textPanel.appendText("Total amount to pay: " + totalAmountToPay + " RON" + "\n");

                        }else{
                            totalAmountToPay = pizza.gettotalPrice() * quantity;
                            float discountValue = (float) (totalAmountToPay * 0.1); //apply a discount of 10%
                            totalAmountToPay = totalAmountToPay - discountValue;

                            textPanel.clearText();
                            textPanel.appendText("Your order is: " + quantity + " " + pizzaName + " " + size + "\n");
                            textPanel.appendText("You have a discount of: 10%" + "\n");
                            textPanel.appendText("Total amount to pay: " + totalAmountToPay + " RON" + "\n");
                        }

                    }else if(pizzaName.equalsIgnoreCase("Custom Pizza")){ //user has selected custom pizza
                        PizzaBuilder builder = new PizzaBuilder();

                        for (int i = 0; i < veggies.size(); i++) {
                            builder.withVeggie(Veggie.valueOf(veggies.get(i).toUpperCase()));
                        }

                        for (int i = 0; i < cheeses.size(); i++) {
                            builder = new PizzaBuilder().withCheese(Cheese.valueOf(cheeses.get(i).toUpperCase()));
                        }

                        for (int i = 0; i < meats.size(); i++) {
                            builder.withMeat(Meat.valueOf(meats.get(i).toUpperCase().replaceAll("\\s+", "")));
                        }

                        Pizza pizza = builder.build();
                        pizza.setPriceAccordingToSize(Size.valueOf(size.toUpperCase()));
                        totalAmountToPay = pizza.gettotalPrice() * quantity;

                        if(discount.equals("")){
                            textPanel.clearText();
                            textPanel.appendText("Your order is: " + quantity + " " + pizzaName + " " + size + " with:" + "\n");
                            for(int i = 0; i < veggies.size(); i++) {
                                textPanel.appendText(veggies.get(i) + " " +"\n");
                            }
                            for(int i = 0; i < cheeses.size(); i++){
                                textPanel.appendText(cheeses.get(i) + " " + "\n");
                            }
                            for(int i = 0; i < meats.size(); i++){
                                textPanel.appendText(meats.get(i) + "\n");
                            }
                            textPanel.appendText("Total amount to pay: " + totalAmountToPay + " RON" + "\n");

                        }else{

                            float discountValue = (float) (totalAmountToPay * 0.1); //apply a discount of 10%
                            totalAmountToPay = totalAmountToPay - discountValue;

                            textPanel.clearText();
                            textPanel.appendText("Your order is: " + quantity + " " + pizzaName + " " + size + " with:" + "\n");
                            for(int i = 0; i < veggies.size(); i++) {
                                textPanel.appendText(veggies.get(i) + " " +"\n");
                            }
                            for(int i = 0; i < cheeses.size(); i++){
                                textPanel.appendText(cheeses.get(i) + " " + "\n");
                            }
                            for(int i = 0; i < meats.size(); i++){
                                textPanel.appendText(meats.get(i) + "\n");
                            }
                            textPanel.appendText("You have a discount of: 10%" + "\n");
                            textPanel.appendText("Total amount to pay: " + totalAmountToPay + " RON" + "\n");
                        }
                    }
                }
            }
        });


        add(formPanel, BorderLayout.WEST);
        formPanel.setPreferredSize(new Dimension(600, 300));
        add(textPanel, BorderLayout.CENTER);


        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
