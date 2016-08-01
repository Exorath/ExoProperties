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
