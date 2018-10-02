package M201532029;
import M201532029.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

	public class productDAO {

	    public static List<product> findAll() throws Exception {
	        String sql = "SELECT p.*, c.categoryName " +
	                     "FROM product p LEFT JOIN category c ON p.categoryId = c.id";
	        try (Connection connection = DB.getConnection("product");
	             PreparedStatement statement = connection.prepareStatement(sql);
	             ResultSet resultSet = statement.executeQuery()) {
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

	    public static List<product> findByName(String name) throws Exception {
	    	String sql = "SELECT p.*, c.Name " +
                    "FROM product p LEFT JOIN category c ON p.categoryId = c.id " +
                    "WHERE p.title LIKE ?";
	        try (Connection connection = DB.getConnection("product");
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, name + "%");
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
	}


