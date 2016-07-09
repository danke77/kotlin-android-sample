# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/xuexiaozhe/Projects/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepclasseswithmembers,allowshrinking class * {
    native <methods>;
}

# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface android.support.annotation.Keep

# Do not strip any method/class that is annotated with @Keep
-keep @android.support.annotation.Keep class *
-keepclassmembers class * {
    @android.support.annotation.Keep *;
}

#android
-dontwarn android.content.res.*
-keep class android.support.v4.** {*; }
-keep interface android.support.v4.** {*; }
-dontwarn android.support.v4.**
-keep class android.support.v7.** {*; }
-keep interface android.support.v7.** {*; }
-dontwarn android.support.v7.**
-keep class android.support.v13.** {*; }
-keep interface android.support.v13.** {*; }
-dontwarn android.support.v13.**
-keep class android.support.design.**{*; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }
-dontwarn android.support.design.**

#Kotlin
-dontwarn kotlin.**

#过滤泛型，防止强制类型转换异常
-keepattributes Signature

#Annotation
-keepattributes *Annotation*
-dontwarn org.springframework.**

# Gson & Json
-keep class com.google.gson.** {*; }
-keep class org.json.** {*; }
