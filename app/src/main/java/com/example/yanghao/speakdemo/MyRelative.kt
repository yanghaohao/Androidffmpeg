package com.example.yanghao.speakdemo

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

class MyRelative : RelativeLayout {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }
}