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

        //Print Log
    }

    private fun setLeagueDate(){
        val matchDate = intent.getStringExtra("matchDate")
        println("Tanggal Match Yang Diterima: $matchDate")

        val tvLeagueDate = findViewById<TextView>(R.id.league_date)
        tvLeagueDate.text = matchDate
    }

    @SuppressLint("SetTextI18n")
    private fun setLeagueName(){
        val matchName = intent.getStringExtra("matchName")
        println("Nama Match Yang Diterima: $matchName")

        if (matchName != null) {
            val tvLeagueName = findViewById<TextView>(R.id.league_name)
            tvLeagueName.text = matchName
        }else{
            val tvLeagueName = findViewById<TextView>(R.id.league_name)
            tvLeagueName.text = "League Name Not Found"
            Log.d("Error", "Error getting league name")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setHomeTeam(){
        val homeTeam = intent.getStringExtra("matchHomeTeam")
        println("Nama Home Team Yang Diterima: $homeTeam")

        if (homeTeam != null) {
            val tvTeamHome = findViewById<TextView>(R.id.team_name_home)
            tvTeamHome.text = homeTeam
        }else{
            val tvTeamHome = findViewById<TextView>(R.id.team_name_home)
            tvTeamHome.text = "Team Name Not Found"
            Log.d("Error", "Error getting team name")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setAwayTeam(){
        val awayTeam = intent.getStringExtra("matchAwayTeam")
        Log.d("Away Team: ", awayTeam.toString())

        if (awayTeam != null) {
            val tvTeamAway = findViewById<TextView>(R.id.team_name_away)
            tvTeamAway.text = awayTeam
        }else{
            val tvTeamAway = findViewById<TextView>(R.id.team_name_away)
            tvTeamAway.text = "Team Name Not Found"
            Log.d("Error", "Error getting team name")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setMatchDuration() {
        val matchDuration = intent.getStringExtra("matchDuration")
        Log.d("Durasi Match: ", matchDuration.toString())

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

                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    tvTimer.text = "00:00:00"
                }
            }.start()
        }else{
            val tvTimer = findViewById<TextView>(R.id.timerTextView)
            tvTimer.text = "Time Not Found"
            Log.d("Error", "Error getting times")
        }
    }

    private fun getHomeTeamGoalKeeper(teamHomeDocId: String) {
        //cari data pada collection pemain yang memiliki field id_tim_pemain sama dengan teamHomeDocId, role_pemain sama dengan Goal Keeper, dan status_pemain sama dengan 1
        db.collection("pemain")
            .whereEqualTo("id_tim_pemain", teamHomeDocId)
            .whereEqualTo("role_pemain", "Goal Keeper")
            .whereEqualTo("status_pemain", "Pemain Aktif")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    teamHomeGoalKeeper = document.getString("nama_pemain").toString()
                    Log.d("Home Goal Keeper: ", teamHomeGoalKeeper)
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
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getHomeTeamDocumentId(){
        val team = intent.getStringExtra("matchHomeTeam")
        Log.d("Nama Team Home: ", team.toString())

        //mencari documentId dari team yang dipilih
        db.collection("team")
            .whereEqualTo("nama_team", team)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val documentId = document.id
                    Log.d("Document Id: ", documentId.toString())
                    teamHomeDocId = documentId

                    //fungsi untuk mendapatkan nama pemain berdasarkan role
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

        //mencari documentId dari team yang dipilih
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
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

}