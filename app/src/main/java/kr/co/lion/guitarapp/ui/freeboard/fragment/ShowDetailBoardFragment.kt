package kr.co.lion.guitarapp.ui.freeboard.fragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.guitarapp.ui.freeboard.BoardActivity
import kr.co.lion.guitarapp.R
import kr.co.lion.guitarapp.databinding.FragmentShowDetailBoardBinding
import kr.co.lion.guitarapp.databinding.RowShowDetailBoardBinding
import kr.co.lion.guitarapp.util.MainFragmentName
import kr.co.lion.guitarapp.ui.freeboard.viewmodel.ShowDetailBoardViewModel


class ShowDetailBoardFragment : Fragment() {

    lateinit var binding: FragmentShowDetailBoardBinding
    lateinit var boardActivity: BoardActivity

    lateinit var showDetailBoardViewModel: ShowDetailBoardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_show_detail_board,container,false)
        showDetailBoardViewModel = ShowDetailBoardViewModel()
        binding.showDetailBoardViewModel = showDetailBoardViewModel
        binding.lifecycleOwner = this

        boardActivity = activity as BoardActivity

        setToolbar()
        setCarousel()
        setCommentButton()

        return binding.root
    }

    fun setCommentButton(){
        binding.apply{
            imageViewCommentShowDetailBoard.setOnClickListener {
                showBottomCommentSheet()
            }
        }
    }

    fun setCarousel(){
        binding.apply{
            // RecyclerView 셋팅
            recyclerViewPhotosShowDetailBoard.apply{
                // 어댑터
                adapter = RecyclerViewAdapterShowDetailBoard()
                // 레이아웃 매니저
                layoutManager = CarouselLayoutManager()
                // layoutManager = CarouselLayoutManager(MultiBrowseCarouselStrategy())
                // layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
                // layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())
            }
        }
    }

    // 툴바 설정
    fun setToolbar(){
        binding.apply{
            toolbarShowDetailBoard.apply{
                title = ""
                setNavigationIcon(R.drawable.ic_arrow_back_24)
                setNavigationOnClickListener {
                    // 백버튼 클릭 이벤트
                    boardActivity.removeFragment(MainFragmentName.SHOW_DETAIL_BOARD_FRAGMENT)
                }

                inflateMenu(R.menu.menu_show_detail_board)

                setOnMenuItemClickListener {
                    when(it.itemId){
                        // 수정 아이콘 클릭 시
                        R.id.menuItemModifyShowDetailBoard -> {
                            boardActivity.replaceFragment(MainFragmentName.MODIFY_BOARD_FRAGMENT,true,true,null)
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

    fun showBottomCommentSheet(){

        val bottomCommentFragment = BottomCommentFragment()
        bottomCommentFragment.show(boardActivity.supportFragmentManager, "BottomCommentSheet")
    }

    inner class RecyclerViewAdapterShowDetailBoard: RecyclerView.Adapter<RecyclerViewAdapterShowDetailBoard.ViewHolderShowDetailBoard>() {
        inner class ViewHolderShowDetailBoard(rowShowDetailBoardBinding: RowShowDetailBoardBinding): RecyclerView.ViewHolder(rowShowDetailBoardBinding.root){
            val rowShowDetailBoardBinding: RowShowDetailBoardBinding

            init{
                this.rowShowDetailBoardBinding = rowShowDetailBoardBinding
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderShowDetailBoard {
            val rowShowDetailBoardBinding = RowShowDetailBoardBinding.inflate(layoutInflater)
            val viewHolderAddBoard = ViewHolderShowDetailBoard(rowShowDetailBoardBinding)
            return viewHolderAddBoard
        }

        override fun getItemCount(): Int = 5

        override fun onBindViewHolder(holder: ViewHolderShowDetailBoard, position: Int) {
            holder.rowShowDetailBoardBinding.imageViewCarouselShowDetailBoard.setImageResource(R.drawable.img_dog)
        }
    }
}