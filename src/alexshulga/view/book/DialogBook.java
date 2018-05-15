package alexshulga.view.book;

import alexshulga.controller.ServiceHandler;
import alexshulga.view.theme.DialogChangeTheme;
import alexshulga.view.theme.DialogCreateTheme;
import alexshulga.view.theme.DialogDeleteTheme;
import alexshulga.view.theme.ModelTableThemes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class DialogBook {

    private JDialog dialog = new JDialog();
    private ServiceHandler serviceHandler;

    private JToolBar tb = new JToolBar();
    private JButton createButton = new JButton(new ImageIcon("images/add.png"));
    private JButton openButton = new JButton(new ImageIcon("images/show.png"));
    private JButton changeButton = new JButton(new ImageIcon("images/change.png"));
    private JButton deleteButton = new JButton(new ImageIcon("images/delete.png"));
    private JButton offButton = new JButton(new ImageIcon("images/back.png"));
    private int index;

    private ModelTableThemes model = new ModelTableThemes();
    private JTable table = new JTable(model);
    private JScrollPane jsp = new JScrollPane(table);

    public DialogBook(ServiceHandler serviceHandler, int index) {
        this.serviceHandler = serviceHandler;
        this.index = index;
    }

    public void show() throws RemoteException {

        dialog.setTitle("Книга "+serviceHandler.getAllBooks().get(index).getTitle());
        dialog.setSize(400, 500);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(null);
        dialog.add(jsp, BorderLayout.CENTER);
        jsp.setPreferredSize(new Dimension(700, 500));

        tb.add(createButton);
        tb.add(openButton);
        tb.add(changeButton);
        tb.add(deleteButton);
        tb.add(offButton);

        dialog.add(tb, BorderLayout.NORTH);

        createButton.addActionListener(new createActionListener());
        changeButton.addActionListener(new changeActionListener());
        openButton.addActionListener(new openActionListener());
        deleteButton.addActionListener(new deleteActionListener());
        offButton.addActionListener(new offActionListener());


        dialog.pack();
        dialog.setVisible(true);
    }

    public class openActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                model.addNotation(serviceHandler.getAllThemesInBock(index));
            }  catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }

    public class createActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogCreateTheme dialogCreateTheme = new DialogCreateTheme(serviceHandler);
            dialogCreateTheme.createTheme(index);
        }
    }

    public class deleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogDeleteTheme dialogDeleteTheme = new DialogDeleteTheme(serviceHandler);
            dialogDeleteTheme.delete(index);
        }
    }

    public class changeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogChangeTheme dialogChangeTheme = new DialogChangeTheme(serviceHandler);
            dialogChangeTheme.change(index);
        }
    }

    public class offActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dialog.dispose();
        }
    }
}
