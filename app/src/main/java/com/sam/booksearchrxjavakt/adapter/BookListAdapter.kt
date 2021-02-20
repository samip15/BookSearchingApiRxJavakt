package com.sam.booksearchrxjavakt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sam.booksearchrxjavakt.R
import com.sam.booksearchrxjavakt.network.VolumeInfo
import java.lang.Exception

class BookListAdapter: RecyclerView.Adapter<BookListAdapter.MyViewHolder>() {
    var bookListData = ArrayList<VolumeInfo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListAdapter.MyViewHolder {
      val inflator = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row,parent,false)
        return MyViewHolder(inflator)
    }

    override fun onBindViewHolder(holder: BookListAdapter.MyViewHolder, position: Int) {
       holder.bind(bookListData[position])
    }

    override fun getItemCount(): Int {
       return bookListData.size
    }
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txTitle1 = view.findViewById<TextView>(R.id.tvTitle)
        val txPublisher1 = view.findViewById<TextView>(R.id.tvPublisher)
        val txDescription1 = view.findViewById<TextView>(R.id.tvDescription)
        val imageView1 = view.findViewById<ImageView>(R.id.thumbImageView)
        fun bind(data: VolumeInfo){
            txTitle1.text = data.volumeInfo.title
            txPublisher1.text = data.volumeInfo.publisher
            txDescription1.text  = data.volumeInfo.description
            try {
                val url = data?.volumeInfo.imageLinks.smallThumbnail
                Glide.with(imageView1)
                        .load(url)
                        .circleCrop()
                        .into(imageView1)
            }catch (e: Exception){
                e.printStackTrace()
                
            }
        }
    }
}