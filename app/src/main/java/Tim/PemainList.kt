package Tim

import Data.pemainData
import PemainListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.statsapp.R
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class PemainList : AppCompatActivity() {

    private lateinit var adapter: PemainListAdapter
    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var documentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemain_list)

        val linearLayoutAdd = findViewById<LinearLayout>(R.id.linear_addpemain)
        linearLayoutAdd.setOnClickListener {
            showAddPemainDialog()
        }

        documentId = intent.getStringExtra("documentId").toString()
        println("documentId yang diterima: $documentId")
        val recyclerView = findViewById<RecyclerView>(R.id.pemain_rv_teams)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val db = FirebaseFirestore.getInstance()
        val query = db.collection("pemain").whereEqualTo("id_tim_pemain", documentId)
        val adapter = PemainListAdapter(query)
        recyclerView.adapter = adapter
        adapter.startListening()
    }

    private fun setTeamJumlahPlayer(){
        documentId = intent.getStringExtra("documentId").toString()
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("team").document(documentId)
        //hitung jumlah pemain yang memiliki id_tim_pemain = documentId
        val query = db.collection("pemain").whereEqualTo("id_tim_pemain", documentId)
        query.get()
            .addOnSuccessListener { documents ->
                val jumlahPemain = documents.size()
                println("Jumlah pemain: $jumlahPemain")
                docRef.update("jumlah_pemain", jumlahPemain)
                    .addOnSuccessListener {
                        println("Jumlah pemain berhasil diupdate")
                    }
                    .addOnFailureListener{
                        println("Jumlah pemain gagal diupdate")
                    }
            }
            .addOnFailureListener{
                println("Jumlah pemain gagal dihitung")
            }
    }

    private fun showAddPemainDialog(){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_pemain, null)
        dialogBuilder.setView(dialogView)
        val editTextPemainName = dialogView.findViewById<EditText>(R.id.et_namapemain_addpemain)
        val buttonAddPemain = dialogView.findViewById<Button>(R.id.btn_add_pemain)
        val buttonCancelPemain = dialogView.findViewById<Button>(R.id.btn_cancel_pemain)

        val alertDialog = dialogBuilder.create()

        buttonAddPemain.setOnClickListener{
            val pemainName = editTextPemainName.text.toString().trim()
            if (pemainName.isEmpty()){
                editTextPemainName.error = "Nama pemain harus diisi"
                editTextPemainName.requestFocus()
                return@setOnClickListener
            }else{
                val newPemain = pemainData(
                "",
                pemainName,
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
                    documentId,
                    "",
                    "",
                    "",
                )
                addPemainToDatabase(newPemain)
                setTeamJumlahPlayer()
                alertDialog.dismiss()
            }
        }
        buttonCancelPemain.setOnClickListener{
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
    private fun addPemainToDatabase(newPemain: pemainData) {
        val db = FirebaseFirestore.getInstance()
        val pemainCollection: CollectionReference = db.collection("pemain")
        setTeamJumlahPlayer()
        pemainCollection.add(newPemain)
            .addOnSuccessListener {
                println("Pemain berhasil ditambahkan")
            }
            .addOnFailureListener{
                println("Pemain gagal ditambahkan")
            }
    }
}
