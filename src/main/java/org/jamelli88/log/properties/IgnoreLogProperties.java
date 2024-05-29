package org.jamelli88.log.properties;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import org.jamelli88.log.constants.LogConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.AntPathMatcher;

import java.util.List;

/**
 * 忽略日志 配置类
 * <p>
 * 做接口权限时，考虑修改成读取配置文件
 *
 * @author Jamel.Li
 * @date 2019/01/03
 */
@Data
@ConfigurationProperties(prefix = IgnoreLogProperties.PREFIX)
public class IgnoreLogProperties {
    public static final String PREFIX = "ignore.log";
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    private List<String> urls = CollUtil.newArrayList(LogConstants.IGNORE_LOG_URLS);

    /**
     * 判断该路径是否需要忽略
     *        
     * @param path
     * @return {@link boolean}
     * @author jamel.li
     * @create 2024/5/29 11:25
     */
    public boolean isIgnore(String path) {
        return this.urls.stream().anyMatch((url) -> path.startsWith(url) || ANT_PATH_MATCHER.match(url, path));
    }

    /**
     * 判断该路径是否需要记录log
     *        
     * @param path
     * @return {@link boolean}
     * @author jamel.li
     * @create 2024/5/29 11:25
     */
    public boolean isNeed(String path) {
        return this.urls.stream().noneMatch((url) -> path.startsWith(url) || ANT_PATH_MATCHER.match(url, path));
    }

    /**
     * 初始化url信息
     *        
     * @param urls
     * @return 
     * @author jamel.li
     * @create 2024/5/29 11:44
     */
    public void setUrls(List<String> urls) {
        urls.addAll(LogConstants.IGNORE_LOG_URLS);
        this.urls = urls;
    }
}
