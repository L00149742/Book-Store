package ie.lyit.serialize;
import java.io.*;
import java.util.*;
import ie.lyit.book.*;

public class BookSerializer {
	private ArrayList<Book> books;
	final String FILENAME = "books.ser";
	
	public BookSerializer()
	{
		books = new ArrayList<Book>();
	}
	
	public void add()
	{
		Book book = new Book();
		book.read();
		books.add(book);
	}
	
	public void serializeBooks()
	{
		ObjectOutputStream os = null;
		try
		{
			FileOutputStream fileStream = new FileOutputStream(FILENAME);
			os = new ObjectOutputStream(fileStream);
			os.writeObject(books);
		}
		catch(FileNotFoundException fnfE)
		{
			 System.out.println("Cannot creat file");
		}
		catch(IOException ioE)
		{
			 System.out.println(ioE.getMessage());
		}
		finally
		{
			try
			{
				os.close();
			}
		catch(IOException ioE)
		{
			 System.out.println(ioE.getMessage());
		}
		}
		
	}
	
	public void deserializeBooks()
	{
		ObjectInputStream is=null;
		try
		{
			FileInputStream fileStream = new FileInputStream(FILENAME);
			is = new ObjectInputStream(fileStream);
			books = (ArrayList<Book>)is.readObject();
			// get the number of last number in ArrayList
			// set static to that number + !
		}
		catch(FileNotFoundException fnfE)
		{
			System.out.println(fnfE.getMessage());
		}
		catch(IOException ioE)
		{
			 System.out.println(ioE.getMessage());
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try
			{
				is.close();
			}
		catch(IOException ioE)
		{
			 System.out.println(ioE.getMessage());
		}
		}
	}
	
	public void list() 
	{
		for(Book tempBook:books)
			System.out.println(tempBook);
	}
	
	public Book view()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("ENTER LIBRARY NUMBER OF BOOK: ");
		int bookToView = keyboard.nextInt();
		
		for(Book tmpBook : books)
		{
			if (tmpBook.getLibraryNumber() == bookToView)
			{
				System.out.println(tmpBook);
				return tmpBook;
			}
		}
		return null;	
	}
	
	public void delete()
	{
		Book bookToDelete = view();
		
		if (bookToDelete != null)
			books.remove(bookToDelete);
	}
	
	public void edit()
	{
		Book bookToEdit = view();
		
		if (bookToEdit != null)
		{
			int index = books.indexOf(bookToEdit);
			bookToEdit.read();
			books.set(index, bookToEdit);
		}
	}

}
