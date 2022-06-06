package kz.diashima.kmgtest.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kz.diashima.kmgtest.databinding.ItemNoteBinding
import kz.diashima.kmgtest.db.AppDatabase
import kz.diashima.kmgtest.db.entity.Note

class NotesAdapter(private val items: List<Note>,
                   private val listener: OnItemClickListener,
                   private val context: Context,
                   private val formId: Int,
                   private val producerId: Int,
                   private val db: AppDatabase) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemCLick(position)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        with(holder){
            with(items[position]) {
                binding.boreholeText.text = "Скважина: ${this.name}"
                binding.dateText.text = "Дата: ${this.date}"
                binding.oilText.text = "Добыча нефти: ${this.oilExtraction}"
                binding.liquidText.text = "Добыча жидкости: ${this.liquidExtraction}"
                binding.formText.text = "Форма: $formId"
                if (producerId == 0) {
                    when (formId) {
                        1 -> {
                            binding.categoryText.visibility = View.GONE
                            binding.ndinText.visibility = View.GONE
                            binding.gasText.visibility = View.GONE
                            binding.waterText.visibility = View.GONE
                        }
                        2 -> {
                            binding.waterText.text = "Обводненность: ${this.waterCut}"
                            binding.categoryText.visibility = View.GONE
                            binding.ndinText.visibility = View.GONE
                            binding.gasText.visibility = View.GONE
                        }
                        3 -> {
                            binding.gasText.text = "Дебит газа: ${this.gasDebit}"
                            binding.categoryText.visibility = View.GONE
                            binding.ndinText.visibility = View.GONE
                            binding.waterText.visibility = View.GONE
                        }
                    }
                } else if (producerId == 1) {
                    binding.ndinText.text = "Ндин: ${this.ndin}"
                    when (formId) {
                        1 -> {
                            binding.categoryText.text = "Категория: 1"
                            binding.gasText.visibility = View.GONE
                            binding.waterText.visibility = View.GONE
                        }
                        2 -> {
                            binding.categoryText.text = "Категория: 1"
                            binding.waterText.text = "Обводненность: ${this.waterCut}"
                            binding.gasText.visibility = View.GONE
                        }
                        3 -> {
                            binding.categoryText.text = "Категория: 2"
                            binding.gasText.text = "Дебит газа: ${this.gasDebit}"
                            binding.waterText.text = "Обводненность: ${this.waterCut}"
                        }
                    }
                }

                binding.dots.setOnClickListener {
                    MaterialAlertDialogBuilder(context)
                        .setMessage("Выбор действия")
                        .setPositiveButton("Редактировать") { dialog, _ ->
                            dialog.dismiss()
                            edit(this, context)
                        }
                        .setNegativeButton("Удалить") { dialog, _ ->
                            db.producerDao().deleteNote(this)
                            dialog.dismiss()
                        }
                        .show()
                }
            }
        }
    }

    override fun getItemCount() = items.size

    interface  OnItemClickListener {
        fun onItemCLick(position: Int)
    }

    private fun edit(note: Note, context: Context) {
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(context)
        builder.setTitle("Редактирование записи")

        val input = EditText(context)
        input.hint = "Добыча нефти"
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            note.oilExtraction = input.text.toString().toInt()
            db.producerDao().updateNote(note)
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
}