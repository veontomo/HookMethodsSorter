package com.veontomo.hookmethodssorter;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;

/**
 * Sort well-known methods (hook ones).
 */
public class Sort extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);

        if (psiFile == null || editor == null) {
            return;
        }

        int offSet = editor.getCaretModel().getOffset();
        PsiElement elementAt = psiFile.findElementAt(offSet);
        PsiClass psiClass = PsiTreeUtil.getParentOfType(elementAt, PsiClass.class);
        String msg = psiClass == null ? "no class found" :  String.valueOf(psiClass.getMethods().length);
        Messages.showMessageDialog(project, msg, "Information", Messages.getInformationIcon());

    }
}
