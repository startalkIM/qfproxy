package com.qunar.qfproxy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    public static InputStream bytesToInputStream(byte[] content) {
        if (content == null || content.length == 0) {
            LOGGER.error("content tranf to inputstreame fail ");
        }
        return new ByteArrayInputStream(content);
    }

    public static byte[] InputStreamToBytes(InputStream fileIs) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            if (fileIs == null) {
                LOGGER.error("inputstreame tranf to  bytes fail ");
            }
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = fileIs.read(buffer))) {
                out.write(buffer, 0, n);
            }
            return out.toByteArray();
        } catch (Exception e) {
            LOGGER.error("inputstreame tranf to  bytes fail", e);
            return null;
        } finally {
            if (fileIs != null) {
                fileIs.close();
            }
            if (out != null) {
                out.close();
            }
        }

    }
}
