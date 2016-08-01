package com.exorath.exoproperties;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Toon on 7/31/2016.
 */
public class PropertiesTest {
    @Test
    public void mapEmptyDefaultTest(){
        Properties properties = new Properties();
        assertTrue(properties.asMap().isEmpty());
    }
    @Test
    public void setPropertyValueContainsTest(){
        Properties properties = new Properties();
        Property<String> property = new Property<>();
        String value = "random value";
        properties.set(property, value);
        assertTrue(properties.asMap().containsKey(property));
    }

    @Test
    public void setPropertyValueTest(){
        Properties properties = new Properties();
        Property<String> property = new Property<>();
        String value = "random value";
        properties.set(property, value);
        assertEquals(properties.asMap().get(property), value);
    }

    @Test
    public void containsPropertyTest(){
        Properties properties = new Properties();
        Property<String> property = new Property<>();
        String value = "blalbalbal";
        properties.set(property, value);
        assertTrue(properties.contains(property));
    }


    @Test
    public void containsPropertyTest2(){
        Properties properties = new Properties();
        Property<String> property = new Property<>();
        assertFalse(properties.contains(property));
    }

    @Test
    public void getPropertyTest(){
        Properties properties = new Properties();
        Property<String> property = new Property<>();
        String value = "blalbalbal";
        properties.set(property, value);
        assertEquals(properties.get(property), value);
    }

    @Test
    public void getPropertyDefaultTest(){
        Properties properties = new Properties();
        Property<String> property = new Property<>();
        String defValue = "This is a default value";
        property.setDefault(defValue);
        assertEquals(properties.get(property), defValue);
    }
}
