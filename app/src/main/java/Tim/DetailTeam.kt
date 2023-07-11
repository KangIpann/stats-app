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

    private lateinit var detailTeamAdapter: DetailTeamAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        val team = intent.getParcelableExtra<teamData>("team")
        val teamId = team?.id

        val db = FirebaseFirestore.getInstance()
        val query = db.collection("team")
        val detailTeamAdapter = DetailTeamAdapter(query)

        detailTeamAdapter.startListening()
    }

}
