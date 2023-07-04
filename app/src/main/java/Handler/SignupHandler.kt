package Handler

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class SignupHandler {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun pushDataToFirestore(context: Context, data: Map<String, Any>) {
        // Dapatkan referensi koleksi yang ingin Anda gunakan
        val collectionRef = db.collection("users")

        // Buat dokumen baru dengan data yang diberikan
        collectionRef
            .add(data)
            .addOnSuccessListener { documentReference ->
                val toast = Toast.makeText(context, "Berhasil menambahkan data", Toast.LENGTH_SHORT)
                toast.show()
                val documentId = documentReference.id

            }
            .addOnFailureListener { e ->

            }
    }
}
