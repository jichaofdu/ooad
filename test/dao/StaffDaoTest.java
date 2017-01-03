package dao;

import entity.Staff;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/** 
* StaffDao Tester. 
* 
* @author <Authors name> 
* @since <pre>һ�� 3, 2017</pre> 
* @version 1.0 
*/ 
public class StaffDaoTest { 

    @Before
    public void before() throws Exception {
        System.out.println("StaffDaoTest Begin");
    }

    @After
    public void after() throws Exception {
        System.out.println("StaffDaoTest End");
    }

    /**
    *
    * Method: getStaffById(int id)
    *
    */
    @Test
    public void testGetStaffById() throws Exception {
        //1.测试正常情况
        Staff staff = StaffDao.getStaffById(1);
        assertTrue(staff.getId() >= 0);
        assertTrue(staff.getName() != null);
        //2.测试不存在情况
        Staff staffNotExist = StaffDao.getStaffById(1000);
        assertTrue(staffNotExist == null);
    }

    /**
    *
    * Method: getStaffList()
    *
    */
    @Test
    public void testGetStaffList() throws Exception {
        //1.测试正常情况
        ArrayList<Staff> staffs = StaffDao.getStaffList();
        assertTrue(staffs.size() > 0);
        //2.没有异常情况
    }

    /**
    *
    * Method: insertStaff(Staff newStaff)
    *
    */
    @Test
    public void testInsertStaff() throws Exception {
        Staff newStaff = new Staff();
        newStaff.setName("JUNIT Testing");
        int result = StaffDao.insertStaff(newStaff);
        assertEquals(StaffDao.INSERT_SUCCESS,result);
    }


} 
