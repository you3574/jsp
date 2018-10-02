package M201532029;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import M201532029.DB;

public class productDAO2 {

    public static List<product> findAll(int currentPage, int pageSize) throws Exception {
        String sql = "SELECT p.*, c.name " + 
                     " FROM product p LEFT JOIN category c ON p.categoryId = c.id " + 
                     " LIMIT ?, ? ";
        try (Connection connection = DB.getConnection("product");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (currentPage - 1) * pageSize); //현재페이지 시작점계산
            statement.setInt(2, pageSize);//레코드 수 계산
            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<product> list = new ArrayList<product>();
                while (resultSet.next()) {
                	 product product = new product();
 	                product.setId(resultSet.getInt("id"));
 	                product.setTitle(resultSet.getString("title"));
 	                product.setCategoryId(resultSet.getInt("categoryId"));
 	                product.setUnitCost(resultSet.getInt("unitCost"));
 	                product.setQuantity(resultSet.getInt("quantity"));
 	                product.setName(resultSet.getString("Name"));
 	                list.add(product);
                }
                return list;
            }
        }
    }

    public static int count() throws Exception {
        String sql = "SELECT COUNT(*) FROM product";
        try (Connection connection = DB.getConnection("product");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return resultSet.getInt(1);
        }
        return 0;
    }
}
