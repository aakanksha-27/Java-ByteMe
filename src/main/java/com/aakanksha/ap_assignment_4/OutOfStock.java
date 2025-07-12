package com.aakanksha.ap_assignment_4;

import org.junit.Test;
import static org.junit.Assert.fail;

public class OutOfStock {
    
    @Test
    public void test1(){
        Database.getMenu().add(new Item("Bagel",50,"Breakfast",false));
        for (Item i : Database.getMenu())
            if (i.getName().equals("Bagel") && i.isAvailable()) fail();
    }

    @Test
    public void test2(){
        Database.getMenu().add(new Item("Spinach sandwich",150,"Breakfast",true));
        for (Item i : Database.getMenu())
            if (i.getName().equals("Spinach sandwich") && !(i.isAvailable())) fail();
    }
}