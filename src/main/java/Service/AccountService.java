package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private final AccountDAO accountDAO = new AccountDAO();

    public Account register(Account account) {
        if (account.getUsername().isBlank() || account.getPassword().length() < 4) {
            return null;
        }
        return accountDAO.register(account);
    }

    public Account login(String username, String password) {
        return accountDAO.login(username, password);
    }
}
