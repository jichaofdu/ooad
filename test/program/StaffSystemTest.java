package program;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * StaffSystem Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>һ�� 3, 2017</pre>
 */
public class StaffSystemTest {

    @Before
    public void before() throws Exception {
        System.out.println("[JUnit] StaffSystem Test Begin");
    }

    @After
    public void after() throws Exception {
        System.out.println("[JUnit] StaffSystem Test End");
    }

    /**
     * Method: launch()
     */
    @Test
    public void testLaunch() throws Exception {
        System.out.println("[JUnit] Begin: ManagerSystemTest - launch()");
        StaffSystem ms = new StaffSystem();
        ByteArrayInputStream in = new ByteArrayInputStream("Q".getBytes());
        System.setIn(in);
        int result = ms.launch();
        assertEquals(0,result);
        System.out.println("[JUnit] End: ManagerSystemTest - launch()");
    }

    /**
     * Method: getSelectionMode()
     */
    @Test
    public void testGetSelectionMode() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: isInteger(String str)
     */
    @Test
    public void testIsInteger() throws Exception {
        System.out.println("[JUnit] Begin: StaffSystemTest - isInteger(Stirng str)");
        assertFalse(StaffSystem.isInteger("123random"));
        assertFalse(StaffSystem.isInteger("a"));
        assertFalse(StaffSystem.isInteger("random123"));
        assertFalse(StaffSystem.isInteger(".9"));
        assertFalse(StaffSystem.isInteger("1.0"));
        assertFalse(StaffSystem.isInteger("1.0f"));
        assertTrue(StaffSystem.isInteger("1123"));
        System.out.println("[JUnit] End: StaffSystemTest - isInteger(Stirng str)");
    }


    /**
     * Method: viewMyBorrowEquipmentRecord()
     */
    @Test
    public void testViewMyBorrowEquipmentRecord() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = StaffSystem.getClass().getMethod("viewMyBorrowEquipmentRecord"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: viewMyBorrowBackupRecord()
     */
    @Test
    public void testViewMyBorrowBackupRecord() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = StaffSystem.getClass().getMethod("viewMyBorrowBackupRecord"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: borrowEquipment()
     */
    @Test
    public void testBorrowEquipment() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = StaffSystem.getClass().getMethod("borrowEquipment"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: returnEquipment()
     */
    @Test
    public void testReturnEquipment() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = StaffSystem.getClass().getMethod("returnEquipment"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: borrowBackup()
     */
    @Test
    public void testBorrowBackup() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = StaffSystem.getClass().getMethod("borrowBackup"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: returnBackup()
     */
    @Test
    public void testReturnBackup() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = StaffSystem.getClass().getMethod("returnBackup"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: setupBackup()
     */
    @Test
    public void testSetupBackup() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = StaffSystem.getClass().getMethod("setupBackup"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: removeBackup()
     */
    @Test
    public void testRemoveBackup() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = StaffSystem.getClass().getMethod("removeBackup"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: viewMyHold()
     */
    @Test
    public void testViewMyHold() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = StaffSystem.getClass().getMethod("viewMyHold"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: viewEquipmentListByKeyWords()
     */
    @Test
    public void testViewEquipmentListByKeyWords() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = StaffSystem.getClass().getMethod("viewEquipmentListByKeyWords"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: viewBackupListByKeyWords()
     */
    @Test
    public void testViewBackupListByKeyWords() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = StaffSystem.getClass().getMethod("viewBackupListByKeyWords"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
