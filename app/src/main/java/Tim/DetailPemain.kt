package Tim

import Adapter.CustomSpinnerAdapter
import Adapter.DetailPemainAdapter
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.statsapp.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.util.Calendar

class DetailPemain : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var playerDocumentId: String
    private lateinit var storageReference: StorageReference
    private lateinit var selectedImageUri: Uri
    private lateinit var detailPemainAdapter: DetailPemainAdapter
    private lateinit var etTanggalLahirPemain: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pemain)

        playerDocumentId = intent.getStringExtra("documentId").toString()
        db = FirebaseFirestore.getInstance()
        println("DocumentId pemain yang diterima: $playerDocumentId")

        val ivBack = findViewById<ImageView>(R.id.pemain_btn_back)
        ivBack.setOnClickListener {
            onBackPressed()
        }

        val ivDeletePemain = findViewById<ImageView>(R.id.pemain_btn_delete)
        ivDeletePemain.setOnClickListener {
            showDeleteTeamDialog()
        }


        val ivFotoPemain = findViewById<ImageView>(R.id.iv_logopemain_detailteam)
        ivFotoPemain.setOnClickListener {
            openGallery()
        }

        etTanggalLahirPemain = findViewById(R.id.et_tanggallahir_detailpemain)
        etTanggalLahirPemain.setOnClickListener {
            showDatePickerDialog()
        }
        etTanggalLahirPemain.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showDatePickerDialog()
            }
        }

        storageReference = FirebaseStorage.getInstance().reference
        pullData()
    }

    private fun showDeleteTeamDialog(){
        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_delete_pemain, null)
        dialogBuilder.setView(dialogView)
        val buttonDeleteTeam = dialogView.findViewById<Button>(R.id.button_delete_pemain)
        val buttonCancelTeam = dialogView.findViewById<Button>(R.id.button_cancel_delete_pemain)

        val alertDialog = dialogBuilder.create()

        buttonDeleteTeam.setOnClickListener{
            deleteTeam()
            alertDialog.dismiss()
        }

        buttonCancelTeam.setOnClickListener{
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun deleteTeam() {
        val docRef = db.collection("pemain").document(playerDocumentId)
        docRef.delete()
            .addOnSuccessListener {
                println("DocumentSnapshot successfully deleted!")
                Toast.makeText(this, "Berhasil menghapus pemain", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
            .addOnFailureListener { e ->
                println("Error deleting document")
                Toast.makeText(this, "Gagal menghapus pemain", Toast.LENGTH_SHORT).show()
            }
    }

    private fun showDatePickerDialog(){
        val calendar = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val tanggalLahirEditText = findViewById<EditText>(R.id.et_tanggallahir_detailpemain)
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                tanggalLahirEditText.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
            }

        val datePickerDialog = DatePickerDialog(
            this, dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun updateDateInView() {
        val myFormat = "dd-MM-yyyy"
        val sdf = java.text.SimpleDateFormat(myFormat, java.util.Locale.US)
        val tanggalLahirEditText = findViewById<EditText>(R.id.et_tanggallahir_detailpemain)
        tanggalLahirEditText.setText(sdf.format(Calendar.getInstance().time))
    }

    private fun updateData(field: String, value: Any) {
        val docRef = db.collection("pemain").document(playerDocumentId)
        docRef.update(field, value)
            .addOnSuccessListener {
                println("DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                println("Error updating document")
                Toast.makeText(this, "Gagal mengupdate data", Toast.LENGTH_SHORT).show()
            }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1000)
    }

    private fun calculateBMI(berat: String, tinggi: String): String {
        if (berat.isNotEmpty() && tinggi.isNotEmpty()) {
            val beratDouble = berat.toDouble()
            val tinggiDouble = tinggi.toDouble()
            val tinggiMeter = tinggiDouble / 100
            val bmi = beratDouble / (tinggiMeter * tinggiMeter)
            return String.format("%.2f", bmi)
        }else{
            return ""
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val ivFotoPemain = findViewById<ImageView>(R.id.iv_logopemain_detailteam)
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                selectedImageUri = data?.data!!
                Glide.with(this)
                    .load(selectedImageUri)
                    .into(ivFotoPemain)
                uploadImageToFirebase(selectedImageUri)
            }
        }
    }

    private fun uploadImageToFirebase(selectedImageUri: Uri) {
        val fotoRef = storageReference.child("foto_pemain/$playerDocumentId")
        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImageUri)
        val compressedBitmap = compressBitmap(bitmap, 500)
        val baos = java.io.ByteArrayOutputStream()
        compressedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val image = baos.toByteArray()

        val uploadTask = fotoRef.putBytes(image)
        uploadTask.addOnFailureListener {
            Toast.makeText(this, "Gagal mengupload foto", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener {
            fotoRef.downloadUrl.addOnSuccessListener { uri ->
                // Use Glide to load the image with correct orientation
                Glide.with(this)
                    .load(uri)
                    .into(findViewById<ImageView>(R.id.iv_logopemain_detailteam))

                // Update the 'foto_pemain' field with the download URL
                updateData("foto_pemain", uri.toString())
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

    private fun pullData() {
        val docRef = db.collection("pemain").document(playerDocumentId)
        docRef.addSnapshotListener{ snapshot, e ->
            if(e != null){
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()){
                val namaPemain = snapshot.getString("nama_pemain")
                val rolePemain = snapshot.getString("role_pemain")
                val nomorPemain = snapshot.getString("no_punggung")
                val lateralitasPemain = snapshot.getString("lateralitas_pemain")
                val tinggiPemain = snapshot.getString("tinggi_pemain")
                val beratPemain = snapshot.getString("berat_pemain")
                val bmiPemain = snapshot.getString("bmi_pemain")
                val tanggalLahirPemain = snapshot.getString("tanggal_lahir_pemain")
                val kelaminPemain = snapshot.getString("kelamin_pemain")
                val domisiliPemain = snapshot.getString("domisili_pemain")
                val nomorHandphonePemain = snapshot.getString("nomor_handphone_pemain")
                val idTimPemain = snapshot.getString("id_tim_pemain")
                val fotoPemain = snapshot.getString("foto_pemain")
                val statusPemain = snapshot.getString("status_pemain")
                val emailPemain = snapshot.getString("email_pemain")

                //deklarasi id xml variable
                val EditTextNamaPemain = findViewById<EditText>(R.id.et_namapemain_detailpemain)
                val EditTextRolePemain = findViewById<Spinner>(R.id.et_posisipemain_detailpemain)
                val EditTextStatusPemain = findViewById<Spinner>(R.id.et_statuspemain_detailpemain)
                val EditTextNomorPemain = findViewById<EditText>(R.id.et_nomerpunggung_detailpemain)
                val EditTextTinggiPemain = findViewById<EditText>(R.id.et_tinggibadan_detailpemain)
                val EditTextLateralitasPemain =
                    findViewById<Spinner>(R.id.et_lateralitas_detailpemain)
                val EditTextBeratPemain = findViewById<EditText>(R.id.et_beratbadan_detailpemain)
                val EditTextBmiPemain = findViewById<EditText>(R.id.et_bmi_detailpemain)
                val EditTextTanggalLahirPemain =
                    findViewById<EditText>(R.id.et_tanggallahir_detailpemain)
                val EditTextKelaminPemain = findViewById<Spinner>(R.id.et_jeniskelamin_detailpemain)
                val EditTextDomisiliPemain = findViewById<EditText>(R.id.et_domisili_detailpemain)
                val EditTextNomorHandphonePemain =
                    findViewById<EditText>(R.id.et_nomorhp_detailpemain)
                val EditTextEmailPemain = findViewById<EditText>(R.id.et_emailpemain_detailpemain)

                //set value dari database ke xml
                EditTextNamaPemain.setText(namaPemain)
                EditTextNomorPemain.setText(nomorPemain)
                EditTextTinggiPemain.setText(tinggiPemain)
                EditTextBeratPemain.setText(beratPemain)
                EditTextBmiPemain.setText(bmiPemain)
                EditTextTanggalLahirPemain.setText(tanggalLahirPemain)
                EditTextDomisiliPemain.setText(domisiliPemain)
                EditTextNomorHandphonePemain.setText(nomorHandphonePemain)
                EditTextEmailPemain.setText(emailPemain)

                Glide.with(this)
                    .load(fotoPemain)
                    .into(findViewById<ImageView>(R.id.iv_logopemain_detailteam))

                //adapter posisi pemain
                val posisiAdapter = CustomSpinnerAdapter(
                    this,
                    R.layout.spinner_kelamin_layout,
                    resources.getStringArray(R.array.posisi_array).toList()
                )
                EditTextRolePemain.adapter = posisiAdapter
                val posisiIndex = posisiAdapter.getPosition(rolePemain)
                EditTextRolePemain.setSelection(posisiIndex)

                //adapter status pemain
                val statusAdapter = CustomSpinnerAdapter(
                    this,
                    R.layout.spinner_kelamin_layout,
                    resources.getStringArray(R.array.status_array).toList()
                )
                EditTextStatusPemain.adapter = statusAdapter
                val statusIndex = statusAdapter.getPosition(statusPemain)
                EditTextStatusPemain.setSelection(statusIndex)

                //adapter lateralitas pemain
                val lateralitasAdapter = CustomSpinnerAdapter(
                    this,
                    R.layout.spinner_kelamin_layout,
                    resources.getStringArray(R.array.lateralitas_array).toList()
                )
                EditTextLateralitasPemain.adapter = lateralitasAdapter
                val lateralitasIndex = lateralitasAdapter.getPosition(lateralitasPemain)
                EditTextLateralitasPemain.setSelection(lateralitasIndex)

                //adapter untuk jenis kelamin
                val kelaminAdapter = CustomSpinnerAdapter(
                    this,
                    R.layout.spinner_kelamin_layout,
                    resources.getStringArray(R.array.gender_array).toList()
                )
                EditTextKelaminPemain.adapter = kelaminAdapter
                val kelaminIndex = kelaminAdapter.getPosition(kelaminPemain)
                EditTextKelaminPemain.setSelection(kelaminIndex)

                EditTextNamaPemain.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        updateData("nama_pemain", EditTextNamaPemain.text.toString())
                    }
                }

                EditTextNomorPemain.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        updateData("no_punggung", EditTextNomorPemain.text.toString())
                    }
                }

                EditTextTinggiPemain.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        updateData("tinggi_pemain", EditTextTinggiPemain.text.toString())
                    }
                }

                EditTextBeratPemain.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        updateData("berat_pemain", EditTextBeratPemain.text.toString())
                    }
                }

                EditTextBmiPemain.setText(
                    calculateBMI(
                        EditTextBeratPemain.text.toString(),
                        EditTextTinggiPemain.text.toString()
                    )
                )
                updateData(
                    "bmi_pemain",
                    calculateBMI(
                        EditTextBeratPemain.text.toString(),
                        EditTextTinggiPemain.text.toString()
                    )
                )

                EditTextTanggalLahirPemain.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        updateData(
                            "tanggal_lahir_pemain",
                            EditTextTanggalLahirPemain.text.toString()
                        )
                    }
                }

                EditTextDomisiliPemain.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        updateData("domisili_pemain", EditTextDomisiliPemain.text.toString())
                    }
                }

                EditTextNomorHandphonePemain.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        updateData(
                            "nomor_handphone_pemain",
                            EditTextNomorHandphonePemain.text.toString()
                        )
                    }
                }

                EditTextEmailPemain.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        updateData("email_pemain", EditTextEmailPemain.text.toString())
                    }
                }

                EditTextRolePemain.setOnItemSelectedListener(object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        updateData("role_pemain", parent?.getItemAtPosition(position).toString())
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        //do nothing
                    }
                })

                EditTextStatusPemain.setOnItemSelectedListener(object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        updateData("status_pemain", parent?.getItemAtPosition(position).toString())
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        //do nothing
                    }
                })

                EditTextLateralitasPemain.setOnItemSelectedListener(object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        updateData(
                            "lateralitas_pemain",
                            parent?.getItemAtPosition(position).toString()
                        )
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        //do nothing
                    }
                })

                EditTextKelaminPemain.setOnItemSelectedListener(object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        updateData("kelamin_pemain", parent?.getItemAtPosition(position).toString())
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                })
            }
        }
    }
}
