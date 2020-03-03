import edu.gduf.dao.StudentClassDao;
import edu.gduf.domain.StudentClass;
import edu.gduf.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-03-01 11:38
 **/
public class StudentClassDaoTest {

    StudentClassDao dao;
    SqlSession session;

    /**
     * 初始化操作，
     * 获取session和StudentClassDao的代理对象
     */
    @Before
    public void init(){
        session = MyBatisUtil.getFactory().openSession();
        dao = session.getMapper(StudentClassDao.class);
    }

    /**
     * 关闭session，
     * 释放资源
     */
    @After
    public void destroy(){
        session.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void test(){
        List<StudentClass> lists = dao.findAll();
        for (StudentClass list : lists) {
            System.out.println(list);
        }
    }

}
