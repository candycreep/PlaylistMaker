package com.practicum.playlistmaker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class SearchActivity : AppCompatActivity() {

    companion object {
        const val INPUT_TEXT = "INPUT_TEXT"
        const val INPUT_TEXT_DEFAULT = ""
    }

    private var inputText = INPUT_TEXT_DEFAULT
    private lateinit var inputSearch: EditText
    private val musicArchive = TracksRepository.getTracks()
    private val filteredTracks = ArrayList<Track>()
    private lateinit var trackAdapter: TrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val clearInputButton = findViewById<ImageView>(R.id.iconClearInput)
        inputSearch = findViewById(R.id.inputSearch)
        val buttonBack = findViewById<ImageView>(R.id.button_back_search)
        buttonBack.setOnClickListener {
            finish()
        }

    filteredTracks.addAll(musicArchive)
    trackAdapter = TrackAdapter(filteredTracks)

        val recyclerSearchTrack = findViewById<RecyclerView>(R.id.recyclerSearchTrack)
        recyclerSearchTrack.adapter = trackAdapter

        clearInputButton.setOnClickListener {
            inputSearch.setText(INPUT_TEXT_DEFAULT)
            filter("")
            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(inputSearch.windowToken, 0)
        }

        val inputTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearInputButton.visibility = clearInputButtonVisibility(s)
                inputText = s.toString()

                filter(inputText)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        inputSearch.addTextChangedListener(inputTextWatcher)
    }

    private fun filter(query: String) {
        filteredTracks.clear()
        if (query.isBlank()) {
            filteredTracks.addAll(musicArchive)
        } else {
            musicArchive.filterTo(filteredTracks) { track ->
                track.trackName.contains(query, ignoreCase = true) ||
                track.artistName.contains(query, ignoreCase = true)
            }
        }
        trackAdapter.notifyDataSetChanged()
    }

    private fun clearInputButtonVisibility(s: CharSequence?): Int {
        return if (s.isNullOrEmpty()) View.GONE else View.VISIBLE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(INPUT_TEXT, inputText)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        inputText = savedInstanceState.getString(INPUT_TEXT, INPUT_TEXT_DEFAULT)
        inputSearch.setText(inputText)
        inputSearch.setSelection(inputSearch.text.length)
        filter(inputText)
    }
}
