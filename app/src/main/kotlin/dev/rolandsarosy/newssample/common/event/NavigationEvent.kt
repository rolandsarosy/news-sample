package dev.rolandsarosy.newssample.common.event

import android.os.Bundle
import androidx.navigation.NavOptions

data class NavigationEvent(val navEndpoint: Int, val bundle: Bundle? = null, val navOptions: NavOptions? = null)
