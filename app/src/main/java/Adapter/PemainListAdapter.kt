import Data.pemainData
import Tim.DetailPemain
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.statsapp.R
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class PemainListAdapter(private val query: Query) :
    RecyclerView.Adapter<PemainListAdapter.PemainViewHolder>() {

    private var pemains : MutableList<pemainData> = mutableListOf()

    inner class PemainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val namaPemainTextView: TextView = itemView.findViewById(R.id.nama_pemain)
        private val rolePemainTextView: TextView = itemView.findViewById(R.id.role_pemain)
        private val nomorPemainTextView: TextView = itemView.findViewById(R.id.no_punggung)

        fun bind(pemain: pemainData) {
            namaPemainTextView.text = pemain.nama_pemain
            rolePemainTextView.text = pemain.role_pemain
            nomorPemainTextView.text = pemain.no_punggung
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PemainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.pemain_row, parent, false)
        return PemainViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pemains.size
    }

    override fun onBindViewHolder(holder: PemainViewHolder, position: Int) {
        val pemain = pemains[position]
        holder.bind(pemain)

        val documentId = pemains[position].id
        val context = holder.itemView.context
        val intent = Intent(context, DetailPemain::class.java)
        intent.putExtra("documentId", documentId)
        holder.itemView.setOnClickListener {
            context.startActivity(intent)
        }
        println("documentId pemain yang dikirim: $documentId")
    }

    fun startListening() {
        query.addSnapshotListener { snapshot: QuerySnapshot?, exception: FirebaseFirestoreException? ->
            if (exception != null) {
                return@addSnapshotListener
            }

            pemains.clear() // Clear the list before adding new data

            for (document in snapshot?.documents!!) {
                val pemain = document.toObject(pemainData::class.java)
                pemain?.id = document.id
                pemains.add(pemain!!)
            }

            notifyDataSetChanged()
        }
    }

}
