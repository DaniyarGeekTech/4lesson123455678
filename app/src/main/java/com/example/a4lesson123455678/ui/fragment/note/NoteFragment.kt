package com.example.a4lesson123455678.ui.fragment.note

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.a4lesson123455678.R
import com.example.a4lesson123455678.base.BaseFragment
import com.example.a4lesson123455678.databinding.FragmentNoteBinding
import com.example.a4lesson123455678.model.NoteModel
import com.example.a4lesson123455678.ui.utils.App

class NoteFragment :
    BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate),NoteAdapter.NoteClicInterfase {
    private lateinit var adapter: NoteAdapter
    override fun setupUI() {
        adapter = NoteAdapter(this)
        binding.rvNote.adapter = adapter
        adapter.addNote(App.db.getDao().getAllNote()as ArrayList<NoteModel>)
    }
    override fun setupObserver() {
        super.setupObserver()
        deleteNote()
        binding.btnAdd.setOnClickListener {
            controller.navigate(R.id.addNoteFragment)
        }
    }

    private fun deleteNote() {
        val simpleCallback = object :
            ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper
                    .RIGHT or ItemTouchHelper.LEFT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Удалить заметку?")
                    .setNegativeButton("Нет") { _: DialogInterface?, _: Int ->
                        adapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .setPositiveButton("Да") { _: DialogInterface?, _: Int ->
                        adapter.deleteNote(viewHolder.adapterPosition)
                        adapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvNote)
    }

    override fun noteClik(model: NoteModel) {
        val bundle = Bundle()
        bundle.putString("title",model.title)
        bundle.putString("desc",model.description)
        model.id?.let { bundle.putInt("id",it) }
        controller.navigate(R.id.addNoteFragment,bundle)
    }

}
