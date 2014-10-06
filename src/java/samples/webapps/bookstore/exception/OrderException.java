/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * Use is subject to license terms.
 */

package samples.webapps.bookstore.exception;

/** This application exception indicates that an order cannot be completed.
*/

public class OrderException extends Exception {

    public OrderException () { }

    public OrderException (String msg) {
        super(msg);
    } 
}

