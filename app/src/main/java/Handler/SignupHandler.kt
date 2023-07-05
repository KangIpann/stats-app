package Handler

import android.content.Context
import android.content.Intent
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
                    sendEmailVerification(context, email, password, name)
                    pushDataToFirestore(context, email, password, name)
                } else {
                    // Registration failed
                    showToast(context, "Gagal mendaftar pengguna: ${task.exception?.message}")
                }
            }
    }
    fun pushDataToFirestore(context: Context, email: String, password: String, name: String) {
        val user = auth.currentUser
        val uid = user?.uid
        val data = hashMapOf(
            "name" to name,
            "email" to email,
            "password" to password
        )
        if (uid != null) {
            db.collection("users").document(uid).set(data)
                .addOnSuccessListener {
                    showToast(context, "Berhasil mendaftar pengguna")
                }
                .addOnFailureListener { e ->
                    showToast(context, "Gagal mendaftar pengguna: ${e.message}")
                }
        }
    }

    private fun sendEmailVerification(context: Context, email: String, password: String, name: String) {
        val user = auth.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Email verification sent successfully
                showToast(context, "Kode verifikasi telah dikirim melalui email")

                // Redirect the user to the Verify activity
                val intent = Intent(context, Verify::class.java)
                intent.putExtra("email", email)
                intent.putExtra("password", password)
                intent.putExtra("name", name)
                context.startActivity(intent)
            } else {
                // Failed to send email verification
                showToast(context, "Gagal mengirim kode verifikasi melalui email")
            }
        }
    }

    private fun showToast(context: Context, message: String) {
        // Display a toast with the given message
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }
}
