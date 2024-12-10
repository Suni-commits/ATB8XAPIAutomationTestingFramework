package org.example.tests.integration.sample;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class dummy {

    @Description("TC_01 : Verifying creation of booking id")
    @Owner("Suneetha")
    @Test(groups= "QA",priority=1)
    void test_createbooking(){
        Assert.assertTrue(true);
    }

    @Description("TC_02 : Verifying booking details ")
    @Owner("Suneetha")
    @Test(groups= "QA", priority=2)
    void test_getbooking(){
        Assert.assertTrue(true);
    }

    @Description("TC_03 : updating booking details ")
    @Owner("Suneetha")

    @Test(groups= "QA", priority=3)
    void test_updatebooking(){
        Assert.assertTrue(true);
    }

    @Description("TC_4 : deleting booking details ")
    @Owner("Suneetha")
    @Test(groups= "QA", priority=4)
    void test_deletebooking(){
        Assert.assertTrue(true);
    }



}
