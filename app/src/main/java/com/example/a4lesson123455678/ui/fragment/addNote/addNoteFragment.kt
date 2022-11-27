package com.example.a4lesson123455678.ui.fragment.addNote

import com.example.a4lesson123455678.base.BaseFragment
import com.example.a4lesson123455678.databinding.FragmentAddNoteBinding
import com.example.a4lesson123455678.model.NoteModel
import com.example.a4lesson123455678.ui.utils.App

class AddNoteFragment : BaseFragment<FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate) {
    override fun setupUI() {
        if (arguments != null) {
            val title = arguments?.getString("title")
            val desc = arguments?.getString("desc")
            val id = arguments?.getInt("id")

            binding.etTitle.setText(title.toString())
            binding.etDes.setText(desc.toString())
            binding.btnSaveNote.setOnClickListener {
                App.db.getDao().updateNote(
                    NoteModel(
                        id, binding.etTitle.text.toString(), binding.etDes.text.toString()
                    )
                )

                controller.navigateUp()
            }
        } else {
            binding.btnSaveNote.setOnClickListener {
                val title = binding.etTitle.text.toString()
                val description = binding.etDes.text.toString()
                App.db.getDao()
                    .addNote(NoteModel(title = title, description = description))
                controller.navigateUp()

            }
        }
    }
}