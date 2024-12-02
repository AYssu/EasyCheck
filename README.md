# Easy Verify 参考文档



> 声明: 编写该项目的初衷是因为需要一份毕设，原本是打算写个管理系统的，类似于可创建 `插件/APP/激活码` 的后台管理，但是后面因为本人比较懒，没有写完，想着写个简单的验证系统就`Ok`结果因为生活琐事没有时间了，现在正式接手成为我为首个开源的后端管理系统，该项目涉及到的 `功能和UI` 仅用于测试，请勿商用，请勿使用该项目进行违法行为，该项目是个人版的系统，所以不打算写管理端(说不定后面会写，谁知道呢...) 即搭建者就是用户，因为提供全部源码和搭建教程 数据全在自己服务器，当然我自己也会使用这个系统，我的技术有限，力所能及的去让项目更加便利和好用，初衷就是最简单的验证平台，既要保护用户验证的完整性和安全性，后面就打算作为`hotfix`的第二代，最初我只是自己在用的后台系统，包含远程变量什么的，当然 `EasyVerify` 只做增强功能依旧，并打算以一种很新的方式进行验证，保留旧的卡密验证，提供程序到用户的一些列功能，欢迎提供建议和问题。
>



# 快速开始 项目未完成 预留文档

1. 环境准备

   均为本人搭建环境，仅做参考。windows和Linux均可

   系统 `Ubuntu 22.04 64bit / Linux 5.15.0-124-generic`

   软件 `mysql`  `redis` `nginx` `java17及以上`

   运维面板 `宝塔面板`

   所需文件，推荐使用打包的源码，有其他需求请自行编译，编译教程后面会说

   `当前目录/vite/dist` 目录下为前端打包后的项目

   `当前目录/springboot/build/libs/easy_verify_[version].jar` 为后端jar包

   `当前目录/mysql/easy_verify.sql` 为后端数据库初始文件	

2. 配置前端项目

   宝塔创建 `web程序`  `easyverify_web` 并移动前端文件到 `/www/wwwroot/easyverify_web`

   向`nginx`配置添加如下

   ```nginx
   # 路由问题 用处自己百度
   location / {
       try_files $uri $uri/ @router;
       index  index.html index.htm;
       error_page 405 =200 $request_uri;
   }
   
   # 解决前端路由刷新403的问题 详情可以去百度
   location @router {
       rewrite ^.*$ /index.html last;
   }
   
   # 由于跨域需要 详情可以去百度
   location /api/ {
       # 后端服务器的地址，根据实际情况修改 这个写后端项目的地址 同一服务器使用localhost即可
       proxy_pass http://localhost:5200;
       # 去掉/api前缀的URL
       error_page 405 =200 $request_uri;
       rewrite ^/api/(.*)$ /$1 break;
   }
   ```

3. 配置后端项目

   宝塔创建`java` 项目 `easyverify_springboot` 移动 前端文件到 `/www/wwwroot/jar/easy_verify_x.x.x.jar`

   jar包路径指向上传的jar包即可，端口默认5200，如需更改自行了解jar包修改 `application.yml`的参数或直接修改配置文件，推荐使用自定义 `application-prod.yml`  作为生成环境的配置文件,具体操作后面会出，目前为止为预设文档。[未完善]



使用到的一些工具包和组件:

前端:

```js
"dependencies": {
    "@element-plus/icons-vue": "^2.3.1",
    "axios": "^1.7.7",
    "echarts": "^5.5.1",
    "element-plus": "^2.8.4",
    "less": "^4.2.0",
    "pinia": "^2.2.4",
    "pinia-plugin-persistedstate": "^4.1.1",
    "sass": "^1.79.4",
    "vue": "^3.4.37",
    "vue-echarts": "^7.0.3",
    "vue-router": "^4.4.5"
  },
```





后端：

使用到的部分依赖

```java
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-validation'

    implementation 'com.alibaba.fastjson2:fastjson2:2.0.53'
    implementation 'com.alibaba.fastjson2:fastjson2-extension:2.0.53'
    implementation 'com.alibaba.fastjson2:fastjson2-extension-spring6:2.0.53'


    implementation 'org.springframework.boot:spring-boot-starter-security:3.3.5'
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

