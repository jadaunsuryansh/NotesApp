package com.suryansh.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
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

    var previousMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.NavigationView)
        setUpToolbar()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, NotesFragment())
            .commit()

        supportActionBar?.title = "Notes"

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer)


        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {

            if(previousMenuItem!= null){
                previousMenuItem?.isChecked = false
            }

            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it

            when(it.itemId){
                R.id.notes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, NotesFragment())
                        .commit()

                    supportActionBar?.title = "Notes"
                    drawerLayout.closeDrawers()
                }
                R.id.starred -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, StarredNotesFragment())
                        .commit()

                    supportActionBar?.title = "Starred"
                    drawerLayout.closeDrawers()


                }
                R.id.reminders -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, ReminderNotesFragment())
                        .commit()

                    supportActionBar?.title = "Reminders"

                    drawerLayout.closeDrawers()

                }
                R.id.diary -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, DiaryFragment())
                        .commit()

                    supportActionBar?.title = "Diary"

                    drawerLayout.closeDrawers()

                }
                R.id.finance -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, FinanceFragment())
                        .commit()

                    supportActionBar?.title = "Finance"

                    drawerLayout.closeDrawers()

                }
                R.id.health -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, HealthNotesFragment())
                        .commit()

                    supportActionBar?.title = "Health"

                    drawerLayout.closeDrawers()
                }
                R.id.personal -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, PersonalNotesFragment())
                        .commit()

                    supportActionBar?.title = "Personal"

                    drawerLayout.closeDrawers()
                }
                R.id.shopping -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, ShoppingNotesFragment())
                        .commit()

                    supportActionBar?.title = "Shopping"

                    drawerLayout.closeDrawers()
                }
                R.id.work -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, WorkNotesFragment())
                        .commit()

                    supportActionBar?.title = "Work"

                    drawerLayout.closeDrawers()
                }
                R.id.withoutFolder -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, WithoutFolderFragment())
                        .commit()

                    supportActionBar?.title = "Without Folder"

                    drawerLayout.closeDrawers()
                }
                R.id.manageFolders -> {
                    startActivity(Intent(this@MainActivity, ManageFolderActivity::class.java))

                    supportActionBar?.title = "Manage Folders"
                }


                R.id.trash -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, TrashFragment())
                        .commit()

                    supportActionBar?.title = "Trash"

                    drawerLayout.closeDrawers()
                }
                R.id.settings -> {
                    startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
                    supportActionBar?.title = "Settings"
                }
                R.id.premium -> {
                    startActivity(Intent(this@MainActivity, PremiumActivity::class.java))
                    supportActionBar?.title = "Premium"
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