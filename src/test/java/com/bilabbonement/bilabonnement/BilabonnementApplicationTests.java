package com.bilabbonement.bilabonnement;

import com.bilabbonement.bilabonnement.Model.OmsaetningMaaned;
import com.bilabbonement.bilabonnement.Service.LejekontraktService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BilabonnementApplicationTests {

    @Test
    void omsaetningsMaanedTest() {
        List<OmsaetningMaaned> omsaetningList = new ArrayList<>();
        omsaetningList.add(new OmsaetningMaaned("EURO", 100));
        omsaetningList.add(new OmsaetningMaaned("DKK", 1000));

        LejekontraktService lejekontraktService = new LejekontraktService();

        Double result = lejekontraktService.omsaetningMaanedTest(omsaetningList);

        // Euro ganges med 7.5 for at få det over i DKK 100 EUR + 1000 DKK = 1750 DKK
        assertEquals(100 * 7.5 + 1000, result);
    }
}
