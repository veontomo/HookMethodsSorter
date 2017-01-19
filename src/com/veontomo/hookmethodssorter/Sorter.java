package com.veontomo.hookmethodssorter;

/**
 * Sorts the fields and methods of the class in a predefined order.
 * A class is said to be sorted canonically if its fields and methods are in this order:
 * 1. fields
 * 2. basic methods
 * 3. other methods
 *
 * The basic methods are defined as an array of strings provided to the constructor.
 * The order in which the name of the methods appears in the array defines the order
 * in which the methods appear after sorting:
 * if methods M1 and M2 have indexes i1 and i2 respectively, and i1 < i2, then method M1
 * must come before method M2 after the sorting procedure.
 *
 */
public class Sorter {
    private final String[] LIFECYCLE_METHODS;

    public Sorter(String[] methods) {
        // perform defencive copying
        int size = methods != null ? methods.length : 0;
        this.LIFECYCLE_METHODS = new String[size];
        System.arraycopy(methods, 0, LIFECYCLE_METHODS, 0, size);
    }
}
