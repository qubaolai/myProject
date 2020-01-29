package com.qubaolai.common.intercepter;

import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * @Description qubaolai
 * @Date 2020/1/11 13:59
 * @Description 监控sql语句执行时间
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
                        ResultHandler.class, CacheKey.class, BoundSql.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
                        ResultHandler.class})
        }
)
@Slf4j
public class TimeIntercepter implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            Object[] args = invocation.getArgs();
            MappedStatement ms = (MappedStatement) args[0];
            Object parameter = args[1];
            Object p;
            //这里的参数这样判断是因为加了@Param的和没有加的好像有点区别，调试的时候看到的
            if (parameter instanceof Map) {
                Map map = (Map) parameter;
                HashSet<Object> set = new HashSet<>();
                set.addAll(map.values());
                p = set;
            } else {
                p = parameter;
            }
            Object result;
            long start = System.currentTimeMillis();
            result = invocation.proceed();
            long end = System.currentTimeMillis();
            long cost = end - start;
            log.info("[TimerInterceptor] execute [{}] cost [{}] ms, parameter:{}", ms.getId(), cost, p);
            if (cost > 2000) {//这里的时间根据情况设定，也可以写在配置文件里面
                log.warn("Sql语句执行时间超过2秒钟，请检查优化,方法:{},耗时:{}ms,参数:{}", ms.getId(), cost, p);
            }
            return result;
        } catch (Throwable r) {
            log.error(r.getMessage(), r);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}