package com.example.eventtabhome.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.eventtabhome.R
import com.google.android.material.textfield.TextInputLayout


class SearchBar(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private var tilSearch: TextInputLayout
    private var tvLocation: TextView
    private var hint: String? = null
    private var inputHint: String? = null
    private var helperVisible: Boolean? = null
    private var onSelectedOption: ((String) -> Unit)? = null

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.search_bar, this)
        tilSearch = findViewById(R.id.tilSearch)
        tvLocation = findViewById(R.id.tvLocation)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SearchBar,
            0, 0
        ).apply {
            try {
                hint = getString(R.styleable.SearchBar_hint)
                inputHint = getString(R.styleable.SearchBar_input_hint)
                helperVisible = getBoolean(R.styleable.SearchBar_helperVisible, false)
                tvLocation.visibility = if (helperVisible == true) View.VISIBLE else View.GONE
                tilSearch.hint = hint
                tilSearch.editText?.hint = inputHint
            } finally {
                recycle()
            }
        }
    }

    fun setOptions(options: List<String>) {
        val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, options)
        (tilSearch.editText as? AutoCompleteTextView)?.run {
            setAdapter(adapter)
            onItemClickListener =
                AdapterView.OnItemClickListener { parent, _, position, _ ->
                    val item = parent.getItemAtPosition(position)
                    makeQuery(item.toString())
                }
        }
        tilSearch.editText?.setOnEditorActionListener { view, actionId, _ ->
            if (arrayOf(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_CALL).contains(actionId)) {
                makeQuery(view.text.toString())
                true
            } else {
                false
            }
        }
    }

    fun setOnSelectedOption(onSelectedOption: (String) -> Unit) {
        this.onSelectedOption = onSelectedOption
    }

    private fun makeQuery(query: String) {
        val labelPrefix = context.getString(R.string.search_fragment_helper_location)
        "$labelPrefix $query".also { tvLocation.text = it }
        onSelectedOption?.invoke(query)
        tilSearch.editText?.hint = query
        tilSearch.editText?.setText("")
        tilSearch.isExpandedHintEnabled = false
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).run {
            hideSoftInputFromWindow(this@SearchBar.windowToken, 0)
            clearFocus()
        }
    }
}