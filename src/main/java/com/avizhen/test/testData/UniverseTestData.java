package com.avizhen.test.testData;

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
        saveTestUniverse( buildTestUniverse(1, "universe", Status.FREE));
        saveTestUniverse( buildTestUniverse(2, "test", Status.BUSY));
    }
    public Integer getNextId() {
        return universesForTest.size() + 1;
    }
    public Universe findById(Integer id) {
        for (Universe universe : universesForTest.values()) {
            if (universe.getId().equals(id)) {
                return universe;
            }
        }
        return null;
    }

    public Universe deleteTestUniverse(Integer id) {
        Universe universeToDelete = findById(id);
        if (universeToDelete != null) {
            universesForTest.remove(id);

        }

        return null;
    }

    private Universe buildTestUniverse(Integer id, String title, Status status) {

        Universe universe = new Universe();
        universe.setId(id);
        universe.setTitle(title);
        universe.setStatus(status);

        return universe;
    }

    public List<Universe> getAllTestUniverses() {
        return new ArrayList<>(universesForTest.values());
    }


    public Universe saveTestUniverse(Universe universe) {
        universesForTest.put(getNextId(), universe);
        return universe;
    }
}
