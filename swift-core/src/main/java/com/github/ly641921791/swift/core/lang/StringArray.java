package com.github.ly641921791.swift.core.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author ly
 * @since 1.0.0
 **/
public class StringArray implements Collection<String> {

    private Collection<String> array;

    public StringArray() {
        array = new ArrayList<>();
    }

    public StringArray(Collection<String> array) {
        this.array = array;
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return array.contains(o);
    }

    @Override
    public Iterator<String> iterator() {
        return array.iterator();
    }

    @Override
    public Object[] toArray() {
        return array.toArray();
    }

    @Override
    @SuppressWarnings("all")
    public <T> T[] toArray(T[] a) {
        return array.toArray(a);
    }

    @Override
    public boolean add(String s) {
        return array.add(s);
    }

    @Override
    public boolean remove(Object o) {
        return array.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return array.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return array.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return array.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return array.retainAll(c);
    }

    @Override
    public void clear() {
        array.clear();
    }

}
