package lab6_19;
import java.sql.Timestamp;
public class article {
int id;
int boardId;
int userId;
int no;
Timestamp writeTime;
String title;
String body;
boolean notice;
String boardName;
String Name;

public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public String getBoardName() {
	return boardName;
}
public void setBoardName(String boardName) {
	this.boardName = boardName;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getBoardId() {
	return boardId;
}
public void setBoardId(int boardId) {
	this.boardId = boardId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getNo() {
	return no;
}
public void setNo(int no) {
	this.no = no;
}
public Timestamp getWriteTime() {
	return writeTime;
}
public void setWriteTime(Timestamp writeTime) {
	this.writeTime = writeTime;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}
public boolean isNotice() {
	return notice;
}
public void setNotice(boolean notice) {
	this.notice = notice;
}

}
