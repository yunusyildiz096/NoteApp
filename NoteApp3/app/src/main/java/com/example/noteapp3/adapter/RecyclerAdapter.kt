package com.example.noteapp3.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp3.R
import com.example.noteapp3.model.Notes
import com.example.noteapp3.view.fragments.NotesListFragmentDirections
import kotlinx.android.synthetic.main.notes_row.view.*

class RecyclerAdapter(val notesList : List<Notes>) : RecyclerView.Adapter<RecyclerAdapter.NotesViewHolder>() {
    class NotesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val colors: Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#0d9de3","#ffe48f")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_row,parent,false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemView.titleRow.text = notesList[position].title
        holder.itemView.noteRow.text = notesList[position].note
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))

        holder.itemView.setOnClickListener {
            val nav = NotesListFragmentDirections.actionNotesListFragmentToDeatailsFragment(notesList[position].id!!)
            Navigation.findNavController(it).navigate(nav)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}