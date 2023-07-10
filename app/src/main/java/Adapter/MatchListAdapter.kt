package Adapter

import Data.matchData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.statsapp.R


class MatchListAdapter(private val query: com.google.firebase.firestore.Query) : RecyclerView.Adapter<MatchListAdapter.MatchViewHolder>() {

    private var matches: MutableList<matchData> = mutableListOf()
    private var onItemClickListener: matchData.OnItemClickListener? = null

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val TimHomeTextView: TextView = itemView.findViewById(R.id.tim_home)
        private val TimGuestTextView: TextView = itemView.findViewById(R.id.tim_guest)
        private val TanggalMatchTextView: TextView = itemView.findViewById(R.id.tgl_match)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val match = matches[position]
                    onItemClickListener?.onItemClick(match)
                }
            }
        }

        fun bind(match: matchData) {
            TimHomeTextView.text = match.tim_home
            TimGuestTextView.text = match.tim_guest
            TanggalMatchTextView.text = match.tgl_match
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): MatchListAdapter.MatchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.list_match_row, parent, false)
        return MatchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MatchListAdapter.MatchViewHolder, position: Int) {
        val match = matches[position]
        holder.bind(match)

    }

    override fun getItemCount(): Int {
        return matches.size
    }

    fun startListening() {
        query.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            if (snapshot != null) {
                matches.clear()
                for (document in snapshot) {
                    val match = document.toObject(matchData::class.java)
                    matches.add(match)
                }
                notifyDataSetChanged()
            }
        }
    }
}