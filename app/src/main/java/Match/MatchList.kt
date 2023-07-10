package Match

import Adapter.MatchListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.statsapp.R
import com.google.firebase.firestore.FirebaseFirestore

class MatchList : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var matchAdapter: MatchListAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_list)

        db = FirebaseFirestore.getInstance()
        recyclerView = findViewById(R.id.rv_match_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //accessing collection "match" open document and open collection "Match 1" and order by "tgl_match" ascending
        val query = db.collection("match").document("Match 1").collection("match").orderBy("tgl_match", com.google.firebase.firestore.Query.Direction.ASCENDING)
        matchAdapter = MatchListAdapter(query)
        recyclerView.adapter = matchAdapter
        matchAdapter.startListening()
    }
}