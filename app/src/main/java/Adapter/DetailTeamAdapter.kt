package Adapter

import Tim.DetailTeam
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.statsapp.R
import com.google.firebase.firestore.DocumentReference
import teamData

class DetailTeamAdapter(private val query: DocumentReference) :
    RecyclerView.Adapter<DetailTeamAdapter.DetailTeamViewHolder>() {

    private var teams: MutableList<teamData> = mutableListOf()
    private var onItemClickListener:OnItemClickListener? = null

    inner class DetailTeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logoTim: ImageView = itemView.findViewById(R.id.iv_logotim_detailteam)
        val namaTimEditText: EditText = itemView.findViewById(R.id.et_namapemain_detailpemain)
        val seasonEditText: EditText = itemView.findViewById(R.id.et_posisipemain_detailpemain)
        val jenisKelaminEditText: EditText = itemView.findViewById(R.id.et_kelamintim_detailteam)
        val jumlahPemainEditText: EditText = itemView.findViewById(R.id.et_jumlahpemain_detailtim)
        val coachEditText: EditText = itemView.findViewById(R.id.et_nomerpunggung_detailpemain)
        val asistenTimEditText: EditText = itemView.findViewById(R.id.et_lateralitas_detailpemain)
        val warnaJerseyEditText: EditText = itemView.findViewById(R.id.et_beratbadan_detailpemain)
        val instansiTimEditText: EditText = itemView.findViewById(R.id.et_bmi_detailpemain)
        val alamatTimEditText: EditText = itemView.findViewById(R.id.et_tanggallahir_detailpemain)
        val KotaKabTimEditText: EditText = itemView.findViewById(R.id.et_jeniskelamin_detailpemain)
        val provinsiTimEditText: EditText = itemView.findViewById(R.id.et_domisili_detailpemain)
        val negaraTimEditText: EditText = itemView.findViewById(R.id.et_nomorhp_detailpemain)
        val emailTimEditText: EditText = itemView.findViewById(R.id.et_emailpemain_detailpemain)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val team = teams[position]
                    onItemClickListener?.onItemClick(team)
                }
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): DetailTeamAdapter.DetailTeamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_team_row, parent, false)
        return DetailTeamViewHolder(view)
    }

    fun startListening() {
        query.addSnapshotListener { snapshot, exception ->
            if (exception != null){
                return@addSnapshotListener
            }
            teams.clear()
            //get intent from TeamListAdapter
            val documentId = snapshot?.id
            val namaTim = snapshot?.getString("nama_team")
            val season = snapshot?.getString("season")
            val coach = snapshot?.getString("coach")
            val asistenTim = snapshot?.getString("asisten")
            val warnaJersey = snapshot?.getString("jersey")
            val instansiTim = snapshot?.getString("instansi")
            val alamatTim = snapshot?.getString("alamat")
            val kotaKabTim = snapshot?.getString("kota")
            val provinsiTim = snapshot?.getString("provinsi")
            val negaraTim = snapshot?.getString("negara")
            val emailTim = snapshot?.getString("email")
            val logoTim = snapshot?.getString("logo")
            val jenisKelamin = snapshot?.getString("jenis_kelamin")
            val jumlahPemain = snapshot?.getString("jumlah_pemain")

            println("documentId: $documentId")
            println("namaTim: $namaTim")
            println("season: $season")
            println("coach: $coach")
            println("asistenTim: $asistenTim")
            println("warnaJersey: $warnaJersey")
            println("instansiTim: $instansiTim")
            println("alamatTim: $alamatTim")
            println("kotaKabTim: $kotaKabTim")
            println("provinsiTim: $provinsiTim")
            println("negaraTim: $negaraTim")
            println("emailTim: $emailTim")
            println("logoTim: $logoTim")
            println("jenisKelamin: $jenisKelamin")
            println("jumlahPemain: $jumlahPemain")


            if (documentId != null && namaTim != null && season != null && coach != null && asistenTim != null && warnaJersey != null && instansiTim != null && alamatTim != null && kotaKabTim != null && provinsiTim != null && negaraTim != null && emailTim != null && logoTim != null && jenisKelamin != null && jumlahPemain != null){
                teams.add(teamData(documentId, namaTim, season, coach, asistenTim, warnaJersey, instansiTim, alamatTim, kotaKabTim, provinsiTim, negaraTim, emailTim, logoTim, jenisKelamin, jumlahPemain))
            }
            notifyDataSetChanged()
        }
    }


    override fun onBindViewHolder(holder: DetailTeamAdapter.DetailTeamViewHolder, position: Int) {
        val currentTeam = teams[position]

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailTeam::class.java)
            intent.putExtra("documentId", currentTeam.id)
            print("Current documentId: ${currentTeam.id}")
            holder.itemView.context.startActivity(intent)
        }

        Glide.with(holder.itemView.context)
            .load(currentTeam.logo)
            .transition(com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade())
            .into(holder.logoTim)

        holder.namaTimEditText.setText(currentTeam.nama_team)
        holder.seasonEditText.setText(currentTeam.season)
        holder.coachEditText.setText(currentTeam.coach)
        holder.asistenTimEditText.setText(currentTeam.asisten)
        holder.warnaJerseyEditText.setText(currentTeam.jersey)
        holder.instansiTimEditText.setText(currentTeam.instansi)
        holder.alamatTimEditText.setText(currentTeam.alamat)
        holder.KotaKabTimEditText.setText(currentTeam.kota)
        holder.provinsiTimEditText.setText(currentTeam.provinsi)
        holder.negaraTimEditText.setText(currentTeam.negara)
        holder.emailTimEditText.setText(currentTeam.email)
        holder.jumlahPemainEditText.setText(currentTeam.jumlah_pemain)
        holder.jenisKelaminEditText.setText(currentTeam.jenis_kelamin)
    }

    override fun getItemCount(): Int {
        return teams.size
    }


    fun setData(team: teamData) {
        teams.add(team)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: DetailTeamAdapter.OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(team: teamData)
    }
}