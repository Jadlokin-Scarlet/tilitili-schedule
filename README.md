# Tilitili job开源框架

一个带简单的管理页面的Job框架，

## 一个简单的示例
```
@Component
public class TestJob {
    @Job(name = "testJob")
    public void testJob() {
        System.out.println("hello");
    }
}
```
```
INSERT INTO `tilitili_job`(`name`, `cron`, `status`, `create_time`, `update_time`) 
VALUES ('testJob', '* * * * * ? ', 1, '2021-11-11 15:48:01', '2021-11-11 15:48:01');
```

## 使用方式：
1. 添加依赖
```
<dependency>
    <groupId>club.tilitili</groupId>
    <artifactId>tilitili-schedule</artifactId>
    <version>1.2</version>
</dependency>
```
2. 找一个mysql数据库，执行项目里的几个sql文件
3. 把以下数据库配置加到spring配置文件里
```
schedule.datasource.driverClassName=com.mysql.cj.jdbc.Driver
schedule.datasource.jdbcUrl=you mysql connect url
schedule.datasource.username=you username
schedule.datasource.password=you password
```
4. 运行，打开
http://localhost:8080
