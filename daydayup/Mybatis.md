##mybatis label

###key words

```
SqlSessionFactoryBuilder
SqlSessionFactory
```

���������ļ���mybatis-config.xml��

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">		У��ͷ
<configuration>											���ñ�ǩ
  <environments default="development">					����һ�黷��
    <environment id="development">						����һ������
      <transactionManager type="JDBC"/>					�����������
      <dataSource type="POOLED">						ָ������Դ���Ƿ������ӳ�
        <property name="driver" value="${driver}"/>
        <property name="url" value="${url}"/>
        <property name="username" value="${username}"/>
        <property name="password" value="${password}"/>
      </dataSource>
    </environment>
  </environments>
  <mappers>												mapperXml�ļ���·��
    <mapper resource="org/mybatis/example/BlogMapper.xml"/>
  </mappers>
</configuration>
```

Ĭ�����񼶱�

```
���ظ�������׼��Ӧ���Ǽ���ʵ�֣���ֹ�ö�

��mysqlʹ����mvcc��ʵ�֣�����ʵ���ڿ��ظ��������񼶱��¶�д����Ӱ�죨���������Ϊɶ�������棩

DDL����ṹ֮����޸Ĳ��������޸Ĳ�������סȫ�����º��������κ����󶼽���ȴ�״̬
```
