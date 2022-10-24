package oit.is.z0481.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class JankenAuthConfiguration {

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserBuilder users = User.builder();

    UserDetails user1 = users
        .username("user1")
        .password("$2y$10$Kr10Mk3pOqwJtmkUW6f3CO1RP4qGPSkg5QX1LAAKI8Y9XgjZBN8Ma")
        .roles("USER")
        .build();
    UserDetails user2 = users
        .username("user2")
        .password("$2y$10$W92SS3EkOU65FuybJpBk4u6IQf2GA6ybZnaSP2FT6xnQMYlovgZ3a")
        .roles("USER")
        .build();
    UserDetails user3 = users
        .username("ほんだ")
        .password("$2y$10$bAbD73sIHaR2BNKXzxFjj.2H8ew8tgsFmvu0QE9RbH2vCPhdjLJYO")
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user1, user2, user3);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // Spring Securityのフォームを利用してログインを行う（自前でログインフォームを用意することも可能）
    http.formLogin();

    // mvcMatchers().authenticated()がmvcMatchersに指定されたアクセス先に認証処理が必要であることを示す
    // authenticated()の代わりにpermitAll()と書くと認証不要となる
    http.authorizeHttpRequests()
        .mvcMatchers("/janken/**").authenticated();

    http.logout().logoutSuccessUrl("/");

    http.csrf().disable();
    http.headers().frameOptions().disable();
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
