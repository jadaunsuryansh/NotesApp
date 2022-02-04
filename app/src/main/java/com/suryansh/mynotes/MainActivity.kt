package com.suryansh.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface  {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    lateinit var viewModal: NoteViewModal
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

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

                    supportActionBar?.title = "Starred"
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

        notesRV = findViewById(R.id.notesRV)
        addFAB = findViewById(R.id.idFAB)

        // on below line we are setting layout
        // manager to our recycler view.
        notesRV.layoutManager = LinearLayoutManager(this)

        // on below line we are initializing our adapter class.
        val noteRVAdapter = NoteRVAdapter(this, this, this)

        // on below line we are setting
        // adapter to our recycler view.
        notesRV.adapter = noteRVAdapter

        // on below line we are
        // initializing our view modal.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)

        // on below line we are calling all notes method
        // from our view modal class to observer the changes on list.
        viewModal.allNotes.observe(this, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            // adding a click listener for fab button
            // and opening a new intent to add a new note.
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
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

    override fun onNoteClick(note: Note) {
        // opening a new intent and passing a data to it.
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        // in on note click method we are calling delete
        // method from our view modal to delete our not.
        viewModal.deleteNote(note)
        // displaying a toast message
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }

}