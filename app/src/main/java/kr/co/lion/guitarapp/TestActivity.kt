package kr.co.lion.guitarapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.lion.guitarapp.databinding.ActivityTestBinding
import kr.co.lion.guitarapp.ui.checkAttendance.AttendanceActivity
import kr.co.lion.guitarapp.ui.main.MainActivity

class TestActivity : AppCompatActivity() {
    lateinit var binding:ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)

        binding.buttonTest1.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonTest2.setOnClickListener {
            val intent = Intent(this,AttendanceActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}