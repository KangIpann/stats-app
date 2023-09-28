package Match

import Adapter.MatchListAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
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

        val ivBack = findViewById<ImageView>(R.id.match_btn_back)
        ivBack.setOnClickListener{
            finish()
        }

        val linearAdTeam = findViewById<LinearLayout>(R.id.linear_addteam)
        linearAdTeam.setOnClickListener{
            val intent = Intent(this, TambahMatch::class.java)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv_match_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val db = FirebaseFirestore.getInstance()
        val dataOwner = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.uid
        val query = db.collection("match").whereEqualTo("data_owner", dataOwner)
        val matchAdapter = MatchListAdapter(query)
        recyclerView.adapter = matchAdapter
        matchAdapter.startListening()
    }
}