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
INSERT INTO `tilitili_job`(`title`, `name`, `cron`, `status`, `create_time`, `update_time`) 
VALUES ('测试Job', 'testJob', '* * * * * ? ', 1, now(), now());

INSERT INTO `tilitili_user`(`user_name`, `password`, `status`, `create_time`, `update_time`) VALUES 
('admin', 'pass', 0, now(), now());
```

## 使用方式：
1. 添加依赖
```
<dependency>
    <groupId>club.tilitili</groupId>
    <artifactId>tilitili-schedule</artifactId>
    <version>1.5.8</version>
</dependency>
```
2. 找一个mysql数据库，新建库，执行项目里的几个sql文件
3. 把以下数据库配置加到spring配置文件里
```
schedule.datasource.driverClassName=com.mysql.cj.jdbc.Driver
schedule.datasource.jdbcUrl=you mysql connect url
schedule.datasource.username=you username
schedule.datasource.password=you password
schedule.core-pool-size = 2
```
4. 在启动类中添加 @EnableTilitiliJob 注解
5. 运行，打开
http://localhost:{port}
