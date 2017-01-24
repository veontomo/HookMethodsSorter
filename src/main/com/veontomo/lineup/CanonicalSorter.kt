package com.veontomo.lineup

import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import org.jetbrains.kotlin.asJava.elements.KtLightElement


/**
 * Sort the class fields and methods in the canonical order.
 *
 * A class is said to be sorted canonically if its fields and methods are in this order:
 * 1. fields
 * 1. base methods
 * 2. other methods
 *
 * The order of the base methods is defined by array lineup.
 */
class CanonicalSorter(private val aClass: PsiClass, private val lineup: Array<String>) {

    /**
     * Perform the ordering of the class methods and fields.
     */
    fun execute() {
        val methods = aClass.methods
        val fields = aClass.fields
        val sorted = lineupFilter(methods)
        val pivot = getFirstMethodOrField(aClass)?.navigationElement
        val parent = pivot?.parent

        if (pivot != null && parent != null) {
            // place the fields after the pivot
            for (field in fields) {
                try {
                    if (field != null) {
                        parent.addBefore(field.navigationElement, pivot)
                    } else {
                        Notifier("cycle").notify("field is null")
                    }
                } catch(e: Exception) {
                    Notifier("cycle").notify("field: ${field.text}, parent: ${parent.text}, exception: ${e.message}")
                }
            }
            fields.forEach { parent.addBefore(it.navigationElement, pivot) }
//             place the lineup methods after the fields
            sorted.forEach { parent.addBefore(it.navigationElement, pivot) }
//             remove the above inserted elements in order to avoid duplicates
            fields.forEach { it.navigationElement.delete() }
            sorted.forEach { it.navigationElement.delete() }
        }
    }

    /**
     * Return methods which names are in the lineup array respecting the order in which they
     * appear in the lineup.
     * If their exists a pair of methods m1 and m2 such that
     * their names are in the lineup array at positions  i1 and i2 respectively,
     * and i1 < i2, then the positions r1 and r2 of the methods m1 and m2 in the
     * resulting array must obey the relation:
     * r1 < r2.

     * @param methods array of methods
     *
     * @return methods from the above array whose names are in the lineup; the order is defined above.
     */
    fun lineupFilter(methods: Array<PsiMethod>): Array<PsiMethod> {
        val list = mutableListOf<PsiMethod>()
        for (methodName in lineup) {
            methods.forEach { if (it.name == methodName) list.add(it) }
        }
        return list.toTypedArray()
    }


    /**
     * Return the first child that is either a field or a method.

     * @param aClass
     * *
     * @return a method or a field with the lowest offset
     */
    fun getFirstMethodOrField(aClass: PsiClass): PsiElement? {
        val methods = aClass.methods
        val fields = aClass.fields
        val methodTotal = methods.size
        val fieldTotal = fields.size
        if (methodTotal == 0 && fieldTotal == 0) return null
        if (methodTotal == 0) return fields[0]
        if (fieldTotal == 0) return methods[0]
        val firstMethodOffset = methods[0].navigationElement.startOffsetInParent
        val firstFieldOffset = fields[0].navigationElement.startOffsetInParent
        return if (firstFieldOffset > firstMethodOffset) methods[0] else fields[0]
    }


}
