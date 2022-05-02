package com.example.catknowledgebase.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.example.catknowledgebase.databinding.ItemCatBinding
import com.example.catknowledgebase.model.FactItem

class FactsAdapter(private val facts:ArrayList<FactItem>):RecyclerView.Adapter<FactsAdapter.ViewHolder>() {

    var onItemLongPress: ((String) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCatBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            bind(factItem = facts[position])
        }
    }

    override fun getItemCount(): Int =facts.size
    fun addFact(catFact: List<FactItem>?) {
        catFact?.let {
            if(catFact.size==1){
                facts.add(0,catFact[0])
                notifyItemInserted(0)
            }else{
                facts.addAll(it)
                notifyDataSetChanged()
            }

        }
    }

   inner class ViewHolder(private val view: ItemCatBinding) : RecyclerView.ViewHolder(view.root) {

        init {
            view.card.setOnLongClickListener {
                onItemLongPress?.invoke(facts[adapterPosition]._id)
                facts.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                true
            }
        }

        fun bind( factItem:FactItem){
            view.tvFact.text=factItem.text
            view.tvDate.text=factItem.createdAt
            Glide.with(view.ivCat.context).load(factItem.catUrl).signature(ObjectKey(System.currentTimeMillis())).centerCrop().dontAnimate().into(view.ivCat)
        }

    }

}