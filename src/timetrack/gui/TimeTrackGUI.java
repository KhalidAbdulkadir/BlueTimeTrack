
package timetrack.gui;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;


public class TimeTrackGUI extends javax.swing.JFrame {

    //Inloggad användare (ID från databasen)
    //Tilldelas värde med set-metoden setUserID.
    //default = 0 (ingen användare är inloggad).
    int userID = 0;
    //Skapar objekt av guiMethods
    GUIMethods guiM = new GUIMethods();
    //Skapar objekt av TimerThread som är en "inner class" i GUIMethods
    GUIMethods.TimerThread timerThread = guiM.new TimerThread();
    //Boolean array som håller reda på vilket menyval som är aktivt
    Boolean[] menuArray = new Boolean[8];
    JFrame loginJFrame;

    public TimeTrackGUI(JFrame loginJFrame) {
        this.loginJFrame = loginJFrame;
        initComponents();
        //Här körs metoden start() som finns i klassen TimerThread som i sin tur finns i klassen GUIMethods
        //Den kommer att starta en ny Thread som kan köras oberoende av det övriga programmet
        //och uppdatera tid och datum via en jLabel längst ner i högra hörnet.
        timerThread.start();
        guiM.setTimeTrackGUI(this);
        //Sätter alla paneler (knappar/menyval) till false vid start,
        //förutom Tidrapportering som ska vara default när programmet startar
        projectPanel.setVisible(false);
        timePanel.setVisible(true);
        //Sätter alla booleans i arrayen till false
        Arrays.fill(menuArray, Boolean.FALSE);
        chooseProjectPanel.setVisible(false);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        motionPanel = new MotionPanel(this);
        mainLeftPanel = new javax.swing.JPanel();
        currentUserLabel = new javax.swing.JLabel();
        menuPanel1 = new javax.swing.JPanel();
        menuLabel1 = new javax.swing.JLabel();
        menuLeftPanel1 = new javax.swing.JPanel();
        menuPanel2 = new javax.swing.JPanel();
        menuLabel2 = new javax.swing.JLabel();
        menuLeftPanel2 = new javax.swing.JPanel();
        menuPanel3 = new javax.swing.JPanel();
        menuLabel3 = new javax.swing.JLabel();
        menuLeftPanel3 = new javax.swing.JPanel();
        menuPanel5 = new javax.swing.JPanel();
        menuLeftPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        menuPanel6 = new javax.swing.JPanel();
        menuLeftPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        menuPanel7 = new javax.swing.JPanel();
        menuLabel7 = new javax.swing.JLabel();
        menuLeftPanel7 = new javax.swing.JPanel();
        menuPanel8 = new javax.swing.JPanel();
        menuLabel8 = new javax.swing.JLabel();
        menuLeftPanel8 = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        timePanel = new javax.swing.JPanel();
        chooseProjectPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        chooseProjectHeader = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chooseProjectTable = new javax.swing.JTable();
        timeDateLabel = new javax.swing.JLabel();
        timeStartLabel = new javax.swing.JLabel();
        timeEndLabel = new javax.swing.JLabel();
        timeProjectLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator(javax.swing.JSeparator.VERTICAL);
        jSeparator4 = new javax.swing.JSeparator(javax.swing.JSeparator.VERTICAL);
        jSeparator5 = new javax.swing.JSeparator(javax.swing.JSeparator.VERTICAL);
        timeDateTextfield = new org.jdesktop.swingx.JXTextField();
        timeProjectTextfield = new org.jdesktop.swingx.JXTextField();
        timeEndTextfield = new org.jdesktop.swingx.JXTextField();
        timeStartTextfield = new org.jdesktop.swingx.JXTextField();
        jPanel1 = new javax.swing.JPanel();
        timeSendButtonPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        menuLeftPanel4 = new javax.swing.JPanel();
        timeSucceededLabel = new javax.swing.JLabel();
        projectPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        overviewPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        adminUserPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        adminProjectPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        settingsPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        dateTimeLabel = new javax.swing.JLabel();
        mainTopPanel = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        closePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        minimizePanel = new javax.swing.JPanel();
        minimizeLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        motionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainLeftPanel.setBackground(new java.awt.Color(210, 219, 228));
        mainLeftPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainLeftPanelMouseClicked(evt);
            }
        });
        mainLeftPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        currentUserLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        currentUserLabel.setForeground(new java.awt.Color(47, 66, 84));
        currentUserLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainLeftPanel.add(currentUserLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 60));

        menuPanel1.setBackground(new java.awt.Color(47, 66, 84));
        menuPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPanel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuPanel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuPanel1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuPanel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                menuPanel1MouseReleased(evt);
            }
        });
        menuPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuLabel1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        menuLabel1.setForeground(new java.awt.Color(255, 255, 255));
        menuLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuLabel1.setText("Tidrapportering");
        menuPanel1.add(menuLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 0, 140, 30));

        menuLeftPanel1.setBackground(new java.awt.Color(47, 66, 84));
        menuPanel1.add(menuLeftPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 30));

        mainLeftPanel.add(menuPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 170, 30));

        menuPanel2.setBackground(new java.awt.Color(92, 126, 162));
        menuPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPanel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuPanel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuPanel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuPanel2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                menuPanel2MouseReleased(evt);
            }
        });
        menuPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        menuLabel2.setForeground(new java.awt.Color(255, 255, 255));
        menuLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuLabel2.setText("Projekt");
        menuPanel2.add(menuLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 140, 30));

        menuLeftPanel2.setBackground(new java.awt.Color(47, 66, 84));
        menuPanel2.add(menuLeftPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

        mainLeftPanel.add(menuPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 170, 30));

        menuPanel3.setBackground(new java.awt.Color(92, 126, 162));
        menuPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPanel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuPanel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuPanel3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuPanel3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                menuPanel3MouseReleased(evt);
            }
        });
        menuPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        menuLabel3.setForeground(new java.awt.Color(255, 255, 255));
        menuLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuLabel3.setText("Översikt");
        menuPanel3.add(menuLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 140, 30));

        menuLeftPanel3.setBackground(new java.awt.Color(47, 66, 84));
        menuPanel3.add(menuLeftPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

        mainLeftPanel.add(menuPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 170, -1));

        menuPanel5.setBackground(new java.awt.Color(92, 126, 162));
        menuPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPanel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuPanel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuPanel5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuPanel5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                menuPanel5MouseReleased(evt);
            }
        });
        menuPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuLeftPanel5.setBackground(new java.awt.Color(47, 66, 84));
        menuPanel5.add(menuLeftPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText(" Hantera användare");
        menuPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 0, 140, 30));

        mainLeftPanel.add(menuPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 170, 30));

        menuPanel6.setBackground(new java.awt.Color(92, 126, 162));
        menuPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPanel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuPanel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuPanel6MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuPanel6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                menuPanel6MouseReleased(evt);
            }
        });
        menuPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuLeftPanel6.setBackground(new java.awt.Color(47, 66, 84));
        menuPanel6.add(menuLeftPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText(" Hantera projekt");
        menuPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 0, 140, 30));

        mainLeftPanel.add(menuPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 170, 30));

        menuPanel7.setBackground(new java.awt.Color(92, 126, 162));
        menuPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPanel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuPanel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuPanel7MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuPanel7MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                menuPanel7MouseReleased(evt);
            }
        });
        menuPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        menuLabel7.setForeground(new java.awt.Color(255, 255, 255));
        menuLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuLabel7.setText("Inställningar");
        menuPanel7.add(menuLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 140, 30));

        menuLeftPanel7.setBackground(new java.awt.Color(47, 66, 84));
        menuPanel7.add(menuLeftPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

        mainLeftPanel.add(menuPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 170, 30));

        menuPanel8.setBackground(new java.awt.Color(92, 126, 162));
        menuPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPanel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuPanel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuPanel8MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuPanel8MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                menuPanel8MouseReleased(evt);
            }
        });
        menuPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        menuLabel8.setForeground(new java.awt.Color(255, 255, 255));
        menuLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuLabel8.setText("Logga ut");
        menuPanel8.add(menuLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 140, 30));

        menuLeftPanel8.setBackground(new java.awt.Color(47, 66, 84));
        menuPanel8.add(menuLeftPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 30));

        mainLeftPanel.add(menuPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 170, 30));

        motionPanel.add(mainLeftPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 170, 530));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        timePanel.setBackground(new java.awt.Color(255, 255, 255));
        timePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timePanelMouseClicked(evt);
            }
        });
        timePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chooseProjectPanel.setBackground(new java.awt.Color(210, 219, 228));
        chooseProjectPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(47, 66, 84));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chooseProjectHeader.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        chooseProjectHeader.setForeground(new java.awt.Color(255, 255, 255));
        chooseProjectHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chooseProjectHeader.setText("Tilldelade projekt");
        jPanel2.add(chooseProjectHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 0, 180, 30));

        chooseProjectPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 30));

        chooseProjectTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        chooseProjectTable.setRowHeight(30);
        chooseProjectTable.setTableHeader(null);
        chooseProjectTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseProjectTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(chooseProjectTable);

        chooseProjectPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, 140));

        timePanel.add(chooseProjectPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 200, 180));

        timeDateLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        timeDateLabel.setForeground(new java.awt.Color(47, 66, 84));
        timeDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeDateLabel.setText(" Datum");
        timePanel.add(timeDateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 130, 30));

        timeStartLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        timeStartLabel.setForeground(new java.awt.Color(47, 66, 84));
        timeStartLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeStartLabel.setText(" Starttid");
        timePanel.add(timeStartLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 140, 30));

        timeEndLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        timeEndLabel.setForeground(new java.awt.Color(47, 66, 84));
        timeEndLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeEndLabel.setText(" Sluttid");
        timePanel.add(timeEndLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 140, 30));

        timeProjectLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        timeProjectLabel.setForeground(new java.awt.Color(47, 66, 84));
        timeProjectLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeProjectLabel.setText(" Projekt");
        timePanel.add(timeProjectLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 150, 30));

        jSeparator2.setBackground(new java.awt.Color(219, 219, 219));
        jSeparator2.setForeground(new java.awt.Color(219, 219, 219));
        timePanel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 10, 10));

        jSeparator4.setBackground(new java.awt.Color(219, 219, 219));
        jSeparator4.setForeground(new java.awt.Color(219, 219, 219));
        timePanel.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 10, 10));

        jSeparator5.setBackground(new java.awt.Color(219, 219, 219));
        jSeparator5.setForeground(new java.awt.Color(219, 219, 219));
        timePanel.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 10, 10));

        timeDateTextfield.setBackground(new java.awt.Color(237, 237, 237));
        timeDateTextfield.setBorder(null);
        timeDateTextfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timePanel.add(timeDateTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 120, 30));

        timeProjectTextfield.setEditable(false);
        timeProjectTextfield.setBackground(new java.awt.Color(237, 237, 237));
        timeProjectTextfield.setBorder(null);
        timeProjectTextfield.setForeground(new java.awt.Color(102, 102, 102));
        timeProjectTextfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timeProjectTextfield.setText("Välj projekt   ﹀");
        timeProjectTextfield.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timeProjectTextfieldMouseClicked(evt);
            }
        });
        timePanel.add(timeProjectTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 140, 30));

        timeEndTextfield.setBackground(new java.awt.Color(237, 237, 237));
        timeEndTextfield.setBorder(null);
        timeEndTextfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timePanel.add(timeEndTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 130, 30));

        timeStartTextfield.setBackground(new java.awt.Color(237, 237, 237));
        timeStartTextfield.setBorder(null);
        timeStartTextfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timePanel.add(timeStartTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 130, 30));

        jPanel1.setBackground(new java.awt.Color(237, 237, 237));
        timePanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 560, 30));

        timeSendButtonPanel.setBackground(new java.awt.Color(92, 126, 162));
        timeSendButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timeSendButtonPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                timeSendButtonPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                timeSendButtonPanelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                timeSendButtonPanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                timeSendButtonPanelMouseReleased(evt);
            }
        });
        timeSendButtonPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(" Skicka");
        timeSendButtonPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        menuLeftPanel4.setBackground(new java.awt.Color(47, 66, 84));
        timeSendButtonPanel.add(menuLeftPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

        timePanel.add(timeSendButtonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, 90, 30));

        timeSucceededLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        timeSucceededLabel.setForeground(new java.awt.Color(255, 255, 255));
        timeSucceededLabel.setText("Din tidredovisning har registrerats");
        timePanel.add(timeSucceededLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, -1, -1));

        mainPanel.add(timePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 510));

        projectPanel.setBackground(new java.awt.Color(255, 255, 255));
        projectPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Projekt");
        projectPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, -1, -1));

        mainPanel.add(projectPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 510));

        overviewPanel.setBackground(new java.awt.Color(255, 255, 255));
        overviewPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("Översikt");
        overviewPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, -1));

        mainPanel.add(overviewPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 510));

        adminUserPanel.setBackground(new java.awt.Color(255, 255, 255));
        adminUserPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Admin User");
        adminUserPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, -1));

        mainPanel.add(adminUserPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 510));

        adminProjectPanel.setBackground(new java.awt.Color(255, 255, 255));
        adminProjectPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Admin Projekt");
        adminProjectPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, -1));

        mainPanel.add(adminProjectPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 510));

        settingsPanel.setBackground(new java.awt.Color(255, 255, 255));
        settingsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("Inställningar");
        settingsPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, -1));

        mainPanel.add(settingsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 510));

        dateTimeLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        dateTimeLabel.setForeground(new java.awt.Color(47, 66, 84));
        dateTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel.add(dateTimeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 510, 360, 20));

        motionPanel.add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 780, 530));

        mainTopPanel.setBackground(new java.awt.Color(47, 66, 84));
        mainTopPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoLabel.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        logoLabel.setForeground(new java.awt.Color(255, 255, 255));
        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setText("T⌚T");
        mainTopPanel.add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, -10, 70, 60));

        closePanel.setBackground(new java.awt.Color(47, 66, 84));
        closePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closePanelMouseExited(evt);
            }
        });
        closePanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("✕");
        closePanel.add(jLabel1, new java.awt.GridBagConstraints());

        mainTopPanel.add(closePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, 50, 50));

        minimizePanel.setBackground(new java.awt.Color(47, 66, 84));
        minimizePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimizePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizePanelMouseExited(evt);
            }
        });
        minimizePanel.setLayout(new java.awt.GridBagLayout());

        minimizeLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        minimizeLabel.setForeground(new java.awt.Color(255, 255, 255));
        minimizeLabel.setText("—");
        minimizePanel.add(minimizeLabel, new java.awt.GridBagConstraints());

        mainTopPanel.add(minimizePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 50, 50));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 8)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Blue Time Track");
        mainTopPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 90, 20));

        motionPanel.add(mainTopPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 50));

        getContentPane().add(motionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuPanel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel7MouseEntered
        menuPanel7.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_menuPanel7MouseEntered

    private void menuPanel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel7MouseExited
        if(!menuArray[6]) {
        menuPanel7.setBackground(new java.awt.Color(92,126,162));
        }
    }//GEN-LAST:event_menuPanel7MouseExited

    private void menuPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel7MouseClicked
        selectedPanel(7);
        menuPanel1.setBackground(new java.awt.Color(92,126,162));
        menuPanel2.setBackground(new java.awt.Color(92,126,162));
        menuPanel3.setBackground(new java.awt.Color(92,126,162));
        menuPanel5.setBackground(new java.awt.Color(92,126,162));
        menuPanel6.setBackground(new java.awt.Color(92,126,162));
        menuPanel8.setBackground(new java.awt.Color(92,126,162));
        Arrays.fill(menuArray, Boolean.FALSE);
        menuArray[6] = true;
    }//GEN-LAST:event_menuPanel7MouseClicked

    private void menuPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel3MouseClicked
        selectedPanel(3);
        menuPanel1.setBackground(new java.awt.Color(92,126,162));
        menuPanel2.setBackground(new java.awt.Color(92,126,162));
        menuPanel5.setBackground(new java.awt.Color(92,126,162));
        menuPanel6.setBackground(new java.awt.Color(92,126,162));
        menuPanel7.setBackground(new java.awt.Color(92,126,162));
        menuPanel8.setBackground(new java.awt.Color(92,126,162));
        Arrays.fill(menuArray, Boolean.FALSE);
        menuArray[2] = true;
    }//GEN-LAST:event_menuPanel3MouseClicked

    private void menuPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel3MouseEntered
        menuPanel3.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_menuPanel3MouseEntered

    private void menuPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel3MouseExited
        if(!menuArray[2]) {
        menuPanel3.setBackground(new java.awt.Color(92,126,162));
        }
    }//GEN-LAST:event_menuPanel3MouseExited

    private void menuPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel1MouseClicked
        selectedPanel(1);
        menuPanel2.setBackground(new java.awt.Color(92,126,162));
        menuPanel3.setBackground(new java.awt.Color(92,126,162));
        menuPanel5.setBackground(new java.awt.Color(92,126,162));
        menuPanel6.setBackground(new java.awt.Color(92,126,162));
        menuPanel7.setBackground(new java.awt.Color(92,126,162));
        menuPanel8.setBackground(new java.awt.Color(92,126,162));
        Arrays.fill(menuArray, Boolean.FALSE);
        menuArray[0] = true;
        
    }//GEN-LAST:event_menuPanel1MouseClicked

    private void menuPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel1MouseEntered
        menuPanel1.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_menuPanel1MouseEntered

    private void menuPanel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel1MouseExited
        if(!menuArray[0]) {
        menuPanel1.setBackground(new java.awt.Color(92,126,162));
        }
    }//GEN-LAST:event_menuPanel1MouseExited

    private void menuPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel2MouseClicked
        selectedPanel(2);
        menuPanel1.setBackground(new java.awt.Color(92,126,162));
        menuPanel3.setBackground(new java.awt.Color(92,126,162));
        menuPanel5.setBackground(new java.awt.Color(92,126,162));
        menuPanel6.setBackground(new java.awt.Color(92,126,162));
        menuPanel7.setBackground(new java.awt.Color(92,126,162));
        menuPanel8.setBackground(new java.awt.Color(92,126,162));
        Arrays.fill(menuArray, Boolean.FALSE);
        menuArray[1] = true;
    }//GEN-LAST:event_menuPanel2MouseClicked

    private void menuPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel2MouseEntered
        menuPanel2.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_menuPanel2MouseEntered

    private void menuPanel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel2MouseExited
        if(!menuArray[1]) {
        menuPanel2.setBackground(new java.awt.Color(92,126,162));
        }
    }//GEN-LAST:event_menuPanel2MouseExited

    private void menuPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel8MouseClicked
        menuPanel1.setBackground(new java.awt.Color(92,126,162));
        menuPanel2.setBackground(new java.awt.Color(92,126,162));
        menuPanel3.setBackground(new java.awt.Color(92,126,162));
        menuPanel5.setBackground(new java.awt.Color(92,126,162));
        menuPanel6.setBackground(new java.awt.Color(92,126,162));
        menuPanel7.setBackground(new java.awt.Color(92,126,162));
        Arrays.fill(menuArray, Boolean.FALSE);
        menuArray[7] = true;
        signOut();
    }//GEN-LAST:event_menuPanel8MouseClicked

    private void menuPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel8MouseEntered
        menuPanel8.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_menuPanel8MouseEntered

    private void menuPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel8MouseExited
        if(!menuArray[7]) {
        menuPanel8.setBackground(new java.awt.Color(92,126,162));
        }
    }//GEN-LAST:event_menuPanel8MouseExited

    private void minimizePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizePanelMouseEntered
        minimizePanel.setBackground(new java.awt.Color(56,79,101));
    }//GEN-LAST:event_minimizePanelMouseEntered

    private void minimizePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizePanelMouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_minimizePanelMouseClicked

    private void minimizePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizePanelMouseExited
        minimizePanel.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_minimizePanelMouseExited

    private void closePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closePanelMouseEntered
        closePanel.setBackground(new java.awt.Color(188,55,55));
    }//GEN-LAST:event_closePanelMouseEntered

    private void closePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closePanelMouseClicked
        //Stänger av visningen av datum och tid via metoden setRunnig()
        //Detta är för att den Thread som körs parallellt ska avslutas
        timerThread.setRunning(false);
        System.exit(0);
    }//GEN-LAST:event_closePanelMouseClicked

    private void closePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closePanelMouseExited
        closePanel.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_closePanelMouseExited

    private void menuPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel5MouseClicked
        selectedPanel(5);
        menuPanel1.setBackground(new java.awt.Color(92,126,162));
        menuPanel2.setBackground(new java.awt.Color(92,126,162));
        menuPanel3.setBackground(new java.awt.Color(92,126,162));
        menuPanel6.setBackground(new java.awt.Color(92,126,162));
        menuPanel7.setBackground(new java.awt.Color(92,126,162));
        menuPanel8.setBackground(new java.awt.Color(92,126,162));
        menuArray[4] = true;
    }//GEN-LAST:event_menuPanel5MouseClicked

    private void menuPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel5MouseEntered
        menuPanel5.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_menuPanel5MouseEntered

    private void menuPanel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel5MouseExited
        if(!menuArray[4]) {
        menuPanel5.setBackground(new java.awt.Color(92,126,162));
        }
    }//GEN-LAST:event_menuPanel5MouseExited

    private void menuPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel6MouseClicked
        selectedPanel(6);
        menuPanel1.setBackground(new java.awt.Color(92,126,162));
        menuPanel2.setBackground(new java.awt.Color(92,126,162));
        menuPanel3.setBackground(new java.awt.Color(92,126,162));
        menuPanel5.setBackground(new java.awt.Color(92,126,162));
        menuPanel7.setBackground(new java.awt.Color(92,126,162));
        menuPanel8.setBackground(new java.awt.Color(92,126,162));
        menuArray[5] = true;
    }//GEN-LAST:event_menuPanel6MouseClicked

    private void menuPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel6MouseEntered
        menuPanel6.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_menuPanel6MouseEntered

    private void menuPanel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel6MouseExited
        if(!menuArray[5]) {
        menuPanel6.setBackground(new java.awt.Color(92,126,162));
        }
    }//GEN-LAST:event_menuPanel6MouseExited

    private void timeSendButtonPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeSendButtonPanelMouseEntered
        timeSendButtonPanel.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_timeSendButtonPanelMouseEntered

    private void timeSendButtonPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeSendButtonPanelMouseExited
        timeSendButtonPanel.setBackground(new java.awt.Color(92,126,162));
    }//GEN-LAST:event_timeSendButtonPanelMouseExited

    private void timeSendButtonPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeSendButtonPanelMousePressed
        timeSendButtonPanel.setBackground(new java.awt.Color(56,79,101));
    }//GEN-LAST:event_timeSendButtonPanelMousePressed

    private void timeSendButtonPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeSendButtonPanelMouseReleased
        timeSendButtonPanel.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_timeSendButtonPanelMouseReleased

    private void menuPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel1MousePressed
        menuPanel1.setBackground(new java.awt.Color(56,79,101));
    }//GEN-LAST:event_menuPanel1MousePressed

    private void menuPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel2MousePressed
        menuPanel2.setBackground(new java.awt.Color(56,79,101));
    }//GEN-LAST:event_menuPanel2MousePressed

    private void menuPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel3MousePressed
        menuPanel3.setBackground(new java.awt.Color(56,79,101));
    }//GEN-LAST:event_menuPanel3MousePressed

    private void menuPanel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel5MousePressed
        menuPanel5.setBackground(new java.awt.Color(56,79,101));
    }//GEN-LAST:event_menuPanel5MousePressed

    private void menuPanel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel6MousePressed
        menuPanel6.setBackground(new java.awt.Color(56,79,101));
    }//GEN-LAST:event_menuPanel6MousePressed

    private void menuPanel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel7MousePressed
        menuPanel7.setBackground(new java.awt.Color(56,79,101));
    }//GEN-LAST:event_menuPanel7MousePressed

    private void menuPanel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel8MousePressed
        menuPanel8.setBackground(new java.awt.Color(56,79,101));
    }//GEN-LAST:event_menuPanel8MousePressed

    private void menuPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel1MouseReleased
        menuPanel1.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_menuPanel1MouseReleased

    private void menuPanel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel2MouseReleased
        menuPanel2.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_menuPanel2MouseReleased

    private void menuPanel3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel3MouseReleased
        menuPanel3.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_menuPanel3MouseReleased

    private void menuPanel5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel5MouseReleased
        menuPanel5.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_menuPanel5MouseReleased

    private void menuPanel6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel6MouseReleased
        menuPanel6.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_menuPanel6MouseReleased

    private void menuPanel7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel7MouseReleased
        menuPanel7.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_menuPanel7MouseReleased

    private void menuPanel8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanel8MouseReleased
        menuPanel8.setBackground(new java.awt.Color(47,66,84));
    }//GEN-LAST:event_menuPanel8MouseReleased

    private void timeSendButtonPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeSendButtonPanelMouseClicked
        guiM.sendTimeToDB(3, 5, "2020-11-28", "09:30", "13:00");
    }//GEN-LAST:event_timeSendButtonPanelMouseClicked

    private void timeProjectTextfieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeProjectTextfieldMouseClicked
        
        chooseProjectPanel.setVisible(true);
        
        ResultSet rs = guiM.getUserProjects(userID);
        chooseProjectTable.setModel(DbUtils.resultSetToTableModel(rs));
    }//GEN-LAST:event_timeProjectTextfieldMouseClicked

    private void chooseProjectTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseProjectTableMouseClicked
        chooseProjectPanel.setVisible(false);
        int column = 0;
        int row = chooseProjectTable.getSelectedRow();
        String project = chooseProjectTable.getModel().getValueAt(row, column).toString();
        timeProjectTextfield.setText(project);
    }//GEN-LAST:event_chooseProjectTableMouseClicked

    private void timePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timePanelMouseClicked
        chooseProjectPanel.setVisible(false);
    }//GEN-LAST:event_timePanelMouseClicked

    private void mainLeftPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainLeftPanelMouseClicked
        chooseProjectPanel.setVisible(false);
    }//GEN-LAST:event_mainLeftPanelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel adminProjectPanel;
    private javax.swing.JPanel adminUserPanel;
    private javax.swing.JLabel chooseProjectHeader;
    private javax.swing.JPanel chooseProjectPanel;
    private javax.swing.JTable chooseProjectTable;
    private javax.swing.JPanel closePanel;
    protected javax.swing.JLabel currentUserLabel;
    private static javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPanel mainLeftPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel mainTopPanel;
    private javax.swing.JLabel menuLabel1;
    private javax.swing.JLabel menuLabel2;
    private javax.swing.JLabel menuLabel3;
    private javax.swing.JLabel menuLabel7;
    private javax.swing.JLabel menuLabel8;
    private javax.swing.JPanel menuLeftPanel1;
    private javax.swing.JPanel menuLeftPanel2;
    private javax.swing.JPanel menuLeftPanel3;
    private javax.swing.JPanel menuLeftPanel4;
    private javax.swing.JPanel menuLeftPanel5;
    private javax.swing.JPanel menuLeftPanel6;
    private javax.swing.JPanel menuLeftPanel7;
    private javax.swing.JPanel menuLeftPanel8;
    private javax.swing.JPanel menuPanel1;
    private javax.swing.JPanel menuPanel2;
    private javax.swing.JPanel menuPanel3;
    private javax.swing.JPanel menuPanel5;
    private javax.swing.JPanel menuPanel6;
    private javax.swing.JPanel menuPanel7;
    private javax.swing.JPanel menuPanel8;
    private javax.swing.JLabel minimizeLabel;
    private javax.swing.JPanel minimizePanel;
    private javax.swing.JPanel motionPanel;
    private javax.swing.JPanel overviewPanel;
    private javax.swing.JPanel projectPanel;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JLabel timeDateLabel;
    private org.jdesktop.swingx.JXTextField timeDateTextfield;
    private javax.swing.JLabel timeEndLabel;
    private org.jdesktop.swingx.JXTextField timeEndTextfield;
    private javax.swing.JPanel timePanel;
    private javax.swing.JLabel timeProjectLabel;
    private org.jdesktop.swingx.JXTextField timeProjectTextfield;
    private javax.swing.JPanel timeSendButtonPanel;
    private javax.swing.JLabel timeStartLabel;
    private org.jdesktop.swingx.JXTextField timeStartTextfield;
    private javax.swing.JLabel timeSucceededLabel;
    // End of variables declaration//GEN-END:variables

    
    private void signOut(){
        //Stänger uppkopplingen till databasen
        guiM.closeDBConnection();
        //avslutar huvudprogrammet
        this.dispose();
        //Stänger av visningen av datum och tid via metoden setRunnig()
        //Detta är för att den Thread som körs parallellt ska avslutas
        timerThread.setRunning(false);
        //loginJFrame är (this) från LoginGUI.
        ((LoginGUI)loginJFrame).clearPasswordField();
        //Sätter markören i textefältet emailInput (användarnamn)
        ((LoginGUI)loginJFrame).emailInput.grabFocus();
        //Markerar all text i textefältet emailInput (användarnamn)
        ((LoginGUI)loginJFrame).emailInput.selectAll();
        loginJFrame.setVisible(true);
    }
    
    //Metod som används för att uppdatera datum och tid i programmet
    public static void updateDateTime(String updatedDateTime) {
        //Eftersom deklarationen av jLabel dateTimeLabel är ändrad till static
        //så kan vi skriva direkt till den från en statisk metod
        dateTimeLabel.setText(updatedDateTime);
    }
    
    ////////
    /////////Get och set metoder längst ner
    ////////
    
    public void setUserID(int userID) {
        this.userID = userID;
      
    }
    
    public int getUserID() {
        return userID;
    }
    
    public void setCurrentUserLabel(String userLabel) {
        //Sätter namnet på inloggad användare uppe till vänster i GUI
        currentUserLabel.setText(userLabel);
    }
    
    public void setTimeSucceededLabelColor(Color color) {
        timeSucceededLabel.setForeground(color);
    }
    
    public void selectedPanel(int menuNr) {
        //Sätter först alla peneler till false
        //och sätter sedan vald panel till true via switch
        timePanel.setVisible(false);
        projectPanel.setVisible(false);
        overviewPanel.setVisible(false);
        adminUserPanel.setVisible(false);
        adminProjectPanel.setVisible(false);
        settingsPanel.setVisible(false);
        
        switch(menuNr) {
            case 1:
                timePanel.setVisible(true);
                break;
            case 2:
                projectPanel.setVisible(true);
                break;
            case 3:
                overviewPanel.setVisible(true);
                break;
            case 4:
                
                break;
            case 5:
                adminUserPanel.setVisible(true);
                break;
            case 6:
                adminProjectPanel.setVisible(true);
                break;
            case 7:
                settingsPanel.setVisible(true);
                break;
            case 8:
                break;
            default:
                
        }
    }
    
}
