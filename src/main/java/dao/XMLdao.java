package dao;

import java.sql.SQLException;

public interface XMLdao {

	void storeBooksAuthors(int bookId, int authorId) throws SQLException;

	void storeAuthors(int id, String name) throws SQLException;

	void storeBooks(int id, String title, int price) throws SQLException;

}
