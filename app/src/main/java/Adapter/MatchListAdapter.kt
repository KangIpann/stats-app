package Adapter

import Data.matchData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.statsapp.R

class MatchListAdapter(private val query: com.google.firebase.firestore.Query)
    : RecyclerView.Adapter<MatchListAdapter.MatchViewHolder>() {

    private var matches: MutableList<matchData> = mutableListOf()

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val TimHomeTextView: TextView = itemView.findViewById(R.id.tim_home)
        private val TimGuestTextView: TextView = itemView.findViewById(R.id.tim_guest)
        private val TanggalMatchTextView: TextView = itemView.findViewById(R.id.tgl_match)
        private val namaMatchTextViewFirst: TextView = itemView.findViewById(R.id.urutan_match)

        fun bind(match: matchData) {
            TimHomeTextView.text = match.tim_home
            TimGuestTextView.text = match.tim_guest
            TanggalMatchTextView.text = match.tgl_match
            namaMatchTextViewFirst.text = match.nama_match
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.list_match_row, parent, false)
        return MatchViewHolder(itemView)
    }

    fun startListening() {
        query.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            if (snapshot != null) {
                matches.clear()
                for (document in snapshot) {
                    val matchs = matchData(
                        document.id,
                        document.getString("tim_home"),
                        document.getString("tim_guest"),
                        document.getString("tgl_match"),
                        document.getString("tgl_match2"),
                        document.getString("nama_match"),
                        document.getString("nama_match_second"),
                    )
                    matches.add(matchs)
                }
                notifyDataSetChanged()
            }
        }
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches[position]
        holder.bind(match)
    }

    override fun getItemCount(): Int {
        return matches.size
    }
}
