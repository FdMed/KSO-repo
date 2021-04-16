package com.fd.kso.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fd.kso.data.model.MyItem
import com.fd.kso.databinding.ItemListBinding

class MItemAdapter(private val items: ArrayList<MyItem>, val context : ItemClickInteractionListener) : RecyclerView.Adapter<MItemAdapter.DataViewHolder>() {

    class DataViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
                ItemListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        val item = items[position]

        holder.binding.departure.text = item.departure.place


        //holder.binding.deleteBtn.setOnClickListener { context.onItemClickListener(books[position].isbn13) }
    }

    fun addItems(items: List<MyItem>) {
        this.items.apply {
            clear()
            addAll(items)
        }

    }


}