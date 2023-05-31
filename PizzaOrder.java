import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@Author(
        name = "Hilario Junior Nengare",
        studentNo = 174682
)
public class PizzaOrder extends JFrame {
    private JComboBox<String> pizzaTypeComboBox;
    private JComboBox<String> sizeComboBox;
    private JCheckBox[] toppingCheckBoxes;
    private JButton addButton;
    private JButton orderButton;
    private JTextArea receiptTextArea;
    private Order currentOrder;
    private ArrayList<Pizza> pizzas;
    private ArrayList<Topping> toppings;

    public PizzaOrder() {
        setTitle("Pizza Order");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        pizzas = new ArrayList<>();
        toppings = new ArrayList<>();

        Pizza margarita = new Pizza();
        margarita.setName("Margarita");
        margarita.setPrice(100.0);
        margarita.setCalorie(800.0);
        pizzas.add(margarita);

        Pizza pepperoni = new Pizza();
        pepperoni.setName("Pepperoni");
        pepperoni.setPrice(120.0);
        pepperoni.setCalorie(1100.0);
        pizzas.add(pepperoni);

        Pizza hawaiian = new Pizza();
        hawaiian.setName("Hawaiian");
        hawaiian.setPrice(130.0);
        hawaiian.setCalorie(850.0);
        pizzas.add(hawaiian);

        Pizza threeCheese = new Pizza();
        threeCheese.setName("Three Cheese");
        threeCheese.setPrice(110.0);
        threeCheese.setCalorie(950.0);
        pizzas.add(threeCheese);

        Topping olive = new Topping();
        olive.setName("Olive");
        olive.setPrice(10.0);
        olive.setCalorie(100.0);
        toppings.add(olive);

        Topping corn = new Topping();
        corn.setName("Corn");
        corn.setPrice(12.0);
        corn.setCalorie(80.0);
        toppings.add(corn);

        Topping chicken = new Topping();
        chicken.setName("Chicken");
        chicken.setPrice(20.0);
        chicken.setCalorie(200.0);
        toppings.add(chicken);

        Topping tomato = new Topping();
        tomato.setName("Tomato");
        tomato.setPrice(15.0);
        tomato.setCalorie(50.0);
        toppings.add(tomato);

        pizzaTypeComboBox = new JComboBox<>();
        for (Pizza pizza : pizzas) {
            pizzaTypeComboBox.addItem(pizza.getName());
        }

        sizeComboBox = new JComboBox<>();
        sizeComboBox.addItem("Small");
        sizeComboBox.addItem("Medium");
        sizeComboBox.addItem("Large");

        toppingCheckBoxes = new JCheckBox[toppings.size()];
        for (int i = 0; i < toppings.size(); i++) {
            Topping topping = toppings.get(i);
            JCheckBox checkBox = new JCheckBox(topping.getName());
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkBox.isSelected()) {
                        currentOrder.addTopping(topping);
                    } else {
                        currentOrder.getToppings().remove(topping);
                    }
                }
            });
            toppingCheckBoxes[i] = checkBox;
        }

        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentOrder == null) {
                    String pizzaName = (String) pizzaTypeComboBox.getSelectedItem();
                    String size = (String) sizeComboBox.getSelectedItem();
                    Pizza selectedPizza = null;
                    for (Pizza pizza : pizzas) {
                        if (pizza.getName().equals(pizzaName)) {
                            selectedPizza = pizza;
                            break;
                        }
                    }
                    if (selectedPizza != null) {
                        selectedPizza.setSize(size);
                        currentOrder = new Order(selectedPizza);
                    }
                }
                clearToppingCheckBoxes();
            }
        });

        orderButton = new JButton("Order");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentOrder != null) {
                    double totalPrice = currentOrder.calculatePrice();
                    double totalCalories = currentOrder.calculateCalories();
                    String receipt = "Pizza: " + currentOrder.getPizza().getName() + " - Price: " + currentOrder.getPizza().getPrice() + " tl\n";
                    for (Topping topping : currentOrder.getToppings()) {
                        receipt += "Topping: " + topping.getName() + " - Price: " + topping.getPrice() + " tl\n";
                    }
                    receipt += "Total Price: " + totalPrice + " tl\n";
                    receipt += "Total Calories: " + totalCalories + " kcal";
                    receiptTextArea.setText(receipt);
                }
            }
        });

        receiptTextArea = new JTextArea(10, 30);
        receiptTextArea.setEditable(false);

        add(new JLabel("Pizza Type:"));
        add(pizzaTypeComboBox);
        add(new JLabel("Size:"));
        add(sizeComboBox);
        add(new JLabel("Toppings:"));
        for (JCheckBox checkBox : toppingCheckBoxes) {
            add(checkBox);
        }
        add(addButton);
        add(orderButton);
        add(new JScrollPane(receiptTextArea));
    }

    private void clearToppingCheckBoxes() {
        for (JCheckBox checkBox : toppingCheckBoxes) {
            checkBox.setSelected(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PizzaOrder app = new PizzaOrder();
                app.setVisible(true);
            }
        });
    }
}
