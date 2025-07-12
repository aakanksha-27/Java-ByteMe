package com.aakanksha.ap_assignment_4;

import java.io.*;
import java.util.*;

public class Customer extends User implements Serializable{

    private boolean VIP;
    private Map<Item,Integer> cart;
    private ArrayList<Order> myOrders;

    public Customer(String em, String pwd, boolean VIP) {
        this.email = em;
        this.password = pwd;
        this.VIP = VIP;
        cart = new HashMap<Item,Integer>();
        myOrders = new ArrayList<>();
        Database.getAllCustomers().add(this);
    }

    public boolean isVIP() {
        return this.VIP;
    }
    public void setVIP(boolean VIP) {
        this.VIP = VIP;
    }
    public Map<Item,Integer> getCart() { return this.cart; }
    public void setCart(Map<Item,Integer> cart) { this.cart = cart; }
    public ArrayList<Order> getMyOrders() { return this.myOrders; }
    public void setMyOrders(ArrayList<Order> myOrders) { this.myOrders = myOrders; }

    @Override
    public void displayMenu() {
        System.out.println("Select functionality:");
        System.out.println("1. Browse menu");
        System.out.println("2. Cart Operations");
        System.out.println("3. Order Tracking");
        System.out.println("4. Item Reviews");
        System.out.println("5. Logout");
        int ft = scanner.nextInt();
        scanner.nextLine();
        switch (ft) {
            case 1:
                browseMenu();
                break;
            case 2:
                cartOps();
                break;
            case 3:
                orderTracking();
                break;
            case 4:
                itemReviews();
                break;
            case 5:
                logout();
                break;
            default:
                System.out.println("Invalid choice.");
        } displayMenu();
    }

    public void browseMenu() {
        System.out.println("What do you want to do?");
        System.out.println("1. View all items");
        System.out.println("2. Search items");
        System.out.println("3. Filter by category");
        System.out.println("4. Sort by price");
        System.out.println("5. Go back");
        int ft = scanner.nextInt();
        scanner.nextLine();
        switch (ft) {
            case 1:
                viewAllItems();
                break;
            case 2:
                searchItems();
                break;
            case 3:
                filterByCategory();
                break;
            case 4:
                sortByPrice();
                break;
            case 5:
                displayMenu();
                break;
            default:
                System.out.println("Invalid choice.");
        } browseMenu();
    }

    public void viewAllItems() {
        for (Item item : Database.getMenu()) System.out.println(item);
    }

    public void searchItems() {
        System.out.println("Which item do you want to search?");
        String n = scanner.nextLine();
        for (Item item : Database.getMenu()){
            if(item.getName().equals(n)) System.out.println(item);
        }
    }

    public void filterByCategory() {
        System.out.println("Which category do you want to see?");
        String c = scanner.nextLine();
        for (Item item : Database.getMenu()){
            if (item.getCategory().equals(c)) System.out.println(item);
        }
    }

    public void sortByPrice() {
        System.out.println("Do you want to sort low to high? (y/n)");
        String yn = scanner.nextLine();
        TreeSet<Item> temp = new TreeSet<>(Database.getMenu());
        switch (yn) {
            case "y":
                for(Item item : temp) System.out.println(item);
                break;
            case "n":
                Iterator<Item> it = temp.descendingIterator();
                while(it.hasNext()) System.out.println(it.next());
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public void cartOps() {
        System.out.println("What do you want to do?");
        System.out.println("1. Add items");
        System.out.println("2. Modify quantities");
        System.out.println("3. Remove items");
        System.out.println("4. View total amount");
        System.out.println("5. Checkout");
        System.out.println("6. Go back");
        int ft = scanner.nextInt();
        scanner.nextLine();
        switch (ft) {
            case 1:
                addItems();
                break;
            case 2:
                modifyQuantities();
                break;
            case 3:
                removeItems();
                break;
            case 4:
                viewTotalAmt();
                break;
            case 5:
                checkout();
                break;
            case 6:
                displayMenu();
                break;
            default:
                System.out.println("Invalid choice.");
        } cartOps();
    }

    public void addItems(){
        System.out.println("Which item do you want to add?");
        String n = scanner.nextLine();
        for (Item item : Database.getMenu()){
            if(item.getName().equals(n)) {
                if (!item.isAvailable()){
                    System.out.println("Requested item is not available.");
                    break;
                }
                System.out.println("What is your desired quantity?");
                int q = scanner.nextInt();
                scanner.nextLine();
                cart.put(item, q);
                System.out.println("Item added successfully.");
            }
        }
    }

    public void modifyQuantities() {
        System.out.println("Which item do you want to modify?");
        String n = scanner.nextLine();
        for (Item item : this.getCart().keySet()){
            if(item.getName().equals(n)) {
                System.out.println("What is your desired quantity?");
                int q = scanner.nextInt();
                scanner.nextLine();
                this.getCart().put(item, q);
                System.out.println("Item updated successfully.");
            }
        }
    }

    public void removeItems() {
        System.out.println("Which item do you want to remove?");
        String n = scanner.nextLine();
        for (Item item : this.getCart().keySet()){
            if(item.getName().equals(n)) {
                this.getCart().remove(item);
                System.out.println("Item removed successfully.");
                break;
            }
        }
    }

    public void viewTotalAmt() {
        int total = 0;
        for (Item item : this.getCart().keySet()) total += (item.getPrice() * this.getCart().get(item));
        System.out.println("Total amount: Rs." + total);
    }

    public void checkout() {
        System.out.println("Enter mode of payment:");
        String pay = scanner.nextLine();
        System.out.println("Enter delivery address:");
        String addr = scanner.nextLine();
        System.out.println("Enter date: (dd/mm/yyyy)");
        String date = scanner.nextLine();
        System.out.println("Any special requests? (y/n)");
        String yn = scanner.nextLine();
        Item it = null;
        if (yn.equals("y")) {
            System.out.println("Enter item name:");
            String n = scanner.nextLine();
            System.out.println("Enter special request:");
            for (Item item : this.getCart().keySet()){
                if(item.getName().equals(n)) {
                    this.getMyOrders().add(new Order(item,this.isVIP(),scanner.nextLine(),date));
                    for(int i = 0; i < this.getCart().get(item)-1; i++) {
                        this.getMyOrders().add(new Order(item,this.isVIP(),date));
                    }
                    it = item;
                    break;
                }
            }
        }
        if(it!=null) this.getCart().remove(it);
        for(Item item : this.getCart().keySet()) {
            for(int i = 0; i < this.getCart().get(item); i++) {
                this.getMyOrders().add(new Order(item,this.isVIP(),date));
            }
        }
        this.getCart().clear();
    }

    public void orderTracking() {
        System.out.println("What do you want to do?");
        System.out.println("1. View order status");
        System.out.println("2. Cancel order");
        System.out.println("3. Order history");
        System.out.println("4. Go back");
        int ft = scanner.nextInt();
        scanner.nextLine();
        switch (ft) {
            case 1:
                viewOrderStatus();
                break;
            case 2:
                cancelOrder();
                break;
            case 3:
                orderHistory();
                break;
            case 4:
                displayMenu();
                break;
            default:
                System.out.println("Invalid choice.");
        } orderTracking();
    }

    public void viewOrderStatus() {
        System.out.println("Which item's order status do you want to view?");
        String n = scanner.nextLine();
        for(Order order : this.getMyOrders()) {
            if(n.equals(order.getItem().getName())) System.out.println(order.getStatus());
        }
    }

    public void cancelOrder() {
        System.out.println("Which order do you want to cancel?");
        String n = scanner.nextLine();
        for(Order order : this.getMyOrders()) {
            if(n.equals(order.getItem().getName())) {
                order.setStatus("Cancelled");
                System.out.println("Order cancelled successfully.");
            }
        }
    }

    public void orderHistory() {
        for(Order order : this.getMyOrders()) {
            System.out.println(order.getItem().getName() + ": " + order.getStatus());
        }
    }

    public void itemReviews(){
        System.out.println("What do you want to do?");
        System.out.println("1. Provide review");
        System.out.println("2. View reviews");
        System.out.println("3. Go back");
        int ft = scanner.nextInt();
        scanner.nextLine();
        switch (ft) {
            case 1:
                provideReview();
                break;
            case 2:
                viewReviews();
                break;
            case 3:
                displayMenu();
                break;
            default:
                System.out.println("Invalid choice.");
        } itemReviews();
    }

    public void provideReview() {
        System.out.println("Which item do you want to leave a review for?");
        String n = scanner.nextLine();
        for(Order order : this.getMyOrders()) {
            if(n.equals(order.getItem().getName())) {
                System.out.println("Enter review:");
                order.getItem().getReviews().add(scanner.nextLine());
                break;
            }
        }
    }

    public void viewReviews() {
        System.out.println("Which item's reviews do you want to view?");
        String n = scanner.nextLine();
        for(Item item : Database.getMenu()){
            if(n.equals(item.getName())) {
                for (String s : item.getReviews()) System.out.println(s);
            }
        }
    }

}