package com.carsonlius.thinkinjava.containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FillingLists {
    public static void main(String[] args) {
        List<StringAddress> stringAddressList = new ArrayList<>(
                Collections.nCopies(3, new StringAddress("Hello"))
        );

        System.out.println(stringAddressList);
        System.out.println(Collections.nCopies(3, new StringAddress("world")));
        Collections.fill(stringAddressList, new StringAddress("world"));
        System.out.println(stringAddressList);

    }
}
