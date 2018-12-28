package com.renrun.basedevelopjetpack.http.interceptor;

import com.orhanobut.logger.Logger;
import okhttp3.*;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by vice on 2017/9/27 16:58
 * E-mail:vicedev1001@gmail.com
 */

public class VHttpLoggingInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    public enum Level {
        /**
         * No logs.
         */
        NONE,
        /**
         * Logs request and response lines.
         * <p>
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1 (3-byte body)
         *
         * <-- 200 OK (22ms, 6-byte body)
         * }</pre>
         */
        BASIC,
        /**
         * Logs request and response lines and their respective headers.
         * <p>
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
         * }</pre>
         */
        HEADERS,
        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         * <p>
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         *
         * Hi?
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         *
         * Hello!
         * <-- END HTTP
         * }</pre>
         */
        BODY,

        /**
         * 只打印url地址和json
         */
        RR,
    }

    public VHttpLoggingInterceptor(Level level) {
        this.level = level;
    }

    private Level level = Level.RR;


    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Logger.e(message);
        }
    });


    public void setLevel(Level level) {
        this.level = level;
    }

    @Override

    public Response intercept(Chain chain) throws IOException {

        if (level == Level.RR) {
            StringBuffer sbOutputText = new StringBuffer();
            Request request = chain.request();
            RequestBody requestBody = request.body();
            Buffer buffer = new Buffer();
            if (requestBody != null) {
                requestBody.writeTo(buffer);
            }
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = null;
            if (requestBody != null) {
                contentType = requestBody.contentType();
            }
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            if (isPlaintext(buffer)) {

                if (contentType != null && contentType.toString().contains("multipart")) {
                    sbOutputText.append(request.method() + "-->" + request.url());
                } else {
                    if (request.url().toString().contains("?")) {
                        sbOutputText.append(request.method() + "-->" + request.url() + "&" + buffer.readString(charset));
                    } else {
                        sbOutputText.append(request.method() + "-->" + request.url() + "?" + buffer.readString(charset));
                    }
                }
            } else {
                if (requestBody != null) {
                    sbOutputText.append("--> END " + request.method() + " (binary "
                            + requestBody.contentLength() + "-byte body omitted)");
                } else {
                    sbOutputText.append("--> END " + request.method() + " (binary "
                            + "requestBody为空" + "-byte body omitted)");
                }
            }
            Response response;
            try {
                response = chain.proceed(request);
            } catch (Exception e) {
                Logger.e("<-- HTTP FAILED: " + e);
                throw e;
            }
            ResponseBody responseBody = response.body();
            long contentLength = 0;
            if (responseBody != null) {
                contentLength = responseBody.contentLength();
            }
            sbOutputText.append("\n<-- "
                    + response.code()
                    + (response.message().isEmpty() ? "" : ' ' + response.message()));

            if (!HttpHeaders.hasBody(response)) {
                Logger.e("<-- END HTTP");
            } else if (bodyEncoded(response.headers())) {
                Logger.e("<-- END HTTP (encoded body omitted)");
            } else {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer bufferbody = source.buffer();

                Charset charsetbody = UTF8;
                MediaType contentTypebody = responseBody.contentType();
                if (contentTypebody != null) {
                    charset = contentTypebody.charset(UTF8);
                }

                if (!isPlaintext(bufferbody)) {
                    return response;
                }

                if (contentLength != 0) {
                    final String json = bufferbody.clone().readString(charsetbody);
                    sbOutputText.append("\n" + json);
                }
            }

            Logger.e("vvv-->" + sbOutputText.toString());

            return response;

        } else {
            if (level == Level.NONE) {
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            } else if (level == Level.BASIC) {
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            } else if (level == Level.HEADERS) {
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            } else if (level == Level.BODY) {
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            }
            return httpLoggingInterceptor.intercept(chain);
        }
    }

    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}
