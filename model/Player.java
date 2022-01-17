package chocobar.model;


public class Player {
    private String playerName;

    public Player(String nom){
        setNom(nom);
    }

    public String getName(){
        return this.playerName;
    }

    public void setNom(String nom){
        this.playerName = nom;
    }

    @Override
    public String toString(){
        return playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Player player = (Player) o;
        return playerName.equals(player.playerName);
    }

}
