package netzme;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
		implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(MyConfig.class);
		ctx.setServletContext(container);

		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}

	/*
	 * Tambahkan kelas initializer yang mengimplementasikan
	 * WebApplicationInitializer di bawah src / main / java dengan paket yang
	 * ditentukan seperti yang ditunjukkan di bawah ini (yang dalam hal ini
	 * bertindak sebagai pengganti dari setiap konfigurasi pegas yang didefinisikan
	 * dalam web.xml). Selama startup Servlet 3.0 Container, kelas ini akan dimuat
	 * dan dipakai dan metode onStartup akan dipanggil oleh servlet container.
	 * LEVEL: IMPORTANT
	 */

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { MyConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	/*
	 * Konten di atas menyerupai konten web.xml dari tutorial sebelumnya karena kami
	 * menggunakan front-controller DispatherServler, menetapkan pemetaan
	 * (url-pattern dalam xml) dan alih-alih menyediakan path ke file konfigurasi
	 * pegas (spring-servlet.xml) , di sini kita mendaftarkan Kelas Konfigurasi.
	 * Secara keseluruhan, kami melakukan hal yang sama, hanya saja pendekatannya
	 * berbeda.
	 */
}
