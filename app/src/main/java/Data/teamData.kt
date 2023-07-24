import android.os.Parcel
import android.os.Parcelable
import android.widget.Spinner

data class teamData(
    var id: String,
    val nama_team: String,
    val season: String,
    val coach: String,
    val asisten: String,
    val instansi: String,
    val alamat: String,
    val kota: String,
    val provinsi: String,
    val negara: String,
    val email: String,
    val logo: String,
    val jersey: String,
    val jenis_kelamin: String,
    val jumlah_pemain: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nama_team)
        parcel.writeString(season)
        parcel.writeString(coach)
        parcel.writeString(asisten)
        parcel.writeString(instansi)
        parcel.writeString(alamat)
        parcel.writeString(kota)
        parcel.writeString(provinsi)
        parcel.writeString(negara)
        parcel.writeString(email)
        parcel.writeString(logo)
        parcel.writeString(jersey)
        parcel.writeString(jenis_kelamin)
        parcel.writeString(jumlah_pemain)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<teamData> {
        override fun createFromParcel(parcel: Parcel): teamData {
            return teamData(parcel)
        }

        override fun newArray(size: Int): Array<teamData?> {
            return arrayOfNulls(size)
        }
    }
}
