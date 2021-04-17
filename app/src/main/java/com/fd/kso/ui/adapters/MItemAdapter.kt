package com.fd.kso.ui.adapters

import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fd.kso.R
import com.fd.kso.data.model.MyItem
import com.fd.kso.databinding.ItemListBinding
import com.fd.kso.utils.DateUtils
import com.fd.kso.utils.Utils
import com.fd.kso.utils.Utils.spanText

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

   /* override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        val item = items[position]
        //val departureToSpan = holder.itemView.context.getString(R.string.departure,item.departure.place,DateUtils.formatDateString(item.departure.datetime),DateUtils.formatDateTimeString(item.departure.datetime))
        //val arrivalToSpanToSpan = holder.itemView.context.getString(R.string.departure,item.arrival.place,DateUtils.formatDateString(item.arrival.datetime),DateUtils.formatDateTimeString(item.arrival.datetime))

        holder.binding.departure.text = parseDepartureData(holder, item, ::spanText)
                //Utils.spanText(departureToSpan,item.departure.place, DateUtils.formatDateString(item.departure.datetime),DateUtils.formatDateTimeString(item.departure.datetime))
        holder.binding.arrival.text = parseArrivalData(holder,item,::spanText)
                //Utils.spanText(arrivalToSpanToSpan,item.arrival.place, DateUtils.formatDateString(item.arrival.datetime),DateUtils.formatDateTimeString(item.arrival.datetime))
        holder.binding.duration.text = holder.itemView.context.getString(R.string.duration,DateUtils.secondsTominutes(item.details.duration_second))



        //holder.binding.deleteBtn.setOnClickListener { context.onItemClickListener(books[position].isbn13) }
    }*/

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = items[position]
        holder.binding.departure.text = parseDepartureData(holder, item, ::spanText)
        holder.binding.arrival.text = parseArrivalData(holder,item,::spanText)
        holder.binding.duration.text = holder.itemView.context.getString(R.string.duration,DateUtils.secondsTominutes(item.details.duration_second))


        //holder.binding.deleteBtn.setOnClickListener { context.onItemClickListener(books[position].isbn13) }
    }

    fun addItems(items: List<MyItem>) {
        this.items.apply {
            clear()
            addAll(items)
        }

    }

    private fun parseDepartureData(holder: DataViewHolder, item : MyItem, spanText: (sapannableText : String, location : String, date: String, time: String) -> SpannableString) : SpannableString{
        val departureToSpan =
                holder.itemView.context.getString(R.string.departure,
                        item.departure.place,
                        DateUtils.formatDateString(item.departure.datetime),
                        DateUtils.formatDateTimeString(item.departure.datetime))
        return spanText (departureToSpan, item.departure.place, DateUtils.formatDateString(item.departure.datetime),DateUtils.formatDateTimeString(item.departure.datetime))
    }

    private fun parseArrivalData(holder: DataViewHolder, item : MyItem, spanText: (sapannableText : String, location : String, date: String, time: String) -> SpannableString) : SpannableString{
        val departureToSpan =
                holder.itemView.context.getString(R.string.arrival,
                        item.arrival.place,
                        DateUtils.formatDateString(item.arrival.datetime),
                        DateUtils.formatDateTimeString(item.arrival.datetime))

        return spanText (departureToSpan, item.arrival.place, DateUtils.formatDateString(item.arrival.datetime),DateUtils.formatDateTimeString(item.arrival.datetime))
    }



}