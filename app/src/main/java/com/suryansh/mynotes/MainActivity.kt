package com.suryansh.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.NavigationView)
        setUpToolbar()

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer)


        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.notes -> {
                    startActivity(Intent(this@MainActivity, MainActivity::class.java))
                }
                R.id.starred -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, StarredNotesFragment())
                        .commit()

                    drawerLayout.closeDrawers()

                }
                R.id.reminders -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, ReminderNotesFragment())
                        .commit()

                    drawerLayout.closeDrawers()

                }
                R.id.diary -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, DiaryFragment())
                        .commit()

                    drawerLayout.closeDrawers()

                }
                R.id.finance -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, FinanceFragment())
                        .commit()

                    drawerLayout.closeDrawers()

                }
                R.id.health -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, HealthNotesFragment())
                        .commit()

                    drawerLayout.closeDrawers()
                }
                R.id.personal -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, PersonalNotesFragment())
                        .commit()

                    drawerLayout.closeDrawers()
                }
                R.id.shopping -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, ShoppingNotesFragment())
                        .commit()

                    drawerLayout.closeDrawers()
                }
                R.id.work -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, WorkNotesFragment())
                        .commit()

                    drawerLayout.closeDrawers()
                }
                R.id.withoutFolder -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, WithoutFolderFragment())
                        .commit()

                    drawerLayout.closeDrawers()
                }
                R.id.manageFolders -> {
                    startActivity(Intent(this@MainActivity, ManageFolderActivity::class.java))
                }
                R.id.trash -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, TrashFragment())
                        .commit()

                    drawerLayout.closeDrawers()
                }
                R.id.settings -> {
                    startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
                }
                R.id.premium -> {
                    startActivity(Intent(this@MainActivity, PremiumActivity::class.java))
                }


            }

            return@setNavigationItemSelectedListener true
        }

    }


    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Notes"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id== android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
            }
        return super.onOptionsItemSelected(item)
    }

}