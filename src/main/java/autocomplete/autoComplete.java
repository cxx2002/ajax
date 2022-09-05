package autocomplete;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import static java.lang.Class.*;

@WebServlet("/autoComplete")
public class autoComplete extends HttpServlet {

    public static String jdbc(StringBuffer sb,String keywords) throws ClassNotFoundException, SQLException {
        //配置信息
//useUnicode=true&characterEncoding=utf-8解决中文乱码
        String url="jdbc:mysql://localhost:3306/people?useUnicode=true&characterEncoding=UTF-8" +
                "&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "123456";
//1.加载驱动
        forName("com.mysql.cj.jdbc.Driver");
//2.连接数据库,代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "select content from t_ajax where content like ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, keywords + "%");
//5.执行查询SQL,返回一个ResultSet :结果集
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()){
            sb.append("{\"content\":\""+rs.getString("content")+"\"},");
        }
//6.关闭连接，释放资源(- -定要做)先开后关
        rs.close();
        preparedStatement.close();
        connection.close();

        return sb.substring(0, sb.length() - 1) + "]";
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入了");

        String keywords = req.getParameter("textValue");//拿到前端的ajax请求的文本框的值
        System.out.println("keywords="+keywords);

        StringBuffer sb = new StringBuffer("[");

        //配置信息
//useUnicode=true&characterEncoding=utf-8解决中文乱码
        String url="jdbc:mysql://localhost:3306/people?useUnicode=true&characterEncoding=UTF-8" +
                "&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "123456";
        try {
//1.加载驱动
            forName("com.mysql.cj.jdbc.Driver");
//2.连接数据库,代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "select content from t_ajax where content like ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, keywords + "%");
//5.执行查询SQL,返回一个ResultSet :结果集
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                sb.append("{\"content\":\""+rs.getString("content")+"\"},");
            }
//6.关闭连接，释放资源(- -定要做)先开后关
            rs.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        System.out.println(sb.substring(0, sb.length() - 1) + "]");
            resp.getWriter().print(sb.substring(0, sb.length() - 1) + "]");

    }
}
