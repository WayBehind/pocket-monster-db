package wb.pocket_moster_db.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBMonsterService {
    // C
    public int addMonster(String name, int trainerId) {
        final String insert = "INSERT INTO monster (name, trainer_id) VALUES (?, ?)";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, trainerId);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // R
    public void printMonster(int id) {
        final String query = "SELECT * FROM monster WHERE id = ?";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt("id") +
                        " | name: " + resultSet.getString("name") +
                        " | trainer_id: " + resultSet.getInt("trainer_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // U
    public void updateMonster(int id, String name, int trainerId) {
        final String update = "UPDATE monster SET name = ?, trainer_id = ? WHERE id = ?";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, trainerId);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // D
    public void deleteMonster(int id) {
        final String delete = "DELETE FROM monster WHERE id = ?";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Print all monsters
    public void printAllMonsters() {
        final String query = "SELECT * FROM monster";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt("id") +
                        " | name: " + resultSet.getString("name") +
                        " | trainer_id: " + resultSet.getInt("trainer_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update trainer_id (Catch)
    public void cachMonster(int id, int trainerId) {
        final String update = "UPDATE monster SET trainer_id = ? WHERE id = ?";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1, trainerId);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update trainer_id to NULL (Release)
    public void releaseMonster(int id) {
        final String update = "UPDATE monster SET trainer_id = NULL WHERE id = ?";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Print monster with trainer_id = NULL (free monster)
    public void printWildMonsters() {
        final String query = "SELECT * FROM monster WHERE trainer_id IS NULL";

        try (Connection connection = HikariDBSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt("id") +
                        " | name: " + resultSet.getString("name") +
                        " | trainer_id: " + resultSet.getInt("trainer_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
