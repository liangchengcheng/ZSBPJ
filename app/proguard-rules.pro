# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Administrator\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:

-dontwarn com.github.bumptech.glide.**{*;}
-dontwarn io.reactivex.**{*;}
-dontwarn com.squareup.retrofit.**{*;}
-dontwarn com.jakewharton.**{*;}
-dontwarn butterknife.internal.**

-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}
-dontwarn butterknife.Views$InjectViewProcessor

-dontwarn com.squareup.**
-dontwarn okio.**
-dontwarn org.codehaus
-dontwarn java.nio
-dontwarn retrofit2.Platform$Java8

-keep public class org.codehaus.**
-keep public class java.nio.**
-dontwarn sun.misc.Unsafe

#sharesdk混淆注意
-keep class android.net.http.SslError
-keep class android.webkit.**{*;}
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class m.framework.**{*;}
#Gson混淆配置不混淆Serializable
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.lcc.entity.**{*;}
-keep class com.lcc.db.**{*;}
-keepnames class * implements java.io.Serializable
#eventbus
-dontwarn de.greenrobot.event.**
-keep class de.greenrobot.event.** { *; }
-keepclassmembers class ** {
    public void onEvent*(**);
    void onEvent*(**);
}


-keep class com.google.**{*;}
-keep class com.baidu.**{*;}
-keep class vi.com.**{*;}
-keep class com.fasterxml.**{*;}
-keep class cn.jpush.**{*;}
-keep class com.tencent.**{*;}
-keep class com.pingplusplus.**{*;}
-keep class org.springframework.**{*;}
-keep class com.tendcloud.**{*;}
-keep class com.nostra13.**{*;}
-keep class com.unionpay.**{*;}
-keep class org.**{*;}
-keep class android.support.**{*;}
#圆形的控件
-keep class de.hdodenhof.**{*;}
-keep class com.balysv.**{*;}
-keep class com.squareup.okhttp.**{*;}
-keep class de.greenrobot.**{*;}
-keep class com.ufreedom.uikit.**{*;}
-keep class com.github.frank-zhu.**{*;}
-keep class cn.finalteam.**{*;}
-keep class com.github.clans.**{*;}
-keep class com.daimajia.easing.**{*;}
-keep class com.daimajia.androidanimations.**{*;}
-keep class com.lovedise.**{*;}

-keep class com.github.bumptech.glide.**{*;}
-keep class io.reactivex.**{*;}
-keep class com.squareup.retrofit.**{*;}
-keep class com.jakewharton.**{*;}
-keep class butterknife.internal.**

#-keep class **{*;}
-keepattributes Exceptions,Signature,*Annotation*,EnclosingMethod