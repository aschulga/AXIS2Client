package alexshulga.view.theme;

import alexshulga.controller.ServiceHandler;
import com.doszhan.CustomServiceStub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class DialogCreateTheme {
    private JDialog dialog = new JDialog();
    private ServiceHandler serviceHandler;

    public DialogCreateTheme(ServiceHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }

    public void createTheme(int index){

        dialog.setSize(400,500);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(null);

        JLabel themeLabel = new JLabel("Тема: ");
        JTextField themeTextField = new JTextField(10);

        dialog.add(themeLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(themeTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel contentLabel = new JLabel("Содержание: ");
        JTextField contentTextField = new JTextField(10);

        dialog.add(contentLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(contentTextField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JButton addNotation = new JButton("Добавить запись");

        dialog.add(addNotation, new GridBagConstraints(0, 6, 2, 1, 2, 2,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.pack();
        dialog.setVisible(true);


        addNotation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((themeTextField.getText()).trim().isEmpty() || (contentTextField.getText()).trim().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Одно или несколько полей не заполнены");
                    return;
                } else {

                    try {
                        CustomServiceStub.Theme theme = new CustomServiceStub.Theme();
                        theme.setTitle(themeTextField.getText());
                        theme.setContent(contentTextField.getText());

                        serviceHandler.addTheme(index, theme);

                        JOptionPane.showMessageDialog(dialog, "Запись успешно добавлена. Для продолжения работы нажмите \"ОК\"");
                        dialog.dispose();
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
