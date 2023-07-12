package Tim

import Adapter.TeamListAdapter
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.statsapp.R
import com.google.firebase.firestore.FirebaseFirestore
import teamData

class TeamList : AppCompatActivity(), TeamListAdapter.OnItemClickListener {
    private lateinit var adapter: TeamListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_list)

        val linearLayoutAdd = findViewById<LinearLayout>(R.id.linear_addteam)
        linearLayoutAdd.setOnClickListener {
            showAddTeamDialog()
        }

        val btnBack = findViewById<ImageView>(R.id.tim_btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.tim_rv_teams)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val db = FirebaseFirestore.getInstance()
        val query = db.collection("team")
        val adapter = TeamListAdapter(query)
        recyclerView.adapter = adapter
        adapter.startListening()
    }

    private fun showAddTeamDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.dialog_add_team, null)
        dialogBuilder.setView(dialogView)
        val editTextTeamName = dialogView.findViewById<EditText>(R.id.et_timname)
        val buttonAdd = dialogView.findViewById<Button>(R.id.button_add_team)
        val buttonCancel = dialogView.findViewById<Button>(R.id.button_cancel_team)

        val alertDialog = dialogBuilder.create()

        buttonAdd.setOnClickListener {
            val teamName = editTextTeamName.text.toString().trim()
            if (teamName.isNotEmpty()) {
                val newTeam = teamData(
                    "",
                    teamName,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
                )
                addTeamToDatabase(newTeam)
                alertDialog.dismiss()
            }
        }

        buttonCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun addTeamToDatabase(team: teamData) {
        val db = FirebaseFirestore.getInstance()
        val teamCollection = db.collection("team")
        teamCollection.add(team)
            .addOnSuccessListener {
                Toast.makeText(this, "Team added successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error adding team", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onItemClick(team: teamData) {
        val intent = Intent(this, DetailTeam::class.java)
        intent.putExtra("team", team)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
