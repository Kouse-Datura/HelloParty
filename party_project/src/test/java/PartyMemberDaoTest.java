import edu.gduf.dao.InspectorDao;
import edu.gduf.dao.PartyMemberDao;
import edu.gduf.domain.PartyMember;
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
 * @date 2020-03-03 16:20
 **/
public class PartyMemberDaoTest {
    PartyMemberDao dao;
    SqlSession session;

    /**
     * 初始化操作，
     * 获取session和相应的dao代理对象
     */
    @Before
    public void init(){
        session = MyBatisUtil.getFactory().openSession();
        dao = session.getMapper(PartyMemberDao.class);
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
     * 查询所有
     */
    @Test
    public void test(){
        List<PartyMember> members = dao.findAll();
        for (PartyMember member : members) {
            System.out.println(member);
        }
    }

}
