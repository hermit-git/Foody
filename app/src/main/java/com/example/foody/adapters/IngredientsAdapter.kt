package com.example.foody.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foody.R
import com.example.foody.data.models.ExtendedIngredient
import com.example.foody.util.Constants.Companion.BASE_IMAGE_URL
import com.example.foody.util.RecipesDiffUtil
import kotlinx.android.synthetic.main.ingredients_row_layout.view.*

class IngredientsAdapter:RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    private var ingredients = emptyList<ExtendedIngredient>()

    class IngredientsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(extendedIngredient: ExtendedIngredient) {
            with(itemView) {
                ingredientImageView.load(BASE_IMAGE_URL + extendedIngredient.image){
                    crossfade(600)
                    error(R.drawable.ic_error_placeholder)
                }
                ingredientName.text = extendedIngredient.name.capitalize()
                ingredientAmount.text = extendedIngredient.amount.toString()
                ingredientUnit.text = extendedIngredient.unit
                ingredientConsistency.text = extendedIngredient.consistency
                ingredientOriginal.text = extendedIngredient.original
            }

        }

//
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder = IngredientsViewHolder(
            LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.ingredients_row_layout,parent,false)
    )

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) = holder.bind(ingredients[position])

    override fun getItemCount(): Int = ingredients.size

    fun setData(newData:List<ExtendedIngredient>){
        val ingredientsDiffUtil = RecipesDiffUtil(ingredients,newData)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientsDiffUtil)
        ingredients = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}