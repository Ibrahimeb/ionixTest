package com.example.ionixtest.presentation.features.home.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ionixtest.domain.models.MovieModel

class HomeAdapter(private val itemList: List<MovieModel>, private val callback: (movieId: String) -> Unit) :
    RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.render(itemList[position], callback)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}