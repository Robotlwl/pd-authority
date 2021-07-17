package com.pd.auth.test;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.base.Captcha;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
public class CaptchaTest {
    @Test
    public void testCapt() throws FileNotFoundException {
        // 算术类型图片验证码
        // 指定图片的宽度和高度
        Captcha captcha = new ArithmeticCaptcha(115, 42);
        captcha.setCharType(2);
        captcha.out(new FileOutputStream(new File("./hello.png")));
        String text = captcha.text();
        System.out.println(text);
    }
}
