package com.bilabbonement.bilabonnement;

import com.bilabbonement.bilabonnement.Model.OmsaetningMaaned;
import com.bilabbonement.bilabonnement.Service.LejekontraktService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BilabonnementApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void omsaetningsMaanedTest() {

        List<OmsaetningMaaned> omsaetningList = new ArrayList<>();
        omsaetningList.add(new OmsaetningMaaned("EURO",100));
        omsaetningList.add(new OmsaetningMaaned("DKK",1000));

        LejekontraktService lejekontraktService = new LejekontraktService();
        Double result = lejekontraktService.omsaetningMaaned();

        assertEquals(100 * 7.5 + 1000, result);
    }
}
