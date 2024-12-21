package com.example.wallpaper

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ViewPager va TabLayout
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        val images = listOf(R.drawable.img1, R.drawable.img2, R.drawable.img3,
            R.drawable.img4, R.drawable.img5, R.drawable.img6,
            R.drawable.img7, R.drawable.img8, R.drawable.img9)
        val adapter = ImageAdapter(images) { imageResId ->
            val intent = Intent(this, FullImageActivity::class.java)
            intent.putExtra("IMAGE_RES_ID", imageResId)
            startActivity(intent)
        }

        val fragments = listOf(
            RecyclerFragment(adapter),
            RecyclerFragment(adapter),
            RecyclerFragment(adapter),
            RecyclerFragment(adapter),
            RecyclerFragment(adapter),
        )

        viewPager.adapter = ViewPagerAdapter(this, fragments)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Home"
                1 -> tab.text = "Popular"
                2 -> tab.text = "Random"
                3 -> tab.text = "Liked"
                4 -> tab.text = "History"
                else -> tab.text = "Tab ${position + 1}"
            }
        }.attach()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    viewPager.setCurrentItem(0, true)
                    true
                }
                R.id.popular -> {
                    viewPager.setCurrentItem(1, true)
                    true
                }
                R.id.favorites -> {
                    viewPager.setCurrentItem(3, true)
                    true
                }
                R.id.random -> {
                    viewPager.setCurrentItem(2, true)
                    true
                }

                else -> false
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navigationView: NavigationView = findViewById(R.id.navigationView)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    viewPager.setCurrentItem(0, true)
                }
                R.id.nav_popular -> {
                    viewPager.setCurrentItem(1, true)
                }
                R.id.nav_random -> {
                    viewPager.setCurrentItem(2, true)
                }
                R.id.nav_liked -> {
                    viewPager.setCurrentItem(3, true)
                }
                R.id.nav_history -> {
                    viewPager.setCurrentItem(4, true)
                }
                R.id.nav_about -> {
                    Toast.makeText(this, "Bu app da siz istalgan rasmni topishingiz mumkin", Toast.LENGTH_SHORT).show()
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }
}