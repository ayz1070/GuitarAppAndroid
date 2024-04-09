package kr.co.lion.guitarapp.ui.checkAttendance.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.guitarapp.R
import kr.co.lion.guitarapp.databinding.FragmentCheckAttendanceBinding
import kr.co.lion.guitarapp.databinding.RowCheckBinding
import kr.co.lion.guitarapp.ui.checkAttendance.AttendanceActivity
import kr.co.lion.guitarapp.ui.checkAttendance.viewmodel.CheckAttendanceViewModel

class CheckAttendanceFragment : Fragment() {

    lateinit var binding:FragmentCheckAttendanceBinding
    lateinit var attendanceActivity: AttendanceActivity
    lateinit var checkAttendanceViewModel: CheckAttendanceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        attendanceActivity = activity as AttendanceActivity

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_attendance, container, false)
        checkAttendanceViewModel = CheckAttendanceViewModel()
        binding.checkAttendanceViewModel = checkAttendanceViewModel
        binding.lifecycleOwner = this

        setToolbar()
        setRecyclerView()
        setAttendButton()
        setNotAttendButton()

        return binding.root
    }

    fun setRecyclerView(){
        binding.apply{
            recyclerViewCheckAttendanceAttend.apply{
                adapter = RecyclerViewAdapterCheckAttend()
                layoutManager = LinearLayoutManager(attendanceActivity,
                    LinearLayoutManager.HORIZONTAL,false)
            }
            recyclerViewCheckAttendanceNotAttend.apply{
                adapter = RecyclerViewAdapterCheckNotAttend()
                layoutManager = LinearLayoutManager(attendanceActivity,
                    LinearLayoutManager.HORIZONTAL,false)
            }

        }
    }

    fun setAttendButton(){
        binding.apply{
            buttonAttendCheckAttendance.setOnClickListener {

            }
        }
    }

    fun setNotAttendButton(){
        binding.apply {
            buttonNotAttendCheckAttendance.setOnClickListener {

            }
        }
    }

    fun setToolbar(){
        binding.apply{
            toolbarCheckAttendance.apply{
                title = "출석 체크"
                setNavigationIcon(R.drawable.ic_arrow_back_24)
                // 백버튼 이벤트
                setNavigationOnClickListener {

                }
            }
        }
    }

    //------------------------------------------------------------------------------------------
    // 출석 리사이클러뷰
    inner class RecyclerViewAdapterCheckAttend():
        RecyclerView.Adapter<RecyclerViewAdapterCheckAttend.ViewHolderCheckAttend>() {

        inner class ViewHolderCheckAttend(rowCheckBinding: RowCheckBinding): RecyclerView.ViewHolder(rowCheckBinding.root){
            val rowCheckBinding:RowCheckBinding

            init {
                this.rowCheckBinding = rowCheckBinding
                this.rowCheckBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCheckAttend {
            val rowCheckBinding = RowCheckBinding.inflate(layoutInflater)
            val viewHolderCheck = ViewHolderCheckAttend(rowCheckBinding)
            return viewHolderCheck
        }

        override fun getItemCount(): Int = 5

        override fun onBindViewHolder(holder: ViewHolderCheckAttend, position: Int) {
            // 이미지는 테스트용
            holder.rowCheckBinding.imageViewProfileCheckRow.setImageResource(R.drawable.img_dog)
            holder.rowCheckBinding.textViewNameCheckRow.text = "강아지"
        }
    }

    //------------------------------------------------------------------------------------------
    // 결석 리사이클러뷰
    inner class RecyclerViewAdapterCheckNotAttend():
        RecyclerView.Adapter<RecyclerViewAdapterCheckNotAttend.ViewHolderCheckNotAttend>() {

        inner class ViewHolderCheckNotAttend(rowCheckBinding: RowCheckBinding): RecyclerView.ViewHolder(rowCheckBinding.root){
            val rowCheckBinding:RowCheckBinding

            init {
                this.rowCheckBinding = rowCheckBinding
                this.rowCheckBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCheckNotAttend {
            val rowCheckBinding = RowCheckBinding.inflate(layoutInflater)
            val viewHolderCheckNotAttend = ViewHolderCheckNotAttend(rowCheckBinding)
            return viewHolderCheckNotAttend
        }

        override fun getItemCount(): Int = 3

        override fun onBindViewHolder(holder: ViewHolderCheckNotAttend, position: Int) {
            // 이미지는 테스트용
            holder.rowCheckBinding.imageViewProfileCheckRow.setImageResource(R.drawable.img_jch)
            holder.rowCheckBinding.textViewNameCheckRow.text = "손흥민"

        }
    }

}