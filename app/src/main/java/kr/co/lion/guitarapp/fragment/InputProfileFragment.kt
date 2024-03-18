package kr.co.lion.guitarapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kr.co.lion.guitarapp.LoginActivity
import kr.co.lion.guitarapp.R
import kr.co.lion.guitarapp.databinding.FragmentInputProfileBinding
import kr.co.lion.guitarapp.viewmodel.InputProfileViewModel


class InputProfileFragment : Fragment() {
    lateinit var inputProfileViewModel: InputProfileViewModel
    lateinit var binding:FragmentInputProfileBinding
    lateinit var loginActivity: LoginActivity

    val weeks = arrayOf("월","화","수","목","금","토","일")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_input_profile,container,false)

        inputProfileViewModel = InputProfileViewModel()
        binding.inputProfileViewModel = inputProfileViewModel
        binding.lifecycleOwner = this

        loginActivity = activity as LoginActivity

        setToolbar()
        setWeekTextField()

        return binding.root
    }

    fun setToolbar(){
        binding.apply{
            toolbarInputProfile.apply{
                setNavigationIcon(R.drawable.ic_arrow_back_24)
                // 네비게이션 아이콘 클릭 이벤트
                setNavigationOnClickListener {

                }
                setOnMenuItemClickListener {
                    when(it.itemId){
                        // 다음 버튼 클릭 이벤트
                        R.id.menu_item_next_input_profile -> {

                        }
                    }
                    true
                }
                inflateMenu(R.menu.menu_input_profile)
            }

        }
    }

    fun setWeekTextField(){
        binding.apply{
            (textInputLayoutWeekInputProfile.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(weeks)
        }
    }
}