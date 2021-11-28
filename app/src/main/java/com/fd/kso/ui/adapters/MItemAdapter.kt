package com.fd.kso.ui.adapters


import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fd.kso.R
import com.fd.kso.data.model.MyItem
import com.fd.kso.databinding.ItemListBinding
import com.fd.kso.utils.Utils.spanText

class MItemAdapter(private val items: ArrayList<MyItem>) : RecyclerView.Adapter<MItemAdapter.DataViewHolder>() {

    var onItemClick: ((MyItem) -> Unit)? = null

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
        holder.binding.departure.text = parseDepartureData(holder, item, ::spanText)
        holder.binding.arrival.text = parseArrivalData(holder, item, ::spanText)

        if (item.details.duration_second != null) {
            holder.binding.duration.visibility = View.VISIBLE
            holder.binding.errorTime.visibility = View.GONE
            holder.binding.duration.text = holder.itemView.context.getString(R.string.duration, item.details.getFormattedDuration())
        } else {
            holder.binding.duration.visibility = View.GONE
            holder.binding.errorTime.visibility = View.VISIBLE
        }

        holder.binding.root.setOnClickListener { onItemClick?.invoke(item) }
    }

    fun addItems(items: List<MyItem>) {
        this.items.apply {
            clear()
            addAll(items)
        }
    }

    private fun parseDepartureData(
            holder: DataViewHolder,
            item: MyItem,
            spanText: (sapannableText: String,
                       location: String,
                       date: String,
                       time: String
            ) -> SpannableString,
    ): SpannableString {

        val departureToSpan = holder.itemView.context.getString(R.string.departure,
                item.departure.place,
                item.departure.getFormattedDate(),
                item.departure.getFormattedTime())
        return spanText(departureToSpan, item.departure.place, item.departure.getFormattedDate(), item.departure.getFormattedTime())
    }

    private fun parseArrivalData(
            holder: DataViewHolder,
            item: MyItem,
            spanText: (sapannableText: String,
                       location: String,
                       date: String,
                       time: String
            ) -> SpannableString,
    ): SpannableString {

        val departureToSpan = holder.itemView.context.getString(R.string.arrival,
                item.arrival.place,
                item.arrival.getFormattedDate(),
                item.arrival.getFormattedTime())
        return spanText(departureToSpan,
            item.arrival.place,
            item.arrival.getFormattedDate(),
            item.arrival.getFormattedTime()
        )
    }

}