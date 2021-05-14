package com.example.digitalisi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalisi.R
import kotlinx.android.synthetic.main.list_process.view.*
import com.example.digitalisi.model.Process
class ProcessAdapter : RecyclerView.Adapter<ProcessAdapter.ViewHolder>(){

        private var processus = emptyList<Process>()

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_process, parent,false))
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
            viewHolder.itemView.name_id.text = processus[pos].name
            viewHolder.itemView.description_id.text = processus[pos].description
            viewHolder.itemView.id = processus[pos].id.toInt()
        }


        override fun getItemCount(): Int {
            return processus.size
        }

        fun setData(newList: List<Process>){
            processus = newList
            notifyDataSetChanged()
    }
}