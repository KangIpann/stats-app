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
import org.w3c.dom.Text
import kotlin.math.log

class MatchBerjalan : AppCompatActivity() {

    private lateinit var documentId: String
    private lateinit var db: FirebaseFirestore
    private lateinit var matchId: String
    private lateinit var teamHomeDocId: String
    private lateinit var teamAwayDocId: String
    private lateinit var teamHomeGoalKeeper: String
    private lateinit var teamHomeCentreBack: String
    private lateinit var teamHomeLeftBack: String
    private lateinit var teamHomeRightBack: String
    private lateinit var teamHomeDefensiveMidfielder: String
    private lateinit var teamHomeCentralMidfielder: String
    private lateinit var teamHomeAttackingMidfielder: String
    private lateinit var teamHomeLeftWinger: String
    private lateinit var teamHomeRightWinger: String
    private lateinit var teamHomeCentreForward: String
    private lateinit var teamHomeSecondStriker: String
    private lateinit var teamAwayGoalKeeper: String
    private lateinit var teamAwayCentreBack: String
    private lateinit var teamAwayLeftBack: String
    private lateinit var teamAwayRightBack: String
    private lateinit var teamAwayDefensiveMidfielder: String
    private lateinit var teamAwayCentralMidfielder: String
    private lateinit var teamAwayAttackingMidfielder: String
    private lateinit var teamAwayLeftWinger: String
    private lateinit var teamAwayRightWinger: String
    private lateinit var teamAwayCentreForward: String
    private lateinit var teamAwaySecondStriker: String

    //variabel global untuk team Home
    private lateinit var matchDuration: String
    private lateinit var matchName: String
    private lateinit var matchDate: String
    private lateinit var leagueName: String
    private lateinit var homeTeam: String
    private lateinit var awayTeam: String
    private lateinit var tvLeagueName: TextView
    private lateinit var tvLeagueDate: TextView
    private lateinit var tvTeamHome: TextView
    private lateinit var tvTeamAway: TextView
    private lateinit var tvTimer: TextView
    private lateinit var tvHomeGoalKeeperName: TextView
    private lateinit var tvHomeCentreBackName: TextView
    private lateinit var tvHomeLeftBackName: TextView
    private lateinit var tvHomeRightBackName: TextView
    private lateinit var tvHomeDefensiveMidfielderName: TextView
    private lateinit var tvHomeCentralMidfielderName: TextView
    private lateinit var tvHomeAttackingMidfielderName: TextView
    private lateinit var tvHomeLeftWingerName: TextView
    private lateinit var tvHomeRightWingerName: TextView
    private lateinit var tvHomeCentreForwardName: TextView
    private lateinit var tvHomeSecondStrikerName: TextView

    //variabel global untuk team Away
    private lateinit var tvAwayGoalKeeperName: TextView
    private lateinit var tvAwayCentreBackName: TextView
    private lateinit var tvAwayLeftBackName: TextView
    private lateinit var tvAwayRightBackName: TextView
    private lateinit var tvAwayDefensiveMidfielderName: TextView
    private lateinit var tvAwayCentralMidfielderName: TextView
    private lateinit var tvAwayAttackingMidfielderName: TextView
    private lateinit var tvAwayLeftWingerName: TextView
    private lateinit var tvAwayRightWingerName: TextView
    private lateinit var tvAwayCentreForwardName: TextView
    private lateinit var tvAwaySecondStrikerName: TextView


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

    private fun setDatabase() {
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
        btnShootGoal.setOnClickListener() {
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = LayoutInflater.from(this)
            val dialogView = inflater.inflate(R.layout.dialog_match_goal, null)
            dialogBuilder.setView(dialogView)
            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            val shootGoal = dialogView.findViewById<TextView>(R.id.button_goal_shoot)
            shootGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val actionGoal = shootGoal.text.toString()
                        val currentShootGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentGoalKeeperShootGoal =
                            documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${shootGoal.text}_goal")
                                ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal",
                                homeGoalKeeperShootGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()

                        val shotGoalData = hashMapOf(
                            "time" to goalTime,
                            "scorer" to "${tvHomeGoalKeeperName.text}",
                            "action" to "shoot",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(shotGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times",
                                FieldValue.arrayUnion(goalTime)
                            )
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
            healGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val actionGoal = healGoal.text.toString()
                        val currentShootGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentGoalKeeperShootGoal =
                            documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${healGoal.text}_goal")
                                ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal",
                                homeGoalKeeperShootGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()

                        val healGoalData = hashMapOf(
                            "time" to goalTime,
                            "scorer" to "${tvHomeGoalKeeperName.text}",
                            "action" to "heal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(healGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times",
                                FieldValue.arrayUnion(goalTime)
                            )
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
            valleyGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val actionGoal = valleyGoal.text.toString()
                        val currentShootGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentGoalKeeperShootGoal =
                            documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${valleyGoal.text}_goal")
                                ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal",
                                homeGoalKeeperShootGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()

                        val valleyGoalData = hashMapOf(
                            "time" to goalTime,
                            "scorer" to "${tvHomeGoalKeeperName.text}",
                            "action" to "valley",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(valleyGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times",
                                FieldValue.arrayUnion(goalTime)
                            )
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
            longGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val actionGoal = longGoal.text.toString()
                        val currentShootGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentGoalKeeperShootGoal =
                            documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${longGoal.text}_goal")
                                ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal",
                                homeGoalKeeperShootGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()

                        val longGoalData = hashMapOf(
                            "time" to goalTime,
                            "scorer" to "${tvHomeGoalKeeperName.text}",
                            "action" to "long",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(longGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times",
                                FieldValue.arrayUnion(goalTime)
                            )
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
            healedGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val actionGoal = healedGoal.text.toString()
                        val currentShootGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentGoalKeeperShootGoal =
                            documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${healedGoal.text}_goal")
                                ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal",
                                homeGoalKeeperShootGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()

                        val healedGoalData = hashMapOf(
                            "time" to goalTime,
                            "scorer" to "${tvHomeGoalKeeperName.text}",
                            "action" to "healed",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(healedGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times",
                                FieldValue.arrayUnion(goalTime)
                            )
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
            lobGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val actionGoal = lobGoal.text.toString()
                        val currentShootGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentGoalKeeperShootGoal =
                            documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${lobGoal.text}_goal")
                                ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal",
                                homeGoalKeeperShootGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()

                        val lobGoalData = hashMapOf(
                            "time" to goalTime,
                            "scorer" to "${tvHomeGoalKeeperName.text}",
                            "action" to "lob",
                        )

                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(lobGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times",
                                FieldValue.arrayUnion(goalTime)
                            )
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
            foulGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val actionGoal = foulGoal.text.toString()
                        val currentShootGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentGoalKeeperShootGoal =
                            documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${foulGoal.text}_goal")
                                ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal",
                                homeGoalKeeperShootGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()

                        val foulGoalData = hashMapOf(
                            "time" to goalTime,
                            "scorer" to "${tvHomeGoalKeeperName.text}",
                            "action" to "foul",
                        )

                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(foulGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times",
                                FieldValue.arrayUnion(goalTime)
                            )
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
            reverseGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val actionGoal = reverseGoal.text.toString()
                        val currentShootGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentGoalKeeperShootGoal =
                            documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${reverseGoal.text}_goal")
                                ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal",
                                homeGoalKeeperShootGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        val reverseGoalData = hashMapOf(
                            "time" to goalTime,
                            "scorer" to "${tvHomeGoalKeeperName.text}",
                            "action" to "reverse",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(reverseGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times",
                                FieldValue.arrayUnion(goalTime)
                            )
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
            scissorsGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val actionGoal = scissorsGoal.text.toString()
                        val currentShootGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentGoalKeeperShootGoal =
                            documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${scissorsGoal.text}_goal")
                                ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal",
                                homeGoalKeeperShootGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        val scissorsGoalData = hashMapOf(
                            "time" to goalTime,
                            "scorer" to "${tvHomeGoalKeeperName.text}",
                            "action" to "scissors",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(scissorsGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times",
                                FieldValue.arrayUnion(goalTime)
                            )
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
            otherGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val actionGoal = otherGoal.text.toString()
                        val currentShootGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentGoalKeeperShootGoal =
                            documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_${otherGoal.text}_goal")
                                ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeGoalKeeperShootGoal = currentGoalKeeperShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal",
                                homeGoalKeeperShootGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        val otherGoalData = hashMapOf(
                            "time" to goalTime,
                            "scorer" to "${tvHomeGoalKeeperName.text}",
                            "action" to "other",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(otherGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeGoalKeeperName.text}_${actionGoal}_goal_times",
                                FieldValue.arrayUnion(goalTime)
                            )
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
        btnShootFail.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentShootFail =
                        documentSnapshot.getLong("${tvTeamHome.text}_shoot_fail") ?: 0
                    val currentGoalKeeperShootFail =
                        documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_shoot_fail") ?: 0
                    val homeShootFail = currentShootFail + 1
                    val homeGoalKeeperShootFail = currentGoalKeeperShootFail + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_shoot_fail", homeShootFail)
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
                    val shootFailData = hashMapOf(
                        "time" to shootFailTime,
                        "player" to "${tvHomeGoalKeeperName.text}",
                        "action" to "shoot_fail",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("shoot_fail")
                        .add(shootFailData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_shoot_fail_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeGoalKeeperName.text}_shoot_fail_times",
                            FieldValue.arrayUnion(shootFailTime)
                        )
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
        btnAssist.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentAssist = documentSnapshot.getLong("${tvTeamHome.text}_assist") ?: 0
                    val currentGoalKeeperAssist =
                        documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_assist") ?: 0
                    val homeAssist = currentAssist + 1
                    val homeGoalKeeperAssist = currentGoalKeeperAssist + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_assist", homeAssist)
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
                    val assistData = hashMapOf(
                        "time" to assistTime,
                        "player" to "${tvHomeGoalKeeperName.text}",
                        "action" to "assist",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("assist")
                        .add(assistData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_assist_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeGoalKeeperName.text}_assist_times",
                            FieldValue.arrayUnion(assistTime)
                        )
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

        val btn10mfail = dialogView.findViewById<TextView>(R.id.button_10mfail)
        btn10mfail.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val current10mfail =
                        documentSnapshot.getLong("${tvTeamHome.text}_10m_fail") ?: 0
                    val currentGoalKeeper10mfail =
                        documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_10m_fail") ?: 0
                    val home10mfail = current10mfail + 1
                    val homeGoalKeeper10mfail = currentGoalKeeper10mfail + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_10m_fail", home10mfail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    //tambahkan field ${tvTeamHome.text}_10m_fail


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
                    val tenMeterFailData = hashMapOf(
                        "time" to tenMeterFailTime,
                        "player" to "${tvHomeGoalKeeperName.text}",
                        "action" to "10m_fail",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("10m_fail")
                        .add(tenMeterFailData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_10m_fail_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeGoalKeeperName.text}_10m_fail_times",
                            FieldValue.arrayUnion(tenMeterFailTime)
                        )
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

        val btn10mgoal = dialogView.findViewById<TextView>(R.id.button_10mGoal)
        btn10mgoal.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val current10mgoal =
                        documentSnapshot.getLong("${tvTeamHome.text}_10m_goal") ?: 0
                    val currentGoalKeeper10mgoal =
                        documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_10m_goal") ?: 0
                    val home10mgoal = current10mgoal + 1
                    val homeGoalKeeper10mgoal = currentGoalKeeper10mgoal + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_10m_goal", home10mgoal)
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
                    val tenMeterGoalData = hashMapOf(
                        "time" to tenMeterGoalTime,
                        "player" to "${tvHomeGoalKeeperName.text}",
                        "action" to "10m_goal",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("10m_goal")
                        .add(tenMeterGoalData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_10m_goal_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeGoalKeeperName.text}_10m_goal_times",
                            FieldValue.arrayUnion(tenMeterGoalTime)
                        )
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
        btnRedCard.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentRedCard =
                        documentSnapshot.getLong("${tvTeamHome.text}_red_card") ?: 0
                    val currentGoalKeeperRedCard =
                        documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_red_card") ?: 0
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
                    val redCardData = hashMapOf(
                        "time" to redCardTime,
                        "player" to "${tvHomeGoalKeeperName.text}",
                        "action" to "red_card",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("violations_card")
                        .add(redCardData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_red_card_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeGoalKeeperName.text}_red_card_times",
                            FieldValue.arrayUnion(redCardTime)
                        )
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
        btnYellowCard.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentYellowCard = it.getLong("${tvTeamHome.text}_yellow_card") ?: 0
                    val currentGoalKeeperYellowCard = it.getLong("${tvHomeGoalKeeperName.text}_yellow_card") ?: 0
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
                    val yellowCardData = hashMapOf(
                        "time" to yellowCardTime,
                        "player" to "${tvHomeGoalKeeperName.text}",
                        "action" to "yellow_card",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("violations_card")
                        .add(yellowCardData)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        }

                    //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_yellow_card_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update("${tvHomeGoalKeeperName.text}_yellow_card_times", FieldValue.arrayUnion(yellowCardTime))
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
        btnSteal.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener(){

                    val currentSteal = it.getLong("${tvTeamHome.text}_steal") ?: 0
                    val currentGoalKeeperSteal = it.getLong("${tvHomeGoalKeeperName.text}_steal") ?: 0
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
                    val stealData = hashMapOf(
                        "time" to stealTime,
                        "player" to "${tvHomeGoalKeeperName.text}",
                        "action" to "steal",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("steal")
                        .add(stealData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_steal_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeGoalKeeperName.text}_steal_times",
                            FieldValue.arrayUnion(stealTime)
                        )
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
        btnPenalty.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener() {

                    val currentPenalty = it.getLong("${tvTeamHome.text}_penalty") ?: 0
                    val currentGoalKeeperPenalty = it.getLong("${tvHomeGoalKeeperName.text}_penalty") ?: 0
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
                    val penaltyData = hashMapOf(
                        "time" to penaltyTime,
                        "player" to "${tvHomeGoalKeeperName.text}",
                        "action" to "penalty",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("penalty")
                        .add(penaltyData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_penalty_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeGoalKeeperName.text}_penalty_times",
                            FieldValue.arrayUnion(penaltyTime)
                        )
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
        btnOffside.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentOffside = documentSnapshot.getLong("${tvTeamHome.text}_offside") ?: 0
                    val currentGoalKeeperOffside =
                        documentSnapshot.getLong("${tvHomeGoalKeeperName.text}_offside") ?: 0
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
                    val offsideData = hashMapOf(
                        "time" to offsideTime,
                        "player" to "${tvHomeGoalKeeperName.text}",
                        "action" to "offside",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("offside")
                        .add(offsideData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeGoalKeeperName.text}_offside_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeGoalKeeperName.text}_offside_times",
                            FieldValue.arrayUnion(offsideTime)
                        )
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
        gkConstraint.setOnClickListener() {
            alertDialog.show()
        }
        tvHomeGoalKeeperName = findViewById<TextView>(R.id.tv_goal_player1_name_home)
        tvHomeGoalKeeperName.setOnClickListener() {
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
        btnShootFail.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentShootFail =
                        documentSnapshot.getLong("${tvTeamHome.text}_shoot_fail") ?: 0
                    val currentCentreBackShootFail =
                        documentSnapshot.getLong("${tvHomeCentreBackName.text}_shoot_fail") ?: 0
                    val homeShootFail = currentShootFail + 1
                    val homeCentreBackShootFail = currentCentreBackShootFail + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_shoot_fail", homeShootFail)
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
                    val shootFailData = hashMapOf(
                        "time" to shootFailTime,
                        "player" to "${tvHomeCentreBackName.text}",
                        "action" to "shoot_fail",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("shoot_fail")
                        .add(shootFailData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_shoot_fail_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeCentreBackName.text}_shoot_fail_times",
                            FieldValue.arrayUnion(shootFailTime)
                        )
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
        btnAssist.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentAssist = documentSnapshot.getLong("${tvTeamHome.text}_assist") ?: 0
                    val currentCentreBackAssist =
                        documentSnapshot.getLong("${tvHomeCentreBackName.text}_assist") ?: 0
                    val homeAssist = currentAssist + 1
                    val homeCentreBackAssist = currentCentreBackAssist + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_assist", homeAssist)
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
                    val assistData = hashMapOf(
                        "time" to assistTime,
                        "player" to "${tvHomeCentreBackName.text}",
                        "action" to "assist",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("assist")
                        .add(assistData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_assist_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeCentreBackName.text}_assist_times",
                            FieldValue.arrayUnion(assistTime)
                        )
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

        val btn10mfail = dialogView.findViewById<TextView>(R.id.button_10mfail)
        btn10mfail.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val current10mfail =
                        documentSnapshot.getLong("${tvTeamHome.text}_10m_fail") ?: 0
                    val currentCentreBack10mfail =
                        documentSnapshot.getLong("${tvHomeCentreBackName.text}_10m_fail") ?: 0
                    val home10mfail = current10mfail + 1
                    val homeCentreBack10mfail = currentCentreBack10mfail + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_10m_fail", home10mfail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
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
                    val tenMeterFailData = hashMapOf(
                        "time" to tenMeterFailTime,
                        "player" to "${tvHomeCentreBackName.text}",
                        "action" to "10m_fail",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("10m_fail")
                        .add(tenMeterFailData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_10m_fail_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeCentreBackName.text}_10m_fail_times",
                            FieldValue.arrayUnion(tenMeterFailTime)
                        )
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

        val btn10mgoal = dialogView.findViewById<TextView>(R.id.button_10mGoal)
        btn10mgoal.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val current10mgoal =
                        documentSnapshot.getLong("${tvTeamHome.text}_10m_goal") ?: 0
                    val currentCentreBack10mgoal =
                        documentSnapshot.getLong("${tvHomeCentreBackName.text}_10m_goal") ?: 0
                    val home10mgoal = current10mgoal + 1
                    val homeCentreBack10mgoal = currentCentreBack10mgoal + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_10m_goal", home10mgoal)
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
                    val tenMeterGoalData = hashMapOf(
                        "time" to tenMeterGoalTime,
                        "player" to "${tvHomeCentreBackName.text}",
                        "action" to "10m_goal",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("10m_goal")
                        .add(tenMeterGoalData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_10m_goal_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeCentreBackName.text}_10m_goal_times",
                            FieldValue.arrayUnion(tenMeterGoalTime)
                        )
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
        btnRedCard.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener(){

                    val currentRedCard = it.getLong("${tvTeamHome.text}_red_card") ?: 0
                    val currentCentreBackRedCard = it.getLong("${tvHomeCentreBackName.text}_red_card") ?: 0
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
                    val redCardData = hashMapOf(
                        "time" to redCardTime,
                        "player" to "${tvHomeCentreBackName.text}",
                        "action" to "red_card",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("violations_card")
                        .add(redCardData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_red_card_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeCentreBackName.text}_red_card_times",
                            FieldValue.arrayUnion(redCardTime)
                        )
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
        btnYellowCard.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener(){

                    val currentYellowCard = it.getLong("${tvTeamHome.text}_yellow_card") ?: 0
                    val currentCentreBackYellowCard = it.getLong("${tvHomeCentreBackName.text}_yellow_card") ?: 0
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
                        .update(
                            "${tvHomeCentreBackName.text}_yellow_card",
                            homeCentreBackYellowCard
                        )
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val yellowCardTime = tvTimer.text.toString()
                    val yellowCardData = hashMapOf(
                        "time" to yellowCardTime,
                        "player" to "${tvHomeCentreBackName.text}",
                        "action" to "yellow_card",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("violations_card")
                        .add(yellowCardData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_yellow_card_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeCentreBackName.text}_yellow_card_times",
                            FieldValue.arrayUnion(yellowCardTime)
                        )
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
        btnSteal.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentSteal = documentSnapshot.getLong("${tvTeamHome.text}_steal") ?: 0
                    val currentCentreBackSteal =
                        documentSnapshot.getLong("${tvHomeCentreBackName.text}_steal") ?: 0
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
                    val stealData = hashMapOf(
                        "time" to stealTime,
                        "player" to "${tvHomeCentreBackName.text}",
                        "action" to "steal",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("steal")
                        .add(stealData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_steal_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeCentreBackName.text}_steal_times",
                            FieldValue.arrayUnion(stealTime)
                        )
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
        btnPenalty.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentPenalty = documentSnapshot.getLong("${tvTeamHome.text}_penalty") ?: 0
                    val currentCentreBackPenalty =
                        documentSnapshot.getLong("${tvHomeCentreBackName.text}_penalty") ?: 0
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
                    val penaltyData = hashMapOf(
                        "time" to penaltyTime,
                        "player" to "${tvHomeCentreBackName.text}",
                        "action" to "penalty",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("penalty")
                        .add(penaltyData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_penalty_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeCentreBackName.text}_penalty_times",
                            FieldValue.arrayUnion(penaltyTime)
                        )
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
        btnOffside.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentOffside = documentSnapshot.getLong("${tvTeamHome.text}_offside") ?: 0
                    val currentCentreBackOffside =
                        documentSnapshot.getLong("${tvHomeCentreBackName.text}_offside") ?: 0
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
                    val offsideData = hashMapOf(
                        "time" to offsideTime,
                        "player" to "${tvHomeCentreBackName.text}",
                        "action" to "offside",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("offside")
                        .add(offsideData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }

                    //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_offside_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeCentreBackName.text}_offside_times",
                            FieldValue.arrayUnion(offsideTime)
                        )
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
        btnGoal.setOnClickListener() {
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_match_goal, null)
            dialogBuilder.setView(dialogView)
            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            val btnGoalShoot = dialogView.findViewById<TextView>(R.id.button_goal_shoot)
            btnGoalShoot.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentShootGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentreBackShootGoal =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_shoot_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeCentreBackShootGoal = currentCentreBackShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_shoot_goal",
                                homeCentreBackShootGoal
                            )
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

                        val shootGoalData = hashMapOf(
                            "time" to shootGoalTime,
                            "player" to "${tvHomeCentreBackName.text}",
                            "action" to "shoot_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(shootGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_shoot_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_shoot_goal_times",
                                FieldValue.arrayUnion(shootGoalTime)
                            )
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

            val btnHealGoal = dialogView.findViewById<TextView>(R.id.button_goal_Heal)
            btnHealGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentHealGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentreBackHealGoal =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_heal_goal") ?: 0
                        val homeHealGoal = currentHealGoal + 1
                        val homeCentreBackHealGoal = currentCentreBackHealGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeHealGoal)
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

                        val healGoalData = hashMapOf(
                            "time" to healGoalTime,
                            "player" to "${tvHomeCentreBackName.text}",
                            "action" to "heal_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(healGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_heal_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_heal_goal_times",
                                FieldValue.arrayUnion(healGoalTime)
                            )
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

            val btnValleyGoal = dialogView.findViewById<TextView>(R.id.button_goal_valley)
            btnValleyGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentValleyGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentreBackValleyGoal =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_valley_goal")
                                ?: 0
                        val homeValleyGoal = currentValleyGoal + 1
                        val homeCentreBackValleyGoal = currentCentreBackValleyGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeValleyGoal)
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
                        val valleyGoalData = hashMapOf(
                            "time" to valleyGoalTime,
                            "player" to "${tvHomeCentreBackName.text}",
                            "action" to "valley_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(valleyGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_valley_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_valley_goal_times",
                                FieldValue.arrayUnion(valleyGoalTime)
                            )
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

            val btnLongGoal = dialogView.findViewById<TextView>(R.id.button_goal_long)
            btnLongGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentLongGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentreBackLongGoal =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_long_goal") ?: 0
                        val homeLongGoal = currentLongGoal + 1
                        val homeCentreBackLongGoal = currentCentreBackLongGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeLongGoal)
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
                        val longGoalData = hashMapOf(
                            "time" to longGoalTime,
                            "player" to "${tvHomeCentreBackName.text}",
                            "action" to "long_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(longGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_long_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_long_goal_times",
                                FieldValue.arrayUnion(longGoalTime)
                            )
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

            val btnHealedGoaled = dialogView.findViewById<TextView>(R.id.button_goal_healed)
            btnHealedGoaled.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentHealedGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentreBackHealedGoal =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_healed_goal")
                                ?: 0
                        val homeHealedGoal = currentHealedGoal + 1
                        val homeCentreBackHealedGoal = currentCentreBackHealedGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeHealedGoal)
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
                        val healedGoalData = hashMapOf(
                            "time" to healedGoalTime,
                            "player" to "${tvHomeCentreBackName.text}",
                            "action" to "healed_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(healedGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_healed_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_healed_goal_times",
                                FieldValue.arrayUnion(healedGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
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

                        val currentLobGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentreBackLobGoal =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_lob_goal") ?: 0
                        val homeLobGoal = currentLobGoal + 1
                        val homeCentreBackLobGoal = currentCentreBackLobGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeLobGoal)
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
                        val lobGoalData = hashMapOf(
                            "time" to lobGoalTime,
                            "player" to "${tvHomeCentreBackName.text}",
                            "action" to "lob_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(lobGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_lob_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_lob_goal_times",
                                FieldValue.arrayUnion(lobGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
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

                        val currentFoulGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentreBackFoulGoal =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_foul_goal") ?: 0
                        val homeFoulGoal = currentFoulGoal + 1
                        val homeCentreBackFoulGoal = currentCentreBackFoulGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeFoulGoal)
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
                        val foulGoalData = hashMapOf(
                            "time" to foulGoalTime,
                            "player" to "${tvHomeCentreBackName.text}",
                            "action" to "foul_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(foulGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_foul_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_foul_goal_times",
                                FieldValue.arrayUnion(foulGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
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

                        val currentReverseGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentreBackReverseGoal =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_reverse_goal")
                                ?: 0
                        val homeReverseGoal = currentReverseGoal + 1
                        val homeCentreBackReverseGoal = currentCentreBackReverseGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeReverseGoal)
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
                        val reverseGoalData = hashMapOf(
                            "time" to reverseGoalTime,
                            "player" to "${tvHomeCentreBackName.text}",
                            "action" to "reverse_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(reverseGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_reverse_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_reverse_goal_times",
                                FieldValue.arrayUnion(reverseGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
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

                        val currentScissorGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentreBackScissorGoal =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_scissor_goal")
                                ?: 0
                        val homeScissorGoal = currentScissorGoal + 1
                        val homeCentreBackScissorGoal = currentCentreBackScissorGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeScissorGoal)
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
                        val scissorGoalData = hashMapOf(
                            "time" to scissorGoalTime,
                            "player" to "${tvHomeCentreBackName.text}",
                            "action" to "scissor_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(scissorGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_scissor_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_scissor_goal_times",
                                FieldValue.arrayUnion(scissorGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
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
                        val currentOtherGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentreBackOtherGoal =
                            documentSnapshot.getLong("${tvHomeCentreBackName.text}_other_goal") ?: 0
                        val homeOtherGoal = currentOtherGoal + 1
                        val homeCentreBackOtherGoal = currentCentreBackOtherGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeOtherGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_other_goal", homeCentreBackOtherGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val otherGoalTime = tvTimer.text.toString()
                        val otherGoalData = hashMapOf(
                            "time" to otherGoalTime,
                            "player" to "${tvHomeCentreBackName.text}",
                            "action" to "other_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(otherGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }

                        //masukkan data time ke dalam field ${tvHomeCentreBackName.text}_other_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeCentreBackName.text}_other_goal_times",
                                FieldValue.arrayUnion(otherGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
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
        btnShootFail.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentShootFail =
                        documentSnapshot.getLong("${tvTeamHome.text}_shoot_fail") ?: 0
                    val currentLeftBackShootFail =
                        documentSnapshot.getLong("${tvHomeLeftBackName.text}_shoot_fail") ?: 0
                    val homeShootFail = currentShootFail + 1
                    val homeLeftBackShootFail = currentLeftBackShootFail + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_shoot_fail", homeShootFail)
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
                    val shootFailData = hashMapOf(
                        "time" to shootFailTime,
                        "player" to "${tvHomeLeftBackName.text}",
                        "action" to "shoot_fail",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("shoot_fail")
                        .add(shootFailData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_shoot_fail_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeLeftBackName.text}_shoot_fail_times",
                            FieldValue.arrayUnion(shootFailTime)
                        )
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
        btnAssist.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentAssist = documentSnapshot.getLong("${tvTeamHome.text}_assist") ?: 0
                    val currentLeftBackAssist =
                        documentSnapshot.getLong("${tvHomeLeftBackName.text}_assist") ?: 0
                    val homeAssist = currentAssist + 1
                    val homeLeftBackAssist = currentLeftBackAssist + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_assist", homeAssist)
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
                    val assistData = hashMapOf(
                        "time" to assistTime,
                        "player" to "${tvHomeLeftBackName.text}",
                        "action" to "assist",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("assist")
                        .add(assistData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_assist_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeLeftBackName.text}_assist_times",
                            FieldValue.arrayUnion(assistTime)
                        )
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

        val btn10mFail = dialogView.findViewById<TextView>(R.id.button_10mfail)
        btn10mFail.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val current10mFail =
                        documentSnapshot.getLong("${tvTeamHome.text}_10m_fail") ?: 0
                    val currentLeftBack10mFail =
                        documentSnapshot.getLong("${tvHomeLeftBackName.text}_10m_fail") ?: 0
                    val home10mFail = current10mFail + 1
                    val homeLeftBack10mFail = currentLeftBack10mFail + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_10m_fail", home10mFail)
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

                    //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_10m_fail_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeLeftBackName.text}_10m_fail_times",
                            FieldValue.arrayUnion(fail10mTime)
                        )
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

        val btn10mgoal = dialogView.findViewById<TextView>(R.id.button_10mGoal)
        btn10mgoal.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val current10mGoal =
                        documentSnapshot.getLong("${tvTeamHome.text}_10m_goal") ?: 0
                    val currentLeftBack10mGoal =
                        documentSnapshot.getLong("${tvHomeLeftBackName.text}_10m_goal") ?: 0
                    val home10mGoal = current10mGoal + 1
                    val homeLeftBack10mGoal = currentLeftBack10mGoal + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_10m_goal", home10mGoal)
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

                    //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_10m_goal_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeLeftBackName.text}_10m_goal_times",
                            FieldValue.arrayUnion(goal10mTime)
                        )
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

        val btnRedCard = dialogView.findViewById<TextView>(R.id.button_redCard)
        btnRedCard.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentRedCard =
                        documentSnapshot.getLong("${tvTeamHome.text}_red_card") ?: 0
                    val currentLeftBackRedCard =
                        documentSnapshot.getLong("${tvHomeLeftBackName.text}_red_card") ?: 0
                    val homeRedCard = currentRedCard + 1
                    val homeLeftBackRedCard = currentLeftBackRedCard + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_red_card", homeRedCard)
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

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val redCardTime = tvTimer.text.toString()

                    val redCardData = hashMapOf(
                        "time" to redCardTime,
                        "player" to "${tvHomeLeftBackName.text}",
                        "action" to "red_card"
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("violations_card")
                        .add(redCardData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot written with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_red_card_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeLeftBackName.text}_red_card_times",
                            FieldValue.arrayUnion(redCardTime)
                        )
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

        val btnYellowCard = dialogView.findViewById<TextView>(R.id.button_yellowCard)
        btnYellowCard.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentYellowCard =
                        documentSnapshot.getLong("${tvTeamHome.text}_yellow_card") ?: 0
                    val currentLeftBackYellowCard =
                        documentSnapshot.getLong("${tvHomeLeftBackName.text}_yellow_card") ?: 0
                    val homeYellowCard = currentYellowCard + 1
                    val homeLeftBackYellowCard = currentLeftBackYellowCard + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_yellow_card", homeYellowCard)
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

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val yellowCardTime = tvTimer.text.toString()
                    val yellowCardData = hashMapOf(
                        "time" to yellowCardTime,
                        "player" to "${tvHomeLeftBackName.text}",
                        "action" to "yellow_card",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("violations_card")
                        .add(yellowCardData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_yellow_card_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeLeftBackName.text}_yellow_card_times",
                            FieldValue.arrayUnion(yellowCardTime)
                        )
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

        val btnSteal = dialogView.findViewById<TextView>(R.id.button_steal)
        btnSteal.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val currentSteal = documentSnapshot.getLong("${tvTeamHome.text}_steal") ?: 0
                    val currentLeftBackSteal =
                        documentSnapshot.getLong("${tvHomeLeftBackName.text}_steal") ?: 0
                    val homeSteal = currentSteal + 1
                    val homeLeftBackSteal = currentLeftBackSteal + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_steal", homeSteal)
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

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val stealTime = tvTimer.text.toString()
                    val stealData = hashMapOf(
                        "time" to stealTime,
                        "player" to "${tvHomeLeftBackName.text}",
                        "action" to "steal",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("steal")
                        .add(stealData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_steal_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeLeftBackName.text}_steal_times",
                            FieldValue.arrayUnion(stealTime)
                        )
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

        val btnPenalty = dialogView.findViewById<TextView>(R.id.button_penalty)
        btnPenalty.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val currentPenalty = documentSnapshot.getLong("${tvTeamHome.text}_penalty") ?: 0
                    val currentLeftBackPenalty =
                        documentSnapshot.getLong("${tvHomeLeftBackName.text}_penalty") ?: 0
                    val homePenalty = currentPenalty + 1
                    val homeLeftBackPenalty = currentLeftBackPenalty + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_penalty", homePenalty)
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
                    val penaltyData = hashMapOf(
                        "time" to penaltyTime,
                        "player" to "${tvHomeLeftBackName.text}",
                        "action" to "penalty",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("penalty")
                        .add(penaltyData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_penalty_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeLeftBackName.text}_penalty_times",
                            FieldValue.arrayUnion(penaltyTime)
                        )
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

        val btnOffside = dialogView.findViewById<TextView>(R.id.button_offside)
        btnOffside.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val currentOffside = documentSnapshot.getLong("${tvTeamHome.text}_offside") ?: 0
                    val currentLeftBackOffside =
                        documentSnapshot.getLong("${tvHomeLeftBackName.text}_offside") ?: 0
                    val homeOffside = currentOffside + 1
                    val homeLeftBackOffside = currentLeftBackOffside + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_offside", homeOffside)
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
                    val offsideData = hashMapOf(
                        "time" to offsideTime,
                        "player" to "${tvHomeLeftBackName.text}",
                        "action" to "offside",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("offside")
                        .add(offsideData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_offside_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeLeftBackName.text}_offside_times",
                            FieldValue.arrayUnion(offsideTime)
                        )
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

        val btnShootGoal = dialogView.findViewById<TextView>(R.id.button_shootGoal)
        btnShootGoal.setOnClickListener() {
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_match_goal, null)
            dialogBuilder.setView(dialogView)
            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            val btnShootGoal = dialogView.findViewById<TextView>(R.id.button_goal_shoot)
            btnShootGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentShootGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftBackShootGoal =
                            documentSnapshot.getLong("${tvHomeLeftBackName.text}_shoot_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeLeftBackShootGoal = currentLeftBackShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
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
                        val shootGoalData = hashMapOf(
                            "time" to shootGoalTime,
                            "player" to "${tvHomeLeftBackName.text}",
                            "action" to "shoot_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(shootGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_shoot_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeLeftBackName.text}_shoot_goal_times",
                                FieldValue.arrayUnion(shootGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                    }
            }

            val btnHealGoal = dialogView.findViewById<TextView>(R.id.button_goal_Heal)
            btnHealGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentHealGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftBackHealGoal =
                            documentSnapshot.getLong("${tvHomeLeftBackName.text}_heal_goal") ?: 0
                        val homeHealGoal = currentHealGoal + 1
                        val homeLeftBackHealGoal = currentLeftBackHealGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeHealGoal)
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
                        val healGoalData = hashMapOf(
                            "time" to healGoalTime,
                            "player" to "${tvHomeLeftBackName.text}",
                            "action" to "heal_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(healGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_heal_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeLeftBackName.text}_heal_goal_times",
                                FieldValue.arrayUnion(healGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                    }
            }

            val btnValleyGoal = dialogView.findViewById<TextView>(R.id.button_goal_valley)
            btnValleyGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentValleyGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftBackValleyGoal =
                            documentSnapshot.getLong("${tvHomeLeftBackName.text}_valley_goal") ?: 0
                        val homeValleyGoal = currentValleyGoal + 1
                        val homeLeftBackValleyGoal = currentLeftBackValleyGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeValleyGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeLeftBackName.text}_valley_goal",
                                homeLeftBackValleyGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val valleyGoalTime = tvTimer.text.toString()
                        val valleyGoalData = hashMapOf(
                            "time" to valleyGoalTime,
                            "player" to "${tvHomeLeftBackName.text}",
                            "action" to "valley_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(valleyGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_valley_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeLeftBackName.text}_valley_goal_times",
                                FieldValue.arrayUnion(valleyGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                    }
            }

            val btnLongGoal = dialogView.findViewById<TextView>(R.id.button_goal_long)
            btnLongGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentLongGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftBackLongGoal =
                            documentSnapshot.getLong("${tvHomeLeftBackName.text}_long_goal") ?: 0
                        val homeLongGoal = currentLongGoal + 1
                        val homeLeftBackLongGoal = currentLeftBackLongGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeLongGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_long_goal", homeLeftBackLongGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val longGoalTime = tvTimer.text.toString()
                        val longGoalData = hashMapOf(
                            "time" to longGoalTime,
                            "player" to "${tvHomeLeftBackName.text}",
                            "action" to "long_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(longGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_long_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeLeftBackName.text}_long_goal_times",
                                FieldValue.arrayUnion(longGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                    }
            }

            val btnHealedGoal = dialogView.findViewById<TextView>(R.id.button_goal_healed)
            btnHealedGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentHealedGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftBackHealedGoal =
                            documentSnapshot.getLong("${tvHomeLeftBackName.text}_healed_goal") ?: 0
                        val homeHealedGoal = currentHealedGoal + 1
                        val homeLeftBackHealedGoal = currentLeftBackHealedGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeHealedGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeLeftBackName.text}_healed_goal",
                                homeLeftBackHealedGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healedGoalTime = tvTimer.text.toString()
                        val healedGoalData = hashMapOf(
                            "time" to healedGoalTime,
                            "player" to "${tvHomeLeftBackName.text}",
                            "action" to "healed_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(healedGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_healed_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeLeftBackName.text}_healed_goal_times",
                                FieldValue.arrayUnion(healedGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
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

                        val currentLobGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftBackLobGoal =
                            documentSnapshot.getLong("${tvHomeLeftBackName.text}_lob_goal") ?: 0
                        val homeLobGoal = currentLobGoal + 1
                        val homeLeftBackLobGoal = currentLeftBackLobGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeLobGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_lob_goal", homeLeftBackLobGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val lobGoalTime = tvTimer.text.toString()
                        val lobGoalData = hashMapOf(
                            "time" to lobGoalTime,
                            "player" to "${tvHomeLeftBackName.text}",
                            "action" to "lob_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(lobGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_lob_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeLeftBackName.text}_lob_goal_times",
                                FieldValue.arrayUnion(lobGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
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

                        val currentFoulGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftBackFoulGoal =
                            documentSnapshot.getLong("${tvHomeLeftBackName.text}_foul_goal") ?: 0
                        val homeFoulGoal = currentFoulGoal + 1
                        val homeLeftBackFoulGoal = currentLeftBackFoulGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeFoulGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeLeftBackName.text}_foul_goal", homeLeftBackFoulGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val foulGoalTime = tvTimer.text.toString()
                        val foulGoalData = hashMapOf(
                            "time" to foulGoalTime,
                            "player" to "${tvHomeLeftBackName.text}",
                            "action" to "foul_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(foulGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_foul_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeLeftBackName.text}_foul_goal_times",
                                FieldValue.arrayUnion(foulGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
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

                        val currentReverseGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftBackReverseGoal =
                            documentSnapshot.getLong("${tvHomeLeftBackName.text}_reverse_goal") ?: 0
                        val homeReverseGoal = currentReverseGoal + 1
                        val homeLeftBackReverseGoal = currentLeftBackReverseGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeReverseGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeLeftBackName.text}_reverse_goal",
                                homeLeftBackReverseGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val reverseGoalTime = tvTimer.text.toString()
                        val reverseGoalData = hashMapOf(
                            "time" to reverseGoalTime,
                            "player" to "${tvHomeLeftBackName.text}",
                            "action" to "reverse_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(reverseGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_reverse_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeLeftBackName.text}_reverse_goal_times",
                                FieldValue.arrayUnion(reverseGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                    }
            }

            val btnScissorsGoal = dialogView.findViewById<TextView>(R.id.button_goal_Scissors)
            btnScissorsGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentScissorsGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftBackScissorsGoal =
                            documentSnapshot.getLong("${tvHomeLeftBackName.text}_scissors_goal")
                                ?: 0
                        val homeScissorsGoal = currentScissorsGoal + 1
                        val homeLeftBackScissorsGoal = currentLeftBackScissorsGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeScissorsGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeLeftBackName.text}_scissors_goal",
                                homeLeftBackScissorsGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val scissorsGoalTime = tvTimer.text.toString()
                        val scissorsGoalData = hashMapOf(
                            "time" to scissorsGoalTime,
                            "player" to "${tvHomeLeftBackName.text}",
                            "action" to "scissors_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(scissorsGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_scissors_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeLeftBackName.text}_scissors_goal_times",
                                FieldValue.arrayUnion(scissorsGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
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

                        val currentOtherGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftBackOtherGoal =
                            documentSnapshot.getLong("${tvHomeLeftBackName.text}_other_goal") ?: 0
                        val homeOtherGoal = currentOtherGoal + 1
                        val homeLeftBackOtherGoal = currentLeftBackOtherGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeOtherGoal)
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
                        val otherGoalData = hashMapOf(
                            "time" to otherGoalTime,
                            "player" to "${tvHomeLeftBackName.text}",
                            "action" to "other_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(otherGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeLeftBackName.text}_other_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeLeftBackName.text}_other_goal_times",
                                FieldValue.arrayUnion(otherGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                    }
            }

        }

    }

    private fun showHomeRightBackDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_match_aksi_landscape, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        val tvNamaPemain = dialogView.findViewById<TextView>(R.id.tv_tendangan_goal)
        tvNamaPemain.text = "Aksi Pemain: ${tvHomeRightBackName.text}"

        val btnShootGoal = dialogView.findViewById<TextView>(R.id.button_shootGoal)
        btnShootGoal.setOnClickListener() {
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_match_goal, null)
            dialogBuilder.setView(dialogView)
            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            val btnShotGoal = dialogView.findViewById<TextView>(R.id.button_goal_shoot)
            btnShotGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentShootGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightBackShootGoal =
                            documentSnapshot.getLong("${tvHomeRightBackName.text}_shoot_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeRightBackShootGoal = currentRightBackShootGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_shoot_goal",
                                homeRightBackShootGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val shootGoalTime = tvTimer.text.toString()
                        val shootGoalData = hashMapOf(
                            "time" to shootGoalTime,
                            "player" to "${tvHomeRightBackName.text}",
                            "action" to "shoot_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(shootGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeRightBackName.text}_shoot_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_shoot_goal_times",
                                FieldValue.arrayUnion(shootGoalTime)
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
            }

            val btnHealGoal = dialogView.findViewById<TextView>(R.id.button_goal_Heal)
            btnHealGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentHealGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightBackHealGoal =
                            documentSnapshot.getLong("${tvHomeRightBackName.text}_heal_goal") ?: 0
                        val homeHealGoal = currentHealGoal + 1
                        val homeRightBackHealGoal = currentRightBackHealGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeHealGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeRightBackName.text}_heal_goal", homeRightBackHealGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healGoalTime = tvTimer.text.toString()
                        val healGoalData = hashMapOf(
                            "time" to healGoalTime,
                            "player" to "${tvHomeRightBackName.text}",
                            "action" to "heal_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(healGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeRightBackName.text}_heal_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_heal_goal_times",
                                FieldValue.arrayUnion(healGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
            }

            val btnValleyGoal = dialogView.findViewById<TextView>(R.id.button_goal_valley)
            btnValleyGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentValleyGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightBackValleyGoal =
                            documentSnapshot.getLong("${tvHomeRightBackName.text}_valley_goal") ?: 0
                        val homeValleyGoal = currentValleyGoal + 1
                        val homeRightBackValleyGoal = currentRightBackValleyGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeValleyGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_valley_goal",
                                homeRightBackValleyGoal
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val valleyGoalTime = tvTimer.text.toString()
                        val valleyGoalData = hashMapOf(
                            "time" to valleyGoalTime,
                            "player" to "${tvHomeRightBackName.text}",
                            "action" to "valley_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(valleyGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeRightBackName.text}_valley_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_valley_goal_times",
                                FieldValue.arrayUnion(valleyGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
            }

            val btnLongGoal = dialogView.findViewById<TextView>(R.id.button_goal_long)
            btnLongGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentLongGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightBackLongGoal =
                            documentSnapshot.getLong("${tvHomeRightBackName.text}_long_goal") ?: 0
                        val homeLongGoal = currentLongGoal + 1
                        val homeRightBackLongGoal = currentRightBackLongGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeLongGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeRightBackName.text}_long_goal", homeRightBackLongGoal)
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val longGoalTime = tvTimer.text.toString()
                        val longGoalData = hashMapOf(
                            "time" to longGoalTime,
                            "player" to "${tvHomeRightBackName.text}",
                            "action" to "long_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(longGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeRightBackName.text}_long_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_long_goal_times",
                                FieldValue.arrayUnion(longGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
            }

            val btnHealedGoal = dialogView.findViewById<TextView>(R.id.button_goal_healed)
            btnHealedGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->

                        val currentHealedGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightBackHealedGoal =
                            documentSnapshot.getLong("${tvHomeRightBackName.text}_healed_goal") ?: 0
                        val homeHealedGoal = currentHealedGoal + 1
                        val homeRightBackHealedGoal = currentRightBackHealedGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeHealedGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_healed_goal",
                                homeRightBackHealedGoal
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healedGoalTime = tvTimer.text.toString()
                        val healedGoalData = hashMapOf(
                            "time" to healedGoalTime,
                            "player" to "${tvHomeRightBackName.text}",
                            "action" to "healed_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(healedGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeRightBackName.text}_healed_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_healed_goal_times",
                                FieldValue.arrayUnion(healedGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
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

                        val currentLobGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightBackLobGoal =
                            documentSnapshot.getLong("${tvHomeRightBackName.text}_lob_goal") ?: 0
                        val homeLobGoal = currentLobGoal + 1
                        val homeRightBackLobGoal = currentRightBackLobGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeLobGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeRightBackName.text}_lob_goal", homeRightBackLobGoal)
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val lobGoalTime = tvTimer.text.toString()
                        val lobGoalData = hashMapOf(
                            "time" to lobGoalTime,
                            "player" to "${tvHomeRightBackName.text}",
                            "action" to "lob_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(lobGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeRightBackName.text}_lob_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_lob_goal_times",
                                FieldValue.arrayUnion(lobGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
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

                        val currentFoulGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightBackFoulGoal =
                            documentSnapshot.getLong("${tvHomeRightBackName.text}_foul_goal") ?: 0
                        val homeFoulGoal = currentFoulGoal + 1
                        val homeRightBackFoulGoal = currentRightBackFoulGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeFoulGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update("${tvHomeRightBackName.text}_foul_goal", homeRightBackFoulGoal)
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val foulGoalTime = tvTimer.text.toString()
                        val foulGoalData = hashMapOf(
                            "time" to foulGoalTime,
                            "player" to "${tvHomeRightBackName.text}",
                            "action" to "foul_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(foulGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeRightBackName.text}_foul_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_foul_goal_times",
                                FieldValue.arrayUnion(foulGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
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
                        val currentReverseGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightBackReverseGoal =
                            documentSnapshot.getLong("${tvHomeRightBackName.text}_reverse_goal")
                                ?: 0
                        val homeReverseGoal = currentReverseGoal + 1
                        val homeRightBackReverseGoal = currentRightBackReverseGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeReverseGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_reverse_goal",
                                homeRightBackReverseGoal
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val reverseGoalTime = tvTimer.text.toString()
                        val reverseGoalData = hashMapOf(
                            "time" to reverseGoalTime,
                            "player" to "${tvHomeRightBackName.text}",
                            "action" to "reverse_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(reverseGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeRightBackName.text}_reverse_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_reverse_goal_times",
                                FieldValue.arrayUnion(reverseGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                    }
            }

            val btnScissorsGoal = dialogView.findViewById<TextView>(R.id.button_goal_Scissors)
            btnScissorsGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->
                        val currentScissorsGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightBackScissorsGoal =
                            documentSnapshot.getLong("${tvHomeRightBackName.text}_scissors_goal")
                                ?: 0
                        val homeScissorsGoal = currentScissorsGoal + 1
                        val homeRightBackScissorsGoal = currentRightBackScissorsGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeScissorsGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_scissors_goal",
                                homeRightBackScissorsGoal
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val scissorsGoalTime = tvTimer.text.toString()
                        val scissorsGoalData = hashMapOf(
                            "time" to scissorsGoalTime,
                            "player" to "${tvHomeRightBackName.text}",
                            "action" to "scissors_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(scissorsGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeRightBackName.text}_scissors_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_scissors_goal_times",
                                FieldValue.arrayUnion(scissorsGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
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
                        val currentOtherGoal =
                            documentSnapshot.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightBackOtherGoal =
                            documentSnapshot.getLong("${tvHomeRightBackName.text}_other_goal") ?: 0
                        val homeOtherGoal = currentOtherGoal + 1
                        val homeRightBackOtherGoal = currentRightBackOtherGoal + 1

                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeOtherGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeRightBackName.text}_other_goal",
                                homeRightBackOtherGoal
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w(
                                    "Error",
                                    "Error updating document",
                                    e
                                )
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val otherGoalTime = tvTimer.text.toString()
                        val otherGoalData = hashMapOf(
                            "time" to otherGoalTime,
                            "player" to "${tvHomeRightBackName.text}",
                            "action" to "other_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(otherGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }
                            .addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }
                    }
            }
        }

        val btnShootFail = dialogView.findViewById<TextView>(R.id.button_shootFail)
        btnShootFail.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val currentShootFail =
                        documentSnapshot.getLong("${tvTeamHome.text}_shoot_fail") ?: 0
                    val currentRightBackShootFail =
                        documentSnapshot.getLong("${tvHomeRightBackName.text}_shoot_fail") ?: 0
                    val homeShootFail = currentShootFail + 1
                    val homeRightBackShootFail = currentRightBackShootFail + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_shoot_fail", homeShootFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_shoot_fail",
                            homeRightBackShootFail
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val shootFailTime = tvTimer.text.toString()
                    val shootFailData = hashMapOf(
                        "time" to shootFailTime,
                        "player" to "${tvHomeRightBackName.text}",
                        "action" to "shoot_fail",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("shoot_fail")
                        .add(shootFailData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeRightBackName.text}_shoot_fail_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_shoot_fail_times",
                            FieldValue.arrayUnion(shootFailTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
        }

        val btnAssist = dialogView.findViewById<TextView>(R.id.button_assist)
        btnAssist.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val currentAssist =
                        documentSnapshot.getLong("${tvTeamHome.text}_assist") ?: 0
                    val currentRightBackAssist =
                        documentSnapshot.getLong("${tvHomeRightBackName.text}_assist") ?: 0
                    val homeAssist = currentAssist + 1
                    val homeRightBackAssist = currentRightBackAssist + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_assist", homeAssist)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_assist",
                            homeRightBackAssist
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val assistTime = tvTimer.text.toString()
                    val assistData = hashMapOf(
                        "time" to assistTime,
                        "player" to "${tvHomeRightBackName.text}",
                        "action" to "assist",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("assist")
                        .add(assistData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeRightBackName.text}_assist_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_assist_times",
                            FieldValue.arrayUnion(assistTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
        }

        val btn10mFail = dialogView.findViewById<TextView>(R.id.button_10mfail)
        btn10mFail.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()

                .addOnSuccessListener { documentSnapshot ->
                    val current10mFail =
                        documentSnapshot.getLong("${tvTeamHome.text}_10m_fail") ?: 0
                    val currentRightBack10mFail =
                        documentSnapshot.getLong("${tvHomeRightBackName.text}_10m_fail") ?: 0
                    val home10mFail = current10mFail + 1
                    val homeRightBack10mFail = currentRightBack10mFail + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_10m_fail", home10mFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_10m_fail",
                            homeRightBack10mFail
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val _10mFailTime = tvTimer.text.toString()
                    val _10mFailData = hashMapOf(
                        "time" to _10mFailTime,
                        "player" to "${tvHomeRightBackName.text}",
                        "action" to "10m_fail",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("10m_fail")
                        .add(_10mFailData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeRightBackName.text}_10m_fail_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_10m_fail_times",
                            FieldValue.arrayUnion(_10mFailTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
        }

        val btn10mGoal = dialogView.findViewById<TextView>(R.id.button_10mGoal)
        btn10mGoal.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()

                .addOnSuccessListener { documentSnapshot ->
                    val current10mGoal =
                        documentSnapshot.getLong("${tvTeamHome.text}_10m_goal") ?: 0
                    val currentRightBack10mGoal =
                        documentSnapshot.getLong("${tvHomeRightBackName.text}_10m_goal") ?: 0
                    val home10mGoal = current10mGoal + 1
                    val homeRightBack10mGoal = currentRightBack10mGoal + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_10m_goal", home10mGoal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_10m_goal",
                            homeRightBack10mGoal
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val _10mGoalTime = tvTimer.text.toString()
                    val _10mGoalData = hashMapOf(
                        "time" to _10mGoalTime,
                        "player" to "${tvHomeRightBackName.text}",
                        "action" to "10m_goal",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("10m_goal")
                        .add(_10mGoalData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeRightBackName.text}_10m_goal_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_10m_goal_times",
                            FieldValue.arrayUnion(_10mGoalTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
        }

        val btnRedCard = dialogView.findViewById<TextView>(R.id.button_redCard)
        btnRedCard.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()

                .addOnSuccessListener { documentSnapshot ->
                    val currentRedCard =
                        documentSnapshot.getLong("${tvTeamHome.text}_red_card") ?: 0
                    val currentRightBackRedCard =
                        documentSnapshot.getLong("${tvHomeRightBackName.text}_red_card") ?: 0
                    val homeRedCard = currentRedCard + 1
                    val homeRightBackRedCard = currentRightBackRedCard + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_red_card", homeRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_red_card",
                            homeRightBackRedCard
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val redCardTime = tvTimer.text.toString()
                    val redCardData = hashMapOf(
                        "time" to redCardTime,
                        "player" to "${tvHomeRightBackName.text}",
                        "action" to "red_card",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("violations_card")
                        .add(redCardData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeRightBackName.text}_red_card_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_red_card_times",
                            FieldValue.arrayUnion(redCardTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
        }

        val btnYellowCard = dialogView.findViewById<TextView>(R.id.button_yellowCard)
        btnYellowCard.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val currentYellowCard = documentSnapshot.getLong("${tvTeamHome.text}_yellow_card") ?: 0
                    val currentRightBackYellowCard = documentSnapshot.getLong("${tvHomeRightBackName.text}_yellow_card") ?: 0
                    val homeYellowCard = currentYellowCard + 1
                    val homeRightBackYellowCard = currentRightBackYellowCard + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_yellow_card", homeYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_yellow_card",
                            homeRightBackYellowCard
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val yellowCardTime = tvTimer.text.toString()
                    val yellowCardData = hashMapOf(
                        "time" to yellowCardTime,
                        "player" to "${tvHomeRightBackName.text}",
                        "action" to "yellow_card",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("violations_card")
                        .add(yellowCardData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeRightBackName.text}_yellow_card_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_yellow_card_times",
                            FieldValue.arrayUnion(yellowCardTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
        }

        val btnSteal = dialogView.findViewById<TextView>(R.id.button_steal)
        btnSteal.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()

                .addOnSuccessListener { documentSnapshot ->
                    val currentSteal =
                        documentSnapshot.getLong("${tvTeamHome.text}_steal") ?: 0
                    val currentRightBackSteal =
                        documentSnapshot.getLong("${tvHomeRightBackName.text}_steal") ?: 0
                    val homeSteal = currentSteal + 1
                    val homeRightBackSteal = currentRightBackSteal + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_steal", homeSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_steal",
                            homeRightBackSteal
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val stealTime = tvTimer.text.toString()
                    val stealData = hashMapOf(
                        "time" to stealTime,
                        "player" to "${tvHomeRightBackName.text}",
                        "action" to "steal",
                    )

                    db.collection("matchStats").document(documentId)
                        .collection("steal")
                        .add(stealData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeRightBackName.text}_steal_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_steal_times",
                            FieldValue.arrayUnion(stealTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
        }

        val btnPenalty = dialogView.findViewById<TextView>(R.id.button_penalty)
        btnPenalty.setOnClickListener(){
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()

                .addOnSuccessListener { documentSnapshot ->
                    val currentPenalty =
                        documentSnapshot.getLong("${tvTeamHome.text}_penalty") ?: 0
                    val currentRightBackPenalty =
                        documentSnapshot.getLong("${tvHomeRightBackName.text}_penalty") ?: 0
                    val homePenalty = currentPenalty + 1
                    val homeRightBackPenalty = currentRightBackPenalty + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_penalty", homePenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_penalty",
                            homeRightBackPenalty
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val penaltyTime = tvTimer.text.toString()
                    val penaltyData = hashMapOf(
                        "time" to penaltyTime,
                        "player" to "${tvHomeRightBackName.text}",
                        "action" to "penalty",
                    )

                    db.collection("matchStats").document(documentId)
                        .collection("penalty")
                        .add(penaltyData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeRightBackName.text}_penalty_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_penalty_times",
                            FieldValue.arrayUnion(penaltyTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
        }

        val btnOffside = dialogView.findViewById<TextView>(R.id.button_offside)
        btnOffside.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()

                .addOnSuccessListener { documentSnapshot ->
                    val currentOffside =
                        documentSnapshot.getLong("${tvTeamHome.text}_offside") ?: 0
                    val currentRightBackOffside =
                        documentSnapshot.getLong("${tvHomeRightBackName.text}_offside") ?: 0
                    val homeOffside = currentOffside + 1
                    val homeRightBackOffside = currentRightBackOffside + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_offside", homeOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_offside",
                            homeRightBackOffside
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val offsideTime = tvTimer.text.toString()
                    val offsideData = hashMapOf(
                        "time" to offsideTime,
                        "player" to "${tvHomeRightBackName.text}",
                        "action" to "offside",
                    )

                    db.collection("matchStats").document(documentId)
                        .collection("offside")
                        .add(offsideData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeRightBackName.text}_offside_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeRightBackName.text}_offside_times",
                            FieldValue.arrayUnion(offsideTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                }
            }

    }

    private fun showHomeDefensiveMidfielderDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_match_aksi_landscape, null)
        alertDialogBuilder.setView(dialogView)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

        //inisialisasi nama pemain
        val tvPlayerName = dialogView.findViewById<TextView>(R.id.tv_tendangan_goal)
        tvPlayerName.text = "Aksi Pemain: ${tvHomeDefensiveMidfielderName.text}"

        //handler shootFail
        val btnShootFail = dialogView.findViewById<TextView>(R.id.button_shootFail)
        btnShootFail.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener() {
                    val currentShootFail = it.getLong("${tvTeamHome.text}_shoot_fail") ?: 0
                    val currentDefensiveMidfielderShootFail =
                        it.getLong("${tvHomeDefensiveMidfielderName.text}_shoot_fail") ?: 0
                    val homeShootFail = currentShootFail + 1
                    val homeDefensiveMidfielderShootFail = currentDefensiveMidfielderShootFail + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_shoot_fail", homeShootFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_shoot_fail",
                            homeDefensiveMidfielderShootFail
                        )
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val shootFailTime = tvTimer.text.toString()
                    val shootFailData = hashMapOf(
                        "time" to shootFailTime,
                        "player" to "${tvHomeDefensiveMidfielderName.text}",
                        "action" to "shoot_fail",
                    )

                    db.collection("matchStats").document(documentId)
                        .collection("shoot_fail")
                        .add(shootFailData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_shoot_fail_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_shoot_fail_times",
                            FieldValue.arrayUnion(shootFailTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() {
                            Log.w("Error", "Error updating document", it)
                        }
                }
        }

        //handler assist
        val btnAssist = dialogView.findViewById<TextView>(R.id.button_assist)
        btnAssist.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener() {
                    val currentAssist = it.getLong("${tvTeamHome.text}_assist") ?: 0
                    val currentDefensiveMidfielderAssist =
                        it.getLong("${tvHomeDefensiveMidfielderName.text}_assist") ?: 0
                    val homeAssist = currentAssist + 1
                    val homeDefensiveMidfielderAssist = currentDefensiveMidfielderAssist + 1

                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_assist", homeAssist)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_assist",
                            homeDefensiveMidfielderAssist
                        )
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val assistTime = tvTimer.text.toString()
                    val assistData = hashMapOf(
                        "time" to assistTime,
                        "player" to "${tvHomeDefensiveMidfielderName.text}",
                        "action" to "assist",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("assist")
                        .add(assistData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_assist_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_assist_times",
                            FieldValue.arrayUnion(assistTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() {
                            Log.w("Error", "Error updating document", it)
                        }

                }
        }

        //handler 10mgoal
        val btn10mgoal = dialogView.findViewById<TextView>(R.id.button_10mGoal)
        btn10mgoal.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener() {
                    val current10mGoal = it.getLong("${tvTeamHome.text}_10m_goal") ?: 0
                    val currentDefensiveMidfielder10mGoal =
                        it.getLong("${tvHomeDefensiveMidfielderName.text}_10m_goal") ?: 0
                    val home10mGoal = current10mGoal + 1
                    val homeDefensiveMidfielder10mGoal = currentDefensiveMidfielder10mGoal + 1
                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_10m_goal", home10mGoal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_10m_goal",
                            homeDefensiveMidfielder10mGoal
                        )
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val goal10mTime = tvTimer.text.toString()
                    val goal10mData = hashMapOf(
                        "time" to goal10mTime,
                        "player" to "${tvHomeDefensiveMidfielderName.text}",
                        "action" to "10m_goal",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("10m_goal")
                        .add(goal10mData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_10m_goal_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_10m_goal_times",
                            FieldValue.arrayUnion(goal10mTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() {
                            Log.w("Error", "Error updating document", it)
                        }

                }
        }

        //handler penalty
        val btnPenalty = dialogView.findViewById<TextView>(R.id.button_penalty)
        btnPenalty.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener() {
                    val currentPenalty = it.getLong("${tvTeamHome.text}_penalty") ?: 0
                    val currentDefensiveMidfielderPenalty =
                        it.getLong("${tvHomeDefensiveMidfielderName.text}_penalty") ?: 0
                    val homePenalty = currentPenalty + 1
                    val homeDefensiveMidfielderPenalty = currentDefensiveMidfielderPenalty + 1
                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_penalty", homePenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_penalty",
                            homeDefensiveMidfielderPenalty
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w(
                                "Error",
                                "Error updating document",
                                e
                            )
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val penaltyTime = tvTimer.text.toString()
                    val penaltyData = hashMapOf(
                        "time" to penaltyTime,
                        "player" to "${tvHomeDefensiveMidfielderName.text}",
                        "action" to "penalty",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("penalty")
                        .add(penaltyData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_penalty_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_penalty_times",
                            FieldValue.arrayUnion(penaltyTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() {
                            Log.w("Error", "Error updating document", it)
                        }
                }
        }

        //handler offside
        val btnOffside = dialogView.findViewById<TextView>(R.id.button_offside)
        btnOffside.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener() {
                    val currentOffside = it.getLong("${tvTeamHome.text}_offside") ?: 0
                    val currentDefensiveMidfielderOffside =
                        it.getLong("${tvHomeDefensiveMidfielderName.text}_offside") ?: 0
                    val homeOffside = currentOffside + 1
                    val homeDefensiveMidfielderOffside = currentDefensiveMidfielderOffside + 1
                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_offside", homeOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_offside",
                            homeDefensiveMidfielderOffside
                        )
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val offsideTime = tvTimer.text.toString()
                    val offsideData = hashMapOf(
                        "time" to offsideTime,
                        "player" to "${tvHomeDefensiveMidfielderName.text}",
                        "action" to "offside",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("offside")
                        .add(offsideData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_offside_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_offside_times",
                            FieldValue.arrayUnion(offsideTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() {
                            Log.w("Error", "Error updating document", it)
                        }
                }
        }

        //handler steal
        val btnSteal = dialogView.findViewById<TextView>(R.id.button_steal)
        btnSteal.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener() {
                    val currentSteal = it.getLong("${tvTeamHome.text}_steal") ?: 0
                    val currentDefensiveMidfielderSteal =
                        it.getLong("${tvHomeDefensiveMidfielderName.text}_steal") ?: 0
                    val homeSteal = currentSteal + 1
                    val homeDefensiveMidfielderSteal = currentDefensiveMidfielderSteal + 1
                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_steal", homeSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_steal",
                            homeDefensiveMidfielderSteal
                        )
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val stealTime = tvTimer.text.toString()
                    val stealData = hashMapOf(
                        "time" to stealTime,
                        "player" to "${tvHomeDefensiveMidfielderName.text}",
                        "action" to "steal",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("steal")
                        .add(stealData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_steal_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_steal_times",
                            FieldValue.arrayUnion(stealTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() {
                            Log.w("Error", "Error updating document", it)
                        }
                }
        }

        //handler yellowcard
        val btnYellowCard = dialogView.findViewById<TextView>(R.id.button_yellowCard)
        btnYellowCard.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener() {
                    val currentYellowCard = it.getLong("${tvTeamHome.text}_yellow_card") ?: 0
                    val currentDefensiveMidfielderYellowCard =
                        it.getLong("${tvHomeDefensiveMidfielderName.text}_yellow_card") ?: 0
                    val homeYellowCard = currentYellowCard + 1
                    val homeDefensiveMidfielderYellowCard =
                        currentDefensiveMidfielderYellowCard + 1
                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_yellow_card", homeYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_yellow_card",
                            homeDefensiveMidfielderYellowCard
                        )
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val yellowCardTime = tvTimer.text.toString()
                    val yellowCardData = hashMapOf(
                        "time" to yellowCardTime,
                        "player" to "${tvHomeDefensiveMidfielderName.text}",
                        "action" to "yellow_card",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("violations_card")
                        .add(yellowCardData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }
                    //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_yellow_card_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_yellow_card_times",
                            FieldValue.arrayUnion(
                                yellowCardTime
                            )
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() {
                            Log.w("Error", "Error updating document", it)
                        }
                }
        }

        //handler redcard
        val btnRedCard = dialogView.findViewById<TextView>(R.id.button_redCard)
        btnRedCard.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener() {
                    val currentRedCard = it.getLong("${tvTeamHome.text}_red_card") ?: 0
                    val currentDefensiveMidfielderRedCard = it.getLong("${tvHomeDefensiveMidfielderName.text}_red_card") ?: 0
                    val homeRedCard = currentRedCard + 1
                    val homeDefensiveMidfielderRedCard = currentDefensiveMidfielderRedCard + 1
                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_red_card", homeRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_red_card",
                            homeDefensiveMidfielderRedCard
                        )
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val redCardTime = tvTimer.text.toString()
                    val redCardData = hashMapOf(
                        "time" to redCardTime,
                        "player" to "${tvHomeDefensiveMidfielderName.text}",
                        "action" to "red_card",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("violations_card")
                        .add(redCardData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_red_card_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_red_card_times",
                            FieldValue.arrayUnion(redCardTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() {
                            Log.w("Error", "Error updating document", it)
                        }

                }
        }

        //handler 10mfail
        val btn10mfail = dialogView.findViewById<TextView>(R.id.button_10mfail)
        btn10mfail.setOnClickListener() {
            val documentId = documentId
            db.collection("matchStats").document(documentId)
                .get()
                .addOnSuccessListener() {
                    val currentTenMeterFail = it.getLong("${tvTeamHome.text}_10m_fail") ?: 0
                    val currentDefensiveMidfielderTenMeterFail =
                        it.getLong("${tvHomeDefensiveMidfielderName.text}_10m_fail") ?: 0
                    val homeTenMeterFail = currentTenMeterFail + 1
                    val homeDefensiveMidfielderTenMeterFail =
                        currentDefensiveMidfielderTenMeterFail + 1
                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_10m_fail", homeTenMeterFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_10m_fail",
                            homeDefensiveMidfielderTenMeterFail
                        )
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val tenMeterFailTime = tvTimer.text.toString()
                    val tenMeterFailData = hashMapOf(
                        "time" to tenMeterFailTime,
                        "player" to "${tvHomeDefensiveMidfielderName.text}",
                        "action" to "10m_fail",
                    )
                    db.collection("matchStats").document(documentId)
                        .collection("10m_fail")
                        .add(tenMeterFailData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "Success",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_10m_fail_times dalam bentuk Array
                    db.collection("matchStats").document(documentId)
                        .update(
                            "${tvHomeDefensiveMidfielderName.text}_10m_fail_times",
                            FieldValue.arrayUnion(tenMeterFailTime)
                        )
                        .addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() {
                            Log.w("Error", "Error updating document", it)
                        }
                }
        }

        //handler btnShootGoal
        val btnShootGoalToogle = dialogView.findViewById<TextView>(R.id.button_shootGoal)
        btnShootGoalToogle.setOnClickListener() {
            val dialogBuilder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_match_goal, null)
            dialogBuilder.setView(dialogView)
            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            //handler shootGoal
            val btnShootGoal = dialogView.findViewById<TextView>(R.id.button_goal_shoot)
            btnShootGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentShootGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentDefensiveMidfielderShootGoal =
                            it.getLong("${tvHomeDefensiveMidfielderName.text}_shoot_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeDefensiveMidfielderShootGoal =
                            currentDefensiveMidfielderShootGoal + 1
                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_shoot_goal",
                                homeDefensiveMidfielderShootGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val shootGoalTime = tvTimer.text.toString()
                        val shootGoalData = hashMapOf(
                            "time" to shootGoalTime,
                            "player" to "${tvHomeDefensiveMidfielderName.text}",
                            "action" to "shoot_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(shootGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_shoot_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_shoot_goal_times",
                                FieldValue.arrayUnion(shootGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }
                    }
            }

            //handler healGoal
            val btnHealGoal = dialogView.findViewById<TextView>(R.id.button_goal_Heal)
            btnHealGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentHealGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentDefensiveMidfielderHealGoal =
                            it.getLong("${tvHomeDefensiveMidfielderName.text}_heal_goal") ?: 0
                        val homeHealGoal = currentHealGoal + 1
                        val homeDefensiveMidfielderHealGoal = currentDefensiveMidfielderHealGoal + 1
                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeHealGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_heal_goal",
                                homeDefensiveMidfielderHealGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healGoalTime = tvTimer.text.toString()
                        val healGoalData = hashMapOf(
                            "time" to healGoalTime,
                            "player" to "${tvHomeDefensiveMidfielderName.text}",
                            "action" to "heal_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(healGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_heal_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_heal_goal_times",
                                FieldValue.arrayUnion(healGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }
                    }
            }

            //handler valleyGoal
            val btnValleyGoal = dialogView.findViewById<TextView>(R.id.button_goal_valley)
            btnValleyGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentValleyGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentDefensiveMidfielderValleyGoal =
                            it.getLong("${tvHomeDefensiveMidfielderName.text}_valley_goal") ?: 0
                        val homeValleyGoal = currentValleyGoal + 1
                        val homeDefensiveMidfielderValleyGoal =
                            currentDefensiveMidfielderValleyGoal + 1
                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeValleyGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_valley_goal",
                                homeDefensiveMidfielderValleyGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val valleyGoalTime = tvTimer.text.toString()
                        val valleyGoalData = hashMapOf(
                            "time" to valleyGoalTime,
                            "player" to "${tvHomeDefensiveMidfielderName.text}",
                            "action" to "valley_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(valleyGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_valley_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_valley_goal_times",
                                FieldValue.arrayUnion(valleyGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }

                    }
            }

            //handler LongGoal
            val btnLongGoal = dialogView.findViewById<TextView>(R.id.button_goal_long)
            btnLongGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentLongGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentDefensiveMidfielderLongGoal =
                            it.getLong("${tvHomeDefensiveMidfielderName.text}_long_goal") ?: 0
                        val homeLongGoal = currentLongGoal + 1
                        val homeDefensiveMidfielderLongGoal = currentDefensiveMidfielderLongGoal + 1
                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeLongGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_long_goal",
                                homeDefensiveMidfielderLongGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val longGoalTime = tvTimer.text.toString()
                        val longGoalData = hashMapOf(
                            "time" to longGoalTime,
                            "player" to "${tvHomeDefensiveMidfielderName.text}",
                            "action" to "long_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(longGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_long_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_long_goal_times",
                                FieldValue.arrayUnion(longGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }
                    }
            }

            //handler healedGoal
            val btnHealedGoal = dialogView.findViewById<TextView>(R.id.button_goal_healed)
            btnHealedGoal.setOnClickListener() {
                val documentId = documentId
                db.collection("matchStats").document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentHealedGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentDefensiveMidfielderHealedGoal =
                            it.getLong("${tvHomeDefensiveMidfielderName.text}_healed_goal") ?: 0
                        val homeHealedGoal = currentHealedGoal + 1
                        val homeDefensiveMidfielderHealedGoal =
                            currentDefensiveMidfielderHealedGoal + 1
                        db.collection("matchStats").document(documentId)
                            .update("${tvTeamHome.text}_goal", homeHealedGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_healed_goal",
                                homeDefensiveMidfielderHealedGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healedGoalTime = tvTimer.text.toString()
                        val healedGoalData = hashMapOf(
                            "time" to healedGoalTime,
                            "player" to "${tvHomeDefensiveMidfielderName.text}",
                            "action" to "healed_goal",
                        )
                        db.collection("matchStats").document(documentId)
                            .collection("goals")
                            .add(healedGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_healed_goal_times dalam bentuk Array
                        db.collection("matchStats").document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_healed_goal_times",
                                FieldValue.arrayUnion(healedGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }

                    }
            }

            //handler lobGoal
            val btnLobGoal = dialogView.findViewById<TextView>(R.id.button_goal_lob)
            btnLobGoal.setOnClickListener() {
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentLobGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentDefensiveMidfielderLobGoal =
                            it.getLong("${tvHomeDefensiveMidfielderName.text}_lob_goal") ?: 0
                        val homeLobGoal = currentLobGoal + 1
                        val homeDefensiveMidfielderLobGoal = currentDefensiveMidfielderLobGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeLobGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_lob_goal",
                                homeDefensiveMidfielderLobGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val lobGoalTime = tvTimer.text.toString()
                        val lobGoalData = hashMapOf(
                            "time" to lobGoalTime,
                            "player" to "${tvHomeDefensiveMidfielderName.text}",
                            "action" to "lob_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection("goals")
                            .add(lobGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_lob_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_lob_goal_times",
                                FieldValue.arrayUnion(lobGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }

                    }
            }

            //handler foulGoal
            val btnFoulGoal = dialogView.findViewById<TextView>(R.id.button_goal_foul)
            btnFoulGoal.setOnClickListener() {
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentFoulGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentDefensiveMidfielderFoulGoal =
                            it.getLong("${tvHomeDefensiveMidfielderName.text}_foul_goal") ?: 0
                        val homeFoulGoal = currentFoulGoal + 1
                        val homeDefensiveMidfielderFoulGoal = currentDefensiveMidfielderFoulGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeFoulGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_foul_goal",
                                homeDefensiveMidfielderFoulGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val foulGoalTime = tvTimer.text.toString()
                        val foulGoalData = hashMapOf(
                            "time" to foulGoalTime,
                            "player" to "${tvHomeDefensiveMidfielderName.text}",
                            "action" to "foul_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection("goals")
                            .add(foulGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_foul_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_foul_goal_times",
                                FieldValue.arrayUnion(foulGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }


                    }
            }

            //handler reverseGoal
            val btnReverseGoal = dialogView.findViewById<TextView>(R.id.button_goal_reverse)
            btnReverseGoal.setOnClickListener() {
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentReverseGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentDefensiveMidfielderReverseGoal =
                            it.getLong("${tvHomeDefensiveMidfielderName.text}_goal") ?: 0
                        val homeReverseGoal = currentReverseGoal + 1
                        val homeDefensiveMidfielderReverseGoal =
                            currentDefensiveMidfielderReverseGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeReverseGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_reverse_goal",
                                homeDefensiveMidfielderReverseGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val reverseGoalTime = tvTimer.text.toString()
                        val reverseGoalData = hashMapOf(
                            "time" to reverseGoalTime,
                            "player" to "${tvHomeDefensiveMidfielderName.text}",
                            "action" to "reverse_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection("goals")
                            .add(reverseGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_reverse_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_reverse_goal_times",
                                FieldValue.arrayUnion(reverseGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }

                    }
            }

            //handler scissorsGoal
            val btnScissorsGoal = dialogView.findViewById<TextView>(R.id.button_goal_Scissors)
            btnScissorsGoal.setOnClickListener() {
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentScissorsGoal =
                            it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentDefensiveMidfielderScissorsGoal =
                            it.getLong("${tvHomeDefensiveMidfielderName.text}_scissors_goal") ?: 0
                        val homeScissorsGoal = currentScissorsGoal + 1
                        val homeDefensiveMidfielderScissorsGoal =
                            currentDefensiveMidfielderScissorsGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeScissorsGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_scissors_goal",
                                homeDefensiveMidfielderScissorsGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val scissorsGoalTime = tvTimer.text.toString()
                        val scissorsGoalData = hashMapOf(
                            "time" to scissorsGoalTime,
                            "player" to "${tvHomeDefensiveMidfielderName.text}",
                            "action" to "scissors_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection("goals")
                            .add(scissorsGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_scissors_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeDefensiveMidfielderName.text}_scissors_goal_times",
                                FieldValue.arrayUnion(scissorsGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }
                    }
            }

            //handler otherGoal
            val btnOtherGoal = dialogView.findViewById<TextView>(R.id.button_goal_other)
            btnOtherGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentOtherGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentDefensiveMidfielderOtherGoal = it.getLong("${tvHomeDefensiveMidfielderName.text}_other_goal") ?: 0
                        val homeOtherGoal = currentOtherGoal + 1
                        val homeDefensiveMidfielderOtherGoal = currentDefensiveMidfielderOtherGoal + 1
                        db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_goal", homeOtherGoal).addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                        db.collection(collectionName).document(documentId).update("${tvHomeDefensiveMidfielderName.text}_other_goal", homeDefensiveMidfielderOtherGoal).addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val otherGoalTime = tvTimer.text.toString()
                        val otherGoalData = hashMapOf(
                            "time" to otherGoalTime,
                            "player" to "${tvHomeDefensiveMidfielderName.text}",
                            "action" to "other_goal",
                        )
                        db.collection(collectionName).document(documentId).collection("goals").add(otherGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeDefensiveMidfielderName.text}_other_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeDefensiveMidfielderName.text}_other_goal_times", FieldValue.arrayUnion(otherGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }
                    }
            }

        }
    }

    private fun showHomeCentralMidfielderDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_match_aksi_landscape, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        val tvPlayerName = dialogView.findViewById<TextView>(R.id.tv_tendangan_goal)
        tvPlayerName.text = "Aksi Pemain: ${tvHomeCentralMidfielderName.text}"

        //handler shootFail
        val btnShootFail = dialogView.findViewById<TextView>(R.id.button_shootFail)
        btnShootFail.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentShootFail = it.getLong("${tvTeamHome.text}_shoot_fail") ?: 0
                    val currentCentralMidfielderShootFail = it.getLong("${tvHomeCentralMidfielderName.text}_shoot_fail") ?: 0
                    val homeShootFail = currentShootFail + 1
                    val homeCentralMidfielderShootFail = currentCentralMidfielderShootFail + 1
                    db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_shoot_fail", homeShootFail).addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error updating document", e)
                    }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeCentralMidfielderName.text}_shoot_fail", homeCentralMidfielderShootFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val shootFailTime = tvTimer.text.toString()
                    val shootFailData = hashMapOf(
                        "time" to shootFailTime,
                        "player" to "${tvHomeCentralMidfielderName.text}",
                        "action" to "shoot_fail",
                    )
                    db.collection(collectionName).document(documentId)
                        .collection("shoot_fail")
                        .add(shootFailData)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_shoot_fail_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeCentralMidfielderName.text}_shoot_fail_times", FieldValue.arrayUnion(shootFailTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }
                }
        }

        //handller assist
        val btnAssist = dialogView.findViewById<TextView>(R.id.button_assist)
        btnAssist.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentAssist = it.getLong("${tvTeamHome.text}_assist") ?: 0
                    val currentCentralMidfielderAssist = it.getLong("${tvHomeCentralMidfielderName.text}_assist") ?: 0
                    val homeAssist = currentAssist + 1
                    val homeCentralMidfielderAssist = currentCentralMidfielderAssist + 1
                    db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_assist", homeAssist).addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error updating document", e)
                    }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeCentralMidfielderName.text}_assist", homeCentralMidfielderAssist)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val assistTime = tvTimer.text.toString()
                    val assistData = hashMapOf(
                        "time" to assistTime,
                        "player" to "${tvHomeCentralMidfielderName.text}",
                        "action" to "assist",
                    )
                    db.collection(collectionName).document(documentId)
                        .collection("assist")
                        .add(assistData)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_assist_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeCentralMidfielderName.text}_assist_times", FieldValue.arrayUnion(assistTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }
                }
        }

        //handler 10mgoal
        val btn10mGoal = dialogView.findViewById<TextView>(R.id.button_10mGoal)
        btn10mGoal.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val current10mGoal = it.getLong("${tvTeamHome.text}_10m_goal") ?: 0
                    val currentCentralMidfielder10mGoal = it.getLong("${tvHomeCentralMidfielderName.text}_10m_goal") ?: 0
                    val home10mGoal = current10mGoal + 1
                    val homeCentralMidfielder10mGoal = currentCentralMidfielder10mGoal + 1
                    db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_10m_goal", home10mGoal).addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error updating document", e)
                    }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeCentralMidfielderName.text}_10m_goal", homeCentralMidfielder10mGoal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val _10mGoalTime = tvTimer.text.toString()
                    val _10mGoalData = hashMapOf(
                        "time" to _10mGoalTime,
                        "player" to "${tvHomeCentralMidfielderName.text}",
                        "action" to "10m_goal",
                    )
                    db.collection(collectionName).document(documentId)
                        .collection("10m_goal")
                        .add(_10mGoalData)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_10m_goal_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeCentralMidfielderName.text}_10m_goal_times", FieldValue.arrayUnion(_10mGoalTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }
                }
        }

        //handler yellowCard
        val btnYellowCard = dialogView.findViewById<TextView>(R.id.button_yellowCard)
        btnYellowCard.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentYellowCard = it.getLong("${tvTeamHome.text}_yellow_card") ?: 0
                    val currentCentralMidfielderYellowCard = it.getLong("${tvHomeCentralMidfielderName.text}_yellow_card") ?: 0
                    val homeYellowCard = currentYellowCard + 1
                    val homeCentralMidfielderYellowCard = currentCentralMidfielderYellowCard + 1
                    db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_yellow_card", homeYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeCentralMidfielderName.text}_yellow_card", homeCentralMidfielderYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val yellowCardTime = tvTimer.text.toString()
                    val yellowCardData = hashMapOf(
                        "time" to yellowCardTime,
                        "player" to "${tvHomeCentralMidfielderName.text}",
                        "action" to "yellow_card",
                    )
                    db.collection(collectionName).document(documentId)
                        .collection("violations_card")
                        .add(yellowCardData)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_yellow_card_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeCentralMidfielderName.text}_yellow_card_times", FieldValue.arrayUnion(yellowCardTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler penalty
        val btnPenalty = dialogView.findViewById<TextView>(R.id.button_penalty)
        btnPenalty.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentPenalty = it.getLong("${tvTeamHome.text}_penalty") ?: 0
                    val currentCentralMidfielderPenalty = it.getLong("${tvHomeCentralMidfielderName.text}_penalty") ?: 0
                    val homePenalty = currentPenalty + 1
                    val homeCentralMidfielderPenalty = currentCentralMidfielderPenalty + 1
                    db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_penalty", homePenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeCentralMidfielderName.text}_penalty", homeCentralMidfielderPenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val penaltyTime = tvTimer.text.toString()
                    val penaltyData = hashMapOf(
                        "time" to penaltyTime,
                        "player" to "${tvHomeCentralMidfielderName.text}",
                        "action" to "penalty",
                    )
                    db.collection(collectionName).document(documentId)
                        .collection("penalty")
                        .add(penaltyData)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_penalty_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeCentralMidfielderName.text}_penalty_times", FieldValue.arrayUnion(penaltyTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler offside
        val btnOffside = dialogView.findViewById<TextView>(R.id.button_offside)
        btnOffside.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentOffside = it.getLong("${tvTeamHome.text}_offside") ?: 0
                    val currentCentralMidfielderOffside = it.getLong("${tvHomeCentralMidfielderName.text}_offside") ?: 0
                    val homeOffside = currentOffside + 1
                    val homeCentralMidfielderOffside = currentCentralMidfielderOffside + 1
                    db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_offside", homeOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeCentralMidfielderName.text}_offside", homeCentralMidfielderOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val offsideTime = tvTimer.text.toString()
                    val offsideData = hashMapOf(
                        "time" to offsideTime,
                        "player" to "${tvHomeCentralMidfielderName.text}",
                        "action" to "offside",
                    )
                    db.collection(collectionName).document(documentId)
                        .collection("offside")
                        .add(offsideData)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_offside_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeCentralMidfielderName.text}_offside_times", FieldValue.arrayUnion(offsideTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }
                }
        }

        //handler steal
        val btnSteal = dialogView.findViewById<TextView>(R.id.button_steal)
        btnSteal.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentSteal = it.getLong("${tvTeamHome.text}_steal") ?: 0
                    val currentCentralMidfielderSteal = it.getLong("${tvHomeCentralMidfielderName.text}_steal") ?: 0
                    val homeSteal = currentSteal + 1
                    val homeCentralMidfielderSteal = currentCentralMidfielderSteal + 1
                    db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_steal", homeSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeCentralMidfielderName.text}_steal", homeCentralMidfielderSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val stealTime = tvTimer.text.toString()
                    val stealData = hashMapOf(
                        "time" to stealTime,
                        "player" to "${tvHomeCentralMidfielderName.text}",
                        "action" to "steal",
                    )
                    db.collection(collectionName).document(documentId)
                        .collection("steal")
                        .add(stealData)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_steal_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeCentralMidfielderName.text}_steal_times", FieldValue.arrayUnion(stealTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler redCard
        val btnRedCard = dialogView.findViewById<TextView>(R.id.button_redCard)
        btnRedCard.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentRedCard = it.getLong("${tvTeamHome.text}_red_card") ?: 0
                    val currentCentralMidfielderRedCard = it.getLong("${tvHomeCentralMidfielderName.text}_red_card") ?: 0
                    val homeRedCard = currentRedCard + 1
                    val homeCentralMidfielderRedCard = currentCentralMidfielderRedCard + 1
                    db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_red_card", homeRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeCentralMidfielderName.text}_red_card", homeCentralMidfielderRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val redCardTime = tvTimer.text.toString()
                    val redCardData = hashMapOf(
                        "time" to redCardTime,
                        "player" to "${tvHomeCentralMidfielderName.text}",
                        "action" to "red_card",
                    )
                    db.collection(collectionName).document(documentId)
                        .collection("violations_card")
                        .add(redCardData)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_red_card_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeCentralMidfielderName.text}_red_card_times", FieldValue.arrayUnion(redCardTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler 10mfail
        val btn10mFail = dialogView.findViewById<TextView>(R.id.button_10mfail)
        btn10mFail.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val current10mFail = it.getLong("${tvTeamHome.text}_10m_fail") ?: 0
                    val currentCentralMidfielder10mFail = it.getLong("${tvHomeCentralMidfielderName.text}_10m_fail") ?: 0
                    val home10mFail = current10mFail + 1
                    val homeCentralMidfielder10mFail = currentCentralMidfielder10mFail + 1
                    db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_10m_fail", home10mFail).addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error updating document", e)
                    }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeCentralMidfielderName.text}_10m_fail", homeCentralMidfielder10mFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val _10mFailTime = tvTimer.text.toString()
                    val _10mFailData = hashMapOf(
                        "time" to _10mFailTime,
                        "player" to "${tvHomeCentralMidfielderName.text}",
                        "action" to "10m_fail",
                    )
                    db.collection(collectionName).document(documentId)
                        .collection("10m_fail")
                        .add(_10mFailData)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_10m_fail_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeCentralMidfielderName.text}_10m_fail_times", FieldValue.arrayUnion(_10mFailTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }
                }
        }

        //handler ShootGoalToogle
        val btnShootGoalToogle = dialogView.findViewById<TextView>(R.id.button_shootGoal)
        btnShootGoalToogle.setOnClickListener(){
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_match_goal, null)
            dialogBuilder.setView(dialogView)
            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            //handler ShootGoal
            val btnShootGoal = dialogView.findViewById<TextView>(R.id.button_goal_shoot)
            btnShootGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentShootGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentralMidfielderShootGoal = it.getLong("${tvHomeCentralMidfielderName.text}_shoot_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeCentralMidfielderShootGoal = currentCentralMidfielderShootGoal + 1
                        db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_goal", homeShootGoal).addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeCentralMidfielderName.text}_shoot_goal", homeCentralMidfielderShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val shootGoalTime = tvTimer.text.toString()
                        val shootGoalData = hashMapOf(
                            "time" to shootGoalTime,
                            "player" to "${tvHomeCentralMidfielderName.text}",
                            "action" to "shoot_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection("goals")
                            .add(shootGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_shoot_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeCentralMidfielderName.text}_shoot_goal_times", FieldValue.arrayUnion(shootGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler HealGoal
            val btnHealGoal = dialogView.findViewById<TextView>(R.id.button_goal_Heal)
            btnHealGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentHealGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentralMidfielderHealGoal = it.getLong("${tvHomeCentralMidfielderName.text}_heal_goal") ?: 0
                        val homeHealGoal = currentHealGoal + 1
                        val homeCentralMidfielderHealGoal = currentCentralMidfielderHealGoal + 1
                        db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_goal", homeHealGoal).addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeCentralMidfielderName.text}_heal_goal", homeCentralMidfielderHealGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healGoalTime = tvTimer.text.toString()
                        val healGoalData = hashMapOf(
                            "time" to healGoalTime,
                            "player" to "${tvHomeCentralMidfielderName.text}",
                            "action" to "heal_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection("goals")
                            .add(healGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_heal_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeCentralMidfielderName.text}_heal_goal_times", FieldValue.arrayUnion(healGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }
                    }
            }

            //handler valleyGoal
            val btnValleyGoal = dialogView.findViewById<TextView>(R.id.button_goal_valley)
            btnValleyGoal.setOnClickListener() {
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentValleyGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentralMidfielderValleyGoal = it.getLong("${tvHomeCentralMidfielderName.text}_valley_goal") ?: 0
                        val homeValleyGoal = currentValleyGoal + 1
                        val homeCentralMidfielderValleyGoal = currentCentralMidfielderValleyGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeValleyGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeCentralMidfielderName.text}_valley_goal",homeCentralMidfielderValleyGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val valleyGoalTime = tvTimer.text.toString()
                        val valleyGoalData = hashMapOf(
                            "time" to valleyGoalTime,
                            "player" to "${tvHomeCentralMidfielderName.text}",
                            "action" to "valley_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection("goals")
                            .add(valleyGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_valley_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeCentralMidfielderName.text}_valley_goal_times",
                                FieldValue.arrayUnion(valleyGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }
                    }
            }

            //handler LongGoal
            val btnLongGoal = dialogView.findViewById<TextView>(R.id.button_goal_long)
            btnLongGoal.setOnClickListener() {
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentLongGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentralMidfielderLongGoal = it.getLong("${tvHomeCentralMidfielderName.text}_long_goal") ?: 0
                        val homeLongGoal = currentLongGoal + 1
                        val homeCentralMidfielderLongGoal = currentCentralMidfielderLongGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeLongGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeCentralMidfielderName.text}_long_goal", homeCentralMidfielderLongGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val longGoalTime = tvTimer.text.toString()
                        val longGoalData = hashMapOf(
                            "time" to longGoalTime,
                            "player" to "${tvHomeCentralMidfielderName.text}",
                            "action" to "long_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection("goals")
                            .add(longGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_long_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeCentralMidfielderName.text}_long_goal_times",
                                FieldValue.arrayUnion(longGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }
                    }
            }

            //handler healedGoal
            val btnHealedGoal = dialogView.findViewById<TextView>(R.id.button_goal_healed)
            btnHealedGoal.setOnClickListener() {
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentHealedGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentralMidfielderHealedGoal =
                            it.getLong("${tvHomeCentralMidfielderName.text}_healed_goal") ?: 0
                        val homeHealedGoal = currentHealedGoal + 1
                        val homeCentralMidfielderHealedGoal = currentCentralMidfielderHealedGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeHealedGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeCentralMidfielderName.text}_healed_goal",
                                homeCentralMidfielderHealedGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healedGoalTime = tvTimer.text.toString()
                        val healedGoalData = hashMapOf(
                            "time" to healedGoalTime,
                            "player" to "${tvHomeCentralMidfielderName.text}",
                            "action" to "healed_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection("goals")
                            .add(healedGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_healed_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeCentralMidfielderName.text}_healed_goal_times",
                                FieldValue.arrayUnion(healedGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }
                    }
            }

            //handler lobGoal
            val btnLobGoal = dialogView.findViewById<TextView>(R.id.button_goal_lob)
            btnLobGoal.setOnClickListener() {
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentLobGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentralMidfielderLobGoal = it.getLong("${tvHomeCentralMidfielderName.text}_lob_goal") ?: 0
                        val homeLobGoal = currentLobGoal + 1
                        val homeCentralMidfielderLobGoal = currentCentralMidfielderLobGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeLobGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeCentralMidfielderName.text}_lob_goal", homeCentralMidfielderLobGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val lobGoalTime = tvTimer.text.toString()
                        val lobGoalData = hashMapOf(
                            "time" to lobGoalTime,
                            "player" to "${tvHomeCentralMidfielderName.text}",
                            "action" to "lob_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection("goals")
                            .add(lobGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_lob_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeCentralMidfielderName.text}_lob_goal_times",
                                FieldValue.arrayUnion(lobGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }
                    }
            }

            //hander foulGoal
            val btnFoulGoal = dialogView.findViewById<TextView>(R.id.button_goal_foul)
            btnFoulGoal.setOnClickListener() {
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentFoulGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentralMidfielderFoulGoal = it.getLong("${tvHomeCentralMidfielderName.text}_foul_goal") ?: 0
                        val homeFoulGoal = currentFoulGoal + 1
                        val homeCentralMidfielderFoulGoal = currentCentralMidfielderFoulGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeFoulGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeCentralMidfielderName.text}_foul_goal", homeCentralMidfielderFoulGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val foulGoalTime = tvTimer.text.toString()
                        val foulGoalData = hashMapOf(
                            "time" to foulGoalTime,
                            "player" to "${tvHomeCentralMidfielderName.text}",
                            "action" to "foul_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection("goals")
                            .add(foulGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_foul_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeCentralMidfielderName.text}_foul_goal_times",
                                FieldValue.arrayUnion(foulGoalTime)
                            )
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }
                    }
            }

            //handler reverseGoal
            val btnReverseGoal = dialogView.findViewById<TextView>(R.id.button_goal_reverse)
            btnReverseGoal.setOnClickListener() {
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentReverseGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentralMidfielderReverseGoal =
                            it.getLong("${tvHomeCentralMidfielderName.text}_reverse_goal") ?: 0
                        val homeReverseGoal = currentReverseGoal + 1
                        val homeCentralMidfielderReverseGoal =
                            currentCentralMidfielderReverseGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeReverseGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeCentralMidfielderName.text}_reverse_goal", homeCentralMidfielderReverseGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val reverseGoalTime = tvTimer.text.toString()
                        val reverseGoalData = hashMapOf(
                            "time" to reverseGoalTime,
                            "player" to "${tvHomeCentralMidfielderName.text}",
                            "action" to "reverse_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection("goals")
                            .add(reverseGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_reverse_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeCentralMidfielderName.text}_reverse_goal_times", FieldValue.arrayUnion(reverseGoalTime))
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }
                    }
            }

            //handler scissorsGoal
            val btnScissorsGoal = dialogView.findViewById<TextView>(R.id.button_goal_Scissors)
            btnScissorsGoal.setOnClickListener() {
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentScissorsGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentralMidfielderScissorsGoal = it.getLong("${tvHomeCentralMidfielderName.text}_scissors_goal") ?: 0
                        val homeScissorsGoal = currentScissorsGoal + 1
                        val homeCentralMidfielderScissorsGoal = currentCentralMidfielderScissorsGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeScissorsGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeCentralMidfielderName.text}_scissors_goal", homeCentralMidfielderScissorsGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val scissorsGoalTime = tvTimer.text.toString()
                        val scissorsGoalData = hashMapOf(
                            "time" to scissorsGoalTime,
                            "player" to "${tvHomeCentralMidfielderName.text}",
                            "action" to "scissors_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection("goals")
                            .add(scissorsGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_scissors_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeCentralMidfielderName.text}_scissors_goal_times", FieldValue.arrayUnion(scissorsGoalTime))
                            .addOnSuccessListener() {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() {
                                Log.w("Error", "Error updating document", it)
                            }
                    }
            }

            //handler otherGoal
            val btnOtherGoal = dialogView.findViewById<TextView>(R.id.button_goal_other)
            btnOtherGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentOtherGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentCentralMidfielderOtherGoal = it.getLong("${tvHomeCentralMidfielderName.text}_other_goal") ?: 0
                        val homeOtherGoal = currentOtherGoal + 1
                        val homeCentralMidfielderOtherGoal = currentCentralMidfielderOtherGoal + 1
                        db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_goal", homeOtherGoal).addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeCentralMidfielderName.text}_other_goal", homeCentralMidfielderOtherGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val otherGoalTime = tvTimer.text.toString()
                        val otherGoalData = hashMapOf(
                            "time" to otherGoalTime,
                            "player" to "${tvHomeCentralMidfielderName.text}",
                            "action" to "other_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection("goals")
                            .add(otherGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeCentralMidfielderName.text}_other_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeCentralMidfielderName.text}_other_goal_times", FieldValue.arrayUnion(otherGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }
        }

    }

    private fun showHomeAttackingMidfielderDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_match_aksi_landscape, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        val tvHomeAttackingMidfielderName = findViewById<TextView>(R.id.tv_goal_player7_name_home)
        val homeAttackMidfielderName = tvHomeAttackingMidfielderName.text.toString()
        val tvPemainAksi = dialogView.findViewById<TextView>(R.id.tv_tendangan_goal)
        tvPemainAksi.text = "Aksi Pemain: ${tvHomeAttackingMidfielderName.text}"


        //handler shootFail
        val btnShootFail = dialogView.findViewById<TextView>(R.id.button_shootFail)
        btnShootFail.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentShootFail = it.getLong("${tvTeamHome.text}_shoot_fail") ?: 0
                    val currentAttackingMidfielderShootFail = it.getLong("${tvHomeAttackingMidfielderName.text}_shoot_fail") ?: 0
                    val homeShootFail = currentShootFail + 1
                    val homeAttackingMidfielderShootFail = currentAttackingMidfielderShootFail + 1
                    db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_shoot_fail", homeShootFail).addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error updating document", e)
                    }
                    db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_shoot_fail", homeAttackingMidfielderShootFail).addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error updating document", e)
                    }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val shootFailTime = tvTimer.text.toString()
                    val shootFailData = hashMapOf(
                        "time" to shootFailTime,
                        "player" to "${tvHomeAttackingMidfielderName.text}",
                        "action" to "shoot_fail",
                    )
                    db.collection(collectionName).document(documentId).collection("shoot_fail").add(shootFailData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_shoot_fail_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_shoot_fail_times", FieldValue.arrayUnion(shootFailTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler Assist
        val btnAssist = dialogView.findViewById<TextView>(R.id.button_assist)
        btnAssist.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentAssist = it.getLong("${tvTeamHome.text}_assist") ?: 0
                    val currentAttackingMidfielderAssist = it.getLong("${tvHomeAttackingMidfielderName.text}_assist") ?: 0
                    val homeAssist = currentAssist + 1
                    val homeAttackingMidfielderAssist = currentAttackingMidfielderAssist + 1
                    db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_assist", homeAssist).addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error updating document", e)
                    }
                    db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_assist", homeAttackingMidfielderAssist).addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error updating document", e)
                    }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val assistTime = tvTimer.text.toString()
                    val assistData = hashMapOf(
                        "time" to assistTime,
                        "player" to "${tvHomeAttackingMidfielderName.text}",
                        "action" to "assist",
                    )
                    db.collection(collectionName).document(documentId).collection("assist").add(assistData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_assist_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_assist_times", FieldValue.arrayUnion(assistTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler 10mGoal
        val btn10mGoal = dialogView.findViewById<TextView>(R.id.button_10mGoal)
        btn10mGoal.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentTenMeterGoal = it.getLong("${tvTeamHome.text}_10m_goal") ?: 0
                    val currentAttackingMidfielderTenMeterGoal = it.getLong("${tvHomeAttackingMidfielderName.text}_10m_goal") ?: 0
                    val homeTenMeterGoal = currentTenMeterGoal + 1
                    val homeAttackingMidfielderTenMeterGoal = currentAttackingMidfielderTenMeterGoal + 1
                    db.collection(collectionName).document(documentId).update("${tvTeamHome.text}_10m_goal", homeTenMeterGoal).addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error updating document", e)
                    }
                    db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_10m_goal", homeAttackingMidfielderTenMeterGoal).addOnSuccessListener {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error updating document", e)
                    }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val tenMeterGoalTime = tvTimer.text.toString()
                    val tenMeterGoalData = hashMapOf(
                        "time" to tenMeterGoalTime,
                        "player" to "${tvHomeAttackingMidfielderName.text}",
                        "action" to "10m_goal",
                    )
                    db.collection(collectionName).document(documentId).collection("10m_goal").add(tenMeterGoalData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_10m_goal_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_10m_goal_times", FieldValue.arrayUnion(tenMeterGoalTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler yellowCard
        val btnYellowCard = dialogView.findViewById<TextView>(R.id.button_yellowCard)
        btnYellowCard.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "violations_card"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentYellowCard = it.getLong("${tvTeamHome.text}_yellow_card") ?: 0
                    val currentAttackingMidfielderYellowCard = it.getLong("${tvHomeAttackingMidfielderName.text}_yellow_card") ?: 0
                    val homeYellowCard = currentYellowCard + 1
                    val homeAttackingMidfielderYellowCard = currentAttackingMidfielderYellowCard + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_yellow_card", homeYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeAttackingMidfielderName.text}_yellow_card", homeAttackingMidfielderYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val yellowCardTime = tvTimer.text.toString()
                    val yellowCardData = hashMapOf(
                        "time" to yellowCardTime,
                        "player" to "${tvHomeAttackingMidfielderName.text}",
                        "action" to "yellow_card",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(yellowCardData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_yellow_card_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_yellow_card_times", FieldValue.arrayUnion(yellowCardTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler penalty
        val btnPenalty = dialogView.findViewById<TextView>(R.id.button_penalty)
        btnPenalty.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "penalty"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentPenalty = it.getLong("${tvTeamHome.text}_penalty") ?: 0
                    val currentAttackingMidfielderPenalty = it.getLong("${tvHomeAttackingMidfielderName.text}_penalty") ?: 0
                    val homePenalty = currentPenalty + 1
                    val homeAttackingMidfielderPenalty = currentAttackingMidfielderPenalty + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_penalty", homePenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeAttackingMidfielderName.text}_penalty", homeAttackingMidfielderPenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val penaltyTime = tvTimer.text.toString()
                    val penaltyData = hashMapOf(
                        "time" to penaltyTime,
                        "player" to "${tvHomeAttackingMidfielderName.text}",
                        "action" to "penalty",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(penaltyData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_penalty_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_penalty_times", FieldValue.arrayUnion(penaltyTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }


        //handler offside
        val btnOffside = dialogView.findViewById<TextView>(R.id.button_offside)
        btnOffside.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "offside"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener() {
                    val currentOffside = it.getLong("${tvTeamHome.text}_offside") ?: 0
                    val currentAttackingMidfielderOffside = it.getLong("${tvHomeAttackingMidfielderName.text}_offside") ?: 0
                    val homeOffside = currentOffside + 1
                    val homeAttackingMidfielderOffside = currentAttackingMidfielderOffside + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_offside", homeOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeAttackingMidfielderName.text}_offside", homeAttackingMidfielderOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val offsideTime = tvTimer.text.toString()
                    val offsideData = hashMapOf(
                        "time" to offsideTime,
                        "player" to "${tvHomeAttackingMidfielderName.text}",
                        "action" to "offside",
                    )
                    db.collection(collectionName).document(documentId)
                        .collection(collectionBranchName).add(offsideData)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                    //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_offside_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update(
                        "${tvHomeAttackingMidfielderName.text}_offside_times",
                        FieldValue.arrayUnion(offsideTime)
                    ).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener() {
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler steal
        val btnSteal = dialogView.findViewById<TextView>(R.id.button_steal)
        btnSteal.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "steal"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentSteal = it.getLong("${tvTeamHome.text}_steal") ?: 0
                    val currentAttackingMidfielderSteal = it.getLong("${tvHomeAttackingMidfielderName.text}_steal") ?: 0
                    val homeSteal = currentSteal + 1
                    val homeAttackingMidfielderSteal = currentAttackingMidfielderSteal + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_steal", homeSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeAttackingMidfielderName.text}_steal", homeAttackingMidfielderSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val stealTime = tvTimer.text.toString()
                    val stealData = hashMapOf(
                        "time" to stealTime,
                        "player" to "${tvHomeAttackingMidfielderName.text}",
                        "action" to "steal",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(stealData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_steal_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_steal_times", FieldValue.arrayUnion(stealTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler redCard
        val btnRedCard = dialogView.findViewById<TextView>(R.id.button_redCard)
        btnRedCard.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "violations_card"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentRedCard = it.getLong("${tvTeamHome.text}_red_card") ?: 0
                    val currentAttackingMidfielderRedCard = it.getLong("${tvHomeAttackingMidfielderName.text}_red_card") ?: 0
                    val homeRedCard = currentRedCard + 1
                    val homeAttackingMidfielderRedCard = currentAttackingMidfielderRedCard + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_red_card", homeRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeAttackingMidfielderName.text}_red_card", homeAttackingMidfielderRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val redCardTime = tvTimer.text.toString()
                    val redCardData = hashMapOf(
                        "time" to redCardTime,
                        "player" to "${tvHomeAttackingMidfielderName.text}",
                        "action" to "red_card",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(redCardData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                }
        }

        //handler 10mFail
        val btn10mFail = dialogView.findViewById<TextView>(R.id.button_10mfail)
        btn10mFail.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "10m_fail"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentTenMeterFail = it.getLong("${tvTeamHome.text}_10m_fail") ?: 0
                    val currentAttackingMidfielderTenMeterFail = it.getLong("${tvHomeAttackingMidfielderName.text}_10m_fail") ?: 0
                    val homeTenMeterFail = currentTenMeterFail + 1
                    val homeAttackingMidfielderTenMeterFail = currentAttackingMidfielderTenMeterFail + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_10m_fail", homeTenMeterFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeAttackingMidfielderName.text}_10m_fail", homeAttackingMidfielderTenMeterFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val tenMeterFailTime = tvTimer.text.toString()
                    val tenMeterFailData = hashMapOf(
                        "time" to tenMeterFailTime,
                        "player" to "${tvHomeAttackingMidfielderName.text}",
                        "action" to "10m_fail",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(tenMeterFailData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_10m_fail_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_10m_fail_times", FieldValue.arrayUnion(tenMeterFailTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }
                }
        }

        //handler ShootGoalToogle
        val toogleShootGoal = dialogView.findViewById<TextView>(R.id.button_shootGoal)
        toogleShootGoal.setOnClickListener(){
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_match_goal, null)
            dialogBuilder.setView(dialogView)
            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            //handler ShootGoal
            val btnShootGoal = dialogView.findViewById<TextView>(R.id.button_goal_shoot)
            btnShootGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentShootGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentAttackingMidfielderShootGoal = it.getLong("${tvHomeAttackingMidfielderName.text}_shoot_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeAttackingMidfielderShootGoal = currentAttackingMidfielderShootGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeAttackingMidfielderName.text}_shoot_goal", homeAttackingMidfielderShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val shootGoalTime = tvTimer.text.toString()
                        val shootGoalData = hashMapOf(
                            "time" to shootGoalTime,
                            "player" to "${tvHomeAttackingMidfielderName.text}",
                            "action" to "shoot_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(shootGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_shoot_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_shoot_goal_times", FieldValue.arrayUnion(shootGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler valleyGoal
            val btnValleyGoal = dialogView.findViewById<TextView>(R.id.button_goal_valley)
            btnValleyGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentValleyGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentAttackingMidfielderValleyGoal = it.getLong("${tvHomeAttackingMidfielderName.text}_valley_goal") ?: 0
                        val homeValleyGoal = currentValleyGoal + 1
                        val homeAttackingMidfielderValleyGoal = currentAttackingMidfielderValleyGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeValleyGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeAttackingMidfielderName.text}_valley_goal", homeAttackingMidfielderValleyGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val valleyGoalTime = tvTimer.text.toString()
                        val valleyGoalData = hashMapOf(
                            "time" to valleyGoalTime,
                            "player" to "${tvHomeAttackingMidfielderName.text}",
                            "action" to "valley_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(valleyGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_valley_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_valley_goal_times", FieldValue.arrayUnion(valleyGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler healedGoal
            val btnHealedGoal = dialogView.findViewById<TextView>(R.id.button_goal_healed)
            btnHealedGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentHealedGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentAttackingMidfielderHealedGoal = it.getLong("${tvHomeAttackingMidfielderName.text}_healed_goal") ?: 0
                        val homeHealedGoal = currentHealedGoal + 1
                        val homeAttackingMidfielderHealedGoal = currentAttackingMidfielderHealedGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeHealedGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeAttackingMidfielderName.text}_healed_goal", homeAttackingMidfielderHealedGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healedGoalTime = tvTimer.text.toString()
                        val healedGoalData = hashMapOf(
                            "time" to healedGoalTime,
                            "player" to "${tvHomeAttackingMidfielderName.text}",
                            "action" to "healed_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(healedGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_healed_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_healed_goal_times", FieldValue.arrayUnion(healedGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler foulGoal
            val btnFoulGoal = dialogView.findViewById<TextView>(R.id.button_goal_foul)
            btnFoulGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentFoulGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentAttackingMidfielderFoulGoal = it.getLong("${tvHomeAttackingMidfielderName.text}_foul_goal") ?: 0
                        val homeFoulGoal = currentFoulGoal + 1
                        val homeAttackingMidfielderFoulGoal = currentAttackingMidfielderFoulGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeFoulGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeAttackingMidfielderName.text}_foul_goal", homeAttackingMidfielderFoulGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val foulGoalTime = tvTimer.text.toString()
                        val foulGoalData = hashMapOf(
                            "time" to foulGoalTime,
                            "player" to "${tvHomeAttackingMidfielderName.text}",
                            "action" to "foul_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(foulGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_foul_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_foul_goal_times", FieldValue.arrayUnion(foulGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler scissorsGoal
            val btnScissorsGoal = dialogView.findViewById<TextView>(R.id.button_goal_Scissors)
            btnScissorsGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentScissorsGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentAttackingMidfielderScissorsGoal = it.getLong("${tvHomeAttackingMidfielderName.text}_scissors_goal") ?: 0
                        val homeScissorsGoal = currentScissorsGoal + 1
                        val homeAttackingMidfielderScissorsGoal = currentAttackingMidfielderScissorsGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeScissorsGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeAttackingMidfielderName.text}_scissors_goal", homeAttackingMidfielderScissorsGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val scissorsGoalTime = tvTimer.text.toString()
                        val scissorsGoalData = hashMapOf(
                            "time" to scissorsGoalTime,
                            "player" to "${tvHomeAttackingMidfielderName.text}",
                            "action" to "scissors_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(scissorsGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_scissors_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_scissors_goal_times", FieldValue.arrayUnion(scissorsGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler otherGoal
            val btnOtherGoal = dialogView.findViewById<TextView>(R.id.button_goal_other)
            btnOtherGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentOtherGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentAttackingMidfielderOtherGoal = it.getLong("${tvHomeAttackingMidfielderName.text}_other_goal") ?: 0
                        val homeOtherGoal = currentOtherGoal + 1
                        val homeAttackingMidfielderOtherGoal = currentAttackingMidfielderOtherGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeOtherGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeAttackingMidfielderName.text}_other_goal", homeAttackingMidfielderOtherGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val otherGoalTime = tvTimer.text.toString()
                        val otherGoalData = hashMapOf(
                            "time" to otherGoalTime,
                            "player" to "${tvHomeAttackingMidfielderName.text}",
                            "action" to "other_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(otherGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_other_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_other_goal_times", FieldValue.arrayUnion(otherGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler reverseGoal
            val btnReverseGoal = dialogView.findViewById<TextView>(R.id.button_goal_reverse)
            btnReverseGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentReverseGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentAttackingMidfielderReverseGoal = it.getLong("${tvHomeAttackingMidfielderName.text}_reverse_goal") ?: 0
                        val homeReverseGoal = currentReverseGoal + 1
                        val homeAttackingMidfielderReverseGoal = currentAttackingMidfielderReverseGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeReverseGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeAttackingMidfielderName.text}_reverse_goal", homeAttackingMidfielderReverseGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val reverseGoalTime = tvTimer.text.toString()
                        val reverseGoalData = hashMapOf(
                            "time" to reverseGoalTime,
                            "player" to "${tvHomeAttackingMidfielderName.text}",
                            "action" to "reverse_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(reverseGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_reverse_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_reverse_goal_times", FieldValue.arrayUnion(reverseGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler lobGoal
            val btnLobGoal = dialogView.findViewById<TextView>(R.id.button_goal_lob)
            btnLobGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentLobGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentAttackingMidfielderLobGoal = it.getLong("${tvHomeAttackingMidfielderName.text}_lob_goal") ?: 0
                        val homeLobGoal = currentLobGoal + 1
                        val homeAttackingMidfielderLobGoal = currentAttackingMidfielderLobGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeLobGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeAttackingMidfielderName.text}_lob_goal", homeAttackingMidfielderLobGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val lobGoalTime = tvTimer.text.toString()
                        val lobGoalData = hashMapOf(
                            "time" to lobGoalTime,
                            "player" to "${tvHomeAttackingMidfielderName.text}",
                            "action" to "lob_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(lobGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_lob_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_lob_goal_times", FieldValue.arrayUnion(lobGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler longGoal
            val btnLongGoal = dialogView.findViewById<TextView>(R.id.button_goal_long)
            btnLongGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentLongGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentAttackingMidfielderLongGoal = it.getLong("${tvHomeAttackingMidfielderName.text}_long_goal") ?: 0
                        val homeLongGoal = currentLongGoal + 1
                        val homeAttackingMidfielderLongGoal = currentAttackingMidfielderLongGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeLongGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeAttackingMidfielderName.text}_long_goal", homeAttackingMidfielderLongGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val longGoalTime = tvTimer.text.toString()
                        val longGoalData = hashMapOf(
                            "time" to longGoalTime,
                            "player" to "${tvHomeAttackingMidfielderName.text}",
                            "action" to "long_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(longGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_long_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_long_goal_times", FieldValue.arrayUnion(longGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler healGoal
            val btnHealGoal = dialogView.findViewById<TextView>(R.id.button_goal_Heal)
            btnHealGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentHealGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentAttackingMidfielderHealGoal = it.getLong("${tvHomeAttackingMidfielderName.text}_heal_goal") ?: 0
                        val homeHealGoal = currentHealGoal + 1
                        val homeAttackingMidfielderHealGoal = currentAttackingMidfielderHealGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeHealGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeAttackingMidfielderName.text}_heal_goal", homeAttackingMidfielderHealGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healGoalTime = tvTimer.text.toString()
                        val healGoalData = hashMapOf(
                            "time" to healGoalTime,
                            "player" to "${tvHomeAttackingMidfielderName.text}",
                            "action" to "heal_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(healGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeAttackingMidfielderName.text}_heal_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeAttackingMidfielderName.text}_heal_goal_times", FieldValue.arrayUnion(healGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }
        }
    }

    private fun showHomeLeftWingerDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_match_aksi_landscape, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        val tvHomeLeftWingerName = findViewById<TextView>(R.id.tv_goal_player8_name_home)
        val homeLeftWingerName = tvHomeLeftWingerName.text.toString()
        val tvPemainName = dialogView.findViewById<TextView>(R.id.tv_tendangan_goal)
        tvPemainName.text = "Aksi Pemain: $homeLeftWingerName"

        //handler ShootFail
        val btnShootFail = dialogView.findViewById<TextView>(R.id.button_shootFail)
        btnShootFail.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "shoot_fail"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentShootFail = it.getLong("${tvTeamHome.text}_shoot_fail") ?: 0
                    val currentLeftWingerShootFail = it.getLong("${tvHomeLeftWingerName.text}_shoot_fail") ?: 0
                    val homeShootFail = currentShootFail + 1
                    val homeLeftWingerShootFail = currentLeftWingerShootFail + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_shoot_fail", homeShootFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeLeftWingerName.text}_shoot_fail", homeLeftWingerShootFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val shootFailTime = tvTimer.text.toString()
                    val shootFailData = hashMapOf(
                        "time" to shootFailTime,
                        "player" to "${tvHomeLeftWingerName.text}",
                        "action" to "shoot_fail",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(shootFailData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_shoot_fail_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_shoot_fail_times", FieldValue.arrayUnion(shootFailTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler Assist
        val btnAssist = dialogView.findViewById<TextView>(R.id.button_assist)
        btnAssist.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "assist"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentAssist = it.getLong("${tvTeamHome.text}_assist") ?: 0
                    val currentLeftWingerAssist = it.getLong("${tvHomeLeftWingerName.text}_assist") ?: 0
                    val homeAssist = currentAssist + 1
                    val homeLeftWingerAssist = currentLeftWingerAssist + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_assist", homeAssist)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeLeftWingerName.text}_assist", homeLeftWingerAssist)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val assistTime = tvTimer.text.toString()
                    val assistData = hashMapOf(
                        "time" to assistTime,
                        "player" to "${tvHomeLeftWingerName.text}",
                        "action" to "assist",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(assistData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_assist_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_assist_times", FieldValue.arrayUnion(assistTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler 10mgoal
        val btn10mGoal = dialogView.findViewById<TextView>(R.id.button_10mGoal)
        btn10mGoal.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "10m_goal"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val current10mGoal = it.getLong("${tvTeamHome.text}_10m_goal") ?: 0
                    val currentLeftWinger10mGoal = it.getLong("${tvHomeLeftWingerName.text}_10m_goal") ?: 0
                    val home10mGoal = current10mGoal + 1
                    val homeLeftWinger10mGoal = currentLeftWinger10mGoal + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_10m_goal", home10mGoal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeLeftWingerName.text}_10m_goal", homeLeftWinger10mGoal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val tenMeterGoalTime = tvTimer.text.toString()
                    val tenMeterGoalData = hashMapOf(
                        "time" to tenMeterGoalTime,
                        "player" to "${tvHomeLeftWingerName.text}",
                        "action" to "10m_goal",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(tenMeterGoalData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_10m_goal_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_10m_goal_times", FieldValue.arrayUnion(tenMeterGoalTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler yellowCard
        val btnYellowCard = dialogView.findViewById<TextView>(R.id.button_yellowCard)
        btnYellowCard.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "violations_card"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentYellowCard = it.getLong("${tvTeamHome.text}_yellow_card") ?: 0
                    val currentLeftWingerYellowCard = it.getLong("${tvHomeLeftWingerName.text}_yellow_card") ?: 0
                    val homeYellowCard = currentYellowCard + 1
                    val homeLeftWingerYellowCard = currentLeftWingerYellowCard + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_yellow_card", homeYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeLeftWingerName.text}_yellow_card", homeLeftWingerYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val yellowCardTime = tvTimer.text.toString()
                    val yellowCardData = hashMapOf(
                        "time" to yellowCardTime,
                        "player" to "${tvHomeLeftWingerName.text}",
                        "action" to "yellow_card",
                    )

                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(yellowCardData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                }
        }

        //handler Penalty
        val btnPenalty = dialogView.findViewById<TextView>(R.id.button_penalty)
        btnPenalty.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "penalty"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentPenalty = it.getLong("${tvTeamHome.text}_penalty") ?: 0
                    val currentLeftWingerPenalty = it.getLong("${tvHomeLeftWingerName.text}_penalty") ?: 0
                    val homePenalty = currentPenalty + 1
                    val homeLeftWingerPenalty = currentLeftWingerPenalty + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_penalty", homePenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeLeftWingerName.text}_penalty", homeLeftWingerPenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val penaltyTime = tvTimer.text.toString()
                    val penaltyData = hashMapOf(
                        "time" to penaltyTime,
                        "player" to "${tvHomeLeftWingerName.text}",
                        "action" to "penalty",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(penaltyData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_penalty_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_penalty_times", FieldValue.arrayUnion(penaltyTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler offside
        val btnOffside = dialogView.findViewById<TextView>(R.id.button_offside)
        btnOffside.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "offside"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentOffside = it.getLong("${tvTeamHome.text}_offside") ?: 0
                    val currentLeftWingerOffside = it.getLong("${tvHomeLeftWingerName.text}_offside") ?: 0
                    val homeOffside = currentOffside + 1
                    val homeLeftWingerOffside = currentLeftWingerOffside + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_offside", homeOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeLeftWingerName.text}_offside", homeLeftWingerOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val offsideTime = tvTimer.text.toString()
                    val offsideData = hashMapOf(
                        "time" to offsideTime,
                        "player" to "${tvHomeLeftWingerName.text}",
                        "action" to "offside",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(offsideData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_offside_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_offside_times", FieldValue.arrayUnion(offsideTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler steal
        val btnSteal = dialogView.findViewById<TextView>(R.id.button_steal)
        btnSteal.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "steal"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentSteal = it.getLong("${tvTeamHome.text}_steal") ?: 0
                    val currentLeftWingerSteal = it.getLong("${tvHomeLeftWingerName.text}_steal") ?: 0
                    val homeSteal = currentSteal + 1
                    val homeLeftWingerSteal = currentLeftWingerSteal + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_steal", homeSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeLeftWingerName.text}_steal", homeLeftWingerSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val stealTime = tvTimer.text.toString()
                    val stealData = hashMapOf(
                        "time" to stealTime,
                        "player" to "${tvHomeLeftWingerName.text}",
                        "action" to "steal",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(stealData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_steal_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_steal_times", FieldValue.arrayUnion(stealTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler redCard
        val btnRedCard = dialogView.findViewById<TextView>(R.id.button_redCard)
        btnRedCard.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "violations_card"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentRedCard = it.getLong("${tvTeamHome.text}_red_card") ?: 0
                    val currentLeftWingerRedCard = it.getLong("${tvHomeLeftWingerName.text}_red_card") ?: 0
                    val homeRedCard = currentRedCard + 1
                    val homeLeftWingerRedCard = currentLeftWingerRedCard + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_red_card", homeRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeLeftWingerName.text}_red_card", homeLeftWingerRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val redCardTime = tvTimer.text.toString()
                    val redCardData = hashMapOf(
                        "time" to redCardTime,
                        "player" to "${tvHomeLeftWingerName.text}",
                        "action" to "red_card",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(redCardData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_red_card_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_red_card_times", FieldValue.arrayUnion(redCardTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler 10mfail
        val btn10mFail = dialogView.findViewById<TextView>(R.id.button_10mfail)
        btn10mFail.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "10m_fail"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val current10mFail = it.getLong("${tvTeamHome.text}_10m_fail") ?: 0
                    val currentLeftWinger10mFail = it.getLong("${tvHomeLeftWingerName.text}_10m_fail") ?: 0
                    val home10mFail = current10mFail + 1
                    val homeLeftWinger10mFail = currentLeftWinger10mFail + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_10m_fail", home10mFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeLeftWingerName.text}_10m_fail", homeLeftWinger10mFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val tenMeterFailTime = tvTimer.text.toString()
                    val tenMeterFailData = hashMapOf(
                        "time" to tenMeterFailTime,
                        "player" to "${tvHomeLeftWingerName.text}",
                        "action" to "10m_fail",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(tenMeterFailData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_10m_fail_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_10m_fail_times", FieldValue.arrayUnion(tenMeterFailTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler shootGoalToogle
        val btnShootGoalToggle = dialogView.findViewById<TextView>(R.id.button_shootGoal)
        btnShootGoalToggle.setOnClickListener(){
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_match_goal, null)
            dialogBuilder.setView(dialogView)
            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            //handler shootGoal
            val btnShootGoal = dialogView.findViewById<TextView>(R.id.button_goal_shoot)
            btnShootGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentShootGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftWingerShootGoal = it.getLong("${tvHomeLeftWingerName.text}_shoot_goal") ?: 0
                        val homeShootGoal = currentShootGoal + 1
                        val homeLeftWingerShootGoal = currentLeftWingerShootGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeLeftWingerName.text}_shoot_goal", homeLeftWingerShootGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val shootGoalTime = tvTimer.text.toString()
                        val shootGoalData = hashMapOf(
                            "time" to shootGoalTime,
                            "player" to "${tvHomeLeftWingerName.text}",
                            "action" to "shoot_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(shootGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_shoot_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_shoot_goal_times", FieldValue.arrayUnion(shootGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler valleyGoal
            val btnValleyGoal = dialogView.findViewById<TextView>(R.id.button_goal_valley)
            btnValleyGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentValleyGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftWingerValleyGoal = it.getLong("${tvHomeLeftWingerName.text}_valley_goal") ?: 0
                        val homeValleyGoal = currentValleyGoal + 1
                        val homeLeftWingerValleyGoal = currentLeftWingerValleyGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeValleyGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeLeftWingerName.text}_valley_goal", homeLeftWingerValleyGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val valleyGoalTime = tvTimer.text.toString()
                        val valleyGoalData = hashMapOf(
                            "time" to valleyGoalTime,
                            "player" to "${tvHomeLeftWingerName.text}",
                            "action" to "valley_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(valleyGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_valley_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_valley_goal_times", FieldValue.arrayUnion(valleyGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler healedGoal
            val btnHealedGoal = dialogView.findViewById<TextView>(R.id.button_goal_healed)
            btnHealedGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentHealedGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftWingerHealedGoal = it.getLong("${tvHomeLeftWingerName.text}_healed_goal") ?: 0
                        val homeHealedGoal = currentHealedGoal + 1
                        val homeLeftWingerHealedGoal = currentLeftWingerHealedGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeHealedGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeLeftWingerName.text}_healed_goal", homeLeftWingerHealedGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healedGoalTime = tvTimer.text.toString()
                        val healedGoalData = hashMapOf(
                            "time" to healedGoalTime,
                            "player" to "${tvHomeLeftWingerName.text}",
                            "action" to "healed_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(healedGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_healed_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_healed_goal_times", FieldValue.arrayUnion(healedGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler Foul
            val btnFoul = dialogView.findViewById<TextView>(R.id.button_goal_foul)
            btnFoul.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentFoul = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftWingerFoul = it.getLong("${tvHomeLeftWingerName.text}_foul_goal") ?: 0
                        val homeFoul = currentFoul + 1
                        val homeLeftWingerFoul = currentLeftWingerFoul + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeFoul)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeLeftWingerName.text}_foul_goal", homeLeftWingerFoul)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val foulTime = tvTimer.text.toString()
                        val foulData = hashMapOf(
                            "time" to foulTime,
                            "player" to "${tvHomeLeftWingerName.text}",
                            "action" to "foul",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(foulData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_foul_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_foul_times", FieldValue.arrayUnion(foulTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler scissorsGoal
            val btnScissorsGoal = dialogView.findViewById<TextView>(R.id.button_goal_Scissors)
            btnScissorsGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentScissorsGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftWingerScissorsGoal = it.getLong("${tvHomeLeftWingerName.text}_scissors_goal") ?: 0
                        val homeScissorsGoal = currentScissorsGoal + 1
                        val homeLeftWingerScissorsGoal = currentLeftWingerScissorsGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeScissorsGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeLeftWingerName.text}_scissors_goal", homeLeftWingerScissorsGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val scissorsGoalTime = tvTimer.text.toString()
                        val scissorsGoalData = hashMapOf(
                            "time" to scissorsGoalTime,
                            "player" to "${tvHomeLeftWingerName.text}",
                            "action" to "scissors_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(scissorsGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_scissors_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_scissors_goal_times", FieldValue.arrayUnion(scissorsGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler otherGoal
            val btnOtherGoal = dialogView.findViewById<TextView>(R.id.button_goal_other)
            btnOtherGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentOtherGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftWingerOtherGoal = it.getLong("${tvHomeLeftWingerName.text}_other_goal") ?: 0
                        val homeOtherGoal = currentOtherGoal + 1
                        val homeLeftWingerOtherGoal = currentLeftWingerOtherGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeOtherGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeLeftWingerName.text}_other_goal", homeLeftWingerOtherGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val otherGoalTime = tvTimer.text.toString()
                        val otherGoalData = hashMapOf(
                            "time" to otherGoalTime,
                            "player" to "${tvHomeLeftWingerName.text}",
                            "action" to "other_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(otherGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_other_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_other_goal_times", FieldValue.arrayUnion(otherGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler reverseGoal
            val btnReverseGoal = dialogView.findViewById<TextView>(R.id.button_goal_reverse)
            btnReverseGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentReverseGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftWingerReverseGoal = it.getLong("${tvHomeLeftWingerName.text}_reverse_goal") ?: 0
                        val homeReverseGoal = currentReverseGoal + 1
                        val homeLeftWingerReverseGoal = currentLeftWingerReverseGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeReverseGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeLeftWingerName.text}_reverse_goal", homeLeftWingerReverseGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val reverseGoalTime = tvTimer.text.toString()
                        val reverseGoalData = hashMapOf(
                            "time" to reverseGoalTime,
                            "player" to "${tvHomeLeftWingerName.text}",
                            "action" to "reverse_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(reverseGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_reverse_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_reverse_goal_times", FieldValue.arrayUnion(reverseGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler lobGoal
            val btnLobGoal = dialogView.findViewById<TextView>(R.id.button_goal_lob)
            btnLobGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentLobGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftWingerLobGoal = it.getLong("${tvHomeLeftWingerName.text}_lob_goal") ?: 0
                        val homeLobGoal = currentLobGoal + 1
                        val homeLeftWingerLobGoal = currentLeftWingerLobGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeLobGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeLeftWingerName.text}_lob_goal", homeLeftWingerLobGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val lobGoalTime = tvTimer.text.toString()
                        val lobGoalData = hashMapOf(
                            "time" to lobGoalTime,
                            "player" to "${tvHomeLeftWingerName.text}",
                            "action" to "lob_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(lobGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_lob_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_lob_goal_times", FieldValue.arrayUnion(lobGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler LongGoal
            val btnLongGoal = dialogView.findViewById<TextView>(R.id.button_goal_long)
            btnLongGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentLongGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftWingerLongGoal =
                            it.getLong("${tvHomeLeftWingerName.text}_long_goal") ?: 0
                        val homeLongGoal = currentLongGoal + 1
                        val homeLeftWingerLongGoal = currentLeftWingerLongGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeLongGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update(
                                "${tvHomeLeftWingerName.text}_long_goal",
                                homeLeftWingerLongGoal
                            )
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val longGoalTime = tvTimer.text.toString()
                        val longGoalData = hashMapOf(
                            "time" to longGoalTime,
                            "player" to "${tvHomeLeftWingerName.text}",
                            "action" to "long_goal",
                        )
                        db.collection(collectionName).document(documentId)
                            .collection(collectionBranchName).add(longGoalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error adding document", e)
                            }

                        //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_long_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update(
                            "${tvHomeLeftWingerName.text}_long_goal_times",
                            FieldValue.arrayUnion(longGoalTime)
                        ).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() {
                            Log.w("Error", "Error updating document", it)
                        }
                    }
            }
            //handler healGoal
            val btnHealGoal = dialogView.findViewById<TextView>(R.id.button_goal_Heal)
            btnHealGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentHealGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentLeftWingerHealGoal = it.getLong("${tvHomeLeftWingerName.text}_heal_goal") ?: 0
                        val homeHealGoal = currentHealGoal + 1
                        val homeLeftWingerHealGoal = currentLeftWingerHealGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeHealGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_heal_goal", homeLeftWingerHealGoal).addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val healGoalTime = tvTimer.text.toString()
                        val healGoalData = hashMapOf(
                            "time" to healGoalTime,
                            "player" to "${tvHomeLeftWingerName.text}",
                            "action" to "heal_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(healGoalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeLeftWingerName.text}_heal_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeLeftWingerName.text}_heal_goal_times", FieldValue.arrayUnion(healGoalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }
        }
    }


    private fun setLeagueDate() {
        matchDate = intent.getStringExtra("matchDate").toString()
        println("Tanggal Match Yang Diterima: $matchDate")

        tvLeagueDate = findViewById<TextView>(R.id.league_date)
        tvLeagueDate.text = matchDate
    }

    @SuppressLint("SetTextI18n")
    private fun setLeagueName() {
        matchName = intent.getStringExtra("matchName").toString()
        println("Nama Match Yang Diterima: $matchName")

        tvLeagueName = findViewById<TextView>(R.id.league_name)
        tvLeagueName.text = matchName
    }

    @SuppressLint("SetTextI18n")
    private fun setHomeTeam() {
        homeTeam = intent.getStringExtra("matchHomeTeam").toString()
        println("Nama Home Team Yang Diterima: $homeTeam")

        tvTeamHome = findViewById<TextView>(R.id.team_name_home)
        tvTeamHome.text = homeTeam
    }

    @SuppressLint("SetTextI18n")
    private fun setAwayTeam() {
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
                    val formattedTime = String.format(
                        "%02d:%02d:%02d",
                        remainingHours,
                        remainingMinutes % 60,
                        remainingSeconds % 60
                    )
                    tvTimer.text = formattedTime
                }

                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    tvTimer = findViewById<TextView>(R.id.timerTextView)
                    tvTimer.text = "00:00:00"
                }
            }.start()
        } else {
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
                    gkConstraint.setOnClickListener() {
                        showGoalKeeperDialog()
                    }

                    val namaHomeGoalKeeper = "${tvHomeGoalKeeperName.text}"
                    val documentId = documentId
                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_goal_keeper", namaHomeGoalKeeper)
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

                    val centreBackConstraint =
                        findViewById<ConstraintLayout>(R.id.gk_constraint_home_centreback)
                    centreBackConstraint.setOnClickListener() {
                        showHomeCentreBackDialog()
                    }

                    val namaHomeCentreBack = "${tvHomeCentreBackName.text}"
                    val documentId = documentId
                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_centre_back", namaHomeCentreBack)
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

                    val leftBackConstraint =
                        findViewById<ConstraintLayout>(R.id.gk_constraint_home_lefback)
                    leftBackConstraint.setOnClickListener() {
                        showHomeLeftBackDialog()
                    }

                    val namaHomeLeftBack = "${tvHomeLeftBackName.text}"
                    val documentId = documentId
                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_left_back", namaHomeLeftBack)
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

                    val rightBackConstraint = findViewById<ConstraintLayout>(R.id.gk_constraint_home_rightback)
                    rightBackConstraint.setOnClickListener() {
                        showHomeRightBackDialog()
                    }

                    val namaHomeRightBack = "${tvHomeRightBackName.text}"
                    val documentId = documentId
                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_right_back", namaHomeRightBack)
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

                    val defensiveMidfielderConstraint = findViewById<ConstraintLayout>(R.id.gk_constraint_home_defensive_midfielder)
                    defensiveMidfielderConstraint.setOnClickListener() {
                        showHomeDefensiveMidfielderDialog()
                    }
                    val nameHomeDefensiveMidfielder = "${tvHomeDefensiveMidfielderName.text}"
                    val documentId = documentId
                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_defensive_midfielder", nameHomeDefensiveMidfielder)
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

                    val centralMidfielderConstraint = findViewById<ConstraintLayout>(R.id.gk_constraint_home_central_midfielder)
                    centralMidfielderConstraint.setOnClickListener() {
                        showHomeCentralMidfielderDialog()
                    }
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

                    val attackingMidfielderConstraint = findViewById<ConstraintLayout>(R.id.gk_constraint_home_attacking_midfielder)
                    attackingMidfielderConstraint.setOnClickListener() {
                        showHomeAttackingMidfielderDialog()
                    }

                    val nameHomeAttackingMidfielder = "${tvHomeAttackingMidfielderName.text}"
                    val documentId = documentId
                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_attacking_midfielder", nameHomeAttackingMidfielder)
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

                    val leftWingerConstraint = findViewById<ConstraintLayout>(R.id.gk_constraint_home_left_winger)
                    leftWingerConstraint.setOnClickListener() {
                        showHomeLeftWingerDialog()
                    }

                    val nameHomeLeftWinger = "${tvHomeLeftWingerName.text}"
                    val documentId = documentId
                    db.collection("matchStats").document(documentId)
                        .update("${tvTeamHome.text}_left_winger", nameHomeLeftWinger)
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

                    val rightWingerConstraint = findViewById<ConstraintLayout>(R.id.gk_constraint_home_right_winger)
                    rightWingerConstraint.setOnClickListener() {
                        showHomeRightWingerDialog()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun showHomeRightWingerDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_match_aksi_landscape, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        val homeRightWingerName = findViewById<TextView>(R.id.tv_goal_player9_name_home)
        val homeRightWingerNameString = homeRightWingerName.text.toString()
        val tvNamaPemain = dialogView.findViewById<TextView>(R.id.tv_tendangan_goal)
        tvNamaPemain.text = "Aksi Pemain: $homeRightWingerNameString"

        //handler shootFail
        val btnShootFail = dialogView.findViewById<TextView>(R.id.button_shootFail)
        btnShootFail.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "shoot_fail"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentShootFail = it.getLong("${tvTeamHome.text}_shoot_fail") ?: 0
                    val currentRightWingerShootFail = it.getLong("${tvHomeRightWingerName.text}_shoot_fail") ?: 0
                    val homeShootFail = currentShootFail + 1
                    val homeRightWingerShootFail = currentRightWingerShootFail + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_shoot_fail", homeShootFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeRightWingerName.text}_shoot_fail", homeRightWingerShootFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val shootFailTime = tvTimer.text.toString()
                    val shootFailData = hashMapOf(
                        "time" to shootFailTime,
                        "player" to "${tvHomeRightWingerName.text}",
                        "action" to "shoot_fail",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(shootFailData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_shoot_fail_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_shoot_fail_times", FieldValue.arrayUnion(shootFailTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler Assist
        val btnAssist = dialogView.findViewById<TextView>(R.id.button_assist)
        btnAssist.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "assist"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentAssist = it.getLong("${tvTeamHome.text}_assist") ?: 0
                    val currentRightWingerAssist = it.getLong("${tvHomeRightWingerName.text}_assist") ?: 0
                    val homeAssist = currentAssist + 1
                    val homeRightWingerAssist = currentRightWingerAssist + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_assist", homeAssist)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }
                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeRightWingerName.text}_assist", homeRightWingerAssist)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val assistTime = tvTimer.text.toString()
                    val assistData = hashMapOf(
                        "time" to assistTime,
                        "player" to "${tvHomeRightWingerName.text}",
                        "action" to "assist",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(assistData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_assist_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_assist_times", FieldValue.arrayUnion(assistTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler 10mgoal
        val btn10mGoal = dialogView.findViewById<TextView>(R.id.button_10mGoal)
        btn10mGoal.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "10m_goal"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val current10mGoal = it.getLong("${tvTeamHome.text}_10m_goal") ?: 0
                    val currentRightWinger10mGoal = it.getLong("${tvHomeRightWingerName.text}_10m_goal") ?: 0
                    val home10mGoal = current10mGoal + 1
                    val homeRightWinger10mGoal = currentRightWinger10mGoal + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_10m_goal", home10mGoal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeRightWingerName.text}_10m_goal", homeRightWinger10mGoal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val tenMeterGoalTime = tvTimer.text.toString()
                    val tenMeterGoalData = hashMapOf(
                        "time" to tenMeterGoalTime,
                        "player" to "${tvHomeRightWingerName.text}",
                        "action" to "10m_goal",
                    )
                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(tenMeterGoalData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_10m_goal_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_10m_goal_times", FieldValue.arrayUnion(tenMeterGoalTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler Yellow Card
        val btnYellowCard = dialogView.findViewById<TextView>(R.id.button_yellowCard)
        btnYellowCard.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "violations_card"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentYellowCard = it.getLong("${tvTeamHome.text}_yellow_card") ?: 0
                    val currentRightWingerYellowCard = it.getLong("${tvHomeRightWingerName.text}_yellow_card") ?: 0
                    val homeYellowCard = currentYellowCard + 1
                    val homeRightWingerYellowCard = currentRightWingerYellowCard + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_yellow_card", homeYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeRightWingerName.text}_yellow_card", homeRightWingerYellowCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val yellowCardTime = tvTimer.text.toString()
                    val yellowCardData = hashMapOf(
                        "time" to yellowCardTime,
                        "player" to "${tvHomeRightWingerName.text}",
                        "action" to "yellow_card",
                    )

                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(yellowCardData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_yellow_card_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_yellow_card_times", FieldValue.arrayUnion(yellowCardTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler penalty
        val btnPenalty = dialogView.findViewById<TextView>(R.id.button_penalty)
        btnPenalty.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "penalty"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentPenalty = it.getLong("${tvTeamHome.text}_penalty") ?: 0
                    val currentRightWingerPenalty = it.getLong("${tvHomeRightWingerName.text}_penalty") ?: 0
                    val homePenalty = currentPenalty + 1
                    val homeRightWingerPenalty = currentRightWingerPenalty + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_penalty", homePenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeRightWingerName.text}_penalty", homeRightWingerPenalty)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val penaltyTime = tvTimer.text.toString()
                    val penaltyData = hashMapOf(
                        "time" to penaltyTime,
                        "player" to "${tvHomeRightWingerName.text}",
                        "action" to "penalty",
                    )

                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(penaltyData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_penalty_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_penalty_times", FieldValue.arrayUnion(penaltyTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler offside
        val btnOffside = dialogView.findViewById<TextView>(R.id.button_offside)
        btnOffside.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "violations_offside"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentOffside = it.getLong("${tvTeamHome.text}_offside") ?: 0
                    val currentRightWingerOffside = it.getLong("${tvHomeRightWingerName.text}_offside") ?: 0
                    val homeOffside = currentOffside + 1
                    val homeRightWingerOffside = currentRightWingerOffside + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_offside", homeOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeRightWingerName.text}_offside", homeRightWingerOffside)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val offsideTime = tvTimer.text.toString()
                    val offsideData = hashMapOf(
                        "time" to offsideTime,
                        "player" to "${tvHomeRightWingerName.text}",
                        "action" to "offside",
                    )

                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(offsideData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_offside_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_offside_times", FieldValue.arrayUnion(offsideTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }
                }
        }

        //handler steal
        val btnSteal = dialogView.findViewById<TextView>(R.id.button_steal)
        btnSteal.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "steal"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentSteal = it.getLong("${tvTeamHome.text}_steal") ?: 0
                    val currentRightWingerSteal = it.getLong("${tvHomeRightWingerName.text}_steal") ?: 0
                    val homeSteal = currentSteal + 1
                    val homeRightWingerSteal = currentRightWingerSteal + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_steal", homeSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeRightWingerName.text}_steal", homeRightWingerSteal)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val stealTime = tvTimer.text.toString()
                    val stealData = hashMapOf(
                        "time" to stealTime,
                        "player" to "${tvHomeRightWingerName.text}",
                        "action" to "steal",
                    )

                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(stealData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_steal_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_steal_times", FieldValue.arrayUnion(stealTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler redCard
        val btnRedCard = dialogView.findViewById<TextView>(R.id.button_redCard)
        btnRedCard.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "violations_card"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val currentRedCard = it.getLong("${tvTeamHome.text}_red_card") ?: 0
                    val currentRightWingerRedCard = it.getLong("${tvHomeRightWingerName.text}_red_card") ?: 0
                    val homeRedCard = currentRedCard + 1
                    val homeRightWingerRedCard = currentRightWingerRedCard + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_red_card", homeRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeRightWingerName.text}_red_card", homeRightWingerRedCard)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val redCardTime = tvTimer.text.toString()
                    val redCardData = hashMapOf(
                        "time" to redCardTime,
                        "player" to "${tvHomeRightWingerName.text}",
                        "action" to "red_card",
                    )

                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(redCardData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_red_card_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_red_card_times", FieldValue.arrayUnion(redCardTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }


                }
        }

        //handler 10mfail
        val btn10mFail = dialogView.findViewById<TextView>(R.id.button_10mfail)
        btn10mFail.setOnClickListener(){
            val documentId = documentId
            val collectionName = "matchStats"
            val collectionBranchName = "10m_fail"
            db.collection(collectionName).document(documentId)
                .get()
                .addOnSuccessListener(){
                    val current10mFail = it.getLong("${tvTeamHome.text}_10m_fail") ?: 0
                    val currentRightWinger10mFail = it.getLong("${tvHomeRightWingerName.text}_10m_fail") ?: 0
                    val home10mFail = current10mFail + 1
                    val homeRightWinger10mFail = currentRightWinger10mFail + 1
                    db.collection(collectionName).document(documentId)
                        .update("${tvTeamHome.text}_10m_fail", home10mFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    db.collection(collectionName).document(documentId)
                        .update("${tvHomeRightWingerName.text}_10m_fail", homeRightWinger10mFail)
                        .addOnSuccessListener {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                    val tvTimer = findViewById<TextView>(R.id.timerTextView)
                    val tenMeterFailTime = tvTimer.text.toString()
                    val tenMeterFailData = hashMapOf(
                        "time" to tenMeterFailTime,
                        "player" to "${tvHomeRightWingerName.text}",
                        "action" to "10m_fail",
                    )

                    db.collection(collectionName).document(documentId).collection(collectionBranchName).add(tenMeterFailData).addOnSuccessListener { documentReference ->
                        Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){ e ->
                        Log.w("Error", "Error adding document", e)
                    }

                    //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_10m_fail_times dalam bentuk Array
                    db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_10m_fail_times", FieldValue.arrayUnion(tenMeterFailTime)).addOnSuccessListener() {
                        Log.d("Success", "DocumentSnapshot successfully updated!")
                        alertDialog.dismiss()
                    }.addOnFailureListener(){
                        Log.w("Error", "Error updating document", it)
                    }

                }
        }

        //handler ToogleShootGoalOption
        val btnToogleShootGoalOption = dialogView.findViewById<TextView>(R.id.button_shootGoal)
        btnToogleShootGoalOption.setOnClickListener(){
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_match_goal, null)
            dialogBuilder.setView(dialogView)
            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            //handler shootGoal
            val btnShootGoal = dialogView.findViewById<TextView>(R.id.button_goal_shoot)
            btnShootGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightWingerGoal = it.getLong("${tvHomeRightWingerName.text}_shoot_goal") ?: 0
                        val homeGoal = currentGoal + 1
                        val homeRightWingerGoal = currentRightWingerGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeRightWingerName.text}_shoot_goal", homeRightWingerGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        val goalData = hashMapOf(
                            "time" to goalTime,
                            "player" to "${tvHomeRightWingerName.text}",
                            "action" to "goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(goalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_shoot_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_shoot_goal_times", FieldValue.arrayUnion(goalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler valleyGoal
            val btnValleyGoal = dialogView.findViewById<TextView>(R.id.button_goal_valley)
            btnValleyGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightWingerGoal = it.getLong("${tvHomeRightWingerName.text}_valley_goal") ?: 0
                        val homeGoal = currentGoal + 1
                        val homeRightWingerGoal = currentRightWingerGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeRightWingerName.text}_valley_goal", homeRightWingerGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        val goalData = hashMapOf(
                            "time" to goalTime,
                            "player" to "${tvHomeRightWingerName.text}",
                            "action" to "valley_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(goalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_valley_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_valley_goal_times", FieldValue.arrayUnion(goalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler healedGoal
            val btnHealedGoal = dialogView.findViewById<TextView>(R.id.button_goal_healed)
            btnHealedGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightWingerGoal = it.getLong("${tvHomeRightWingerName.text}_healed_goal") ?: 0
                        val homeGoal = currentGoal + 1
                        val homeRightWingerGoal = currentRightWingerGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeRightWingerName.text}_healed_goal", homeRightWingerGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        val goalData = hashMapOf(
                            "time" to goalTime,
                            "player" to "${tvHomeRightWingerName.text}",
                            "action" to "healed_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(goalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_healed_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_healed_goal_times", FieldValue.arrayUnion(goalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }


                    }
            }

            //handler foulGoal
            val btnFoulGoal = dialogView.findViewById<TextView>(R.id.button_goal_foul)
            btnFoulGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightWingerGoal = it.getLong("${tvHomeRightWingerName.text}_foul_goal") ?: 0
                        val homeGoal = currentGoal + 1
                        val homeRightWingerGoal = currentRightWingerGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeRightWingerName.text}_foul_goal", homeRightWingerGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        val goalData = hashMapOf(
                            "time" to goalTime,
                            "player" to "${tvHomeRightWingerName.text}",
                            "action" to "foul_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(goalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_foul_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_foul_goal_times", FieldValue.arrayUnion(goalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler scissorsGoal
            val btnScissorsGoal = dialogView.findViewById<TextView>(R.id.button_goal_Scissors)
            btnScissorsGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightWingerGoal = it.getLong("${tvHomeRightWingerName.text}_scissors_goal") ?: 0
                        val homeGoal = currentGoal + 1
                        val homeRightWingerGoal = currentRightWingerGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeRightWingerName.text}_scissors_goal", homeRightWingerGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        val goalData = hashMapOf(
                            "time" to goalTime,
                            "player" to "${tvHomeRightWingerName.text}",
                            "action" to "scissors_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(goalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_scissors_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_scissors_goal_times", FieldValue.arrayUnion(goalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler otherGoal
            val btnOtherGoal = dialogView.findViewById<TextView>(R.id.button_goal_other)
            btnOtherGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightWingerGoal = it.getLong("${tvHomeRightWingerName.text}_other_goal") ?: 0
                        val homeGoal = currentGoal + 1
                        val homeRightWingerGoal = currentRightWingerGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }
                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeRightWingerName.text}_other_goal", homeRightWingerGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        val goalData = hashMapOf(
                            "time" to goalTime,
                            "player" to "${tvHomeRightWingerName.text}",
                            "action" to "other_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(goalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_other_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_other_goal_times", FieldValue.arrayUnion(goalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler reverseGoal
            val btnReverseGoal = dialogView.findViewById<TextView>(R.id.button_goal_reverse)
            btnReverseGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightWingerGoal = it.getLong("${tvHomeRightWingerName.text}_reverse_goal") ?: 0
                        val homeGoal = currentGoal + 1
                        val homeRightWingerGoal = currentRightWingerGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeRightWingerName.text}_reverse_goal", homeRightWingerGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        val goalData = hashMapOf(
                            "time" to goalTime,
                            "player" to "${tvHomeRightWingerName.text}",
                            "action" to "reverse_goal",
                        )
                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(goalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_reverse_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_reverse_goal_times", FieldValue.arrayUnion(goalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler lobGoal
            val btnLobGoal = dialogView.findViewById<TextView>(R.id.button_goal_lob)
            btnLobGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener() {
                        val currentGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightWingerGoal =
                            it.getLong("${tvHomeRightWingerName.text}_lob_goal") ?: 0
                        val homeGoal = currentGoal + 1
                        val homeRightWingerGoal = currentRightWingerGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeRightWingerName.text}_lob_goal", homeRightWingerGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                                Log.w("Error", "Error updating document", e)
                            }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        val goalData = hashMapOf(
                            "time" to goalTime,
                            "player" to "${tvHomeRightWingerName.text}",
                            "action" to "lob_goal",
                        )

                        db.collection(collectionName).document(documentId)
                            .collection(collectionBranchName).add(goalData)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    "Success",
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                                alertDialog.dismiss()
                            }.addOnFailureListener() { e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_lob_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update(
                            "${tvHomeRightWingerName.text}_lob_goal_times",
                            FieldValue.arrayUnion(goalTime)
                        ).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener() {
                            Log.w("Error", "Error updating document", it)
                        }
                    }
            }

            //handler longGoal
            val btnLongGoal = dialogView.findViewById<TextView>(R.id.button_goal_long)
            btnLongGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightWingerGoal = it.getLong("${tvHomeRightWingerName.text}_long_goal") ?: 0
                        val homeGoal = currentGoal + 1
                        val homeRightWingerGoal = currentRightWingerGoal + 1
                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeRightWingerName.text}_long_goal", homeRightWingerGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()
                        val goalData = hashMapOf(
                            "time" to goalTime,
                            "player" to "${tvHomeRightWingerName.text}",
                            "action" to "long_goal",
                        )

                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(goalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_long_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_long_goal_times", FieldValue.arrayUnion(goalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }

            //handler healGoal
            val btnHealGoal = dialogView.findViewById<TextView>(R.id.button_goal_Heal)
            btnHealGoal.setOnClickListener(){
                val documentId = documentId
                val collectionName = "matchStats"
                val collectionBranchName = "goals"
                db.collection(collectionName).document(documentId)
                    .get()
                    .addOnSuccessListener(){
                        val currentGoal = it.getLong("${tvTeamHome.text}_goal") ?: 0
                        val currentRightWingerGoal = it.getLong("${tvHomeRightWingerName.text}_heal_goal") ?: 0
                        val homeGoal = currentGoal + 1
                        val homeRightWingerGoal = currentRightWingerGoal + 1

                        db.collection(collectionName).document(documentId)
                            .update("${tvTeamHome.text}_goal", homeGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                        db.collection(collectionName).document(documentId)
                            .update("${tvHomeRightWingerName.text}_heal_goal", homeRightWingerGoal)
                            .addOnSuccessListener {
                                Log.d("Success", "DocumentSnapshot successfully updated!")
                                alertDialog.dismiss()
                            }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error updating document", e)
                        }

                        val tvTimer = findViewById<TextView>(R.id.timerTextView)
                        val goalTime = tvTimer.text.toString()

                        val goalData = hashMapOf(
                            "time" to goalTime,
                            "player" to "${tvHomeRightWingerName.text}",
                            "action" to "heal_goal",
                        )

                        db.collection(collectionName).document(documentId).collection(collectionBranchName).add(goalData).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){ e ->
                            Log.w("Error", "Error adding document", e)
                        }

                        //masukkan data time ke dalam field ${tvHomeRightWingerName.text}_heal_goal_times dalam bentuk Array
                        db.collection(collectionName).document(documentId).update("${tvHomeRightWingerName.text}_heal_goal_times", FieldValue.arrayUnion(goalTime)).addOnSuccessListener() {
                            Log.d("Success", "DocumentSnapshot successfully updated!")
                            alertDialog.dismiss()
                        }.addOnFailureListener(){
                            Log.w("Error", "Error updating document", it)
                        }

                    }
            }
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

                    tvHomeCentreForwardName =
                        findViewById<TextView>(R.id.tv_goal_player10_name_home)
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

                    tvHomeSecondStrikerName =
                        findViewById<TextView>(R.id.tv_goal_player11_name_home)
                    tvHomeSecondStrikerName.text = teamHomeSecondStriker
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    private fun getHomeTeamDocumentId() {
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

    private fun getAwayTeamDocumentId() {
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

                    tvAwayDefensiveMidfielderName =
                        findViewById<TextView>(R.id.tv_goal_player5_name_away)
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

                    tvAwayCentralMidfielderName =
                        findViewById<TextView>(R.id.tv_goal_player6_name_away)
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

                    tvAwayAttackingMidfielderName =
                        findViewById<TextView>(R.id.tv_goal_player7_name_away)
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

                    tvAwayCentreForwardName =
                        findViewById<TextView>(R.id.tv_goal_player10_name_away)
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

                    tvAwaySecondStrikerName =
                        findViewById<TextView>(R.id.tv_goal_player11_name_away)
                    tvAwaySecondStrikerName.text = teamAwaySecondStriker

                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }
}