package foundation_project.util;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {
        Connection conn = ConnectionUtil.getConnection();
        Connection conn2 = ConnectionUtil.getConnection();
    }
}