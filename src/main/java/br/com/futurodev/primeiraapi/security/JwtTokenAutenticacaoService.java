package br.com.futurodev.primeiraapi.security;

import br.com.futurodev.primeiraapi.context.ApplicatonContextLoad;
import br.com.futurodev.primeiraapi.model.Usuario;
import br.com.futurodev.primeiraapi.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Service
@Component
public class JwtTokenAutenticacaoService {
    /* A validade do Token de 2 dias */
    private static final long EXPERAION_TIME = 24 * 60 * 60 * 2;

    /* Uma senha única para compor a atentiticação e ajudar na segurança */
    private static final String SECRET = "SenhaExtremamenteSecretaForte";

    /* Prefixo padrão do token */
    private static final String TOKEN_PREFIX = "Bearer";

    /* O cabeçalho do token */
    private static final String HEADER_STRING = "Authorization";

    /* Gerando token de autentição e adicionando ao cabeçalho e resposta Http */
    public void addAuthentication(HttpServletResponse response, String username) throws IOException{

        /* Montagem do token */
        String JWT = Jwts.builder() // chama o gerador de token
                .setSubject(username) // adicionamos o usuário
                .setExpiration(new Date(System.currentTimeMillis() + EXPERAION_TIME)) // tempo de expiração do token
                .signWith(SignatureAlgorithm.HS512, SECRET).compact(); // compactando e usando o algoritmo de criptografia


        String token = TOKEN_PREFIX + "  " + JWT; /*Bearer asfgas8fg7asd0f87asd0f87as0df8safd0asd8f7a0s9*/

        /* adiciona no cabeçalho Http*/
        response.addHeader(HEADER_STRING, token);

        /* Escrever o token como resposta no corpo Http */
        response.getWriter().write("{\"Authorization\": \""+token+"\"}");

    }

    /*Retorna o usuário validado com token ou caso seja inválido retorna null*/
    public Authentication getAuthentication(HttpServletRequest request){

        /* Pega o token enviado no cabeçalho Http*/
        String token = request.getHeader(HEADER_STRING);

        if(token != null){

            String user = Jwts.parser().setSigningKey(SECRET) /*Bearer sdf8g7s0df8gs0dfg8s0d*/
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "")) /* sdf8g7s0df8gs0dfg8s0d */
                    .getBody().getSubject(); /* brunomoura */

            if(user != null){
                 Usuario usuario = ApplicatonContextLoad.getApplicationContext()
                         .getBean(UsuarioRepository.class).findUserByLogin(user);

                 System.out.println("Login do usuario: "+usuario.getLogin());

                 if(usuario != null){
                     return new UsernamePasswordAuthenticationToken(
                             usuario.getLogin(),
                             usuario.getSenha(),
                             usuario.getAuthorities());
                 }
            }

        }

        return null; /* Não autorizado */
    }


}
