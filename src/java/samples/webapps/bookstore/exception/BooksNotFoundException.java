/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * Use is subject to license terms.
 */

package samples.webapps.bookstore.exception;

/** This application exception indicates that books 
 *  have not been found.
*/

public class BooksNotFoundException extends Exception {

    public BooksNotFoundException () { }

    public BooksNotFoundException (String msg) {
        super(msg);
    } 
}

