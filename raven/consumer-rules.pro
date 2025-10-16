-keepattributes !SourceFile
-dontshrink

-keep class com.ak.c{*;}

#appsflyer start
# keep init adpost
-keep class com.appsflyer.** { *; }
-keep class kotlin.jvm.internal.** { *; }
-keep public class com.android.installreferrer.** { *; }
#appsflyer end


-keep class com.bytedance.sdk.** { *; }

#加固
-keep class kotlin.**{*;}
-keep class kotlinx.**{*;}
-keep class androidx.**{*;}
-keep class android.**{*;}
-keep class com.facebook.**{*;}
-keep class com.tencent.mmkv.**{*;}
-keep class okhttp3.** {*;}
-keep class com.google.**{*;}
-keep class com.android.**{*;}