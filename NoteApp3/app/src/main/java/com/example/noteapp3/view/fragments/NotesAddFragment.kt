package com.example.noteapp3.view.fragments

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.noteapp3.R
import com.example.noteapp3.model.Notes
import com.example.noteapp3.viewmodel.AddNoteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_notes_add.*

class NotesAddFragment : Fragment() {

    val viewModel : AddNoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_add, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveNote.setOnClickListener {
            val title = editTextTitle.text.toString()
            val note = editTextNote.text.toString()
            if (title.isEmpty() || note.isEmpty()){
                Toast.makeText(requireContext(),"Please Enter Note!!",Toast.LENGTH_LONG).show()
            }else{
                val notes = Notes(null,title, note)
                viewModel.addNotes(notes)
                Toast.makeText(requireContext(), "save Notes", Toast.LENGTH_SHORT).show()

                val nav = NotesAddFragmentDirections.actionNotesAddFragmentToNotesListFragment()
                Navigation.findNavController(it).navigate(nav)
            }


        }

    }


}