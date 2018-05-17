Spring Boot 定制和优化内嵌的Tomcat
	1、默认的配置文件的方式进行配置 application.properties，配置如下：
		server.port=8090
		server.contextPath=/user
		server.tomcat.accesslog.enabled=true
		server.tomcat.accesslog.directory=e:/tomcatlogs/
		server.tomcat.accesslog.suffix=.txt
	2、编程的方式进行配置
		2.1、编写一个类实现EmbeddedServletContainerCustomizer接口，并将该类纳入到spring容器中进行管理，
			具体参见类：springboot010.MyEmbeddedServletContainerCustomizer.class 做法
		
		2.2、自己创建一个TomcatEmbeddedServletContainerFactory.class的bean，并被spring容器管理
			具体参参见类：springboot010.MyTomcatEmbeddedServletContainerFactoryConfiguration.class 的做法
	3、之所以可以这么做是因为 org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration.class
		这个类的存在会默认的将我们的servlet容器进行一些初始化设置