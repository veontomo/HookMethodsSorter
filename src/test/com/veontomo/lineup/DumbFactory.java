package com.veontomo.lineup;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.util.MethodSignature;
import com.intellij.psi.util.MethodSignatureBackedByPsiMethod;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

/**
 * A factory to produce the stubs of objects for testing purposes.
 */
class DumbFactory {
    /**
     * Create a psi method corresponding to a method with given name.
     *
     * It returns a stub instance that implements just one method "getName()".
     * @param name a string to be returned on a getName() call.
     * @return a stub instance
     */
    PsiMethod createMethod(final String name) {
        return new PsiMethod() {
            @Nullable
            @Override
            public PsiType getReturnType() {
                return null;
            }

            @Nullable
            @Override
            public PsiTypeElement getReturnTypeElement() {
                return null;
            }

            @NotNull
            @Override
            public PsiParameterList getParameterList() {
                return null;
            }

            @NotNull
            @Override
            public PsiReferenceList getThrowsList() {
                return null;
            }

            @Nullable
            @Override
            public PsiCodeBlock getBody() {
                return null;
            }

            @Override
            public boolean isConstructor() {
                return false;
            }

            @Override
            public boolean isVarArgs() {
                return false;
            }

            @NotNull
            @Override
            public MethodSignature getSignature(@NotNull PsiSubstitutor psiSubstitutor) {
                return null;
            }

            @Nullable
            @Override
            public PsiIdentifier getNameIdentifier() {
                return null;
            }

            @NotNull
            @Override
            public PsiMethod[] findSuperMethods() {
                return new PsiMethod[0];
            }

            @NotNull
            @Override
            public PsiMethod[] findSuperMethods(boolean b) {
                return new PsiMethod[0];
            }

            @NotNull
            @Override
            public PsiMethod[] findSuperMethods(PsiClass psiClass) {
                return new PsiMethod[0];
            }

            @NotNull
            @Override
            public List<MethodSignatureBackedByPsiMethod> findSuperMethodSignaturesIncludingStatic(boolean b) {
                return null;
            }

            @Nullable
            @Override
            public PsiMethod findDeepestSuperMethod() {
                return null;
            }

            @NotNull
            @Override
            public PsiMethod[] findDeepestSuperMethods() {
                return new PsiMethod[0];
            }

            @NotNull
            @Override
            public PsiModifierList getModifierList() {
                return null;
            }

            @NotNull
            @Override
            public String getName() {
                return name;
            }

            @Override
            public PsiElement setName(@NonNls @NotNull String s) {
                return null;
            }

            @NotNull
            @Override
            public HierarchicalMethodSignature getHierarchicalMethodSignature() {
                return null;
            }

            @Override
            public boolean isDeprecated() {
                return false;
            }

            @Override
            public boolean hasTypeParameters() {
                return false;
            }

            @Nullable
            @Override
            public PsiTypeParameterList getTypeParameterList() {
                return null;
            }

            @NotNull
            @Override
            public PsiTypeParameter[] getTypeParameters() {
                return new PsiTypeParameter[0];
            }

            @Nullable
            @Override
            public PsiClass getContainingClass() {
                return null;
            }

            @Nullable
            @Override
            public ItemPresentation getPresentation() {
                return null;
            }

            @Override
            public void navigate(boolean b) {

            }

            @Override
            public boolean canNavigate() {
                return false;
            }

            @Override
            public boolean canNavigateToSource() {
                return false;
            }

            @Nullable
            @Override
            public PsiDocComment getDocComment() {
                return null;
            }

            @Override
            public boolean hasModifierProperty(@PsiModifier.ModifierConstant @NonNls @NotNull String s) {
                return false;
            }

            @NotNull
            @Override
            public Project getProject() throws PsiInvalidElementAccessException {
                return null;
            }

            @NotNull
            @Override
            public Language getLanguage() {
                return null;
            }

            @Override
            public PsiManager getManager() {
                return null;
            }

            @NotNull
            @Override
            public PsiElement[] getChildren() {
                return new PsiElement[0];
            }

            @Override
            public PsiElement getParent() {
                return null;
            }

            @Override
            public PsiElement getFirstChild() {
                return null;
            }

            @Override
            public PsiElement getLastChild() {
                return null;
            }

            @Override
            public PsiElement getNextSibling() {
                return null;
            }

            @Override
            public PsiElement getPrevSibling() {
                return null;
            }

            @Override
            public PsiFile getContainingFile() throws PsiInvalidElementAccessException {
                return null;
            }

            @Override
            public TextRange getTextRange() {
                return null;
            }

            @Override
            public int getStartOffsetInParent() {
                return 0;
            }

            @Override
            public int getTextLength() {
                return 0;
            }

            @Nullable
            @Override
            public PsiElement findElementAt(int i) {
                return null;
            }

            @Nullable
            @Override
            public PsiReference findReferenceAt(int i) {
                return null;
            }

            @Override
            public int getTextOffset() {
                return 0;
            }

            @Override
            public String getText() {
                return null;
            }

            @NotNull
            @Override
            public char[] textToCharArray() {
                return new char[0];
            }

            @Override
            public PsiElement getNavigationElement() {
                return null;
            }

            @Override
            public PsiElement getOriginalElement() {
                return null;
            }

            @Override
            public boolean textMatches(@NotNull @NonNls CharSequence charSequence) {
                return false;
            }

            @Override
            public boolean textMatches(@NotNull PsiElement psiElement) {
                return false;
            }

            @Override
            public boolean textContains(char c) {
                return false;
            }

            @Override
            public void accept(@NotNull PsiElementVisitor psiElementVisitor) {

            }

            @Override
            public void acceptChildren(@NotNull PsiElementVisitor psiElementVisitor) {

            }

            @Override
            public PsiElement copy() {
                return null;
            }

            @Override
            public PsiElement add(@NotNull PsiElement psiElement) throws IncorrectOperationException {
                return null;
            }

            @Override
            public PsiElement addBefore(@NotNull PsiElement psiElement, @Nullable PsiElement psiElement1) throws IncorrectOperationException {
                return null;
            }

            @Override
            public PsiElement addAfter(@NotNull PsiElement psiElement, @Nullable PsiElement psiElement1) throws IncorrectOperationException {
                return null;
            }

            @Override
            public void checkAdd(@NotNull PsiElement psiElement) throws IncorrectOperationException {

            }

            @Override
            public PsiElement addRange(PsiElement psiElement, PsiElement psiElement1) throws IncorrectOperationException {
                return null;
            }

            @Override
            public PsiElement addRangeBefore(@NotNull PsiElement psiElement, @NotNull PsiElement psiElement1, PsiElement psiElement2) throws IncorrectOperationException {
                return null;
            }

            @Override
            public PsiElement addRangeAfter(PsiElement psiElement, PsiElement psiElement1, PsiElement psiElement2) throws IncorrectOperationException {
                return null;
            }

            @Override
            public void delete() throws IncorrectOperationException {

            }

            @Override
            public void checkDelete() throws IncorrectOperationException {

            }

            @Override
            public void deleteChildRange(PsiElement psiElement, PsiElement psiElement1) throws IncorrectOperationException {

            }

            @Override
            public PsiElement replace(@NotNull PsiElement psiElement) throws IncorrectOperationException {
                return null;
            }

            @Override
            public boolean isValid() {
                return false;
            }

            @Override
            public boolean isWritable() {
                return false;
            }

            @Nullable
            @Override
            public PsiReference getReference() {
                return null;
            }

            @NotNull
            @Override
            public PsiReference[] getReferences() {
                return new PsiReference[0];
            }

            @Nullable
            @Override
            public <T> T getCopyableUserData(Key<T> key) {
                return null;
            }

            @Override
            public <T> void putCopyableUserData(Key<T> key, @Nullable T t) {

            }

            @Override
            public boolean processDeclarations(@NotNull PsiScopeProcessor psiScopeProcessor, @NotNull ResolveState resolveState, @Nullable PsiElement psiElement, @NotNull PsiElement psiElement1) {
                return false;
            }

            @Nullable
            @Override
            public PsiElement getContext() {
                return null;
            }

            @Override
            public boolean isPhysical() {
                return false;
            }

            @NotNull
            @Override
            public GlobalSearchScope getResolveScope() {
                return null;
            }

            @NotNull
            @Override
            public SearchScope getUseScope() {
                return null;
            }

            @Override
            public ASTNode getNode() {
                return null;
            }

            @Override
            public boolean isEquivalentTo(PsiElement psiElement) {
                return false;
            }

            @Override
            public Icon getIcon(@IconFlags int i) {
                return null;
            }

            @Nullable
            @Override
            public <T> T getUserData(@NotNull Key<T> key) {
                return null;
            }

            @Override
            public <T> void putUserData(@NotNull Key<T> key, @Nullable T t) {

            }
        };
    }

    /**
     * Return a dumb psi class with given fully qualified name
     * @param name fully qualified class name
     * @return
     */
    PsiClass createClass(final String name){
        return new PsiClass() {
            @Nullable
            @Override
            public String getQualifiedName() {
                return name;
            }

            @Override
            public boolean isInterface() {
                return false;
            }

            @Override
            public boolean isAnnotationType() {
                return false;
            }

            @Override
            public boolean isEnum() {
                return false;
            }

            @Nullable
            @Override
            public PsiReferenceList getExtendsList() {
                return null;
            }

            @Nullable
            @Override
            public PsiReferenceList getImplementsList() {
                return null;
            }

            @NotNull
            @Override
            public PsiClassType[] getExtendsListTypes() {
                return new PsiClassType[0];
            }

            @NotNull
            @Override
            public PsiClassType[] getImplementsListTypes() {
                return new PsiClassType[0];
            }

            @Nullable
            @Override
            public PsiClass getSuperClass() {
                return null;
            }

            @Override
            public PsiClass[] getInterfaces() {
                return new PsiClass[0];
            }

            @NotNull
            @Override
            public PsiClass[] getSupers() {
                return new PsiClass[0];
            }

            @NotNull
            @Override
            public PsiClassType[] getSuperTypes() {
                return new PsiClassType[0];
            }

            @NotNull
            @Override
            public PsiField[] getFields() {
                return new PsiField[0];
            }

            @NotNull
            @Override
            public PsiMethod[] getMethods() {
                return new PsiMethod[0];
            }

            @NotNull
            @Override
            public PsiMethod[] getConstructors() {
                return new PsiMethod[0];
            }

            @NotNull
            @Override
            public PsiClass[] getInnerClasses() {
                return new PsiClass[0];
            }

            @NotNull
            @Override
            public PsiClassInitializer[] getInitializers() {
                return new PsiClassInitializer[0];
            }

            @NotNull
            @Override
            public PsiField[] getAllFields() {
                return new PsiField[0];
            }

            @NotNull
            @Override
            public PsiMethod[] getAllMethods() {
                return new PsiMethod[0];
            }

            @NotNull
            @Override
            public PsiClass[] getAllInnerClasses() {
                return new PsiClass[0];
            }

            @Nullable
            @Override
            public PsiField findFieldByName(@NonNls String s, boolean b) {
                return null;
            }

            @Nullable
            @Override
            public PsiMethod findMethodBySignature(PsiMethod psiMethod, boolean b) {
                return null;
            }

            @NotNull
            @Override
            public PsiMethod[] findMethodsBySignature(PsiMethod psiMethod, boolean b) {
                return new PsiMethod[0];
            }

            @NotNull
            @Override
            public PsiMethod[] findMethodsByName(@NonNls String s, boolean b) {
                return new PsiMethod[0];
            }

            @NotNull
            @Override
            public List<Pair<PsiMethod, PsiSubstitutor>> findMethodsAndTheirSubstitutorsByName(@NonNls String s, boolean b) {
                return null;
            }

            @NotNull
            @Override
            public List<Pair<PsiMethod, PsiSubstitutor>> getAllMethodsAndTheirSubstitutors() {
                return null;
            }

            @Nullable
            @Override
            public PsiClass findInnerClassByName(@NonNls String s, boolean b) {
                return null;
            }

            @Nullable
            @Override
            public PsiElement getLBrace() {
                return null;
            }

            @Nullable
            @Override
            public PsiElement getRBrace() {
                return null;
            }

            @Nullable
            @Override
            public PsiIdentifier getNameIdentifier() {
                return null;
            }

            @Override
            public PsiElement getScope() {
                return null;
            }

            @Override
            public boolean isInheritor(@NotNull PsiClass psiClass, boolean b) {
                return false;
            }

            @Override
            public boolean isInheritorDeep(PsiClass psiClass, @Nullable PsiClass psiClass1) {
                return false;
            }

            @Nullable
            @Override
            public PsiClass getContainingClass() {
                return null;
            }

            @NotNull
            @Override
            public Collection<HierarchicalMethodSignature> getVisibleSignatures() {
                return null;
            }

            @Override
            public PsiElement setName(@NonNls @NotNull String s) {
                return null;
            }

            @Override
            public boolean isDeprecated() {
                return false;
            }

            @Override
            public boolean hasTypeParameters() {
                return false;
            }

            @Nullable
            @Override
            public PsiTypeParameterList getTypeParameterList() {
                return null;
            }

            @NotNull
            @Override
            public PsiTypeParameter[] getTypeParameters() {
                return new PsiTypeParameter[0];
            }

            @Nullable
            @Override
            public ItemPresentation getPresentation() {
                return null;
            }

            @Override
            public void navigate(boolean b) {

            }

            @Override
            public boolean canNavigate() {
                return false;
            }

            @Override
            public boolean canNavigateToSource() {
                return false;
            }

            @Nullable
            @Override
            public PsiDocComment getDocComment() {
                return null;
            }

            @Nullable
            @Override
            public PsiModifierList getModifierList() {
                return null;
            }

            @Override
            public boolean hasModifierProperty(@PsiModifier.ModifierConstant @NonNls @NotNull String s) {
                return false;
            }

            @Nullable
            @Override
            public String getName() {
                return null;
            }

            @NotNull
            @Override
            public Project getProject() throws PsiInvalidElementAccessException {
                return null;
            }

            @NotNull
            @Override
            public Language getLanguage() {
                return null;
            }

            @Override
            public PsiManager getManager() {
                return null;
            }

            @NotNull
            @Override
            public PsiElement[] getChildren() {
                return new PsiElement[0];
            }

            @Override
            public PsiElement getParent() {
                return null;
            }

            @Override
            public PsiElement getFirstChild() {
                return null;
            }

            @Override
            public PsiElement getLastChild() {
                return null;
            }

            @Override
            public PsiElement getNextSibling() {
                return null;
            }

            @Override
            public PsiElement getPrevSibling() {
                return null;
            }

            @Override
            public PsiFile getContainingFile() throws PsiInvalidElementAccessException {
                return null;
            }

            @Override
            public TextRange getTextRange() {
                return null;
            }

            @Override
            public int getStartOffsetInParent() {
                return 0;
            }

            @Override
            public int getTextLength() {
                return 0;
            }

            @Nullable
            @Override
            public PsiElement findElementAt(int i) {
                return null;
            }

            @Nullable
            @Override
            public PsiReference findReferenceAt(int i) {
                return null;
            }

            @Override
            public int getTextOffset() {
                return 0;
            }

            @Override
            public String getText() {
                return null;
            }

            @NotNull
            @Override
            public char[] textToCharArray() {
                return new char[0];
            }

            @Override
            public PsiElement getNavigationElement() {
                return null;
            }

            @Override
            public PsiElement getOriginalElement() {
                return null;
            }

            @Override
            public boolean textMatches(@NotNull @NonNls CharSequence charSequence) {
                return false;
            }

            @Override
            public boolean textMatches(@NotNull PsiElement psiElement) {
                return false;
            }

            @Override
            public boolean textContains(char c) {
                return false;
            }

            @Override
            public void accept(@NotNull PsiElementVisitor psiElementVisitor) {

            }

            @Override
            public void acceptChildren(@NotNull PsiElementVisitor psiElementVisitor) {

            }

            @Override
            public PsiElement copy() {
                return null;
            }

            @Override
            public PsiElement add(@NotNull PsiElement psiElement) throws IncorrectOperationException {
                return null;
            }

            @Override
            public PsiElement addBefore(@NotNull PsiElement psiElement, @Nullable PsiElement psiElement1) throws IncorrectOperationException {
                return null;
            }

            @Override
            public PsiElement addAfter(@NotNull PsiElement psiElement, @Nullable PsiElement psiElement1) throws IncorrectOperationException {
                return null;
            }

            @Override
            public void checkAdd(@NotNull PsiElement psiElement) throws IncorrectOperationException {

            }

            @Override
            public PsiElement addRange(PsiElement psiElement, PsiElement psiElement1) throws IncorrectOperationException {
                return null;
            }

            @Override
            public PsiElement addRangeBefore(@NotNull PsiElement psiElement, @NotNull PsiElement psiElement1, PsiElement psiElement2) throws IncorrectOperationException {
                return null;
            }

            @Override
            public PsiElement addRangeAfter(PsiElement psiElement, PsiElement psiElement1, PsiElement psiElement2) throws IncorrectOperationException {
                return null;
            }

            @Override
            public void delete() throws IncorrectOperationException {

            }

            @Override
            public void checkDelete() throws IncorrectOperationException {

            }

            @Override
            public void deleteChildRange(PsiElement psiElement, PsiElement psiElement1) throws IncorrectOperationException {

            }

            @Override
            public PsiElement replace(@NotNull PsiElement psiElement) throws IncorrectOperationException {
                return null;
            }

            @Override
            public boolean isValid() {
                return false;
            }

            @Override
            public boolean isWritable() {
                return false;
            }

            @Nullable
            @Override
            public PsiReference getReference() {
                return null;
            }

            @NotNull
            @Override
            public PsiReference[] getReferences() {
                return new PsiReference[0];
            }

            @Nullable
            @Override
            public <T> T getCopyableUserData(Key<T> key) {
                return null;
            }

            @Override
            public <T> void putCopyableUserData(Key<T> key, @Nullable T t) {

            }

            @Override
            public boolean processDeclarations(@NotNull PsiScopeProcessor psiScopeProcessor, @NotNull ResolveState resolveState, @Nullable PsiElement psiElement, @NotNull PsiElement psiElement1) {
                return false;
            }

            @Nullable
            @Override
            public PsiElement getContext() {
                return null;
            }

            @Override
            public boolean isPhysical() {
                return false;
            }

            @NotNull
            @Override
            public GlobalSearchScope getResolveScope() {
                return null;
            }

            @NotNull
            @Override
            public SearchScope getUseScope() {
                return null;
            }

            @Override
            public ASTNode getNode() {
                return null;
            }

            @Override
            public boolean isEquivalentTo(PsiElement psiElement) {
                return false;
            }

            @Override
            public Icon getIcon(@IconFlags int i) {
                return null;
            }

            @Nullable
            @Override
            public <T> T getUserData(@NotNull Key<T> key) {
                return null;
            }

            @Override
            public <T> void putUserData(@NotNull Key<T> key, @Nullable T t) {

            }
        };
    }
}
