package io.plan8.backoffice.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by chokwanghwan on 2017. 11. 9..
 */
open class BaseFragment:Fragment() {
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d("lifeCycle", "Fragment onAttach :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifeCycle", "Fragment onCreate :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("lifeCycle", "Fragment onCreateView :: " + javaClass.simpleName + "  ::  " + hashCode())
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("lifeCycle", "Fragment onActivityCreated :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifeCycle", "Fragment onStart :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifeCycle", "Fragment onResume :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifeCycle", "Fragment onPause :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifeCycle", "Fragment onStop :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("lifeCycle", "Fragment onDestroyView :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifeCycle", "Fragment onDestroy :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("lifeCycle", "Fragment onDetach :: " + javaClass.simpleName + "  ::  " + hashCode())
    }
}