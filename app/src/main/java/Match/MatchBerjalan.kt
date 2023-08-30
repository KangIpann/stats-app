package Match

import Adapter.MatchBerjalanAdapter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.statsapp.R
import com.google.firebase.firestore.FirebaseFirestore
import java.time.Duration

class MatchBerjalan : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var teamHomeDocId : String
    private lateinit var teamAwayDocId : String
    private lateinit var teamHomeGoalKeeper : String
    private lateinit var teamHomeCentreBack : String
    private lateinit var teamHomeLeftBack : String
    private lateinit var teamHomeRightBack : String
    private lateinit var teamHomeDefensiveMidfielder : String
    private lateinit var teamHomeCentralMidfielder : String
    private lateinit var teamHomeAttackingMidfielder : String
    private lateinit var teamHomeLeftWinger : String
    private lateinit var teamHomeRightWinger : String
    private lateinit var teamHomeCentreForward : String
    private lateinit var teamHomeSecondStriker : String
    private lateinit var teamAwayGoalKeeper : String
    private lateinit var teamAwayCentreBack : String
    private lateinit var teamAwayLeftBack : String
    private lateinit var teamAwayRightBack : String
    private lateinit var teamAwayDefensiveMidfielder : String
    private lateinit var teamAwayCentralMidfielder : String
    private lateinit var teamAwayAttackingMidfielder : String
    private lateinit var teamAwayLeftWinger : String
    private lateinit var teamAwayRightWinger : String
    private lateinit var teamAwayCentreForward : String
    private lateinit var teamAwaySecondStriker : String

    //variabel global untuk team Home
    private lateinit var matchDuration: String
    private lateinit var matchName : String
    private lateinit var matchDate : String
    private lateinit var leagueName : String
    private lateinit var homeTeam : String
    private lateinit var awayTeam : String
    private lateinit var tvLeagueName : TextView
    private lateinit var tvLeagueDate : TextView
    private lateinit var tvTeamHome : TextView
    private lateinit var tvTeamAway : TextView
    private lateinit var tvTimer: TextView
    private lateinit var tvHomeGoalKeeperName : TextView
    private lateinit var tvHomeCentreBackName : TextView
    private lateinit var tvHomeLeftBackName : TextView
    private lateinit var tvHomeRightBackName : TextView
    private lateinit var tvHomeDefensiveMidfielderName : TextView
    private lateinit var tvHomeCentralMidfielderName : TextView
    private lateinit var tvHomeAttackingMidfielderName : TextView
    private lateinit var tvHomeLeftWingerName : TextView
    private lateinit var tvHomeRightWingerName : TextView
    private lateinit var tvHomeCentreForwardName : TextView
    private lateinit var tvHomeSecondStrikerName : TextView

    //variabel global untuk team Away
    private lateinit var tvAwayGoalKeeperName : TextView
    private lateinit var tvAwayCentreBackName : TextView
    private lateinit var tvAwayLeftBackName : TextView
    private lateinit var tvAwayRightBackName : TextView
    private lateinit var tvAwayDefensiveMidfielderName : TextView
    private lateinit var tvAwayCentralMidfielderName : TextView
    private lateinit var tvAwayAttackingMidfielderName : TextView
    private lateinit var tvAwayLeftWingerName : TextView
    private lateinit var tvAwayRightWingerName : TextView
    private lateinit var tvAwayCentreForwardName : TextView
    private lateinit var tvAwaySecondStrikerName : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_berjalan)

        //inisialisasi variabel
        db = FirebaseFirestore.getInstance()

        setLeagueDate()
        setLeagueName()
        setHomeTeam()
        setAwayTeam()
        setMatchDuration()
        getHomeTeamDocumentId()
        getAwayTeamDocumentId()
    }

    private fun setLeagueDate(){
        matchDate = intent.getStringExtra("matchDate").toString()
        println("Tanggal Match Yang Diterima: $matchDate")

        tvLeagueDate = findViewById<TextView>(R.id.league_date)
        tvLeagueDate.text = matchDate
    }

    @SuppressLint("SetTextI18n")
    private fun setLeagueName(){
        matchName = intent.getStringExtra("matchName").toString()
        println("Nama Match Yang Diterima: $matchName")

        tvLeagueName = findViewById<TextView>(R.id.league_name)
        tvLeagueName.text = matchName
    }

    @SuppressLint("SetTextI18n")
    private fun setHomeTeam(){
        homeTeam = intent.getStringExtra("matchHomeTeam").toString()
        println("Nama Home Team Yang Diterima: $homeTeam")

        tvTeamHome = findViewById<TextView>(R.id.team_name_home)
        tvTeamHome.text = homeTeam
    }

    @SuppressLint("SetTextI18n")
    private fun setAwayTeam(){
        awayTeam = intent.getStringExtra("matchAwayTeam").toString()
        Log.d("Away Team: ", awayTeam.toString())

        tvTeamAway = findViewById<TextView>(R.id.team_name_away)
        tvTeamAway.text = awayTeam
    }

    @SuppressLint("SetTextI18n")
    private fun setMatchDuration() {
        matchDuration = intent.getStringExtra("matchDuration").toString()
        Log.d("Durasi Match: ", matchDuration.toString())

        val durationParts = matchDuration.split(":")
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

                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    tvTimer = findViewById<TextView>(R.id.timerTextView)
                    tvTimer.text = "00:00:00"
                }
            }.start()
        }else{
            tvTimer = findViewById<TextView>(R.id.timerTextView)
            tvTimer.text = "Time Not Found"
            Log.d("Error", "Error getting times")
        }
    }

    private fun getHomeTeamGoalKeeper(teamHomeDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamHomeDocId)
            .whereEqualTo("role_pemain", "Goal Keeper")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamHomeGoalKeeper = document.getString("nama_pemain").toString()
                    Log.d("Home Goal Keeper: ", teamHomeGoalKeeper)

                    tvHomeGoalKeeperName = findViewById<TextView>(R.id.tv_goal_player1_name_home)
                    tvHomeGoalKeeperName.text = teamHomeGoalKeeper
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getHomeTeamCentreBack(teamHomeDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamHomeDocId)
            .whereEqualTo("role_pemain", "Centre Back")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamHomeCentreBack = document.getString("nama_pemain").toString()
                    Log.d("Home Centre Back: ", teamHomeCentreBack)

                    tvHomeCentreBackName = findViewById<TextView>(R.id.tv_goal_player2_name_home)
                    tvHomeCentreBackName.text = teamHomeCentreBack
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getHomeTeamLeftBack(teamHomeDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamHomeDocId)
            .whereEqualTo("role_pemain", "Left Back")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamHomeLeftBack = document.getString("nama_pemain").toString()
                    Log.d("Home Left Back: ", teamHomeLeftBack)

                    tvHomeLeftBackName = findViewById<TextView>(R.id.tv_goal_player3_name_home)
                    tvHomeLeftBackName.text = teamHomeLeftBack
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getHomeTeamRightBack(teamHomeDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamHomeDocId)
            .whereEqualTo("role_pemain", "Right Back")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamHomeRightBack = document.getString("nama_pemain").toString()
                    Log.d("Home Right Back: ", teamHomeRightBack)

                    tvHomeRightBackName = findViewById<TextView>(R.id.tv_goal_player4_name)
                    tvHomeRightBackName.text = teamHomeRightBack
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getHomeTeamDefensiveMidfielder(teamHomeDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamHomeDocId)
            .whereEqualTo("role_pemain", "Defensive Midfielder")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamHomeDefensiveMidfielder = document.getString("nama_pemain").toString()
                    Log.d("Home Defensive Midfielder: ", teamHomeDefensiveMidfielder)

                    tvHomeDefensiveMidfielderName = findViewById<TextView>(R.id.tv_goal_player5_name_home)
                    tvHomeDefensiveMidfielderName.text = teamHomeDefensiveMidfielder
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getHomeTeamCentralMidfielder(teamHomeDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamHomeDocId)
            .whereEqualTo("role_pemain", "Central Midfielder")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamHomeCentralMidfielder = document.getString("nama_pemain").toString()
                    Log.d("Home Central Midfielder: ", teamHomeCentralMidfielder)

                    tvHomeCentralMidfielderName = findViewById<TextView>(R.id.tv_goal_player6_name_home)
                    tvHomeCentralMidfielderName.text = teamHomeCentralMidfielder
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getHomeTeamAttackingMidfielder(teamHomeDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamHomeDocId)
            .whereEqualTo("role_pemain", "Attacking Midfielder")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamHomeAttackingMidfielder = document.getString("nama_pemain").toString()
                    Log.d("Home Attacking Midfielder: ", teamHomeAttackingMidfielder)

                    tvHomeAttackingMidfielderName = findViewById<TextView>(R.id.tv_goal_player7_name_home)
                    tvHomeAttackingMidfielderName.text = teamHomeAttackingMidfielder
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getHomeTeamLeftWinger(teamHomeDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamHomeDocId)
            .whereEqualTo("role_pemain", "Left Winger")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamHomeLeftWinger = document.getString("nama_pemain").toString()
                    Log.d("Home Left Winger: ", teamHomeLeftWinger)

                    tvHomeLeftWingerName = findViewById<TextView>(R.id.tv_goal_player8_name_home)
                    tvHomeLeftWingerName.text = teamHomeLeftWinger
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getHomeTeamRightWinger(teamHomeDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamHomeDocId)
            .whereEqualTo("role_pemain", "Right Winger")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamHomeRightWinger = document.getString("nama_pemain").toString()
                    Log.d("Home Right Winger: ", teamHomeRightWinger)

                    tvHomeRightWingerName = findViewById<TextView>(R.id.tv_goal_player9_name_home)
                    tvHomeRightWingerName.text = teamHomeRightWinger
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getHomeTeamCentreForward(teamHomeDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamHomeDocId)
            .whereEqualTo("role_pemain", "Centre Forward")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamHomeCentreForward = document.getString("nama_pemain").toString()
                    Log.d("Home Centre Forward: ", teamHomeCentreForward)

                    tvHomeCentreForwardName = findViewById<TextView>(R.id.tv_goal_player10_name_home)
                    tvHomeCentreForwardName.text = teamHomeCentreForward
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getHomeSecondStriker(teamHomeDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamHomeDocId)
            .whereEqualTo("role_pemain", "Second Striker")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamHomeSecondStriker = document.getString("nama_pemain").toString()
                    Log.d("Home Second Striker: ", teamHomeSecondStriker)

                    tvHomeSecondStrikerName = findViewById<TextView>(R.id.tv_goal_player11_name_home)
                    tvHomeSecondStrikerName.text = teamHomeSecondStriker
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getHomeTeamDocumentId(){
        val team = intent.getStringExtra("matchHomeTeam")
        Log.d("Nama Team Home: ", team.toString())

        db.collection("team")
            .whereEqualTo("nama_team", team)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val documentId = document.id
                    Log.d("Document Id: ", documentId.toString())
                    teamHomeDocId = documentId

                    getHomeTeamGoalKeeper(teamHomeDocId)
                    getHomeTeamCentreBack(teamHomeDocId)
                    getHomeTeamLeftBack(teamHomeDocId)
                    getHomeTeamRightBack(teamHomeDocId)
                    getHomeTeamDefensiveMidfielder(teamHomeDocId)
                    getHomeTeamCentralMidfielder(teamHomeDocId)
                    getHomeTeamAttackingMidfielder(teamHomeDocId)
                    getHomeTeamLeftWinger(teamHomeDocId)
                    getHomeTeamRightWinger(teamHomeDocId)
                    getHomeTeamCentreForward(teamHomeDocId)
                    getHomeSecondStriker(teamHomeDocId)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getAwayTeamDocumentId(){
        val team = intent.getStringExtra("matchAwayTeam")
        Log.d("Nama Team Away: ", team.toString())

        //mencari documentId dari teaqm yang dipilih
        db.collection("team")
            .whereEqualTo("nama_team", team)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val documentId = document.id
                    Log.d("Document Id: ", documentId.toString())
                    teamAwayDocId = documentId
                    getAwayTeamGoalKeeper(teamAwayDocId)
                    getAwayTeamCentreBack(teamAwayDocId)
                    getAwayTeamLeftBack(teamAwayDocId)
                    getAwayTeamRightBack(teamAwayDocId)
                    getAwayTeamDefensiveMidfielder(teamAwayDocId)
                    getAwayTeamCentralMidfielder(teamAwayDocId)
                    getAwayTeamAttackingMidfielder(teamAwayDocId)
                    getAwayTeamLeftWinger(teamAwayDocId)
                    getAwayTeamRightWinger(teamAwayDocId)
                    getAwayTeamCentreForward(teamAwayDocId)
                    getAwayTeamSecondStriker(teamAwayDocId)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getAwayTeamGoalKeeper(teamAwayDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamAwayDocId)
            .whereEqualTo("role_pemain", "Goal Keeper")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamAwayGoalKeeper = document.getString("nama_pemain").toString()
                    Log.d("Away Goal Keeper: ", teamAwayGoalKeeper)

                    tvAwayGoalKeeperName = findViewById<TextView>(R.id.tv_goal_player1_name)
                    tvAwayGoalKeeperName.text = teamAwayGoalKeeper
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getAwayTeamCentreBack(teamAwayDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamAwayDocId)
            .whereEqualTo("role_pemain", "Centre Back")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamAwayCentreBack = document.getString("nama_pemain").toString()
                    Log.d("Away Centre Back: ", teamAwayCentreBack)

                    tvAwayCentreBackName = findViewById<TextView>(R.id.tv_goal_player2_name_away)
                    tvAwayCentreBackName.text = teamAwayCentreBack
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getAwayTeamLeftBack(teamAwayDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamAwayDocId)
            .whereEqualTo("role_pemain", "Left Back")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamAwayLeftBack = document.getString("nama_pemain").toString()
                    Log.d("Away Left Back: ", teamAwayLeftBack)

                    tvAwayLeftBackName = findViewById<TextView>(R.id.tv_goal_player3_name_away)
                    tvAwayLeftBackName.text = teamAwayLeftBack

                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getAwayTeamRightBack(teamAwayDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamAwayDocId)
            .whereEqualTo("role_pemain", "Right Back")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamAwayRightBack = document.getString("nama_pemain").toString()
                    Log.d("Away Right Back: ", teamAwayRightBack)

                    tvAwayRightBackName = findViewById<TextView>(R.id.tv_goal_player4_name_away)
                    tvAwayRightBackName.text = teamAwayRightBack
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getAwayTeamDefensiveMidfielder(teamAwayDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamAwayDocId)
            .whereEqualTo("role_pemain", "Defensive Midfielder")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamAwayDefensiveMidfielder = document.getString("nama_pemain").toString()
                    Log.d("Away Defensive Midfielder: ", teamAwayDefensiveMidfielder)

                    tvAwayDefensiveMidfielderName = findViewById<TextView>(R.id.tv_goal_player5_name_away)
                    tvAwayDefensiveMidfielderName.text = teamAwayDefensiveMidfielder

                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getAwayTeamCentralMidfielder(teamAwayDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamAwayDocId)
            .whereEqualTo("role_pemain", "Central Midfielder")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamAwayCentralMidfielder = document.getString("nama_pemain").toString()
                    Log.d("Away Central Midfielder: ", teamAwayCentralMidfielder)

                    tvAwayCentralMidfielderName = findViewById<TextView>(R.id.tv_goal_player6_name_away)
                    tvAwayCentralMidfielderName.text = teamAwayCentralMidfielder
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getAwayTeamAttackingMidfielder(teamAwayDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamAwayDocId)
            .whereEqualTo("role_pemain", "Attacking Midfielder")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamAwayAttackingMidfielder = document.getString("nama_pemain").toString()
                    Log.d("Away Attacking Midfielder: ", teamAwayAttackingMidfielder)

                    tvAwayAttackingMidfielderName = findViewById<TextView>(R.id.tv_goal_player7_name_away)
                    tvAwayAttackingMidfielderName.text = teamAwayAttackingMidfielder

                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getAwayTeamLeftWinger(teamAwayDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamAwayDocId)
            .whereEqualTo("role_pemain", "Left Winger")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamAwayLeftWinger = document.getString("nama_pemain").toString()
                    Log.d("Away Left Winger: ", teamAwayLeftWinger)

                    tvAwayLeftWingerName = findViewById<TextView>(R.id.tv_goal_player8_name_away)
                    tvAwayLeftWingerName.text = teamAwayLeftWinger
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getAwayTeamRightWinger(teamAwayDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamAwayDocId)
            .whereEqualTo("role_pemain", "Right Winger")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamAwayRightWinger = document.getString("nama_pemain").toString()
                    Log.d("Away Right Winger: ", teamAwayRightWinger)

                    tvAwayRightWingerName = findViewById<TextView>(R.id.tv_goal_player9_name_away)
                    tvAwayRightWingerName.text = teamAwayRightWinger
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getAwayTeamCentreForward(teamAwayDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamAwayDocId)
            .whereEqualTo("role_pemain", "Centre Forward")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamAwayCentreForward = document.getString("nama_pemain").toString()
                    Log.d("Away Centre Forward: ", teamAwayCentreForward)

                    tvAwayCentreForwardName = findViewById<TextView>(R.id.tv_goal_player10_name_away)
                    tvAwayCentreForwardName.text = teamAwayCentreForward
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getAwayTeamSecondStriker(teamAwayDocId: String) {
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamAwayDocId)
            .whereEqualTo("role_pemain", "Second Striker")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamAwaySecondStriker = document.getString("nama_pemain").toString()
                    Log.d("Away Second Striker: ", teamAwaySecondStriker)

                    tvAwaySecondStrikerName = findViewById<TextView>(R.id.tv_goal_player11_name_away)
                    tvAwaySecondStrikerName.text = teamAwaySecondStriker

                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

}