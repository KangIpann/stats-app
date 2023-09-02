package Match

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.statsapp.R
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.math.log

class MatchBerjalan : AppCompatActivity() {

    private lateinit var documentId : String
    private lateinit var db: FirebaseFirestore
    private lateinit var matchId : String
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
        setDatabase()
    }

    private fun setDatabase(){
        val matchStats = hashMapOf(
            "document_id" to "",
            "league_name" to "",
            "league_date" to "",
            "home_team" to "",
            "away_team" to "",
            "home_goal_keeper" to "",
            "home_centre_back" to "",
            "home_left_back" to "",
            "home_right_back" to "",
            "home_defensive_midfielder" to "",
            "home_central_midfielder" to "",
            "home_attacking_midfielder" to "",
            "home_left_winger" to "",
            "home_right_winger" to "",
            "home_centre_forward" to "",
            "home_second_striker" to "",
            "away_goal_keeper" to "",
            "away_centre_back" to "",
            "away_left_back" to "",
            "away_right_back" to "",
            "away_defensive_midfielder" to "",
            "away_central_midfielder" to "",
            "away_attacking_midfielder" to "",
            "away_left_winger" to "",
            "away_right_winger" to "",
            "away_centre_forward" to "",
            "away_second_striker" to "",
            "home_shoot_fail" to 0,
            "home_goal" to 0,
        )

        db.collection("matchStats")
            .add(matchStats)
            .addOnSuccessListener { documentReference ->
                Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                matchId = intent.getStringExtra("matchId").toString()
                documentId = documentReference.id
                println("Match Id: $matchId")

                db.collection("matchStats").document(documentId)
                    .update("document_id", documentId)
                    .addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }

                db.collection("matchStats").document(documentId)
                    .update("match_id", matchId)
                    .addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }

                db.collection("matchStats").document(documentId)
                    .update("league_name", matchName)
                    .addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }

                db.collection("matchStats").document(documentId)
                    .update("league_date", matchDate)
                    .addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }

                //update home team
                db.collection("matchStats").document(documentId)
                val homeTeam = "${tvTeamHome.text}"
                db.collection("matchStats").document(documentId)
                    .update("home_team", homeTeam)
                    .addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }

                //update away team
                db.collection("matchStats").document(documentId)
                val awayTeam = "${tvTeamAway.text}"
                db.collection("matchStats").document(documentId)
                    .update("away_team", awayTeam)
                    .addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }
            .addOnFailureListener { e ->
                Log.w("Error", "Error adding document", e)
            }

    }

    private fun showGoalKeeperDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.dialog_match_aksi_landscape, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()

        val tvPlayerName = dialogView.findViewById<TextView>(R.id.tv_tendangan_goal)
        val goalKeeperName = "${tvHomeGoalKeeperName.text}"
        tvPlayerName.text = "Aksi Pemain: $goalKeeperName"

        val btnShootGoal = dialogView.findViewById<TextView>(R.id.button_shootGoal)
        //jika button shoot goal diklik maka dialog akan berganti menjadi dialog goal
        btnShootGoal.setOnClickListener(){
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = LayoutInflater.from(this)
            val dialogView = inflater.inflate(R.layout.dialog_match_goal, null)
            dialogBuilder.setView(dialogView)
            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            val shootGoal = dialogView.findViewById<TextView>(R.id.button_goal_shoot)
            shootGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val actionGoal = shootGoal.text.toString()
                        val currentShootGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentGoalKeeperShootGoal = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${shootGoal.text}_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal", homeGoalKeeperShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal_time", goalTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                        val currentShootGoalTimePlayer = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${actionGoal}_goal") ?: 0
                        val fieldName = "${tvHomeGoalKeeperName.text}_${currentShootGoalTimePlayer}_${actionGoal}_goal_time"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }

            val healGoal = dialogView.findViewById<TextView>(R.id.button_goal_Heal)
            healGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val actionGoal = healGoal.text.toString()
                        val currentShootGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentGoalKeeperShootGoal = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${healGoal.text}_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal", homeGoalKeeperShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_heal_goal_times", goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                        val currentShootGoalTimePlayer = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${actionGoal}_goal") ?: 0
                        val fieldName = "${tvHomeGoalKeeperName.text}_${currentShootGoalTimePlayer}_${actionGoal}_goal_time"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }

            val valleyGoal = dialogView.findViewById<TextView>(R.id.button_goal_valley)
            valleyGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val actionGoal = valleyGoal.text.toString()
                        val currentShootGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentGoalKeeperShootGoal = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${valleyGoal.text}_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal", homeGoalKeeperShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times", goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentShootGoalTimePlayer = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${actionGoal}_goal") ?: 0
                        val fieldName = "${tvHomeGoalKeeperName.text}_${currentShootGoalTimePlayer}_${actionGoal}_goal_time"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }

            val longGoal = dialogView.findViewById<TextView>(R.id.button_goal_long)
            longGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val actionGoal = longGoal.text.toString()
                        val currentShootGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentGoalKeeperShootGoal = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${longGoal.text}_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal", homeGoalKeeperShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times", goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentShootGoalTimePlayer = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${actionGoal}_goal") ?: 0
                        val fieldName = "${tvHomeGoalKeeperName.text}_${currentShootGoalTimePlayer}_${actionGoal}_goal_time"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }

            val healedGoal = dialogView.findViewById<TextView>(R.id.button_goal_healed)
            healedGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val actionGoal = healedGoal.text.toString()
                        val currentShootGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentGoalKeeperShootGoal = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${healedGoal.text}_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal", homeGoalKeeperShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times", goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentShootGoalTimePlayer = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${actionGoal}_goal") ?: 0
                        val fieldName = "${tvHomeGoalKeeperName.text}_${currentShootGoalTimePlayer}_${actionGoal}_goal_time"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }

            val lobGoal = dialogView.findViewById<TextView>(R.id.button_goal_lob)
            lobGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val actionGoal = lobGoal.text.toString()
                        val currentShootGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentGoalKeeperShootGoal = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${lobGoal.text}_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal", homeGoalKeeperShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times", goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentShootGoalTimePlayer = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${actionGoal}_goal") ?: 0
                        val fieldName = "${tvHomeGoalKeeperName.text}_${currentShootGoalTimePlayer}_${actionGoal}_goal_time"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }

            val foulGoal = dialogView.findViewById<TextView>(R.id.button_goal_foul)
            foulGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val actionGoal = foulGoal.text.toString()
                        val currentShootGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentGoalKeeperShootGoal = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${foulGoal.text}_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal", homeGoalKeeperShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times", goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentShootGoalTimePlayer = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${actionGoal}_goal") ?: 0
                        val fieldName = "${tvHomeGoalKeeperName.text}_${currentShootGoalTimePlayer}_${actionGoal}_goal_time"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }

            val reverseGoal = dialogView.findViewById<TextView>(R.id.button_goal_reverse)
            reverseGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val actionGoal = reverseGoal.text.toString()
                        val currentShootGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentGoalKeeperShootGoal = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${reverseGoal.text}_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal", homeGoalKeeperShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times", goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentShootGoalTimePlayer = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${actionGoal}_goal") ?: 0
                        val fieldName = "${tvHomeGoalKeeperName.text}_${currentShootGoalTimePlayer}_${actionGoal}_goal_time"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }

            val scissorsGoal = dialogView.findViewById<TextView>(R.id.button_goal_Scissors)
            scissorsGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val actionGoal = scissorsGoal.text.toString()
                        val currentShootGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentGoalKeeperShootGoal = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${scissorsGoal.text}_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal", homeGoalKeeperShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times", goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentShootGoalTimePlayer = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${actionGoal}_goal") ?: 0
                        val fieldName = "${tvHomeGoalKeeperName.text}_${currentShootGoalTimePlayer}_${actionGoal}_goal_time"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    }.addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }

            val otherGoal = dialogView.findViewById<TextView>(R.id.button_goal_other)
            otherGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val actionGoal = otherGoal.text.toString()
                        val currentShootGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentGoalKeeperShootGoal = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${otherGoal.text}_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal", homeGoalKeeperShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times", goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentShootGoalTimePlayer = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${actionGoal}_goal") ?: 0
                        val fieldName = "${tvHomeGoalKeeperName.text}_${currentShootGoalTimePlayer}_${actionGoal}_goal_time"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, goalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }.addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }

        }

        val btnShootFail = dialogView.findViewById<TextView>(R.id.button_shootFail)
        btnShootFail.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->

                    val currentShootFail = documentSnapshot.getLong("home_shoot_fail") ?: 0
                    val currentGoalKeeperShootFail = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_shoot_fail") ?: 0
                    val homeShootFail = currentShootFail + 1
                    val homeGoalKeeperShootFail = currentGoalKeeperShootFail + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_shoot_fail", homeShootFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_shoot_fail", homeGoalKeeperShootFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val shootFailTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_shoot_fail_times", shootFailTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val currentShootFailPlayer = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_shoot_fail") ?: 0
                    val fieldName = "${tvHomeGoalKeeperName.text}_${currentShootFailPlayer}_shoot_fail_times"
                    db.collection("matchStats").document(documentId)
                        .update(fieldName, shootFailTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }

        }

        val btnAssist = dialogView.findViewById<TextView>(R.id.button_assist)
        btnAssist.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->

                    val currentAssist = documentSnapshot.getLong("home_assist") ?: 0
                    val currentGoalKeeperAssist = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_assist") ?: 0
                    val homeAssist = currentAssist + 1
                    val homeGoalKeeperAssist = currentGoalKeeperAssist + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_assist", homeAssist)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_assist", homeGoalKeeperAssist)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val assistTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_assist_times", assistTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btn10mfail = dialogView.findViewById<TextView>(R.id.button_10mfail)
        btn10mfail.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->

                    val current10mfail = documentSnapshot.getLong("home_10m_fail") ?: 0
                    val currentGoalKeeper10mfail = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_10m_fail") ?: 0
                    val home10mfail = current10mfail + 1
                    val homeGoalKeeper10mfail = currentGoalKeeper10mfail + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_10m_fail", home10mfail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_10m_fail", homeGoalKeeper10mfail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val tenMeterFailTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_10m_fail_times", tenMeterFailTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btn10mgoal = dialogView.findViewById<TextView>(R.id.button_10mGoal)
        btn10mgoal.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val current10mgoal = documentSnapshot.getLong("home_10m_goal") ?: 0
                    val currentGoalKeeper10mgoal = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_10m_goal") ?: 0
                    val home10mgoal = current10mgoal + 1
                    val homeGoalKeeper10mgoal = currentGoalKeeper10mgoal + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_10m_goal", home10mgoal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_10m_goal", homeGoalKeeper10mgoal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val tenMeterGoalTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_10m_goal_times", tenMeterGoalTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }.addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnRedCard = dialogView.findViewById<TextView>(R.id.button_redCard)
        btnRedCard.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentRedCard = documentSnapshot.getLong("${tvTeamHome.text}_red_card") ?: 0
                    val currentGoalKeeperRedCard = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_red_card") ?: 0
                    val homeRedCard = currentRedCard + 1
                    val homeGoalKeeperRedCard = currentGoalKeeperRedCard + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_red_card", homeRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_red_card", homeGoalKeeperRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val redCardTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_red_card_times", redCardTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }.addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnYellowCard = dialogView.findViewById<TextView>(R.id.button_yellowCard)
        btnYellowCard.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentYellowCard = documentSnapshot.getLong("${tvTeamHome.text}_yellow_card") ?: 0
                    val currentGoalKeeperYellowCard = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_yellow_card") ?: 0
                    val homeYellowCard = currentYellowCard + 1
                    val homeGoalKeeperYellowCard = currentGoalKeeperYellowCard + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_yellow_card", homeYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_yellow_card", homeGoalKeeperYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val yellowCardTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_yellow_card_times", yellowCardTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }.addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnSteal = dialogView.findViewById<TextView>(R.id.button_steal)
        btnSteal.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentSteal = documentSnapshot.getLong("${tvTeamHome.text}_steal") ?: 0
                    val currentGoalKeeperSteal = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_steal") ?: 0
                    val homeSteal = currentSteal + 1
                    val homeGoalKeeperSteal = currentGoalKeeperSteal + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_steal", homeSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_steal", homeGoalKeeperSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val stealTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_steal_times", stealTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }.addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnPenalty = dialogView.findViewById<TextView>(R.id.button_penalty)
        btnPenalty.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentPenalty = documentSnapshot.getLong("${tvTeamHome.text}_penalty") ?: 0
                    val currentGoalKeeperPenalty = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_penalty") ?: 0
                    val homePenalty = currentPenalty + 1
                    val homeGoalKeeperPenalty = currentGoalKeeperPenalty + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_penalty", homePenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_penalty", homeGoalKeeperPenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val penaltyTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_penalty_times", penaltyTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }.addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnOffside = dialogView.findViewById<TextView>(R.id.button_offside)
        btnOffside.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentOffside = documentSnapshot.getLong("${tvTeamHome.text}_offside") ?: 0
                    val currentGoalKeeperOffside = documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_offside") ?: 0
                    val homeOffside = currentOffside + 1
                    val homeGoalKeeperOffside = currentGoalKeeperOffside + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_offside", homeOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_offside", homeGoalKeeperOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val offsideTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_offside_times", offsideTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }.addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val gkConstraint = findViewById<ConstraintLayout>(R.id.gk_constraint_home)
        gkConstraint.setOnClickListener(){
            alertDialog.show()
        }
        tvHomeGoalKeeperName = findViewById<TextView>(R.id.tv_goal_player1_name_home)
        tvHomeGoalKeeperName.setOnClickListener(){
            alertDialog.show()
        }
    }

    private fun showHomeCentreBackDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_match_aksi_landscape, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        tvHomeCentreBackName = findViewById<TextView>(R.id.tv_goal_player2_name_home)

        val tvName = dialogView.findViewById<TextView>(R.id.tv_tendangan_goal)
        tvName.text = "Aksi Pemain: ${tvHomeCentreBackName.text}"

        val btnShootFail = dialogView.findViewById<TextView>(R.id.button_shootFail)
        btnShootFail.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->

                    val currentShootFail = documentSnapshot.getLong("home_shoot_fail") ?: 0
                    val currentCentreBackShootFail = documentSnapshot.getLong("${tvHomeCentreBackName.text}_shoot_fail") ?: 0
                    val homeShootFail = currentShootFail + 1
                    val homeCentreBackShootFail = currentCentreBackShootFail + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_shoot_fail", homeShootFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_shoot_fail", homeCentreBackShootFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val shootFailTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_shoot_fail_times", shootFailTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val currentShootFailPlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_shoot_fail") ?: 0
                    val fieldName = "${tvHomeCentreBackName.text}_${currentShootFailPlayer}_shoot_fail"
                    db.collection("matchStats").document(documentId)
                        .update(fieldName, shootFailTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }

        }

        val btnAssist = dialogView.findViewById<TextView>(R.id.button_assist)
        btnAssist.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->

                    val currentAssist = documentSnapshot.getLong("home_assist") ?: 0
                    val currentCentreBackAssist = documentSnapshot.getLong("${tvHomeCentreBackName.text}_assist") ?: 0
                    val homeAssist = currentAssist + 1
                    val homeCentreBackAssist = currentCentreBackAssist + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_assist", homeAssist)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_assist", homeCentreBackAssist)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val assistTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_assist_times", assistTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val currentAssistPlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_assist") ?: 0
                    val fieldName = "${tvHomeCentreBackName.text}_${currentAssistPlayer}_assist"
                    db.collection("matchStats").document(documentId)
                        .update(fieldName, assistTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btn10mfail = dialogView.findViewById<TextView>(R.id.button_10mfail)
        btn10mfail.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->

                    val current10mfail = documentSnapshot.getLong("home_10m_fail") ?: 0
                    val currentCentreBack10mfail = documentSnapshot.getLong("${tvHomeCentreBackName.text}_10m_fail") ?: 0
                    val home10mfail = current10mfail + 1
                    val homeCentreBack10mfail = currentCentreBack10mfail + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_10m_fail", home10mfail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_10m_fail", homeCentreBack10mfail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val tenMeterFailTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_10m_fail_times", tenMeterFailTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val current10mFailPlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_10m_fail") ?: 0
                    val fieldName = "${tvHomeCentreBackName.text}_${current10mFailPlayer}_10m_fail"
                    db.collection("matchStats").document(documentId)
                        .update(fieldName, tenMeterFailTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }

        }

        val btn10mgoal = dialogView.findViewById<TextView>(R.id.button_10mGoal)
        btn10mgoal.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val current10mgoal = documentSnapshot.getLong("home_10m_goal") ?: 0
                    val currentCentreBack10mgoal =
                        documentSnapshot.getLong("${tvHomeCentreBackName.text}_10m_goal") ?: 0
                    val home10mgoal = current10mgoal + 1
                    val homeCentreBack10mgoal = currentCentreBack10mgoal + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_10m_goal", home10mgoal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_10m_goal", homeCentreBack10mgoal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val tenMeterGoalTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_10m_goal_times", tenMeterGoalTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val current10mGoalPlayer =
                        documentSnapshot.getLong("${tvHomeCentreBackName.text}_10m_goal") ?: 0
                    val fieldName = "${tvHomeCentreBackName.text}_${current10mGoalPlayer}_10m_goal"
                    db.collection("matchStats").document(documentId)
                        .update(fieldName, tenMeterGoalTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }.addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnRedCard = dialogView.findViewById<TextView>(R.id.button_redCard)
        btnRedCard.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentRedCard = documentSnapshot.getLong("${tvTeamHome.text}_red_card") ?: 0
                    val currentCentreBackRedCard = documentSnapshot.getLong("${tvHomeCentreBackName.text}_red_card") ?: 0
                    val homeRedCard = currentRedCard + 1
                    val homeCentreBackRedCard = currentCentreBackRedCard + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_red_card", homeRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_red_card", homeCentreBackRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val redCardTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_red_card_times", redCardTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val currentRedCardPlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_red_card") ?: 0
                    val fieldName = "${tvHomeCentreBackName.text}_${currentRedCardPlayer}_red_card"
                    db.collection("matchStats").document(documentId)
                        .update(fieldName, redCardTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }.addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnYellowCard = dialogView.findViewById<TextView>(R.id.button_yellowCard)
        btnYellowCard.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentYellowCard = documentSnapshot.getLong("${tvTeamHome.text}_yellow_card") ?: 0
                    val currentCentreBackYellowCard = documentSnapshot.getLong("${tvHomeCentreBackName.text}_yellow_card") ?: 0
                    val homeYellowCard = currentYellowCard + 1
                    val homeCentreBackYellowCard = currentCentreBackYellowCard + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_yellow_card", homeYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_yellow_card", homeCentreBackYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val yellowCardTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_yellow_card_times", yellowCardTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val currentYellowCardPlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_yellow_card") ?: 0
                    val fieldName = "${tvHomeCentreBackName.text}_${currentYellowCardPlayer}_yellow_card"
                    db.collection("matchStats").document(documentId)
                        .update(fieldName, yellowCardTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }.addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnSteal = dialogView.findViewById<TextView>(R.id.button_steal)
        btnSteal.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentSteal = documentSnapshot.getLong("${tvTeamHome.text}_steal") ?: 0
                    val currentCentreBackSteal = documentSnapshot.getLong("${tvHomeCentreBackName.text}_steal") ?: 0
                    val homeSteal = currentSteal + 1
                    val homeCentreBackSteal = currentCentreBackSteal + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_steal", homeSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_steal", homeCentreBackSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val stealTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_steal_times", stealTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val currentStealPlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_steal") ?: 0
                    val fieldName = "${tvHomeCentreBackName.text}_${currentStealPlayer}_steal"
                    db.collection("matchStats").document(documentId)
                        .update(fieldName, stealTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }.addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnPenalty = dialogView.findViewById<TextView>(R.id.button_penalty)
btnPenalty.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentPenalty = documentSnapshot.getLong("${tvTeamHome.text}_penalty") ?: 0
                    val currentCentreBackPenalty = documentSnapshot.getLong("${tvHomeCentreBackName.text}_penalty") ?: 0
                    val homePenalty = currentPenalty + 1
                    val homeCentreBackPenalty = currentCentreBackPenalty + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_penalty", homePenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_penalty", homeCentreBackPenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val penaltyTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_penalty_times", penaltyTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val currentPenaltyPlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_penalty") ?: 0
                    val fieldName = "${tvHomeCentreBackName.text}_${currentPenaltyPlayer}_penalty"
                    db.collection("matchStats").document(documentId)
                        .update(fieldName, penaltyTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }.addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnOffside = dialogView.findViewById<TextView>(R.id.button_offside)
        btnOffside.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentOffside = documentSnapshot.getLong("${tvTeamHome.text}_offside") ?: 0
                    val currentCentreBackOffside = documentSnapshot.getLong("${tvHomeCentreBackName.text}_offside") ?: 0
                    val homeOffside = currentOffside + 1
                    val homeCentreBackOffside = currentCentreBackOffside + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_offside", homeOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_offside", homeCentreBackOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val offsideTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeCentreBackName.text}_offside_times", offsideTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val currentOffsidePlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_offside") ?: 0
                    val fieldName = "${tvHomeCentreBackName.text}_${currentOffsidePlayer}_offside"
                    db.collection("matchStats").document(documentId)
                        .update(fieldName, offsideTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }.addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnGoal = dialogView.findViewById<TextView>(R.id.button_shootGoal)
        btnGoal.setOnClickListener(){
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_match_goal, null)
            dialogBuilder.setView(dialogView)
            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            val btnGoalShoot = dialogView.findViewById<TextView>(R.id.button_goal_shoot)
            btnGoalShoot.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val currentShootGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentCentreBackShootGoal = documentSnapshot.getLong("${tvHomeCentreBackName.text}_shoot_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeCentreBackShootGoal = currentCentreBackShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeCentreBackName.text}_shoot_goal", homeCentreBackShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val shootGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeCentreBackName.text}_shoot_goal_times", shootGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentShootGoalPlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_shoot_goal") ?: 0
                        val fieldName = "${tvHomeCentreBackName.text}_${currentShootGoalPlayer}_shoot_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, shootGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }

            val btnHealGoal = dialogView.findViewById<TextView>(R.id.button_goal_Heal)
            btnHealGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentHealGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentCentreBackHealGoal =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_heal_goal") ?: 0
                        val homeHealGoal = currentHealGoal + 1
                        val homeCentreBackHealGoal = currentCentreBackHealGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeHealGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_heal_goal",
                                homeCentreBackHealGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeCentreBackName.text}_heal_goal_times", healGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentHealGoalPlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_heal_goal") ?: 0
                        val fieldName = "${tvHomeCentreBackName.text}_${currentHealGoalPlayer}_heal_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, healGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }

            val btnValleyGoal = dialogView.findViewById<TextView>(R.id.button_goal_valley)
            btnValleyGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentValleyGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentCentreBackValleyGoal =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_valley_goal")
                                ?: 0
                        val homeValleyGoal = currentValleyGoal + 1
                        val homeCentreBackValleyGoal = currentCentreBackValleyGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeValleyGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_valley_goal",
                                homeCentreBackValleyGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val valleyGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_valley_goal_times",
                                valleyGoalTime
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentValleyGoalPlayer =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_valley_goal")
                                ?: 0
                        val fieldName =
                            "${tvHomeCentreBackName.text}_${currentValleyGoalPlayer}_valley_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, valleyGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }

            val btnLongGoal = dialogView.findViewById<TextView>(R.id.button_goal_long)
            btnLongGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentLongGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentCentreBackLongGoal =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_long_goal")
                                ?: 0
                        val homeLongGoal = currentLongGoal + 1
                        val homeCentreBackLongGoal = currentCentreBackLongGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeLongGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_long_goal",
                                homeCentreBackLongGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val longGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_long_goal_times", longGoalTime
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentLongGoalPlayer =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_long_goal") ?: 0
                        val fieldName = "${tvHomeCentreBackName.text}_${currentLongGoalPlayer}_long_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, longGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
                    .addOnFailureListener { e ->
                        Log.w("Error", "Error updating document", e)
                    }
            }

            val btnHealedGoaled = dialogView.findViewById<TextView>(R.id.button_goal_healed)
            btnHealedGoaled.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentHealedGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentCentreBackHealedGoal = documentSnapshot.getLong("${tvHomeCentreBackName.text}_healed_goal") ?: 0
                        val homeHealedGoal = currentHealedGoal + 1
                        val homeCentreBackHealedGoal = currentCentreBackHealedGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeHealedGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_healed_goal",
                                homeCentreBackHealedGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healedGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_healed_goal_times",
                                healedGoalTime
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentHealedGoalPlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_healed_goal") ?: 0
                        val fieldName = "${tvHomeCentreBackName.text}_${currentHealedGoalPlayer}_healed_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, healedGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
            }

            val btnLobGoal = dialogView.findViewById<TextView>(R.id.button_goal_lob)
            btnLobGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentLobGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentCentreBackLobGoal = documentSnapshot.getLong("${tvHomeCentreBackName.text}_lob_goal") ?: 0
                        val homeLobGoal = currentLobGoal + 1
                        val homeCentreBackLobGoal = currentCentreBackLobGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeLobGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_lob_goal",
                                homeCentreBackLobGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val lobGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_lob_goal_times",
                                lobGoalTime
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentLobGoalPlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_lob_goal") ?: 0
                        val fieldName = "${tvHomeCentreBackName.text}_${currentLobGoalPlayer}_lob_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, lobGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
            }

            val btnFoulGoal = dialogView.findViewById<TextView>(R.id.button_goal_foul)
            btnFoulGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentFoulGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentCentreBackFoulGoal = documentSnapshot.getLong("${tvHomeCentreBackName.text}_foul_goal") ?: 0
                        val homeFoulGoal = currentFoulGoal + 1
                        val homeCentreBackFoulGoal = currentCentreBackFoulGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeFoulGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_foul_goal",
                                homeCentreBackFoulGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val foulGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_foul_goal_times",
                                foulGoalTime
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentFoulGoalPlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_foul_goal") ?: 0
                        val fieldName = "${tvHomeCentreBackName.text}_${currentFoulGoalPlayer}_foul_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, foulGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
            }

            val btnReverseGoal = dialogView.findViewById<TextView>(R.id.button_goal_reverse)
            btnReverseGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentReverseGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentCentreBackReverseGoal = documentSnapshot.getLong("${tvHomeCentreBackName.text}_reverse_goal") ?: 0
                        val homeReverseGoal = currentReverseGoal + 1
                        val homeCentreBackReverseGoal = currentCentreBackReverseGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeReverseGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_reverse_goal",
                                homeCentreBackReverseGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val reverseGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_reverse_goal_times",
                                reverseGoalTime
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentReverseGoalPlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_reverse_goal") ?: 0
                        val fieldName = "${tvHomeCentreBackName.text}_${currentReverseGoalPlayer}_reverse_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, reverseGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
            }

            val btnScissorGoal = dialogView.findViewById<TextView>(R.id.button_goal_Scissors)
            btnScissorGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentScissorGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentCentreBackScissorGoal = documentSnapshot.getLong("${tvHomeCentreBackName.text}_scissor_goal") ?: 0
                        val homeScissorGoal = currentScissorGoal + 1
                        val homeCentreBackScissorGoal = currentCentreBackScissorGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeScissorGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_scissor_goal",
                                homeCentreBackScissorGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val scissorGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_scissor_goal_times",
                                scissorGoalTime
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentScissorGoalPlayer =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_scissor_goal") ?: 0
                        val fieldName = "${tvHomeCentreBackName.text}_${currentScissorGoalPlayer}_scissor_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, scissorGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
            }

            val btnOtherGoal = dialogView.findViewById<TextView>(R.id.button_goal_other)
            btnOtherGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->
                        val currentOtherGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentCentreBackOtherGoal = documentSnapshot.getLong("${tvHomeCentreBackName.text}_other_goal") ?: 0
                        val homeOtherGoal = currentOtherGoal + 1
                        val homeCentreBackOtherGoal = currentCentreBackOtherGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeOtherGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_other_goal", homeCentreBackOtherGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val otherGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_other_goal_times", otherGoalTime
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        val currentOtherGoalPlayer = documentSnapshot.getLong("${tvHomeCentreBackName.text}_other_goal") ?: 0
                        val fieldName = "${tvHomeCentreBackName.text}_${currentOtherGoalPlayer}_other_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, otherGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }
                    }
            }
        }
    }

    private fun showHomeLeftBackDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_match_aksi_landscape, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()

        alertDialog.show()

        val homeLeftBackName = findViewById<TextView>(R.id.tv_goal_player3_name_home)
        val tvName = dialogView.findViewById<TextView>(R.id.tv_tendangan_goal)
        tvName.text = "Aksi Pemain: ${homeLeftBackName.text}"

        val btnShootFail = dialogView.findViewById<TextView>(R.id.button_shootFail)
        btnShootFail.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->

                    val currentShootFail = documentSnapshot.getLong("home_shoot_fail") ?: 0
                    val currentLeftBackShootFail = documentSnapshot.getLong("${tvHomeLeftBackName.text}_shoot_fail") ?: 0
                    val homeShootFail = currentShootFail + 1
                    val homeLeftBackShootFail = currentLeftBackShootFail + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_shoot_fail", homeShootFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_shoot_fail", homeLeftBackShootFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val shootFailTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_shoot_fail_times", shootFailTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val currentShootFailPlayer = documentSnapshot.getLong("${tvHomeLeftBackName.text}_shoot_fail") ?: 0
                    val fieldName = "${tvHomeLeftBackName.text}_${currentShootFailPlayer}_shoot_fail"
                    db.collection("matchStats").document(documentId)
                        .update(fieldName, shootFailTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnAssist = dialogView.findViewById<TextView>(R.id.button_assist)
        btnAssist.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->

                    val currentAssist = documentSnapshot.getLong("home_assist") ?: 0
                    val currentLeftBackAssist = documentSnapshot.getLong("${tvHomeLeftBackName.text}_assist") ?: 0
                    val homeAssist = currentAssist + 1
                    val homeLeftBackAssist = currentLeftBackAssist + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_assist", homeAssist)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_assist", homeLeftBackAssist)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val assistTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_assist_times", assistTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btn10mFail = dialogView.findViewById<TextView>(R.id.button_10mfail)
        btn10mFail.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->

                    val current10mFail = documentSnapshot.getLong("home_10m_fail") ?: 0
                    val currentLeftBack10mFail = documentSnapshot.getLong("${tvHomeLeftBackName.text}_10m_fail") ?: 0
                    val home10mFail = current10mFail + 1
                    val homeLeftBack10mFail = currentLeftBack10mFail + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_10m_fail", home10mFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_10m_fail", homeLeftBack10mFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val fail10mTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_10m_fail_times", fail10mTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btn10mgoal = dialogView.findViewById<TextView>(R.id.button_10mGoal)
        btn10mgoal.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->

                    val current10mGoal = documentSnapshot.getLong("home_10m_goal") ?: 0
                    val currentLeftBack10mGoal = documentSnapshot.getLong("${tvHomeLeftBackName.text}_10m_goal") ?: 0
                    val home10mGoal = current10mGoal + 1
                    val homeLeftBack10mGoal = currentLeftBack10mGoal + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_10m_goal", home10mGoal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_10m_goal", homeLeftBack10mGoal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val goal10mTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_10m_goal_times", goal10mTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnRedCard = dialogView.findViewById<TextView>(R.id.button_redCard)
        btnRedCard.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->

                    val currentRedCard = documentSnapshot.getLong("home_red_card") ?: 0
                    val currentLeftBackRedCard = documentSnapshot.getLong("${tvHomeLeftBackName.text}_red_card") ?: 0
                    val homeRedCard = currentRedCard + 1
                    val homeLeftBackRedCard = currentLeftBackRedCard + 1
                    val currentTeamRedCard = documentSnapshot.getLong("${tvTeamHome}_red_card") ?: 0
                    val teamHomeRedCard = currentTeamRedCard + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_red_card", homeRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_red_card", homeLeftBackRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome}_red_card", teamHomeRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e)}

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val redCardTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_red_card_times", redCardTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnYellowCard = dialogView.findViewById<TextView>(R.id.button_yellowCard)
        btnYellowCard.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->

                    val currentYellowCard = documentSnapshot.getLong("home_yellow_card") ?: 0
                    val currentLeftBackYellowCard = documentSnapshot.getLong("${tvHomeLeftBackName.text}_yellow_card") ?: 0
                    val homeYellowCard = currentYellowCard + 1
                    val homeLeftBackYellowCard = currentLeftBackYellowCard + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_yellow_card", homeYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_yellow_card", homeLeftBackYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                    val currentTeamYellowCard = documentSnapshot.getLong("${tvTeamHome}_yellow_card") ?: 0
                    val teamHomeYellowCard = currentTeamYellowCard + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome}_yellow_card", teamHomeYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e)}

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val yellowCardTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_yellow_card_times", yellowCardTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e)}
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnSteal = dialogView.findViewById<TextView>(R.id.button_steal)
        btnSteal.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->

                    val currentSteal = documentSnapshot.getLong("home_steal") ?: 0
                    val currentLeftBackSteal = documentSnapshot.getLong("${tvHomeLeftBackName.text}_steal") ?: 0
                    val homeSteal = currentSteal + 1
                    val homeLeftBackSteal = currentLeftBackSteal + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_steal", homeSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_steal", homeLeftBackSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnPenalty = dialogView.findViewById<TextView>(R.id.button_penalty)
        btnPenalty.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->
                    val currentPenalty = documentSnapshot.getLong("home_penalty") ?: 0
                    val currentLeftBackPenalty = documentSnapshot.getLong("${tvHomeLeftBackName.text}_penalty") ?: 0
                    val homePenalty = currentPenalty + 1
                    val homeLeftBackPenalty = currentLeftBackPenalty + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_penalty", homePenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_penalty", homeLeftBackPenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val penaltyTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_penalty_times", penaltyTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error","Error updating document", e) }
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnOffside = dialogView.findViewById<TextView>(R.id.button_offside)
        btnOffside.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener{ documentSnapshot ->
                    val currentOffside = documentSnapshot.getLong("home_offside") ?: 0
                    val currentLeftBackOffside = documentSnapshot.getLong("${tvHomeLeftBackName.text}_offside") ?: 0
                    val homeOffside = currentOffside + 1
                    val homeLeftBackOffside = currentLeftBackOffside + 1

                    db.collection("matchStats").document(documentId)
                        .update("home_offside", homeOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_offside", homeLeftBackOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val offsideTime = tvTimer.text.toString()
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeLeftBackName.text}_offside_times", offsideTime)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e -> Log.w("Error","Error updating document", e) }
                }
                .addOnFailureListener { e ->
                    Log.w("Error", "Error updating document", e)
                }
        }

        val btnShootGoal = dialogView.findViewById<TextView>(R.id.button_shootGoal)
        btnShootGoal.setOnClickListener(){
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_match_goal, null)
            dialogBuilder.setView(dialogView)
            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            val btnShootGoal = dialogView.findViewById<TextView>(R.id.button_goal_shoot)
            btnShootGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val currentShootGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentLeftBackShootGoal = documentSnapshot.getLong("${tvHomeLeftBackName.text}_shoot_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeLeftBackShootGoal = currentLeftBackShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_shoot_goal", homeLeftBackShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val shootGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_shoot_goal_times", shootGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentShootGoalPlayer = documentSnapshot.getLong("${tvHomeLeftBackName.text}_shoot_goal") ?: 0
                        val fieldName = "${tvHomeLeftBackName.text}_${currentShootGoalPlayer}_shoot_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, shootGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
            }

            val btnHealGoal = dialogView.findViewById<TextView>(R.id.button_goal_Heal)
            btnHealGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val currentHealGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentLeftBackHealGoal = documentSnapshot.getLong("${tvHomeLeftBackName.text}_heal_goal") ?: 0
                        val homeHealGoal = currentHealGoal + 1
                        val homeLeftBackHealGoal = currentLeftBackHealGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeHealGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_heal_goal", homeLeftBackHealGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_heal_goal_times", healGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentHealGoalPlayer = documentSnapshot.getLong("${tvHomeLeftBackName.text}_heal_goal") ?: 0
                        val fieldName = "${tvHomeLeftBackName.text}_${currentHealGoalPlayer}_heal_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, healGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }
                    }
            }

            val btnValleyGoal = dialogView.findViewById<TextView>(R.id.button_goal_valley)
            btnValleyGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val currentValleyGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentLeftBackValleyGoal = documentSnapshot.getLong("${tvHomeLeftBackName.text}_valley_goal") ?: 0
                        val homeValleyGoal = currentValleyGoal + 1
                        val homeLeftBackValleyGoal = currentLeftBackValleyGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeValleyGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_valley_goal", homeLeftBackValleyGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val valleyGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_valley_goal_times", valleyGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        val currentValleyGoalPlayer = documentSnapshot.getLong("${tvHomeLeftBackName.text}_valley_goal") ?: 0
                        val fieldName = "${tvHomeLeftBackName.text}_${currentValleyGoalPlayer}_valley_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, valleyGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }
                    }
            }

            val btnLongGoal = dialogView.findViewById<TextView>(R.id.button_goal_long)
            btnLongGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val currentLongGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentLeftBackLongGoal = documentSnapshot.getLong("${tvHomeLeftBackName.text}_long_goal") ?: 0
                        val homeLongGoal = currentLongGoal + 1
                        val homeLeftBackLongGoal = currentLeftBackLongGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeLongGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_long_goal", homeLeftBackLongGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val longGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_long_goal_times", longGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        val currentLongGoalPlayer = documentSnapshot.getLong("${tvHomeLeftBackName.text}_long_goal") ?: 0
                        val fieldName = "${tvHomeLeftBackName.text}_${currentLongGoalPlayer}_long_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, longGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }
                    }
            }

            val btnHealedGoal = dialogView.findViewById<TextView>(R.id.button_goal_healed)
            btnHealedGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val currentHealedGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentLeftBackHealedGoal = documentSnapshot.getLong("${tvHomeLeftBackName.text}_healed_goal") ?: 0
                        val homeHealedGoal = currentHealedGoal + 1
                        val homeLeftBackHealedGoal = currentLeftBackHealedGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeHealedGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_healed_goal", homeLeftBackHealedGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healedGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_healed_goal_times", healedGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        val currentHealedGoalPlayer = documentSnapshot.getLong("${tvHomeLeftBackName.text}_healed_goal") ?: 0
                        val fieldName = "${tvHomeLeftBackName.text}_${currentHealedGoalPlayer}_healed_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, healedGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }
                    }
            }

            val btnLobGoal = dialogView.findViewById<TextView>(R.id.button_goal_lob)
            btnLobGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val currentLobGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentLeftBackLobGoal = documentSnapshot.getLong("${tvHomeLeftBackName.text}_lob_goal") ?: 0
                        val homeLobGoal = currentLobGoal + 1
                        val homeLeftBackLobGoal = currentLeftBackLobGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeLobGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_lob_goal", homeLeftBackLobGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val lobGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_lob_goal_times", lobGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        val currentLobGoalPlayer = documentSnapshot.getLong("${tvHomeLeftBackName.text}_lob_goal") ?: 0
                        val fieldName = "${tvHomeLeftBackName.text}_${currentLobGoalPlayer}_lob_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, lobGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }
                    }
            }

            val btnFoulGoal = dialogView.findViewById<TextView>(R.id.button_goal_foul)
            btnFoulGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val currentFoulGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentLeftBackFoulGoal = documentSnapshot.getLong("${tvHomeLeftBackName.text}_foul_goal") ?: 0
                        val homeFoulGoal = currentFoulGoal + 1
                        val homeLeftBackFoulGoal = currentLeftBackFoulGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeFoulGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_foul_goal", homeLeftBackFoulGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val foulGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_foul_goal_times", foulGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        val currentFoulGoalPlayer = documentSnapshot.getLong("${tvHomeLeftBackName.text}_foul_goal") ?: 0
                        val fieldName = "${tvHomeLeftBackName.text}_${currentFoulGoalPlayer}_foul_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, foulGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }
                    }
            }

            val btnReverseGoal = dialogView.findViewById<TextView>(R.id.button_goal_reverse)
            btnReverseGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val currentReverseGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentLeftBackReverseGoal = documentSnapshot.getLong("${tvHomeLeftBackName.text}_reverse_goal") ?: 0
                        val homeReverseGoal = currentReverseGoal + 1
                        val homeLeftBackReverseGoal = currentLeftBackReverseGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeReverseGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_reverse_goal", homeLeftBackReverseGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e -> Log.w("Error", "Error updating document", e) }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val reverseGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_reverse_goal_times", reverseGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentReverseGoalPlayer = documentSnapshot.getLong("${tvHomeLeftBackName.text}_reverse_goal") ?: 0
                        val fieldName = "${tvHomeLeftBackName.text}_${currentReverseGoalPlayer}_reverse_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, reverseGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
            }

            val btnScissorsGoal = dialogView.findViewById<TextView>(R.id.button_goal_Scissors)
            btnScissorsGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val currentScissorsGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentLeftBackScissorsGoal = documentSnapshot.getLong("${tvHomeLeftBackName.text}_scissors_goal") ?: 0
                        val homeScissorsGoal = currentScissorsGoal + 1
                        val homeLeftBackScissorsGoal = currentLeftBackScissorsGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeScissorsGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_scissors_goal", homeLeftBackScissorsGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val scissorsGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_scissors_goal_times", scissorsGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentScissorsGoalPlayer = documentSnapshot.getLong("${tvHomeLeftBackName.text}_scissors_goal") ?: 0
                        val fieldName = "${tvHomeLeftBackName.text}_${currentScissorsGoalPlayer}_scissors_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, scissorsGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
            }

            val btnOtherGoal = dialogView.findViewById<TextView>(R.id.button_goal_other)
            btnOtherGoal.setOnClickListener(){
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener{ documentSnapshot ->

                        val currentOtherGoal = documentSnapshot.getLong("home_goal") ?: 0
                        val currentLeftBackOtherGoal = documentSnapshot.getLong("${tvHomeLeftBackName.text}_other_goal") ?: 0
                        val homeOtherGoal = currentOtherGoal + 1
                        val homeLeftBackOtherGoal = currentLeftBackOtherGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("home_goal", homeOtherGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_other_goal", homeLeftBackOtherGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val otherGoalTime = tvTimer.text.toString()
                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_other_goal_times", otherGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val currentOtherGoalPlayer = documentSnapshot.getLong("${tvHomeLeftBackName.text}_other_goal") ?: 0
                        val fieldName = "${tvHomeLeftBackName.text}_${currentOtherGoalPlayer}_other_goal"
                        db.collection("matchStats").document(documentId)
                            .update(fieldName, otherGoalTime)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
            }

        }

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

                   val gkConstraint = findViewById<ConstraintLayout>(R.id.gk_constraint_home)
                    gkConstraint.setOnClickListener(){
                        showGoalKeeperDialog()
                    }

                    val namaHomeGoalKeeper = "${tvHomeGoalKeeperName.text}"
                    val documentId = documentId
                    db.collection("matchStats").document(documentId)
                        .update("home_goal_keeper", namaHomeGoalKeeper)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
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

                    val centreBackConstraint = findViewById<ConstraintLayout>(R.id.gk_constraint_home_centreback)
                    centreBackConstraint.setOnClickListener(){
                        showHomeCentreBackDialog()
                    }

                    val namaHomeCentreBack = "${tvHomeCentreBackName.text}"
                    val documentId = documentId
                    db.collection("matchStats").document(documentId)
                        .update("home_centre_back", namaHomeCentreBack)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

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

                    val leftBackConstraint = findViewById<ConstraintLayout>(R.id.gk_constraint_home_lefback)
                    leftBackConstraint.setOnClickListener(){
                        showHomeLeftBackDialog()
                    }

                    val namaHomeLeftBack = "${tvHomeLeftBackName.text}"
                    val documentId = documentId
                    db.collection("matchStats").document(documentId)
                        .update("home_left_back", namaHomeLeftBack)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
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