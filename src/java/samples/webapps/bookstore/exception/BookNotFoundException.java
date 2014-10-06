/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * Use is subject to license terms.
 */

package samples.webapps.bookstore.exception;

/** This application exception indicates that a book 
 *  has not been found.
*/

public class BookNotFoundException extends Exception {

    public BookNotFoundException () { }

    public BookNotFoundException (String msg) {
        super(msg);
    } 
}

