package Tim

import PemainListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.statsapp.R
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class PemainList : AppCompatActivity() {

    private lateinit var adapter: PemainListAdapter
    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var documentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemain_list)

        documentId = intent.getStringExtra("documentId").toString()
        println("documentId yang diterima: $documentId")
        val recyclerView = findViewById<RecyclerView>(R.id.pemain_rv_teams)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val db = FirebaseFirestore.getInstance()
        //cari pada collection pemain, yang memiliki field id_tim_pemain yang sama dengan documentId
        val query = db.collection("pemain").whereEqualTo("id_tim_pemain", documentId)
        val adapter = PemainListAdapter(query)
        recyclerView.adapter = adapter
        adapter.startListening()
    }
}
