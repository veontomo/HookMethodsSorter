# Line up the class lifecycle methods
This is a plugin for Intellij IDEA IDE that rearranges fields and the Android lifecycle methods of Kotlin and Java classes. 
After applying the plugin, the class declaration is to have the following structure:
    <ol>
    <li>field declarations</li>
    <li>lifecycle method declarations</li>
    <li>other method declarations</li>
    </ol>
    The lifecycle methods are those of both activities and fragments and become sorted in this order:
    <ol>
        <li>onAttach</li>
        <li>onCreate</li>
        <li>onCreateView</li>
        <li>onViewCreated</li>
        <li>onActivityCreated</li>
        <li>onViewStateRestored</li>
        <li>onRestoreInstanceState</li>
        <li>onRestart</li>
        <li>onStart</li>
        <li>onResume</li>
        <li>onPause</li>
        <li>onSaveInstanceState</li>
        <li>onStop</li>
        <li>onDestroyView</li>
        <li>onDestroy</li>
        <li>onDetach</li>
    </ol>
    The idea of this plugin is inspired by <a href="https://github.com/armandAkop/Lifecycle-Sorter">Lifecycle Sorter for Android Studio</a> one.
 
