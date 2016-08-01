package com.exorath.exoproperties;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Toon on 7/31/2016.
 */
public class PropertyTest {
    @Test
    public void tagsDefaultSizeTest(){
        Property property = new Property();
        assertEquals(property.getTags().size(), 0);
    }

    @Test
    public void addTagSizeTest(){
        Property property = new Property();
        property.getTags().add("This is a tag");
        assertEquals(property.getTags().size(), 1);
    }

    @Test
    public void addTagContainsTest(){
        Property property = new Property();
        String tag = "Random tag";
        property.getTags().add(tag);
        assertTrue(property.getTags().contains(tag));
    }


    @Test
    public void getDefaultValueTest(){
        Property property = new Property();
        assertEquals(property.getDefault(), null);
    }

    @Test
    public void setDefaultValueTest(){
        Object def = new Object();
        Property property = new Property();
        property.setDefault(def);
        assertEquals(property.getDefault(), def);
    }
}
