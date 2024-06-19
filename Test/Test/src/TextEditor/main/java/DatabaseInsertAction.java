import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseInsertAction extends AbstractAction {
    private JTextPane textPane;
    private String dbUrl;
    private String user;
    private String password;

    public DatabaseInsertAction(JTextPane textPane, String dbUrl, String user, String password) {
        super("Вставить из БД");
        this.textPane = textPane;
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String query = JOptionPane.showInputDialog("Введите SQL-запрос:");
        System.out.print(query);
        if (query != null && !query.trim().isEmpty()) {
            insertDataFromDatabase(query);
        }
    }
    private void insertDataFromDatabase(String query) {

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/teacher_cathedra", "postgres", "12345");

             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            StringBuilder sb = new StringBuilder();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    sb.append(resultSet.getString(i)).append(" ");
                }
                sb.append("\n");
            }
            textPane.replaceSelection(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка при выполнении запроса: " + ex.getMessage());
        }
    }
}