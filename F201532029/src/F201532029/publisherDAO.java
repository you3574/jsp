package F201532029;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import F201532029.DB;

public class publisherDAO {

    public static List<Publisher> findAll() throws Exception {
        String sql = "SELECT * FROM publisher" ;
        try (Connection connection = DB.getConnection("book2");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ArrayList<Publisher> list = new ArrayList<Publisher>();
            while (resultSet.next()) {
                Publisher publisher = new Publisher();
                publisher.setId(resultSet.getInt("id"));
                publisher.setTitle(resultSet.getString("Title"));
                list.add(publisher);
            }
            return list;
        }
    }
}
