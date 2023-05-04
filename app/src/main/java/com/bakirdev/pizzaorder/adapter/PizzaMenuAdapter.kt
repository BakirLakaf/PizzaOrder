package com.bakirdev.pizzaorder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bakirdev.pizzaorder.R
import com.bakirdev.pizzaorder.model.PizzaModel
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView

class PizzaMenuAdapter(
    context: Context,
    pizzaMenuList: List<PizzaModel>,
    private val onItemClick: (item: PizzaModel) -> Unit
) : RecyclerView.Adapter<PizzaMenuViewHolder>() {

    private val context: Context
    private val pizzaMenuList: List<PizzaModel>

    init {
        this.context = context
        this.pizzaMenuList = pizzaMenuList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaMenuViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pizza_menu_item, parent, false)
        return PizzaMenuViewHolder(view)
    }

    override fun getItemCount(): Int = pizzaMenuList.size

    override fun onBindViewHolder(holder: PizzaMenuViewHolder, position: Int) {
        val item = pizzaMenuList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}

class PizzaMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val pizzaImage = itemView.findViewById(R.id.pizzaMenuItem_Image) as RoundedImageView
    private val pizzaName = itemView.findViewById(R.id.pizzaMenuItem_Name) as TextView
    private val pizzaPrice = itemView.findViewById(R.id.pizzaMenuItem_Price) as TextView

    fun bind(item: PizzaModel) {
        Glide.with(itemView.context).load(item.image).into(pizzaImage)
        pizzaName.text = item.name
        pizzaPrice.text = itemView.context.getString(R.string.prix, item.prix.toInt())
    }

}
