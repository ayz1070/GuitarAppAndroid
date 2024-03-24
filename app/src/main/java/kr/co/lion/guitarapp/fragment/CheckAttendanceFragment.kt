package kr.co.lion.guitarapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.co.lion.guitarapp.MainActivity
import kr.co.lion.guitarapp.R
import kr.co.lion.guitarapp.databinding.FragmentCheckAttendanceBinding
import kr.co.lion.guitarapp.viewmodel.CheckAttendanceViewModel

class CheckAttendanceFragment : Fragment() {

    lateinit var binding:FragmentCheckAttendanceBinding
    lateinit var mainActivity: MainActivity
    lateinit var checkAttendanceViewModel: CheckAttendanceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mainActivity = activity as MainActivity

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_attendance, container, false)
        checkAttendanceViewModel = CheckAttendanceViewModel()
        binding.checkAttendanceViewModel = checkAttendanceViewModel
        binding.lifecycleOwner = this

        setToolbar()

        return binding.root
    }

    fun setToolbar(){
        binding.apply{
            toolbarCheckAttendance.apply{
                title = "출석 체크"
                setNavigationIcon(R.drawable.ic_arrow_back_24)
                // 백버튼 이벤트
                setNavigationOnClickListener {

                }
            }
        }
    }

}