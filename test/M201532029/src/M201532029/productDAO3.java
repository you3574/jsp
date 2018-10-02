package M201532029;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import M201532029.DB;

public class productDAO3 {
    public static category createcategory(ResultSet resultSet) throws SQLException {
    	  category category = new category();
         category.setId(resultSet.getInt("id"));
          category.setName(resultSet.getString("name"));
    	 // category.setP_id(resultSet.getInt("id"));
          category.setTitle(resultSet.getString("title"));
          category.setUnitCost(resultSet.getInt("unitCost"));
          category.setQuantity(resultSet.getInt("quantity"));
          return category;
    }

    public static List<category> findAll() throws Exception {
        String sql = "select p.id, c.name, p.title, p.unitCost, p.quantity from category c right join  product p on c.id=p.categoryId";
        try (Connection connection = DB.getConnection("product");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ArrayList<category> list = new ArrayList<category>();
            while (resultSet.next())
                list.add(createcategory(resultSet));
            return list;
        }
    }

 

    public static List<category> findBycategoryId(int id) throws Exception {
        String sql = "select c.name, p.id, p.title, p.unitCost, p.quantity from category c right join  product p on c.id=p.categoryId where p.categoryId = ?";
        try (Connection connection = DB.getConnection("product");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<category> list = new ArrayList<category>();
                while (resultSet.next())
                    list.add(createcategory(resultSet));
                return list;
            }
        }
    }
}
