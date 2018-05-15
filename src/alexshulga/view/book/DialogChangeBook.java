package alexshulga.view.book;

import alexshulga.controller.ServiceHandler;
import com.doszhan.CustomServiceStub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class DialogChangeBook {

    private JDialog dialog = new JDialog();
    private ServiceHandler serviceHandler;

    public DialogChangeBook(ServiceHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }

    public void change(){

        dialog.setSize(400,500);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(null);

        JLabel numberLabel = new JLabel("Номер записи: ");
        JTextField numberTextField = new JTextField(10);

        dialog.add(numberLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(numberTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel lastNameLabel = new JLabel("Фамилия: ");
        JTextField lastNameTextField = new JTextField(10);

        dialog.add(lastNameLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(lastNameTextField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel surNameLabel = new JLabel("Имя: ");
        JTextField surNameTextField = new JTextField(10);

        dialog.add(surNameLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(surNameTextField, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel titleLabel = new JLabel("Название: ");
        JTextField titleTextField = new JTextField(10);

        dialog.add(titleLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(titleTextField, new GridBagConstraints(1, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JButton changeNotation = new JButton("Изменить запись");

        dialog.add(changeNotation, new GridBagConstraints(0, 6, 2, 1, 2, 2,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.pack();
        dialog.setVisible(true);


        changeNotation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numberTextField.getText().trim().isEmpty() || surNameLabel.getText().trim().isEmpty() ||
                        lastNameLabel.getText().trim().isEmpty() || titleLabel.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Одно или несколько полей не заполнены");
                    return;
                } else {
                     CustomServiceStub.Author author = new CustomServiceStub.Author();
                        author.setFirstname(lastNameTextField.getText());
                        author.setLastname(surNameTextField.getText());

                        CustomServiceStub.Book book = new CustomServiceStub.Book();
                        book.setAuthor(author);
                        book.setTitle(titleTextField.getText());
                    try {
                        serviceHandler.changeBook(Integer.parseInt(numberTextField.getText()), book);
                        JOptionPane.showMessageDialog(dialog, "Запись успешно изменена. Для продолжения работы нажмите \"ОК\"");
                        dialog.dispose();
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
