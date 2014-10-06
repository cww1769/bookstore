/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * Use is subject to license terms.
 */

package samples.webapps.bookstore.util;

public class Counter {
   private int counter;
   public Counter() {
      counter = 0;
   }
   public synchronized int getCounter() {
      return counter;
   }
   public synchronized int setCounter(int c) {
      counter = c;
      return counter;
   }
   public synchronized int incCounter() {
      return(++counter);
   }
}
