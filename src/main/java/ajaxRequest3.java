import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/ajaxRequest3")
public class ajaxRequest3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usercode = req.getParameter("usercode");
        String username = req.getParameter("username");
        resp.getWriter().println("<font color=\"red\">成功发送post请求</font><br>"+usercode+"|"+username);

        //super.doGet(req, resp);//对应的post请求就应该在doPost里写，不然会405，这里调用doGet
    }
}
