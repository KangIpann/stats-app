package Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.statsapp.R
import teamData

class DetailTeamAdapter(private val query: teamData?) :
    RecyclerView.Adapter<DetailTeamAdapter.DetailTeamViewHolder>() {

    private var teams: MutableList<teamData> = mutableListOf()
    private var onItemClickListener: AdapterView.OnItemClickListener? = null

    inner class DetailTeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logoTim : ImageView = itemView.findViewById(R.id.iv_logotim_detailteam)
        val namaTimEditText : EditText = itemView.findViewById(R.id.et_namatim_detailteam)
        val seasonEditText : EditText = itemView.findViewById(R.id.et_seasontim_detailteam)
        val coachEditText : EditText = itemView.findViewById(R.id.et_coachtim_detailteam)
        val asistenTimEditText : EditText = itemView.findViewById(R.id.et_asistentim_detailteam)
        val warnaJerseyEditText : EditText = itemView.findViewById(R.id.et_warnajersey_detailteam)
        val instansiTimEditText : EditText = itemView.findViewById(R.id.et_instansitim_detailteam)
        val alamatTimEditText : EditText = itemView.findViewById(R.id.et_alamattim_detailteam)
        val KotaKabTimEditText : EditText = itemView.findViewById(R.id.et_kotakabtim_detailteam)
        val provinsiTimEditText : EditText = itemView.findViewById(R.id.et_provinsitim_detailteam)
        val negaraTimEditText : EditText = itemView.findViewById(R.id.et_negaratim_detailteam)
        val emailTimEditText : EditText = itemView.findViewById(R.id.et_emailtim_detailteam)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): DetailTeamAdapter.DetailTeamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_team_row, parent, false)
        return DetailTeamViewHolder(view)
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
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    fun setOnItemClickListener(listener: AdapterView.OnItemClickListener) {
        onItemClickListener = listener
    }


    fun setData(team: teamData) {
        teams.add(team)
        notifyDataSetChanged()
    }
}