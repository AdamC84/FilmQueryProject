package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		String user = "student";
		String pwd = "student";
		String sql = "SELECT f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features,l.name FROM film f JOIN language l ON f.language_id = l.id WHERE f.id = ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pwd);
				PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setInt(1, filmId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
//				count++;
				return new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getString("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features"), rs.getString("name"),
						(findActorsByFilmId(rs.getInt("id"))));
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
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actorList = new ArrayList<>();
		String user = "student";
		String pwd = "student";
		String sql = "SELECT * FROM actor JOIN film_actor on actor.id = film_actor.actor_id JOIN film on film_actor.film_id = film.id WHERE film.id = ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pwd);
				PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setInt(1, filmId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
				actorList.add(actor);
			}
			if (actorList.equals(null)) {
				return null;
			} else {
				return actorList;
			}
		}
	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) throws SQLException {
		List<Film> films = new ArrayList<>();
		String user = "student";
		String pwd = "student";
		String sql = "SELECT f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features,l.name FROM film f JOIN language l ON f.language_id = l.id WHERE f.title like ? OR f.description like ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pwd);
				PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setString(1, "%" + keyword + "%");
			pst.setString(2, "%" + keyword + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Film film = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getString("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features"), rs.getString("name"),
						(findActorsByFilmId(rs.getInt("id"))));
				films.add(film);
			}
			if (films.equals(null)) {
				return null;
			} else {
				return films;
			}
		}
	}

}
