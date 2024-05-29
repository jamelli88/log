package org.jamelli88.log.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 日志对象信息
 * <p>
 *
 * @author Jamel.Li
 * @date 2022-01-12 15:50
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogInfo {

    /**
     * 关联用户
     */
    @ApiModelProperty(value = "用户编号", position = 1)
    private Long userId;

    /**
     * 请求IP地址
     */
    @ApiModelProperty(value = "请求IP地址", position = 2)
    private String ip;

    /**
     * 请求URL
     */
    @ApiModelProperty(value = "请求URL", position = 3)
    private String url;

    /**
     * 请求类型
     */
    @ApiModelProperty(value = "请求类型", position = 4)
    private String requestType;

    /**
     * 请求客户端
     */
    @ApiModelProperty(value = "请求客户端", position = 5)
    private String browser;

    /**
     * 请求操作系统
     */
    @ApiModelProperty(value = "请求操作系统", position = 6)
    private String operatingSystem;

    /**
     * 请求入参JSON
     */
    @ApiModelProperty(value = "请求入参JSON", position = 7)
    private String inParams;

    /**
     * 请求出参JSON
     */
    @ApiModelProperty(value = "请求出参JSON", position = 8)
    private String outParams;

    /**
     * 消耗时间（单位：毫秒）
     */
    @ApiModelProperty(value = "消耗时间（单位：毫秒）", position = 9)
    private long time;
}