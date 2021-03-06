
package timetrack.gui;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Akram
 */
public class GUIMethods{
    Connection cn;
    PreparedStatement pstat;
    ResultSet rs;
    String DBAddress;
    String DBUser;
    String DBPass;
    JFrame loginJFrame;
    String currentUser;
    TimeTrackGUI tGUI;
    ProjectMethods pM;
    //Kan ändras till private när logingenvägarna har tagits bort
    protected static int adminInt;
    ArrayList<Integer> userid = new ArrayList<Integer>();
    ArrayList<Integer> uidSetOnP = new ArrayList<Integer>();
    ArrayList<Integer> useronproject = new ArrayList<Integer>();
    Vector isSaved1 = new Vector();
    Vector isSaved2 = new Vector();
        // booleans uidSetOnP & uop är redan skapade som kan jämföra sparade användare med nuvarande användare.
        String pName1, pName2;
        String pDesc1, pDesc2;
        String pStatus1, pStatus2;
        String pCustomer1, pCustomer2;
    
    public GUIMethods() {
        readProperties();
        cn = prepareDBConnection();

        
    }
    
    public int loginUser(String email, String pass1){
        String qEmail = email;
        String qPass = pass1;
        //returnUserID som kommer att returnera UserID från databasen om användarnamn + lösenord matchar
        //Annars är den default 0 och returnerar då 0.
        int returnUserID = 0;
        try {
            //Ökar timeout till 5 sekunder
            DriverManager.setLoginTimeout(5);
            //Skapar ett SELECT statement till PreparedStatement objekt
            pstat = cn.prepareStatement("SELECT * FROM users WHERE email=? AND BINARY user_password=?");
            //Ändrar value-parametrar till texten i text-fälten.
            pstat.setString(1, qEmail);
            pstat.setString(2, qPass);
            //Utför SQL kommand
            rs = pstat.executeQuery();
            //Kollar om det finns MINST en rad från select statement (while hade kollat alla)
            if(rs.next()){
                //sparar värdet från första kolumnen (userID) från Select statemant.
                returnUserID = rs.getInt(1);
                currentUser = rs.getString(2) + " " + rs.getString(3);
                adminInt = rs.getInt(6);
            }
            //Om ingen rad returneras från select statemant så betyder det att kombinationen
            //av användarnamn och lösenord ej hittades i databasen och då körs istället else.
            else{
                JOptionPane.showMessageDialog(null, "Felaktigt användarnamn/lösenord", "Ej behörig!", 0);
            }
            
        //Om något går fel med kopplingen till databasen... (kommer även hit om felaktigt select statement används osv.
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kunde inte ansluta till databasen.\nKontrollera att du är ansluten till internet.", "Är du online?", 0);
            System.out.println(ex);
        }
        //Returnerar userID (från databasen)
        //Om login misslyckades så returneras 0.
        return returnUserID;
    }
    
    public int showDialog(String title, String message) {
        //ImageIcon icon = new ImageIcon("C:\\Users\\Akram\\OneDrive\\Skrivbord\\TimeTrack\\src\\timetrack\\gui\\ic_logo2.png");
        int input = JOptionPane.showConfirmDialog(null,message,title, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
        
        return input;
    }
    
    public void setPm(ProjectMethods pM){
        this.pM = pM;
        this.pM.setConnection(cn);
    }
    
    
    protected void readProperties(){
        //Metod som läser in värden från filen db.properties
        //Skapar objekt av Properties för att läsa från filen db.properties
        Properties prop = new Properties();
        try {
            FileReader reader = new FileReader("src/timetrack/gui/db.properties");
            prop.load(reader);
            //Tilldelar värdena från filen db.properties till klassvariablerna
            //som sedan ska användas för att logga in på databasen
            DBAddress = prop.getProperty("db");
            DBUser = prop.getProperty("user");
            DBPass = prop.getProperty("pass");
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Kanske saknas filen db.properties i src/timetrack/gui?");
        } catch (IOException ex) {
            Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Kanske saknas filen db.properties i src/timetrack/gui?");
        }
    }
    
    public void setCurrentUserLabel(TimeTrackGUI tGUI) {
        tGUI.currentUserLabel.setText(currentUser);
    }
    
    public Connection prepareDBConnection() {
        //Skapar Connection variabel med login info till DB
        //och returnerar den
        Connection con = null;
        try {
            con = DriverManager.getConnection(DBAddress, DBUser, DBPass);
        } catch (SQLException ex) {
            System.err.println("Uppkopplingen till databasen misslyckades. \n Förmodligen p.g.a för många användare aktiva");
        }
        return con;
    }
    
    public boolean resetpw(String password, int userid){
        boolean success = false;
        try {
        pstat = cn.prepareStatement("update users set user_password = ? where user_id = ?");
        pstat.setString(1, password);
        pstat.setInt(2, userid);
        pstat.executeUpdate();
        /*        if(rs.next()){
            System.out.println(result);
        }*/
            success = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return success;
    }
    
    public void closeDBConnection() {
        try {
        cn.close();
        } catch (SQLException ex) {
        Logger.getLogger(GUIMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertUsersHasSkills(String email) {
        int userID = 0;
            try {
                pstat = cn.prepareStatement("SELECT user_id FROM users WHERE email = ?");
                pstat.setString(1, email);
                rs = pstat.executeQuery();
                rs.next();
                userID = rs.getInt(1);
            } catch (Exception e) {
                System.out.println(e);
            }
        int rowCount = tGUI.jTable2.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            try {
                //Hämtar skillID för vald skill (loopar alla valda skills i tabellen)
                pstat = cn.prepareStatement("SELECT skill_id FROM skills WHERE skill = ?");
                String skillName = (String) tGUI.jTable2.getValueAt(i, 0);
                pstat.setString(1, skillName);
                rs = pstat.executeQuery();
                rs.next();
                int skillID = 0;
                skillID = rs.getInt(1);
                //Gör insert med vald skill + userID
                pstat = cn.prepareStatement("INSERT INTO users_has_skills (skill_id, user_id) VALUES (?,?)");
                pstat.setInt(1, skillID);
                pstat.setInt(2, userID);
                pstat.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(TimeTrackGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     
    }
    
    public void startTimeTrack(JFrame loginJFrame, GUIMethods loginGUI_guiM, int userID){
        this.loginJFrame = loginJFrame;
        loginJFrame.setVisible(false);
        //Gör loginrutan osynlig när användaren har loggat in
        TimeTrackGUI tGUI = new TimeTrackGUI(loginJFrame);
        //Sätter inloggad användare till userID (från databasen)
        tGUI.setUserID(userID);
        setCurrentUserLabel(tGUI);
        //Placerar objeketet i mitten på användarens skärm
        tGUI.setLocationRelativeTo(null);
        tGUI.setVisible(true);
    }
    
    public class TimerThread extends Thread{
        //Så länge som isRunning=true så uppdateras tiden
        boolean isRunning;
        //Här anges i vilket format som datum och tid ska visas
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        public TimerThread() {
            //isRunning sätts till true direkt i konstruktorn
            //Metoden getRunning() kan användas för att se om den är igång eller inte
            //Det kan behövs för att veta om man måste stänga den när programmet avslutas
            this.isRunning = true;
        }
        //Gör Override på metoden run() (kolla i klassen Thread för att se hur run() fungerar)
        @Override
        public void run() {
            //While-loop som kör så länge som isRunning=true
            //Vi stänger av den med metoden setRunning(false) som då ger boolean isRunning värdet false
            while (isRunning) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //Hämtar tid och datum med dom statiska metoderna getInstance() och getTime()
                        Calendar currentCalendar = Calendar.getInstance();
                        Date currentTime = currentCalendar.getTime();
                        //Skapar en string där aktuellt datum och tid först in
                        String dateTime = (dateFormat.format(currentTime) + "  |  " + timeFormat.format(currentTime));
                        //Strängen skickas med till metoden updateDateTime() som ligger i TImeTrackGUI
                        //Denna metod är static och kan därför användas direkt såhär
                        //Eftersom tiden inte ska behandlas på olika sätt för
                        //olika objekt så valde jag att göra den static
                        TimeTrackGUI.updateDateTime(dateTime);
                    }
                });
                try {
                    //Programmet (endast denna thread) stannar i x antal millisekunder
                    //5000 millisekunder = 5 sekunder
                    //while-loopen kommer alltså att köras och uppdatera tiden var 5'e sekund
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
        //Metod för att kunna stänga av uppdateringen av tid och datum
        //Eftersom while-loopen använder denna så kommer den av avbrytas
        //om isRunning sätts till false.
        //När en Thread har kört klart sina metoder så avslutas den automatiskt.
        //Vi kan därfö använda denna metod för att stänga av thread när 
        //programmet ska avslutas
        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }
        //Metod för att kunna kolla om denna Thread körs
        //Denna kan vi använda för att veta om vi måste stänga av den i samband
        //med att användaren stänger programmet.
        //Om den fortfarande körs så kommer programmet inte att stänga helt
        public boolean getRunning() {
            return this.isRunning;
        }
    }
    
    public boolean deleteProject(int projectid){
        boolean success = false;
        try {
            pstat = cn.prepareStatement("delete from projects where projects_id = ?");
            pstat.setInt(1, projectid);
            pstat.executeUpdate();
            success = true;
        }catch(SQLException ex) {
            System.out.println(ex);
        } 
        return success;
    }
        
    public boolean deleteUser(int userid){
        boolean success = false;
        try {
            pstat = cn.prepareStatement("delete from users where user_id = ?");
            pstat.setInt(1, userid);
            pstat.executeUpdate();
            
            success = true;
        }catch(SQLException ex) {
            System.out.println(ex);
        } 
        return success;
    }

        
    static String pStart;//Static eftersom alla ska ha tillgång till den.
    static String pEnd;
    static String pTotalTime;
    
    private static void projectStartTime() {
        Locale sv = new Locale ("sv","SV");//Skapar en Locale så att datum visas på svenska, alltså "EEEE"(dag) står på svenska.
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());//Hämta/stämpla nuvarande tid
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-(EEEE) HH:mm",sv);//Spara tiden enligt år,måndad,dag(dag ej i siffror)timmar, dagar.
        
        String startTime = sdf.format(ts);//Spara värden i en string om vi vill använda värden senare, exempelvis en totalTime.
        System.out.println(startTime);//Anledning till utskrivning är för att lätt kunan se om något händer.
        
        pStart=startTime;//Spara tiden i pStart i class så alla har tillgång till den.
    }
    
    private static void projectEndTime() {
        Locale sv = new Locale ("sv","SV");
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-(EEEE) HH:mm",sv);

        String endTime = sdf.format(ts);
        System.out.println(endTime);

        pEnd = endTime;
    }
  
    private static void projectTotalTime()  {    
        Locale sv = new Locale ("sv","SV");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-(EEEE) HH:mm",sv);

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(pStart);//Formaterar till den format startTime och endTime har för att kunna göra uträkning.
            d2 = format.parse(pEnd);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();//pEnd - pStart för uträkning

            long diffMinutes = diff / (60 * 1000) % 60;//Gör om millisekunder till minut.
            long diffHours = diff / (60 * 60 * 1000) % 24;//Gör om minut till timmar.
            long diffDays = diff / (24 * 60 * 60 * 1000);//Gör om timmar till dagar.

            System.out.print("Total project time: " +diffDays + " dagar, "+diffHours + " timmar, "+diffMinutes + " minuter, ");
            String days = String.valueOf(diffDays);//Sparade alla dessa i String för att kunna slå ihop dem till en enda sträng.
            String hours = String.valueOf(diffHours);
            String minutes = String.valueOf(diffMinutes);
            pTotalTime = "Dagar: "+days+" Timmar: "+hours+" Minuter: "+minutes;//Slår ihop alla till en sträng och spara i class.
        } 
        catch (ParseException e) {
        }
    }

    public boolean createProject(String pname, String pdesc, int pstatus,int custid){
        boolean success = false;
        try {
            pstat = cn.prepareStatement("insert into projects (project_name, project_description, project_status_id, customer_id) VALUES (?,?,?,?)");
            pstat.setString(1, pname);
            pstat.setString(2, pdesc);
            pstat.setInt(3, pstatus);
            pstat.setInt(4, custid);
            pstat.executeUpdate();

            success = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return success;
    }
            
            
    public boolean createUser(String name, String lastName, String email, String password, String skill, boolean isAdmin) {
        boolean success = false;
        try {
             pstat = cn.prepareStatement("insert into users (FName, LName, email, user_password, is_admin) VALUES (?,?,?,?,?) ");
             pstat.setString(1,name);            
             pstat.setString(2,lastName);
             pstat.setString(3,email);
             pstat.setString(4,password);
             pstat.setBoolean(5, isAdmin);
             pstat.executeUpdate();

            success = true;
        } catch (SQLException ex) {
         System.out.println(ex);
        }
        return success;
    }
            
    public void getAvailableSkills() {
        tGUI.jComboBox1.removeAllItems();
        try {
            pstat = cn.prepareStatement("select skill from skills");
            rs = pstat.executeQuery();
            while(rs.next()) {
            tGUI.jComboBox1.addItem(rs.getString(1));
        }
        }catch (SQLException ex) {
            Logger.getLogger(GUIMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class Thread2 extends Thread {
        
        JLabel jLabel;
        
        public Thread2(JLabel jLabel) {
            this.jLabel = jLabel;
        }
      
        public void run(){
            //Gör en fade in och fade out på en Label
            //Ska från vit 255,255,255 till grön 60,117,57
            try {
                Thread.sleep(750);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUIMethods.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < 180; i=i+2) {
                jLabel.setForeground(new java.awt.Color(60, 117, 57, i));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    System.err.println("Fel i faden på texten \"Din tidrapportering har registrerats\"");
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUIMethods.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 180; i > 0; i--) {

                jLabel.setForeground(new java.awt.Color(60, 117, 57, i));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    System.err.println("Fel i faden på texten \"Din tidredovisning har registrerats\"");
                }
            }
            tGUI.menuTimeDefaultValues();
        }
    }
    
    public ResultSet getUserProjects(int userID) {
    try {
            //Skapar ett SELECT statement till PreparedStatement objekt
            pstat = cn.prepareStatement("SELECT project_name FROM projects p\n" +
                                        "join users_has_projects up on p.projects_id = up.project_id\n" +
                                        "join users u on up.user_id = u.user_id\n" +
                                        "where u.user_id = ?");
            
            pstat.setInt(1, userID);
            //Utför SQL statement till Databas. Returnerar ett resultat till ResultSet rs
            rs = pstat.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    return rs;
    }
    
    public boolean sendTimeToDB(int userID, int project, String date, String startTime, String endTime){
        //Slår samman datum och tid till en sträng. Det är så den lagras i databasen
        String dateTimeStart = date + " " + startTime;
        String dateTimeEnd = date + " " + endTime;
        boolean success = false;
        try {
        pstat = cn.prepareStatement("INSERT INTO time (start_time, end_time, user_id, project_id) VALUES (?, ?, ?, ?);");
        pstat.setString(1, dateTimeStart);
        pstat.setString(2, dateTimeEnd);
        pstat.setInt(3, userID);
        pstat.setInt(4, project);
        int re = pstat.executeUpdate();
        //Kontrollerar om det är mer än 0 tillbaka så har det lyckats
        if (re > 0) {
            //Ny thread startar som kommer att visa en text med att rapporteringen har lyckats
            Thread2 thread2 = new Thread2(tGUI.timeSucceededLabel);
            thread2.start();
            tGUI.setTimeDefaultValues();
        }
        else {
            System.err.println("Något gick fel med att skicka tidrapporteringen");
        }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return success;
    }
    
    public boolean sendTimeUpdateToDB(int userID, int timeID, int project, String date, String startTime, String endTime){
        //Slår samman datum och tid till en sträng. Det är så den lagras i databasen
        String dateTimeStart = date + " " + startTime;
        String dateTimeEnd = date + " " + endTime;
        boolean success = false;
        try {
            pstat = cn.prepareStatement("UPDATE time " +
                                        "SET start_time = ?, " +
                                        "end_time = ?, " +
                                        "user_id = ?, " +
                                        "project_id = ? " +
                                        "WHERE time_id = ?");
            pstat.setString(1, dateTimeStart);
            pstat.setString(2, dateTimeEnd);
            pstat.setInt(3, userID);
            pstat.setInt(4, project);
            pstat.setInt(5, timeID);
            int re = pstat.executeUpdate();
            //Kontrollerar om det är mer än 0 tillbaka så har det lyckats
            if (re > 0) {
                //Ny thread startar som kommer att visa en text med att rapporteringen har lyckats
                Thread2 thread2 = new Thread2(tGUI.timeSucceededLabel1);
                thread2.start();
                tGUI.setTimeDefaultValues();
            }
            else {
                System.err.println("Något gick fel med att skicka tidrapporteringen");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return success;
    }
    
    public int getProjectID(String projectName){
        try {
        pstat = cn.prepareStatement("select projects_id from projects where project_name = ?");
        pstat.setString(1, projectName);
        rs = pstat.executeQuery();
        } catch (SQLException ex) {
                System.out.println(ex);
        }
        int projectID = 0;
        try {
            if(rs.next()) {
                projectID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projectID;
    }
    
    public ResultSet getAllProjectInfo(int projectID) {
        try {
            pstat = cn.prepareStatement("SELECT p.project_name, c.customer, c.contact, c.phone, c.email, p.project_description, ps.status " +
                                        "FROM projects p " +
                                        "JOIN customers c ON p.customer_id = c.customer_id " +
                                        "JOIN project_status ps ON p.project_status_id = ps.project_status_id " +
                                        "WHERE p.projects_id = ?");
            pstat.setInt(1, projectID);
            rs = pstat.executeQuery();
            rs.next();
        } catch (SQLException ex) {
                System.out.println(ex);
        }
        
        return rs;
    }
    
    public void setTimeTrackGUI(TimeTrackGUI tGUI) {
        this.tGUI = tGUI;
    }
    
    public void insertSkillValue() {
             String skill = (String) tGUI.jComboBox1.getSelectedItem();
        DefaultTableModel model = (DefaultTableModel)tGUI.jTable2.getModel();
        
       
        
        for(int i = 0; i < tGUI.jTable2.getRowCount(); i++) {
            if(tGUI.jTable2.getModel().getValueAt(i,0).equals(tGUI.jComboBox1.getSelectedItem())) {
               return;
                
         
            }
        }  
        
       Vector row = new Vector();
       row.add(skill);
       model.addRow(row);
    }

    public boolean emailIsAvailable(String email) {
        //Kontrollerar att emailen inte redan finns i databasen.
        boolean availableEmail = false;
        try {
            pstat = cn.prepareStatement("SELECT email FROM users WHERE email = ?");
            pstat.setString(1, email);
            rs = pstat.executeQuery();
            if(!rs.next()) {
                availableEmail = true;
            }
            else {
                //För tydlighetens skull
                availableEmail = false;
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return availableEmail;
    }
    
    public ResultSet getAvailableProjects(int userID, JComboBox comboBox, boolean cBox) {
        
        comboBox.removeAllItems();
        comboBox.addItem("Välj projekt");
        try {
            //Skapar ett SELECT statement till PreparedStatement objekt
            pstat = cn.prepareStatement("SELECT project_name FROM projects p\n" +
                                        "join users_has_projects up on p.projects_id = up.project_id\n" +
                                        "join users u on up.user_id = u.user_id\n" +
                                        "where u.user_id = ?");
            
            pstat.setInt(1, userID);
            //Utför SQL statement till Databas. Returnerar ett resultat till ResultSet rs
            rs = pstat.executeQuery();
            if(cBox) {
                while(rs.next()) {
                    comboBox.addItem(rs.getString(1));
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(GUIMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void clearAllTextFieldsInCreateUser() {
        tGUI.jTextField1.setText("");
        tGUI.jTextField2.setText("");
        tGUI.jTextField3.setText("");
        tGUI.jPasswordField2.setText("");
        
        DefaultTableModel model = (DefaultTableModel)tGUI.jTable2.getModel();
        
        int a = tGUI.jTable2.getRowCount();
         
        for(int i = a; i > 0 ; i--) {
               model.removeRow(i -1);
             }
        }  
 
        protected boolean getIsAdmin() {
            boolean isAdmin;
            isAdmin = adminInt==1;
            return isAdmin;
        }
                     
 
            
    protected ResultSet getTimeStampFromDB(int userID, String date) {                                         
        //Select till tabell
        System.out.println(userID + " " + date);
        try {
            pstat = cn.prepareStatement("SELECT t.time_id \"Tid-ID\", " +
                                        "p.project_name Projekt, " +
                                        "DATE(t.start_time) Datum, " +
                                        "DATE_FORMAT(t.start_time,'%H:%i') Starttid, " +
                                        "DATE_FORMAT(t.end_time,'%H:%i') Sluttid " +
                                        "FROM time t " +
                                        "JOIN projects p ON t.project_id = p.projects_id " +
                                        "WHERE t.user_id = ? " +
                                        "AND DATE(t.start_time) = ?");
            pstat.setInt(1, userID);
            pstat.setString(2, date);
            rs = pstat.executeQuery();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return rs;
    }
    
    protected ResultSet getColleaguesFromDB(int projectID) {                                         
        //Select till tabell
        try {
            pstat = cn.prepareStatement("SELECT CONCAT_WS(\" \", u.FName, u.LName) AS Namn, MAX(DATE(t.start_time)) AS \"Senast aktiv\" " +
                                        "FROM users u " +
                                        "JOIN time t ON u.user_id = t.user_id " +
                                        "JOIN users_has_projects up ON u.user_id = up.user_id " +
                                        "WHERE t.project_id = ? " +
                                        "GROUP BY Namn");
            pstat.setInt(1, projectID);
            rs = pstat.executeQuery();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return rs;
    }
    
    
    
    
    
    /*
     ////SÄTTER IN ALLA PROJEKT I EN COMBOBOX///
    public void projectCombobox(){
        System.out.println("Projectszsz");
        tGUI.ProjectsComboBox.removeAllItems();

        try {
            pstat = cn.prepareStatement("select project_name from projects");
            rs = pstat.executeQuery();

            while (rs.next()) {

                tGUI.ProjectsComboBox.addItem(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(GUIMethods.class.getName()).log(Level.SEVERE, null, ex);
        }

        tGUI.ProjectsComboBox.setSelectedItem(null);
    }


    public void StatusCombobox(){
        ///SÄTTER IN VAL AV STATUS I EN COMBOBOX///
        tGUI.StatusComboBox.removeAllItems();
        try {
            pstat = cn.prepareStatement("select status from project_status");
            rs = pstat.executeQuery();

            while (rs.next()) {

                tGUI.StatusComboBox.addItem(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(GUIMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        tGUI.StatusComboBox.setSelectedItem(null);
    }

    public void CustomerCombobox(){
        ///SÄTTER IN LISTA AV KUNDER I COMBOBOX///
        tGUI.CustomerComboBox.removeAllItems();
        try {
            pstat = cn.prepareStatement("select customer from customers");
            rs = pstat.executeQuery();

            while (rs.next()) {

                tGUI.CustomerComboBox.addItem(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(GUIMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        tGUI.CustomerComboBox.setSelectedItem(null);
    }


    ///HÄMTAR INFO OM PROJEKT MAN VÄLJER I COMBOBOX OCH SÄTTER I TEXTFIELDS/COMBOBOX///
    public void setProjectInfo() {

        try {
            String projectName = tGUI.ProjectsComboBox.getSelectedItem().toString();
            try {
                pstat = cn.prepareStatement("select projects_id, project_name, project_description, ps.status, c.customer from projects p\n" +
                        "join project_status ps\n" +
                        "on p.project_status_id = ps.project_status_id \n" +
                        "join customers c\n" +
                        "on p.customer_id=c.customer_id\n" +
                        "where project_name = ?");
                pstat.setString(1, projectName);
                rs = pstat.executeQuery();

                while (rs.next()) {

                    tGUI.ProjectTextField1.setText(rs.getString(1));
                    tGUI.ProjectTextField2.setText(rs.getString(2));
                    tGUI.ProjectTextArea1.setText(rs.getString(3));
                    tGUI.StatusComboBox.setSelectedItem(rs.getString(4));
                    tGUI.CustomerComboBox.setSelectedItem(rs.getString(5));
                }

            } catch (SQLException ex) {
                Logger.getLogger(ProjectMethods.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch (Exception e) {
            System.out.println("blabla");
        }
          //   getProjectInfo1();
    }

    public void updateProject() {
        ///SPARAR ÄNDRINGAR MAN GJORT I TEXTFIELDS/COMBOBOX///

        String projectID = tGUI.ProjectTextField1.getText();
        String pn = tGUI.ProjectTextField2.getText();
        String pd = tGUI.ProjectTextArea1.getText();
        String statusID = tGUI.StatusComboBox.getSelectedItem().toString();
        String customerID = tGUI.CustomerComboBox.getSelectedItem().toString();

        ///HÄMTAR/GÖR OM STATUS/CUSTOMER-NAMN TILL ID///
        int sstatusID = getStatusID(statusID);
        int ccustomerID = getCustomerID(customerID);

        try {
            pstat = cn.prepareStatement("update projects set project_name = ?, project_description = ?, project_status_id = ?, customer_id = ? where projects_id = ?");
            pstat.setString(1, pn);
            pstat.setString(2, pd);
            pstat.setInt(3, sstatusID);
            pstat.setInt(4, ccustomerID);
            pstat.setString(5, projectID);
            pstat.executeUpdate();

        }
        catch (SQLException ex) {
            Logger.getLogger(ProjectMethods.class.getName()).log(Level.SEVERE, null, ex);
        } //return;
    }


    ///HÄMTAR STATUS ID FRÅN STATUS NAMN///
    public int getStatusID(String statusName){

        try {
            pstat = cn.prepareStatement("select project_status_id from project_status where status = ?");
            pstat.setString(1, statusName);
            rs = pstat.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        int statusID = 0;
        try {
            if(rs.next()) {
                statusID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statusID;
    }


    ///HÄMTAR CUSTOMER ID FRÅN CUSTOMER NAMN///
    public int getCustomerID(String customerName){

        try {
            pstat = cn.prepareStatement("select customer_id from customers where customer = ?");
            pstat.setString(1, customerName);
            rs = pstat.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        int customerID = 0;
        try {
            if(rs.next()) {
                customerID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customerID;
    }
    ///HÄMTAR USER_ID EFTER FÖR OCH EFTERNAMN///
    public int getUserID(String UserName){

        try {
            pstat = cn.prepareStatement("select user_id from users where FName = ? AND LName = ?");
            pstat.setString(1, UserName);
            rs = pstat.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        int statusID = 0;
        try {
            if(rs.next()) {
                statusID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statusID;
    }

    public void saveNewProject(){
        ///SKAPAR ETT NYTT PROJEKT///
        String pn = tGUI.ProjectTextField2.getText();
        String pd = tGUI.ProjectTextArea1.getText();
        String statusID = tGUI.StatusComboBox.getSelectedItem().toString();
        String customerID = tGUI.CustomerComboBox.getSelectedItem().toString();

        int sstatusID = getStatusID(statusID);
        int ccustomerID = getCustomerID(customerID);

        try {
            pstat = cn.prepareStatement ("insert into projects (project_name, project_description, project_status_id, customer_id) VALUES (?,?,?,?)");
            pstat.setString(1, pn);
            pstat.setString(2,pd);
            pstat.setInt(3, sstatusID);
            pstat.setInt(4, ccustomerID);
            pstat.executeUpdate();

        }catch (SQLException ex) {
            Logger.getLogger(ProjectMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean question(){
        ///DIALOGRUTA SOM FRÅGAR OM MAN VILL SPARA VAD MAN SKRIVIT I TEXTFIELDS///
        boolean q;
        String pn = tGUI.ProjectTextField2.getText();
        String pd = tGUI.ProjectTextArea1.getText();
        String statusID = tGUI.StatusComboBox.getSelectedItem().toString();
        String customerID = tGUI.CustomerComboBox.getSelectedItem().toString();
        {
            int svar = JOptionPane.showConfirmDialog(null,
                    "Namn - " + pn + "\n"+
                            "Beskrivning - " + pd + "\n"+
                            "Status - " + statusID + "\n"+
                            "Kund - " + customerID + "\n\n"+
                            "Vill du spara?\n", "Spara nytt projekt?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (svar == JOptionPane.YES_OPTION){

                saveNewProject();
                q = true;                                }
            else{
                q = false;
            }
        }
        return q;
    }
    ///LÄGGER IN ALLA SKILLS I EN JCOMBOBOX///
    public void getAvailableSkillsProject() {
        try {
            tGUI.SkillBox.removeAllItems();
            try {
                pstat = cn.prepareStatement("select skill from skills");
                rs = pstat.executeQuery();
                while(rs.next()) {
                    tGUI.SkillBox.addItem(rs.getString(1));
                }
            }catch (SQLException ex) {
                Logger.getLogger(ProjectMethods.class.getName()).log(Level.SEVERE, null, ex);
            }
            tGUI.SkillBox.setSelectedItem(null);
        }
        catch (Exception e){
            System.out.println("haja");
        }
    }
    ///LÄGGER TILL ANVÄNDARE MED VALD SKILL I JCOMBOBOX///
    public void setSkillUsers() {
        tGUI.sSkillChosenBox.removeAllItems();
        try {
            String skill = tGUI.SkillBox.getSelectedItem().toString();
            int sskill = getSkillID(skill);

            try {
                pstat = cn.prepareStatement("select users.user_id, users.FName, users.LName\n" +
                        "from users_has_skills\n" +
                        "join users \n" +
                        "on users_has_skills.user_id=users.user_id\n" +
                        "join skills\n" +
                        "on users_has_skills.skill_id=skills.skill_id\n" +
                        "where users_has_skills.skill_id = ?");

                pstat.setInt(1, sskill);
                rs = pstat.executeQuery();

                while(rs.next()) {

                    userid.add(rs.getInt(1));
                    tGUI.sSkillChosenBox.addItem(rs.getString(2) + " " + (rs.getString(3)));

                }
                tGUI.sSkillChosenBox.setSelectedItem(null);
                tGUI.sSkillChosenBox.setEnabled(true);
            } catch (SQLException ex) {
                Logger.getLogger(ProjectMethods.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        catch (Exception e){
            System.err.println("jaja");
        }

        for (int i : userid) {
            System.out.println(i);
        }

    }



    ///HÄMTAR SKILL ID FRÅN SKILL NAMN///
    public int getSkillID(String skill){
        try {
            pstat = cn.prepareStatement("select skill_id from skills where skill = ?");
            pstat.setString(1, skill);
            rs = pstat.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        int skillID = 0;
        try {
            if(rs.next()) {
                skillID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return skillID;
    }




    ///LÄGG TILL ANVÄNDARE SOM ÄR KOPPLADE TILL PROJEKTET I EN JTABLE///
    public void setProjectSkillUsers() {
        try {
            DefaultTableModel model = (DefaultTableModel)tGUI.sSkillTable.getModel();
            int a = tGUI.sSkillTable.getRowCount();
            for(int i = a; i > 0 ; i--) {
                model.removeRow(i -1);
            }

            int pid = Integer.parseInt(tGUI.ProjectTextField1.getText());

            try {
                pstat = cn.prepareStatement("select users.user_id, users.FName, users.LName\n" +
                        "from users_has_projects\n" +
                        "join users\n" +
                        "on users_has_projects.user_id=users.user_id\n" +
                        "join projects\n" +
                        "on users_has_projects.project_id=projects.projects_id\n" +
                        "where users_has_projects.project_id = ?");

                pstat.setInt(1, pid);
                rs = pstat.executeQuery();


                while(rs.next()) {
                //    uidSetOnP.add(id);
                    int id = (rs.getInt(1));
                    String name = (rs.getString(2) + " " + (rs.getString(3)));
                    model.addRow(new Object[]{id, name});
                        uidSetOnP.add(id);

                }
            } catch (SQLException ex) {
                Logger.getLogger(ProjectMethods.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            System.err.println("setprojectskillusers");
        }
    }


    ///LÄGG TILL NYA ANVÄNDARE TILL PROJEKT I EN JTABLE///
    public void setProjectSkillUsers2(){
        try {
            int uid = tGUI.sSkillChosenBox.getSelectedIndex();
            String skill = (String) tGUI.sSkillChosenBox.getSelectedItem();
            DefaultTableModel model = (DefaultTableModel)tGUI.sSkillTable.getModel();


            for(int i = 0; i < tGUI.sSkillTable.getRowCount(); i++) {
                if(tGUI.sSkillTable.getModel().getValueAt(i,1).equals(tGUI.sSkillChosenBox.getSelectedItem())) {
                    return;
                }
            }

            model.addRow(new Object[]{userid.get(uid), skill});
            System.out.println(userid.get(uid));
        }
        catch (Exception e){
            System.out.println("lilo");
        }
    }


    ///HÄMTA PROJEKTID FRÅN PROJEKTNAMN I PROJECTTEXTFIELD2///
    public int getProjectIdFromProjectName(String PName){
        String name = tGUI.ProjectTextField2.getText();
        try {
            pstat = cn.prepareStatement("select projects_id from projects where project_name = ?");
            pstat.setString(1, name);
            rs = pstat.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        int projectID = 0;
        try {
            if(rs.next()) {
                projectID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projectID;
    }

    ///TAR FRAM VILKA USER_ID SOM ÄR KOPPLAD TILL VALT PROJEKT///
    public void getUserIdFromProject (int pid) {

        try {

            pstat = cn.prepareStatement("select user_id from users_has_projects where project_id = ?");
            pstat.setInt(1, pid);

            rs = pstat.executeQuery();

            while (rs.next()) {
                useronproject.add(rs.getInt(1));

            }
        } catch (Exception e){
            System.out.println("noUIDfromPID");
        }

    }

    ///LÄGG IN DATA I USERS_HAS_PROJECT TABELLEN
    public void usersHasProject() {
        int pid;
        ArrayList <Integer> uop = new ArrayList <Integer>();
        try {
            String name = tGUI.ProjectTextField2.getText();
            pid = getProjectIdFromProjectName(name);
            getUserIdFromProject(pid);
            DefaultTableModel model = (DefaultTableModel)tGUI.sSkillTable.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                int uid = (Integer) tGUI.sSkillTable.getValueAt(i, 0);
                uop.add(uid);


                if (!useronproject.contains(uid)){

                    try {
                        pstat = cn.prepareStatement("insert into users_has_projects (user_id, project_id) values (?,?)");
                        pstat.setInt(1, uid);
                        pstat.setInt(2, pid);
                        pstat.executeUpdate();

                        useronproject.clear();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProjectMethods.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                }

            }
            for (int y : useronproject){
                if (!uop.contains(y)){
                    removeUserFromProject(y,pid);
                }
            }
        } catch (Exception e) {
            System.out.println("nixenpixen");
        }
    }
    ///TAR BORT FRÅN USERS_HAS_PROJECTS///
    public void removeUserFromProject(int uid, int pid){
        try{
            pstat = cn.prepareStatement("delete from users_has_projects where user_id = ? AND project_id = ?");
            pstat.setInt(1, uid);
            pstat.setInt(2, pid);
            pstat.executeUpdate();
        }
        catch(Exception e){

        }
    }
    ///TÖMMER ALLA FÄLT I ADMINPROJECTS///
    public void clearAllProjectFields() {
        tGUI.ProjectTextField1.setText("");
        tGUI.ProjectTextField2.setText("");
        tGUI.ProjectTextArea1.setText("");
        tGUI.ProjectsComboBox.removeAllItems();
        tGUI.CustomerComboBox.removeAllItems();
        tGUI.StatusComboBox.removeAllItems();
        tGUI.sSkillChosenBox.removeAllItems();
        tGUI.SkillBox.removeAllItems();
        DefaultTableModel mod = (DefaultTableModel) tGUI.sSkillTable.getModel();
        mod.setRowCount(0);
    }
*/
   /*
    public void getProjectInfo1(){
        // booleans uidSetOnP & uop är redan skapade som kan jämföra sparade användare med nuvarande användare.
        pName1 = tGUI.ProjectTextField2.getText();
        pDesc1 = tGUI.ProjectTextArea1.getText();
        pStatus1 = (String) tGUI.StatusComboBox.getSelectedItem();
        pCustomer1 = (String) tGUI.CustomerComboBox.getSelectedItem();
        
        isSaved1.add(pName1, pDesc1, pStatus1, pCustomer1);
        
    }
    
    public void getProjectInfo2(){
        // booleans uidSetOnP & uop är redan skapade som kan jämföra sparade användare med nuvarande användare.
        pName2 = tGUI.ProjectTextField2.getText();
        pDesc2 = tGUI.ProjectTextArea1.getText();
        pStatus2 = (String) tGUI.StatusComboBox.getSelectedItem();
        pCustomer2 = (String) tGUI.CustomerComboBox.getSelectedItem();
        
        isSaved2.add(pName2, pDesc2, pStatus2, pCustomer2);
                
    }

    */
}


