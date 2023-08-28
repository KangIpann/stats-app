package Match

import Adapter.MatchBerjalanAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import com.example.statsapp.R
import com.google.firebase.firestore.FirebaseFirestore

class MatchBerjalan : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var matchAdapter: MatchBerjalanAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_berjalan)

        //inisialisasi variabel
        db = FirebaseFirestore.getInstance()

        val tvLeagueDate = findViewById<TextView>(R.id.league_date)
        val tvLeagueName = findViewById<TextView>(R.id.league_name)
        val tvMatchDay = findViewById<TextView>(R.id.matchday)
        val tvScoreHome = findViewById<TextView>(R.id.goal_team_home)
        val tvScoreAway = findViewById<TextView>(R.id.goal_team_away)
        val tvTeamHome = findViewById<TextView>(R.id.team_name_home)
        val tvTeamAway = findViewById<TextView>(R.id.team_name_away)
        val tvTimer = findViewById<TextView>(R.id.timerTextView)

        setLeagueDate()
        setLeagueName()
        setHomeTeam()
        setAwayTeam()
        setMatchDuration()
    }

    private fun setLeagueDate(){
        val matchDate = intent.getStringExtra("matchDate")
        println("Tanggal Match Yang Diterima: $matchDate")

        val tvLeagueDate = findViewById<TextView>(R.id.league_date)
        tvLeagueDate.text = matchDate
    }

    private fun setLeagueName(){
        val matchName = intent.getStringExtra("matchName")
        println("Nama Match Yang Diterima: $matchName")

        val tvLeagueName = findViewById<TextView>(R.id.league_name)
        tvLeagueName.text = matchName
    }

    private fun setHomeTeam(){
        val homeTeam = intent.getStringExtra("matchHomeTeam")
        println("Nama Home Team Yang Diterima: $homeTeam")

        val tvTeamHome = findViewById<TextView>(R.id.team_name_home)
        tvTeamHome.text = homeTeam
    }

    private fun setAwayTeam(){
        val awayTeam = intent.getStringExtra("matchAwayTeam")
        println("Nama Away Team Yang Diterima: $awayTeam")

        val tvTeamAway = findViewById<TextView>(R.id.team_name_away)
        tvTeamAway.text = awayTeam
    }

    private fun setMatchDuration() {
        val matchDuration = intent.getStringExtra("matchDuration")
        println("Durasi Match Yang Diterima: $matchDuration")

        val durationParts = matchDuration!!.split(":")
        if (durationParts.size == 3) {
            val hours = durationParts[0].toLong()
            val minutes = durationParts[1].toLong()
            val seconds = durationParts[2].toLong()

            val totalMilliseconds = hours * 3600000 + minutes * 60000 + seconds * 1000

            object : CountDownTimer(totalMilliseconds, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val remainingSeconds = millisUntilFinished / 1000
                    val remainingMinutes = remainingSeconds / 60
                    val remainingHours = remainingMinutes / 60
                    val formattedTime = String.format("%02d:%02d:%02d", remainingHours, remainingMinutes % 60, remainingSeconds % 60)
                    tvTimer.text = formattedTime
                }

                override fun onFinish() {
                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    tvTimer.text = "00:00:00"
                }
            }.start()
        }
    }

}