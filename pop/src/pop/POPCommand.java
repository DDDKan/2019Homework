package pop;

public class POPCommand {

    //登录
    public static String USER(String username){
        return "USER " + username;
    }

    public static String PASS(String password){
        return "PASS " + password;
    }

    //STAT
    public static String STAT(){
        return "STAT";
    }

    //LIST
    public static String LIST(String name) {
        return "LIST " + name;
    }

    //RETR
    public static String RETR(String filename){
        return "RETR " + filename;
    }

    //DELE
    public static String DELE(String filename){
        return "DELE " + filename;
    }
}
