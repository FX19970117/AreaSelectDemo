package com.qiangyu.test.areaselectdemo.bean;

import android.content.Context;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Gordo on 2016/12/15.
 */

public class FileUtils {

    public static String readAssets(Context context, String fileName) {
        String content = "";
        InputStream is = null;

        try {
            is = context.getAssets().open(fileName);
            if (is != null) {
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int readLine = is.read(buffer);
                    if (readLine == -1) break;
                    byteArrayOutputStream.write(buffer, 0, readLine);
                }
                is.close();
                byteArrayOutputStream.close();
                content = new String(byteArrayOutputStream.toByteArray());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return content;
    }

}
