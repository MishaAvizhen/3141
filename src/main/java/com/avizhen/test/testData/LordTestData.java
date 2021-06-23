package com.avizhen.test.testData;

import com.avizhen.entity.Lord;
import com.avizhen.entity.Universe;
import com.avizhen.enums.Status;

import java.util.*;

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
    public Integer getNextId() {
        return lordsForTest.size() + 1;
    }

    private void initLordTestData() {
        Lord lordFree = new Lord();
        lordFree.setId(1);
        lordFree.setName("lord");
        lordFree.setAge(18);
        assignUniversityToLord(lordFree, Collections.emptyList());
        saveTestLord(lordFree);
        Lord lordBusy = new Lord();
        List<Universe> universeList = new ArrayList<>();
        universeList.add(UniverseTestData.getInstance().findById(2));

        lordBusy.setId(2);
        lordBusy.setName("test");
        lordBusy.setAge(22);
        assignUniversityToLord(lordBusy, universeList);
        saveTestLord(lordBusy);
    }

    private void assignUniversityToLord(Lord lordBusy, List<Universe> universeList) {
        lordBusy.setLordUniverseList(universeList);
        for (Universe universe : universeList) {
            universe.setLord(lordBusy);
        }
    }


    public List<Lord> getAllTestLordsWithoutPlanet() {
        List<Lord> lordList = new ArrayList<>();
        for (Lord lord : lordsForTest.values()) {
            if (lord.getLordUniverseList().isEmpty()) {
                lordList.add(lord);
            }
        }
        return lordList;
    }

    public List<Lord> getAllTestLords() {
        return new ArrayList<>(lordsForTest.values());
    }


    public Lord findTestLordById(Integer id) {
        for (Lord lord : lordsForTest.values()) {
            if (lord.getId().equals(id)) {
                return lord;
            }
        }
        return null;
    }

    public void appointToRuleTestPlanet(Integer lordId, Integer universeId) {
        Lord testLordById = findTestLordById(lordId);
        Universe testUniverse = UniverseTestData.getInstance().findById(universeId);
        testUniverse.setStatus(Status.BUSY);
        testUniverse.setLord(testLordById);
        saveTestLord(testLordById);
    }

    public Lord saveTestLord(Lord lord) {
        lordsForTest.put(lord.getId(), lord);
        return lord;
    }
}
