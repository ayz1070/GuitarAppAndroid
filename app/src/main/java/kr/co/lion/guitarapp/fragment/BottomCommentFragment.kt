package kr.co.lion.guitarapp.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.divider.MaterialDivider
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.guitarapp.MainActivity
import kr.co.lion.guitarapp.R
import kr.co.lion.guitarapp.databinding.FragmentBottomCommentBinding
import kr.co.lion.guitarapp.databinding.RowCommentBinding
import kr.co.lion.guitarapp.viewmodel.BottomCommentViewModel


class BottomCommentFragment : BottomSheetDialogFragment() {

    lateinit var binding:FragmentBottomCommentBinding
    lateinit var mainActivity: MainActivity

    lateinit var bottomCommentViewModel: BottomCommentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mainActivity = activity as MainActivity

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_bottom_comment, container, false)
        bottomCommentViewModel = BottomCommentViewModel()
        binding.bottomCommentViewModel = bottomCommentViewModel
        binding.lifecycleOwner = this

        setRecyclerViewBottomComment()

        return binding.root
    }

    fun setRecyclerViewBottomComment(){
        binding.apply{
            recyclerViewCommentBottomComment.apply{
                // 어댑터
                adapter = RecyclerViewAdapterBottomComment()
                layoutManager = LinearLayoutManager(mainActivity)

                // 데코레이션
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 다이얼로그를 받는다.
        val dialog = super.onCreateDialog(savedInstanceState)
        // 다이얼로그가 보일 때 동작하는 리스너
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            // 높이 설정
            setBottomSheetHeight(bottomSheetDialog)

        }
        return dialog
    }


    // 댓글 목록을 보여줄 RecyclerView의 어뎁터
    inner class RecyclerViewAdapterBottomComment : RecyclerView.Adapter<RecyclerViewAdapterBottomComment.ViewHolderBottomComment>(){

        inner class ViewHolderBottomComment(rowCommentBinding: RowCommentBinding) : RecyclerView.ViewHolder(rowCommentBinding.root){
            val rowCommentBinding : RowCommentBinding

            init {
                this.rowCommentBinding = rowCommentBinding

                rowCommentBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBottomComment {
            val rowCommentBinding = RowCommentBinding.inflate(layoutInflater)
            val viewHolderBottomComment = ViewHolderBottomComment(rowCommentBinding)
            return viewHolderBottomComment
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: ViewHolderBottomComment, position: Int) {
            holder.rowCommentBinding.textViewNickNameBottomCommentRow.text = "작성자${position}"
            holder.rowCommentBinding.textViewDateBottomCommentRow.text = "2024년 03월 22일 15시 30분"
            holder.rowCommentBinding.textViewContentBottomCommentRow.text = "내용${position}"
            holder.rowCommentBinding.imageViewProfileCommentRow.setImageResource(R.drawable.tiger)
        }
    }

    // BottomSheet의 높이를 설정해준다.
    fun setBottomSheetHeight(bottomSheetDialog:BottomSheetDialog){
        // BottomSheet의 기본 뷰 객체를 가져온다
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)!!
        // BottomSheet 높이를 설정할 수 있는 객체를 생성한다.
        val behavior = BottomSheetBehavior.from(bottomSheet)
        // 높이를 설정한다.
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = getBottomSheetDialogHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

    }

    // BottomSheet의 높이를 구한다(화면 액정의 85% 크기)
    fun getBottomSheetDialogHeight() : Int {
        return (getWindowHeight() * 0.85).toInt()
    }

    // 사용자 단말기 액정의 길이를 구해 반환하는 메서드
    fun getWindowHeight() : Int {
        // 화면 크기 정보를 담을 배열 객체
        val displayMetrics = DisplayMetrics()
        // 액정의 가로 세로 길이 정보를 담아준다.
        mainActivity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        // 세로길이를 반환해준다.
        return displayMetrics.heightPixels
    }


}