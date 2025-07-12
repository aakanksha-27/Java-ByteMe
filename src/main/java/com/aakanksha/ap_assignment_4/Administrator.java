package com.aakanksha.ap_assignment_4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Administrator extends User implements Serializable {

    public Administrator() {
        this.displayMenu();
    }

    public void displayMenu() {
        System.out.println("Select functionality:");
        System.out.println("1. Menu Management");
        System.out.println("2. Order Management");
        System.out.println("3. Report Generation");
        System.out.println("4. Logout");
        int action = scanner.nextInt();
        scanner.nextLine();

        switch(action) {
            case 1:
                menuManagement();
                break;
            case 2:
                orderManagement();
                break;
            case 3:
                reportGeneration();
                break;
            case 4:
                logout();
                break;
            default:
                System.out.println("Invalid choice.");
        } displayMenu();
    }

    public void menuManagement() {
        System.out.println("What do you want to do?");
        System.out.println("1. Add new items");
        System.out.println("2. Update existing items");
        System.out.println("3. Remove items");
        System.out.println("4. Go back");
        int ft = scanner.nextInt();
        scanner.nextLine();
        switch(ft) {
            case 1:
                addItems();
                break;
            case 2:
                updateItems();
                break;
            case 3:
                removeItems();
                break;
            case 4:
                displayMenu();
                break;
            default:
                System.out.println("Invalid choice.");
        } menuManagement();
    }

    public void addItems() {
        System.out.println("Enter the name of the item you would like to add:");
        String name = scanner.nextLine();
        System.out.println("Enter the price:");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the category:");
        String category = scanner.nextLine();
        System.out.println("Is the item available? (y/n)");
        String yn = scanner.nextLine();
        switch(yn) {
            case "y":
                Database.getMenu().add(new Item(name, price, category, true));
                break;
            case "n":
                Database.getMenu().add(new Item(name, price, category, false));
                break;
        }
        System.out.println("Item added successfully.");
        //Database.serializeMenu();
    }

    public void updateItems() {
        System.out.println("Enter the name of the item you would like to update:");
        String name = scanner.nextLine();
        for (Item item : Database.getMenu()) {
            if (item.getName().equals(name)) {
                System.out.println("Would you like to update price, category, or availability?");
                String ft = scanner.nextLine();
                switch(ft) {
                    case "price":
                        System.out.println("Enter new price:");
                        item.setPrice(scanner.nextInt());
                        scanner.nextLine();
                        System.out.println("Price updated successfully.");
                        //Database.serializeMenu();
                        break;
                    case "category":
                        System.out.println("Enter new category:");
                        item.setCategory(scanner.nextLine());
                        System.out.println("Category updated successfully.");
                        //Database.serializeMenu();
                        break;
                    case "availability":
                        System.out.println("Is the item available? (y/n)");
                        String yn = scanner.nextLine();
                        switch(yn) {
                            case "y":
                                item.setAvailable(true);
                                break;
                            case "n":
                                item.setAvailable(false);
                                break;
                        }
                        System.out.println("Availability updated successfully.");
                        //Database.serializeMenu();
                        break;
                }
            }
        }
    }

    public void removeItems() {
        System.out.println("Which item would you like to remove?");
        String n = scanner.nextLine();
        for (Item item : Database.getMenu()) {
            if (item.getName().equals(n)) {
                Database.getMenu().remove(item);
                for (Order o : Database.getVIPOrders()){
                    if (o.getItem().equals(item) && o.getStatus().equals("Pending")) o.setStatus("Denied");
                }
                for (Order o : Database.getnonVIPOrders()){
                    if (o.getItem().equals(item) && o.getStatus().equals("Pending")) o.setStatus("Denied");
                }
                System.out.println("Item removed successfully.");
                //Database.serializeMenu();
                break;
            }
        }
    }

    public void orderManagement() {
        System.out.println("What do you want to do?");
        System.out.println("1. View pending orders");
        System.out.println("2. Update order status");
        System.out.println("3. Process refunds");
        System.out.println("4. Handle special requests");
        System.out.println("5. Go back");
        int ft = scanner.nextInt();
        scanner.nextLine();
        switch(ft) {
            case 1:
                viewPendingOrders();
                break;
            case 2:
                updateOrderStatus();
                break;
            case 3:
                processRefunds();
                break;
            case 4:
                handleReqs();
                break;
            case 5:
                displayMenu();
                break;
            default:
                System.out.println("Invalid choice.");
        } orderManagement();
    }

    public void viewPendingOrders() {
        if(!Database.getVIPOrders().isEmpty()) {
            System.out.println("VIP Orders:");
            for (Order o : Database.getVIPOrders()) {
                if(o.getStatus().equals("Pending")) System.out.println(o.getItem().getName());
            }
        }
        System.out.println("Regular Orders:");
        for (Order o : Database.getnonVIPOrders()) {
            if(o.getStatus().equals("Pending")) System.out.println(o.getItem().getName());
        }
    }

    public void updateOrderStatus() {
        System.out.println("Which item's order status would you like to update?");
        String n = scanner.nextLine();
        System.out.println("Is the order VIP? (y/n)");
        String yn = scanner.nextLine();
        Order oo = null;
        switch(yn) {
            case "y":
                for (Order o : Database.getVIPOrders()) {
                    if(o.getItem().getName().equals(n)) oo = o;
                }
                break;
            case "n":
                for (Order o : Database.getnonVIPOrders()) {
                    if(o.getItem().getName().equals(n)) oo = o;
                }
                break;
        }
        System.out.println("What's the new status?");
        oo.setStatus(scanner.nextLine());
    }

    public void processRefunds() {
        System.out.println("Which item's order status would you like to update?");
        String n = scanner.nextLine();
        System.out.println("Is the order VIP? (y/n)");
        String yn = scanner.nextLine();
        switch(yn) {
            case "y":
                for (Order o : Database.getVIPOrders()) {
                    if(o.getItem().getName().equals(n)) o.setStatus("Refund Processed.");
                }
                break;
            case "n":
                for (Order o : Database.getnonVIPOrders()) {
                    if(o.getItem().getName().equals(n)) o.setStatus("Refund Processed.");
                }
                break;
        }
    }

    public void handleReqs(){
        for (Order o: Database.getVIPOrders()){
            if (!o.getSpReq().isEmpty()) System.out.println(o.getItem().getName() + ": " + o.getSpReq());
        }
        for (Order o: Database.getnonVIPOrders()){
            if (!o.getSpReq().isEmpty()) System.out.println(o.getItem().getName() + ": " + o.getSpReq());
        }
    }

    public void reportGeneration(){
        System.out.println("Enter date: (dd/mm/yyyy)");
        String date = scanner.nextLine();
        ArrayList<Order> allOrders = new ArrayList<>();
        for(Order o : Database.getVIPOrders()) {
            if (o.getDate().equals(date)) allOrders.add(o);
        }
        for(Order o : Database.getnonVIPOrders()) {
            if (o.getDate().equals(date)) allOrders.add(o);
        }
        int totalSales = 0;
        Map<Item, Integer> popularItems = new HashMap<>();
        int totalOrders = 0;
        for(Order o: allOrders){
            totalSales += o.getItem().getPrice();
            if(popularItems.containsKey(o.getItem())) popularItems.put(o.getItem(), popularItems.get(o.getItem())+1);
            else popularItems.put(o.getItem(), 1);
            totalOrders++;
        }
        System.out.println("Total Sales: " + totalSales);
        int max = 0;
        Item i = null;
        for (Item item : popularItems.keySet()) {
            if(popularItems.get(item) > max) {
                max = popularItems.get(item);
                i = item;
            }
        }
        System.out.println("Most popular item: " + i.getName());
        System.out.println("Total orders: " + totalOrders);
    }
}
