package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValveInfoDTO {
    /**
     * 云平台订阅类型
     */
    private String notifyType;
    /**
     * 云平台ID
     */
    private String deviceId;
    /**
     * 云平台服务id
     */
    private String serviceId;
    /**
     * 服务类型为
     */
    private String serviceType;
    /**
     * 进水温度
     */
    private String waterInletTemperature;
    /**
     * 回水温度
     */
    private String returnWaterTemperature;
    /**
     * 累计热量
     */
    private String totalQuantityHeat;
    /**
     * 阀门开度
     */
    private String valveOpening;
    /**
     * 阀门状态 0 关 1 开
     */
    private String valveStatus;

}
