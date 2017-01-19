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
 */
public class Sorter {
    /**
     * Return a new array constructed from the elements of a given array by sorting them in a given order.
     *
     * @param methods
     * @param order   list of methods name. Require that the list contains no pair of equal strings.
     * @return
     */
    public PsiMethod[] sort(final PsiMethod[] methods, final String[] order) {
        PsiMethod[] result = new PsiMethod[methods.length];
        // create an index of methods. Some methods might have equal names.
        Map<String, List<PsiMethod>> index = getIndex(methods);
        // put the methods from the order at the beginning
        int counter = 0;
        for (String methodName : order) {
            if (index.containsKey(methodName)) {
                List<PsiMethod> items = index.get(methodName);
                writeToArray(result, items, counter);
                counter += items.size();
                index.remove(methodName);
            }
        }
        // put the remaining methods
        for (String methodName : index.keySet()) {
            List<PsiMethod> items = index.get(methodName);
            writeToArray(result, items, counter);
            counter += items.size();
        }
        assert result.length == methods.length;
        return result;
    }

    /**
     * Create an index of methods.
     * The index is a  map from method names to the methods themselves.
     * A class might contain methods with coinciding names (due to the method overload or to an error).
     *
     * @return a map from a string into a list of methods with that string as a name
     */
    private Map<String, List<PsiMethod>> getIndex(PsiMethod[] methods) {
        Map<String, List<PsiMethod>> index = new HashMap<>();
        if (methods == null) {
            return index;
        }
        for (PsiMethod method : methods) {
            String name = method.getName();
            if (index.containsKey(name)) {
                index.get(name).add(method);
            } else {
                List<PsiMethod> list = new ArrayList<>();
                list.add(method);
                index.put(name, list);
            }
        }
        return index;
    }

    /**
     * Put items to the array starting at given offset.
     * Assume that the offset is a valid one and that there is enough room for all items.
     *
     * @param target an array that gets modified
     * @param items  elements to be inserted
     * @param offset index of the first element in the target that is to be modified
     */
    private void writeToArray(final PsiMethod[] target, final List<PsiMethod> items, int offset) {
        int index = offset;
        for (PsiMethod item : items) {
            target[index] = item;
            index++;
        }
    }

}
