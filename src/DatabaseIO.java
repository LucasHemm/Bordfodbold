import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseIO implements IFileIO {


    @Override
    public void saveGameData(ArrayList<Match> data) {

    }

    @Override
    public String[] loadTeamData() {
        Connection connection = null;
        String JdbcUrl = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "****";
        String[] teamData = new String[8];
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM sp3.teams ORDER BY id");

            ResultSet result = statement.executeQuery();

            int counter = 0;
            while (result.next()) {

                String teamName = result.getString("name");
                String numOfPlayers = result.getString("numOfPlayers");
                String points = result.getString("points");
                String goalDiff = result.getString("goalDiff");

                String teamBuild = teamName + ", " + numOfPlayers + ", " + points + ", " + goalDiff;

                teamData[counter] = teamBuild;

                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamData;
    }

    @Override
    public ArrayList<String> loadPlayerData() {
        Connection connection = null;
        String JdbcUrl = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "****";
        ArrayList<String> playerData = new ArrayList<>();
        try {
                connection = DriverManager.getConnection(JdbcUrl, username, password);
                PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM sp3.players ORDER BY teamid");

                ResultSet result1 = statement1.executeQuery();
                String p1 = "";
                String p2 = "";
                String p3 = "";
                String p4 = "";
                String p5 = "";
                String p6 = "";
                String p7 = "";
                String p8 = "";
                while (result1.next()) {

                    String playerName = result1.getString("name");
                    int teamid = result1.getInt("teamid");

                   switch(teamid){

                       case 1:

                           p1 += playerName + ", ";
                           break;
                       case 2:
                           p2 += playerName + ", ";

                           break;
                       case 3:
                           p3 += playerName + ", ";

                           break;

                       case 4:
                           p4 += playerName + ", ";

                           break;
                       case 5:
                           p5 += playerName + ", ";

                           break;
                       case 6:
                           p6 += playerName + ", ";

                           break;
                       case 7:
                           p7 += playerName + ", ";

                           break;
                       case 8:
                           p8 += playerName + ", ";
                           break;
                   }
                    playerData.add(p1);
                    playerData.add(p2);
                    playerData.add(p3);
                    playerData.add(p4);
                    playerData.add(p5);
                    playerData.add(p6);
                    playerData.add(p7);
                    playerData.add(p8);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerData;
    }

    @Override
    public ArrayList<String> loadGameData(){
        Connection connection = null;
        String JdbcUrl = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "****";
        ArrayList<String> gameData = new ArrayList<>();



        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM sp3.matches ORDER BY id");
            PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM sp3.teams ORDER BY id");

            ResultSet result1 = statement1.executeQuery();
            ResultSet result2 = statement2.executeQuery();


            String s1 = "";
            String s2 = "";
            String s3 = "";
            String s4 = "";
            String s5 = "";
            String s6 = "";
            String s7 = "";

            while (result1.next()) {
                int id = result1.getInt("id");


                switch(id){

                    case 1:
                        s1 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2")) + ", " + result1.getString("date") +
                        ", " + result1.getString("time") + ", " + result1.getString("result");
                        break;
                    case 2:
                        s2 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2")) + ", " + result1.getString("date") +
                                ", " + result1.getString("time") + ", " + result1.getString("result");
                        break;
                    case 3:
                        s3 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2")) + ", " + result1.getString("date") +
                                ", " + result1.getString("time") + ", " + result1.getString("result");
                        break;

                    case 4:
                        s4 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2")) + ", " + result1.getString("date") +
                                ", " + result1.getString("time") + ", " + result1.getString("result");
                        break;
                    case 5:

                            s5 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2")) + ", " + result1.getString("date") +
                                    ", " + result1.getString("time") + ", " + result1.getString("result");


                        break;
                    case 6:
                        s6 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2")) + ", " + result1.getString("date") +
                                ", " + result1.getString("time") + ", " + result1.getString("result");
                        break;
                    case 7:
                        s7 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2")) + ", " + result1.getString("date") +
                                ", " + result1.getString("time") + ", " + result1.getString("result");
                        break;

                }

            }
            gameData.add(s1);
            gameData.add(s2);
            gameData.add(s3);
            gameData.add(s4);

            if(s5.equals("") == false){
                System.out.println("DO NOT DO THIS");
                gameData.add(s5);

            }
            if(s6.equals("") == false){
                gameData.add(s6);

            }
            if(s7.equals("") == false){
                gameData.add(s7);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameData;

    }

    private String getNameFromID(int index){
        Connection connection = null;
        String JdbcUrl = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "****";
        String s = "";

        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement1 = connection.prepareStatement("SELECT name FROM sp3.teams where id = ? ORDER BY id");


            statement1.setInt(1,index);
            ResultSet result1 = statement1.executeQuery();

            while(result1.next()){


                s = result1.getString("name");
                if(s == null){
                    s= " ";
                }
            }


        } catch(SQLException e){
            e.printStackTrace();
        }

        return s;
    }



    @Override
    public void saveTeamData(ArrayList<Team> data) {

    }

    @Override
    public void savePlayerData(ArrayList<Team> teams) {

    }



    @Override
    public void clear() {

    }

    @Override
    public void fill() {

    }
}
