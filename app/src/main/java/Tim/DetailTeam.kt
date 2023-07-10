package Tim


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.statsapp.R
import teamData

class DetailTeam : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        // Mendapatkan data tim dari Intent
        val team = intent.getSerializableExtra("team") as? teamData

        if (team != null) {
        }
    }
}
