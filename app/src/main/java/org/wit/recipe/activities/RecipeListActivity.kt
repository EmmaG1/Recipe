package org.wit.recipe.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
import org.wit.recipe.databinding.ActivityRecipeListBinding
//import org.wit.recipe.databinding.CardRecipeBinding
import org.wit.recipe.main.MainApp
//import org.wit.recipe.models.RecipeModel
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.wit.recipe.R
import org.wit.recipe.adapters.RecipeAdapter
import org.wit.recipe.adapters.RecipeListener
import org.wit.recipe.models.RecipeModel

class RecipeListActivity : AppCompatActivity(), RecipeListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityRecipeListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent> //new

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title =title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
       // binding.recyclerView.adapter = RecipeAdapter(app.recipes)
        //binding.recyclerView.adapter=RecipeAdapter(app.recipes) //might be missing a .finalAll() here
        //binding.recyclerView.adapter = RecipeAdapter(app.recipes.findAll())
        binding.recyclerView.adapter = RecipeAdapter(app.recipes.findAll(),this)
        //binding.recyclerView.
        registerRefreshCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, RecipeActivity::class.java)
                refreshIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRecipeClick(recipe: RecipeModel) {
        val launcherIntent = Intent(this, RecipeActivity::class.java)
        launcherIntent.putExtra("recipe_edit", recipe)
        refreshIntentLauncher.launch(launcherIntent)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        binding.recyclerView.adapter?.notifyDataSetChanged()
//        super.onActivityResult(requestCode, resultCode, data)
//    }

    //new
    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { binding.recyclerView.adapter?.notifyDataSetChanged() }
    }

//    override fun onRecipeClick(recipe: RecipeModel) {
//        val launcherIntent = Intent(this, RecipeActivity::class.java)
//        startActivityForResult(launcherIntent,0)
//    }
}

