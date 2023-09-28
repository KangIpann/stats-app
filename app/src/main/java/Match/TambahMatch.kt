package Match

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.statsapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class TambahMatch : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var etNamaMatch: EditText
    private lateinit var tvTanggalMatch: TextView
    private lateinit var spinnerTeamHome: Spinner
    private lateinit var spinnerTeamAway: Spinner
    private lateinit var spinnerJerseyHome: Spinner
    private lateinit var spinnerJerseyAway: Spinner
    private lateinit var matchId : String
    private lateinit var etStartTime: TextInputEditText
    private lateinit var etEndTime: TextInputEditText
    private lateinit var etSeason: EditText
    private lateinit var etCompetitions: EditText
    private lateinit var LinearStartMatch: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_match)

        etNamaMatch = findViewById(R.id.et_nama_match)
        tvTanggalMatch = findViewById(R.id.tv_tanggal_match)
        etStartTime = findViewById(R.id.et_start_time)
        etEndTime = findViewById(R.id.et_end_time)
        etSeason = findViewById(R.id.et_season_matchmaking)
        etCompetitions = findViewById(R.id.et_competition_matchmaking)
        LinearStartMatch = findViewById(R.id.linear_startMatch)

        // handler ketika tombol tanggal N klik
        tvTanggalMatch.setOnClickListener {
            showDatePickerDialog()
        }

        LinearStartMatch.setOnClickListener {
            startMatch()
        }


        val spinnerTeamHome = findViewById<Spinner>(R.id.spinner_tim_home)
        val spinnerTeamAway = findViewById<Spinner>(R.id.spinner_tim_away)
        val spinnerJerseyHome = findViewById<Spinner>(R.id.spinner_jersey_tim_home)
        val spinnerJerseyAway = findViewById<Spinner>(R.id.spinner_jersey_tim_away)

        //inisiaisasi spinner team
        val teamNamesList = ArrayList<String>()
        teamNamesList.add("Pilih Tim") // Add the default value
        val teamAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, teamNamesList)
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTeamHome.adapter = teamAdapter
        spinnerTeamAway.adapter = teamAdapter

        //inisialisasi spinner jersey
        val jerseyList = ArrayList<String>()
        jerseyList.add("Pilih Jersey") // Add the default value
        val jerseyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, jerseyList)
        jerseyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerJerseyHome.adapter = jerseyAdapter
        spinnerJerseyAway.adapter = jerseyAdapter

        db = FirebaseFirestore.getInstance()
        val teamRef = db.collection("team")

        //mengambil data team dari firestore
        teamRef.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val teamName = document.data["nama_team"].toString()
                    val jerseyColor = document.data["jersey"].toString()
                    println("teamName yang dipilih: $teamName dan jersey: $jerseyColor")
                    teamNamesList.add(teamName)
                    jerseyList.add(jerseyColor)
                }
                teamAdapter.notifyDataSetChanged()
                jerseyAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener() { exception ->
                println("Error getting documents: $exception")
            }

        //handler ketika spinner team home dipilih
        spinnerTeamHome.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing Selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val teamName = parent?.getItemAtPosition(position).toString()
                intent.putExtra("teamHome", teamName)
                println("Team Home yang dipilih: $teamName")
                if (teamName != "Pilih Tim") {
                    val teamRef = db.collection("team").whereEqualTo("nama_team", teamName)
                    teamRef.get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                val jerseyColor = document.data["jersey"].toString()
                                println("jerseyColor yang dipilih: $jerseyColor")
                                spinnerJerseyHome.setSelection(jerseyAdapter.getPosition(jerseyColor))
                            }
                        }
                        .addOnFailureListener() { exception ->
                            println("Error getting documents: $exception")
                        }
                }
            }
        }

        spinnerTeamAway.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing Selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val teamName = parent?.getItemAtPosition(position).toString()
                intent.putExtra("teamAway", teamName)
                println("teamName yang dipilih: $teamName")
                if (teamName != "Pilih Tim") {
                    val teamRef = db.collection("team").whereEqualTo("nama_team", teamName)
                    teamRef.get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                val jerseyColor = document.data["jersey"].toString()
                                println("jerseyColor yang dipilih: $jerseyColor")
                                spinnerJerseyAway.setSelection(jerseyAdapter.getPosition(jerseyColor))
                            }
                        }
                        .addOnFailureListener() { exception ->
                            println("Error getting documents: $exception")
                        }
                }
            }
        }

        //handler untuk waktu mulai dan selesai
        etStartTime.setOnClickListener {
            setStartTime()
        }

        etEndTime.setOnClickListener {
            setEndTime()
        }

        //mengambil data yang diperlukan untuk lanjut ke activity match
    }

    private fun showDatePickerDialog() {
        if (tvTanggalMatch.text.toString() == "DD-MM-YYYY") {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    val tanggalMatch = findViewById<TextView>(R.id.tv_tanggal_match)
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    tanggalMatch.text =
                        String.format("%02d-%02d-%02d", dayOfMonth, month + 1, year) // Format the date
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        } else {
            val tanggalMatch = findViewById<TextView>(R.id.tv_tanggal_match)
            val calendar = Calendar.getInstance()
            tanggalMatch.text =
                String.format("%02d-%02d-%02d", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)) // Format the date

        }
    }

    private fun setStartTime() {
        val calendar = Calendar.getInstance()
        val timeSetListener =
            TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                val startTimeEditText = findViewById<TextView>(R.id.et_start_time)
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                startTimeEditText.text =
                    String.format("%02d:%02d:00", hour, minute) // Format the time
            }
        val timePickerDialog = TimePickerDialog(
            this, timeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }

    private fun setEndTime() {
        val calendar = Calendar.getInstance()
        val timeSetListener =
            TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                val endTimeEditText = findViewById<TextView>(R.id.et_end_time)
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                endTimeEditText.text =
                    String.format("%02d:%02d:00", hour, minute) // Format the time
            }
        val timePickerDialog = TimePickerDialog(
            this, timeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }


    private fun getHomeTeamPlayers() {
        val teamName = spinnerTeamHome.selectedItem.toString()

        db.collection("team")
            .whereEqualTo("nama_team", teamName)
            .get()
            .addOnSuccessListener { teamDocuments ->
                for (teamDocument in teamDocuments) {
                    val teamId = teamDocument.id

                    db.collection("pemain")
                        .whereEqualTo("id_tim_pemain", teamId)
                        .get()
                        .addOnSuccessListener { playerDocuments ->
                            for (playerDocument in playerDocuments) {
                                val playerName = playerDocument.getString("nama_pemain")
                                val playerNumber = playerDocument.getString("no_punggung")

                                // Do something with playerName and playerNumber
                                println("Player Name: $playerName, Player Number: $playerNumber")
                            }
                        }
                        .addOnFailureListener { exception ->
                            // Handle the error
                        }
                }
            }
            .addOnFailureListener { exception ->
                // Handle the error
            }
    }

    private fun getAwayTeamPlayers() {
        val teamName = spinnerTeamAway.selectedItem.toString()

        db.collection("team")
            .whereEqualTo("nama_team", teamName)
            .get()
            .addOnSuccessListener { teamDocuments ->
                for (teamDocument in teamDocuments) {
                    val teamId = teamDocument.id

                    db.collection("pemain")
                        .whereEqualTo("id_tim_pemain", teamId)
                        .get()
                        .addOnSuccessListener { playerDocuments ->
                            for (playerDocument in playerDocuments) {
                                val playerName = playerDocument.getString("nama_pemain")
                                val playerNumber = playerDocument.getString("no_punggung")

                                // Do something with playerName and playerNumber
                                println("Player Name: $playerName, Player Number: $playerNumber")
                            }
                        }
                        .addOnFailureListener { exception ->
                            // Handle the error
                        }
                }
            }
            .addOnFailureListener { exception ->
                // Handle the error
            }
    }

    fun getMatchDuration(): String {
        val startTime = etStartTime.text.toString()
        val endTime = etEndTime.text.toString()

        val startCalendar = Calendar.getInstance()
        val endCalendar = Calendar.getInstance()

        val startTimeParts = startTime.split(":")
        val endTimeParts = endTime.split(":")

        if(startTimeParts.size == 3 && endTimeParts.size == 3){
            startCalendar.set(Calendar.HOUR_OF_DAY, startTimeParts[0].toInt())
            startCalendar.set(Calendar.MINUTE, startTimeParts[1].toInt())
            startCalendar.set(Calendar.SECOND, startTimeParts[2].toInt())

            endCalendar.set(Calendar.HOUR_OF_DAY, endTimeParts[0].toInt())
            endCalendar.set(Calendar.MINUTE, endTimeParts[1].toInt())
            endCalendar.set(Calendar.SECOND, endTimeParts[2].toInt())

            val timeDifferenceInMillis = endCalendar.timeInMillis - startCalendar.timeInMillis
            val hours = timeDifferenceInMillis / (1000 * 60 * 60)
            val minutes = (timeDifferenceInMillis % (1000 * 60 * 60)) / (1000 * 60)
            val seconds = (timeDifferenceInMillis % (1000 * 60)) / 1000

            return String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            //jika waktu tidak diisi maka akan mengisi dengan default 90 menit
            return "01:30:00"
        }
    }


    fun startMatch() {

        //inisialisasi variabel
        val matchName = etNamaMatch.text.toString()
        val matchDate = tvTanggalMatch.text.toString()
        val matchSeason = etSeason.text.toString()
        val matchCompetition = etCompetitions.text.toString()
        val matchHomeTeam = intent.getStringExtra("teamHome")
        val matchAwayTeam = intent.getStringExtra("teamAway")
        val matchStartTime = etStartTime.text.toString()
        val matchEndTime = etEndTime.text.toString()
        val matchDuration = getMatchDuration()
        val data_owner = FirebaseAuth.getInstance().currentUser?.uid

        //print variabel untuk mengecek apa yang diambil
        println("Match Name: $matchName")
        println("Match Date: $matchDate")
        println("Match Season: $matchSeason")
        println("Match Competition: $matchCompetition")
        println("Match Home Team: $matchHomeTeam")
        println("Match Away Team: $matchAwayTeam")
        println("Match Duration: $matchDuration")

        //upload data ke firestore
        val match = hashMapOf(
            "id_match" to "",
            "nama_match" to matchName,
            "tanggal_match" to matchDate,
            "musim_match" to matchSeason,
            "kompetisi_match" to matchCompetition,
            "tim_home_match" to matchHomeTeam,
            "tim_away_match" to matchAwayTeam,
            "waktu_mulai_match" to matchStartTime,
            "waktu_selesai_match" to matchEndTime,
            "durasi_match" to matchDuration,
            "data_owner" to data_owner
        )

        Toast.makeText(this, "Match started", Toast.LENGTH_SHORT).show()

        db.collection("match")
            .add(match)
            .addOnSuccessListener { documentReference ->
                println("DocumentSnapshot added with ID: ${documentReference.id}")
                val matchId = documentReference.id

                val intent = Intent(this, MatchBerjalan::class.java)
                intent.putExtra("matchId", matchId)
                intent.putExtra("matchName", matchName)
                intent.putExtra("matchDate", matchDate)
                intent.putExtra("matchSeason", matchSeason)
                intent.putExtra("matchCompetition", matchCompetition)
                intent.putExtra("matchHomeTeam", matchHomeTeam)
                intent.putExtra("matchAwayTeam", matchAwayTeam)
                intent.putExtra("matchDuration", matchDuration)
                startActivity(intent)

                db.collection("match").document(matchId)
                    .update("id_match", matchId)
                    .addOnSuccessListener {
                        println("DocumentSnapshot successfully updated!")
                    }
                    .addOnFailureListener { e ->
                        println("Error updating document: $e")
                    }
            }
            .addOnFailureListener { e ->
                println("Error adding document: $e")
            }


    }


}
