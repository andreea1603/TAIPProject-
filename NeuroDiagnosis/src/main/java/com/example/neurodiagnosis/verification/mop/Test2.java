package com.example.neurodiagnosis.verification.mop;

import java.util.Iterator;
import java.util.Vector;

public class Test2 {
    public static void main(String[] args) {
        Vector v=new Vector();
        v.add(new Integer(10));
        Iterator i =v.iterator();
        v.add(new Integer(20));
        Object obj = i.next();
        System.out.println(obj);
    }
}
