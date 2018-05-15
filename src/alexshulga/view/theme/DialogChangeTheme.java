package alexshulga.view.theme;

import alexshulga.controller.ServiceHandler;
import com.doszhan.CustomServiceStub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class DialogChangeTheme {
    private JDialog dialog = new JDialog();
    private ServiceHandler serviceHandler;

    public DialogChangeTheme(ServiceHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }

    public void change(int index){

        dialog.setSize(400,500);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(null);

        JLabel numberLabel = new JLabel("Номер записи: ");
        JTextField numberTextField = new JTextField(10);

        dialog.add(numberLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(numberTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel themeLabel = new JLabel("Тема: ");
        JTextField themeTextField = new JTextField(10);

        dialog.add(themeLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(themeTextField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel contentLabel = new JLabel("Содержание: ");
        JTextField contentTextField = new JTextField(10);

        dialog.add(contentLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(contentTextField, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JButton changeNotation = new JButton("Изменить запись");

        dialog.add(changeNotation, new GridBagConstraints(0, 6, 2, 1, 2, 2,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.pack();
        dialog.setVisible(true);


        changeNotation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numberTextField.getText().trim().isEmpty() || themeLabel.getText().trim().isEmpty() ||
                        contentLabel.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Одно или несколько полей не заполнены");
                    return;
                }
                else {
                    try
                    {
                        CustomServiceStub.Theme theme = new CustomServiceStub.Theme();
                        theme.setTitle(themeTextField.getText());
                        theme.setContent(contentTextField.getText());

                        serviceHandler.changeTheme(index, Integer.parseInt(numberTextField.getText()), theme);
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
