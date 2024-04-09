package kr.co.lion.guitarapp.ui.setting.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kr.co.lion.guitarapp.R
import kr.co.lion.guitarapp.databinding.FragmentSettingMainBinding
import kr.co.lion.guitarapp.ui.setting.viewmodel.SettingMainViewModel


class SettingMainFragment : Fragment() {

    private var _binding: FragmentSettingMainBinding? = null
    private val binding get() = _binding!!

    private val settingMainViewModel by viewModels<SettingMainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentSettingMainBinding.inflate(inflater, container, false)

        setToolbar()


        return binding.root
    }

    fun setToolbar(){
        binding.apply{
            toolbarSettingMain.apply{
                title = "설정"
                setNavigationIcon(R.drawable.ic_arrow_back_24px)

                // 백버튼 구현
                setNavigationOnClickListener {

                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}