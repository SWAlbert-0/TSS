package com.tss.device.input;

import com.tss.device.InputDevice;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * 目的地键盘
 * 用于输入行程目的地的代码
 */
@Component
public class DestinationKeyboard extends InputDevice {
    /**
     * 当前输入的目的地代码
     */
    @Getter
    private String currentInput = "";

    /**
     * 目的地代码最大长度
     */
    private final int MAX_CODE_LENGTH = 3;

    @Override
    public boolean init() {
        this.deviceId = "DEST_KB_001";
        this.deviceName = "目的地键盘";
        this.initialized = true;
        return true;
    }

    @Override
    public boolean doSelfTest() {
        // 模拟键盘自检
        return this.initialized;
    }

    @Override
    public void reset() {
        this.currentInput = "";
    }

    @Override
    public String readInput() {
        return currentInput;
    }

    /**
     * 处理按键输入
     * 
     * @param key 输入的按键字符
     * @return 当前输入的完整代码
     */
    public String inputKey(char key) {
        if (currentInput.length() < MAX_CODE_LENGTH) {
            currentInput += key;
        }
        return currentInput;
    }

    /**
     * 清除当前输入
     */
    public void clearInput() {
        currentInput = "";
    }
}