package org.wit.recipe.models
import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeModel(var id: Long=0, var title: String = "",
                       var description: String = "", var image: Uri = Uri.EMPTY) : Parcelable //added image var

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable
