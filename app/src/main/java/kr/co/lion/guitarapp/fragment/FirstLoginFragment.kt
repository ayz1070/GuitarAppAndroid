package kr.co.lion.guitarapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.co.lion.guitarapp.LoginActivity
import kr.co.lion.guitarapp.R
import kr.co.lion.guitarapp.databinding.FragmentFirstLoginBinding
import kr.co.lion.guitarapp.util.LoginFragmentName
import kr.co.lion.guitarapp.viewmodel.FirstLoginViewModel


class FirstLoginFragment : Fragment() {
    lateinit var firstLoginViewModel: FirstLoginViewModel

    lateinit var binding:FragmentFirstLoginBinding
    lateinit var loginActivity:LoginActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first_login,container,false)

        firstLoginViewModel = FirstLoginViewModel()
        binding.firstLoginViewModel = firstLoginViewModel
        binding.lifecycleOwner = this

        loginActivity = activity as LoginActivity

        setGoogleLoginButton()

        return binding.root
    }

    fun setGoogleLoginButton(){
        binding.apply{
            buttonGoogleLoginFirstLogin.apply{
                setOnClickListener {
                    loginActivity.replaceFragment(LoginFragmentName.INPUT_PROFILE_FRAGMENT,true,true,null)
                }
            }
        }
    }

}