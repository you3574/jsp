package lab6_19;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lab6_19.DB;

public class userDAO {

    public static List<user> findAll() throws Exception {
        String sql = "SELECT * FROM user";
        try (Connection connection = DB.getConnection("bbs2");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ArrayList<user> list = new ArrayList<user>();
            while (resultSet.next()) {
                user user = new user();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                list.add(user);
            }
            return list;
        }
    }
}
