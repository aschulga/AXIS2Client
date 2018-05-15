package alexshulga.view.book;

import alexshulga.controller.ServiceHandler;
import com.doszhan.CustomServiceStub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class DialogCreateBook {

    private JDialog dialog = new JDialog();
    private ServiceHandler serviceHandler;

    public DialogCreateBook(ServiceHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }

    public void create(){

        dialog.setSize(400,500);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(null);

        JLabel lastNameLabel = new JLabel("Фамилия: ");
        JTextField lastNameTextField = new JTextField(10);

        dialog.add(lastNameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(lastNameTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel surNameLabel = new JLabel("Имя: ");
        JTextField surNameTextField = new JTextField(10);

        dialog.add(surNameLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(surNameTextField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel titleLabel = new JLabel("Название: ");
        JTextField titleTextField = new JTextField(10);

        dialog.add(titleLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(titleTextField, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JButton addNotation = new JButton("Добавить запись");

        dialog.add(addNotation, new GridBagConstraints(0, 6, 2, 1, 2, 2,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.pack();
        dialog.setVisible(true);


        addNotation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((lastNameTextField.getText()).trim().isEmpty() || (surNameTextField.getText()).trim().isEmpty() ||
                        (titleTextField.getText()).trim().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Одно или несколько полей не заполнены");
                    return;
                } else {
                    try
                    {
                        CustomServiceStub.Author author = new CustomServiceStub.Author();
                        author.setFirstname(lastNameTextField.getText());
                        author.setLastname(surNameTextField.getText());

                        CustomServiceStub.Book book = new CustomServiceStub.Book();
                        book.setAuthor(author);
                        book.setTitle(titleTextField.getText());
                        serviceHandler.addBook(book);

                        JOptionPane.showMessageDialog(dialog, "Запись успешно добавлена. Для продолжения работы нажмите \"ОК\"");
                        dialog.dispose();
                    }
                    catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
