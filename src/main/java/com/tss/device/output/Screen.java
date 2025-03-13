package com.tss.device.output;

import com.tss.device.OutputDevice;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * 显示屏
 * 用于显示系统输出和用户提示信息
 */
@Component
public class Screen extends OutputDevice {
    /**
     * 当前显示的内容
     */
    @Getter
    private String currentDisplay = "";

    @Override
    public boolean init() {
        this.deviceId = "SCREEN_001";
        this.deviceName = "显示屏";
        this.initialized = true;
        return true;
    }

    @Override
    public boolean doSelfTest() {
        // 模拟显示屏自检
        return this.initialized;
    }

    @Override
    public void reset() {
        this.currentDisplay = "";
    }

    @Override
    public boolean output(String content) {
        this.currentDisplay = content;
        return true;
    }

    /**
     * 显示信息
     * 
     * @param message 要显示的信息
     */
    public void display(String message) {
        this.currentDisplay = message;
    }
}