# Spring Security
## 简介
    Spring Security是专门针对基于Spring的项目的安全框架，充分利用了依赖注入和AOP来实现安全的功能。
    安全框架有两个重要的概念，即认证（Authentication）和授权（Authorization）。认证即确认用户可以访问当前系统；
    授权即确定用户在当前系统下所拥有的功能权限。
## 配置
   1. 根据项目架构添加相应注解支持  
        Spring项目在配置类上添加@EnableWebSecurity注解，并扩展WebSecurityConfigurerAdapter类，不推荐实现WebSecurityConfigurer。  
        Spring MVC项目在配置类上添加@EnableWebMvcSecurity注解，并扩展WebSecurityConfigurerAdapter类。  
        Spring Boot项目中直接在配置类中扩展WebSecurityConfigurerAdapter类即可。

2. 重写WebSecurityConfigurerAdapter的三个configure方法来配置Web安全

### 用户认证，通过重写configure(AuthenticationManagerBuilder auth)方法来实现定制
1. 使用内存中的用户（个人觉得测试时很好用，或者某些项目只需要一个用户时，使用随机生成密码会大大提高安全性。）  
    使用AuthenticationManagerBuilder的inMemoryAuthentication方法即可添加在内存中的用户，并可给用户指定角色权限。
    
        auth.inMemoryAuthentication()
            .withUser("username1").password("password1").roles("ROLE_ADMIN")
            .and
            .withUser("username2").password("password2").roles("ROLE_USER");
            
2. JDBC中的用户  
    JDBC中的用户需要指定dataSource
    
        auth.jdbcAuthentication().dataSource(dataSource);
    这种写法需要数据库中的表结构与Spring Security一致，详见org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl中
    DEF_USERS_BY_USERNAME_QUERY等定义。  
    推荐使用自定义查询用户和权限的SQL语句
    
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select username,password from myusers where username = ?")
            .authoritiesByUsernameQuery("select username,role from roles where username = ?");
            
3. 通用用户   
    详见Spring Boot
    
### 用户授权，通过重写configure(HttpSecurity http)方法实现请求拦截
   Spring Security使用以下匹配器来匹配请求路径：
       
       antMatchers: 使用Ant风格的路径匹配
       regexMatchers: 使用正则表达式匹配路径
       anyRequest: 匹配所有请求路径
   在匹配了请求路径后，需要根据当前的用户信息对请求路径做安全处理，Spring Security提供了以下几种方式：
   
    access(String): Spring EL表达式结果为true时可访问
    anonymous(): 匿名可访问
    denyAll(): 用户不能访问
    fullyAuthenticated(): 用户完全认证可访问（非 remember me 下自动登录）
    hasAnyAuthority(String...): 用户拥有参数中任意一个权限可访问
    hasAnyRole(String...): 用户拥有参数中任意一个角色可访问
    hasAuthority(String): 用户拥有某个权限可访问
    hasRole(String...): 用户拥有某个角色可访问
    hasIpAddress(String): 如果用户来自参数中的IP可访问
    permitAll(): 用户可任意访问
    rememberMe(): 允许通过remmeber me 登录的用户访问
    authenticated(): 用户登录后可访问

    示例：
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests() //1
            .antMatchers("/admin/**").hasRole("ROLE_ADMIN") //2
            .antMatchers("/user/**").hasAnyRole("ROLE_ADMIN", "ROLE_USER") //3
            .anyRequest().authenticated(); //4
    }
    1. 通过authorizeRequests方法来开始请求权限配置，定义哪些URL需要被保护；
    2. 请求匹配/admin/**，只有拥有ROLE_ADMIN角色的用户可以访问；
    3. 请求匹配/user/**，拥有ROLE_ADMIN或ROLE_USER角色的用户都可以访问；
    4. 其余所有请求都需要认证后（登录后）才可以访问。
    
### 定制登录行为
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/index") 
            .failureUrl("/login?error")
            .permitAll()
            .and()
            .rememberMe()
                .tokenValiditySeconds(1209600)
                .key("mykey")
            .and()
            .logout()
                .logoutUrl("/custom-logout")
                .logoutSuccessUrl("/logout-success")
                .permitAll();
    }
    permitAll()，用户可任意访问，即在此前定义的页面可以随意访问。

## Spring Boot中使用Spring Security
   Spring Boot针对Spring Security的自动配置在org.springframework.boot.autoconfigure.security包中。  
   主要通过SecurityAutoConfiguration和SecurityProperties来完成配置。  
   *`SecurityAutoConfiguration导入了SpringBootWebSecurityConfiguration中的配置，我们将获得如下自动配置：`*
   1. 自动配置一个内存中的用户，账号为user，密码在程序启动时出现；
   2. 忽略/css/**、/js/**、/images/**和/**/favicon.ico等静态文件的拦截；
   3. 自动配置的securityFilterChainRegistration的Bean。
   当需要自己扩展配置时，只需要配置继承WebSecurityConfigurerAdapter类即可。
   
   配置用户和角色
   1. 用户实体类实现UserDetails接口，Spring Security会使用该类进行控制；
   2. 重写getAuthorities方法，将用户角色与权限绑定；
   3. 使用自定义CustomUserService，实现UserDetailsService接口，重写loadUserByUsername获得用户信息，用于Spring Security使用；
   4. 扩展WebSecurityConfigurerAdapter类，注册CustomUserService的Bean；
   5. 通过AuthenticationManagerBuilder添加自定义的CustomUserService的Bean；
   6. 通过HttpSecurity定制用户的行为（Ex. 所有用户需要认证、登录或注销等行为）；
   7. 前端显示控制通过具体使用框架实现。