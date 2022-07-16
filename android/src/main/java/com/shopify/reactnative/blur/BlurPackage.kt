package com.shopify.reactnative.blur

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext

@Suppress("unused")
class BlurPackage : ReactPackage {
    override fun createNativeModules(reactContext: ReactApplicationContext) = mutableListOf<NativeModule>()

    override fun createViewManagers(reactContext: ReactApplicationContext) = listOf(BlurViewManager())
}
