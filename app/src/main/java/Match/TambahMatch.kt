package Match

import Adapter.CustomSpinnerAdapter
import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import com.example.statsapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import teamData
import java.util.Calendar

class TambahMatch : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var etNamaMatch: EditText
    private lateinit var tvTanggalMatch: TextView
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

        // handler ketika tombol tanggal di klik
        tvTanggalMatch.setOnClickListener {
            showDatePickerDialog()
        }

        val spinnerTeamHome = findViewById<Spinner>(R.id.spinner_tim_home)
        val spinnerTeamAway = findViewById<Spinner>(R.id.spinner_tim_away)
        val spinnerJerseyHome = findViewById<Spinner>(R.id.spinner_jersey_tim_home)
        val spinnerJerseyAway = findViewById<Spinner>(R.id.spinner_jersey_tim_away)

        val teamNamesList = ArrayList<String>()
        teamNamesList.add("Pilih Tim") // Add the default value
        val teamAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, teamNamesList)
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerTeamHome.adapter = teamAdapter
        spinnerTeamAway.adapter = teamAdapter

        val jerseyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mutableListOf<String>())
        jerseyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerJerseyHome.adapter = jerseyAdapter
        spinnerJerseyAway.adapter = jerseyAdapter

        // Initialize Firestore
        db = FirebaseFirestore.getInstance()

        // Fetch data from Firestore and populate the teamNamesList
        val teamCollectionRef = db.collection("team")

        teamCollectionRef.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val teamName = document.getString("nama_team")
                    if (teamName != null) {
                        teamNamesList.add(teamName)
                    }
                }
                teamAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                // Handle the error
            }

        // Set up the listener for team selection
        spinnerTeamHome.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedTeam = parent?.getItemAtPosition(position).toString()
                // Fetch and populate jersey data for the selected team
                fetchJerseyData(selectedTeam, spinnerJerseyHome)
                fetchDocumentId(selectedTeam, "home")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle when nothing is selected
            }
        }

        // Similar listener for spinnerTeamAway
        spinnerTeamAway.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedTeam = parent?.getItemAtPosition(position).toString()
                // Fetch and populate jersey data for the selected team
                fetchJerseyData(selectedTeam, spinnerJerseyAway)
                fetchDocumentId(selectedTeam, "away")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle when nothing is selected
            }
        }
    }

    private fun fetchJerseyData(selectedTeam: String, jerseySpinner: Spinner) {
        val jerseyCollectionRef = db.collection("team")
            .whereEqualTo("nama_team", selectedTeam)

        jerseyCollectionRef.get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val jerseyData = documents.documents[0].getString("jersey")
                    if (jerseyData != null) {
                        val jerseyList = jerseyData.split(",") // You may need to adjust this based on how your jersey data is stored
                        val jerseyAdapter = jerseySpinner.adapter as ArrayAdapter<String>
                        jerseyAdapter.clear()
                        jerseyAdapter.addAll(jerseyList)
                        jerseyAdapter.notifyDataSetChanged()
                    }
                }
            }
            .addOnFailureListener { exception ->
                // Handle the error
            }
    }

    private fun selectedTeamDocumentId(selectedTeam: String, homeOrAway: String) {
        val teamCollectionRef = db.collection("team")
            .whereEqualTo("nama_team", selectedTeam)

        teamCollectionRef.get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val documentId = documents.documents[0].id
                    if (homeOrAway == "home") {
                        teamData.id = documentId
                    } else {
                        teamData.id = documentId
                    }
                }
            }
            .addOnFailureListener { exception ->
                // Handle the error
            }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val tanggalLahirEditText = findViewById<TextView>(R.id.tv_tanggal_match)
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                tanggalLahirEditText.text =
                    dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
            }
        val datePickerDialog = DatePickerDialog(
            this, dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
    }
}
