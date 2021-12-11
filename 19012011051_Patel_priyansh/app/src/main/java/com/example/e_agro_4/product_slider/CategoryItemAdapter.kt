package com.example.e_agro_4.product_slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_agro_4.R

class CategoryItemAdapter(private val context: Context,private val categoryItem:List<CategoryItem>):RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder>() {
    class CategoryItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val itemImage:ImageView
        val itemname: TextView
        val itemprice:TextView
        init{
            itemImage=itemView.findViewById(R.id.item_image)
            itemname=itemView.findViewById(R.id.item_name)
            itemprice=itemView.findViewById(R.id.item_price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cat_row_items,parent,false))
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.itemImage.setImageResource(categoryItem[position].ImageUrl)
        holder.itemname.setText(categoryItem[position].productname)
        holder.itemprice.setText(categoryItem[position].productprice)
    }

    override fun getItemCount(): Int {
        return categoryItem.size
    }
}