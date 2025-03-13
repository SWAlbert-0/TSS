package com.tss.device.input;

import com.tss.device.InputDevice;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * 继续/取消键盘
 * 用于控制购票流程的继续或取消
 */
@Component
public class ActionKeyboard extends InputDevice {
    /**
     * 用户选择的动作
     */
    @Getter
    private String selectedAction;

    /**
     * 继续操作的标识
     */
    public static final String CONTINUE = "CONTINUE";

    /**
     * 取消操作的标识
     */
    public static final String CANCEL = "CANCEL";

    @Override
    public boolean init() {
        this.deviceId = "ACTION_KB_001";
        this.deviceName = "继续/取消键盘";
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
        this.selectedAction = null;
    }

    @Override
    public String readInput() {
        return selectedAction;
    }

    /**
     * 选择继续操作
     */
    public void pressContinue() {
        this.selectedAction = CONTINUE;
    }

    /**
     * 选择取消操作
     */
    public void pressCancel() {
        this.selectedAction = CANCEL;
    }
}