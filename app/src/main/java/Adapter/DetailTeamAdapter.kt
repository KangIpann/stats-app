package Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.statsapp.R
import com.google.firebase.firestore.Query
import teamData

class DetailTeamAdapter(private val query: Query) :
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
            for (document in snapshot!!){
                val team = teamData(
                    document.id,
                    document.getString("nama_team")!!,
                    document.getString("season")!!,
                    document.getString("coach")!!,
                    document.getString("asisten")!!,
                    document.getString("instansi")!!,
                    document.getString("alamat")!!,
                    document.getString("kota")!!,
                    document.getString("provinsi")!!,
                    document.getString("negara")!!,
                    document.getString("email")!!,
                    document.getString("logo")!!,
                    document.getString("jersey")!!,
                    document.getString("jenis_kelamin")!!,
                    document.getString("jumlah_pemain")!!,
                )
                teams.add(team)
            }
            notifyDataSetChanged()
        }
    }


    override fun onBindViewHolder(holder: DetailTeamAdapter.DetailTeamViewHolder, position: Int) {
        val currentTeam = teams[position]

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

    interface OnItemClickListener {
        fun onItemClick(team: teamData)
    }
}