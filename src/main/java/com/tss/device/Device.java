package com.tss.device;

import lombok.Data;

/**
 * 设备抽象基类
 * 所有硬件设备的基础类
 */
@Data
public abstract class Device {
    /**
     * 设备ID
     */
    protected String deviceId;

    /**
     * 设备名称
     */
    protected String deviceName;

    /**
     * 设备是否已初始化
     */
    protected boolean initialized;

    /**
     * 初始化设备
     * 
     * @return 初始化是否成功
     */
    public abstract boolean init();

    /**
     * 执行设备自检
     * 
     * @return 自检是否通过
     */
    public abstract boolean doSelfTest();

    /**
     * 重置设备到初始状态
     */
    public abstract void reset();
}