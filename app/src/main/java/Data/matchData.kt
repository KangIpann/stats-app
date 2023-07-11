package Data

import android.os.Parcel
import android.os.Parcelable

data class matchData(
    val id_match: String?,
    val tim_home: String?,
    val tim_guest: String?,
    val tgl_match: String?,
    val tgl_match2: String?,
    val nama_match: String?,
    val nama_match_second: String?,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_match)
        parcel.writeString(tim_home)
        parcel.writeString(tim_guest)
        parcel.writeString(tgl_match)
        parcel.writeString(tgl_match2)
        parcel.writeString(nama_match)
        parcel.writeString(nama_match_second)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<matchData> {
        override fun createFromParcel(parcel: Parcel): matchData {
            return matchData(parcel)
        }

        override fun newArray(size: Int): Array<matchData?> {
            return arrayOfNulls(size)
        }
    }
}


