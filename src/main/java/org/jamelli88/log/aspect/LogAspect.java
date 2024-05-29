package org.jamelli88.log.aspect;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jamelli88.log.RequestLogger;
import org.jamelli88.log.constants.LogConstants;
import org.jamelli88.log.model.LogInfo;
import org.jamelli88.log.properties.IgnoreLogProperties;
import org.jamelli88.log.util.BaseContextHandler;
import org.jamelli88.log.util.IpUtil;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p>
 *  日志切面
 * <p>
 *
 * @author Jamel.Li
 * @date 2022-01-11 10:04
 */
@Aspect
@Component
public class LogAspect {

    @Resource
    private IgnoreLogProperties ignoreProperties;

    /**
     * 定义切点Pointcut
     *
     * @author Jamel.Li
     * @date 2021/12/25 10:05
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void requestPointcut() {

    }

    @Pointcut("@within(org.springframework.cloud.openfeign.FeignClient)")
    public void feignClientPointcut() {
    }

    @Around(value = "requestPointcut() && !feignClientPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        LogInfo info = new LogInfo();
        info.setUserId(BaseContextHandler.getUserId());
        setRequestInfo(info);
        boolean ignore = ignoreProperties.isIgnore(info.getUrl());
        if (ignore) {
            return joinPoint.proceed();
        }

        assert joinPoint.getSignature() instanceof MethodSignature;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        setInParams(info, signature, joinPoint.getArgs());
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        info.setTime(System.currentTimeMillis() - start);
        info.setOutParams(JSONUtil.toJsonStr(proceed));

        Method method = signature.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();

        MDC.put(LogConstants.MDC_LOGGER, declaringClass.getName());
        MDC.put(LogConstants.MDC_METHOD, method.getName());
        MDC.put(LogConstants.MDC_REQUEST_INFO, JSONUtil.toJsonStr(info));

        String desc = setDesc(method);
        RequestLogger log = new RequestLogger(Class.forName(declaringClass.getName()));
        log.info(desc);
        return proceed;
    }

    /**
     * 设置接口描述信息
     *        
     * @param method
     * @return {@link java.lang.String}
     * @author jamel.li
     * @create 2024/5/29 11:37
     */
    private String setDesc(Method method) {
        List<String> desc = new ArrayList<>();
        Api api = method.getDeclaringClass().getAnnotation(Api.class);
        if (api != null) {
            desc.add(ArrayUtil.join(api.tags(), ","));
        }
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        if (apiOperation != null) {
            desc.add(apiOperation.value());
        }
        return StrUtil.join("-", desc);
    }

    /**
     * 封装请求信息
     *        
     * @param info
     * @return 
     * @author jamel.li
     * @create 2024/5/29 11:27
     */
    private void setRequestInfo(LogInfo info) {
        if (RequestContextHolder.getRequestAttributes() == null) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 请求IP
        String ip = IpUtil.getIpAddr(request);
        info.setIp(ip);
        // 获得请求地址
        String url = URLUtil.getPath(request.getRequestURI());
        info.setUrl(url);
        // 获得请求类型
        info.setRequestType(request.getMethod());

        UserAgent userAgent = UserAgentUtil.parse(request.getHeader(HttpHeaders.USER_AGENT));
        // 获得浏览器版本
        info.setBrowser(userAgent.getBrowser() + " " + userAgent.getVersion());
        // 操作系统
        info.setOperatingSystem(userAgent.getOs().toString());
    }

    /**
     * 获得传入参数
     */
    private void setInParams(LogInfo info, MethodSignature signature, Object[] paramValues) {
        int capacity = paramValues.length;
        String[] paramNames = signature.getParameterNames();
        Map<String, Object> paramMap = new HashMap<>(capacity);
        if (ArrayUtil.isEmpty(paramNames)) {
            info.setInParams(null);
            return;
        }
        for (int i = 0; i < capacity; i++) {
            if (paramValues[i] instanceof Serializable) {
                paramMap.put(paramNames[i], paramValues[i]);
            }
        }
        info.setInParams(JSONUtil.toJsonStr(paramMap));
    }
}