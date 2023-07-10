package Tim

import Adapter.TeamListAdapter
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.statsapp.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import teamData

class TeamList : AppCompatActivity(), View.OnClickListener{
    private lateinit var adapter : TeamListAdapter
    private lateinit var recyclerview : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_list)

        val linearLayout_add = findViewById<LinearLayout>(R.id.linear_addteam)
        linearLayout_add.setOnClickListener {
            showAddTeamDialog(this)
        }
        val btn_back = findViewById<ImageView>(R.id.tim_btn_back)
        btn_back.setOnClickListener {
            finish()
        }

        recyclerview = findViewById(R.id.tim_rv_teams)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val db = FirebaseFirestore.getInstance()
        val query = db.collection("team").orderBy("nama_team", Query.Direction.ASCENDING)
        adapter = TeamListAdapter(query)
        recyclerview.adapter = adapter

        adapter.startListening()

    }

    private fun showAddTeamDialog(context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_add_team, null)
        dialogBuilder.setView(dialogView)

        val editTextTeamName = dialogView.findViewById<EditText>(R.id.editTextTeamName)
        val buttonAdd = dialogView.findViewById<Button>(R.id.buttonAdd)
        val buttonCancel = dialogView.findViewById<Button>(R.id.buttonCancel)

        val alertDialog = dialogBuilder.create()

        buttonAdd.setOnClickListener {
            val teamName = editTextTeamName.text.toString()
            // Do something with the teamName, e.g., save to database
            alertDialog.dismiss()
        }

        buttonCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    override fun onClick(v: View?) {

    }
    fun onItemClick(team: teamData) {
        val intent = Intent(this, DetailTeam::class.java)
        intent.putExtra("team", team)
        startActivity(intent)
    }

        override fun onDestroy() {
        super.onDestroy()
        adapter.stopListening()
    }

}
