package org.example;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int addOrder(ArrayList<String> pizzas,
                                ArrayList<Integer> quantities,
                                String pizzaType,
                                int quantity){
        if (quantity <= 0){
            System.out.println("Quantity must be positive");
            return 2;
        }
        pizzas.add(pizzaType);
        quantities.add(quantity);
        return 0;

    }
    public static int updateOrder(ArrayList<Integer> quantities,
                                   int index,
                                   int newQuantity){
        if (!(index < quantities.size() && index >= 0)){
            if (quantities.isEmpty()){
                System.out.println("Order list is empty!");
            } else {
                System.out.println("Index must be between 0 and " + (quantities.size() - 1) + ".");
            }
            return 1;
        }
        if (newQuantity <= 0){
            System.out.println("Quantity must be positive");
            return 2;
        }
        quantities.set(index, newQuantity);
        return 0;
    }
    public static int removeOrder(ArrayList<String> pizzas,
                                   ArrayList<Integer> quantities,
                                   int index){
        if (!(index < pizzas.size() && index >= 0)){
            if (quantities.isEmpty()){
                System.out.println("Order list is empty!");
            } else {
                System.out.println("Index must be between 0 and " + (quantities.size() - 1) + ".");
            }
            return 1;
        }
        pizzas.remove(index);
        quantities.remove(index);
        return 0;
    }
    public static void printOrders(ArrayList<String> pizzas,
                                   ArrayList<Integer> quantities){
        System.out.println("--- Current Orders ---");
        for (int i = 0; i < pizzas.size(); i++){
            String pizza = pizzas.get(i);
            int quantity = quantities.get(i);
            String order = String.format("%d. %s x %d", (i + 1), pizza, quantity);
            System.out.println(order);
        }
    }
    public static boolean isOptionValid (int option){
        if (option <=5 && option > 0){
            return true;
        }
        return false;
    }
    public static boolean isOrderNumberInvalid(ArrayList<String> reference, int orderNumber){
        if (orderNumber <= reference.size() && orderNumber > 0){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> pizzas = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();

        System.out.println("1. Add Order");
        System.out.println("2. Update Order");
        System.out.println("3. Remove Order");
        System.out.println("4. View Orders");
        System.out.println("5. Exit");
        while (true){
            System.out.print("Choose option: ");
            int option = scanner.nextInt();

            if (isOptionValid(option)){
                if (option == 5){
                    break;
                }

                switch (option){
                    case 1:{
                        System.out.print("Pizza type: ");
                        String pizzaType = scanner.next();
                        System.out.print("Quantity: ");
                        int quantity = scanner.nextInt();
                        while (addOrder(pizzas, quantities, pizzaType, quantity) == 2){
                            System.out.print("Quantity: ");
                            quantity = scanner.nextInt();
                        }
                        break;
                    }
                    case 2:{
                        System.out.print("Order number to update: ");
                        int index = scanner.nextInt();
                        while (isOrderNumberInvalid(pizzas, index)){
                            System.out.println("Order number must be between 1 and " + quantities.size() + ".");
                            System.out.print("Order number to update: ");
                            index = scanner.nextInt();
                        }
                        System.out.print("New quantity: ");
                        int newQuantity = scanner.nextInt();
                        while (updateOrder(quantities, index - 1, newQuantity) == 2){
                            System.out.print("New quantity: ");
                            newQuantity = scanner.nextInt();
                        }
                        break;
                    }
                    case 3:{
                        System.out.print("Order number to remove: ");
                        int index = scanner.nextInt();
                        while (isOrderNumberInvalid(pizzas, index)){
                            System.out.println("Order number must be between 1 and " + quantities.size() + ".");
                            System.out.print("Order number to remove: ");
                            index = scanner.nextInt();
                        }
                        removeOrder(pizzas, quantities, index - 1);
                        break;
                    }
                    case 4:{
                        printOrders(pizzas, quantities);
                        break;
                    }
                }
                System.out.println("");
            } else {
                System.out.println("Choose an option from 1 to 5. If you wish to exit, input 5.");
            }
        }
        System.out.println("---Thank you!---");
    }
}