package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import dao.XMLdao;
import dao.XMLdaoImpl;
import model.Author;
import model.Book;

public class XMLserviceImpl implements XMLservice {
	XMLdao xmldao = new XMLdaoImpl();
	List<Author> authorsService = new LinkedList<Author>();

	@Override
	public void storeXML(Set<Book> books) throws Exception {
		// TODO Auto-generated method stub

		for (Book book : books) {
			for (Author author : book.getAuthors()) {
				boolean flag = checkList(author.getId());
				if (flag) {
					continue;
				} else {
					authorsService.add(new Author(author.getId(), author.getName()));
				}
			}
		}

		insertBooks(books);
		insertAuthors(authorsService);
		insertBooksAuthors(books);

	}

	private boolean checkList(int i) {

		boolean flag = false;

		Iterator<Author> itr = authorsService.iterator();
		while (itr.hasNext()) {
			if (itr.next().getId() == i) {
				flag = true;
				break;
			} else {
				flag = false;
			}
		}

		return flag;

	}

	private void insertBooksAuthors(Set<Book> books) throws SQLException, Exception {
		for (Book book : books) {
			for (Author author : book.getAuthors()) {
				int bookId = book.getId();
				int authorId = author.getId();
				xmldao.storeBooksAuthors(bookId, authorId);
			}
		}

	}

	private void insertAuthors(List<Author> authorsService) throws Exception {

		for (Author author : authorsService) {
			int id = author.getId();
			String name = author.getName();
			xmldao.storeAuthors(id, name);
		}

	}

	private void insertBooks(Set<Book> books) throws Exception {

		for (Book book : books) {
			int id = book.getId();
			String title = book.getTitle();
			int price = book.getPrice();

			xmldao.storeBooks(id, title, price);
		}
	}



}
