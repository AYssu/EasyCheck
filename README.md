# Easy Check- 个人版网络验证平台
> 声明: 编写该项目的初衷是因为需要一份毕设，介于现在的网络验证划分的产业和需求越来越丰富，该平台面向的目标用户为开发者，无论您是软件开发者，网页开发者，亦或为其他大大小小的项目开发者，均需要一份属于该项目的后端项目，涉及到用户登录注册签到，或提供软件使用权，不限于，激活码，卡密，激活证等，于是打算编写一款由**Vue3**前端框架和**SpringBoot3**后端框架的开源网络验证系统，项目只提供源码以及搭建教程的分享，请勿用于商用和其他非法用途，用户产生的一切责任和作者无关，若使用该源码，则视为同意该协议。
>
> 更正须知,各种原因 该项目不能作为毕业设计了，时间截至 所以该项目成为正式本人开发项目，并作为`hotfix` 的二代产品,加入各种验证机制和功能，来满足各位开发者的需求





使用到的项目包:

前端:

1. 路由管理: [`Vue Router`](https://router.vuejs.org/zh/installation.html)
2. 状态管理: [`Pinin`](https://pinia.vuejs.org/zh/)
3. 状态持久化插件: [`pinia-plugin-persistedstate`](https://www.npmjs.com/package/pinia-plugin-persistedstate)
4. 图形化组件库: [`element-plus`](https://element-plus.org/zh-CN/guide/installation.html)
5. CSS辅助工具: [`Sass`](https://www.sass.hk/)





后端：

使用到的部分依赖

```java
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-validation'

    implementation 'org.springframework.boot:spring-boot-starter-mail'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.apache.logging.log4j:log4j-api:2.6.2'
    implementation 'com.baomidou:mybatis-plus-spring-boot3-starter:3.5.5'
    implementation 'com.auth0:java-jwt:4.4.0'
    implementation 'org.springframework.boot:spring-boot-starter-data-redis:3.1.3'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'com.mysql:mysql-connector-j'

    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    testImplementation 'junit:junit:4.13.1'
}
```





