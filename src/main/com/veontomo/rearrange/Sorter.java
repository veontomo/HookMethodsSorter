package main.com.veontomo.rearrange;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param order list of methods name. Require that the list contains no pair of equal strings.
     * @return
     */
    public PsiElement[] sort(final PsiMethod[] methods, final String[] order) {
        PsiElement[] result = new PsiElement[methods.length];
        // create an index of methods. Some methods might have equal names.
        Map<String, List<PsiMethod>> index = new HashMap<>();
        for (PsiMethod method : methods){
            String name = method.getName();
            if (index.containsKey(name)){
                index.get(name).add(method);
            } else {
                List<PsiMethod> list = new ArrayList<>();
                list.add(method);
                index.put(name, list);
            }
        }
        // put the methods from the order at the beginning
        int counter = 0;
        for (String methodName : order){
            if (index.containsKey(methodName)){
                for(PsiMethod method: index.get(methodName)){
                    result[counter] = method;
                    counter++;
                }
                index.remove(methodName);
            }
        }
        // put the remaining methods
        for (String methodName : index.keySet()){
            for (PsiMethod method : index.get(methodName)){
                result[counter] = method;
                counter++;
            }
        }
        return result;
    }
}
