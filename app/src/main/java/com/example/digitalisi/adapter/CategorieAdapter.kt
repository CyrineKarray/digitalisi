package com.example.digitalisi.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalisi.R
import com.example.digitalisi.model.Categorie
import com.example.digitalisi.process.ProcessActivity
import kotlinx.android.synthetic.main.list_categorie.view.*


class CategorieAdapter : RecyclerView.Adapter<CategorieAdapter.ViewHolder>(){

    private var categories = emptyList<Categorie>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_categorie, parent,false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
        viewHolder.itemView.displayName_txt.text = categories[pos].name
        viewHolder.itemView.description_txt.text = categories[pos].description
        viewHolder.itemView.id = categories[pos].id.toInt()

        viewHolder.itemView.setOnClickListener {
            Log.d("okayy","hhh1")
            val intent = Intent(viewHolder.itemView.context, ProcessActivity::class.java) //win mawjoud w win bech yemchi
            intent.putExtra("categorie_id",categories[pos].id)
            intent.putExtra("categorie_name",categories[pos].name)
            viewHolder.itemView.context.startActivity(intent)
            Log.d("okayy","hhh2")
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun setData(newList: List<Categorie>){
        categories = newList
        notifyDataSetChanged()
    }
}