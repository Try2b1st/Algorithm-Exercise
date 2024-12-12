package org.ex;

import java.math.BigInteger;
import java.util.*;

public class Test {

    private static int i = 0;

   public synchronized int add(int a){
       return i + a;
   }

   public synchronized int function(int a){
       int j = add(a);
       return j;
   }

}
