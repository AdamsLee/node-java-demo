let java = require("java"); //引入nodejs的java模块
java.classpath.push("./target/demo-0.0.1-SNAPSHOT-with-dependence.jar"); //导入编写的jar包
let JedisTool = java.import('com.example.demo.JedisTool'); //package.class
let jedisTool = java.newInstance("JedisTool");
jedisTool.initJedis();  //方法调用
setTimeout(function(){ jedisTool.usePipeline(1); }, 2000);