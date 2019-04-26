package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		String user = "student";
		String pwd = "student";
		String sql = "SELECT * FROM film WHERE id = ?";
		int count = 0;

		try (Connection conn = DriverManager.getConnection(URL, user, pwd);
				PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setInt(1, filmId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
//				count++;
				return new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getString("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features"));

			}
		}

		return null;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		String user = "student";
		String pwd = "student";
		String sql = "SELECT id, first_name, last_name FROM Actor WHERE id = ?";
		int count = 0;

		try (Connection conn = DriverManager.getConnection(URL, user, pwd);
				PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setInt(1, actorId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
//				count++;
				return new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
			}
		}
		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		String user = "student";
		String pwd = "student";
		String sql = "SELECT id, first_name, last_name FROM Actor WHERE id = ?";
		int count = 0;

		try (Connection conn = DriverManager.getConnection(URL, user, pwd);
				PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setInt(1, actorId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
//				count++;
				return new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
			}
		}
	}
		return null;
	}

}
