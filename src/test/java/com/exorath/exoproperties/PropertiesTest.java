/*
 *    Copyright 2016 Exorath
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.exorath.exoproperties;

import org.junit.Test;
import rx.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

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
        Property<String> property = mock(Property.class);
        String value = "random value";
        properties.set(property, value);
        assertTrue(properties.asMap().containsKey(property));
    }

    @Test
    public void setPropertyValueTest(){
        Properties properties = new Properties();
        Property<String> property = mock(Property.class);
        String value = "random value";
        properties.set(property, value);
        assertEquals(properties.asMap().get(property), value);
    }

    @Test
    public void containsPropertyTest(){
        Properties properties = new Properties();
        Property<String> property = mock(Property.class);
        String value = "blalbalbal";
        properties.set(property, value);
        assertTrue(properties.contains(property));
    }


    @Test
    public void containsPropertyTest2(){
        Properties properties = new Properties();
        Property<String> property = mock(Property.class);
        assertFalse(properties.contains(property));
    }

    @Test
    public void getPropertyTest(){
        Properties properties = new Properties();
        Property<String> property = mock(Property.class);
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

    @Test
    public void getObservableAlwaysDefinedTest(){
        Properties properties = new Properties();
        Property<String> property = mock(Property.class);
        assertTrue(properties.getObservable(property) != null);
    }

    @Test
    public void setCallsSubscribersTest1(){
        Properties properties = new Properties();
        Property<String> property = mock(Property.class);
        String value = "This is a value";
        AtomicBoolean received = new AtomicBoolean(false);
        properties.set(property, "lala");
        properties.getObservable(property).subscribe((obj) -> {
            if(obj.equals(value))
                received.set(true);
        });
        properties.set(property, value);
        assertTrue(received.get());
    }

    @Test
    public void setCallsSubscribersTest2(){
        Properties properties = new Properties();
        Property<String> property = mock(Property.class);
        String value = "This is a value";
        AtomicBoolean received = new AtomicBoolean(false);
        properties.set(property, value);
        properties.getObservable(property).subscribe((obj) -> {
            if(obj.equals(value))
                received.set(true);
        });
        assertTrue(received.get());
    }

    @Test
    public void setCallsSubscribersTest3(){
        Properties properties = new Properties();
        Property<String> property = mock(Property.class);
        String value = "This is a value";
        AtomicBoolean received = new AtomicBoolean(false);
        properties.set(property, value);
        properties.getObservable(property).subscribe((obj) -> {
            if(obj.equals(value))
                received.set(true);
        });
        properties.set(property, "blabla");
        assertTrue(received.get());
    }

    @Test
    public void setCallsSubscribersTest4(){
        Properties properties = new Properties();
        Property<String> property = mock(Property.class);
        String value = "This is a value";
        AtomicBoolean received = new AtomicBoolean(false);
        properties.set(property, "blabla");
        properties.getObservable(property).subscribe((obj) -> {
            if(obj.equals(value))
                received.set(true);
        });
        assertFalse(received.get());
    }
    @Test
    public void setCallsSubscribersTest5(){
        Properties properties = new Properties();
        Property<String> property = mock(Property.class);
        String value = "This is a value";
        AtomicBoolean received = new AtomicBoolean(false);
        properties.set(property, value);
        properties.getObservable(property).subscribe((obj) -> {
            received.set(obj.equals(value));
        });
        properties.set(property, "blabla");
        assertFalse(received.get());
    }
    @Test
    public void removeTest(){
        Properties properties = new Properties();
        Property property = mock(Property.class);
        properties.set(property, "lorem ipsum");
        properties.remove(property);
        assertFalse(properties.contains(property));
    }

    @Test
    public void removeClearsSubscribersTest1(){
        Properties properties = new Properties();
        Property property = mock(Property.class);
        properties.set(property, "lorem ipsum");
        Subscription subscription = properties.getObservable(property).subscribe();
        properties.remove(property);
        assertTrue(subscription.isUnsubscribed());
    }

    @Test
    public void removeClearsSubscribersReaddTest(){
        Properties properties = new Properties();
        Property property = mock(Property.class);
        properties.set(property, "lorem ipsum");
        Subscription subscription = properties.getObservable(property).subscribe();
        properties.remove(property);
        Subscription subscription2 = properties.getObservable(property).subscribe();
        assertFalse(subscription2.isUnsubscribed());
    }
}
