package com.example.notesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db:NoteDatabaseHelper
    private var noteId: Int= -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= NoteDatabaseHelper(this)

        noteId= intent.getIntExtra("note_id", -1)
        if (noteId== -1) {
            finish()
            return
        }

        val note= db.getNoteByID(noteId)
        binding.updatetitleEditText.setText(note.title)
        binding.updatecontentEditText.setText(note.content)

        binding.updatesaveButton.setOnClickListener {
            val newTitle= binding.updatetitleEditText.text.toString()
            val newContent= binding.updatecontentEditText.text.toString()
            val updatedNote = Note(noteId, newTitle, newContent)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this,"Changes Saved",Toast.LENGTH_SHORT ).show()
        }

    }
}
