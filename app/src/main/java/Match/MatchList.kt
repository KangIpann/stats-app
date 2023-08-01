package Match

import Adapter.MatchListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
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
    }
}