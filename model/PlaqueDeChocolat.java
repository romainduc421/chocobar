package chocobar.model;

public class PlaqueDeChocolat {

    private boolean[][] masque_choco ;
    private int width, longer;
    private int[] Dims;

    public PlaqueDeChocolat(int nbLig, int nbCol){
        this.masque_choco = new boolean[nbLig][nbCol];

        this.width = this.masque_choco[0].length;
        this.longer = this.masque_choco.length;
        this.Dims = new int[]{this.getLong(),this.getWidth()};
    }

    public int getWidth() {
        return this.width;
    }

    public int getLong() {
        return this.longer;
    }

    public boolean getCarreChoco(int k, int l){
        return this.masque_choco[k][l];
    }

    public void setCarreChoco(int k, int l, boolean mask){
        this.masque_choco[k][l] = mask;
    }

    public int[] getDims() {
        return Dims;
    }
}
