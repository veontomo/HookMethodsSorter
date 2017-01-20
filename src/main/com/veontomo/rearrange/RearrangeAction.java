package main.com.veontomo.rearrange;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.*;


/**
 * Plugin for rearrange fields and methods of classes found in a file that is currently open in the
 * Intellij IDEA IDE.
 */
public class RearrangeAction extends AnAction {
    /**
     * list of basic method names that should be ordered according to their indexes in the array:
     */
    private final String[] BASE_METHOD_NAMES = {
            "onAttach", "onCreate", "onCreateView", "onViewCreated", "onActivityCreated", "onViewStateRestored",
            "onRestart", "onStart", "onResume", "onPause", "onStop", "onDestroyView", "onDestroy", "onDetach"
    };

    private final Notifier notifier = new Notifier("Rearrange plugin");


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
     * Make a given class be ordered canonically.
     *
     * @param aClass a class whose methods and fields are to be ordered.
     */
    private void elaborateSingleClass(final PsiClass aClass) {
        new WriteCommandAction.Simple(aClass.getProject(), aClass.getContainingFile()) {
            @Override
            protected void run() throws Throwable {
                CanonicalSorter sorter = new CanonicalSorter(aClass, BASE_METHOD_NAMES);
                sorter.execute();
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
