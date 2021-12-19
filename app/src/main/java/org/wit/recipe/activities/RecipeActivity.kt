package org.wit.recipe.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.recipe.R
import org.wit.recipe.databinding.ActivityRecipeBinding
import org.wit.recipe.helpers.showImagePicker
import org.wit.recipe.main.MainApp
import org.wit.recipe.models.RecipeModel
import timber.log.Timber
import timber.log.Timber.i

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    var recipe = RecipeModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        registerImagePickerCallback()
        registerMapCallback()
        super.onCreate(savedInstanceState)
        var edit =false
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title=title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Recipe Activity started...")

        if (intent.hasExtra("recipe_edit")) {
            edit = true
            recipe = intent.extras?.getParcelable("recipe_edit")!!
            binding.recipeTitle.setText(recipe.title)
            binding.description.setText(recipe.description)
            binding.btnAdd.setText(R.string.save_recipe)

            Picasso.get()
                .load(recipe.image)
                .into(binding.recipeImage)
        }

        binding.btnAdd.setOnClickListener() {
            recipe.title = binding.recipeTitle.text.toString()
            recipe.description = binding.description.text.toString()
            if (recipe.title.isEmpty()) {
                Snackbar.make(it,R.string.enter_recipe_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.recipes.update(recipe.copy())
                } else {
                    app.recipes.create(recipe.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
        }

        //add image
        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }

        binding.recipeLocation.setOnClickListener {
            i ("Set Location Pressed")
        }

        binding.recipeLocation.setOnClickListener {
            val launcherIntent = Intent(this, MapActivity::class.java)
            mapIntentLauncher.launch(launcherIntent)
        }

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

    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { i("Map Loaded") }
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            recipe.image = result.data!!.data!!
                            Picasso.get()
                                .load(recipe.image)
                                .into(binding.recipeImage)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

//    private fun registerImagePickerCallback() {
//        imageIntentLauncher =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
//            { result ->
//                when(result.resultCode){
//                    RESULT_OK -> {
//                        if (result.data != null) {
//                            i("Got Result ${result.data!!.data}")
//                        } // end of if
//                    }
//                    RESULT_CANCELED -> { } else -> { }
//                }
//            }
//    }
}

