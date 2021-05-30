package services;

import java.util.Set;

import model.Book;

public interface XMLservice {

	void storeXML(Set<Book> books) throws Exception;

}
