package com.aakanksha.ap_assignment_4;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.fail;

public class InvalidLogin {

    @Test
    public void test1() throws IOException {
        User user = new Customer("emily","gilmore",true);
        if (user != null){
            try{
                Home.testLogin("emily","gilmore");
            }
            catch(IOException e){
                fail();
            }
        }
        else fail();
    }

    @Test
    public void test2() throws IOException {
        User user = new Customer("jess","mariano",false);
        if(user != null){
            try{
                Home.testLogin("jess","danes");
            }
            catch(IOException e){}
        }
        else fail();
    }
}