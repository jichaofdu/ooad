package dao;

import entity.Backup;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import java.sql.Timestamp;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/** 
* BackupDao Tester. 
* 
* @author <Authors name> 
* @since <pre>һ�� 3, 2017</pre> 
* @version 1.0 
*/ 
public class BackupDaoTest { 

    @Before
    public void before() throws Exception {
        System.out.println("BackupDaoTest Begin");
    }

    @After
    public void after() throws Exception {
        System.out.println("BackupDaoTest End");
    }

    /**
    *
    * Method: saveOrUpdateBackup(Backup backup)
    *
    */
    @Test
    public void testSaveOrUpdateBackup() throws Exception {
        Backup newBackup = new Backup();
        newBackup.setName("JUNIT Testing");
        newBackup.setPurchaseDate(new Timestamp(System.currentTimeMillis()));
        int result = BackupDao.saveOrUpdateBackup(newBackup);
        assertEquals(BackupDao.SAVEORUPDATE_SUCCESS,result);
    }

    /**
    *
    * Method: getBackupById(int id)
    *
    */
    @Test
    public void testGetBackupById() throws Exception {
        //1.测试正常情况
        Backup backup = BackupDao.getBackupById(1);
        assertTrue(backup.getId() >= 0);
        assertTrue(backup.getName() != null);
        assertTrue(backup.getPurchaseDate() != null);
        //2.测试不存在情况
        Backup backupNotExist = BackupDao.getBackupById(10000);
        assertTrue(backupNotExist == null);
    }

    /**
    *
    * Method: getBackupListByKeyWords(String keywords)
    *
    */
    @Test
    public void testGetBackupListByKeyWords() throws Exception {
        //1.测试正常情况
        ArrayList<Backup> backups = BackupDao.getBackupListByKeyWords("冀超");
        assertTrue(backups.size() > 0);
        //2.测试异常情况
        ArrayList<Backup> backupsNotExist = BackupDao.getBackupListByKeyWords("sdhssfajdkaljdqwd");
        assertTrue(backupsNotExist.size() == 0);
    }

    /**
    *
    * Method: getBackupList()
    *
    */
    @Test
    public void testGetBackupList() throws Exception {
        //1.测试正常情况
        ArrayList<Backup> backups = BackupDao.getBackupList();
        assertTrue(backups.size() > 0);
        //2.没有异常情况
    }


} 
