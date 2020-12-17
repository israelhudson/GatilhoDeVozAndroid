package com.ihudtecnologia.gatilhodevozandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var soundClassifier: SoundClassifier


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.gatilho) as TextView

        soundClassifier = SoundClassifier(this, SoundClassifier.Options()).also {
            it.lifecycleOwner = this
        }

        //textView.text = "teste"
        textView.text = soundClassifier.labelList[1] + " - " + soundClassifier.labelList.indexOf("Ola Ana")

        soundClassifier.probabilities.observe(this) { resultMap ->
            val label = resultMap["Ola Ana"]
            val probability = resultMap["Ola Ana"] ?: 0f
            var percent = (probability * 100).toInt()
            Log.w("ISRAEL", "${(probability * 100).toInt()}")

            textView.text =
                soundClassifier.labelList[1] + " - " + percent

        }
    }
}