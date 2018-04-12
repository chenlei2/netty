package org.yyx.netty.study;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.netty.study.entity.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * <p>
 * create by 叶云轩 at 2018/4/12-下午6:50
 * contact by tdg_yyx@foxmail.com
 */
public class CodeCTest {
    /**
     * CodeCTest 日志控制器
     * Create by 叶云轩 at 2018/4/12 下午6:53
     * Concat at tdg_yyx@foxmail.com
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeCTest.class);

    @Test
    public void testCodeC() throws IOException {
        User user = new User();
        user.setUserName("yyx");
        user.setUserGender(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(user);
        objectOutputStream.flush();
        objectOutputStream.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        LOGGER.info("--- [JDK] {}", bytes.length);
        byteArrayOutputStream.close();
        LOGGER.info("--- [byte] {}", user.codeC().length);
    }

    @Test
    public void testCodec() throws Exception{
        User user = new User();
        user.setUserName("yyx");
        user.setUserGender(1);
        int loop = 1000000;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(user);
            os.flush();
            os.close();
            byte[] bytes = bos.toByteArray();
            bos.close();
        }
        long endTime = System.currentTimeMillis();
        LOGGER.info("--- [jdk耗时] {}", endTime - startTime);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            byte[] bytes = user.codeC(buffer);
        }
        endTime = System.currentTimeMillis();
        LOGGER.info("--- [byte耗时] {}", endTime - startTime);
    }

}
