package io.plan8.backoffice.model.api

import com.google.gson.annotations.SerializedName
import io.plan8.backoffice.model.BaseModel

/**
 * Created by SSozi on 2017. 11. 27..
 */
class UploadInfo : BaseModel{
    @SerializedName("name") var name: String? = null
    @SerializedName("mimetype") var mimetype: String? = null
    @SerializedName("url") var url: String? = null
}