package com.apollo.base.view

import android.app.AlertDialog
import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import com.apollo.base.R
import kotlinx.android.synthetic.main.base_dialog.view.*
import kotlinx.android.synthetic.main.base_dialog_edittext.view.*
import kotlinx.android.synthetic.main.base_dialog_edittextautocomplete.view.*


class BaseDialog(val context: Context) {


    private var dialog = AlertDialog.Builder(context).create()

    private var baseView = LayoutInflater.from(context).inflate(R.layout.base_dialog, null)

    private var contentView: View? = null

    private var inputs = mutableListOf<TextView>()

    private var onShow: MutableList<() -> Unit> = mutableListOf()
    private var onDismiss: MutableList<() -> Unit> = mutableListOf()

    init {
        dialog.setOnShowListener { onShow.forEach { it.invoke() } }
        dialog.setOnDismissListener { onDismiss.forEach { it.invoke() } }
    }

    fun title(message: String): BaseDialog {
        dialog.setTitle(message)
        return this
    }

    fun message(message: String): BaseDialog {
        dialog.setMessage(message)
        return this
    }

    fun textViewPrimary(message: String): BaseDialog {
        val textView = LayoutInflater.from(context).inflate(R.layout.base_dialog_textview_primary, null) as TextView
        val layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        baseView.containerContent.addView(textView, layoutParams)

        textView.text = message
        return this
    }

    fun textViewSecondary(message: String): BaseDialog {
        val textView = LayoutInflater.from(context).inflate(R.layout.base_dialog_textview_secondary, null) as TextView
        val layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        baseView.containerContent.addView(textView, layoutParams)

        textView.text = message
        return this
    }

    fun customView(layoutId: Int, onInflate: (View?) -> Unit): BaseDialog {
        contentView = LayoutInflater.from(context).inflate(layoutId, baseView.containerContent, true)
        onInflate(contentView)
        return this
    }

    fun positiveButton(message: String = "OK", callback: (List<Editable>) -> Unit = {}): BaseDialog {
        val button = LayoutInflater.from(context).inflate(R.layout.base_dialog_button, null) as Button
        val layoutParams = LayoutParams(0, WRAP_CONTENT, 1f)
        baseView.containerButtons.addView(button, 0, layoutParams)

        button.setBackgroundColor(ContextCompat.getColor(context, R.color.positive))
        button.text = message
        button.setOnClickListener { callback(inputs.map(TextView::getEditableText)); dialog.dismiss() }

        return this
    }

    fun configurationButton(message: String, callback: (List<Editable>) -> Unit = {}): BaseDialog {
        val button = LayoutInflater.from(context).inflate(R.layout.base_dialog_button, null) as Button
        val layoutParams = LayoutParams(0, WRAP_CONTENT, 1f)
        baseView.containerButtons.addView(button, 0, layoutParams)

        button.setBackgroundColor(ContextCompat.getColor(context, R.color.configuration))
        button.text = message
        button.setOnClickListener { callback(inputs.map(TextView::getEditableText)) }

        return this
    }

    fun negativeButton(message: String = "Cancelar", callback: (List<Editable>) -> Unit = {}): BaseDialog {
        val button = LayoutInflater.from(context).inflate(R.layout.base_dialog_button, null) as Button
        val layoutParams = LayoutParams(0, WRAP_CONTENT, 1f)
        baseView.containerButtons.addView(button, 0, layoutParams)

        button.setBackgroundColor(ContextCompat.getColor(context, R.color.negative))
        button.text = message
        button.setOnClickListener { callback(inputs.map(TextView::getEditableText)); dialog.dismiss() }

        return this
    }

    fun editTextInteger(label: String, value: Int = 0): BaseDialog {
        val layout = LayoutInflater.from(context).inflate(R.layout.base_dialog_edittext, null)
        baseView.containerContent.addView(layout)

        layout.label.text = label
        layout.editText.setText(value.toString())
        layout.editText.inputType = InputType.TYPE_CLASS_NUMBER

        inputs.add(layout.editText)
        return this
    }

    fun editTextDouble(label: String, value: Double = 0.0): BaseDialog {
        val layout = LayoutInflater.from(context).inflate(R.layout.base_dialog_edittext, null)
        baseView.containerContent.addView(layout)

        layout.label.text = label
        layout.editText.setText(value.toString())
        layout.editText.inputType = InputType.TYPE_CLASS_NUMBER
        layout.editText.keyListener = DigitsKeyListener.getInstance("0123456789.,")

        inputs.add(layout.editText)
        return this
    }

    fun editTextString(label: String, value: String = ""): BaseDialog {
        val layout = LayoutInflater.from(context).inflate(R.layout.base_dialog_edittext, null)
        baseView.containerContent.addView(layout)

        layout.label.text = label
        layout.editText.setText(value)
        layout.editText.inputType = InputType.TYPE_CLASS_TEXT

        inputs.add(layout.editText)
        return this
    }

    fun editTextAutoCompleteString(label: String, suggestions: List<String>, value: String = ""): BaseDialog {
        val layout = LayoutInflater.from(context).inflate(R.layout.base_dialog_edittextautocomplete, null)
        baseView.containerContent.addView(layout)

        layout.autoCompleteLabel.text = label
        layout.autoCompleteValue.setText(value)
        layout.autoCompleteValue.inputType = InputType.TYPE_CLASS_TEXT

        val suggestionsAdapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, suggestions)
        layout.autoCompleteValue.setAdapter(suggestionsAdapter)
        onDismiss.add({ layout.autoCompleteValue.setAdapter(null) })

        inputs.add(layout.autoCompleteValue)
        return this
    }


    fun show() {
        dialog.setView(baseView)
        dialog.show()
    }
}