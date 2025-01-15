package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

// /**
//  * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
//  * found in readme.md as well as the test cases. You should
//  * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
//  */
public class SocialMediaController {
    private final AccountService accountService = new AccountService();
    private final MessageService messageService = new MessageService();

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
        
        // Message-related endpoints
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesByUserHandler);
        
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

    // Handler for creating a new message
    private void postMessageHandler(Context ctx) {
        Message message = ctx.bodyAsClass(Message.class);
        Message created = messageService.createMessage(message);
        if (created != null) {
            ctx.json(created);
        } else {
            ctx.status(400);
        }
    }

    // Handler for retrieving all messages
    private void getAllMessagesHandler(Context ctx) {
        ctx.json(messageService.getAllMessages());
    }

    // Handler for retrieving a message by ID
    private void getMessageByIdHandler(Context ctx) {
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.getMessageById(messageId);
        if (message != null) {
            ctx.json(message);
        } else {
            ctx.status(200).result("");
        }
    }

    // Handler for deleting a message by ID
    private void deleteMessageHandler(Context ctx) {
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        Message deletedMessage = messageService.deleteMessage(messageId);
        if (deletedMessage != null) {
            ctx.json(deletedMessage);
        } else {
            ctx.status(200).result("");
        }
    }

    // Handler for updating a message by ID
    private void updateMessageHandler(Context ctx) {
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        Message messageUpdate = ctx.bodyAsClass(Message.class);
        Message updatedMessage = messageService.updateMessage(messageId, messageUpdate);
        if (updatedMessage != null) {
            ctx.json(updatedMessage);
        } else {
            ctx.status(400);
        }
    }
    
    // Handler for retrieving all messages by a specific user
    private void getMessagesByUserHandler(Context ctx) {
        int accountId = Integer.parseInt(ctx.pathParam("account_id"));
        ctx.json(messageService.getMessagesByAccountId(accountId));
    }
}
