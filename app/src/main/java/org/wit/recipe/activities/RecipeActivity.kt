package org.wit.recipe.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import org.wit.recipe.R
import org.wit.recipe.databinding.ActivityRecipeBinding
import org.wit.recipe.main.MainApp
import org.wit.recipe.models.RecipeModel
import timber.log.Timber
import timber.log.Timber.i

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    var recipe = RecipeModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title=title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Recipe Activity started...")

        if (intent.hasExtra("recipe_edit")) {
            recipe = intent.extras?.getParcelable("recipe_edit")!!
            binding.recipeTitle.setText(recipe.title)
            binding.description.setText(recipe.description)
        }

        binding.btnAdd.setOnClickListener() {
            recipe.title = binding.recipeTitle.text.toString()
            recipe.description = binding.description.text.toString()
            if (recipe.title.isNotEmpty()) {
                app.recipes.create(recipe.copy())
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar.make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
//        binding.btnAdd.setOnClickListener() {
//            recipe.title = binding.recipeTitle.text.toString()
//            recipe.description = binding.description.text.toString()
//            if (recipe.title.isNotEmpty()) {
//                //app.recipes.add(recipe.copy())
//                app.recipes.create(recipe.copy())
//                i("add Button Pressed: ${recipe}")
//                for (i in app.recipes.indices) {
//                    i("Recipe[$i]:${this.app.recipes[i]}")
//                }
//            setResult(RESULT_OK)
//            finish()
//        }
//            else {
//                Snackbar.make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
//                    .show()
//            }
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_recipe, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
//class RecipeActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityRecipeBinding
//    var recipe = RecipeModel()
//    var app : MainApp? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityRecipeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        app = application as MainApp
//        i("Recipe Activity started...")
//        binding.btnAdd.setOnClickListener() {
//            recipe.title = binding.recipeTitle.text.toString()
//            recipe.description = binding.description.text.toString()
//            if (recipe.title.isNotEmpty()) {
//                app!!.recipes.add(recipe.copy())
//                i("add Button Pressed: ${recipe}")
//                for (i in app!!.recipes.indices)
//                { i("Recipe[$i]:${this.app!!.recipes[i]}") }
//            }
//            else {
//                Snackbar.make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
//                    .show()
//            }
//        }
//    }
//}
//class RecipeActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityRecipeBinding
//    var recipe = RecipeModel()
//    val recipes = ArrayList<RecipeModel>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityRecipeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        Timber.plant(Timber.DebugTree())
//        i("Recipe Activity started...")
//
//        binding.btnAdd.setOnClickListener() {
//            recipe.title = binding.recipeTitle.text.toString()
//            recipe.description = binding.description.text.toString()
//            if (recipe.title.isNotEmpty()) {
//                recipes.add(recipe.copy())
//                i("add Button Pressed: ${recipe}")
//                for (i in recipes.indices)
//                { i("Recipe[$i]:${this.recipes[i]}") }
//            }
//            else {
//                Snackbar.make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
//                    .show()
//            }
//        }
//    }
//}
//class RecipeActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityRecipeBinding
//    var recipe = RecipeModel()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityRecipeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        Timber.plant(Timber.DebugTree())
//        i("Recipe Activity started...")
//
////        binding.btnAdd.setOnClickListener() {
////            i("add Button Pressed")
////        }
//
//        binding.btnAdd.setOnClickListener() {
//            recipe.title = binding.recipeTitle.text.toString()
//            if (recipe.title.isNotEmpty()) {
//                i("add Button Pressed: $recipe.title")
//            }
//            else {
//                Snackbar
//                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
//                    .show()
//            }
//        }
//    }
//}

