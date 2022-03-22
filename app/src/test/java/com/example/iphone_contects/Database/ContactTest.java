package com.example.iphone_contects.Database;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContactTest {

    @Test
    public void setId() {
        Contact c=new Contact();
        c.setId(1);
        assertEquals(1,c.getId());
    }

    @Test
    public void setFav() {
        Contact c=new Contact();
        c.setFav(true);
        assertTrue(c.isFav());
    }

    @Test
    public void getFirst_name() {
        Contact c=new Contact();
        c.setFirst_name("mostafa");
        assertEquals("mostafa",c.getFirst_name());
    }
}