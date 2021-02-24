package org.valentinenikolaev.webcrud.utils.jsonconverters;

import com.google.gson.*;
import org.valentinenikolaev.webcrud.models.Account;
import org.valentinenikolaev.webcrud.models.User;

import java.lang.reflect.Type;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserConverter implements JsonSerializer<User>, JsonDeserializer<User> {
    @Override
    public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject userInJson = new JsonObject();

        userInJson.addProperty("user_id", user.getId());
        userInJson.addProperty("firstName", user.getFirstName());
        userInJson.addProperty("lastName", user.getLastName());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM.dd.yyyy");
        userInJson.addProperty("birthday", user.getBirthday().format(dateTimeFormatter));

        Gson gson = new GsonBuilder().registerTypeAdapter(Account.class, new AccountConverter()).create();
        userInJson.add("account", gson.toJsonTree(user.getAccount()));

        return userInJson;
    }

    @Override
    public User deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject userInJson = jsonElement.getAsJsonObject();

        String firstName = userInJson.get("firstName").getAsString();
        String lastName = userInJson.get("lastName").getAsString();

        LocalDate birthday;
        try {
            birthday = LocalDate.parse(userInJson.get("birthday")
                                                           .getAsString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeException e) {
            throw  new DateTimeException("Illegal format of user birthday.");
        }

        Gson jsonConverter = new GsonBuilder().registerTypeAdapter(Account.class, new AccountConverter()).create();
        Account account = jsonConverter.fromJson(userInJson.get("account"),Account.class);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthday(birthday);
        account.setUser(user);
        user.setAccount(account);

        return user;
    }
}
