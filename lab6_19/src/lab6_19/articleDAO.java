package lab6_19;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class articleDAO {

    public static List<article> findAll(int currentPage, int pageSize, String ss, String st, String od) throws Exception {
        String sql = "call article_findAll(?, ?, ?, ?, ?)";
        try (Connection connection = DB.getConnection("bbs2");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (currentPage - 1) * pageSize); // firstRecordIndex
            statement.setInt(2, pageSize);                     // pageSize
            statement.setString(3, ss);                        // 조회 방법
            statement.setString(4, st);                        // 검색 문자열
            statement.setString(5, od);                        // 정렬 순서
            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<article> list = new ArrayList<article>();
                while (resultSet.next()) {
                    article article = new article();
                    article.setId(resultSet.getInt("id"));
                    article.setUserId(resultSet.getInt("userId"));
                    article.setBoardId(resultSet.getInt("boardId"));
                    article.setNo(resultSet.getInt("no"));
                    article.setWriteTime(resultSet.getTimestamp("writeTime"));
                    article.setTitle(resultSet.getString("title"));
                    article.setBody(resultSet.getString("body"));
                    article.setNotice(resultSet.getBoolean("notice"));
                    article.setName(resultSet.getString("name"));
                    article.setBoardName(resultSet.getString("boardName"));
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
                    article.setUserId(resultSet.getInt("userId"));
                    article.setBoardId(resultSet.getInt("boardId"));
                    article.setNo(resultSet.getInt("no"));
                    article.setWriteTime(resultSet.getTimestamp("writeTime"));
                    article.setTitle(resultSet.getString("title"));
                    article.setBody(resultSet.getString("body"));
                    article.setNotice(resultSet.getBoolean("notice"));
                  
                    return article;
                }
            }
            return null;
        }
    }

    public static void update(article article) throws Exception {
        String sql = "UPDATE article SET title=?, body=?, notice=?, userId=?" +
                     " WHERE id = ?";
        try (Connection connection = DB.getConnection("bbs2");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getBody());
            statement.setBoolean(3, article.isNotice());
            statement.setInt(4, article.getUserId());
            statement.setInt(5, article.getId());
            statement.executeUpdate();
        }
    }

    public static void insert(article article) throws Exception {
        String sql = "INSERT article (userId, boardId, no, title, body, notice, writeTime)" +
                     " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DB.getConnection("bbs2");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, article.getUserId());
            statement.setInt(2, article.getBoardId());
            statement.setInt(3, article.getNo());
            statement.setString(4, article.getTitle());
            statement.setString(5, article.getBody());
            statement.setBoolean(6, article.isNotice());
            statement.setTimestamp(7, article.getWriteTime());
            statement.executeUpdate();
        }
    }

    public static void delete(int id) throws Exception {
        String sql = "DELETE FROM article WHERE id = ?";
        try (Connection connection = DB.getConnection("article1");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}

