package Adapter

import Data.teamData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.statsapp.R
import com.google.firebase.firestore.Query

class TeamListAdapter(private val query: Query) :
    RecyclerView.Adapter<TeamListAdapter.TeamViewHolder>() {

    private var teams: MutableList<teamData> = mutableListOf()

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val namaTimTextView: TextView = itemView.findViewById(R.id.nama_tim)
        private val seasonTextView: TextView = itemView.findViewById(R.id.season_tim)

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
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    fun startListening() {
        query.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                return@addSnapshotListener
            }
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
                    document.getString("logo")!!
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
}
