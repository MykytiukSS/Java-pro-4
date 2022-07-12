package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;

import java.util.Map;

public class AllUsers {
    private static final AllUsers usersList = new AllUsers();

    private final Gson gson;

    private final Map<String, User> users = new HashMap();

    public Map<String, User> getUsers() {
        return users;
    }

    public static AllUsers getInstance() {
        return usersList;
    }

    private AllUsers() {
        gson = new GsonBuilder().create();
    }

    public synchronized int add(User user) {
        for (String s : users.keySet()) {
            if (s.equals(user.getLogin())){
                return HttpServletResponse.SC_BAD_REQUEST;
            }
        }
        users.put(user.getLogin(), user);
        System.out.println("user " + user.getLogin()+ " added");
        return HttpServletResponse.SC_OK;
    }

    public synchronized int passwordCheck(String login, String pass){
        if (users.get(login).getPassword().equals(pass)) {
            users.get(login).setOnline(true);
            return HttpServletResponse.SC_OK;

        }else
            return HttpServletResponse.SC_BAD_REQUEST;
    }

    public synchronized String toJSON() {
        return gson.toJson(users);
    }


    @Override
    public String toString() {
        return "Users{" +
                "gson=" + gson +
                ", users=" + users +
                '}';
    }
}
