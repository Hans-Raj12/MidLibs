package com.example.midlibs_a1l5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.io.InputStream
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class FillData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_data)

        var files = mutableListOf<String>("madlib0","madlib1","madlib2","madlib3","madlib4")
        var random:Random = Random()
        var num = random.nextInt(5)
        
        val ins: InputStream = resources.openRawResource(
            resources.getIdentifier(
                ""+files[num],
                "raw", packageName
            )
        )

        val scanner:Scanner = Scanner(ins)

        var text = ""
        while (scanner.hasNextLine())
        {
            var line = scanner.nextLine()
            text+=line
        }
        scanner.close()


//        var pattern = Pattern.compile("<(.+?)>", Pattern.DOTALL);
//        var matcher = pattern.matcher(text);
//        matcher.find();

        var pattern = Pattern.compile("<(.+?)>",Pattern.DOTALL)
        var matcher = pattern.matcher(text)
        matcher.find()



        var array = getTagValues(text)

        val tvWordsLeft = findViewById<TextView>(R.id.tvWordsLeft)
        val btnOk = findViewById<Button>(R.id.btnOk)
        val tvPlaceHolderHint = findViewById<TextView>(R.id.tvPlaceHolderHint)
        val etWords = findViewById<EditText>(R.id.etWords)
        var placeholders = mutableListOf<String>()



        var index = 0
        var wordCount = array?.size

        etWords.hint = array?.get(0)
        tvWordsLeft.text = array?.size.toString()+" word(s) Left"
        tvPlaceHolderHint.text = "please type a/an "+array?.get(index)
        btnOk.setOnClickListener{
            placeholders.add(etWords.text.toString())

            val ntext:String = etWords.text.toString()
            text = text.replace("<"+array!!.get(index)+">", ntext)

            wordCount = wordCount?.minus(1)
            index = index.inc()
            if(index!=array?.size){
                etWords.text = null
                tvPlaceHolderHint.text = "please type a/an "+array?.get(index)
                tvWordsLeft.text = ""+wordCount+" word(s) Left"
                etWords.hint = array?.get(index)
            }
            else{
                btnOk.isEnabled = false
                val intent = Intent(this,StoryActivity::class.java)
                intent.putExtra("story",text)
                startActivity(intent)
            }
        }

    }
    private val TAG_REGEX = Pattern.compile("<(.+?)>", Pattern.DOTALL)

    private fun getTagValues(str: String): List<String>? {
        val tagValues: MutableList<String> = ArrayList()
        val matcher: Matcher = TAG_REGEX.matcher(str)
        while (matcher.find()) {
            tagValues.add(matcher.group(1))
        }
        return tagValues
    }
}



