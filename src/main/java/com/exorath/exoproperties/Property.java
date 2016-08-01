package com.exorath.exoproperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toon on 7/31/2016.
 */
public class Property<E> {
    private E def;
    private List<String> tags = new ArrayList<String>();
    public List<String> getTags() {
        return tags;
    }

    public E getDefault() {
        return def;
    }

    public void setDefault(E def) {
        this.def = def;
    }
}
