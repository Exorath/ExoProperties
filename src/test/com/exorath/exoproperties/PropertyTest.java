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
