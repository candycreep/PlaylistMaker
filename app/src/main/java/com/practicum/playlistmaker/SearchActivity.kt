package com.practicum.playlistmaker


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class SearchActivity : AppCompatActivity() {

    companion object {
        const val INPUT_TEXT = "INPUT_TEXT"
        const val INPUT_TEXT_DEFAULT = ""
    }

    private var inputText = INPUT_TEXT_DEFAULT
    private lateinit var inputSearch: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val listTrack: ArrayList<Track> = arrayListOf(
            Track(
                "Enemy",
                "Imagine Dragons",
                "2:53",
                "https://is1-ssl.mzstatic.com/image/thumb/Music211/v4/54/cf/11/54cf11ab-ac8b-587c-d98f-ac491277ae97/00198704145971_Cover.jpg/316x316bb.webp"
            ),
            Track(
                "Ты не верь слезам",
                "Шура",
                "3:13",
                "https://is1-ssl.mzstatic.com/image/thumb/Music49/v4/18/e1/e0/18e1e042-5d85-67b8-912d-b284de27e52f/cover.jpg/296x296bb.webp"
            ),
            Track(
                "Седая ночь",
                "Юрий Шатунов",
                "5:34",
                "https://is1-ssl.mzstatic.com/image/thumb/Music211/v4/b4/f1/63/b4f163f8-a6e2-cf1d-9904-18cb5313d4ef/cover.jpg/296x296bb.webp"
            ),
            Track(
                "Белые розы",
                "Юрий Шатунов",
                "5:40",
                "https://is1-ssl.mzstatic.com/image/thumb/Music211/v4/31/de/dd/31dedd97-ef22-070a-193b-9c978cfded98/cover.jpg/296x296bb.webp"
            ),
            Track(
                "Кошка",
                "Веня Д'ркин",
                "2:19",
                "https://is1-ssl.mzstatic.com/image/thumb/Music221/v4/0f/99/1c/0f991cf2-29e8-dcf7-86c6-1fa5db3b1a18/cover.jpg/296x296bb.webp"
            ),
            Track(
                "Любовь на перше",
                "Веня Д'ркин",
                "2:27",
                "https://is1-ssl.mzstatic.com/image/thumb/Music221/v4/0f/99/1c/0f991cf2-29e8-dcf7-86c6-1fa5db3b1a18/cover.jpg/296x296bb.webpg"
            ),
            Track(
                "Scatman (Ski-Ba-Bop-Ba-Dop-Bop) ",
                "Scatman John",
                "3:33",
                "https://is1-ssl.mzstatic.com/image/thumb/Music112/v4/98/21/7d/98217d06-8681-cede-5e54-4f23856f1707/cover.jpg/296x296bb.webp"
            ),
            Track(
                "Scatman (Ski-Ba-Bop-Ba-Dop-Bop) ",
                "Scatman John",
                "3:33",
                "https://is1-ssl.mzstatic.com/image/thumb/Music112/v4/98/21/7d/98217d06-8681-cede-5e54-4f23856f1707/cover.jpg/296x296bb.webp"
            ),
            Track(
                "Scatman (Ski-Ba-Bop-Ba-Dop-Bop) ",
                "Scatman John",
                "3:33",
                "https://is1-ssl.mzstatic.com/image/thumb/Music112/v4/98/21/7d/98217d06-8681-cede-5e54-4f23856f1707/cover.jpg/296x296bb.webp"
            ),
            Track(
                "Scatman (Ski-Ba-Bop-Ba-Dop-Bop) ",
                "Scatman John",
                "3:33",
                "https://is1-ssl.mzstatic.com/image/thumb/Music112/v4/98/21/7d/98217d06-8681-cede-5e54-4f23856f1707/cover.jpg/296x296bb.webp"
            ),
            Track(
                "Scatman (Ski-Ba-Bop-Ba-Dop-Bop) ",
                "Scatman John",
                "3:33",
                "https://is1-ssl.mzstatic.com/image/thumb/Music112/v4/98/21/7d/98217d06-8681-cede-5e54-4f23856f1707/cover.jpg/296x296bb.webp"
            ),
            Track(
                "Scatman (Ski-Ba-Bop-Ba-Dop-Bop) ",
                "Scatman John",
                "3:33",
                "https://is1-ssl.mzstatic.com/image/thumb/Music112/v4/98/21/7d/98217d06-8681-cede-5e54-4f23856f1707/cover.jpg/296x296bb.webp"
            ),
        )

        val clearInputButton = findViewById<ImageView>(R.id.iconClearInput)
        inputSearch = findViewById(R.id.inputSearch)
        val buttonBack = findViewById<ImageView>(R.id.button_back_search)
        buttonBack.setOnClickListener {
            finish()
        }

        clearInputButton.setOnClickListener {
            inputSearch.setText(INPUT_TEXT_DEFAULT)
            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(inputSearch.windowToken, 0)

        }

        val inputTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearInputButton.visibility = clearInputButtonVisibility(s)
                inputText = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        }
        inputSearch.addTextChangedListener(inputTextWatcher)

        val recyclerSearchTrack = findViewById<RecyclerView>(R.id.recyclerSearchTrack)
        val trackAdapter = TrackAdapter(listTrack)
        recyclerSearchTrack.adapter = trackAdapter
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
    }

}