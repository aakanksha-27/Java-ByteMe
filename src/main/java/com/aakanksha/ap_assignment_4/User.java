package com.aakanksha.ap_assignment_4;

import java.io.Serializable;
import java.util.Scanner;

public abstract class User implements Serializable {

    static Scanner scanner = new Scanner(System.in);
    protected String email;
    protected String password;

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void logout(){
        System.out.println("Logged out successfully.");
        Home.start();
    }

    public abstract void displayMenu();
}
