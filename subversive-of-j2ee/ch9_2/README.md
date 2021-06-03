# Spring Batch
以下摘抄自[一篇文章全面解析大数据批处理框架Spring Batch](http://geek.csdn.net/news/detail/128641)
## 基本概念
Spring Batch是用来处理大量数据操作的一个框架，主要用来读取结构化数据，然后进行一定的处理后输出成指定的形式。
通过Spring Batch框架可以构建出轻量级的健壮的并行处理应用，支持事务、并发、流程、监控、纵向和横向扩展，提供统一的接口管理和任务管理。
## 应用场景
批处理作业两个典型特征是批量执行与自动执行（需要无人值守）：
1. 前者能够处理大批量数据的导入、导出和业务逻辑计算；
2. 后者无需人工干预，能够自动化执行批量任务。

除关注其基本功能之外，还需要关注如下的几点：
1. 健壮性：不会因为无效数据或错误数据导致程序崩溃；
2. 可靠性：通过跟踪、监控、日志及相关的处理策略（重试、跳过、重启）实现批作业的可靠执行；
3. 扩展性：通过并发或者并行技术实现应用的纵向和横向扩展，满足海量数据处理的性能需求。
## Spring Batch框架结构
一个典型的批处理作业分为3部分：作业读、作业处理、作业写，也是典型的三步式架构。整个批处理框架基本上围绕Read、Process、Writer来处理。
除此之外，框架提供了作业调度器、作业仓库（用以存放Job的元数据信息，支持内存、DB两种模式）。
### Job Launcher 基础组件，用来启动Job。 
    Job Launcher（作业调度器）是Spring Batch框架基础设施层提供的运行Job的能力。通过给定的Job名称和Job Parameters，可以通过Job Launcher执行Job。
    通过Job Launcher可以在Java程序中调用批处理任务，也可以在通过命令行或者其它框架（如定时调度框架Quartz）中调用批处理任务。
### Job Repository 基础组件，用来持久化Job的元数据，默认使用内存。
    Job Repository（作业仓库）来存储Job执行期的元数据，并提供两种默认实现。
    元数据：
        这里的元数据是指Job Instance、Job Execution、Job Parameters、Step Execution、Execution Context等数据。
    两种默认实现：
        一种是存放在内存中；另一种将元数据存放在数据库中。通过将元数据存放在数据库中，可以随时监控批处理Job的执行状态。
        Job执行结果是成功还是失败，并且使得在Job失败的情况下重新启动Job成为可能。
        Step表示作业中的一个完整步骤，一个Job可以有一个或者多个Step组成。  
#### Job Instance
    Job Instance（作业实例）是一个运行期的概念，Job每执行一次都会涉及到一个Job Instance。
    Job Instance来源可能有两种：一种是根据设置的Job Parameters从Job Repository（作业仓库）中获取一个；
    如果根据Job Parameters从Job Repository没有获取Job Instance，则新创建一个新的Job Instance。
#### Job Execution
    Job Execution表示Job执行的句柄，一次Job的执行可能成功也可能失败。只有Job执行成功后，对应的Job Instance才会被完成。
    因此在Job执行失败的情况下，会有一个Job Instance对应多个Job Execution的场景发生。
#### Job
    应用组件，是Batch操作的基础执行单元。
#### Step
    Job的一个阶段，Job由一组Step组成。
    一个Job中的不同Step可以顺序执行，也可以按照不同的条件有选择的执行，通过next元素或者decision元素来定义跳转规则。
    为了提高多个Step的执行效率，框架提供了Step并行执行的能力（使用Split进行声明，通常情况下需要Step之间没有任何的依赖关系）。
    每个Step由ItemReader、ItemProcessor、ItemWriter组成，同时提供了大量的ItemReader、ItemWriter的实现，提供了对FlatFile、XML、Json、Database、Message等多种数据类型的支持。
    框架还为Step提供了重启、事务、重启次数、并发数；以及提交间隔、异常跳过、重试、完成策略等能力。
#### Tasklet
    Step的一个事务过程，包含重复执行、同步、异步等策略。
#### Item
    从数据源读出或写入的一条数据记录
#### Chunk
    给定数据的Item的集合，支持事务管理：
    通过commit-interval设置read多少记录进行一次提交；
    支持对每个Tasklet设置细粒度的事务配置：隔离界别、传播行为、超时；
    支持rollback和no rollback，通过skippable-exception-classes和no-rollback-exception-classes进行支撑；
    支持JMS Queue的事务级别配置。
#### Item Reader
    从给定的数据源读取Item集合
#### Item Processor
    在Item写入数据源之前进行数据清洗（转换校验过滤...）
#### Item Writer
    把Chunk中包含的Item写入数据源
## Spring Batch业务表
Spring Batch使用六张业务表存储了所有的元数据信息（包括Job、Step的实例，上下文，执行器信息，为后续的监控、重启、重试、状态恢复等提供了可能）。
    
    BATCH_JOB_INSTANCE：作业实例表，用于存放Job的实例信息
    BATCH_JOB_EXECUTION_PARAMS：作业参数表，用于存放每个Job执行时候的参数信息，该参数实际对应Job实例的。
    BATCH_JOB_EXECUTION：作业执行器表，用于存放当前作业的执行信息，比如创建时间，执行开始时间，执行结束时间，执行的那个Job实例，执行状态等。
    BATCH_JOB_EXECUTION_CONTEXT：作业执行上下文表，用于存放作业执行器上下文的信息。
    BATCH_STEP_EXECUTION：作业步执行器表，用于存放每个Step执行器的信息，比如作业步开始执行时间，执行完成时间，执行状态，读写次数，跳过次数等信息。
    BATCH_STEP_EXECUTION_CONTEXT：作业步执行上下文表，用于存放每个作业步上下文的信息。