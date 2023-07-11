package Tim

import Adapter.DetailTeamAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.statsapp.R
import com.google.firebase.firestore.FirebaseFirestore
import teamData

class DetailTeam : AppCompatActivity() {

    private lateinit var detailTeamAdapter: DetailTeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        val documentId = intent.getStringExtra("documentId")
        println("documentId: $documentId")

        val ivBack = findViewById<ImageView>(R.id.tim_btn_back)
        ivBack.setOnClickListener {
            finish()
        }

        PullData()
    }

    private fun PullData() {
        val db = FirebaseFirestore.getInstance()
        val documentId = intent.getStringExtra("documentId")
        println("documentId: $documentId")
        val docRef = db.collection("team").document(documentId.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val namaTim = document.getString("nama_team")
                    val season = document.getString("season")
                    val coach = document.getString("coach")
                    val asistenTim = document.getString("asisten")
                    val warnaJersey = document.getString("jersey")
                    val instansiTim = document.getString("instansi")
                    val alamatTim = document.getString("alamat")
                    val kotaKabTim = document.getString("kota")
                    val provinsiTim = document.getString("provinsi")
                    val negaraTim = document.getString("negara")
                    val emailTim = document.getString("email")
                    val jenisKelamin = document.getString("jenis_kelamin")
                    val jumlahPemain = document.getString("jumlah_pemain")
                    val logoTim = document.getString("logo")
                    val logoTimUrl = logoTim.toString()
                    val ivLogoTim = findViewById<ImageView>(R.id.iv_logotim_detailtim)
                    val etNamaTim = findViewById<EditText>(R.id.et_namatim_detailteam)
                    val etJenisKelamin = findViewById<EditText>(R.id.et_kelamintim_detailtim)
                    val etSeason = findViewById<EditText>(R.id.et_season_detailteam)
                    val etCoach = findViewById<EditText>(R.id.et_coach_detailteam)
                    val etAsisten = findViewById<EditText>(R.id.et_asisten_detailteam)
                    val etJumlahPemain = findViewById<EditText>(R.id.et_jumlahpemain_detailtim)
                    val etWarnaJersey = findViewById<EditText>(R.id.et_warnajersey_detailteam)
                    val etInstansi = findViewById<EditText>(R.id.et_instansi_detailteam)
                    val etAlamat = findViewById<EditText>(R.id.et_alamat_detailteam)
                    val etKotaKab = findViewById<EditText>(R.id.et_kotakab_detailteam)
                    val etProvinsi = findViewById<EditText>(R.id.et_provinsi_detailteam)
                    val etNegara = findViewById<EditText>(R.id.et_negara_detailteam)
                    val etEmail = findViewById<EditText>(R.id.et_emailteam_detailteam)

                    // Set data to views here
                    etNamaTim.setText(namaTim)
                    etSeason.setText(season)
                    etCoach.setText(coach)
                    etAsisten.setText(asistenTim)
                    etJumlahPemain.setText(jumlahPemain)
                    etWarnaJersey.setText(warnaJersey)
                    etInstansi.setText(instansiTim)
                    etAlamat.setText(alamatTim)
                    etKotaKab.setText(kotaKabTim)
                    etProvinsi.setText(provinsiTim)
                    etNegara.setText(negaraTim)
                    etEmail.setText(emailTim)
                    etJenisKelamin.setText(jenisKelamin)

                    // Load image using Glide
                    Glide.with(this)
                        .load(logoTimUrl)
                        .transition(com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade())
                        .into(ivLogoTim)
                }
            }
            .addOnFailureListener { exception ->
                println("Error getting document: $exception")
            }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
