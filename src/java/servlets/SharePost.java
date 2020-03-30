package servlets;

import DataBaseFiles.ServicesImplementation.PostServices;
import Model.Post;
import Model.User;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Eng Eslam khder
 */
public class SharePost extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String text = request.getParameter("texts"),
                    image = request.getParameter("image");
            User user = (User) request.getSession().getAttribute("user");
            Connection connect = (Connection) getServletContext().getAttribute("Connect");
            PostServices postservices = new PostServices();
            postservices.setConnection(connect);
            Post post = new Post();
            post.setIduser(user.getId());
            post.setText(text);
            post.setImage(image);
            if (postservices.sharePost(post) == 1) {
                response.sendRedirect("View/friend-finder/newsfeed.jsp");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "You must select only image", "Error", JOptionPane.ERROR_MESSAGE);
            response.sendRedirect("View/friend-finder/newsfeed.jsp");
        }
    }
}