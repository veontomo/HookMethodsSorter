package com.veontomo.hookmethodssorter;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.compiler.CompilationException;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Sort well-known methods (hook ones).
 */
public class Sort extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        PsiClass[] psiClasses = getPsiClasses(e);
        if (psiClasses != null) {
            elaborateMultipleClasses(psiClasses);
        } else {
            System.out.println("no class is found");
        }

    }

    /**
     * Iterate over given array of classes and elaborate each class.
     *
     * @param classes array of classes
     */
    private void elaborateMultipleClasses(final PsiClass[] classes) {
        for (PsiClass aClass : classes) {
            elaborateSingleClass(aClass);
        }
    }

    /**
     * Order the class methods in a predefined way.
     *
     * @param aClass a class whose methods/properties are to be ordered.
     */
    private void elaborateSingleClass(final PsiClass aClass) {
        new WriteCommandAction.Simple(aClass.getProject(), aClass.getContainingFile()) {
            @Override
            protected void run() throws Throwable {
                final String separator = System.getProperty("line.separator");
                StringBuilder builder = new StringBuilder();
                PsiMethod[] methods = aClass.getMethods();
                PsiField[] fields = aClass.getFields();
                builder.append("Fields: ");
                for (PsiField field : fields) {
                    builder.append(field.getName()).append(", ");
                }
                builder.append(separator).append("Methods: ");

                for (PsiMethod method : methods) {
                    builder.append(method.getName()).append(", ");
                }
                Messages.showMessageDialog(aClass.getProject(), builder.toString(), "Info", Messages.getInformationIcon());

//                int len = methods.length;
//                if (len > 2) {
//                    PsiElement elem1 = methods[0].getNavigationElement();
//                    PsiElement elem2 = methods[1].getNavigationElement();
//                    PsiElement parent = elem1.getParent();
//                    parent.addAfter(elem1, elem2);
//                    elem1.getNavigationElement().delete();
//
//                }
            }
        }.execute();
    }


    /**
     * Determine classes that the currently open file contains
     * @param e the action event that occurred
     * @return array of PsiClass instances
     */
    private PsiClass[] getPsiClasses(AnActionEvent e) {
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        if (psiFile != null && psiFile instanceof PsiClassOwner) {
            return ((PsiClassOwner) psiFile).getClasses();
        }
        return null;

    }
}
