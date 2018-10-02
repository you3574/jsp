package p201532029;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import p201532029.DB;

public class boardDAO {

    public static List<board> findAll() throws Exception {
        String sql = "SELECT * FROM board";
        try (Connection connection = DB.getConnection("bbs2");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ArrayList<board> list = new ArrayList<board>();
            while (resultSet.next()) {
                board board = new board();
                board.setId(resultSet.getInt("id"));
                board.setBoradName(resultSet.getString("boradName"));
                list.add(board);
            }
            return list;
        }
    }
}
