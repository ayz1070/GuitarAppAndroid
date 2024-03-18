package kr.co.lion.guitarapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.co.lion.guitarapp.MainActivity
import kr.co.lion.guitarapp.R
import kr.co.lion.guitarapp.databinding.ActivityMainBinding
import kr.co.lion.guitarapp.databinding.FragmentFreeBoardBinding
import kr.co.lion.guitarapp.viewmodel.FreeBoardViewModel


class FreeBoardFragment : Fragment() {

    lateinit var binding:FragmentFreeBoardBinding
    lateinit var mainActivity: MainActivity
    lateinit var freeBoardViewModel:FreeBoardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_free_board, container, false)
        freeBoardViewModel = FreeBoardViewModel()
        binding.freeBoardViewModel = freeBoardViewModel
        binding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        setToolbar()

        return binding.root
    }

    fun setToolbar(){
        binding.apply{
            toolbarFreeBoard.apply{
                // 타이틀
                title = "자유 게시판"
                // 네비게이션
                setNavigationIcon(R.drawable.ic_arrow_back_24)
                setNavigationOnClickListener {
                    // 백버튼 클릭 이벤트
                }
            }
        }
    }

}