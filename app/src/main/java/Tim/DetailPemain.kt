package Tim

import Adapter.DetailPemainAdapter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.statsapp.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class DetailPemain : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var playerDocumentId: String
    private lateinit var storageReference: StorageReference
    private lateinit var selectedImageUri: Uri
    private lateinit var detailPemainAdapter: DetailPemainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pemain)

        playerDocumentId = intent.getStringExtra("documentId").toString()
        db = FirebaseFirestore.getInstance()
        println("DocumentId pemain yang diterima: $playerDocumentId")

        val ivBack = findViewById<ImageView>(R.id.pemain_btn_back)
        ivBack.setOnClickListener {
            onBackPressed()
        }

        storageReference = FirebaseStorage.getInstance().reference
        pullData()
    }

    private fun pullData() {
        val docRef = db.collection("pemain").document(playerDocumentId)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val namaPemain = document.getString("nama_pemain")
                    val rolePemain = document.getString("role_pemain")
                    val nomorPemain = document.getString("no_punggung")
                    val lateralitasPemain = document.getString("lateralitas_pemain")
                    val tinggiPemain = document.getString("tinggi_pemain")
                    val beratPemain = document.getString("berat_pemain")
                    val bmiPemain = document.getString("bmi_pemain")
                    val tanggalLahirPemain = document.getString("tanggal_lahir_pemain")
                    val kelaminPemain = document.getString("kelamin_pemain")
                    val domisiliPemain = document.getString("domisili_pemain")
                    val nomorHandphonePemain = document.getString("nomor_handphone_pemain")
                    val idTimPemain = document.getString("id_tim_pemain")
                    val fotoPemain = document.getString("foto_pemain")
                    val statusPemain = document.getString("status_pemain")
                    val emailPemain = document.getString("email_pemain")

                    //deklarasi id xml variable
                    val EditTextNamaPemain = findViewById<EditText>(R.id.et_namapemain_detailpemain)
                    val EditTextRolePemain = findViewById<EditText>(R.id.et_posisipemain_detailpemain)
                    val EditTextStatusPemain = findViewById<EditText>(R.id.et_statuspemain_detailpemain)
                    val EditTextNomorPemain = findViewById<EditText>(R.id.et_nomerpunggung_detailpemain)
                    val EditTextLateralitasPemain = findViewById<EditText>(R.id.et_lateralitas_detailpemain)
                    val EditTextBeratPemain = findViewById<EditText>(R.id.et_beratbadan_detailpemain)
                    val EditTextBmiPemain = findViewById<EditText>(R.id.et_bmi_detailpemain)
                    val EditTextTanggalLahirPemain = findViewById<EditText>(R.id.et_tanggallahir_detailpemain)
                    val EditTextKelaminPemain = findViewById<EditText>(R.id.et_jeniskelamin_detailpemain)
                    val EditTextDomisiliPemain = findViewById<EditText>(R.id.et_domisili_detailpemain)
                    val EditTextNomorHandphonePemain = findViewById<EditText>(R.id.et_nomorhp_detailpemain)
                    val EditTextEmailPemain = findViewById<EditText>(R.id.et_emailpemain_detailpemain)

                    //set value dari database ke xml
                    EditTextNamaPemain.setText(namaPemain)
                    EditTextRolePemain.setText(rolePemain)
                    EditTextStatusPemain.setText(statusPemain)
                    EditTextNomorPemain.setText(nomorPemain)
                    EditTextLateralitasPemain.setText(lateralitasPemain)
                    EditTextBeratPemain.setText(beratPemain)
                    EditTextBmiPemain.setText(bmiPemain)
                    EditTextTanggalLahirPemain.setText(tanggalLahirPemain)
                    EditTextKelaminPemain.setText(kelaminPemain)
                    EditTextDomisiliPemain.setText(domisiliPemain)
                    EditTextNomorHandphonePemain.setText(nomorHandphonePemain)
                    EditTextEmailPemain.setText(emailPemain)

                    Glide.with(this)
                        .load(fotoPemain)
                        .into(findViewById<ImageView>(R.id.iv_logopemain_detailteam))
                } else {
                    // Handle the case when the document does not exist
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors that occur during the retrieval
            }
    }
}
