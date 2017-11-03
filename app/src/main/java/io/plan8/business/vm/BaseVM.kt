package io.plan8.business.vm

import android.content.Context
import android.databinding.BaseObservable
import android.os.Bundle

/**
 * Created by SSozi on 2017. 11. 2..
 */
open class BaseVM : BaseObservable {
    protected var context: Context
    var savedInstanceState: Bundle? = null

    constructor(context: Context, savedInstanceState: Bundle?) {
        this.context = context
        this.savedInstanceState = savedInstanceState
    }

    constructor(context: Context) {
        this.context = context
    }
}