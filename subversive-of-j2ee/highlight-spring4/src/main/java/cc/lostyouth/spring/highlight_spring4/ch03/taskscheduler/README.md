# Spring 计划任务
1. 通过在配置类注解@EnableScheduling来开启计划任务的支持（类似添加@EnableAsync开启多线程支持）；
2. 在需要执行计划任务的方法上添加注解@Scheduled，声明这是一个计划任务(类似添加@Async声明异步任务)。
3. **`计划任务必须是无参的方法，返回类型一般为void，任何有返回值的方法会被忽略。Scheduled注释 The annotated method must expect no arguments. It will typically have a void return type; if not, the returned value will be ignored when called through the scheduler.`**

## @Scheduled任务类型（三个类型必须指定其中一个）
### fixedRate
每隔固定时间运行任务，单位毫秒。 Ex. fixedRate = 5000 每隔5秒运行一次。

### fixedDelay
在上一次执行任务完成后的XXX时间开始下一次执行，单位毫秒。

### cron 参考[Spring 定时任务之 @Scheduled cron表达式](http://rainbowdesert.iteye.com/blog/2107220)
在指定时间执行任务，使用表达式来指定时间。 Ex. (0 15 10 ? * *)每天上午10点15执行
* cron表达式有至少6个（也可能7个）有空格分隔的时间元素(秒 分钟 小时 天 月 天？ 掉价)
    * 秒（0~59）
    * 分钟（0~59）
    * 小时（0~23）
    * 天（月）（0~31，但是你需要考虑你月的天数）
    * 月（0~11）
    * 天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
    * 7.年份（1970－2099）

**`由于"月份中的日期"和"星期中的日期"这两个元素互斥的,必须要对其中一个设置?.`**

### 可选类型
#### initialDelay
第一次执行任务前的延时时间