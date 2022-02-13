package com.example.noteapp3.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp3.R
import com.example.noteapp3.adapter.RecyclerAdapter
import com.example.noteapp3.viewmodel.AddNoteViewModel
import kotlinx.android.synthetic.main.fragment_notes_list.*


class NotesListFragment : Fragment() {

    val viewModel : AddNoteViewModel by viewModels()
    var recyclerAdapter : RecyclerAdapter? = null

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectNotes = recyclerAdapter!!.notesList[layoutPosition]
            viewModel.deleteNotes(selectNotes)

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goToAddActivity.setOnClickListener {
            findNavController().navigate(NotesListFragmentDirections.actionNotesListFragmentToNotesAddFragment())
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            recyclerAdapter = RecyclerAdapter(it)
            recyclerView.adapter = recyclerAdapter
            ItemTouchHelper(swipeCallBack).attachToRecyclerView(recyclerView)
        })
    }
}