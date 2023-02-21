package gui;

public class password {
    String lowercase = "abcdefghijklmnopqrstuvwxyz";
    String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String specialchar = "-_*!§%&$#@";
    String num ="1234567890";
    String pass =" ";
    {for (int i=0; i<10;i++){
    int rand = (int)(5*Math.random());
    switch (rand) {case 0:
    pass += String.valueOf((int)(Math.random()));
    break;
        case 1:
    rand = (int)(lowercase.length()*Math.random());
    pass += String.valueOf(lowercase.charAt(rand));
    break;
        case 2:
            rand = (int)(uppercase.length()*Math.random());
            pass += String.valueOf(uppercase.charAt(rand));
            break;
        case 3:
            rand = (int)(specialchar.length()*Math.random());
            pass += String.valueOf(specialchar.charAt(rand));
            break;
        case 4:
            rand = (int)(num.length()*Math.random());
            pass += String.valueOf(num.charAt(rand));
            break;

    }}}
}
