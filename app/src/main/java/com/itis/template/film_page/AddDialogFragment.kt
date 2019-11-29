package com.itis.template.film_page


import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment

import com.itis.template.R

class AddDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_dialog, container, false)
    }

    private var dialogListener: DialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        val view = inflater?.inflate(R.layout.fragment_add_dialog, null)
        builder.setView(view)
        builder.setPositiveButton("Ok") { _, _ ->
            dialogListener?.onFinishEditDialog(
                    view?.findViewById<EditText>(R.id.et_title)?.text.toString(),
                    view?.findViewById<EditText>(R.id.et_desc)?.text.toString(),
                    view?.findViewById<EditText>(R.id.et_position)?.text.toString().toIntOrNull()
            )
        }
        builder.setNegativeButton("Cancel") { _, _ ->
            dismiss()
        }
        return builder.create()
    }

    override fun onAttach(context: Context) {
        dialogListener = targetFragment as DialogListener?
        super.onAttach(context)
    }

    interface DialogListener {
        fun onFinishEditDialog(title: String, descr: String, position: Int?)
    }
}
