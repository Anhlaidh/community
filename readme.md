### git && github
- git commit --amend --no-edit 给上一次 操作追加  

- [BootStrap]( https://v3.bootcss.com/components/#navbar)
- [gitapp](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
[打开Github网站反应慢的问题](https://blog.csdn.net/xinshui151/article/details/79200390)

## Idea
- ctrl+shift+v:替换变量名
- ctrl+alt+v:抽取并赋值
- ctrl+p:查看参数
- ctrl+alt+o删除多余包
- ctrl+F12 查看当前类中的包
 a
## h2
[设置用户名密码](https://blog.csdn.net/tripleDemo/article/details/98888281)

## flyway
[集成h2抽空再学](https://www.jianshu.com/p/4f4f314bc163)

```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
集成成功了！！！！  
驱动版本降低

## bootStrap 
- 栅格系统 

## mybatis
- 默认不认驼峰，可配置
`` 
mybatis.configuration.map-underscore-to-camel-case=true
``
识别驼峰

## MybatisGenerator
1. 插件，依赖
2. 配置xml（windows路径用\）     
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

3. 配置application.property（配置springboot的读取路径）
4. 主入口加@MapperScan(basePackages = "com.namesapce.community.mapper")
5. `` java
User user = userMapper.selectByPrimaryKey(question.getCreator());  
QuestionDTO questionDTO = new QuestionDTO();
 ``