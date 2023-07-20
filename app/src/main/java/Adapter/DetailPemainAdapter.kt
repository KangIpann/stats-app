package Adapter

import Data.pemainData
import Tim.DetailPemain
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
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot

class DetailPemainAdapter(private val query : DocumentReference) :
    RecyclerView.Adapter<DetailPemainAdapter.DetailPemainViewHolder>() {

    private var pemains : MutableList<pemainData> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null

    inner class DetailPemainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val namaPemainEditText: EditText = itemView.findViewById(R.id.et_nama_pemain_detailpemain)
            val posisiPemainEditText: EditText = itemView.findViewById(R.id.et_posisi_pemain_detailpemain)
            val nomorPunggungEditText: EditText = itemView.findViewById(R.id.et_nomer_punggung_detailpemain)
            val lateralitasEditText: EditText = itemView.findViewById(R.id.et_lateralitas_pemain_detailpemain)
            val beratBadanEditText: EditText = itemView.findViewById(R.id.et_beratbadan_pemain_detailpemain)
            val bmiEditText: EditText = itemView.findViewById(R.id.et_bmi_pemain_detailpemain)
            val tanggalLahirEditText: EditText = itemView.findViewById(R.id.et_tanggal_lahir_detailpemain)
            val jenisKelaminEditText: EditText = itemView.findViewById(R.id.et_jeniskelamin_pemain_detailpemain)
            val domisiliEditText: EditText = itemView.findViewById(R.id.et_domisili_pemain_detailpemain)
            val nomorHpEditText: EditText = itemView.findViewById(R.id.et_nomorhp_pemain_detailpemain)
            val fotoPemain: ImageView = itemView.findViewById(R.id.iv_profilpemain_detailpemain)

        init {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(adapterPosition)
            }
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }


    fun addSnapshotListener() {
        query.addSnapshotListener { value, error ->
            error?.let {
                return@addSnapshotListener
            }
            value?.let {
                pemains.clear()
                for (document in value) {
                    pemains.add(pemainData.fromSnapshot(document))
                }
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPemainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_pemain_row, parent, false)
        return DetailPemainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pemains.size
    }

    override fun onBindViewHolder(holder: DetailPemainViewHolder, position: Int) {
        val pemain = pemains[position]
        Glide.with(holder.itemView.context)
            .load(pemain.foto_pemain)
            .transition(com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade())
            .into(holder.fotoPemain)

        holder.namaPemainEditText.setText(pemain.nama_pemain)
        holder.posisiPemainEditText.setText(pemain.role_pemain)
        holder.nomorPunggungEditText.setText(pemain.no_punggung)
        holder.lateralitasEditText.setText(pemain.lateralitas_pemain)
        holder.beratBadanEditText.setText(pemain.berat_pemain)
        holder.bmiEditText.setText(pemain.bmi_pemain)
        holder.tanggalLahirEditText.setText(pemain.tanggal_lahir_pemain)
        holder.jenisKelaminEditText.setText(pemain.kelamin_pemain)
        holder.domisiliEditText.setText(pemain.domisili_pemain)
        holder.nomorHpEditText.setText(pemain.nomor_handphone_pemain)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}

private operator fun DocumentSnapshot.iterator(): Iterator<QueryDocumentSnapshot> {
    return iterator()
}
