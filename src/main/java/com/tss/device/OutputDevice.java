package com.tss.device;

/**
 * 输出设备抽象类
 * 所有输出类设备的基础类
 */
public abstract class OutputDevice extends Device {
    /**
     * 输出信息
     * 
     * @param content 要输出的内容
     * @return 输出是否成功
     */
    public abstract boolean output(String content);
}