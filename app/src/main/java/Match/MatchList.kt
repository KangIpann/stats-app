package Match

import Adapter.MatchListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.statsapp.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

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
        val query = db.collection("match").orderBy("tgl_match", Query.Direction.DESCENDING)
        matchAdapter = MatchListAdapter(query)
        recyclerView.adapter = matchAdapter
        matchAdapter.startListening()
    }
}