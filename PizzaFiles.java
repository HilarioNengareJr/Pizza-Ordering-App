import java.util.ArrayList;

abstract class Food {
    private String name;
    private double price;
    private double calorie;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }
}

class Topping extends Food {
    // Add additional methods or properties specific to Toppings if needed
}

class Pizza extends Food {
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

class Order {
    private Pizza pizza;
    private ArrayList<Topping> toppings = new ArrayList<>();

    public Order(Pizza pizza) {
        this.pizza = pizza;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public double calculatePrice() {
        double totalPrice = pizza.getPrice();
        for (Topping topping : toppings) {
            totalPrice += topping.getPrice();
        }
        return totalPrice;
    }

    public double calculateCalories() {
        double totalCalories = pizza.getCalorie();
        for (Topping topping : toppings) {
            totalCalories += topping.getCalorie();
        }
        return totalCalories;
    }
}

