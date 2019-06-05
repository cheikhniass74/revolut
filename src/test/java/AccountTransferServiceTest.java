import com.money.transfer.entity.Account;
import com.money.transfer.entity.TransferForm;
import com.money.transfer.entity.User;
import com.money.transfer.repo.AccountRepo;
import com.money.transfer.repo.UserRepo;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class AccountTransferServiceTest {
    private static final String ENDPOINT = "http://localhost:8080/rest/transfer";
    ResteasyClient client = new ResteasyClientBuilder().build();
    private UserRepo userRepo = new UserRepo();
    private AccountRepo accountRepo = new AccountRepo();

    @Test
    public void testAccountTransferSuccess() {

        ResteasyWebTarget target = client.target(ENDPOINT).path("money");
        Account accountFrom = accounts().get(0);
        Account accountTo = accounts().get(1);
        TransferForm transferForm = TransferForm.builder().amount(100).fromAccountNumber(accountFrom.getAccountNumber()).toAccountNumber(accountTo.getAccountNumber()).build();
        Response response = target.request().post(Entity.entity(transferForm, MediaType.APPLICATION_JSON_TYPE));
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testAccountTransferAccountNotFoundException() {

        ResteasyWebTarget target = client.target(ENDPOINT).path("money");
        Account accountTo = accounts().get(1);
        TransferForm transferForm = TransferForm.builder().amount(20000).fromAccountNumber(0).toAccountNumber(accountTo.getAccountNumber()).build();
        Response response = target.request().post(Entity.entity(transferForm, MediaType.APPLICATION_JSON_TYPE));
        Assert.assertEquals(409, response.getStatus());

    }


    @Test
    public void testAccountTransferNotEnoughFunds() {

        ResteasyWebTarget target = client.target(ENDPOINT).path("money");
        Account accountFrom = accounts().get(0);
        Account accountTo = accounts().get(1);
        TransferForm transferForm = TransferForm.builder().amount(20000).fromAccountNumber(accountFrom.getAccountNumber()).toAccountNumber(accountTo.getAccountNumber()).build();
        Response response = target.request().post(Entity.entity(transferForm, MediaType.APPLICATION_JSON_TYPE));
        Assert.assertEquals(409, response.getStatus());

    }

    public List<Account> accounts() {
        Account account1 = Account.builder().accountType("checking").balance(1000).user(users().get(0)).build();
        Account account2 = Account.builder().accountType("checking").balance(500).user(users().get(1)).build();
        accountRepo.saveAccount(account1);
        accountRepo.saveAccount(account2);
        System.out.println("*****" + accountRepo.getAllAccount());
        return accountRepo.getAllAccount();

    }

    public List<User> users() {
        User user1 = User.builder().email("Jack.frank@gmail.com").name("Jack Frank").build();
        User user2 = User.builder().email("Thomas.miller").name("Thomas Miller").build();
        userRepo.saveOrUpdate(user1);
        userRepo.saveOrUpdate(user2);
        return userRepo.getAllUsers();
    }


}
