package x2;

import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;


/**
 * Date：2025/7/28
 * Describe:
 */
public class MWebChromeClient extends WebChromeClient {
    @Override
    public void onProgressChanged(WebView webView, int i10) {
        super.onProgressChanged(webView, i10);
        // todo del
        Log.e("LOG-->", "onProgressChanged: " + i10);
        if (i10 == 100) {
            x2.f.a.c1(i10);
        }
    }
}
