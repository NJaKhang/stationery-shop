package com.group.sshop.config;

import com.cksource.ckfinder.servlet.CKFinderServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.WebApplicationInitializer;

import java.io.IOException;
import java.nio.file.Files;

public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Register the CKFinder's servlet.
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("ckfinder", new CKFinderServlet());
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/ckfinder/*");
        dispatcher.setInitParameter("scan-path", "com.group.sshop.config");

        FilterRegistration.Dynamic filter = servletContext.addFilter("x-content-options", new Filter() {
            @Override
            public void init(FilterConfig filterConfig) {
            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                ((HttpServletResponse) response).setHeader("X-Content-Type-Options", "nosniff");
                chain.doFilter(request, response);
            }

            @Override
            public void destroy() {
            }
        });

        filter.addMappingForUrlPatterns(null, false, "/content/*");

        String tempDirectory;

        try {
            tempDirectory = Files.createTempDirectory("ckfinder").toString();
        } catch (IOException e) {
            tempDirectory = null;
        }

        dispatcher.setMultipartConfig(new MultipartConfigElement(tempDirectory));
    }
}
