package Tim

import Adapter.CustomSpinnerAdapter
import Adapter.DetailTeamAdapter
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.statsapp.R
import com.example.statsapp.starter.WelcomePage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import teamData
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.lang.Exception

class DetailTeam : AppCompatActivity() {

    private lateinit var detailTeamAdapter: DetailTeamAdapter
    private lateinit var db: FirebaseFirestore
    private lateinit var documentId: String
    private lateinit var storageRef: StorageReference
    private lateinit var selectedImageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        documentId = intent.getStringExtra("documentId").toString()
        db = FirebaseFirestore.getInstance()

        val ivDelete = findViewById<ImageView>(R.id.tim_btn_delete)
        ivDelete.setOnClickListener {
            showDeleteTeamDialog()
        }

        val ivLogoTim = findViewById<ImageView>(R.id.iv_logotim_detailtim)
        ivLogoTim.setOnClickListener {
            openGallery()
        }

        val ivBack = findViewById<ImageView>(R.id.tim_btn_back)
        ivBack.setOnClickListener {
            finish()
        }
        storageRef = FirebaseStorage.getInstance().reference
        PullData()

        val ivDetailPemain = findViewById<ImageView>(R.id.iv_daftarpemain)
        ivDetailPemain.setOnClickListener{
            val intent = Intent(this, PemainList::class.java)
            intent.putExtra("documentId", documentId)
            startActivity(intent)
        }

    }
    private fun showDeleteTeamDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.dialog_delete_team, null)
        dialogBuilder.setView(dialogView)
        val buttonDelete = dialogView.findViewById<Button>(R.id.button_delete_team)
        val buttonCancel = dialogView.findViewById<Button>(R.id.button_cancel_delete_team)

        val alertDialog = dialogBuilder.create()

        buttonDelete.setOnClickListener {
            db.collection("team").document(documentId).delete()
            try {
                Toast.makeText(this,"Sedang Menghapus Data", Toast.LENGTH_LONG).show()
                Thread.sleep(1500)
                val intent = Intent(baseContext, TeamList::class.java)
                startActivity(intent)
                Thread.sleep(1500)
                Toast.makeText(this,"Berhasil Menghapus Data", Toast.LENGTH_LONG).show()
                overridePendingTransition(androidx.appcompat.R.anim.abc_fade_in, androidx.appcompat.R.anim.abc_fade_out)
            }catch (e: Exception){
                e.printStackTrace()
            }
            alertDialog.dismiss()
        }
        buttonCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
    private fun PullData() {
        val docRef = db.collection("team").document(documentId)
        docRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                // Handle error
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val namaTim = snapshot.getString("nama_team")
                val season = snapshot.getString("season")
                val coach = snapshot.getString("coach")
                val asistenTim = snapshot.getString("asisten")
                val warnaJersey = snapshot.getString("jersey")
                val instansiTim = snapshot.getString("instansi")
                val alamatTim = snapshot.getString("alamat")
                val kotaKabTim = snapshot.getString("kota")
                val provinsiTim = snapshot.getString("provinsi")
                val negaraTim = snapshot.getString("negara")
                val emailTim = snapshot.getString("email")
                val jenisKelamin = snapshot.getString("jenis_kelamin")
                val jumlahPemain = snapshot.getString("jumlah_pemain")
                val logoTim = snapshot.getString("logo")
                val logoTimUrl = logoTim.toString()

                val ivLogoTim = findViewById<ImageView>(R.id.iv_logotim_detailtim)
                val etNamaTim = findViewById<EditText>(R.id.et_namatim_detailteam)
                val etJenisKelamin = findViewById<Spinner>(R.id.et_kelamintim_detailtim)
                val etSeason = findViewById<EditText>(R.id.et_season_detailteam)
                val etCoach = findViewById<EditText>(R.id.et_coach_detailteam)
                val etAsisten = findViewById<EditText>(R.id.et_asisten_detailteam)
                val etJumlahPemain = findViewById<EditText>(R.id.et_jumlahpemain_detailtim)
                val etWarnaJersey = findViewById<EditText>(R.id.et_warnajersey_detailteam)
                val etInstansi = findViewById<EditText>(R.id.et_instansi_detailteam)
                val etAlamat = findViewById<EditText>(R.id.et_alamat_detailteam)
                val etKotaKab = findViewById<EditText>(R.id.et_kotakab_detailteam)
                val etProvinsi = findViewById<EditText>(R.id.et_provinsi_detailteam)
                val etNegara = findViewById<EditText>(R.id.et_negara_detailteam)
                val etEmail = findViewById<EditText>(R.id.et_emailteam_detailteam)

                // Set data to views
                etNamaTim.setText(namaTim)
                etSeason.setText(season)
                etCoach.setText(coach)
                etAsisten.setText(asistenTim)
                etJumlahPemain.setText(jumlahPemain)
                etWarnaJersey.setText(warnaJersey)
                etInstansi.setText(instansiTim)
                etAlamat.setText(alamatTim)
                etKotaKab.setText(kotaKabTim)
                etProvinsi.setText(provinsiTim)
                etNegara.setText(negaraTim)
                etEmail.setText(emailTim)

                val genderAdapter = CustomSpinnerAdapter(
                    this,
                    R.layout.spinner_kelamin_layout,
                    resources.getStringArray(R.array.gender_array).toList()
                )
                etJenisKelamin.adapter = genderAdapter
                val genderIndex = genderAdapter.getPosition(jenisKelamin)
                etJenisKelamin.setSelection(genderIndex)

                // Add listener for EditText
                etNamaTim.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val newNamaTim = etNamaTim.text.toString()
                        updateData("nama_team", newNamaTim)
                    }
                }

                etSeason.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val newSeason = etSeason.text.toString()
                        updateData("season", newSeason)
                    }
                }

                etCoach.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val newCoach = etCoach.text.toString()
                        updateData("coach", newCoach)
                    }
                }

                etAsisten.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val newAsisten = etAsisten.text.toString()
                        updateData("asisten", newAsisten)
                    }
                }

                etJumlahPemain.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val newJumlahPemain = etJumlahPemain.text.toString()
                        updateData("jumlah_pemain", newJumlahPemain)
                    }
                }

                etWarnaJersey.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val newWarnaJersey = etWarnaJersey.text.toString()
                        updateData("jersey", newWarnaJersey)
                    }
                }

                etInstansi.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val newInstansi = etInstansi.text.toString()
                        updateData("instansi", newInstansi)
                    }
                }

                etAlamat.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val newAlamat = etAlamat.text.toString()
                        updateData("alamat", newAlamat)
                    }
                }

                etKotaKab.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val newKotaKab = etKotaKab.text.toString()
                        updateData("kota", newKotaKab)
                    }
                }

                etProvinsi.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val newProvinsi = etProvinsi.text.toString()
                        updateData("provinsi", newProvinsi)
                    }
                }

                etNegara.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val newNegara = etNegara.text.toString()
                        updateData("negara", newNegara)
                    }
                }

                etEmail.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val newEmail = etEmail.text.toString()
                        updateData("email", newEmail)
                    }
                }

                etJenisKelamin.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val selectedGender = genderAdapter.getItem(position).toString()
                        updateData("jenis_kelamin", selectedGender)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // Do nothing
                    }
                })
            }

            val logoTimUrl = snapshot?.getString("logo").toString()
            val ivLogoTim = findViewById<ImageView>(R.id.iv_logotim_detailtim)
            Glide.with(this)
                .load(logoTimUrl)
                .transition(com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade())
                .into(ivLogoTim)
        }
    }

    private fun updateData(field: String, value: Any) {
        val docRef = db.collection("team").document(documentId)
        docRef.update(field, value)
            .addOnSuccessListener {
                println("Data yang berhasil diubah: +$field + $value")
                Toast.makeText(this,"Berhasil ubah data: $field", Toast.LENGTH_LONG)
            }
            .addOnFailureListener { exception ->

            }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data
            if (selectedImage != null) {
                selectedImageUri = selectedImage
            }

            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage)
                val ivLogoTim = findViewById<ImageView>(R.id.iv_logotim_detailtim)
                ivLogoTim.setImageBitmap(bitmap)

                uploadLogoToFirebaseStorage()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun uploadLogoToFirebaseStorage() {
        val logoRef = storageRef.child("logo_tim/logo_${documentId}")
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
        val compressedBitmap = compressBitmap(bitmap, 500)
        val baos = ByteArrayOutputStream()
        compressedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = logoRef.putBytes(data)
        uploadTask.addOnFailureListener {
            Toast.makeText(this, "Gagal upload logo tim", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener { taskSnapshot ->
            Toast.makeText(this, "Berhasil upload logo tim", Toast.LENGTH_SHORT).show()
            logoRef.downloadUrl.addOnSuccessListener { uri ->
                val logoUrl = uri.toString()
                updateData("logo", logoUrl)
            }
        }
    }

    private fun compressBitmap(originalBitmap: Bitmap, maxSize: Int): Bitmap {
        var width = originalBitmap.width
        var height = originalBitmap.height

        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            val scaledHeight = width / bitmapRatio
            height = scaledHeight.toInt()
        } else {
            height = maxSize
            val scaledWidth = height * bitmapRatio
            width = scaledWidth.toInt()
        }

        return Bitmap.createScaledBitmap(originalBitmap, width, height, true)
    }
    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        private const val REQUEST_IMAGE_GALLERY = 1
    }
}