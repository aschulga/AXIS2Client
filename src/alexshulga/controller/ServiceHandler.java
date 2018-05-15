package alexshulga.controller;

import com.doszhan.CustomServiceStub;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

public class ServiceHandler {

    private final static String END_POINT = "http://localhost:8080/axis2/services/CustomService?wsdl";

    public List<CustomServiceStub.Book> getAllBooks() throws RemoteException {
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.GetAllBooks allBooks= new CustomServiceStub.GetAllBooks();
        CustomServiceStub.GetAllBooksResponse allBooksResponse = stub.getAllBooks(allBooks);
        CustomServiceStub.Book[] books = allBooksResponse.get_return();
        if(books != null){
            return Arrays.asList(books);
        }

        return null;
    }

    public void addBook(CustomServiceStub.Book book)throws RemoteException{
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.AddBook addBook = new CustomServiceStub.AddBook();
        addBook.setBook(book);
        stub.addBook(addBook);
    }

    public void deleteBook(int index)throws RemoteException{
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.DeleteBook deleteBook = new CustomServiceStub.DeleteBook();
        deleteBook.setIndex(index-1);
        stub.deleteBook(deleteBook);
    }

    public void changeBook(int index, CustomServiceStub.Book book) throws RemoteException {
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.ChangeBook changeBook = new CustomServiceStub.ChangeBook();
        changeBook.setIndex(index-1);
        changeBook.setBook(book);
        stub.changeBook(changeBook);
    }

    public List<CustomServiceStub.Theme> getAllThemesInBock(int index) throws RemoteException {
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.GetAllThemes allThemes = new CustomServiceStub.GetAllThemes();
        allThemes.setIndex(index);
        CustomServiceStub.GetAllThemesResponse allThemesResponse = stub.getAllThemes(allThemes);
        CustomServiceStub.Theme[] themes = allThemesResponse.get_return();
        if(themes != null){
            return Arrays.asList(themes);
        }

        return null;
    }

    public void addTheme(int index, CustomServiceStub.Theme theme)throws RemoteException{
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.AddTheme addTheme = new CustomServiceStub.AddTheme();
        addTheme.setIndexInListBooks(index);
        addTheme.setTheme(theme);
        stub.addTheme(addTheme);
    }

    public void deleteTheme(int indexInListBooks, int index)throws RemoteException{
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.DeleteTheme deleteTheme = new CustomServiceStub.DeleteTheme();
        deleteTheme.setIndexInListBooks(indexInListBooks);
        deleteTheme.setIndex(index-1);
        stub.deleteTheme(deleteTheme);
    }

    public void changeTheme(int indexInListBooks, int index, CustomServiceStub.Theme theme) throws RemoteException {
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.ChangeTheme changeTheme = new CustomServiceStub.ChangeTheme();
        changeTheme.setIndexInListBooks(indexInListBooks);
        changeTheme.setIndex(index-1);
        changeTheme.setTheme(theme);
        stub.changeTheme(changeTheme);
    }
}
