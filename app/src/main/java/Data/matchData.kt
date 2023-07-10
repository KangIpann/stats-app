package Data

data class matchData(
    val tim_home: String,
    val tim_guest: String,
    val tgl_match: String,
    val skor_home: String,
    val skor_guest: String,
    val id_match: String
) {
    class OnItemClickListener {
        fun onItemClick(match: matchData) {

        }
    }
}


