# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-optimizationpasses 5  # 指定代码的压缩级别
-dontusemixedcaseclassnames  # 是否使用大小写混合
-dontpreverify  # 混淆时是否做预校验
-verbose  # 混淆时是否记录日志
# 混淆时所采用的算法
-optimizations !code/simpliflcation/arithmetic,!field/*,!class/merging/*
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keepclasseswithmembernames class * { native <methods>; }  # 保持 native 方法不被混淆
-keepclasseswithmembers class * { public <init>(android.content.Context, android.util.AttributeSet); }  # 保持自定义控件类不被混淆
-keepclasseswithmembers class * { public <init>(android.content.Context, android.util.AttributeSet, int); }  # 保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity { public void * (android.view.View); }
-keepclassmembers enum * {  # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parceable { public static final android.os.Parcelable$Creator *; }  # 保持 Parcelable 不被混淆