package lab2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lab2.DB;

public class departmentDAO {

    public static List<department> findAll() throws Exception {
        String sql = "SELECT * FROM department ";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ArrayList<department> list = new ArrayList<department>();
            while (resultSet.next()) {
                department department = new department();
                department.setId(resultSet.getInt("id"));
                department.setDepartmentName(resultSet.getString("departmentName"));
                list.add(department);
            }
            return list;
        }
    }
}
