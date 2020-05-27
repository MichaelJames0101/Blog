package cn.zlj.blog;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * 只有在构建war文件时才需要WebApplicationInitializer部署它。
 * WebApplicationInitializer可以看做是Web.xml的替代，它是一个接口。
 * 通过实现WebApplicationInitializer，在其中可以添加servlet，listener等，
 * 在加载Web项目的时候会加载这个接口实现类，从而起到web.xml相同的作用。
 * @author ZLJ
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * Configure the application. Normally all you would need to do is to add sources
	 * (e.g. config classes) because other settings have sensible defaults. You might
	 * choose (for instance) to add default command line arguments, or set an active
	 * Spring profile.
	 * 配置应用程序。通常，您需要做的就是添加源(例如配置类)，因为其他设置具有合理的默认值。
	 * 您可以选择(例如)添加默认的命令行参数，或者设置一个活动的Spring配置文件。
	 * @param builder a builder for the application context一个应用程序上下文的生成器
	 * @return the application builder应用程序生成器
	 * @see SpringApplicationBuilder
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		/**
		 * Add more sources (configuration classes and components) to this application.
		 * 向这个应用程序添加更多的源(配置类和组件)。
		 * @param sources the sources to add要添加的来源
		 * @return the current builder当前的构建器
		 */
		return application.sources(BlogApplication.class);
	}

}
