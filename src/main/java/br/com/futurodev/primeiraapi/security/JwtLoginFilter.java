package br.com.futurodev.primeiraapi.security;

import br.com.futurodev.primeiraapi.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* Estabelece o nosso gerenciador de Token */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {


    /* Configurando o nosso  gerenciador de autenticação */
    protected JwtLoginFilter(String url, AuthenticationManager authenticationManager){

        /*Obriga a autenticar a URL*/
        super(new AntPathRequestMatcher(url));

        System.out.println(url);

        /* Gerenciador de autenticação */
        setAuthenticationManager(authenticationManager);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        /* Pegamos o token do usuário para validar */
        Usuario user = new ObjectMapper()
                .readValue(request.getInputStream(), Usuario.class);

        /* Retorna o usuário login, senha e acessos */
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                user.getLogin(), user.getSenha()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        new JwtTokenAutenticacaoService().addAuthentication(response, authResult.getName());

    }
}
