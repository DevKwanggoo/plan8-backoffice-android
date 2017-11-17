package io.plan8.backoffice.vm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import io.plan8.backoffice.activity.EditTaskActivity

/**
 * Created by SSozi on 2017. 11. 16..
 */
class EditTaskActivityVM(var editTaskActivity: EditTaskActivity, savedInstanceState: Bundle?) : ActivityVM(editTaskActivity, savedInstanceState) {
    fun finishEditTask(view: View){
        editTaskActivity.onBackPressed()
    }

    fun completeEditTask(view: View){
        Toast.makeText(editTaskActivity, "완료", Toast.LENGTH_SHORT).show()
    }
}