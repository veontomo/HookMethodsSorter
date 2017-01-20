package main.com.veontomo.rearrange;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.*;


/**
 * Plugin for rearrange fields and methods of classes found in a file that is currently open in the
 * Intellij IDEA IDE.
 * <p>
 * After applying this plugin, a class must have fields and methods being ordered as follows:
 * 1. fields
 * 2. basic methods
 * 3. other methods
 */
public class RearrangeAction extends AnAction {
    /**
     * list of basic method names that should be ordered according to their indexes in the array:
     */
    private final String[] BASIC_METHOD_NAMES = {
            "onAttach", "onCreate", "onCreateView", "onViewCreated", "onActivityCreated", "onViewStateRestored",
            "onRestart", "onStart", "onResume", "onPause", "onStop", "onDestroyView", "onDestroy", "onDetach"
    };

    private final Notification notifier = new Notification("Plugin");


    @Override
    public void actionPerformed(AnActionEvent e) {
        PsiClass[] psiClasses = getPsiClasses(e);
        if (psiClasses != null) {
            elaborateMultipleClasses(psiClasses);
        } else {
            notifier.notify("no class is found");
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
     * Order the class BASIC_METHOD_NAMES in a predefined way.
     *
     * @param aClass a class whose methods and fields are to be ordered.
     */
    private void elaborateSingleClass(final PsiClass aClass) {
        new WriteCommandAction.Simple(aClass.getProject(), aClass.getContainingFile()) {
            /**
             * Name of the class that is to be modified
             */
            private final Notification notifier = new Notification(aClass.getName());

            @Override
            protected void run() throws Throwable {
                Sorter sorter = new Sorter();
                PsiMethod[] methods = aClass.getMethods();
                PsiField[] fields = aClass.getFields();
                PsiMethod[] sorted = sorter.lineupFilter(methods, BASIC_METHOD_NAMES);
                PsiElement firstElem = getFirstMethodOrField(aClass);

                if (firstElem == null) {
                    notifier.notify("Neither method nor field is found");
                    return;
                }
                notifier.notify("first; " + firstElem.getText());
                PsiElement firstNavElem = firstElem.getNavigationElement();
                PsiElement parent = firstNavElem.getParent();

                if (parent == null) {
                    notifier.notify("No parent is found");
                    return;
                }
                for (PsiElement field : fields) {
                    parent.addBefore(field.getNavigationElement(), firstNavElem);
                }
                for (PsiElement method : sorted) {
                    parent.addBefore(method.getNavigationElement(), firstNavElem);
                }

                for (PsiElement field : fields) {
                    field.getNavigationElement().delete();
                }
                for (PsiElement method : sorted) {
                    method.getNavigationElement().delete();
                }

            }

            /**
             * Return the first child that is either a field or a method.
             *
             * @param aClass
             * @return
             */
            private PsiElement getFirstMethodOrField(final PsiClass aClass) {
                final PsiMethod[] methods = aClass.getMethods();
                final PsiField[] fields = aClass.getFields();
                final int methodTotal = methods.length;
                final int fieldTotal = fields.length;
                if (methodTotal == 0 && fieldTotal == 0) return null;
                if (methodTotal == 0) return fields[0];
                if (fieldTotal == 0) return methods[0];
                final int firstMethodOffset = methods[0].getNavigationElement().getStartOffsetInParent();
                final int firstFieldOffset = fields[0].getNavigationElement().getStartOffsetInParent();
                return (firstFieldOffset > firstMethodOffset) ? methods[0] : fields[0];
            }
        }.execute();

    }


    /**
     * Determine classes that the currently open file contains
     *
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
