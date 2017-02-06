package info.mhylle.playground.lpr3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainClass
{
  public void start()
  {


    JFrame frame = new JFrame();
    JPanel menuBar = new JPanel();
    JPanel contentPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    JLabel menuLabel = new JLabel("Menu");
    JLabel contentLabel = new JLabel("Content");
    JLabel buttonLabel = new JLabel("Button");
    menuBar.add(menuLabel);
    contentPanel.add(contentLabel);
    buttonPanel.add(buttonLabel);
    frame.setLayout(new BorderLayout());
    frame.add(menuBar, BorderLayout.NORTH);
    frame.add(contentPanel, BorderLayout.CENTER);
    frame.add(buttonPanel, BorderLayout.SOUTH);

    frame.setSize(800,600);
    frame.addWindowListener(new WindowAdapter()
    {
      @Override public void windowClosing(WindowEvent e)
      {
        System.exit(-1);
      }
    });

    frame.setVisible(true);
  }
}
