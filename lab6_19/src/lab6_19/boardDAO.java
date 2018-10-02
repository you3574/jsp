package lab6_19;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lab6_19.DB;

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
                board.setBoardName(resultSet.getString("boardName"));
                list.add(board);
            }
            return list;
        }
    }
}
