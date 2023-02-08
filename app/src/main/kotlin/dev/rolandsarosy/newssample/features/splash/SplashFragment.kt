package dev.rolandsarosy.newssample.features.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.fragment.findNavController
import dev.rolandsarosy.newssample.R
import dev.rolandsarosy.newssample.common.base.BaseFragment
import dev.rolandsarosy.newssample.common.extensions.navigateSafely
import dev.rolandsarosy.newssample.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    companion object {
        private const val DELAY_TIME = 750L
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Placeholder timer
        Handler().postDelayed({ findNavController().navigateSafely(this, R.id.action_splashFragment_to_dashboardContainerFragment) }, DELAY_TIME)
    }
}
