import edu.gduf.dao.StudentDao;
import edu.gduf.domain.Student;
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
 * @date 2020-02-29 20:16
 **/
public class StudentDaoTest {

    StudentDao dao;
    SqlSession session;
    @Before
    public void init(){
        session = MyBatisUtil.getFactory().openSession();
        dao = session.getMapper(StudentDao.class);
    }

    @After
    public void destroy(){
        session.close();
    }

    /**
     * 查询整个student表
     */
    @Test
    public void findAllTest(){
        List<Student> students = dao.findAll();
        for (Student student : students) {
            System.out.println(student);
        }
    }


}
