package com.xinbo.app.appbaselibrary.interceptor;



import com.xinbo.app.appbaselibrary.utils.JsonUtils;
import com.xinbo.app.appbaselibrary.utils.LoggerUtils;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author LC
 * @date 2019/4/10 0010
 * @Describe
 */
public class LoggerInterceptor implements HttpLoggingInterceptor.Logger {
    private StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(String message) {
        // 请求或者响应开始
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0);
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {
            message = JsonUtils.formatJson(JsonUtils.decodeUnicode(message));
        }
        mMessage.append(message.concat("\n"));
        // 响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            LoggerUtils.e(mMessage.toString());
        }
    }
}
