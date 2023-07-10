package Adapter

import Tim.DetailTeam
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.statsapp.R
import com.google.firebase.firestore.Query
import teamData

class TeamListAdapter(private val query: Query) :
    RecyclerView.Adapter<TeamListAdapter.TeamViewHolder>() {

    private var teams: MutableList<teamData> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

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
            intent.putExtra("team", team)
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
