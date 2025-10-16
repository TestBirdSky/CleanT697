package b2

import android.app.Activity
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ak.impI.Core
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * Date：2025/7/16
 * Describe:
 */
class AdCenter {
    private val mPAH = PangleAdImpl()// 高价值
    private val mPangleAdImpl = PangleAdImpl("1") // 低价值
    var idH = ""
    var idL = ""
    fun loadAd() {
        mPAH.lAd(idH)
        mPangleAdImpl.lAd(idL)
    }

//    fun canPo(): Boolean {
//        if (mPAH.isReadyAd()) {
//            return true
//        }
//        if (mAdImpl.isReadyAd()) {
//            return true
//        }
//
//        return false
//    }

    private var job: Job? = null
    fun sa(ac: Activity) {
        AdE.sNumJump(0)
        if (ac is AppCompatActivity) {
            ac.onBackPressedDispatcher.addCallback {}
            job?.cancel()
            job = ac.lifecycleScope.launch {
                Core.pE("ad_done")
                delay(Random.nextLong(AdE.gDTime()))
                if (AdE.isLoadH) {
                    x2.f.a.c2(ac)
                }
                var isS = mPAH.shAd(ac)
                if (isS.not()) {
                    isS = mPangleAdImpl.shAd(ac)
                }
                if (isS.not()) {
                    ac.finishAndRemoveTask()
                }
            }
        }
    }
}