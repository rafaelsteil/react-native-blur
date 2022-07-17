package com.shopify.reactnative.blur

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.ViewGroup
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.annotations.ReactProp
import com.hoko.blur.HokoBlur
import com.hoko.blur.util.BitmapUtil
import java.lang.ref.WeakReference

class HokoBlurViewManager : ViewGroupManager<HokoBlurView>() {
    override fun getName() = REACT_CLASS

    /**
     * This is called on view creation
     * */
    override fun createViewInstance(context: ThemedReactContext): HokoBlurView {
         val blurBuilder = HokoBlur.with(context)
             .mode(HokoBlur.MODE_STACK)
             .sampleFactor(1f)
             .forceCopy(true)
             .needUpscale(false)

        val decorView = context.currentActivity!!.window.decorView
        val rootView = decorView.findViewById<ViewGroup>(android.R.id.content)

        val viewBitmap: Bitmap = BitmapUtil.getViewBitmap(rootView, 0, 0, SAMPLE_FACTOR)
        return HokoBlurView(context, WeakReference(viewBitmap), blurBuilder)
    }

    @ReactProp(name = "radius", defaultInt = DEFAULT_RADIUS)
    fun setRadius(view: HokoBlurView, radius: Int) {
        view.blurBuilder.radius(radius)
    }

    /**
     * This gets on redraws (including the first)
     * */
    override fun onAfterUpdateTransaction(view: HokoBlurView) {
        val targetBitmap = view.targetBitmap.get()

        if (targetBitmap == null) {
            Log.e(LOG_TAG, "Target bitmap to blur is null, probably got reclaimed by gc")
            return
        }

        val blurredBitmap = view.blurBuilder
            .processor()
            .blur(targetBitmap)

        view.layoutParams = ViewGroup.LayoutParams(blurredBitmap.width, blurredBitmap.height)
        view.background = BitmapDrawable(view.context.resources, blurredBitmap)

        super.onAfterUpdateTransaction(view)
    }

    private companion object {
        const val REACT_CLASS = "HokoBlurView"
        const val LOG_TAG = "HokoBlurView"
        const val DEFAULT_RADIUS = 7
        const val SAMPLE_FACTOR = 5f
    }
}
