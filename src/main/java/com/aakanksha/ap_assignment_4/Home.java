package com.aakanksha.ap_assignment_4;

import java.io.IOException;
import java.io.Serializable;

public class Home implements HomePage, Serializable {

    public static void start(){
        System.out.println("Enter user type: 1. Admin 2. Customer 3. Exit");
        int type = scanner.nextInt();
        scanner.nextLine();
        User user = null;
        switch (type){
            case 1:
                adminStart(user);
                break;
            case 2:
                customerStart(user);
                break;
            case 3:
                Database.serializeMenu();
                Database.serializeCustomers();
                Database.serializeVIPOrders();
                Database.serializenonVIPOrders();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice.");
        } start();
    }

    public static void adminStart(User user){
        System.out.println("Enter password:");
        if (scanner.nextLine().equals("admin123")){
            System.out.println("Login successful.");
            user = new Administrator();
        }
        else System.out.println("Incorrect password.");
    }

    public static void customerStart(User user){
        System.out.println("1. Login or 2. Sign Up?");
        int action = scanner.nextInt();
        scanner.nextLine();
        switch(action){
            case 1:
                System.out.println("Enter email id:");
                String e = scanner.nextLine();
                Customer cc = null;
                for (Customer c : Database.getAllCustomers()){
                    if (c.getEmail().equals(e)) cc = c;
                }
                if (cc == null)System.out.println("Email address does not exist.");
                System.out.println("Enter password:");
                if (cc.getPassword().equals(scanner.nextLine())) {
                    System.out.println("Login successful.");
                    cc.displayMenu();
                }
                else System.out.println("Incorrect password.");
                break;
            case 2:
                System.out.println("Enter email id:");
                e = scanner.nextLine();
                System.out.println("Create password:");
                String p = scanner.nextLine();
                System.out.println("Do you wish to become a VIP? (y/n)");
                String yn = scanner.nextLine();
                switch(yn){
                    case "y":
                        user = new Customer(e,p,true);
                        break;
                    case "n":
                        user = new Customer(e,p,false);
                        break;
                }
                System.out.println("Signed up successfully.");
                user.displayMenu();
                break;
            default:
                System.out.println("Invalid request.");
        }
    }

    public static void testLogin(String e, String p) throws IOException{
        Customer cc = null;
        for (Customer c : Database.getAllCustomers()){
            if (c.getEmail().equals(e)) cc = c;
        }
        if (cc == null)throw new IOException("Email address does not exist.");
        if (cc.getPassword().equals(p)) {
            System.out.println("Login successful.");
        }
        else throw new IOException("Incorrect password.");
    }
}