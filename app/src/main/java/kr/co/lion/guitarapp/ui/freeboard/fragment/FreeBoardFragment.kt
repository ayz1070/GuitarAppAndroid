package kr.co.lion.guitarapp.ui.freeboard.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.guitarapp.ui.freeboard.BoardActivity
import kr.co.lion.guitarapp.R
import kr.co.lion.guitarapp.databinding.FragmentFreeBoardBinding
import kr.co.lion.guitarapp.databinding.RowFreeBoardBinding
import kr.co.lion.guitarapp.util.MainFragmentName
import kr.co.lion.guitarapp.ui.freeboard.viewmodel.FreeBoardViewModel


class FreeBoardFragment : Fragment() {

    lateinit var binding:FragmentFreeBoardBinding
    lateinit var boardActivity: BoardActivity
    lateinit var freeBoardViewModel: FreeBoardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_free_board, container, false)
        freeBoardViewModel = FreeBoardViewModel()
        binding.freeBoardViewModel = freeBoardViewModel
        binding.lifecycleOwner = this

        boardActivity = activity as BoardActivity

        setToolbar()
        setSearchBar()
        setRecyclerViewFreeBoard()
        setRecyclerViewSearchFreeBoard()


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
                inflateMenu(R.menu.menu_free_board)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menuItemAddFreeBoard -> {
                            boardActivity.replaceFragment(MainFragmentName.ADD_BOARD_FRAGMENT,true,true,null)
                        }
                    }
                    true
                }
            }
        }
    }

    fun setSearchBar(){
        binding.apply{
            searchBarFreeBoard.apply{
                // SearchBar에 보여줄 메시지
                hint = "여기를 눌러 검색해주세요"
                // 메뉴
                inflateMenu(R.menu.menu_search_free_board)

            }

            searchViewFreeBoard.apply{
                // SearchView에 보여줄 메시지
                hint = "검색어를 입력해주세요"
            }
        }
    }

    fun setRecyclerViewFreeBoard(){
        binding.apply {
            recyclerViewFreeBoard.apply {
                // 어뎁터
                adapter = RecyclerViewAdapterFreeBoard()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(boardActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(boardActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
    // 검색 화면의 RecyclerView를 구성하는 메서드
    fun setRecyclerViewSearchFreeBoard(){
        binding.apply {
            recyclerViewSearchFreeBoard.apply {
                // 어뎁터
                adapter = RecyclerViewAdapterSearchFreeBoard()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(boardActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(boardActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 메인 화면의 RecyclerView의 어뎁터
    inner class RecyclerViewAdapterFreeBoard : RecyclerView.Adapter<RecyclerViewAdapterFreeBoard.ViewHolderFreeBoard>(){
        inner class ViewHolderFreeBoard(rowFreeBoardBinding: RowFreeBoardBinding) : RecyclerView.ViewHolder(rowFreeBoardBinding.root){
            val rowFreeBoardBinding:RowFreeBoardBinding

            init {
                this.rowFreeBoardBinding = rowFreeBoardBinding

                this.rowFreeBoardBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFreeBoard {
            val rowMainBinding = RowFreeBoardBinding.inflate(layoutInflater)
            val mainViewHolder = ViewHolderFreeBoard(rowMainBinding)
            return mainViewHolder
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: ViewHolderFreeBoard, position: Int) {
            holder.rowFreeBoardBinding.textViewTitleFreeBoardRow.text = "제목 $position"
            holder.rowFreeBoardBinding.textViewNickNameFreeBoardRow.text = "작성자 $position"

            holder.rowFreeBoardBinding.root.setOnClickListener {
                boardActivity.replaceFragment(MainFragmentName.SHOW_DETAIL_BOARD_FRAGMENT,true,true,null)
            }
        }
    }

    // 검색 화면의 RecyclerView의 어뎁터
    inner class RecyclerViewAdapterSearchFreeBoard : RecyclerView.Adapter<RecyclerViewAdapterSearchFreeBoard.ViewHolderSearchFreeBoard>(){
        inner class ViewHolderSearchFreeBoard(rowMainBinding: RowFreeBoardBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowFreeBoardBinding:RowFreeBoardBinding

            init {
                this.rowFreeBoardBinding = rowMainBinding

                this.rowFreeBoardBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSearchFreeBoard {
            val rowMainBinding = RowFreeBoardBinding.inflate(layoutInflater)
            val searchViewHolder = ViewHolderSearchFreeBoard(rowMainBinding)
            return searchViewHolder
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: ViewHolderSearchFreeBoard, position: Int) {
            holder.rowFreeBoardBinding.textViewTitleFreeBoardRow.text = "제목 $position"
            holder.rowFreeBoardBinding.textViewNickNameFreeBoardRow.text = "작성자 $position"
        }
    }

}