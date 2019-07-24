###spring-web ��������
[��������](https://my.oschina.net/klausprince/blog/1791357)
	
###spring aop ����
####����ע��
aop֧�ֺ�ע��֧��������

```
   <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>4.3.1.RELEASE</version>
    </dependency>
    <!-- aop aspectע�⵼��-->
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjrt</artifactId>
      <version>1.8.6</version>
    </dependency>
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.8.9</version>
    </dependency
```
ע��˵����ע��֧���Ƿ���Ҫ�����������Ϊ�ù��ܿ��أ�����

```
@Aspect �������࣬��һ��������һ������
@Order	�������࣬����int�����ڶ������ʱ������ָ����������ȼ�����ֵԽ�����ȼ�Խ��
@Pointcut �����ڷ���������execution���ʽ���������������  �����õķ���һ�㲻��Ҫ����ʵ��

����ע�ⶼ����Я��������ֵΪ"declearJoinPointExpression()"�𣿣���

@Before ǰ��֪ͨ Ŀ�귽��֮ǰִ�С������η���Я��JoinPoint���Ͳ���
@After ����֪ͨ��Ŀ�귽��ִ�к󷵻�ǰִ�С������η���Я��JoinPoint���Ͳ���
@AfterReturning ����֪ͨ��Ŀ�귽����������ʱִ�С������η���Я��JoinPoint���Ͳ�����Object���Ͳ������Ŀ�귽������ֵ��
@AfterThrowing Ŀ�귽���쳣ʱִ�С������η���Я��JoinPoint���Ͳ�����Exception���Ͳ������Ŀ�귽���׳����쳣��
@Around ����֪ͨ������ǰ��  ���� ���� �쳣 �������εķ�������Я������ProceedingJoinPoint���͵Ĳ����������з���ֵ������Ŀ�귽���ķ���ֵ��
```
���ӣ�

```
package com.example.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect    //�ñ�ǩ��LoggerAspect������Ϊһ������
@Order(1)  //������������ȼ�������ж�����棬��ͨ���������ȼ����������ִ��˳����ֵԽС�����ȼ�Խ�ߣ�
@Component //�ñ�ǩ��LoggerAspect��ŵ�IOC������
public class LoggerAspect {

    /**
     * ����һ�����������������������ʽ��������һ�㲻��Ҫ�����������
     * ʹ��@Pointcut�����������ʽ
     * �����ֱ֪ͨ��ʹ�÷����������õ�ǰ���е���ʽ�������������ʹ�ã����ϰ�������
     */
    @Pointcut("execution(public * com.example.controller.*Controller.*(..))")
    public void declearJoinPointExpression(){}

    /**
     * ǰ��֪ͨ
     * @param joinPoint
     */
    @Before("declearJoinPointExpression()") //�ñ�ǩ�����η�����һ��ǰ��֪ͨ����Ŀ�귽����ʼ֮ǰִ��
    public void beforMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("this method "+methodName+" begin. param<"+ args+">");
    }
    /**
     * ����֪ͨ�����۷����Ƿ����쳣����ִ��,���Է��ʲ��������ķ���ֵ��
     * @param joinPoint
     */
    @After("declearJoinPointExpression()")
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("this method "+methodName+" end.");
    }
    /**
     * ����֪ͨ���ڷ�����������ִ�еĴ��룩
     * ����֪ͨ���Է��ʵ������ķ���ֵ��
     * @param joinPoint
     */
    @AfterReturning(value="declearJoinPointExpression()",returning="result")
    public void afterReturnMethod(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("this method "+methodName+" end.result<"+result+">");
    }
    /**
     * �쳣֪ͨ�����������쳣ִ�еĴ��룩
     * ���Է��ʵ��쳣�����ҿ���ָ���ڳ����ض��쳣ʱִ�еĴ���
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value="declearJoinPointExpression()",throwing="ex")
    public void afterThrowingMethod(JoinPoint joinPoint,Exception ex){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("this method "+methodName+" end.ex message<"+ex+">");
    }
    /**
     * ����֪ͨ(��ҪЯ������ΪProceedingJoinPoint���͵Ĳ���)
     * ����֪ͨ����ǰ�á����á����ء��쳣֪ͨ��ProceedingJoinPoin ���͵Ĳ������Ծ����Ƿ�ִ��Ŀ�귽��
     * �һ���֪ͨ�����з���ֵ������ֵ��Ŀ�귽���ķ���ֵ
     * @param point
     */
    @Around(value="declearJoinPointExpression()")
    public Object aroundMethod(ProceedingJoinPoint point){

        Object result = null;
        String methodName = point.getSignature().getName();
        try {
            //ǰ��֪ͨ
            System.out.println("The method "+ methodName+" start. param<"+ Arrays.asList(point.getArgs())+">");
            //ִ��Ŀ�귽��
            result = point.proceed();
            //����֪ͨ
            System.out.println("The method "+ methodName+" end. result<"+ result+">");
        } catch (Throwable e) {
            //�쳣֪ͨ
            System.out.println("this method "+methodName+" end.ex message<"+e+">");
            throw new RuntimeException(e);
        }
        //����֪ͨ
        System.out.println("The method "+ methodName+" end.");
        return result;
    }
}
```
####����XML
��ǩ˵����

```
<aop:config> : ��������ĸ���ǩ������proxy-target-class="true"  ����ǿ��Ҫ��ʹ��CGLIB���д��� falseΪjdk����
<aop:pointcut>: ��������㣬����id��������ʶ��execution���ʽ���������������������ӵ�
<aop:aspect>:�������Ĺ������ã�����order���������ȼ���ref������ʵ�ʵ�ʵ����bean
<aop:before>:���þ����ǰ��֪ͨ������method���������ж�Ӧ�ķ�������pointcut-ref:��Ҫ�����������
<aop:after>��ͬ��
<aop:after-returning>��ͬ��
<aop:after-throwing>��ͬ��
<aop:around>��ͬ��
```

[<aop:aspect>��<aop:advisor>������](https://www.jianshu.com/p/40f79da0cdef)


ʾ����

```
<!-- ���������Bean -->
    <bean id="sysAspect" class="com.example.aop.SysAspect"/>
    <!-- ����AOP -->
    <aop:config>
        <!-- �����е���ʽ  -->
        <aop:pointcut id="pointcut" expression="execution(public * com.example.controller.*Controller.*(..))"/>
        <!-- �������漰���� -->
        <aop:aspect order="3" ref="sysAspect">
            <!-- ǰ��֪ͨ -->
            <aop:before method="beforMethod"  pointcut-ref="pointcut" />
            <!-- ����֪ͨ -->
            <aop:after method="afterMethod"  pointcut-ref="pointcut"/>
            <!-- ����֪ͨ -->
            <aop:after-returning method="afterReturnMethod" pointcut-ref="pointcut" returning="result"/>
            <!-- �쳣֪ͨ -->
            <aop:after-throwing method="afterThrowingMethod" pointcut-ref="pointcut" throwing="ex"/>
            <aop:around method="aroundMethod" pointcut-ref="pointcut"/>
        </aop:aspect>
    </aop:config>
```
