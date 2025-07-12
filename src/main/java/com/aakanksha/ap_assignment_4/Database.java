package com.aakanksha.ap_assignment_4;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Database implements Serializable {
    public static Set<Customer> allCustomers;
    public static Set<Item> menu;
    public static ArrayList<Order> nonVIPOrders;
    public static ArrayList<Order> VIPOrders;

    static {
        allCustomers = new HashSet<>();
        menu = new HashSet<>();
        nonVIPOrders = new ArrayList<>();
        VIPOrders = new ArrayList<>();
        data();
        deserialize();
    }

    public static Set<Customer> getAllCustomers() {
        return allCustomers;
    }
    public static Set<Item> getMenu() {
        return menu;
    }
    public static ArrayList<Order> getnonVIPOrders() { return nonVIPOrders; }
    public static ArrayList<Order> getVIPOrders() { return VIPOrders; }

    public static void data(){
        getAllCustomers().add(new Customer("abc", "123", false));
        getAllCustomers().add(new Customer("pqr", "456", false));
        getAllCustomers().add(new Customer("xyz", "789", false));
        getMenu().add(new Item("Pancakes", 175, "Breakfast"));
        getMenu().add(new Item("Hash brown", 85, "Breakfast"));
        getMenu().add(new Item("Salad", 200, "Lunch"));
        getMenu().add(new Item("Chicken wrap", 250, "Lunch"));
        getMenu().add(new Item("Chips", 25, "Snacks"));
        getMenu().add(new Item("Oatmeal cookies", 50, "Snacks"));
        getMenu().add(new Item("Ramen", 160, "Dinner"));
        getMenu().add(new Item("Pasta", 180, "Dinner"));
    }

    public static void serializeMenu(){
        try{
            FileOutputStream file = new FileOutputStream("menu.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(menu);
            out.close();
            file.close();
        }
        catch(IOException e){
            menu = new HashSet<>();
        }
        catch (Exception e){
            System.exit(1);
        }
    }
    public static void serializeCustomers(){
        try{
            FileOutputStream file = new FileOutputStream("allCustomers.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(allCustomers);
            out.close();
            file.close();
        }
        catch(IOException e){
            allCustomers = new HashSet<>();
        }
        catch (Exception e){
            System.exit(1);
        }
    }
    public static void serializeVIPOrders(){
        try{
            FileOutputStream file = new FileOutputStream("VIPOrders.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(VIPOrders);
            out.close();
            file.close();
        }
        catch(IOException e){
            VIPOrders = new ArrayList<>();
        }
        catch (Exception e){
            System.exit(1);
        }
    }
    public static void serializenonVIPOrders(){
        try{
            FileOutputStream file = new FileOutputStream("nonVIPOrders.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(nonVIPOrders);
            out.close();
            file.close();
        }
        catch(IOException e){
            nonVIPOrders = new ArrayList<>();
        }
        catch (Exception e){
            System.exit(1);
        }
    }

    public static void deserialize(){
        try{
            FileInputStream file = new FileInputStream("menu.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            menu = (HashSet<Item>)in.readObject();
            in.close();
            file.close();
        }
        catch(IOException e){}
        catch (Exception e){
            System.exit(1);
        }
        try{
            FileInputStream file = new FileInputStream("allCustomers.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            allCustomers = (Set<Customer>)in.readObject();
            in.close();
            file.close();
        }
        catch(IOException e){}
        catch (Exception e){
            System.exit(1);
        }
        try{
            FileInputStream file = new FileInputStream("VIPOrders.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            VIPOrders = (ArrayList<Order>) in.readObject();
            in.close();
            file.close();
        }
        catch(IOException e){}
        catch (Exception e){
            System.exit(1);
        }
        try{
            FileInputStream file = new FileInputStream("nonVIPOrders.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            nonVIPOrders = (ArrayList<Order>) in.readObject();
            in.close();
            file.close();
        }
        catch(IOException e){}
        catch (Exception e){
            System.exit(1);
        }
    }
}