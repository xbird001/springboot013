package springboot010;

import java.io.File;
import java.util.Arrays;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.valves.AccessLogValve;
import org.apache.catalina.valves.Constants;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

/*@Component*/
public class MyEmbeddedServletContainerCustomizer implements EmbeddedServletContainerCustomizer {

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		TomcatEmbeddedServletContainerFactory factory = (TomcatEmbeddedServletContainerFactory)container;
		//设置端口号
		factory.setPort(9090);
		//设置项目上下文路径
		factory.setContextPath("/user");
		//设置服务器的基本目录
		factory.setBaseDirectory(new File("e:/tomcat/directory"));
		factory.setContextValves(Arrays.asList(getAccessLogValve()));
		
		//设置tomcat对应的连接器
		factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {

			@Override
			public void customize(Connector connector) {
				Http11NioProtocol protocal = (Http11NioProtocol)connector.getProtocolHandler();
				//设置最大连接数
				protocal.setMaxConnections(2000);
				//设置最大线程数
				protocal.setMaxThreads(500);
			}
			
		});
	}
	
	private AccessLogValve getAccessLogValve() {
		AccessLogValve av = new AccessLogValve();
		av.setEnabled(true);
		av.setDirectory("e:/logs");
		av.setSuffix(".txt");
		av.setPrefix("spring-boot-container");
		av.setPattern(Constants.AccessLog.COMMON_ALIAS);
		return av;
	}
	
}
