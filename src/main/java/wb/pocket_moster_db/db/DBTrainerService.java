package wb.pocket_moster_db.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBTrainerService {
    // C
    public int addTrainer(String name) {
        final String insert = "INSERT INTO trainer (name) VALUES (?)";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, name);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // R
    public void printTrainer(int id) {
        final String query = "SELECT * FROM trainer WHERE id = ?";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt("id") +
                        " | name: " + resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // U
    public void updateTrainer(String name, int trainerId) {
        final String update = "UPDATE trainer SET name = ?";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // D
    public void deleteTrainer(int id) {
        final String delete = "DELETE FROM trainer WHERE id = ?";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Print all trainers
    public void printAllTrainers() {
        final String query = "SELECT * FROM trainer";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt("id") +
                        " | name: " + resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Print trainer's monsters
    public void printTrainersMonsters() {
        String query = "SELECT t.name, m.name FROM trainer t JOIN monster m ON t.id = m.trainer_id ORDER BY t.name";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("t.name") + " has " + resultSet.getString("m.name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Print trainers by amount of monsters
    public void printTrainersByMonsterAmount() {
        String query = "SELECT t.name, COUNT(m.id) as count FROM trainer t JOIN monster m ON t.id = m.trainer_id GROUP BY t.name ORDER BY count DESC";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("t.name") + " has " + resultSet.getInt("count") + " monsters");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
