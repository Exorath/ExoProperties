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
