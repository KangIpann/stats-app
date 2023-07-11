package Adapter

import Tim.DetailTeam
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.statsapp.R
import com.google.firebase.firestore.Query
import teamData

class TeamListAdapter(private val query: Query) :
    RecyclerView.Adapter<TeamListAdapter.TeamViewHolder>() {

    private var teams: MutableList<teamData> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null


    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val namaTimTextView: TextView = itemView.findViewById(R.id.nama_tim)
        private val seasonTextView: TextView = itemView.findViewById(R.id.season_tim)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val team = teams[position]
                    onItemClickListener?.onItemClick(team)
                }
            }
        }

        fun bind(team: teamData) {
            namaTimTextView.text = team.nama_team
            seasonTextView.text = team.season
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.team_row, parent, false)
        return TeamViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teams[position]
        holder.bind(team)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailTeam::class.java)
            intent.putExtra("nama_team", team.nama_team)
            intent.putExtra("season", team.season)
            intent.putExtra("coach", team.coach)
            intent.putExtra("asisten", team.asisten)
            intent.putExtra("instansi", team.instansi)
            intent.putExtra("alamat", team.alamat)
            intent.putExtra("kota", team.kota)
            intent.putExtra("provinsi", team.provinsi)
            intent.putExtra("negara", team.negara)
            intent.putExtra("email", team.email)
            intent.putExtra("logo", team.logo)
            intent.putExtra("jersey", team.jersey)
            intent.putExtra("jenis_kelamin", team.jenis_kelamin)
            intent.putExtra("jumlah_pemain", team.jumlah_pemain)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    fun startListening() {
        query.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                return@addSnapshotListener
            }
            teams.clear()
            for (document in snapshot!!) {
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

    fun stopListening() {
        teams.clear()
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(team: teamData)
    }
}
