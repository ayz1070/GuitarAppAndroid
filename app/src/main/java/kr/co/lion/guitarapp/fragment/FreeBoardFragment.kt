package kr.co.lion.guitarapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.guitarapp.MainActivity
import kr.co.lion.guitarapp.R
import kr.co.lion.guitarapp.databinding.ActivityMainBinding
import kr.co.lion.guitarapp.databinding.FragmentFreeBoardBinding
import kr.co.lion.guitarapp.databinding.RowFreeBoardBinding
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
        setRecyclerViewFreeBoard()

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

    fun setRecyclerViewFreeBoard(){
        binding.apply {
            recyclerViewFreeBoard.apply {
                // 어뎁터
                adapter = RecyclerViewAdapterFreeBoard()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(mainActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 메인 화면의 RecyclerView의 어뎁터
    inner class RecyclerViewAdapterFreeBoard : RecyclerView.Adapter<RecyclerViewAdapterFreeBoard.MainViewHolder>(){
        inner class MainViewHolder(rowMainBinding: RowFreeBoardBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding:RowFreeBoardBinding

            init {
                this.rowMainBinding = rowMainBinding

                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val rowMainBinding = RowFreeBoardBinding.inflate(layoutInflater)
            val mainViewHolder = MainViewHolder(rowMainBinding)
            return mainViewHolder
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.rowMainBinding.textViewTitleFreeBoardRow.text = "제목 $position"
            holder.rowMainBinding.textViewNickNameFreeBoardRow.text = "작성자 $position"
        }
    }

}