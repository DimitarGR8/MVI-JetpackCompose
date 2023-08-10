package com.example.mvi_jetpackcompose.custom.permissions

class CameraPermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems that you permanently declined camera permission. Please go to the app settings to grant it."
        } else "This app needs access to your camera."
    }
}

class LocationPermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems that you permanently declined location permission. Please go to the app settings to grant it."
        } else "This app needs access to your location."
    }
}

class GalleryPermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems that you permanently declined gallery permission. Please go to the app settings to grant it."
        } else "This app needs access to your gallery."
    }
}
