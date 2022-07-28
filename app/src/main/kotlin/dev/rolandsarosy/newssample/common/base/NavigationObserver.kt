package dev.rolandsarosy.newssample.common.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import dev.rolandsarosy.newssample.R
import dev.rolandsarosy.newssample.common.event.Event
import dev.rolandsarosy.newssample.common.event.NavigationEvent
import dev.rolandsarosy.newssample.common.extensions.navigateSafely

interface NavigationObserver {
    fun defaultNavigationObserver(activity: FragmentActivity, callerFragment: Fragment): Observer<Event<NavigationEvent>> =
        Observer<Event<NavigationEvent>> { event ->
            event.getContentIfNotHandled()?.let { navigationEvent ->
                activity
                    .findNavController(R.id.main_navigation_host)
                    .navigateSafely(callerFragment, navigationEvent.navEndpoint, navigationEvent.bundle, navigationEvent.navOptions)
            }
        }
}
