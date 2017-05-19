package com.qr.toufiqakbar.qrscanner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class TextToSpeechActivity : AppCompatActivity() {
    internal var textToSpeech: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_to_speech)
        var txtResult = findViewById(R.id.txtResult)as TextView
        var btnResult = findViewById(R.id.btnResult)as Button

        textToSpeech = TextToSpeech(this@TextToSpeechActivity, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech!!.language = Locale.UK
            }
        })
        txtResult.text=intent.getStringExtra("TestResult")

        txtResult.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                btnResult.performClick()
            }
        })
        btnResult.setOnClickListener { v: View? ->
            Toast.makeText(applicationContext, txtResult.text, Toast.LENGTH_SHORT).show()
            textToSpeech!!.speak(txtResult.text as String?, TextToSpeech.QUEUE_FLUSH, null) }

    }
    public override fun onPause() {
        if (textToSpeech != null) {
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
        }
        super.onPause()
    }

    override fun onResume() {
        super.onResume()

    }
}
