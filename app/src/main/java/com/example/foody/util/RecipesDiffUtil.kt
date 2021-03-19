package com.example.foody.util

import androidx.recyclerview.widget.DiffUtil
import com.example.foody.data.models.Result

class RecipesDiffUtil<T>(private val oldList:List<T>,private val newList:List<T>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] == newList[newItemPosition]
}