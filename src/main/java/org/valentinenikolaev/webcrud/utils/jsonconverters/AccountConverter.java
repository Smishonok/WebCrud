package org.valentinenikolaev.webcrud.utils.jsonconverters;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.valentinenikolaev.webcrud.models.Account;
import org.valentinenikolaev.webcrud.models.AccountStatus;
import org.valentinenikolaev.webcrud.utils.PasswordHandler;

import java.lang.reflect.Type;

public class AccountConverter implements JsonSerializer<Account>, JsonDeserializer<Account> {

    private PasswordHandler passwordHandler;

    @Autowired
    public void setPasswordHandler(PasswordHandler passwordHandler) {
        this.passwordHandler = passwordHandler;
    }

    @Override
    public JsonElement serialize(Account account, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject accountInJson = new JsonObject();
        accountInJson.addProperty("account_id", account.getId());
        accountInJson.addProperty("login", account.getLogin());
        accountInJson.addProperty("status", account.getStatus().toString());
        return accountInJson;
    }

    @Override
    public Account deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject accountInJson = jsonElement.getAsJsonObject();

        String login = accountInJson.get("login").getAsString();
        String password = accountInJson.get("password").getAsString();
        String passwordToken = passwordHandler.encode(password);

        AccountStatus accountStatus = null;
        if (accountInJson.has("status")) {
            accountStatus = AccountStatus.valueOf(accountInJson.get("status").getAsString());
        }

        Account account = new Account();
        account.setLogin(login);
        account.setPasswordToken(passwordToken);
        account.setStatus(accountStatus);
        return account;
    }
}
