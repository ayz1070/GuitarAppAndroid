package kr.co.lion.guitarapp.fragment

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.media.MediaActionSound
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.lion.guitarapp.MainActivity
import kr.co.lion.guitarapp.R
import kr.co.lion.guitarapp.databinding.FragmentAddBoardBinding
import kr.co.lion.guitarapp.util.MainFragmentName
import kr.co.lion.guitarapp.util.Util
import kr.co.lion.guitarapp.viewmodel.AddBoardViewModel

class AddBoardFragment : Fragment() {
    lateinit var binding: FragmentAddBoardBinding
    lateinit var mainActivity: MainActivity

    lateinit var addBoardViewModel: AddBoardViewModel

    // Activity 실행을 위한 런처
    lateinit var albumLauncher:ActivityResultLauncher<Intent>

    // 이미지를 첨부한 적이 있는지...
    var isAddPicture = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_board, container, false)
        addBoardViewModel = AddBoardViewModel()
        binding.addBoardViewModel = addBoardViewModel
        binding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        setToolbar()
        setAlbumLauncher()

        setImageViewEvent()

        return binding.root
    }

    fun setToolbar(){
        binding.apply{
            toolbarAddBoard.apply{
                title = "자유게시판"
                setNavigationIcon(R.drawable.ic_arrow_back_24)
                // 백버튼 이벤트
                setNavigationOnClickListener{
                    mainActivity.removeFragment(MainFragmentName.ADD_BOARD_FRAGMENT)
                }
                inflateMenu(R.menu.menu_add_board)

                setOnMenuItemClickListener {
                    when(it.itemId){
                        // 완료 버튼 이벤트
                        R.id.menuCompleteAddBoard -> {

                        }
                    }
                    true
                }

            }
        }
    }

    fun setImageViewEvent(){
        binding.apply {
            imageViewPhotoAddBoard.apply{
                setOnClickListener {
                    startAlbumLauncher()
                }
            }
        }
    }

    // 앨범 런처 설정
    fun setAlbumLauncher(){
        // 앨범 실행을 위한 런처
        val contract = ActivityResultContracts.StartActivityForResult()
        albumLauncher = registerForActivityResult(contract){
            // 사진을 선택하고 돌아왔다면
            if(it.resultCode == AppCompatActivity.RESULT_OK){
                // 선택한 이미지으 ㅣ경로 데이터를 관리하는 Uri 객체를 추출한다.
                val uri = it.data?.data
                if(uri != null){
                    val bitmap = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                        // 이미지를 생성할 수 있는 객체를 생성한다.
                        val source = ImageDecoder.createSource(mainActivity.contentResolver, uri)
                        // Bitmap을 생성한다.
                        ImageDecoder.decodeBitmap(source)
                    }else{
                        // 컨텐츠 프로바이더를 통해 이미지 데이터에 접근한다.
                        val cursor = mainActivity.contentResolver.query(uri, null,null,null,null)
                        if(cursor!= null){
                            cursor.moveToNext()

                            // 이미지의 경로를 가져온다.
                            val idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                            val source = cursor.getString(idx)

                            // 이미지를 생성한다.
                            BitmapFactory.decodeFile(source)
                        }else{
                            null
                        }
                    }

                    // 회전 각도값을 가져온다.
                    val degree = Util.getDegree(mainActivity, uri)
                    // 회전 이미지를 가져온다.
                    val bitmap2 = Util.rotateBitmap(bitmap!!, degree.toFloat())
                    // 크기를 줄인 이미지를 가져온다.
                    val bitmap3 = Util.resizeBitmap(bitmap2, 1024)

                    binding.imageViewPhotoAddBoard.setImageBitmap(bitmap3)
                    isAddPicture = true

                }
            }
        }
    }

    // 앨범 런처를 실행하는 메서드
    fun startAlbumLauncher(){
        // 앨범에서 사진을 선택할 수 있도록 세팅된 인텐트를 생성한다.
        val albumIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        // 실행할 액티비티의 타입을 설정(이미지를 선택할 수 있는 것이 뜨게 한다.)
        albumIntent.setType("image/*")
        // 선택할 수 있는 파일들의 MimeType을 설정한다.
        val mimeType = arrayOf("image/*")
        albumIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
        // 액티비티를 실행한다.
        albumLauncher.launch(albumIntent)
    }
}