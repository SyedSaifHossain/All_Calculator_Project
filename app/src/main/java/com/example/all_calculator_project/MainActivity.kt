package com.example.all_calculator_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.all_calculator_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var navController = findNavController(R.id.fragmentContainerView)
        binding.bottombar.setupWithNavController(navController)


        actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.drawerLayout, R.string.nav_open, R.string.nav_close)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.drawerNav.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bmiFragment ->{
                    binding.drawerLayout.closeDrawers()
                    binding.drawerNav.setupWithNavController(navController)
                }
                R.id.frenheightFragment ->{
                    binding.drawerLayout.closeDrawers()
                    binding.drawerNav.setupWithNavController(navController)
                }
                R.id.setting ->{
                    binding.drawerLayout.closeDrawers()
                }
                R.id.share ->{
                    binding.drawerLayout.closeDrawers()
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            true
        }else super.onOptionsItemSelected(item)
    }

}