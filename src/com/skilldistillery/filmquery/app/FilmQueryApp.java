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

//	private void test() throws SQLException {
//		System.out.println(film);
//	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {
		boolean programPower = true;

		do {
			System.out.println("Welcome to A.I.M.D.B (Adam's Internet Movie Database)");
			System.out.println("Please enter what option you would like to do from the menu screen");
			System.out.println("1. Find Film by film id. ");
			System.out.println("2. Find Actor by Actor id. ");
			System.out.println("3. Find Actors by film id. ");
			System.out.println("4. Find film by keyword.");
			System.out.println("5. exit.");
			int choice = 0;
			try {
				choice = input.nextInt();
			} catch (Exception e1) {
				System.out.println("Invalid entry, please try again.");
			}

			switch (choice) {
			case 1:
				System.out.println("Please enter the id of the film that you would like to see: ");
				int filmId = 0;
				try {
					filmId = input.nextInt();
				} catch (Exception e) {
					System.out.println("Invalid entry. Please try again.");
					;
				}
				Film film = db.findFilmById(filmId);
				System.out.println(film);
				continue;
			case 2:
				System.out.println("Please enter the id of the Actor that you would like to see: ");
				int actorId = 0;
				try {
					actorId = input.nextInt();
				} catch (Exception e) {
					System.out.println("Invalid input. Please try again.");
				}
				Actor actor = db.findActorById(actorId);
				System.out.println(actor);
				continue;
			case 3:

				System.out
						.println("Please enter the id of the film that the Actor was in that you would like to see: ");
				int filmActorId = 0;
				try {
					filmActorId = input.nextInt();
				} catch (Exception e) {
					System.out.println("Invalid entry. Please try again.");
				}
				List<Actor> actorList = db.findActorsByFilmId(filmActorId);
				for (Actor actor2 : actorList) {
					if (actor2.equals(null)) {
						System.out.println("Sorry, your query had no resluts.");
					} else {
						System.out.println(actor2);
					}
				}
				continue;
			case 4:
				System.out.println("Please enter the keyword to search on: ");
				String keyword = "";
				try {
					keyword = input.next();
				} catch (Exception e) {
					System.out.println("Invalid entry. Please try again.");
				}
				List<Film> films = db.findFilmByKeyword(keyword);
				for (Film film2 : films) {
					if (film2.equals(null)) {
						System.out.println("Sorry, your query had no results.");
					} else {
						System.out.println(film2);
					}
				}
				continue;
			case 5:
				System.out.println("Goodbye!");
				programPower = false;
				break;
			default: {
				System.out.println("Not a valid entry.");
			}
			}

		} while (programPower);

	}
}
