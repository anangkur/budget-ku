package com.anangkur.beritaku.base

import android.view.View
import android.widget.AdapterView

interface BaseSpinnerListener {
    fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
}