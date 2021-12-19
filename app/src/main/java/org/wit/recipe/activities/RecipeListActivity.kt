package org.wit.recipe.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wit.recipe.databinding.ActivityRecipeListBinding
import org.wit.recipe.databinding.CardRecipeBinding
import org.wit.recipe.main.MainApp
import org.wit.recipe.models.RecipeModel
import android.view.Menu
import android.view.MenuItem
import org.wit.recipe.R

class RecipeListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityRecipeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title =title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = RecipeAdapter(app.recipes)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, RecipeActivity::class.java)
                startActivityForResult(launcherIntent,0)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

class RecipeAdapter constructor(private var recipes: List<RecipeModel>) :
    RecyclerView.Adapter<RecipeAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRecipeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val recipe = recipes[holder.adapterPosition]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size

    class MainHolder(private val binding : CardRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: RecipeModel) {
            binding.recipeTitle.text = recipe.title
            binding.description.text = recipe.description
        }
    }
}
//package org.wit.recipe.activities
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import org.wit.recipe.R
//import org.wit.recipe.main.MainApp
//
//class RecipeListActivity : AppCompatActivity() {
//
//    lateinit var app: MainApp
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_recipe_list)
//        app = application as MainApp
//    }
//}