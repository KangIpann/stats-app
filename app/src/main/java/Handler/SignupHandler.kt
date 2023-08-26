package Handler

import android.content.Context
import android.content.Intent
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.statsapp.starter.Login
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.example.statsapp.starter.Verify

class SignupHandler {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun registerUserWithEmail(context: Context, email: String, password: String, name: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    pushDataToFirestore(context, email, password, name)
                } else {
                    // Registration failed
                    showToast(context, "Gagal mendaftar pengguna: ${task.exception?.message}")
                }
            }
    }
    private fun pushDataToFirestore(context: Context, email: String, password: String, name: String) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val data = hashMapOf(
                "name" to name,
                "email" to email,
                "userId" to uid,
                "password" to password
        )
        if (uid != null) {
            db.collection("users").document(uid).set(data)
                .addOnSuccessListener {
                    showToast(context, "Berhasil mendaftar pengguna")
                    val intent = Intent(context, Login::class.java)
                    context.startActivity(intent)
                }
                .addOnFailureListener { e ->
                    showToast(context, "Gagal mendaftar pengguna: ${e.message}")
                }
        }
    }

    private fun showToast(context: Context, message: String) {
        // Display a toast with the given message
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }
}
