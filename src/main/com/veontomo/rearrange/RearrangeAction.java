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

    private final Notifier notifier = new Notifier("Plugin");


    @Override
    public void actionPerformed(AnActionEvent e) {
        final PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        PsiClass[] psiClasses = getPsiClasses(psiFile);
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
            private final Notifier notifier = new Notifier(aClass.getName());

            @Override
            protected void run() throws Throwable {
                Sorter sorter = new Sorter();
                PsiMethod[] methods = aClass.getMethods();
                PsiField[] fields = aClass.getFields();
                PsiMethod[] sorted = sorter.lineupFilter(methods, BASIC_METHOD_NAMES);
                PsiElement firstElem = sorter.getFirstMethodOrField(aClass);

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

        }.execute();

    }


    /**
     * Determine classes that the currently open file contains
     *
     * @param psiFile current psi file
     * @return array of PsiClass instances
     */
    private PsiClass[] getPsiClasses(final PsiFile psiFile) {
        if (psiFile != null && psiFile instanceof PsiClassOwner) {
            return ((PsiClassOwner) psiFile).getClasses();
        }
        return null;

    }
}
