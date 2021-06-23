package com.avizhen.test;

import com.avizhen.converter.impl.LordConverter;
import com.avizhen.converter.impl.UniverseConverter;
import com.avizhen.dto.LordCreateDto;
import com.avizhen.entity.Lord;
import com.avizhen.entity.Universe;
import com.avizhen.enums.Status;
import com.avizhen.repository.LordRepository;
import com.avizhen.repository.UniverseRepository;
import com.avizhen.service.impl.LordServiceImpl;
import com.avizhen.service.impl.UniverseServiceImpl;
import com.avizhen.test.testData.LordTestData;
import com.avizhen.test.testData.UniverseTestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.DependsOn;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LordServiceTest {
    private LordTestData lordTestData = LordTestData.getInstance();
    private UniverseTestData universeTestData = UniverseTestData.getInstance();

    @Mock
    private LordRepository lordRepository;
    @Mock
    private UniverseRepository universeRepository;
    @Spy
    private UniverseConverter universeConverter = new UniverseConverter();
    @Spy
    private LordConverter lordConverter = new LordConverter();
    @InjectMocks
    private UniverseServiceImpl universeService;

    private LordServiceImpl lordService;

    @Before
    public void setUp() throws Exception {
        lordService = new LordServiceImpl(lordRepository, lordConverter, universeService, universeRepository);
        when(lordRepository.findAll()).thenReturn(lordTestData.getAllTestLords());
        when(universeRepository.findAll()).thenReturn(universeTestData.getAllTestUniverses());
        when(lordRepository.getOne(any(Integer.class))).thenAnswer(i -> lordTestData.findTestLordById((Integer) i.getArguments()[0]));
        when(universeRepository.getOne(any(Integer.class))).thenAnswer(i -> universeTestData.findById((Integer) i.getArguments()[0]));
        when(universeRepository.save(any((Universe.class)))).thenAnswer(i -> universeTestData.saveTestUniverse((Universe) i.getArguments()[0]));
        when(lordRepository.save(any((Lord.class)))).thenAnswer(i -> lordTestData.saveTestLord((Lord) i.getArguments()[0]));
    }

    @Test
    public void createLord() throws Exception {
        Integer nextId = lordTestData.getNextId();
        String name = "testLordCreate";
        Integer age = 27;
        LordCreateDto lordCreateDto = new LordCreateDto();
        lordCreateDto.setName(name);
        lordCreateDto.setAge(age);
        Lord lord = new Lord();
        lord.setId(nextId);
        lord.setName(name);
        lord.setAge(age);

        Lord actualLord = lordTestData.saveTestLord(lord);
        Lord expectedLord = lordService.createLord(lordCreateDto);
        Assert.assertNotNull("Lord not found", expectedLord);
        String expectedLordName = expectedLord.getName();
        String actualLordName = actualLord.getName();
        Assert.assertNotNull("Created lord equals null ", actualLord);
        Assert.assertEquals("LordName is not equals", expectedLordName, actualLordName);

    }

    @Test
    public void appointToRulePlanet() throws Exception {
        Integer lordId = 2;
        Integer universeId = 1;
        Lord testLordById = lordTestData.findTestLordById(lordId);
        Universe byId = universeTestData.findById(universeId);
        byId.setLord(testLordById);
        byId.setStatus(Status.BUSY);
        lordTestData.appointToRuleTestPlanet(lordId, universeId);
        lordService.appointToRulePlanet(lordId, universeId);
        Status expectedStatus = byId.getStatus();
        Lord expectedLord = byId.getLord();
        Assert.assertEquals("Lord not equals", expectedLord, testLordById);
        Assert.assertEquals("Status not equals", expectedStatus, Status.BUSY);

    }

    @Test
    public void findLordWithoutPlanet() throws Exception {
        List<Lord> actualTestLordsWithoutPlanet = lordTestData.getAllTestLordsWithoutPlanet();
        List<Lord> expectedLordWithoutPlanet = lordService.findLordWithoutPlanet();
        Assert.assertEquals(expectedLordWithoutPlanet.size(), actualTestLordsWithoutPlanet.size());
    }

    @Test
    public void findAllLords() throws Exception {
        List<Lord> actualLords = lordTestData.getAllTestLords();
        List<Lord> expectedLords = lordRepository.findAll();
        Assert.assertEquals(expectedLords.size(), actualLords.size());


    }

}