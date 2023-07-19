package Data

import com.google.firebase.firestore.QueryDocumentSnapshot

data class pemainData(
    var id : String = "",
    val nama_pemain : String = "",
    val role_pemain : String = "",
    val no_punggung : String = "",
    val lateralitas_pemain : String = "",
    val tinggi_pemain : String = "",
    val berat_pemain : String = "",
    val bmi_pemain : String = "",
    val tanggal_lahir_pemain : String = "",
    val kelamin_pemain : String = "",
    val domisili_pemain : String = "",
    val nomor_handphone_pemain : String = "",
    val id_tim_pemain : String = "",
    val foto_pemain : String = "",
    val status_pemain : String = "",
    val email_pemain : String = "",
) {
    companion object {
        fun fromSnapshot(document: QueryDocumentSnapshot): pemainData {
            return pemainData(
                id = document.id,
                nama_pemain = document.getString("nama_pemain")!!,
                role_pemain = document.getString("role_pemain")!!,
                no_punggung = document.getString("no_punggung")!!,
                lateralitas_pemain = document.getString("lateralitas_pemain")!!,
                tinggi_pemain = document.getString("tinggi_pemain")!!,
                berat_pemain = document.getString("berat_pemain")!!,
                bmi_pemain = document.getString("bmi_pemain")!!,
                tanggal_lahir_pemain = document.getString("tanggal_lahir_pemain")!!,
                kelamin_pemain = document.getString("kelamin_pemain")!!,
                domisili_pemain = document.getString("domisili_pemain")!!,
                nomor_handphone_pemain = document.getString("nomor_handphone_pemain")!!,
                id_tim_pemain = document.getString("id_tim_pemain")!!,
                foto_pemain = document.getString("foto_pemain")!!,
                status_pemain = document.getString("status_pemain")!!,
                email_pemain = document.getString("email_pemain")!!,
            )
        }
    }
}
