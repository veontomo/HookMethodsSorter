package com.veontomo.hookmethodssorter;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.*;

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
                PsiMethod[] methods = aClass.getMethods();
                int len = methods.length;
//                Messages.showMessageDialog(aClass.getProject(), "Sorting class " + aClass.getName() + " with " + len + " methods.", "Info", Messages.getInformationIcon());
                if (len > 2) {
                    PsiMethod anchor = aClass.getMethods()[0];
                    PsiMethod elem = aClass.getMethods()[len - 2];
//                    Messages.showMessageDialog(aClass.getProject(), "Removing method  " + elem.getName() + " in class " + aClass.getName(), "Info", Messages.getInformationIcon());
//                    String oldName = elem.getName();
//                    elem.setName(oldName  + "A");
//                    aClass.getNavigationElement();
                    aClass.getNavigationElement().addBefore(elem.getNavigationElement(), anchor.getNavigationElement());
//                    aClass.addBefore(methods[len - 2], elem);

                }
            }
        }.execute();
    }


    /**
     * @param e the action event that occurred
     * @return The PSIClass object based on which class your mouse cursor was in
     */
    private PsiClass[] getPsiClasses(AnActionEvent e) {
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        PsiClassOwner owner = (PsiClassOwner) psiFile;
        if (owner != null) {
            return owner.getClasses();
        }
        return null;

    }
}
