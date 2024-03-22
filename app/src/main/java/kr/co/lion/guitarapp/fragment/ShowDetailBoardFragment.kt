package kr.co.lion.guitarapp.fragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.guitarapp.MainActivity
import kr.co.lion.guitarapp.R
import kr.co.lion.guitarapp.databinding.FragmentShowDetailBoardBinding
import kr.co.lion.guitarapp.util.MainFragmentName
import kr.co.lion.guitarapp.viewmodel.ShowDetailBoardViewModel


class ShowDetailBoardFragment : Fragment() {

    lateinit var binding:FragmentShowDetailBoardBinding
    lateinit var mainActivity: MainActivity

    lateinit var showDetailBoardViewModel: ShowDetailBoardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_show_detail_board,container,false)
        showDetailBoardViewModel = ShowDetailBoardViewModel()
        binding.showDetailBoardViewModel = showDetailBoardViewModel
        binding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        setToolbar()

        return binding.root
    }

    // 툴바 설정
    fun setToolbar(){
        binding.apply{
            toolbarShowDetailBoard.apply{
                title = ""
                setNavigationIcon(R.drawable.ic_arrow_back_24)
                setNavigationOnClickListener {
                    // 백버튼 클릭 이벤트
                    mainActivity.removeFragment(MainFragmentName.SHOW_DETAIL_BOARD_FRAGMENT)
                }

                inflateMenu(R.menu.menu_show_detail_board)

                setOnMenuItemClickListener {
                    when(it.itemId){
                        // 수정 아이콘 클릭 시
                        R.id.menuItemModifyShowDetailBoard -> {
                            mainActivity.replaceFragment(MainFragmentName.MODIFY_BOARD_FRAGMENT,true,true,null)
                        }

                        // 삭제 아이콘 클릭 시
                        R.id.menuItemDeleteShowDetailBoard -> {
                            setDeleteDialog()
                        }
                    }
                    true
                }
            }
        }
    }

    fun setDeleteDialog(){
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(requireContext())
        materialAlertDialogBuilder.apply{
            setTitle("삭제")
            setMessage("정말로 삭제하시겠습니까?")
            setPositiveButton("삭제"){ dialogInterface: DialogInterface, i: Int ->

            }
            setNegativeButton("닫기"){ dialogInterface: DialogInterface, i: Int ->

            }
            show()
        }
    }
}