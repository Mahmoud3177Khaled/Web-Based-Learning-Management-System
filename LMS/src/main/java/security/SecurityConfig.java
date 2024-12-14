package security; // Declares the package name for this class

import org.springframework.context.annotation.Bean; // Imports the Bean annotation for defining beans
import org.springframework.context.annotation.Configuration; // Imports the Configuration annotation for configuration classes
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // Imports HttpSecurity for configuring web security
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; // Imports EnableWebSecurity annotation to enable security
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer; // Imports AbstractHttpConfigurer for configuring HTTP security
import org.springframework.security.core.userdetails.User; // Imports User class for user details
import org.springframework.security.core.userdetails.UserDetails; // Imports UserDetails interface for user details
import org.springframework.security.core.userdetails.UserDetailsService; // Imports UserDetailsService interface for user details service
import org.springframework.security.provisioning.InMemoryUserDetailsManager; // Imports InMemoryUserDetailsManager for in-memory user management
import org.springframework.security.web.SecurityFilterChain; // Imports SecurityFilterChain for security filter configuration

import static org.springframework.security.config.Customizer.withDefaults; // Imports withDefaults method for default configurations

@Configuration // Marks this class as a configuration class for Spring
@EnableWebSecurity // Enables web security for the application
public class SecurityConfig { // Declares the SecurityConfig class

    @Bean // Marks this method as a bean to be managed by Spring
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // Configures the security filter chain
        http // Begins configuring the HttpSecurity object
                .csrf(AbstractHttpConfigurer::disable) // Disables CSRF protection
                .authorizeHttpRequests(authz -> authz // Configures authorization for HTTP requests
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Requires ADMIN role for requests matching /api/admin/**
                        .requestMatchers("/api/instructor/**").hasRole("INSTRUCTOR") // Requires INSTRUCTOR role for requests matching /api/instructor/**
                        .requestMatchers("/api/student/**").hasRole("STUDENT") // Requires STUDENT role for requests matching /api/student/**
                        .anyRequest().authenticated() // Requires authentication for any other request
                )
                .formLogin(withDefaults()); // Enables form-based login with default settings
        return http.build(); // Builds and returns the configured SecurityFilterChain
    }

    @Bean // Marks this method as a bean to be managed by Spring
    public UserDetailsService userDetailsService() { // Configures an in-memory user details service
        UserDetails admin = User.withDefaultPasswordEncoder() // Creates a user with ADMIN role
                .username("admin") // Sets the username for the admin user
                .password("adminpass") // Sets the password for the admin user
                .roles("ADMIN") // Assigns the ADMIN role to the user
                .build(); // Builds the UserDetails object for the admin user
        UserDetails instructor = User.withDefaultPasswordEncoder() // Creates a user with INSTRUCTOR role
                .username("instructor") // Sets the username for the instructor user
                .password("instructorpass") // Sets the password for the instructor user
                .roles("INSTRUCTOR") // Assigns the INSTRUCTOR role to the user
                .build(); // Builds the UserDetails object for the instructor user
        UserDetails student = User.withDefaultPasswordEncoder() // Creates a user with STUDENT role
                .username("student") // Sets the username for the student user
                .password("studentpass") // Sets the password for the student user
                .roles("STUDENT") // Assigns the STUDENT role to the user
                .build(); // Builds the UserDetails object for the student user
        return new InMemoryUserDetailsManager(admin, instructor, student); // Returns an in-memory user details manager with the created users
    }
}