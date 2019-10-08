##MySQL
###Notes
һ������Ǩ��ʱ����Ż�����

```
alter tab1 disable keys  ;  --����tab1����

insert into tab1 select * from tab2 ;  --��tab1Ǩ������

alter tab1 enable keys; -- ����tab1����
```

executable comments

����ı�����ֵ����ǻᱻִ�еġ�

ΪʲôҪ������д��ע�������أ� ��Ϊ�����ű���Щʱ����ܻ��õ��������ݿ���ȥִ�С�

40101����ָ��MySQL 4.1.1��4.01.01�������ϰ汾��ִ�С�

```
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
```

innodb��myisam
--
[��ϸ����](https://www.cnblogs.com/y-rong/p/8110596.html)

�ؼ�����ʾ
myisam:

```
Ĭ������   �ʺ�ִ�д���select  û������  ��֧�����    ���������в���ֱ������  
�ѱ������ļ���������ͷ ��frm��ű�ṹ��MYD������ݣ�MYI���������  
��̬��  ��̬��  ѹ����  
�����ֶα���Ϊ����  ����������Բ��ڵ�һ��   ���������������������    ��
����������¼��countЧ�ʸ�   ֧��FULLTEXT��ȫ��������������  
```
innodb:

```
�ʺϲ���  insert  update  delete ֧������  ֧�����  ֧��������ģ����sql���˻�Ϊ������
������֯��   �����ռ�   ���ռ�洢  frm�ļ��洢��ṹ   ���������ݴ洢��һ��  idb�ļ�   
�����ֶα���������   �������ʱ���������ڵ�һ��
���û�д���������Ψһ�ǿ�����  ���Զ�����6�ֽڵ��������û����ɼ���
����¼������  count���ĺܴ�  ��֧��FULLTEXT��ȫ������������ʹ��sphinx���֧�֣�������
```

innodb����

```
������뼶��δ�ύ��(Read uncommitted)�����ύ��(Read committed)�����ظ���(Repeatable read)�������л�(Serializable)


Innodb������ģʽ�����¼��֣�������������������������(����)������������(����)����϶����

ע�⣺�����û��ʹ��������innodb����ȷ���������У����ʱ���ʹ�õ���������Ҳ���Ǳ���
```

������ע�����

```
1�������� show create table tablename �������������͡�

2���Բ�֧������ı���start/commit����û���κ�Ч������ִ��commitǰ�Ѿ��ύ��

3������ִ�������������л���������������ݲ��ᶪʧ����innodb���myisam�����ȫ��alter table tablename type=innodb;����ʹ�� alter table tablename engine = innodb;

4��Ĭ��innodb�ǿ����Զ��ύ�ģ�����㰴��myisam��ʹ�÷�������д����ҳ������ڴ���ֻ�����ܻ�ܵ͡�

����ڱ�д����ʱ��������ݿ����ܣ�

	a���������������һ�������У������ύ���������ύ���µ����ݿ⿪����

	b����һ�����������������������������Ժ�������滹����Ҫ�����sql��䣬�����������߶���sql���֮�����Ӧ�����ٵĽ����߼�����ʹ�����������ʱ�䡣

	c��������������

	d��sql��������where�Ӿ�һ��Ҫʹ�����������������ȡ������������(???)

	(???)f����������Լ������ݿ⻷������־ϵͳ��ֱ���룬���޸ĵģ���������ʹ�û�����淽ʽ��ZION_LOG_DB�վ�ʹ��myisam�洢���棬ֻ��ZION_GAME_DB��ZION_LOGIN_DB��DAUM_BILLINGʹ��Innodb���档
```

dual

```
dual ��oracle��Ϊ������ select����from�����Ľṹ���õĹؼ��֣���mysql��sqlserver��֧��select 1 ������from���﷨��ע�⣺select * from dual��mysql�б�����oracle�᷵��һ��Ĭ�ϵ�����
```

```
using BTREE : ʹ���������ϣ�ָ�����������ݽṹ��ͨ������ΪBTREE����HASH��  BTREE��MYISAM��InnoDBΨһ֧�ֵ����ݽṹ��MEMORY��HEAP�洢�������֧��HASH��BTREE����

SET SQL_SAFE_UPDATES = 0 ���ر����ݿⰲȫģʽ
```

binlog

```
binlog�� �������ļ������������ļ�����־�ļ��������ļ����ڼ�¼��Щ��־�ļ����ڱ�ʹ�ã���־�ļ���¼���ݿ��DDL��DML��
binlog������innodb���У�����һ�����¼���¼��־��
binlog�����ݻָ������ݱ��ݣ�����Ƿ��й�����Ϊ
binlog��	binlog������ʽ ����row��ʽ��׼ȷ��¼ÿ�б仯�����ݣ�
		ʹ��mysqlbinlog -vv(��ʽΪrowʱ��Ҫ)�鿴binlog��
		ɾ��binlog��reset master��ɾ��������־����PURGE { BINARY | MASTER } LOGS { TO 'log_name' | BEFORE datetime_expr }ɾ��������������־��������־���ڲ�����expire_logs_days=N��
		����������

log_bin = {on | off | base_name}
ָ���Ƿ����ü�¼��������־����ָ��һ����־·��
sql_log_bin ={ on | off }
ָ���Ƿ����ü�¼��������־
expire_logs_days
ָ���Զ�ɾ����������־��ʱ�䣬����־����ʱ��
log_bin_index
ָ��mysql-bin.index�ļ���·��
binlog_format = { mixed | row | statement }
ָ����������־����ʲôģʽ��¼
max_binlog_size
ָ����������־�ļ����ֵ
binlog_cache_size
ָ��������־��������С
max_binlog_cache_size
ָ����������־��������С
sync_binlog = { 0 | n }
ָ��д������ٴΣ�ˢһ����

���ڽ���binlog��jar��mysql-binlog-connector-java����open-replicator
```
[binlog1](https://www.cnblogs.com/rjzheng/p/9721765.html)
[binlog2](https://www.cnblogs.com/rjzheng/p/9745551.html)





