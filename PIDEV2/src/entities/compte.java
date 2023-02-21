package entities;

import com.sun.xml.internal.bind.v2.model.core.ID;
import gui.password;

import java.util.Objects;

public class compte {


    ;
    private int id;
    public static String login;
    public static password mdp;

    public compte(int id, String login, password mdp) {
        this.id = id;
        this.login = login;
        this.mdp = mdp;
    }

    public compte() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public password getMdp() {
        return mdp;
    }

    public void setMdp(password mdp) {
        this.mdp = mdp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        compte compte = (compte) o;
        return id == compte.id && login.equals(compte.login) && mdp.equals(compte.mdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, mdp);
    }

    @Override
    public String toString() {
        return "compte{" +
                "mdp=" + mdp +
                '}';
    }
}
