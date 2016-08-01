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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Toon on 7/31/2016.
 */
public class Properties {
    private HashMap<Property, Object> properties = new HashMap();

    public Map<Property, Object> asMap() {
        return properties;
    }
    /**
     * Assigns a value to a property within this properties repository. This will override any previous value.
     *
     * @param property the property to store under
     * @param value the value you want to store in the key
     */
    public void set(Property property, Object value) {
        properties.put(property, value);
    }

    public <T> T get(Property<T> property) {
        if(!properties.containsKey(property))
            return property.getDefault();
        return (T) properties.get(property);
    }

    /**
     * Returns true if this properties repository has assigned a value (even if it's the same as the default value) for this property, false if not.
     *
     * @param property the property
     *
     * @return true if this properties repository has a value assigned to the provided property
     */
    public boolean contains(Property property) {
        return properties.containsKey(property);
    }
}
