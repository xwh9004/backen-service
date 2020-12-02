package com.example.order.aspect;

import com.example.order.annotatiion.DataSourceTarget;
import com.example.order.db.DynamicRoutingDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 10:09 on 2020/12/2
 * @version V0.1
 * @classNmae OrderServiceAspect
 */
@Aspect
@Component
public class DataSourceRouter {

    @Autowired
    private DynamicRoutingDataSource sourceRouter;

    /**
     * 基于方法拦截
     * @param joinPoint
     * @return
     * @throws Throwable
     */
//    @Around("execution( * com.example.order.service.impl.OrderServiceImpl.*(..))")
//    public Object dataSourceRouter(ProceedingJoinPoint joinPoint) throws Throwable {
//       String name = joinPoint.getSignature().getName();
//        try{
//            if(name.startsWith("save")){
//                sourceRouter.setRoutKey("primaryDataSource");
//            }
//            if(name.startsWith("query")){
//                sourceRouter.setRoutKey("replicationDataSource");
//            }
//            return joinPoint.proceed();
//        }finally {
//            sourceRouter.clearRoutKey();
//        }
//    }

    @Around("@annotation(com.example.order.annotatiion.DataSourceTarget)")
    public Object dataSourceRouter(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        DataSourceTarget annotation = methodSignature.getMethod().getAnnotation(DataSourceTarget.class);

        try{
            String dataSourceName = annotation.name();
            if(StringUtils.hasText(dataSourceName)){
                sourceRouter.setRoutKey(dataSourceName);
            }else{
                sourceRouter.setRoutKey("primaryDataSource");
            }

            return joinPoint.proceed();
        }finally {
            sourceRouter.clearRoutKey();
        }
    }
}
