# Line up the class lifecycle methods
This is a plugin for Intellij IDEA IDE that rearranges fields and the Android lifecycle methods of Kotlin and Java classes. 
After applying the plugin, the class declaration is to have the following structure:

1. field declarations
2. lifecycle method declarations
3. other method declarations

The lifecycle methods are those of both activities and fragments and become sorted in this order:

1. onAttach
2. onCreate
3. onCreateView
4. onViewCreated
5. onActivityCreated
6. onViewStateRestored
7. onRestoreInstanceState
8. onRestart
9. onStart
10. onResume
11. onPause
12. onSaveInstanceState
13. onStop
14. onDestroyView
15. onDestroy
16. onDetach

The idea of this plugin is inspired by <a href="https://github.com/armandAkop/Lifecycle-Sorter">Lifecycle Sorter for Android Studio</a> one.

 
