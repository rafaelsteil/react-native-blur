package com.shopify.reactnative.blur

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.widget.FrameLayout
import com.facebook.react.uimanager.ThemedReactContext
import com.hoko.blur.api.IBlurBuild
import java.lang.ref.WeakReference

@SuppressLint("ViewConstructor")
class HokoBlurView(
    context: ThemedReactContext,
    var targetBitmap: WeakReference<Bitmap>,
    val blurBuilder: IBlurBuild
) : FrameLayout(context)
