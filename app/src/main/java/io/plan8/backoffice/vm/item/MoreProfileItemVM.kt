package io.plan8.backoffice.vm.item

import android.databinding.Bindable
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import com.afollestad.materialdialogs.MaterialDialog
import com.makeramen.roundedimageview.RoundedImageView
import io.plan8.backoffice.BR
import io.plan8.backoffice.R
import io.plan8.backoffice.model.item.MoreProfileItem
import io.plan8.backoffice.vm.FragmentVM
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.provider.MediaStore
import android.content.Intent
import android.net.Uri
import io.plan8.backoffice.Constants


/**
 * Created by SSozi on 2017. 11. 10..
 */
class MoreProfileItemVM(var fragment: Fragment, savedInstanceState: Bundle?, var MoreProfileItem: MoreProfileItem) : FragmentVM(fragment, savedInstanceState) {
    private var bottomSheet: BottomSheetDialog = BottomSheetDialog(context)

    var testUri: String? = null
        @Bindable
        get() {
            if (Constants.me != null){
                return Constants.me!!.avatar
            }
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.testUri)
        }


    init {
        bottomSheet.setContentView(R.layout.bottom_sheet_layout)
        bottomSheet.findViewById<AppCompatImageView>(R.id.bottomSheetFirstIcon)!!.setImageResource(R.drawable.ic_line_field)
        bottomSheet.findViewById<AppCompatImageView>(R.id.bottomSheetFirstIcon)!!.setColorFilter(ContextCompat.getColor(fragment.context, R.color.grayColor))
        bottomSheet.findViewById<TextView>(R.id.bottomSheetFirstTitle)!!.text = "이름 편집"
        bottomSheet.findViewById<RelativeLayout>(R.id.bottomSheetFirstItem)!!.setOnClickListener {
            bottomSheet.hide()
            MaterialDialog.Builder(fragment.context)
                    .title("내 이름 편집")
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .positiveText("완료")
                    .negativeText("취소")
                    //                .negativeColor(Color.parseColor("#ff0000"))
                    .input("다른 사람에게 표시될 프로필명을 입력하세요.", MoreProfileItem.profileName) { dialog, input ->
                        if (input != "") {
                            setProfileName(input.toString())
                        }
                        dialog.dismiss()
                    }.show()
        }
        bottomSheet.findViewById<AppCompatImageView>(R.id.bottomSheetSecondIcon)!!.setImageResource(R.drawable.ic_line_camera)
        bottomSheet.findViewById<AppCompatImageView>(R.id.bottomSheetSecondIcon)!!.setColorFilter(ContextCompat.getColor(fragment.context, R.color.grayColor))
        bottomSheet.findViewById<TextView>(R.id.bottomSheetSecondTitle)!!.text = "프로필 사진 변경"
        bottomSheet.findViewById<RelativeLayout>(R.id.bottomSheetSecondItem)!!.setOnClickListener {
            bottomSheet.hide()
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = "image/*"
            fragment.startActivityForResult(intent, Constants.SELECT_IMAGE_CODE)
        }
    }

    @Bindable
    fun getProfileName(): String {
        return MoreProfileItem.profileName
    }

    private fun setProfileName(profileName: String) {
        MoreProfileItem.profileName = profileName
        notifyPropertyChanged(BR.profileName)
    }

    @Bindable
    fun getProfilePhoneNumber(): String {
        return MoreProfileItem.profilePhoneNumber
    }

    fun editProfile(view: View) {
        bottomSheet.show()
//        when (bottomSheet.isShowing) {
//            true -> {
//                bottomSheet.hide()
//                Log.e("isShowing", "true")
//            }
//            false -> {
//                bottomSheet.show()
//                Log.e("isShowing", "false")
//            }
//        }
    }
}
