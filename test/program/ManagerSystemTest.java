package program;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by chaoj on 2017/1/2.
 */
public class ManagerSystemTest {
    @Before
    public void setUp() throws Exception {
        System.out.println("[JUnit] ManagerSystem Test Begin");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("[JUnit] ManagerSystem Test End");
    }

    @Test
    public void testLaunch() throws Exception {
        System.out.println("[JUnit] Begin: ManagerSystemTest - launch()");
        ManagerSystem ms = new ManagerSystem();
        ByteArrayInputStream in = new ByteArrayInputStream("Q".getBytes());
        System.setIn(in);
        int result = ms.launch();
        assertEquals(0,result);
        System.out.println("[JUnit] End: ManagerSystemTest - launch()");
    }

    @Test
    public void testViewEquipmentList() throws Exception{
        System.out.println("[JUnit] Begin: ManagerSystemTest - viewEquipmentList()");
        ManagerSystem ms = new ManagerSystem();
        Class msClass = ms.getClass();
        Method method = msClass.getDeclaredMethod("viewEquipmentList");
        method.setAccessible(true);
        int value = (int)method.invoke(ms);
        assertTrue(value >= 0);
        System.out.println("[JUnit] End: ManagerSystemTest - viewEquipmentList()");
    }

    @Test
    public void testViewBackupList() throws Exception{
        System.out.println("[JUnit] Begin: ManagerSystemTest - viewBackupList()");
        ManagerSystem ms = new ManagerSystem();
        Class msClass = ms.getClass();
        Method method = msClass.getDeclaredMethod("viewBackupList");
        method.setAccessible(true);
        int value = (int)method.invoke(ms);
        assertTrue(value >= 0);
        System.out.println("[JUnit] End: ManagerSystemTest - viewBackupList()");
    }

    @Test
    public void testAddEquipment() throws Exception{
        System.out.println("[JUnit] Begin: ManagerSystemTest - addEquipment()");
        //1.测试按Q返回的情况
        ManagerSystem ms = new ManagerSystem();
        Class msClass = ms.getClass();
        Method method = msClass.getDeclaredMethod("addEquipment");
        method.setAccessible(true);
        ByteArrayInputStream in = new ByteArrayInputStream("Q".getBytes());
        System.setIn(in);
        int value = (int)method.invoke(ms);
        assertEquals(-1,value);
        //2.测试正常输入的情况
        ManagerSystem ms2 = new ManagerSystem();
        Class msClass2 = ms.getClass();
        Method method2 = msClass.getDeclaredMethod("addEquipment");
        method2.setAccessible(true);
        ByteArrayInputStream in2 = new ByteArrayInputStream("JUNIT Test".getBytes());
        System.setIn(in2);
        int value2 = (int)method2.invoke(ms);
        assertEquals(0,value2);
        System.out.println("[JUnit] End: ManagerSystemTest - addEquipment()");
    }

    public void testScrapeEquipment() throws Exception{

    }

    @Test
    public void testIsInteger() throws Exception {
        System.out.println("[JUnit] Begin: ManagerSystemTest - isInteger(Stirng str)");
        assertFalse(ManagerSystem.isInteger("123random"));
        assertFalse(ManagerSystem.isInteger("a"));
        assertFalse(ManagerSystem.isInteger("random123"));
        assertFalse(ManagerSystem.isInteger(".9"));
        assertFalse(ManagerSystem.isInteger("1.0"));
        assertFalse(ManagerSystem.isInteger("1.0f"));
        assertTrue(ManagerSystem.isInteger("1123"));
        System.out.println("[JUnit] End: ManagerSystemTest - isInteger(Stirng str)");
    }

}