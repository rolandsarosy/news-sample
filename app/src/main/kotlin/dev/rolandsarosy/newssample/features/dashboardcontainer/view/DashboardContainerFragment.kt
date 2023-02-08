package dev.rolandsarosy.newssample.features.dashboardcontainer.view

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dev.rolandsarosy.newssample.R
import dev.rolandsarosy.newssample.common.base.BaseFragment
import dev.rolandsarosy.newssample.databinding.FragmentDashboardContainerBinding
import dev.rolandsarosy.newssample.features.dashboardcontainer.adapter.DashboardContainerAdapter
import timber.log.Timber

class DashboardContainerFragment : BaseFragment<FragmentDashboardContainerBinding>(FragmentDashboardContainerBinding::inflate) {

    companion object {
        private const val PAGER_INDEX_0_READING_LIST = 0
        private const val PAGER_INDEX_1_FEED = 1
        private const val PAGER_INDEX_2_PREFERENCES = 2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        binding.viewpager.adapter = DashboardContainerAdapter(this)
        binding.viewpager.isUserInputEnabled = false
        initTabLayoutMediator()
        initPageChangedCallback()
    }

    private fun initTabLayoutMediator() {
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.customView = when (position) {
                PAGER_INDEX_0_READING_LIST -> ImageView(requireContext()).apply { setImageResource(R.drawable.ic_tab_reading_list) }
                PAGER_INDEX_1_FEED -> ImageView(requireContext()).apply { setImageResource(R.drawable.ic_tab_feed) }
                PAGER_INDEX_2_PREFERENCES -> ImageView(requireContext()).apply { setImageResource(R.drawable.ic_tab_preferences) }
                else -> throw IllegalArgumentException("TabLayoutMediator has reached an invalid position.")
            }
        }.attach()
    }

    private fun initPageChangedCallback() {
        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setTabLayoutColor(position)
            }
        })
    }

    private fun setTabLayoutColor(position: Int) {
        when (position) {
            PAGER_INDEX_0_READING_LIST -> {
                setTabColorFilter(PAGER_INDEX_0_READING_LIST, R.color.magenta)
                clearTabColorFilter(PAGER_INDEX_1_FEED)
                clearTabColorFilter(PAGER_INDEX_2_PREFERENCES)
            }
            PAGER_INDEX_1_FEED -> {
                clearTabColorFilter(PAGER_INDEX_0_READING_LIST)
                setTabColorFilter(PAGER_INDEX_1_FEED, R.color.teal)
                clearTabColorFilter(PAGER_INDEX_2_PREFERENCES)
            }
            PAGER_INDEX_2_PREFERENCES -> {
                clearTabColorFilter(PAGER_INDEX_0_READING_LIST)
                clearTabColorFilter(PAGER_INDEX_1_FEED)
                setTabColorFilter(PAGER_INDEX_2_PREFERENCES, R.color.orange)
            }
        }
    }

    private fun setTabColorFilter(position: Int, color: Int) {
        binding.tabs.getTabAt(position)?.let { tab ->
            (tab.customView as ImageView?)?.setColorFilter(ContextCompat.getColor(requireContext(), color), PorterDuff.Mode.SRC_ATOP)
        }
        binding.tabs.setSelectedTabIndicator(R.drawable.ic_tab_indicator)
        binding.tabs.setSelectedTabIndicatorColor(ContextCompat.getColor(requireContext(), color))
    }

    private fun clearTabColorFilter(position: Int) = binding.tabs.getTabAt(position)?.let { (it.customView as ImageView?)?.clearColorFilter() }
}
