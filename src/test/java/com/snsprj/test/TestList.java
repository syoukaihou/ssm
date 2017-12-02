package com.snsprj.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestList {

    @Test
    public void testListSet(){
        
        List<String> list = new ArrayList<>();
        
        // ERROR!!!!
        list.set(0, "hahah");
        list.set(1, "rrrr");
        list.get(1);
    }
}
