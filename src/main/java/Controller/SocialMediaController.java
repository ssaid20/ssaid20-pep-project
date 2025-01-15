package Controller;

import Model.Account;
import Service.AccountService;
import io.javalin.Javalin;
import io.javalin.http.Context;

// /**
//  * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
//  * found in readme.md as well as the test cases. You should
//  * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
//  */
public class SocialMediaController {
    private final AccountService accountService = new AccountService();
    

     /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        
        // User-related endpoints
        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        
        
        return app;
    }

    // Handler for user registration
    private void registerHandler(Context ctx) {
        Account account = ctx.bodyAsClass(Account.class);
        Account registered = accountService.register(account);
        if (registered != null) {
            ctx.json(registered);
        } else {
            ctx.status(400);
        }
    }

    // Handler for user login
    private void loginHandler(Context ctx) {
        Account account = ctx.bodyAsClass(Account.class);
        Account loggedIn = accountService.login(account.getUsername(), account.getPassword());
        if (loggedIn != null) {
            ctx.json(loggedIn);
        } else {
            ctx.status(401);
        }
    }
}


