package p201532029;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import p201532029.DB;

public class articleDAO {

    public static List <article> findAll(int currentPage, int pageSize, String ss, String st, String od) 
    throws Exception {
        String sql = "call article_findAll(?, ?, ?, ?, ?) ";
        try (Connection connection = DB.getConnection("bbs2");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (currentPage - 1) * pageSize); // firstRecordIndex
            statement.setInt(2, pageSize);                     // pageSize
            statement.setString(3, ss);// 조회 방법 
            statement.setString(4, st );                  // 검색 문자열
            statement.setString(5, od);                        // 정렬 순서
            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<article> list = new ArrayList<article>();
                while (resultSet.next()) {
                    article article = new article();
                    article.setId(resultSet.getInt("id"));
                    article.setNo(resultSet.getInt("no"));
                    article.setBoardName(resultSet.getString("boardName"));
                    article.setName(resultSet.getString("name"));
                    article.setWriteTime(resultSet.getInt("writeTime"));
                    article.setTitle(resultSet.getString("title"));
                    list.add(article);
                }
                return list;
            }
        }
    }

    public static int count(String ss, String st) throws Exception {
        String sql = "call article_count(?, ?)";
        try (Connection connection = DB.getConnection("bbs2");
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
    public static article findOne(int id) throws Exception {
        String sql = "SELECT * FROM article WHERE id=?";
        try (Connection connection = DB.getConnection("bbs2");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    article article = new article();
                    article.setId(resultSet.getInt("id"));
                    article.setNo(resultSet.getInt("no"));
                    article.setBoardName(resultSet.getString("boardName"));
                    article.setName(resultSet.getString("name"));
                    article.setWriteTime(resultSet.getInt("writeTime"));
                    article.setTitle(resultSet.getString("title"));
                    return article;
                }
            }
            return null;
        }
    }

    public static void update(article article) throws Exception {
        String sql = "UPDATE article SET no=?, boardName=?, name=?, writeTime=?, title=? " +
                     " WHERE id = ?";
        try (Connection connection = DB.getConnection("bbs2");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, article.getNo());
            statement.setString(2, article.getBoardName());
            statement.setString(3, article.getName());
            statement.setInt(4, article.getWriteTime());
            statement.setString(5, article.getTitle());
            statement.executeUpdate();
        }
    }

    public static void insert(article article) throws Exception {
        String sql = "INSERT article (no, boardName, name, writeTime, title)" +
                     " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DB.getConnection("bbs2");
             PreparedStatement statement = connection.prepareStatement(sql)) {
        	statement.setInt(1, article.getNo());
            statement.setString(2, article.getBoardName());
            statement.setString(3, article.getName());
            statement.setInt(4, article.getWriteTime());
            statement.setString(5, article.getTitle());
            statement.executeUpdate();
        }
    }

    public static void delete(int id) throws Exception {
        String sql = "DELETE FROM article WHERE id = ?";
        try (Connection connection = DB.getConnection("bbs2");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
