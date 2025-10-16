package x2;


import android.os.Handler;
import android.os.Message;


/**
 * Date：2025/7/28
 * Describe:
 */
// todo
public class MHandler extends Handler {
    @Override
    public void handleMessage(Message message) {
        x2.f.a.c1(message.what);
    }
}
