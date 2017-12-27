package com.utils.servlets;

import com.utils.helperclasses.ConnectToDB;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/game/write")
public class Writer extends HttpServlet {

    private ConnectToDB dbConnection;

    /**
     * @throws IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");

        if (login == null) {

            return;
        }

        if (dbConnection == null) {

            dbConnection = (ConnectToDB) request.getServletContext().getAttribute("db_conection");
        }

        try {

            String score = request.getParameter("score");
            String credit = request.getParameter("credit");

            dbConnection.executeUpdate(String.format("UPDATE players SET `Score`=%s, `Credits`=%s WHERE `Login`=%s"
                    , score, credit, login));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

