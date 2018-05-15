package alexshulga.view.theme;

import alexshulga.controller.ServiceHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class DialogDeleteTheme {
    private JDialog dialog = new JDialog();
    private ServiceHandler serviceHandler;

    public DialogDeleteTheme(ServiceHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }

    public void delete(int index) {

        dialog.setSize(500, 500);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(null);

        JLabel numberLabel = new JLabel("Номер записи: ");
        JTextField numberTextField = new JTextField(10);

        JButton deleteButton = new JButton("Удалить");

        JPanel panelDeleteButton = new JPanel();
        panelDeleteButton.setLayout(new GridBagLayout());

        panelDeleteButton.add(numberLabel, new GridBagConstraints(0, 0, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));


        panelDeleteButton.add(numberTextField, new GridBagConstraints(0, 1, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));


        dialog.add(panelDeleteButton, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(deleteButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.pack();
        dialog.setVisible(true);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ((numberTextField.getText()).trim().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Поле не заполнено");
                    return;
                } else {
                    try {
                        serviceHandler.deleteTheme(index, Integer.parseInt(numberTextField.getText()));

                        JOptionPane.showMessageDialog(dialog, "Запись успешно удалена. Для продолжения работы нажмите \"ОК\"");
                        dialog.dispose();
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
