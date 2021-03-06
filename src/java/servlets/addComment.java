package servlets;

import DataBaseFiles.ServicesImplementation.CommentServices;
import Model.Post;
import Model.User;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addComment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String comment = request.getParameter("comment"),
                    idpost = request.getParameter("id");
            if (comment.isEmpty()) {
                response.getWriter().print("comment");
            } else {
                User user = (User) request.getSession().getAttribute("user");
                Connection connection = (Connection) getServletContext().getAttribute("Connect");
                Post post = new Post();
                post.setIdpost(Integer.parseInt(idpost));
                CommentServices commentservices = new CommentServices();
                commentservices.setConnection(connection);
                int res = commentservices.addComment(user, post, comment);
                response.getWriter().print("success");
            }
        } catch (Exception e) {
            response.sendRedirect("View/friend-finder/Log_In.jsp");
        }

    }
}
