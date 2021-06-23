package com.avizhen.test;

import com.avizhen.converter.impl.LordConverter;
import com.avizhen.converter.impl.UniverseConverter;
import com.avizhen.dto.LordCreateDto;
import com.avizhen.dto.UniverseCreateDto;
import com.avizhen.entity.Lord;
import com.avizhen.entity.Universe;
import com.avizhen.enums.Status;
import com.avizhen.repository.LordRepository;
import com.avizhen.repository.UniverseRepository;
import com.avizhen.service.impl.LordServiceImpl;
import com.avizhen.service.impl.UniverseServiceImpl;
import com.avizhen.test.testData.UniverseTestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UniverseServiceTest {
    private UniverseTestData universeTestData = UniverseTestData.getInstance();

    @Mock
    private UniverseRepository universeRepository;
    @InjectMocks
    private UniverseServiceImpl universeService;
    @Spy
    private UniverseConverter universeConverter = new UniverseConverter();

    @Before
    public void setUp() throws Exception {
        when(universeRepository.findAll()).thenReturn(universeTestData.getAllTestUniverses());
        when(universeRepository.getOne(any(Integer.class))).thenAnswer(i -> universeTestData.findById((Integer) i.getArguments()[0]));
        when(universeRepository.save(any((Universe.class)))).thenAnswer(i -> universeTestData.saveTestUniverse((Universe) i.getArguments()[0]));
    }

    @Test
    public void createUniverse() throws Exception {
        Integer nextId = universeTestData.getNextId();
        String title = "mars";
        UniverseCreateDto universeCreateDto = new UniverseCreateDto();
        universeCreateDto.setTitle(title);
        Universe universe = new Universe();
        universe.setId(nextId);
        universe.setTitle(title);
        universe.setStatus(Status.FREE);

        Universe actualUniverse = universeTestData.saveTestUniverse(universe);
        Universe expectedUniverse = universeService.createUniverse(universeCreateDto);
        Assert.assertNotNull("Universe not found", expectedUniverse);
        String actualTitle = actualUniverse.getTitle();
        String expectedUniverseTitle = expectedUniverse.getTitle();
        Assert.assertNotNull("Created universe equals null ", actualUniverse);
        Assert.assertEquals("Title is not equals", expectedUniverseTitle, actualTitle);
    }

    @Test
    public void deleteUniverse() throws Exception {
        Integer universeId = 2;
        Universe universe = universeTestData.deleteTestUniverse(universeId);
        universeService.deleteUniverse(universeId);
        Assert.assertNull("Universe was not delete", universe);

    }

    @Test
    public void getAllUniversity() throws Exception {
        List<Universe> actualTestUniverses = universeTestData.getAllTestUniverses();
        List<Universe> expectedTestUniverses = universeService.getAllUniversity();
        Assert.assertEquals(expectedTestUniverses.size(), actualTestUniverses.size());

    }


}