package dev.rolandsarosy.newssample.features.dashboardcontainer.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.rolandsarosy.newssample.features.feed.FeedFragment
import dev.rolandsarosy.newssample.features.preferences.PreferencesFragment
import dev.rolandsarosy.newssample.features.readinglist.ReadingListFragment

class DashboardContainerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments: List<Fragment> = listOf(ReadingListFragment(), FeedFragment(), PreferencesFragment())

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}
