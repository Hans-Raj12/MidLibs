package com.example.midlibs_a1l5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.ArrayList
import java.util.regex.Matcher

class StoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        var story = intent.getStringExtra("story")
        val tvStory = findViewById<TextView>(R.id.tvStory)
        tvStory.text = story

        val btnOtherStory = findViewById<Button>(R.id.btnOtherStory)
        btnOtherStory.setOnClickListener{
            val intent = Intent(this,FillData::class.java)
            startActivityForResult(intent, 123)
        }
    }

    ample
    moist


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==123)
        {
            var story = data?.getStringExtra("story")
            val tvStory = findViewById<TextView>(R.id.tvStory)
            tvStory.text = story
        }
    }

}