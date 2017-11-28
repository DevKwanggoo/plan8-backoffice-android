package io.plan8.backoffice.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.plan8.backoffice.BR
import io.plan8.backoffice.Constants
import io.plan8.backoffice.R
import io.plan8.backoffice.databinding.FragmentMoreBinding
import io.plan8.backoffice.model.item.*
import io.plan8.backoffice.vm.MoreFragmentVM
import android.provider.MediaStore
import android.util.Log
import android.widget.RelativeLayout
import io.plan8.backoffice.SharedPreferenceManager
import io.plan8.backoffice.adapter.RestfulAdapter
import io.plan8.backoffice.model.api.UploadInfo
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Multipart
import java.io.File


/**
 * Created by chokwanghwan on 2017. 11. 9..
 */
class MoreFragment : BaseFragment() {
    var binding: FragmentMoreBinding? = null
    var progress: RelativeLayout? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val testData: MutableList<Any> = mutableListOf()
        //        이름, 전화번호, 주소, 예약일, 예약시간, 종료시간, 상품/이름, 추가 요청 사항, 내부 노트
        testData.add(MoreTitleItem("내 프로필"))
        var userName: String?
        if (Constants.me!!.userName != null) {
            userName = Constants.me!!.userName
        } else {
            userName = "이름 없음"
        }
        testData.add(MoreProfileItem(userName!!, Constants.me!!.phoneNumber!!))
        testData.add(MoreTitleItem("팀 선택"))

        if (Constants.me!!.team != null && Constants.me!!.team!!.isNotEmpty()) {
            for (team in Constants.me!!.team!!) {
                testData.add(MoreTeamItem(team.teamName, team.teamName))
            }
        }
        testData.add(EmptySpaceItem(0))
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_more, container, false)
        vm = MoreFragmentVM(this, savedInstanceState, testData)
        binding!!.setVariable(BR.vm, vm)
        binding!!.executePendingBindings()

        progress = binding!!.moreMenuProgressBar

        return binding!!.root
    }

    override fun onDestroy() {
        binding!!.unbind()
        super.onDestroy()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var vm: MoreFragmentVM? = null

        fun uploadImage(data: Uri, activity: Activity) {
            val uri = data
            val imagePath = getRealPathFromURI(uri, activity)

            val cursor = activity.contentResolver.query(Uri.parse(uri.toString()), null, null, null, null)
            cursor.moveToNext()
            val absolutePath = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
            cursor.close()

            val files = File(absolutePath)
            val requestFile = RequestBody.create(MediaType.parse(activity.contentResolver.getType(uri)), files)
            val multipartBody: MultipartBody.Part = MultipartBody.Part.createFormData("files", files.name, requestFile)

            if (RestfulAdapter.instance!!.serviceApi != null) {
                RestfulAdapter.instance!!.serviceApi!!.postUpload("Bearer " + SharedPreferenceManager(activity).userToken, requestFile).enqueue(object : Callback<List<UploadInfo>> {
                    override fun onResponse(call: Call<List<UploadInfo>>?, response: Response<List<UploadInfo>>?) {
                        Log.e("test", "test")
                    }

                    override fun onFailure(call: Call<List<UploadInfo>>?, t: Throwable?) {
                        Log.e("test", "test")
                    }
                })
            }
        }

        fun getRealPathFromURI(imageUri: Uri, activity: Activity): String {
            val path = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = activity.managedQuery(imageUri, path, null, null, null)
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(columnIndex)
        }
    }
}