package kr.co.lion.guitarapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.lion.guitarapp.databinding.ActivityTestBinding
import kr.co.lion.guitarapp.ui.checkAttendance.AttendanceActivity
import kr.co.lion.guitarapp.ui.freeboard.BoardActivity
import kr.co.lion.guitarapp.ui.login.LoginActivity

class TestActivity : AppCompatActivity() {
    lateinit var binding:ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)

        binding.buttonTest1.setOnClickListener {
            val intent = Intent(this, BoardActivity::class.java)
            startActivity(intent)
        }

        binding.buttonTest2.setOnClickListener {
            val intent = Intent(this,AttendanceActivity::class.java)
            startActivity(intent)
        }

        binding.buttonTest3.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}