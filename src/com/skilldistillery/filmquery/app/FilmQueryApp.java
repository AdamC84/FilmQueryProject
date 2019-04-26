package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() throws SQLException {
		System.out.println(film);
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {
		boolean programPower = true;

		do {
			System.out.println("Welcome to AIMDB (Adam's Internet Movie Database");
			System.out.println("Please enter what option you would like to do from the menu screen");
			System.out.println("1. Find Film by film id. ");
			System.out.println("2. Find Actor by Actor id. ");
			System.out.println("3. Find Actors by film id. ");
			System.out.println("4. quit.");
			int choice = input.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Please enter the id of the film that you would like to see: ");
				int filmId = input.nextInt();
				Film film = db.findFilmById(filmId);
				System.out.println(film);
				break;
			case 2:
				System.out.println("Please enter the id of the Actor that you would like to see: ");
				int actorId = input.nextInt();
				Actor actor = db.findActorById(actorId);
				System.out.println(actor);
				break;
			case 3:
				System.out
						.println("Please enter the id of the film that the Actor was in that you would like to see: ");
				int filmActorId = input.nextInt();
				List<Actor> actorList = db.findActorsByFilmId(filmActorId);
				for (Actor actor2 : actorList) {

					System.out.println(actor2);
				}
				break;
			}

		} while (programPower);

	}
}
