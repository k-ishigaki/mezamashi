package io.github.k_ishigaki.mezamashi

import platform.UIKit.UIDevice

actual fun platformName(): String {
    return UIDevice.currentDevice.systemName + " " + UIDevice.currentDevice.systemVersion
}