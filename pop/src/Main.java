import pop.POPCommand;
import pop.POPCore;

import java.io.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        POPCore core;
        //connect
        while(true){
            System.out.print("> ");
            String[] talentLine = scanner.nextLine().split(" ");
            if(talentLine.length != 3){
                System.out.println("Please input the right form to talent!");
                continue;
            }
            String popServer = talentLine[1];
            int port = Integer.valueOf(talentLine[2]);

            try{
                core = new POPCore(popServer,port);
            }catch (IOException e){
                System.out.println("FAIL TO CONNECT!!!");
                System.out.println("Form: talent [POPServer] [PORT]");
                continue;
            }
            break;
        }


        //user and pass
        while (true) {
            System.out.print("> ");
            String[] userInfo = scanner.nextLine().split(" ");

            if (userInfo.length != 2 || !userInfo[0].equals("user")) {
                System.out.println("Please input the username.");
                continue;
            }
            try {
                core.execute(POPCommand.USER(userInfo[1]), "+OK");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Login error!!!");
            }
            System.out.print("> ");
            String[] passInfo = scanner.nextLine().split(" ");
            if (passInfo.length != 2 || !passInfo[0].equals("pass")) {
                System.out.println("Please input the password.");
                continue;
            }
            try {
                core.execute(POPCommand.PASS(passInfo[1]), "+OK");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Login error!!!");
            }

            break;
        }

        //execute the instructions
        while (true) {
            System.out.print("> ");
            String[] instructions = scanner.nextLine().split(" ");
            String name = "";
            if (instructions.length != 1) {
                name = instructions[1];
            }

            if (instructions[0].equals("stat")) {
                try {
                    String response = core.execute(POPCommand.STAT(), "+OK");
                    System.out.println(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (instructions[0].equals("list")) {
                try {
                    String response = core.execute(POPCommand.LIST(name), "+OK");
                    System.out.println(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (instructions[0].equals("retr")) {
                if (name.equals("")) {
                    System.out.println("Example: retr [num]");
                    continue;
                }
                try {
                    String response = core.execute(POPCommand.RETR(name), "+OK");
                    System.out.println(response);
                    System.out.println(core.getDetails());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (instructions[0].equals("dele")) {
                if (name.equals("")) {
                    System.out.println("Example: dele [num]");
                    continue;
                }
                try {
                    String response = core.execute(POPCommand.DELE(name), "+OK");
                    System.out.println(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else if (instructions[0].equals("quit")) {
                return;
            } else {
                System.out.println("Please use the cmd like stat, list, retr, dele or quit.");
            }

        }
    }
}
