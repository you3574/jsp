package F201532029;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import F201532029.DB;

public class categoryDAO {

    public static List<Category> findAll() throws Exception {
        String sql = "SELECT * FROM Category" ;
        try (Connection connection = DB.getConnection("book2");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ArrayList<Category> list = new ArrayList<Category>();
            while (resultSet.next()) {
                Category Category = new Category();
                Category.setId(resultSet.getInt("id"));
                Category.setCategoryName(resultSet.getString("categoryName"));
                list.add(Category);
            }
            return list;
        }
    }
}
