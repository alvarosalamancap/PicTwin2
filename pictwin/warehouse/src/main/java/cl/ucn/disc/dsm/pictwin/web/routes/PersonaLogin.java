package cl.ucn.disc.dsm.pictwin.web.routes;

import cl.ucn.disc.dsm.pictwin.services.Controller;
import cl.ucn.disc.dsm.pictwin.web.Route;

import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class PersonaLogin extends Route{
    public PersonaLogin(@NonNull final Controller controller){
        super(Method.POST, "/api/personas");
        this.handler = buildHandler(controller);
    }

    private static Handler buildHandler(Controller controller){
        return ctx -> {
            String password = ctx.formParam("password");
            String email = ctx.formParam("email");
            log.debug("Email detectado={} y contrasenia={}",email,password);

            if(StringUtils.isEmpty(password) || StringUtils.isEmpty(email)){
                ctx.status(HttpStatus.UNPROCESSABLE_CONTENT);
                return;
            }

            email = email.trim();
            password = password.trim();

            ctx.json(controller.login(email,password));
            ctx.status(200);
        };
    }
}