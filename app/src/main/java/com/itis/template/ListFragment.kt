package com.itis.template

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    lateinit var adapter: NoteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = NoteAdapter(Data.notes) {
            openNote(it.id)
        }
        rv_notes.adapter = adapter
    }

    private fun openNote(noteID: Int) {
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.container, NoteFragment.newInstance(noteID))
            addToBackStack(null)
            commit()
        }
//        Toast.makeText(this, "Clicked ${note.title}", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(): ListFragment = ListFragment()
    }
}
