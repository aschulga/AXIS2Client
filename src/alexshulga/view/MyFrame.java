package alexshulga.view;

import alexshulga.controller.ServiceHandler;
import alexshulga.view.book.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class MyFrame {

    private String title;
    private Dimension d;
    private ServiceHandler serviceHandler;
    private JFrame frame = new JFrame();

    private JToolBar tb = new JToolBar();
    private JButton createButton = new JButton(new ImageIcon("images/add.png"));
    private JButton openButton = new JButton(new ImageIcon("images/show.png"));
    private JButton changeButton = new JButton(new ImageIcon("images/change.png"));
    private JButton deleteButton = new JButton(new ImageIcon("images/delete.png"));
    private JButton openBookButton = new JButton(new ImageIcon("images/openBook.png"));
    private JButton offButton = new JButton(new ImageIcon("images/exit.png"));

    private ModelTableBooks model = new ModelTableBooks();
    private JTable table = new JTable(model);
    private JScrollPane jsp = new JScrollPane(table);

    public MyFrame(String title, Dimension d, ServiceHandler serviceHandler) {
        this.title = title;
        this.d = d;
        this.serviceHandler = serviceHandler;
    }

    public void init(){

        frame.setTitle(title);
        frame.setSize(d);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(jsp, BorderLayout.CENTER);
        jsp.setPreferredSize(new Dimension(700, 500));

        tb.add(createButton);
        tb.add(openButton);
        tb.add(changeButton);
        tb.add(deleteButton);
        tb.add(openBookButton);
        tb.add(offButton);

        frame.getContentPane().add(tb, BorderLayout.NORTH);

        createButton.addActionListener(new createActionListener());
        changeButton.addActionListener(new changeActionListener());
        openButton.addActionListener(new openActionListener());
        deleteButton.addActionListener(new deleteActionListener());
        openBookButton.addActionListener(new openBookButtonActionListener());
        offButton.addActionListener(new offActionListener());

        frame.setVisible(true);
        frame.pack();
    }

    public class openActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                model.addNotation(serviceHandler.getAllBooks());
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }

    public class createActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogCreateBook addStudentDialog = new DialogCreateBook(serviceHandler);
            addStudentDialog.create();
        }
    }

    public class deleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogDeleteBook searchStudentDialog = new DialogDeleteBook(serviceHandler);
            searchStudentDialog.delete();
        }
    }

    public class changeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogChangeBook changeDialog = new DialogChangeBook(serviceHandler);
            changeDialog.change();
        }
    }

    public class openBookButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogOpenBook openBookDialog = new DialogOpenBook(serviceHandler);
            openBookDialog.openBook();
        }
    }

    public class offActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

}
