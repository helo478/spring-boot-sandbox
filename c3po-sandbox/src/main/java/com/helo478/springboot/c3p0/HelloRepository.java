package com.helo478.springboot.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloRepository {

	private static final String CREATE_TABLE_QUERY = "CREATE TABLE hello ("
			+ " id INT PRIMARY KEY AUTO_INCREMENT, "
			+ " hello VARCHAR(255) NOT NULL);";
			
	private static final String SELECT_HELLO_QUERY = "SELECT hello FROM hello;";
	
	private static final String INSERT_HELLO_QUERY = "INSERT INTO hello (hello) VALUES (?);";

	@Autowired
	private DataSource dataSource;

	public String getHello() {

		final StringBuilder stringBuilder = new StringBuilder("Hellos:\n");

		try (final Connection connection = dataSource.getConnection();
				final Statement statement = connection.createStatement()) {

			final ResultSet resultSet = statement.executeQuery(SELECT_HELLO_QUERY);

			while (resultSet.next()) {

				stringBuilder.append(resultSet.getString(1)).append("\n");
			}

		} catch (final SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}

		return stringBuilder.toString();
	}

	public void putHello(final String hello) {

		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement statement = connection.prepareStatement(INSERT_HELLO_QUERY)) {

			statement.setString(1, hello);
			statement.execute();

		} catch (final SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	
	public void createTable() {
		
		try (final Connection connection = dataSource.getConnection();
				final Statement statement = connection.createStatement()) {

			statement.execute(CREATE_TABLE_QUERY);

		} catch (final SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

}
