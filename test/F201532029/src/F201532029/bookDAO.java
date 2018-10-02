package F201532029;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import F201532029.DB;

public class bookDAO {

    public static List<Book> findAll() throws Exception {
        String sql = "select b.*, c.categoryName\r\n" + 
        		"from book b join category c on b.categoryId=c.id\r\n" + 
        		"order by id";
        try (Connection connection = DB.getConnection("book2");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ArrayList<Book> list = new ArrayList<Book>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setCategoryName(resultSet.getString("categoryName"));
                book.setPrice(resultSet.getInt("price"));
                book.setAvailable(resultSet.getBoolean("available"));
                list.add(book);
            }
            return list;
        }
    }
    
    public static List<Book> findAll2() throws Exception {
        String sql = "select b.*, c.categoryName , p.title publisherName\r\n" + 
        		"from book b\r\n" + 
        		"left join category c on b.categoryId=c.id\r\n" + 
        		"left join publisher p on b.publisherId=p.id\r\n" + 
        		"order by id";
        try (Connection connection = DB.getConnection("book2");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ArrayList<Book> list = new ArrayList<Book>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setCategoryName(resultSet.getString("categoryName"));
                book.setPublisherName(resultSet.getString("publisherName"));                
                book.setPrice(resultSet.getInt("price"));
                book.setAvailable(resultSet.getBoolean("available"));
                list.add(book);
            }
            return list;
        }
    }
    
    public static List<Book> findAll3(int currentPage, int pageSize) throws Exception {
        String sql = "select b.*, c.categoryName , p.title publisherName\r\n" + 
        		"from book b\r\n" + 
        		"left join category c on b.categoryId=c.id\r\n" + 
        		"left join publisher p on b.publisherId=p.id\r\n" + 
        		"order by id\r\n" + 
        		"LIMIT ?,?;";
        try (Connection connection = DB.getConnection("book2");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (currentPage - 1) * pageSize); //현재페이지 시작점계산
            statement.setInt(2, pageSize);//레코드 수 계산
            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<Book> list = new ArrayList<Book>();
                while (resultSet.next()) {
                	Book book = new Book();
                    book.setId(resultSet.getInt("id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setCategoryName(resultSet.getString("categoryName"));
                    book.setPublisherName(resultSet.getString("publisherName"));                
                    book.setPrice(resultSet.getInt("price"));
                    book.setAvailable(resultSet.getBoolean("available"));
                    list.add(book);
                }
                return list;
            }
        }
    }

    public static int count() throws Exception {
        String sql = "SELECT COUNT(*) FROM Book";
        try (Connection connection = DB.getConnection("Book2");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return resultSet.getInt(1);
        }
        return 0;
    }
    
    public static Book findOne(int id) throws Exception {
        String sql = "SELECT * FROM book WHERE id=?";
        try (Connection connection = DB.getConnection("book2");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                	Book book = new Book();
                	 book.setId(resultSet.getInt("id"));
                     book.setTitle(resultSet.getString("title"));
                     book.setAuthor(resultSet.getString("author"));
                     book.setCategoryId(resultSet.getInt("categoryId"));
                     book.setPrice(resultSet.getInt("price"));
                     book.setPublisherId(resultSet.getInt("publisherId"));
                     book.setAvailable(resultSet.getBoolean("available"));
                    return book;
                }
            }
            return null;
        }
    }
    public static void update(Book book) throws Exception {
        String sql = "UPDATE book SET title=?, author=?, categoryId=?, publisherId=?, price=?, available=? " +  
                     " WHERE id = ?"; 
        try (Connection connection = DB.getConnection("book2");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getCategoryId());
            statement.setInt(4, book.getPublisherId());
            statement.setInt(5, book.getPrice());
            statement.setBoolean(6, book.isAvailable());
            statement.setInt(7, book.getId());
            statement.executeUpdate();
        }
    }
    
    public static void delete(int id) throws Exception {
        String sql = "DELETE FROM book WHERE id = ?";
        try (Connection connection = DB.getConnection("book2");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
    
    public static List<Book> findAll4(int currentPage, int pageSize, String ss, String st) throws Exception {
        String sql = "call book_findAll(?, ?, ?, ?)";
        try (Connection connection = DB.getConnection("book2");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (currentPage - 1) * pageSize); // firstRecordIndex
            statement.setInt(2, pageSize);                     // pageSize
            statement.setString(3, ss);                        // 조회 방법
            statement.setString(4, st);                        // 검색 문자열
            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<Book> list = new ArrayList<Book>();
                while (resultSet.next()) {
                	Book book = new Book();
                    book.setId(resultSet.getInt("id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setCategoryId(resultSet.getInt("categoryId"));
                    book.setPublisherId(resultSet.getInt("publisherId"));
                    book.setCategoryName(resultSet.getString("categoryName"));
                    book.setPublisherName(resultSet.getString("publisherName"));                
                    book.setPrice(resultSet.getInt("price"));
                    book.setAvailable(resultSet.getBoolean("available"));
                    list.add(book);
                }
                return list;
            }
        }
    }

    public static int count2(String ss, String st) throws Exception {
        String sql = "call book_count(?, ?)";
        try (Connection connection = DB.getConnection("book2");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ss);  // 조회 방법
            statement.setString(2, st);  // 검색 문자열
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return resultSet.getInt(1);
            }
        }
        return 0;
    }
}
