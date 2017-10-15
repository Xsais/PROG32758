/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: QLab6.java
 * Main class: com.servlet.QLab6.java
 * Other Files in this Project:
 *  web
 *     └── WEB-INF
 *        └── web.xml
 * Assignment: Quiz Lab 6
 * Creation Date: 10, 2017 14
 * Last Modified: 10, 2017 14
 * Java Version: 1.8.0_141
 * Description: Command center for the game
 * ----------------------------------------------------------------------------+
 */

package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Boulet_list
 */
@WebServlet(name = "QLab6")
public class QLab6 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QLab6() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		StringBuilder builder = new StringBuilder();

		for (int i =- 0; i < 9; ++i) {

			builder.append(String.format("<li>%d</li>", (int)(Math.random() * 99) + 1));
		}

		out.println(String.format("<!DOCTYPE html><html><head><title>QLab 6</title>" +
				"<link rel='stylesheet' type='text/css' href='res/css-main.css'></head>" +
				"<body><h1>A Boulet List</h1><br /><br /><ul>%s</ul></body></html>", builder));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
