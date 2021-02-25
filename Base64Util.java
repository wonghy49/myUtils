package com.richstonedt.transport.portal.cs.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.*;

/**
 * <b><code>Base64Util</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 21-2-3 下午3:35.
 *
 * @author huanghuayan
 * @since nile-cmgdpd-transport-portal-be
 */
public class Base64Util {

    /**
     * 图片转为Base64字节码
     * @param path 图片路径
     * @return 返回base64字节码
     */
    public static String imageToBase64(String path) {
        byte[] data = null;
        try {
            InputStream in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Base64 base64 = new Base64();
        return base64.encodeToString(data);
    }

    /**
     * 图片流转为Base64字节码
     * @param in 图片流
     * @return 返回base64字节码
     */
    public static String imageStreamToBase64(InputStream in) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            //设置接收附件最大10MB
            byte [] fileByte = new byte[10*1024*1024];
            int len =0;

            while((len = in.read(fileByte))!=-1) {
                data.write(fileByte,0,len);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Base64 base64 = new Base64();
        return base64.encodeToString(data.toByteArray());
    }

    /**
     * Base64字节码转图片
     * @param base64Str 字节码存储路径
     * @param path 文件存储路径
     * @return 返回true或者false
     */
    public static boolean base64ToImage(String base64Str, String path) {
        if (base64Str == null){
            return false;
        }
        Base64 base64 = new Base64();
        try {
            byte[] bytes = base64.decodeBase64(base64Str);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            File img = new File(path);
            if (!img.getParentFile().exists()) {
                img.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(path);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
