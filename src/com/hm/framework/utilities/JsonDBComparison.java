package com.hm.framework.utilities;

import org.junit.Assert;

public class JsonDBComparison {

    public static void jsonDataToDBDataComparison(Object json,Object database){

        Assert.assertEquals(json,database);


    }
}

