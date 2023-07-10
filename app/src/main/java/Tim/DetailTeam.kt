package Tim

import Adapter.DetailTeamAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.statsapp.R
import com.google.firebase.firestore.FirebaseFirestore
import teamData

class DetailTeam : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var detailTeamAdapter: DetailTeamAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        val team = intent.getParcelableExtra<teamData>("team")
        val nestedScrollView = findViewById<NestedScrollView>(R.id.sv_detail_team)
        nestedScrollView.isFillViewport = true


        db = FirebaseFirestore.getInstance()
        recyclerView = findViewById(R.id.rv_detail_team)
        detailTeamAdapter = DetailTeamAdapter(team)

        recyclerView.adapter = detailTeamAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set the data to the adapter
        if (team != null) {
            detailTeamAdapter.setData(team)
        }

    }

}
