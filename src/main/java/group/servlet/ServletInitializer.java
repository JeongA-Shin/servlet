package group.servlet;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// 강의에서 딱히 언급안 됨. 그냥 프로젝트 처음에 생성 될 때 부터 있는 파일이었음
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServletApplication.class);
	}

}
