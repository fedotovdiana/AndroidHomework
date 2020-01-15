package com.itis.template


import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id = arguments?.getInt("ID")
        id?.let {
            val note = Data.notes[it]
            val editable = Editable.Factory.getInstance()
            et_title.text = editable.newEditable(note.title)
            et_desc.text = editable.newEditable(note.description)
        }

        btn_add.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.container, ListFragment.newInstance())
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_delete).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    companion object {
        private const val ID = "Title"

        fun newInstance(noteID: Int): NoteFragment = NoteFragment().apply {
            arguments = Bundle().apply {
                putInt(ID, noteID)
            }
        }
    }
}
