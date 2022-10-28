package br.com.futurodev.primeiraapi.security;


import br.com.futurodev.primeiraapi.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //Quais requests serão autorizadas e como será a autorização
                .authorizeRequests()
                /*Quais Urls eu vou filtrar? É pra permitir ou bloquear?
                O antMatchers tem 3 sobrecargas
                 1 - Passando só a url,
                 2 - Passando só p método (Get, Post...etc)
                 3 - Passando método + url
                 Nesse caso liberei o /usuarios o /usuarios/ com barra no final é barrado
                */
                .antMatchers(HttpMethod.GET, "/usuarios")
                //Permito acesso a todos
                .permitAll()
                //.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
                /*
                 * Quando for realizado um delete para o endpoint '/usuarios/*'
                 * será verificado no token qual a Claim (ROLE) que ele possui
                 * e só será permitida execução da requisição se a role for, nesse
                 * caso, o de admin
                 * */
                //.antMatchers(HttpMethod.DELETE, "/usuarios/*").hasRole("ADMIN")
                .antMatchers("/usuarios/**").hasRole("ADMIN")
                .antMatchers("/produtos/**").hasRole("ADMIN")
                .antMatchers("/pedidos/**").hasRole("ADMIN")

                // .antMatchers(HttpMethod.POST, "/usuarios").permitAll()
                //Qualquer outra requisção fora as que eu citei acima
                .anyRequest()
                //Só aceite requisição de pessoas authenticadas
                .authenticated()
                // e
                .and()
                /*E utilize o formulario de Login padrão do spring pra fazer a autenticação
                 Nesse caso além de fornecer a tela de login, o spring também fornece um controller
                 que ele mesmo acessa e faz toda a magica de receber o login e senha e chamar os métodos
                e service para realizar a autenticação
                */
                // .formLogin();

                /*
                 * CSRF é um tipo de ataque a aplicações web. Porém como a authenticação será feita por token
                 * a proteção a esse ataque é dispensável, então para evitar que o spring gaste processamento
                 * com essa proteção sem sentido, nesse caso, posso desabilitar
                 * */
                .csrf().disable()
                /*
                 * com o método abaixo eu digo que não quero que a autenticação fique salva na sessão
                 * eu informo que a politica de criação de sessão é stateless como rege as boas práticas
                 * do desenvolvimento de api's
                 * */
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                /*Nesse caso eu não vou mais ter a tela padrão do Spring com o formLogin
                  e também perco o controller default onde o spring fazia toda a mágica de receber
                  o login e senha do formLogin. Nesse caso agora eu é que tenho que criar um controller
                  para receber esses dados e gerar o token e um filtro para interceptar a requisição
                  pegar o token e validar
                 */
                .and()
                /*
                Só que o Spring tem um filtro próprio. Se eu quiser usar o meu eu preciso dizer que o
                meu fitro deve ser usado antes mesmo do filtro do Spring
                */
                //.addFilterBefore(new AutenticacaoViaTokenFilter(), UsernamePasswordAuthenticationFilter.class)

                /* Filtrar as requisições de login para fazer a autenticação */
                 .addFilterBefore(new JwtLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)

                /* Filtrar demais requisições para verificar a preservação do TOKEN JWT no HEADER do HTTP */
               .addFilterBefore(new JwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* Service que irá consultar o usuário no banco de dados */
        auth.userDetailsService(cadastroUsuarioService)

                /* Definimos o padrão de codificação de senha */
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html",
                "/v2/api-docs",
                "/webjars/**",
                "/configuration/**",
                "/swagger-resources/**");
    }
}
