package academy.prog;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class LoginServlet extends HttpServlet {

    private MessageList msgList = MessageList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("You are entering in system");
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        AllUsers users = AllUsers.getInstance();
        System.out.println(users.getUsers());
        int result = users.passwordCheck(login, pass);
        resp.setStatus(result);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String fromStr = req.getParameter("from");
        int from = 0;
        try {
            from = Integer.parseInt(fromStr);
            if (from < 0) from = 0;
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        resp.setContentType("application/json");

        String json = msgList.toJSON(from);
        if (json != null) {
			OutputStream os = resp.getOutputStream();byte[] buf = json.getBytes(StandardCharsets.UTF_8);
		os.write(buf);


        }
    }
}
