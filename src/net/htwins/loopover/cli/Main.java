package net.htwins.loopover.cli;

import java.util.Scanner;

import net.htwins.loopover.internal.Board;

public class Main {
    public static void main(String[] args) {
        System.out.println("Loading...");
        Scanner scan = new Scanner(System.in);
        Board board = null;
        int numLength = 0;
        System.out.println("Loopover by Cary Huang\nProgrammed by Ray Redondo\nCLI and internal library copyright (C) 2019 Lightning Creations\nSolving engine copyright (C) UNFINISHED Lightning Creations\nScrambling engine copyright (C) UNFINISHED Lightning Creations\nIt is illegal to distribute this game under any conditions unless under express written/typed permission from Lightning Creations or Ray Redondo. (this includes you, Cary)\nType \"help\" to list available commands.");
        while(true) {
            System.out.print("> ");
            String input = scan.nextLine();
            if(input.length() == 0) System.out.println("Please enter a command.");
            else {
                // Handle command
                if("board".equals(input)) {
                    if(board != null) {
                        System.out.print("Replace existing board? (y or n) ");
                        String response = scan.nextLine();
                        if("y".equals(response)) {
                            System.out.println("Proceeding...");
                        } else if("n".equals(response)) {
                            System.out.println("Cancelling...");
                            Util.flushTerminals(); // Make Eclipse display things in order
                            continue;
                        } else {
                            System.out.println("Invalid reponse, assuming no. Cancelling...");
                            Util.flushTerminals(); // Make Eclipse display things in order
                            continue;
                        }
                    }
                    System.out.print("How wide? ");
                    String widthStr = scan.nextLine();
                    if(!widthStr.matches("\\d+")) {
                        System.err.println("Error: \"" + widthStr + "\" isn't a valid number!");
                        Util.flushTerminals();
                        continue;
                    }
                    System.out.print("How tall? ");
                    String heightStr = scan.nextLine();
                    if(!heightStr.matches("\\d+")) {
                        System.err.println("Error: \"" + heightStr + "\" isn't a valid number!");
                        Util.flushTerminals(); // Make Eclipse display things in order
                        continue;
                    }
                    int width  = Integer.valueOf(widthStr);
                    int height = Integer.valueOf(heightStr);
                    board = new Board(width, height);
                    numLength = (int) Math.ceil(Math.log10(width*height));
                } else if("help".equals(input)) {
                    System.out.println("Commands:\nboard                      Create a new board\nhelp                        Display this help\nmovecolumn   Move a column a specified amount\nmoverow         Move a row a specified amount\nquit                        Exit Loopover CLI\nview                           View the board");
                } else if("movecolumn".equals(input)) {
                    System.out.print("Which column? ");
                    String columnStr = scan.nextLine();
                    if(!columnStr.matches("\\d+")) {
                        System.err.println("Error: \"" + columnStr + "\" isn't a valid number!");
                        Util.flushTerminals();
                        continue;
                    }
                    int column = Integer.valueOf(columnStr);
                    if(column >= board.getWidth()) {
                        System.err.println("Error: \"" + columnStr + "\" is too big!");
                        Util.flushTerminals();
                        continue;
                    }
                    System.out.print("How far? ");
                    String distStr = scan.nextLine();
                    if(!distStr.matches("[+-]?\\d+")) {
                        System.err.println("Error: \"" + distStr + "\" isn't a valid number!");
                        Util.flushTerminals(); // Make Eclipse display things in order
                        continue;
                    }
                    int dist = Integer.valueOf(distStr);
                    board.moveColumn(column, dist);
                    for(int y = 0; y < board.getHeight(); y++) {
                        for(int x = 0; x < board.getWidth(); x++) {
                            System.out.printf("%0" + numLength + "d ", board.getSquare(x, y));
                        }
                        System.out.println();
                    }
                } else if("moverow".equals(input)) {
                    System.out.print("Which row? ");
                    String rowStr = scan.nextLine();
                    if(!rowStr.matches("\\d+")) {
                        System.err.println("Error: \"" + rowStr + "\" isn't a valid number!");
                        Util.flushTerminals();
                        continue;
                    }
                    int row = Integer.valueOf(rowStr);
                    if(row >= board.getHeight()) {
                        System.err.println("Error: \"" + rowStr + "\" is too big!");
                        Util.flushTerminals();
                        continue;
                    }
                    System.out.print("How far? ");
                    String distStr = scan.nextLine();
                    if(!distStr.matches("[+-]?\\d+")) {
                        System.err.println("Error: \"" + distStr + "\" isn't a valid number!");
                        Util.flushTerminals(); // Make Eclipse display things in order
                        continue;
                    }
                    int dist = Integer.valueOf(distStr);
                    board.moveRow(row, dist);
                    for(int y = 0; y < board.getHeight(); y++) {
                        for(int x = 0; x < board.getWidth(); x++) {
                            System.out.printf("%0" + numLength + "d ", board.getSquare(x, y));
                        }
                        System.out.println();
                    }
                } else if("quit".equals(input)) {
                    System.out.println("Exiting...");
                    break;
                } else if("view".equals(input)) {
                    if(board == null) {
                        System.err.println("No board to view!");
                    } else {
                        for(int y = 0; y < board.getHeight(); y++) {
                            for(int x = 0; x < board.getWidth(); x++) {
                                System.out.printf("%0" + numLength + "d ", board.getSquare(x, y));
                            }
                            System.out.println();
                        }
                    }
                } else {
                    System.err.println("Error: \"" + input + "\" is not a valid command!");
                }
            }
            Util.flushTerminals(); // Make Eclipse display things in order
        }
        scan.close();
    }
}
