package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import java.util.*;


public class FormPanel extends JPanel {

    private JLabel nrOfPizzaLabel;
    private JLabel discountLabel;
    private JLabel pizzaTypeLabel;
    private JLabel pizzaSizeLabel;
    private JLabel veggiesLabel;
    private JLabel cheesesLabel;
    private JLabel meatsLabel;

    private JTextField nrOfPizzaField;
    private JTextField discountField;

    private JButton submit;
    private JButton newOrder;

    private JComboBox pizzaType;
    private JComboBox pizzaSize;

    private JCheckBox[] veggieIngredients = {new JCheckBox("Tomatoes"), new JCheckBox("Peppers"), new JCheckBox("Mushrooms"), new JCheckBox("Sweetcorn")};
    private JCheckBox[] cheeseIngredients = {new JCheckBox("Cheddar"), new JCheckBox("Ricotta"), new JCheckBox("Mozzarela")};
    private JCheckBox[] meatIngredients = {new JCheckBox("Ham"), new JCheckBox("Salami"), new JCheckBox("Sausage"), new JCheckBox("Spicy Beaf")};

    private Set<String> veggiesSelected;
    private Set<String> cheesesSelected;
    private Set<String> meatsSelected;

    private FormListener formListener;


    public FormPanel() {

        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        nrOfPizzaLabel = new JLabel("Quantity: ");
        discountLabel = new JLabel("Discount Code: ");
        pizzaTypeLabel = new JLabel("Pizza Type");
        pizzaSizeLabel = new JLabel("Pizza Size");
        veggiesLabel = new JLabel("Veggies");
        cheesesLabel = new JLabel("Chesses");
        meatsLabel = new JLabel("Meats");

        nrOfPizzaField = new JTextField(10);
        discountField = new JTextField(10);

        pizzaType = new JComboBox();
        pizzaSize = new JComboBox();

        veggiesSelected = new LinkedHashSet<>();
        cheesesSelected = new LinkedHashSet<>();
        meatsSelected = new LinkedHashSet<>();

        submit = new JButton("Submit Order");
        submit.setSize(100,10);
        newOrder = new JButton("New Order");
        newOrder.setSize(100, 100);


        //Set up combo box for pizza type
        DefaultComboBoxModel pizzaTypeModel = new DefaultComboBoxModel();
        pizzaTypeModel.addElement(new PizzaType(0, "Margherita"));
        pizzaTypeModel.addElement(new PizzaType(1, "Meat Feast"));
        pizzaTypeModel.addElement(new PizzaType(2, "Veggie Feast"));
        pizzaTypeModel.addElement(new PizzaType(3, "Cheese Feast"));
        pizzaTypeModel.addElement(new PizzaType(4, "Custom Pizza"));

        pizzaType.setModel(pizzaTypeModel);
        pizzaType.setPreferredSize(new Dimension(140, 70));

        //Set up combo box for pizza size
        DefaultComboBoxModel pizzaSizeModel = new DefaultComboBoxModel();
        pizzaSizeModel.addElement("Small");
        pizzaSizeModel.addElement("Medium");
        pizzaSizeModel.addElement("Large");

        pizzaSize.setModel(pizzaSizeModel);
        pizzaSize.setPreferredSize(new Dimension(140, 70));

        //Set up borders for ingredient labels
        veggiesLabel.setBorder(BorderFactory.createEtchedBorder());
        cheesesLabel.setBorder(BorderFactory.createEtchedBorder());
        meatsLabel.setBorder(BorderFactory.createEtchedBorder());

        //make ingredients read-only

        for(int i = 0; i < veggieIngredients.length; i++){
            veggieIngredients[i].setEnabled(false);
        }
        for(int i = 0; i < cheeseIngredients.length; i++){
            cheeseIngredients[i].setEnabled(false);
        }
        for(int i = 0; i < meatIngredients.length; i++){
            meatIngredients[i].setEnabled(false);
        }

        // add action listener for menu pizza
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = pizzaType.getSelectedItem().toString();
                    String size = pizzaSize.getSelectedItem().toString();
                    String givenQuantity = nrOfPizzaField.getText();
                    int quantity = Integer.parseInt(givenQuantity);
                    String discount = discountField.getText().toUpperCase();

                    List<String> veggies = new ArrayList<>();
                    veggies.addAll(veggiesSelected);

                    List<String> cheeses = new ArrayList<>();
                    cheeses.addAll(cheesesSelected);

                    List<String> meats = new ArrayList<>();
                    meats.addAll(meatsSelected);

                    if(discount.equalsIgnoreCase("PROMO")){

                        if(name.equalsIgnoreCase("Custom Pizza")) {
                            PizzaOrderEvent ev = new PizzaOrderEvent(this, name, size, quantity, discount, veggies, cheeses, meats);
                            if(formListener != null) {
                                formListener.formEventOccurred(ev);
                            }

                        }else{
                            PizzaOrderEvent ev = new PizzaOrderEvent(this, name, size, quantity, discount);
                            if(formListener != null) {
                                formListener.formEventOccurred(ev);
                            }
                        }

                    }else if(discount.isEmpty()){
                        if(name.equalsIgnoreCase("Custom Pizza")){
                            PizzaOrderEvent ev = new PizzaOrderEvent(this, name, size, quantity, veggies, cheeses, meats);
                            ev.setDiscount("");
                            if(formListener != null) {
                                formListener.formEventOccurred(ev);
                            }
                        }else {
                            PizzaOrderEvent ev = new PizzaOrderEvent(this, name, size, quantity);
                            ev.setDiscount("");
                            if(formListener != null) {
                                formListener.formEventOccurred(ev);
                                }
                            }
                        }

                    else{
                        JOptionPane.showMessageDialog(FormPanel.this, "Invalid discount code. Use PROMO");
                    }

                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(FormPanel.this, "Please insert a valid number.");
                }
            }
        });

        newOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clearTextField(discountField);
                clearTextField(nrOfPizzaField);
                clearTextField(discountField);

                clearVeggies();
                clearCheeses();
                clearMeats();

                pizzaType.setSelectedIndex(0);
                pizzaSize.setSelectedIndex(0);

            }
        });

        //add a listener on pizza type -> if user selects Custom Pizza, he should be able to choose ingredients, otherwise the ingredients are read-only
        pizzaType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = pizzaType.getSelectedItem().toString();

                if(name.equalsIgnoreCase("Custom Pizza")){

                    for (int i = 0; i < veggieIngredients.length; i++) {
                    veggieIngredients[i].setEnabled(true);
                    }
                    for (int i = 0; i < cheeseIngredients.length; i++) {
                    cheeseIngredients[i].setEnabled(true);
                    }
                    for (int i = 0; i < meatIngredients.length; i++) {
                    meatIngredients[i].setEnabled(true);
                    }
                }else{
                    for (int i = 0; i < veggieIngredients.length; i++) {
                        veggieIngredients[i].setEnabled(false);
                    }
                    for (int i = 0; i < cheeseIngredients.length; i++) {
                        cheeseIngredients[i].setEnabled(false);
                    }
                    for (int i = 0; i < meatIngredients.length; i++) {
                        meatIngredients[i].setEnabled(false);
                    }

                    clearVeggies();
                    clearCheeses();
                    clearMeats();
                }
            }
        });

        //add a listener to see which veggies have been selected
        for(int i = 0; i < veggieIngredients.length; i++) {
            veggieIngredients[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int j = 0; j < veggieIngredients.length; j++) {
                        String veggie = veggieIngredients[j].getText();

                        if (veggieIngredients[j].isSelected()) {
                            veggiesSelected.add(veggie);
                        }
                    }
                }
            });
        }

        //add a listener to see which cheeses have been selected
        for(int i = 0; i < cheeseIngredients.length; i++) {
            cheeseIngredients[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int j = 0; j < cheeseIngredients.length; j++) {
                        String cheese = cheeseIngredients[j].getText();

                        if (cheeseIngredients[j].isSelected()) {
                            cheesesSelected.add(cheese);
                        }
                    }
                }
            });
        }

        //add a listener to see which meats have been selected
        for(int i = 0; i < meatIngredients.length; i++) {
            meatIngredients[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int j = 0; j < meatIngredients.length; j++) {
                        String meat = meatIngredients[j].getText();

                        if (meatIngredients[j].isSelected()) {
                            meatsSelected.add(meat);
                        }
                    }
                }
            });
        }

        //add a listener to see which veggies have been deselected
        for(int i = 0; i < veggieIngredients.length; i++) {
            veggieIngredients[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int j = 0; j < veggieIngredients.length; j++) {
                        String veggie = veggieIngredients[j].getText();

                        if (!(veggieIngredients[j].isSelected())) {
                            veggiesSelected.remove(veggie);
                        }
                    }
                }
            });
        }

        //add a listener to see which cheeses have been deselected
        for(int i = 0; i < cheeseIngredients.length; i++) {
            cheeseIngredients[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int j = 0; j < cheeseIngredients.length; j++) {
                        String cheese = cheeseIngredients[j].getText();

                        if (!(cheeseIngredients[j].isSelected())) {
                            cheesesSelected.remove(cheese);
                        }
                    }
                }
            });
        }

        //add a listener to see which meats have been deselected
        for(int i = 0; i < meatIngredients.length; i++) {
            meatIngredients[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int j = 0; j < meatIngredients.length; j++) {
                        String meat = meatIngredients[j].getText();

                        if (!(meatIngredients[j].isSelected())) {
                            meatsSelected.remove(meat);
                        }
                    }
                }
            });
        }


        Border innerBorder = BorderFactory.createTitledBorder("Pizza Order");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        layoutComponent();

    }

    public void setFormListener(FormListener listener) {

        this.formListener = listener;
    }

    //newOrder text fields
    public void clearTextField(JTextField textField){
        textField.setText("");
    }

    // newOrder ingredients

    public void clearVeggies(){
        for(int i = 0; i < veggieIngredients.length; i++){
            veggieIngredients[i].setSelected(false);
        }
    }

    public void clearCheeses(){
        for(int i = 0; i < cheeseIngredients.length; i++){
            cheeseIngredients[i].setSelected(false);
        }
    }

    public void clearMeats(){
        for(int i = 0; i < meatIngredients.length; i++){
            meatIngredients[i].setSelected(false);
        }
    }


    public void layoutComponent(){
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();


        ////////////First row ///////////////////////////////////
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(pizzaTypeLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(pizzaType, gc);

        ////////////Second row //////////////////////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(pizzaSizeLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(pizzaSize, gc);

        //////////// Third row ///////////////////////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 0);
        add(nrOfPizzaLabel, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(nrOfPizzaField, gc);

        ////////////Fourth row ///////////////////////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 3;
        gc.insets = new Insets(40, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        add(veggiesLabel, gc);

        gc.gridy--;
        for(int i = 0; i < veggieIngredients.length; i++){
            gc.gridy++;

            gc.gridx = 1;
            gc.anchor = GridBagConstraints.FIRST_LINE_START;
            if(i == 0){
                gc.insets = new Insets(40, 0, 0, 0);

            }else {
                gc.insets = new Insets(0, 0, 0, 0);
            }
            add(veggieIngredients[i], gc);
        }

        ////////////Fifth row ///////////////////////////////////

        gc.gridx = 1;
        gc.gridy = 3;
        gc.insets = new Insets(40, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        add(cheesesLabel, gc);

        gc.gridy--;
        for(int i = 0; i < cheeseIngredients.length; i++){
            gc.gridy++;

            gc.gridx = 2;
            gc.anchor = GridBagConstraints.FIRST_LINE_START;
            if(i == 0){
                gc.insets = new Insets(40, 0, 0, 0);

            }else {
                gc.insets = new Insets(0, 0, 0, 0);
            }
            add(cheeseIngredients[i], gc);
        }

        ////////////Sixth row ///////////////////////////////////

        gc.gridx = 2;
        gc.gridy = 3;
        gc.insets = new Insets(40, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        add(meatsLabel, gc);

        gc.gridy--;
        for(int i = 0; i < meatIngredients.length; i++){
            gc.gridy++;

            gc.gridx = 3;
            gc.anchor = GridBagConstraints.FIRST_LINE_START;
            if(i == 0){
                gc.insets = new Insets(40, 0, 0, 0);
            }else {
                gc.insets = new Insets(0, 0, 0, 0);
            }
            add(meatIngredients[i], gc);
        }

        ////////////Seventh row ///////////////////////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 9;
        gc.insets = new Insets(50, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(discountLabel, gc);

        gc.gridx = 1;
        gc.gridy = 9;
        gc.insets = new Insets(50, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(discountField, gc);

        ////////////Eighth row ///////////////////////////////////

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 3;
        gc.gridy = 10;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(submit, gc);

        ////////////Nineth row ///////////////////////////////////

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 3;
        gc.gridy = 12;
        gc.anchor = GridBagConstraints.PAGE_END;
        gc.insets = new Insets(0, 0, 0, 0);
        add(newOrder, gc);

    }

}

class PizzaType{
    private int id;
    private String type;

    public PizzaType(int id, String type){
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }

    public int getId(){
        return id;
    }
}

