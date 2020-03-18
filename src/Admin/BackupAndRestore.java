package Admin;

import General.ErrorBox;
import Homepages.SystemAdminHomepage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class BackupAndRestore {
    public static void Backupdbtosql() {
        try {


            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = SystemAdminHomepage.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            System.out.println(jarDir);
            /*NOTE: Creating Database Constraints*/
            String dbName = "atsdb";
            String dbUser = "root";
            String dbPass = "admin";

            /*NOTE: Creating Path Constraints for folder saving*/
            /*NOTE: Here the backup folder is created for saving inside it*/
            String folderPath = jarDir + "\\backup";

            /*NOTE: Creating Folder if it does not exist*/
            File f1 = new File(folderPath);
            f1.mkdir();

            /*NOTE: Creating Path Constraints for backup saving*/
            /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
            String savePath = "\"" + jarDir + "\\backup\\" + "backup.sql\"";

            /*NOTE: Used to create a cmd command*/
            String executeCmd = "D:\\Program Files\\MySQL\\MySQL Workbench 8.0 CE\\mysqldump -u" + dbUser + " -p" + dbPass + " --databases " + dbName + " -r " + savePath;

            /*NOTE: Executing the command here*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                ErrorBox.display("Success","The databsae has been backed up.");
            } else {
                System.out.println("Backup Failure");
            }

        } catch (URISyntaxException | IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
    }

    public static void Restoredbfromsql(String s) {
        try {
            /*NOTE: String s is the mysql file name including the .sql in its name*/
            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = SystemAdminHomepage.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            /*NOTE: Creating Database Constraints*/
            String dbName = "atsdb";
            String dbUser = "root";
            String dbPass = "admin";

            /*NOTE: Creating Path Constraints for restoring*/
            String restorePath = jarDir + "\\backup" + "\\" + s;

            /*NOTE: Used to create a cmd command*/
            /*NOTE: Do not create a single large string, this will cause buffer locking, use string array*/
            String[] executeCmd = new String[]{"D:\\Program Files\\MySQL\\MySQL Workbench 8.0 CE\\mysql.exe", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + restorePath};

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                ErrorBox.display("Success","The databsae has been restored.");
            } else {
                JOptionPane.showMessageDialog(null, "Error at restoring");
            }


        } catch (URISyntaxException | IOException | InterruptedException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
        }

    }
}
