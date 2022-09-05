import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajaxRequest4")
public class ajaxRequest4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        if (username.equals("zhangsan")){
            resp.getWriter().write("<font color=\"red\">用户名已存在</font>"+username+"!!!!!!!");
        }else {
            resp.getWriter().write("<font color='green'>用户名正确</font>"+username+"!!!!!!!");
        }
    }
}
