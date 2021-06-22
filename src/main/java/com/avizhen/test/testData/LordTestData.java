package com.avizhen.test.testData;

import com.avizhen.entity.Lord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LordTestData {
    private Map<Integer, Lord> lordsForTest = new HashMap<>();
    private static LordTestData INSTANCE = null;

    private LordTestData() {
        initLordTestData();
    }

    public static LordTestData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LordTestData();
        }
        return INSTANCE;
    }

    private void initLordTestData() {
        buildLord("lord", 1, 18);
        buildLord("test", 2,22);
    }

    private Lord buildLord(String name, Integer id, Integer age) {
        Lord testLord = lordsForTest.get(1);
        Lord lord = new Lord();
        lord.setId(id);
        lord.setName(name);
        lord.setAge(age);
        return lord;
    }

    public List<Lord> getAllTestLords() {
        return new ArrayList<>(lordsForTest.values());
    }



    public Lord saveTestLord(Lord lord) {
        lordsForTest.put(lord.getId(), lord);
        return lord;
    }
}
