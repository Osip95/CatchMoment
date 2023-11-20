package com.example.catchmoment.listscreen


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.catchmoment.Moment
import com.example.catchmoment.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MomentsAdapter(private var timeNow:Long) : RecyclerView.Adapter<MomentsAdapter.ViewHolder>() {


    private var momentsData: ArrayList<Moment> = ArrayList()
    private var reversRV: Boolean = false


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvNameMoment)
        val tvDate: TextView = view.findViewById(R.id.tvDataAndTime)
        val imageView: ImageView = view.findViewById(R.id.imageViewItem)
        val indicator: ImageView = view.findViewById(R.id.indicator)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_moment, viewGroup, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val positionItem = if (reversRV) momentsData.size - 1 - position else position
        val stringDateFormat = "dd.MM.yyyy HH:mm:ss"
        val format = SimpleDateFormat(stringDateFormat, Locale.getDefault())
        val date = Date(momentsData[positionItem].time.toLong())
        viewHolder.tvName.text = momentsData[positionItem].name
        viewHolder.tvDate.text = format.format(date).toString()
        viewHolder.imageView.setImageBitmap(momentsData[positionItem].bitmap)

        val idIndicator = getIdIndicator(momentsData[positionItem].time)
        if (idIndicator == -1) viewHolder.indicator.isVisible = false
        else viewHolder.indicator.setImageResource(idIndicator)
    }

    override fun getItemCount() = momentsData.size

    fun setData(momentList: ArrayList<Moment>) {
        momentsData = momentList
        notifyDataSetChanged()
    }

    fun setReversRV(newFirst: Boolean) {
        this.reversRV = newFirst
    }




    private fun getIdIndicator(timeItem: String): Int {
        var index = -1
        val deltaTime = timeNow - timeItem.toLong()
        if (deltaTime >= 120000L) index = R.drawable.indicator_red
        else if (deltaTime >= 60000L) index = R.drawable.indicator_yellow
        return index
    }
}





