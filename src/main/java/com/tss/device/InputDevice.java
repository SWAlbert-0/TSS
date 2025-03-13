package com.tss.device;

/**
 * 输入设备抽象类
 * 所有输入类设备的基础类
 */
public abstract class InputDevice extends Device {
    /**
     * 读取输入
     * 
     * @return 读取到的输入内容
     */
    public abstract String readInput();
}