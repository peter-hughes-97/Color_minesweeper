import javax.swing.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class view extends JFrame {
    minesweeper model = new minesweeper();
    
    // The size in pixels for the frame.
    private static final int SIZE = 500;
    
    
    public JFrame  frame;
    
    private JLabel Counter;
    private JLabel clock;
    private JButton reset;
    
    public void runview(final int gridSize) {
        frame = new JFrame("Minesweeper");
        frame.setSize(SIZE, SIZE);
        frame.setLayout(new BorderLayout());
    
        initializeButtonPanel();
        initializeGrid(gridSize);
        createMenuBar();
    
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
      public void initializeGrid(final int gridSize) {
        Container grid = new Container();
        grid.setLayout(new GridLayout(gridSize, gridSize, 2, 2));
            for (int row = 0; row < gridSize; row++) {
                for (int col = 0; col < gridSize; col++) {
                    model.cells[row][col] = new Cell(row, col, actionListener);
                    grid.add(model.cells[row][col]);
                    model.cells[row][col].setBackground(new Color (192,192,192));
                    model.cells[row][col].setBorder(BorderFactory.createMatteBorder(30,30,30,30,new Color (192,192,192)));
                }
            }
            model.createMines();
            frame.add(grid, BorderLayout.CENTER);
        }
        
        public final ActionListener actionListener = actionEvent -> {
            Object source = actionEvent.getSource();
            if (source == reset) {
                model.reset();
            } else {
                model.handleCell((Cell) source);
            }
        };
        
        private void createMenuBar() {
    
            var menuBar = new JMenuBar();
    
            var fileMenu = new JMenu("File");
            fileMenu.setMnemonic(KeyEvent.VK_F);
    
            var eMenuItem = new JMenuItem("Exit");
            eMenuItem.setToolTipText("Exit application");
            eMenuItem.addActionListener((event) -> System.exit(0));
            frame.setJMenuBar(menuBar);
        }
    
        private void initializeButtonPanel() {
            JPanel buttonPanel = new JPanel();
    
            reset = new JButton("Reset");
            clock = new JLabel(String.valueOf(0));
            
            reset.addActionListener(actionListener);
    
            buttonPanel.add(reset);
            buttonPanel.add(clock);
            frame.add(buttonPanel, BorderLayout.SOUTH);
        }
        
        public void gameOver(String message) {
            JOptionPane.showMessageDialog(
                frame, message, "Game Over",
                JOptionPane.ERROR_MESSAGE
            );
            model.reset();
        }
        
        public void win() {
            JOptionPane.showMessageDialog(
            frame, "You have won!", "Congratulations",
            JOptionPane.INFORMATION_MESSAGE
            );
            model.reset();
        }

}