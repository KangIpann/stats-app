package Tim

import Adapter.DetailTeamAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.example.statsapp.R
import com.google.firebase.firestore.FirebaseFirestore
import teamData

class DetailTeam : AppCompatActivity() {

    private lateinit var detailTeamAdapter: DetailTeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        val documentId = intent.getStringExtra("documentId")

        val ivBack = findViewById<ImageView>(R.id.tim_btn_back)
        ivBack.setOnClickListener {
            finish()
        }

        val etNamaTim = findViewById<EditText>(R.id.et_namatim_detailteam)
        val etJenisKelamin = findViewById<EditText>(R.id.et_kelamintim_detailteam)
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

        val db = FirebaseFirestore.getInstance()
        val query = db.collection("team").document(documentId.toString())
        detailTeamAdapter = DetailTeamAdapter(query)

        detailTeamAdapter.setOnItemClickListener(object : DetailTeamAdapter.OnItemClickListener {
            fun onItemUpdate(team: teamData) {
                etNamaTim.setText(team.nama_team)
                etJenisKelamin.setText(team.jenis_kelamin)
                etSeason.setText(team.season)
                etCoach.setText(team.coach)
                etAsisten.setText(team.asisten)
                etJumlahPemain.setText(team.jumlah_pemain)
                etWarnaJersey.setText(team.jersey)
                etInstansi.setText(team.instansi)
                etAlamat.setText(team.alamat)
                etKotaKab.setText(team.kota)
                etProvinsi.setText(team.provinsi)
                etNegara.setText(team.negara)
                etEmail.setText(team.email)
            }

            override fun onItemClick(team: teamData) {
                onItemUpdate(team)
            }
        })

        detailTeamAdapter.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
