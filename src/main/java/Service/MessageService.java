package Service;

import DAO.MessageDAO;
import Model.Message;
import java.util.List;

public class MessageService {
    private final MessageDAO messageDAO = new MessageDAO();

    public Message createMessage(Message message) {
        if (message.getMessage_text().isBlank() || message.getMessage_text().length() > 255) {
            return null;
        }
        return messageDAO.createMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int messageId) {
        return messageDAO.getMessageById(messageId);
    }

    public Message deleteMessage(int messageId) {
        return messageDAO.deleteMessage(messageId);
    }

    public Message updateMessage(int messageId, Message updatedMessage) {
        if (updatedMessage.getMessage_text().isBlank() || updatedMessage.getMessage_text().length() > 255) {
            return null;
        }
        return messageDAO.updateMessage(messageId, updatedMessage);
    }

    public List<Message> getMessagesByAccountId(int accountId) {
        return messageDAO.getMessagesByAccountId(accountId);
    }
}
