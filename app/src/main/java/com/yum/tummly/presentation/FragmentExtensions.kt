package com.yum.tummly.presentation

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

fun Fragment.scrollToTop(view: RecyclerView){
    view.scrollToPosition(0)
}