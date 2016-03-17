package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    protected Hashtable<String, SoccerPlayer> table = new Hashtable<String, SoccerPlayer>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {
        if(table.containsKey(firstName + "##" + lastName)){
            return false;
        }
        else{
            table.put((firstName + "##" + lastName), new SoccerPlayer(firstName, lastName, uniformNumber,
                    teamName));
            return true;
        }
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        if(table.containsKey(firstName+"##"+lastName)){
            table.remove((firstName + "##" + lastName));
            return true;
        }
        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {
        if(table.containsKey(firstName+"##"+lastName)){
            return table.get((firstName+"##"+lastName));
        }
        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        if(table.containsKey(firstName+"##"+lastName)){
            table.get((firstName+"##"+lastName)).bumpGoals();
            return true;
        }
        return  false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        if(table.containsKey(firstName+"##"+lastName)){
            table.get((firstName+"##"+lastName)).bumpAssists();
            return true;
        }
        return  false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        if(table.containsKey(firstName+"##"+lastName)){
            table.get((firstName+"##"+lastName)).bumpShots();
            return true;
        }
        return  false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        if(table.containsKey(firstName+"##"+lastName)){
            table.get((firstName+"##"+lastName)).bumpSaves();
            return true;
        }
        return  false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        if(table.containsKey(firstName+"##"+lastName)){
            table.get((firstName+"##"+lastName)).bumpFouls();
            return true;
        }
        return  false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        if(table.containsKey(firstName+"##"+lastName)){
            table.get((firstName+"##"+lastName)).bumpYellowCards();
            return true;
        }
        return  false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        if(table.containsKey(firstName+"##"+lastName)){
            table.get((firstName+"##"+lastName)).bumpRedCards();
            return true;
        }
        return  false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {
        int i=0;
        if(teamName == null){
            return table.size();
        }
        else{
            for(SoccerPlayer s: table.values()){
                if(s.getTeamName().equals(teamName)) {
                    i++;
                }
            }
            return i;
        }

	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        int i = 0;
        if(teamName == null){
            if(idx == 0){
                for (SoccerPlayer s : table.values()) {
                    i++;
                    if (i == 1) {
                        return s;
                    }
                }
            }
            for (SoccerPlayer s : table.values()) {
                i++;
                if (i == idx) {
                    return s;
                }
            }
        }
        for (SoccerPlayer s : table.values()) {
            if (s.getTeamName().equals(teamName)) {
                if (i == idx) {
                    return s;
                }
                i++;
            }
        }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        return file.exists();

	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        try{
            PrintWriter pw = new PrintWriter(file);
            for (SoccerPlayer s : table.values()) {
                pw.println(logString(s.getFirstName()));
                pw.println(logString(s.getLastName()));
                pw.println(logString(s.getTeamName()));
                pw.println(logString("" + s.getUniform()));
                pw.println(logString("" + s.getGoals()));
                pw.println(logString("" + s.getAssists()));
                pw.println(logString("" + s.getShots()));
                pw.println(logString("" + s.getSaves()));
                pw.println(logString("" + s.getFouls()));
                pw.println(logString("" + s.getYellowCards()));
                pw.println(logString("" + s.getRedCards()));

            }
            pw.close();
            return true;
        }
        catch(FileNotFoundException e){
            return false;
        }

    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        return new HashSet<String>();
	}

}
