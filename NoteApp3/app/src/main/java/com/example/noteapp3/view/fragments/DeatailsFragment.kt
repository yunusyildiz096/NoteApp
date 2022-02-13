package com.example.noteapp3.view.fragments

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp3.R
import com.example.noteapp3.model.Notes
import com.example.noteapp3.viewmodel.AddNoteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_deatails.*

class DeatailsFragment : Fragment() {

    private val viewModel : AddNoteViewModel by viewModels()
    private var noteId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deatails, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
           noteId =  DeatailsFragmentArgs.fromBundle(it).data
        }

        updateNote.setOnClickListener {
            val title = editTextTitle.text.toString()
            val note = editTextNote.text.toString()
            if (title.isEmpty() || note.isEmpty()){
                Toast.makeText(requireContext(),"Please Enter Note!!",Toast.LENGTH_LONG).show()
            }else{
                val notes = Notes(noteId,title,note)
                viewModel.updateNotes(notes)
            }

            Toast.makeText(requireContext(), "update Note", Toast.LENGTH_SHORT).show()
            val nav = DeatailsFragmentDirections.actionDeatailsFragmentToNotesListFragment()
            Navigation.findNavController(it).navigate(nav)
        }

        viewModel.getNote(noteId)
        viewModel.noteLiveData.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                editTextTitle.setText(note.title)
                editTextNote.setText(note.note)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.dlt_id){
            val bottomSheet : BottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.bottom_sheet_delete)
            bottomSheet.findViewById<TextView>(R.id.yesButton)!!.setOnClickListener {
                val title = editTextTitle.text.toString()
                val note = editTextNote.text.toString()
                val notes = Notes(noteId,title,note)
                viewModel.deleteNotes(notes)
                findNavController().navigate(DeatailsFragmentDirections.actionDeatailsFragmentToNotesListFragment())
                Toast.makeText(requireContext(),"Delete Note!!",Toast.LENGTH_LONG).show()
                bottomSheet.dismiss()
            }
            bottomSheet.findViewById<TextView>(R.id.noButton)!!.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}