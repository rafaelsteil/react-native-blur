package com.shopify.reactnative.blur

import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.hoko.blur.HokoBlur


class BlurViewManager : ViewGroupManager<FrameLayout>() {
    override fun getName() = REACT_CLASS

    override fun createViewInstance(context: ThemedReactContext): FrameLayout {
        val decorView: View = context.currentActivity!!.window.decorView
        val rootView = decorView.findViewById<ViewGroup>(R.id.content)

        val blurredBitmap = HokoBlur.with(context)
            .mode(HokoBlur.MODE_STACK)
            .radius(DEFAULT_RADIUS)
            .sampleFactor(5f)
            .needUpscale(true)
            .processor()
            .blur(rootView)

        return FrameLayout(context).also {
            it.layoutParams = ViewGroup.LayoutParams(blurredBitmap.width, blurredBitmap.height)
            it.background = BitmapDrawable(context.resources, blurredBitmap)
        }
    }

    private companion object {
        const val REACT_CLASS = "BlurView"
        const val DEFAULT_RADIUS = 10
    }
}
