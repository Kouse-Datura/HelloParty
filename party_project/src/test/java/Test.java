import edu.gduf.utils.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Demo class
 *
 * @author 古市
 * @date 2020/02/02 14:38
 */
public class Test {
    @org.junit.Test
    public void testAdd(){
        Connection[]connections = new Connection[5];
        for (int i = 0; i < 5; i++) {
            try {
                connections[i] = DruidUtil.getConnection();
                String sql = "select * from student;";
                PreparedStatement ps = connections[i].prepareStatement(sql);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 5; i++) {
            DruidUtil.close(null, connections[i]);
        }
    }

}
