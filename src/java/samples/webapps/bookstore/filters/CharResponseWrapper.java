/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * Use is subject to license terms.
 */

package samples.webapps.bookstore.filters;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class CharResponseWrapper extends HttpServletResponseWrapper {
    private CharArrayWriter output;

    public String toString() {
        return output.toString();
    }
      
    public CharResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new CharArrayWriter();
    }

    public PrintWriter getWriter() {
        return new PrintWriter(output);
    }
}
