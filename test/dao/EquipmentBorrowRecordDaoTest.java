package dao;

import entity.EquipmentBorrowRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * EquipmentBorrowRecordDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>һ�� 3, 2017</pre>
 */
public class EquipmentBorrowRecordDaoTest {

    @Before
    public void before() throws Exception {
        System.out.println("[JUnit] EquipmentBorrowRecordDao Test Begin");
    }

    @After
    public void after() throws Exception {
        System.out.println("[JUnit] EquipmentBorrowRecordDao Test End");
    }

    /**
     * Method: borrowEquipment(EquipmentBorrowRecord record)
     */
    @Test
    public void testBorrowEquipment() throws Exception {
        EquipmentBorrowRecord record = new EquipmentBorrowRecord();
        record.setUserId(1);
        record.setEquipmentId(1);
        record.setReturnDate(new Timestamp(System.currentTimeMillis()));
        record.setBorrowDate(new Timestamp(System.currentTimeMillis()));
        assertEquals(400, EquipmentBorrowRecordDao.borrowEquipment(record));
    }

    /**
     * Method: getEquipmentBorrowRecordListByStaffId(int staffId)
     */
    @Test
    public void testGetEquipmentBorrowRecordListByStaffId() throws Exception {
        assertEquals(-1, getEquipmentBorrowRecordListByStaffId(113));
        assertEquals(1, getEquipmentBorrowRecordListByStaffId(1));
    }

    private int getEquipmentBorrowRecordListByStaffId(int staffId){
        ArrayList<EquipmentBorrowRecord> list = EquipmentBorrowRecordDao.getEquipmentBorrowRecordListByStaffId(staffId);
        if(list != null && list.size() != 0){
            return 1;
        }else {
            return -1;
        }
    }

    /**
     * Method: getEquipmentBorrowRecordListByEquipmentId(int equipmentId)
     */
    @Test
    public void testGetEquipmentBorrowRecordListByEquipmentId() throws Exception {
        assertEquals(1, getEquipmentBorrowRecordListByEquipmentId(3));
        assertEquals(-1, getEquipmentBorrowRecordListByEquipmentId(300));
    }

    private int getEquipmentBorrowRecordListByEquipmentId(int equipmentId){
        ArrayList<EquipmentBorrowRecord> list = EquipmentBorrowRecordDao.getEquipmentBorrowRecordListByEquipmentId(equipmentId);
        if(list != null && list.size() != 0){
            return 1;
        }else {
            return -1;
        }
    }

    /**
     * Method: getEquipmentDate(int equipmentId)
     */
    @Test
    public void testGetEquipmentDate() throws Exception {
        Timestamp[] timestamps = EquipmentBorrowRecordDao.getEquipmentDate(5);
        assertEquals(null, timestamps[0]);
        assertEquals(null, timestamps[1]);
    }

    /**
     * Method: getUserEquipmentById(int userId, int equipmentId)
     */
    @Test
    public void testGetUserEquipmentById() throws Exception {
        assertEquals(null, EquipmentBorrowRecordDao.getUserEquipmentById(1, 6));
    }

    /**
     * Method: getOwnEquipment(int userId)
     */
    @Test
    public void testGetOwnEquipment() throws Exception {
        assertEquals(1, getOwnEquipment(1));
        assertEquals(-1, getOwnEquipment(1111));
    }

    private int getOwnEquipment(int userId){
        ArrayList<EquipmentBorrowRecord> list = EquipmentBorrowRecordDao.getOwnEquipment(userId);
        if(list != null && list.size() != 0){
            return 1;
        }else {
            return -1;
        }
    }


} 
