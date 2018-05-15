package alexshulga;

import alexshulga.controller.ServiceHandler;
import alexshulga.view.MyFrame;

import java.awt.*;

public class Main {
    public static void main(String args[]){
        ServiceHandler serviceHandler = new ServiceHandler();
        MyFrame frame = new MyFrame("Справочник по языку VBScript",new Dimension(600,400), serviceHandler);
        frame.init();
    }
}
