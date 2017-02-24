package dao;

import entity.BackupBorrowRecord;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * BackupBorrowRecordDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>һ�� 3, 2017</pre>
 */
public class BackupBorrowRecordDaoTest {

    @Before
    public void before() throws Exception {
        System.out.println("[JUnit] BackupBorrowRecordDao Test Begin");
    }

    @After
    public void after() throws Exception {
        System.out.println("[JUnit] BackupBorrowRecordDao Test End");
    }

    /**
     * Method: borrowBackup(BackupBorrowRecord record)
     */
    @Test
    public void testBorrowBackup() throws Exception {
        BackupBorrowRecord record = new BackupBorrowRecord();
        record.setUserId(1);
        record.setEquipmentId(1);
        record.setBackupId(1);
        record.setBorrowDate(new Timestamp(System.currentTimeMillis()));
        record.setReturnDate(new Timestamp(System.currentTimeMillis()));
        assertEquals(200, BackupBorrowRecordDao.borrowBackup(record));
    }

    /**
     * Method: getBorrowBorrowRecordListByBackupId(int backupId)
     */
    @Test
    public void testGetBorrowBorrowRecordListByBackupId() throws Exception {

        assertEquals(-1, getBorrowBorrowRecordListByBackupId(113));
        assertEquals(1, getBorrowBorrowRecordListByBackupId(1));
    }

    private int getBorrowBorrowRecordListByBackupId(int backupId){
        ArrayList<BackupBorrowRecord> list = BackupBorrowRecordDao.getBorrowBorrowRecordListByBackupId(backupId);
        if(list.size() != 0){
            return 1;
        }else {
            return -1;
        }
    }

    /**
     * Method: getBorrowBorrowRecordListByStaffId(int staffId)
     */
    @Test
    public void testGetBorrowBorrowRecordListByStaffId() throws Exception {
        assertEquals(1, getBorrowRecordListByStaffId(1));
        assertEquals(-1, getBorrowRecordListByStaffId(4));
    }

    private int getBorrowRecordListByStaffId(int staffId){
        ArrayList<BackupBorrowRecord> list = BackupBorrowRecordDao.getBorrowBorrowRecordListByStaffId(staffId);
        if(list.size() != 0){
            return 1;
        }else {
            return -1;
        }
    }

    /**
     * Method: getUserBackupById(int userId, int backupId)
     */
    @Test
    public void testGetUserBackupById() throws Exception {
        BackupBorrowRecord record = BackupBorrowRecordDao.getUserBackupById(1, 3);
        assertEquals(null, record);
    }

    /**
     * Method: getSetupBackup(int userId, int equipmentId)
     */
    @Test
    public void testGetSetupBackup() throws Exception {
        BackupBorrowRecord record = BackupBorrowRecordDao.getUserBackupById(1, 4);
        assertEquals(null, record);
    }

    /**
     * Method: getBackupDate(int backupId)
     */
    @Test
    public void testGetBackupDate() throws Exception {

        Timestamp[] timestamps = BackupBorrowRecordDao.getBackupDate(2);
        assertEquals(null, timestamps[0]);
        assertEquals(null, timestamps[1]);
    }

    /**
     * Method: getOwnBackup(int userId)
     */
    @Test
    public void testGetOwnBackup() throws Exception {
        assertEquals(-1, getOwnBackup(10000));
        assertEquals(1, getOwnBackup(1));
    }

    private int getOwnBackup(int userId){
        ArrayList<BackupBorrowRecord> list = BackupBorrowRecordDao.getOwnBackup(userId);
        if(list != null && list.size() > 0){
            return 1;
        }else {
            return -1;
        }
    }


} 
