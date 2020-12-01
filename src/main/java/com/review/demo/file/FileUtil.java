package com.review.demo.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/***
 * 实现文件拷贝
 *
 * @Author ZH
 * @version 1.0.0
 * @Date 2020/12/1
 */
public class FileUtil {

    private FileUtil() {
    }

    public static void copyFile(String target, String source) throws IOException {
        try (InputStream ins = new FileInputStream(source)) {
            try (OutputStream ops = new FileOutputStream(target)) {
                byte[] bufferRead = new byte[4096];
                while (ins.read(bufferRead) != -1) {
                    ops.write(bufferRead);
                }

            }

        }

    }


    public static void copyFileNio(String target, String source) throws IOException {
        try (FileInputStream ins = new FileInputStream(source)) {
            try (FileOutputStream ops = new FileOutputStream(target)) {
                FileChannel channel = ins.getChannel();
                FileChannel outChannel = ops.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(4096);
                while (channel.read(buffer) != -1) {
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }
            }

        }

    }
}
