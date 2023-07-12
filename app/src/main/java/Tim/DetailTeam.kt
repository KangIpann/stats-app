package Tim

import Adapter.CustomSpinnerAdapter
import Adapter.DetailTeamAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import com.bumptech.glide.Glide
import com.example.statsapp.R
import com.google.firebase.firestore.FirebaseFirestore
import teamData

class DetailTeam : AppCompatActivity() {

    private lateinit var detailTeamAdapter: DetailTeamAdapter
    private lateinit var db: FirebaseFirestore
    private lateinit var documentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        documentId = intent.getStringExtra("documentId").toString()
        db = FirebaseFirestore.getInstance()

        val ivBack = findViewById<ImageView>(R.id.tim_btn_back)
        ivBack.setOnClickListener {
            finish()
        }

        PullData()
    }

    private fun PullData() {
        val docRef = db.collection("team").document(documentId)
        docRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                // Handle error
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val namaTim = snapshot.getString("nama_team")
                val season = snapshot.getString("season")
                val coach = snapshot.getString("coach")
                val asistenTim = snapshot.getString("asisten")
                val warnaJersey = snapshot.getString("jersey")
                val instansiTim = snapshot.getString("instansi")
                val alamatTim = snapshot.getString("alamat")
                val kotaKabTim = snapshot.getString("kota")
                val provinsiTim = snapshot.getString("provinsi")
                val negaraTim = snapshot.getString("negara")
                val emailTim = snapshot.getString("email")
                val jenisKelamin = snapshot.getString("jenis_kelamin")
                val jumlahPemain = snapshot.getString("jumlah_pemain")
                val logoTim = snapshot.getString("logo")
                val logoTimUrl = logoTim.toString()

                val ivLogoTim = findViewById<ImageView>(R.id.iv_logotim_detailtim)
                val etNamaTim = findViewById<EditText>(R.id.et_namatim_detailteam)
                val etJenisKelamin = findViewById<Spinner>(R.id.et_kelamintim_detailtim)
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

                // Set data to views
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

                // Set selected item in Spinner
                val genderAdapter = CustomSpinnerAdapter(
                    this,
                    R.layout.spinner_kelamin_layout,
                    resources.getStringArray(R.array.gender_array).toList()
                )
                etJenisKelamin.adapter = genderAdapter
                val genderIndex = genderAdapter.getPosition(jenisKelamin)
                etJenisKelamin.setSelection(genderIndex)


            }
            val logoTimUrl = snapshot?.getString("logo").toString()
            val ivLogoTim = findViewById<ImageView>(R.id.iv_logotim_detailtim)
            Glide.with(this)
                .load(logoTimUrl)
                .transition(com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade())
                .into(ivLogoTim)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
