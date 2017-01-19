package main.com.veontomo.rearrange;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;

/**
 * Sorts the fields and methods of the class in a predefined order.
 * A class is said to be sorted canonically if its fields and methods are in this order:
 * 1. basic methods
 * 2. other methods
 *
 *
 */
public class Sorter {
    /**
     * Return a new array constructed from the elements of a given array by sorting them in a given order.
     * @param methods
     * @param base
     * @return
     */
    public PsiElement[] sort(final PsiMethod[] methods, final String[] base) {
        return new PsiElement[0];
    }
}
