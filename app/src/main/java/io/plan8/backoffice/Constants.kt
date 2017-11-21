package io.plan8.backoffice

import io.plan8.backoffice.model.api.AuthInfo
import io.plan8.backoffice.model.api.Me
import io.plan8.backoffice.model.api.Team

/**
 * Created by chokwanghwan on 2017. 11. 5..
 */
class Constants {
    companion object {
        //task status
        val TASK_STATUS_BLUE:String = "taskStatusBlue"
        val TASK_STATUS_ORANGE:String = "taskStatusOrange"
        val TASK_STATUS_RED:String = "taskStatusRed"
        val TASK_STATUS_GREEN:String = "taskStatusGreen"
        val TASK_STATUS_GRAY:String = "taskStatusGray"

        //Change Image code
        val SELECT_IMAGE_CODE = 2000
        val PICK_IMAGE_CODE = 2001
        val SELECT_FILE_CODE = 2002

        var me: Me? = null
        var team: List<Team>? = null
    }
}
