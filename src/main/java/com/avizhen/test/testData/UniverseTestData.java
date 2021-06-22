package com.avizhen.test.testData;

import com.avizhen.entity.Lord;
import com.avizhen.entity.Universe;
import com.avizhen.enums.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniverseTestData {
    private Map<Integer, Universe> universesForTest = new HashMap<>();
    private static UniverseTestData INSTANCE = null;

    private UniverseTestData() {
        initUniverseTestData();
    }

    public static UniverseTestData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UniverseTestData();
        }
        return INSTANCE;
    }

    private void initUniverseTestData() {
        buildUniverse("universe", 1,Status.FREE);
        buildUniverse("test", 2,Status.BUSY);
    }

    private Universe findById(Integer id) {
        for (Universe universe : universesForTest.values()) {
            if (universe.getId().equals(id)) {
                return universe;
            }
        }
        return null;
    }

    private void delete(Integer id) {
        Universe universe = findById(id);
        if (universe != null) {
            universesForTest.remove(id);
        }
    }

    private Universe buildUniverse(String title, Integer id, Status status) {

        Lord testLord = new Lord();
        testLord.setId(1);
        testLord.setName("lord");
        testLord.setAge(20);

        Universe universe = new Universe();
        universe.setId(id);
        universe.setTitle(title);
        universe.setStatus(status);
        universe.setLord(testLord);

        return universe;
    }

    public List<Universe> getAllTestUniverses() {
        return new ArrayList<>(universesForTest.values());
    }



    public Universe saveTestUniverse(Universe universe) {
        universesForTest.put(1, universe);
        return universe;
    }
}
