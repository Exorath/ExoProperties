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

import rx.Observable;
import rx.subjects.BehaviorSubject;

import java.util.HashMap;
import java.util.Map;

/**
 * Properties can be added to objects to allow other system components to assign and observe metadata to and of that object.
 *
 * Sidenote: Is the documentation maybe giving a bit to much away of the internals? who knows...
 * Created by Toon on 7/31/2016.
 */
public class Properties {
    private HashMap<Property, BehaviorSubject> properties = new HashMap<>();


    public Map<Property, Object> asMap() {
        Map<Property, Object> toReturn = new HashMap<>();
        properties.entrySet().stream()
                .filter((entry) -> hasValue(entry.getKey()))
                .forEach((entry) -> toReturn.put(entry.getKey(), entry.getValue().getValue()));//.getValue().getValue() indicates the value (an Object) of the value (a BehaviourSubject) of the entry
        return toReturn;
    }

    /**
     * Assigns a value to a property within this properties repository. This will override any previous value.
     *
     * @param property the property to store under
     * @param value    the value you want to store in the key
     */
    public void set(Property property, Object value) {
        if (properties.containsKey(property))
            properties.get(property).onNext(value);
        else
            properties.put(property, BehaviorSubject.create(value));
    }

    /**
     * Returns the value assigned to the property or null if no value was assigned.
     *
     * @param property property to retrieve value of
     * @param <T>      type of the value
     * @return the value assigned to the property or null if no value was assigned
     */
    public <T> T get(Property<T> property) {
        if (!properties.containsKey(property) || !properties.get(property).hasValue())
            return property.getDefault();
        return (T) properties.get(property).getValue();
    }


    /**
     * Returns an observable that will emit changes to the property to its subscribers (it can manage multiple subscriptions).
     * This observable will instantly emit the initial value of the property if the value exists when you subscribe to it.
     * Even if no value is assigned yet this will return a valid observable that will emit changes in the future.
     *
     * @param property property to observe
     * @param <T>      type of the property
     * @return an observable of the property
     */
    public <T> Observable<T> getObservable(Property<T> property) {
        if (!properties.containsKey(property))
            properties.put(property, BehaviorSubject.create());
        return (Observable<T>) properties.get(property);
    }

    /**
     * Removes a property from this properties object. This will also unsubscribe any subscriptions allowing the garbage collector to collect the property value (If it's not defined elsewhere!)
     * Returns true if there was an observable assigned to the provided property, false if no observable was assigned under the given property key (note that no value may have been assigned).
     * @param property The property to remove from this properties object.
     *
     * @return true if there was a value assigned to the provided property
     */
    public boolean remove(Property property){
        if(properties.containsKey(property)){
            properties.get(property).onCompleted();
            properties.remove(property);
            return true;
        }
        return false;
    }
    /**
     * Returns true if this properties repository has assigned a value (even if it's the same as the default value) for this property, false if not.
     *
     * @param property the property
     * @return true if this properties repository has a value assigned to the provided property
     */
    public boolean contains(Property property) {
        return properties.containsKey(property) && hasValue(property);
    }

    private boolean hasValue(Property property) {
        return properties.get(property).hasValue();
    }

    public static Properties create(){
        return new Properties();
    }
}
