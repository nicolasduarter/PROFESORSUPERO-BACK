package eci.edu.dows.profesorSuperO.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcSecurityConfig implements WebMvcConfigurer {
    private final RoleBasedAccessInterceptor roleBasedAccessInterceptor;

    @Autowired
    public WebMvcSecurityConfig(RoleBasedAccessInterceptor roleBasedAccessInterceptor) {
        this.roleBasedAccessInterceptor = roleBasedAccessInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roleBasedAccessInterceptor)
                .addPathPatterns("/administracion/**", "/decanatura/**");
    }
}
