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

        holder.itemView.setOnClickListener{
            val documentId = pemains[position].id
            val intent = Intent(holder.itemView.context, DetailPemain::class.java)
            intent.putExtra("documentId", documentId)
            holder.itemView.context.startActivity(intent)
            println("DocumentId pemain yang dikirim: $documentId")
        }
    }

    fun startListening() {
        query.addSnapshotListener { snapshot: QuerySnapshot?, exception: FirebaseFirestoreException? ->
            if (exception != null) {
                return@addSnapshotListener
            }

            if (snapshot != null) {
                pemains.clear()
                for (document in snapshot) {
                    val pemain = pemainData(
                        document.id,
                        document.getString("nama_pemain")!!,
                        document.getString("role_pemain")!!,
                        document.getString("no_punggung")!!,
                        document.getString("lateralitas_pemain")!!,
                        document.getString("tinggi_pemain")!!,
                        document.getString("berat_pemain")!!,
                        document.getString("bmi_pemain")!!,
                        document.getString("tanggal_lahir_pemain")!!,
                        document.getString("kelamin_pemain")!!,
                        document.getString("domisili_pemain")!!,
                        document.getString("nomor_handphone_pemain")!!,
                        document.getString("id_tim_pemain")!!,
                        document.getString("foto_pemain")!!,
                        document.getString("status_pemain")!!,
                        document.getString("email_pemain")!!,
                    )
                    pemains.add(pemain)
                }
                notifyDataSetChanged()
            }
        }
    }


}
