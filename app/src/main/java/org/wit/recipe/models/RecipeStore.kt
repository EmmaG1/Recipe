package org.wit.recipe.models

interface RecipeStore {
    fun findAll(): List<RecipeModel>
    fun create(recipe: RecipeModel)
    fun update(recipe: RecipeModel)
}