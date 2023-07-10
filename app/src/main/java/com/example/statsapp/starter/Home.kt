package com.example.statsapp.starter

import Match.MatchList
import Tim.TeamList
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.statsapp.R

class Home : AppCompatActivity(), View.OnClickListener {
    private lateinit var ib_teamlist: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val ib_matchlist = findViewById<ImageButton>(R.id.ib_match)
        ib_matchlist.setOnClickListener {
            val intent = Intent(this, MatchList::class.java)
            startActivity(intent)
        }
        ib_teamlist = findViewById(R.id.ib_teamlist)
        ib_teamlist.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ib_teamlist -> {
                val intent = Intent(this, TeamList::class.java)
                startActivity(intent)
            }
        }
    }
}