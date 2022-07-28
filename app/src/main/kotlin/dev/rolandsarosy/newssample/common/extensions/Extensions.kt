package dev.rolandsarosy.newssample.common.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun NavController.navigateSafely(callerFragment: Fragment, @IdRes resId: Int, args: Bundle? = null, navOptions: NavOptions? = null) {
    val currentDestination = (this.currentDestination as? FragmentNavigator.Destination)?.className
        ?: (this.currentDestination as? DialogFragmentNavigator.Destination)?.className
    if (currentDestination == callerFragment.javaClass.name || currentDestination == callerFragment.parentFragment?.javaClass?.name) {
        this.navigate(resId, args, navOptions)
    }
}

fun waitAndRun(delay: Long, viewModelScope: CoroutineScope, doOnComplete: suspend () -> Unit) {
    viewModelScope.launch(Dispatchers.IO) {
        delay(delay)
        doOnComplete.invoke()
    }
}
