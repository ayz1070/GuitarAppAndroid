package kr.co.lion.guitarapp.ui.freeboard.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.co.lion.guitarapp.ui.main.MainActivity
import kr.co.lion.guitarapp.R
import kr.co.lion.guitarapp.databinding.FragmentModifyBoardBinding
import kr.co.lion.guitarapp.util.MainFragmentName
import kr.co.lion.guitarapp.ui.freeboard.viewmodel.ModifyBoardViewModel


class ModifyBoardFragment : Fragment() {
    lateinit var binding:FragmentModifyBoardBinding
    lateinit var mainActivity: MainActivity

    lateinit var modifyBoardViewModel: ModifyBoardViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mainActivity = activity as MainActivity

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_modify_board,container,false)
        modifyBoardViewModel = ModifyBoardViewModel()
        binding.modifyBoardViewModel = modifyBoardViewModel
        binding.lifecycleOwner = this

        setToolbar()


        return binding.root
    }

    fun setToolbar(){
        binding.toolbarModifyBoard.apply{
            title = "수정"

            setNavigationIcon(R.drawable.ic_arrow_back_24)
            setNavigationOnClickListener {
                mainActivity.removeFragment(MainFragmentName.MODIFY_BOARD_FRAGMENT)
            }
            inflateMenu(R.menu.menu_modify_board)
            setOnMenuItemClickListener {
                when(it.itemId){
                    // 수정 완료 아이콘 클릭 이벤트
                    R.id.menuCompleteModifyBoard -> {
                        mainActivity.removeFragment(MainFragmentName.MODIFY_BOARD_FRAGMENT)
                    }
                }
                true
            }
        }
    }

}