package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utility.DBConnection;

public class XMLdaoImpl implements XMLdao {

	@Override
	public void storeBooksAuthors(int bookId, int authorId) throws SQLException {
		// TODO Auto-generated method stub

		Connection connection = DBConnection.getConnection();

		String sql = "INSERT INTO books_authors(book_id,author_id) values (?, ?)";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, bookId);
		preparedStatement.setInt(2, authorId);
		preparedStatement.executeUpdate();

		preparedStatement.close();
		connection.close();

	}

	@Override
	public void storeAuthors(int id, String name) throws SQLException {
		// TODO Auto-generated method stub

		Connection connection = DBConnection.getConnection();

		String sql = "INSERT INTO authors values (?, ?)";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, name);
		preparedStatement.executeUpdate();

		preparedStatement.close();
		connection.close();

	}

	@Override
	public void storeBooks(int id, String title, int price) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = DBConnection.getConnection();

		String sql = "INSERT INTO books values (?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, title);
		preparedStatement.setInt(3, price);
		preparedStatement.executeUpdate();

		preparedStatement.close();
		connection.close();

	}

}
