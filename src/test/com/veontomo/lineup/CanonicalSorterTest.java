package com.veontomo.lineup;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Test suit for CanonicalSorter class
 */
public class CanonicalSorterTest {
    private final DumbFactory factory = new DumbFactory();

    /**
     * Test a execute method.
     * Partition the input as follows:
     * 1. # methods: 0, 1, > 1
     * 2. # indications: 0, 1, > 1
     * 3. # overlapping: 0, 1, > 1
     */
    // Cover:
    // 1. # methods: 0
    // 2. # of indications: 0
    // 3. # of overlapping: 0
    @Test
    public void sort_empty_list_with_empty_indications() {
        CanonicalSorter sorter = new CanonicalSorter(factory.createClass("aClass"), new String[0]);
        PsiElement[] result = sorter.lineupFilter(new PsiMethod[0]);
        assertEquals(0, result.length);
    }

    // Cover:
    // 1. # methods: 1
    // 2. # of indications: 0
    // 3. # of overlapping: 0
    @Test
    public void sort_single_elem_list_with_empty_indications() {
        CanonicalSorter sorter = new CanonicalSorter(factory.createClass("DumbClass"), new String[0]);
        PsiMethod method = factory.createMethod("test1");
        PsiElement[] result = sorter.lineupFilter(new PsiMethod[]{method});
        assertEquals(0, result.length);
    }

    // Cover:
    // 1. # methods: > 1
    // 2. # of indications: > 1
    // 3. # of overlapping: > 1
    @Test
    public void sort_two_elem_list_with_three_indications() {
        CanonicalSorter sorter = new CanonicalSorter(factory.createClass("DumbClass"), new String[]{"method2", "method3", "method1"});
        PsiMethod method1 = factory.createMethod("method1");
        PsiMethod method2 = factory.createMethod("method2");
        PsiMethod method3 = factory.createMethod("method3");
        PsiElement[] result = sorter.lineupFilter(new PsiMethod[]{method1, method2, method3});
        assertEquals(3, result.length);
        assertEquals(method2, result[0]);
        assertEquals(method3, result[1]);
        assertEquals(method1, result[2]);
    }

    // Cover:
    // 1. # methods: > 1
    // 2. # of indications: > 1
    // 3. # of overlapping: > 1
    @Test
    public void sort_two_elem_list_with_three_indications_duplicate_names() {
        CanonicalSorter sorter = new CanonicalSorter(factory.createClass("DumbClass"),
                new String[]{"A", "ZZZ", "C", "B"});
        PsiMethod method1 = factory.createMethod("A");
        PsiMethod method2 = factory.createMethod("B");
        PsiMethod method3 = factory.createMethod("C");
        PsiMethod method4 = factory.createMethod("A");
        PsiMethod method5 = factory.createMethod("C");
        PsiElement[] result = sorter.lineupFilter(new PsiMethod[]{method1, method2, method3, method4, method5});
        assertEquals(5, result.length);
//         the first elements must be (method1, method4) in either order
        assertTrue((result[0] == method1 && result[1] == method4) || (result[0] == method4 && result[1] == method1));
//         next two elements must be (method3, method5) in either order
        assertTrue((result[2] == method3 && result[3] == method5) || (result[2] == method5 && result[3] == method3));
//         the last element must be method2
        assertEquals(method2, result[4]);
    }

    // Cover:
    // 1. # methods: > 1
    // 2. # of indications: > 1
    // 3. # of overlapping: > 1
    @Test
    public void sort_two_elem_list_with_three_indications_no_duplicate_names() {
        CanonicalSorter sorter = new CanonicalSorter(factory.createClass("DumbClass"), new String[]{"D", "B"});
        PsiMethod method1 = factory.createMethod("A");
        PsiMethod method2 = factory.createMethod("B");
        PsiMethod method3 = factory.createMethod("C");
        PsiMethod method4 = factory.createMethod("D");
        PsiMethod method5 = factory.createMethod("E");
        PsiElement[] result = sorter.lineupFilter(new PsiMethod[]{method1, method2, method3, method4, method5});
        assertEquals(2, result.length);
        assertEquals(method4, result[0]);
        assertEquals(method2, result[1]);
    }

}