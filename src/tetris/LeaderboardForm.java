package tetris;

import javax.naming.ldap.SortKey;
import javax.swing.*;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

public class LeaderboardForm extends JFrame {
    private JButton btMainMenu;
    private JTable leaderboard;
    private DefaultTableModel tm;
    private TableRowSorter<TableModel> sorter;
    private JScrollPane scrollPane;
    private final String LEADERBOARD_FILE = "leaderboard";
    private void initTableData() {
        //tm = (DefaultTableModel) leaderboard.getModel();
        tm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override
            public Class getColumnClass(int col) {
                if (col == 1)       //second column accepts only Integer values
                    return Integer.class;
                else return String.class;  //other columns accept String values
            }
        };
        tm.addColumn("Player");
        tm.addColumn("Score");

        Vector ci = new Vector();
        ci.add("Player");
        ci.add("Score");

        leaderboard = new JTable(tm);
        try {
            FileInputStream fs = new FileInputStream(LEADERBOARD_FILE);
            ObjectInputStream os = new ObjectInputStream(fs);
            tm.setDataVector((Vector<Vector>) os.readObject(), ci);
            os.close();
            fs.close();
        } catch (Exception e) {

        }
    }
    private void initTableSorter() {
        sorter = new TableRowSorter<>(tm);
        leaderboard.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> keys = new ArrayList<>();
        keys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
        sorter.setSortKeys(keys);
    }
    public LeaderboardForm() {
        this.setSize(720, 500);
        Color pink = new Color(255,192,203);
        this.getContentPane().setBackground(pink);
        ImageIcon icon = new ImageIcon("res/gamebutton/MainMenuButton.png");
        Image image = icon.getImage();
        Image resizeImg = image.getScaledInstance(150, 30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        btMainMenu = new JButton(icon);
        btMainMenu.setOpaque(false);
        btMainMenu.setContentAreaFilled(false);
        btMainMenu.setBorderPainted(false);
        btMainMenu.setBounds(10, 10, 150, 30);
        btMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Tetris.showStartup();
            }
        });

        this.add(btMainMenu);

        //leaderboard = new JTable();
        initTableData();
        initTableSorter();

        //tm.addColumn("Player"); tm.addColumn("Score");
        //leaderboard = new JTable(tm);
        //leaderboard.setBounds(10, 50, 680, 400);
        //this.add(leaderboard);
        scrollPane = new JScrollPane(leaderboard);
        scrollPane.setBounds(10, 50, 680, 400);
        getContentPane().add(scrollPane);


        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //this.setVisible(true);
    }
    public void addPlayer(String playerName, int score) {
        tm.addRow(new Object[] {playerName, score});
        sorter.sort();
        saveLeaderboard();
        this.setVisible(true);
    }
    private void saveLeaderboard() {
        try {
            FileOutputStream fs = new FileOutputStream(LEADERBOARD_FILE);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(tm.getDataVector());
            os.close();
            fs.close();
        } catch (Exception e) {

        }
    }
    public static void main(String[] args) {
        /*EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LeaderboardForm().setVisible(true);
            }
        });*/
    }
}
