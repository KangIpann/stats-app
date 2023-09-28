package Match

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.statsapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text

class DetailMatch : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var data_owner: FirebaseAuth
    private lateinit var homeTeam : String
    private lateinit var awayTeam : String
    private lateinit var documentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_detail)

        //set variabel from intent
        db = FirebaseFirestore.getInstance()
        documentId = intent.getStringExtra("documentId").toString()
        homeTeam = intent.getStringExtra("homeTeam").toString()
        awayTeam = intent.getStringExtra("awayTeam").toString()

        setHomePicture(documentId)
        setGoal(documentId)
        setAssist(documentId)
        setViolations(documentId)
    }

    private fun setViolations(documentId: String) {
        val tvHomeViolations = findViewById<TextView>(R.id.tvHomeViolations)
        val tvAwayViolations = findViewById<TextView>(R.id.tvAwayViolations)
        val docRef = db.collection("matchStats").whereEqualTo("match_id", documentId)
        docRef.addSnapshotListener(){
            snapshot, e ->
            if (e != null){
                Log.w("TAG", "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null){
                for (document in snapshot){
                    val homeTeam = intent.getStringExtra("homeTeam").toString()
                    val awayTeam = intent.getStringExtra("awayTeam").toString()

                    //variabel pelanggaran team home
                    val homeShootFail = document.getLong("${homeTeam}_shoot_fail") ?: 0
                    val home10mGoal = document.getLong("${homeTeam}_10m_goal") ?: 0
                    val home10mFail = document.getLong("${homeTeam}_10m_fail") ?: 0
                    val homePenalty = document.getLong("${homeTeam}_penalty") ?: 0
                    val homeYellowCard = document.getLong("${homeTeam}_yellow_card") ?: 0
                    val homeRedCard = document.getLong("${homeTeam}_red_card") ?: 0
                    val homeOffside = document.getLong("${homeTeam}_offside") ?: 0
                    val homeSteal = document.getLong("${homeTeam}_steal") ?: 0
                    val homeViolations = homeShootFail + home10mGoal + home10mFail + homePenalty + homeYellowCard + homeRedCard + homeOffside + homeSteal

                    //variabel pelanggaran team away
                    val awayShootFail = document.getLong("${awayTeam}_shoot_fail") ?: 0
                    val away10mGoal = document.getLong("${awayTeam}_10m_goal") ?: 0
                    val away10mFail = document.getLong("${awayTeam}_10m_fail") ?: 0
                    val awayPenalty = document.getLong("${awayTeam}_penalty") ?: 0
                    val awayYellowCard = document.getLong("${awayTeam}_yellow_card") ?: 0
                    val awayRedCard = document.getLong("${awayTeam}_red_card") ?: 0
                    val awayOffside = document.getLong("${awayTeam}_offside") ?: 0
                    val awaySteal = document.getLong("${awayTeam}_steal") ?: 0
                    val awayViolations = awayShootFail + away10mGoal + away10mFail + awayPenalty + awayYellowCard + awayRedCard + awayOffside + awaySteal

                    tvHomeViolations.text = homeViolations.toString()
                    tvAwayViolations.text = awayViolations.toString()

                    //setel untuk offside
                    val tvHomeOffside = findViewById<TextView>(R.id.tvHomeOffside)
                    val tvAwayOffside = findViewById<TextView>(R.id.tvAwayOffside)
                    tvHomeOffside.text = "0"
                    tvAwayOffside.text = "0"
                    if (homeOffside == null || awayOffside == null){
                        tvHomeOffside.text = "0"
                        tvAwayOffside.text = "0"
                    }else{
                        tvHomeOffside.text = homeOffside.toString()
                        tvAwayOffside.text = awayOffside.toString()
                    }

                    //setel untuk yellow dan red card
                    val tvHomeYellowCard = findViewById<TextView>(R.id.homeYellowCard)
                    val tvAwayYellowCard = findViewById<TextView>(R.id.awayYellowCard)
                    val tvHomeRedCard = findViewById<TextView>(R.id.homeRedCard)
                    val tvAwayRedCard = findViewById<TextView>(R.id.awayRedCard)
                    tvHomeYellowCard.text = "0"
                    tvAwayYellowCard.text = "0"
                    tvHomeRedCard.text = "0"
                    tvAwayRedCard.text = "0"
                    if (homeYellowCard == null || awayYellowCard == null || homeRedCard == null || awayRedCard == null){
                        tvHomeYellowCard.text = homeYellowCard.toString()
                        tvAwayYellowCard.text = awayYellowCard.toString()
                        tvHomeRedCard.text = homeRedCard.toString()
                        tvAwayRedCard.text = awayRedCard.toString()
                    }else{
                        tvHomeYellowCard.text = homeYellowCard.toString()
                        tvAwayYellowCard.text = awayYellowCard.toString()
                        tvHomeRedCard.text = homeRedCard.toString()
                        tvAwayRedCard.text = awayRedCard.toString()
                    }

                }
            }else{
                Log.d("TAG", "Current data: null")
            }

        }
    }

    private fun setAssist(documentId: String) {
        val tvHomeAssist = findViewById<TextView>(R.id.tvHomeAssist)
        val tvAwayAssist = findViewById<TextView>(R.id.tvAwayAssist)
        db.collection("matchStats").whereEqualTo("match_id", documentId)
            .get()
            .addOnSuccessListener {
                for (document in it){
                    val homeTeam = intent.getStringExtra("homeTeam").toString()
                    val awayTeam = intent.getStringExtra("awayTeam").toString()
                    val homeAssist = document.getLong("${homeTeam}_assist")
                    val awayAssist = document.getLong("${awayTeam}_assist")
                    tvHomeAssist.text = "0"
                    tvAwayAssist.text = "0"
                    if (homeAssist == null || awayAssist == null){
                        tvHomeAssist.text = "0"
                        tvAwayAssist.text = "0"
                    }else{
                        tvHomeAssist.text = homeAssist.toString()
                        tvAwayAssist.text = awayAssist.toString()
                    }
                }
            }
            .addOnFailureListener {
                Log.d("TAG", "Error getting documents: ", it)
            }
    }

    private fun setGoal(documentId: String) {
        val tvHomeGoal = findViewById<TextView>(R.id.tvHomeGoal)
        val tvAwayGoal = findViewById<TextView>(R.id.tvGoalAway)
        db.collection("matchStats").whereEqualTo("match_id", documentId)
            .get()
            .addOnSuccessListener {
                for (document in it){
                    val homeTeam = intent.getStringExtra("homeTeam").toString()
                    val awayTeam = intent.getStringExtra("awayTeam").toString()
                    val homeGoal = document.getLong("${homeTeam}_goal")
                    val awayGoal = document.getLong("${awayTeam}_goal")
                    tvHomeGoal.text = "0"
                    tvAwayGoal.text = "0"
                    if (homeGoal == null || awayGoal == null){
                        tvHomeGoal.text = "0"
                        tvAwayGoal.text = "0"
                    }else{
                        tvHomeGoal.text = homeGoal.toString()
                        tvAwayGoal.text = awayGoal.toString()
                    }
                }
            }
            .addOnFailureListener {
                Log.d("TAG", "Error getting documents: ", it)
            }
    }

    private fun setHomePicture(documentId : String){
        val ivHome = findViewById<ImageView>(R.id.iv_home)
        db.collection("match").document(documentId)
            .get()
            .addOnSuccessListener {
                val homeTeam = it.getString("tim_home_match")
                val tvHomeTeam = findViewById<TextView>(R.id.tvHomeName)
                tvHomeTeam.text = homeTeam.toString()
                db.collection("team").whereEqualTo("nama_team", homeTeam)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            val homePicture = document.getString("logo")
                            Glide.with(this)
                                .load(homePicture)
                                .into(ivHome)
                        }
                    }
            }

        val ivAway = findViewById<ImageView>(R.id.iv_away)
        db.collection("match").document(documentId)
            .get()
            .addOnSuccessListener(){
                val awayTeam = it.getString("tim_away_match")
                val tvAwayTeam = findViewById<TextView>(R.id.tvAwayName)
                tvAwayTeam.text = awayTeam.toString()
                db.collection("team").whereEqualTo("nama_team", awayTeam)
                    .get()
                    .addOnSuccessListener{ documents ->
                        for (document in documents) {
                            val awayPicture = document.getString("logo")
                            Glide.with(this)
                                .load(awayPicture)
                                .into(ivAway)
                        }
                    }
            }
    }

}