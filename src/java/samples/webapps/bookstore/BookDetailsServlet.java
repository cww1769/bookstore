/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * Use is subject to license terms.
 */

package samples.webapps.bookstore; 

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import samples.webapps.bookstore.database.*;
import samples.webapps.bookstore.cart.*;
import samples.webapps.bookstore.util.Currency;
import samples.webapps.bookstore.exception.*;

/**
 * This is a simple example of an HTTP Servlet.  It responds to the GET
 * method of the HTTP protocol. 
 */
public class BookDetailsServlet extends HttpServlet {

   private BookDB bookDB;

   public void init() throws ServletException {
      bookDB =
            (BookDB)getServletContext().getAttribute("bookDB");
         if (bookDB == null)
             throw new UnavailableException("Couldn't get database.");
   }

   public void destroy() {
         bookDB = null;
   }

      
   public void doGet (HttpServletRequest request,
                       HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(true);
    		ResourceBundle messages = (ResourceBundle)session.getAttribute("messages");

   // set headers and buffer size before accessing the Writer
        response.setContentType("text/html");
            response.setBufferSize(8192);
            PrintWriter out = response.getWriter();

   // then write the response
        out.println("<html>" +
                    "<head><title>" + messages.getString("TitleBookDescription") + "</title></head>");

        // Get the dispatcher; it gets the banner to the user
        RequestDispatcher dispatcher =
               getServletContext().getRequestDispatcher("/banner");
                                       
            if (dispatcher != null)
               dispatcher.include(request, response);
               
        //Get the identifier of the book to display
        String bookId = request.getParameter("bookId");
        if (bookId != null) {

            // and the information about the book
            try {
               BookDetails bd = bookDB.getBookDetails(bookId);
               Currency c = (Currency)session.getAttribute("currency");
               if (c == null) {
                  c = new Currency();
                  c.setLocale(request.getLocale());
                  session.setAttribute("currency", c);
               }
               c.setAmount(bd.getPrice());
      

               //Print out the information obtained
               out.println("<h2>" + bd.getTitle() + "</h2>" +
                           "&nbsp;" + messages.getString("By") + " <em>" + bd.getFirstName() + " " +
                           bd.getSurname() + "</em> &nbsp; &nbsp; " +
                           "(" + bd.getYear() + ")<br> &nbsp; <br>" +
                           "<h4>" + messages.getString("Critics")+ "</h4>" +
                           "<blockquote>" + bd.getDescription() +
                           "</blockquote>" +
                           "<h4>" + messages.getString("Price") + c.getFormat() + "</h4>" +
                           "<p><strong><a href=\"" +
                           response.encodeURL(request.getContextPath() + "/catalog?bookId=" + bookId) +
                           "\">" + messages.getString("CartAdd") + "</a>&nbsp;&nbsp;&nbsp;" +
                           "<a href=\"" + 
                           response.encodeURL(request.getContextPath() + "/catalog") + "\">" + messages.getString("ContinueShopping") + "</a></p></strong>");
            } catch (BookNotFoundException ex) {
                     response.resetBuffer();
                     throw new ServletException(ex);
            }
                                    

        }
        out.println("</body></html>");
        out.close();
    }

    public String getServletInfo() {
        return "The BookDetail servlet returns information about" +
               "any book that is available from the bookstore.";
    }

}
