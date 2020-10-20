package com.review.demo.MybatisPlugin;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/***
 *
 * MyBatis默认支持对4大对象（Executor，StatementHandler，ParameterHandler，ResultSetHandler）
 * 上的方法执行拦截，具体支持的方法为：
 *
 * Executor ，主要用于sql重写。
 * ParameterHandler，用于参数处理。
 * ResultSetHandler ，用于结果集二次处理。
 * StatementHandler ，用于jdbc层的控制。
 *
 * 一般仅在Executor做插件比如SQL重写、结果集脱敏，
 * ResultSetHandler和StatementHandler仅在高级场景中使用，而且某些场景中非常有价值
 *
 * @Author ZH
 * @version 1.0.0
 * @Date 2020/10/19
 */
@Intercepts({
        //要监听过多个可以写多个@Signature，也可以多个方法实现多个拦截
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class SimpleMybatisPlugin implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(SimpleMybatisPlugin.class);

    private static int MAPPED_STATEMENT_INDEX = 0;// 这是对应上面的args的序号
    private static int PARAMETER_INDEX = 1;
    private static int ROWBOUNDS_INDEX = 2;
    private static int RESULT_HANDLER_INDEX = 3;


    /**
     * 这里是每次执行操作的时候，都会进行这个拦截器的方法内
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        //根据拦截的参数序号
        MappedStatement mappedStatement = (MappedStatement) args[MAPPED_STATEMENT_INDEX];
        Object parameter = args[PARAMETER_INDEX];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        // 获取到SQL ，进行调整
        String sql = boundSql.getSql();
        String name = mappedStatement.getId();
        logger.debug("拦截的方法名是:" + name + ",sql是" + sql + ",参数是" + JSON.toJSONString(parameter));
        //重新new一个查询语句对像
        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        // 把新的查询放到statement里
        /*MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }*/
        /*args[MAPPED_STATEMENT_INDEX] = newMs;*/

        //该方法之前全是对应业务代码
        Object result = invocation.proceed();
        return result;
    }

    /**
     * 主要是为了把这个拦截器生成一个代理放到拦截器链中
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(java.lang.Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 插件初始化的时候调用，也只调用一次，插件配置的属性从这里设置进来
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {

    }
}
