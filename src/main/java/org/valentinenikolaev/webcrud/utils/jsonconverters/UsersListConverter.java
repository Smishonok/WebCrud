package org.valentinenikolaev.webcrud.utils.jsonconverters;

import com.google.gson.*;
import org.valentinenikolaev.webcrud.models.User;

import java.lang.reflect.Type;
import java.util.List;

public class UsersListConverter implements JsonSerializer<List<User>> {
    @Override
    public JsonElement serialize(List<User> users, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray userList = new JsonArray();

        Gson jsonUser = new GsonBuilder().registerTypeAdapter(User.class, new UserConverter()).create();

        for (User user : users) {
            userList.add(jsonUser.toJsonTree(user));
        }

        return userList;
    }
}
