package utils;

public class Generator {

    public String telefone(boolean ddd){
        String telefone = "XXXX-XXXX";
        for (int i = 0; i < 8; i++) {
            telefone = telefone.replaceFirst("X",String.valueOf(randomiza(9)));
        }
        return ddd?ddd()+telefone:telefone;
    }

    private String ddd() {
        return randomiza(100)>80?"(11)": "("+(randomiza(9)+10)+")";
    }

    public String celular(){
        return ddd()+"9"+telefone(false);
    }


    public String sexo(){
        return randomiza(100)<53?"Masculino":"Feminino";
    }

    public String data(){
        String ini = "19";
        String decada = String.valueOf(randomiza(39)+60);
        int mes = randomiza(11)+1;
        int dia = randomiza(27)+1;
        String mesDia = (mes<10? ("0"+mes):String.valueOf(mes))+"-"+
                (dia<10? ("0"+dia):String.valueOf(dia));
        return ini+decada+"-"+mesDia;
    }

    public String email(String name){
        String email;
        switch (randomiza(10)) {
            case 1:
                email ="@gmail.com";
                break;
            case 2:
                email ="@hotmail.com";
                break;
            case 3:
                email ="@yahoo.com";
                break;
            case 4:
                email ="@yahoo.com.br";
                break;
            case 5:
                email ="@outlook.com";
                break;
            case 6:
                email ="@live.com";
                break;
            case 7:
                email ="@uol.com";
                break;
            case 8:
                email ="@usp.br";
                break;
            case 9:
                email ="@aol.es";
                break;
            case 0:
                email = "@gov.br";
                break;
            default: email = "@example.com";

        }
        return name+"_"+(randomiza(1000)+1)+email;
    }


    public int randomiza(int n) {
        int ranNum = (int) (Math.random() * n);
        return ranNum;
    }

    private int mod(int dividendo, int divisor) {
        return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
    }

    public String cpf(boolean comPontos) {
        int n = 9;
        int n1 = randomiza(n);
        int n2 = randomiza(n);
        int n3 = randomiza(n);
        int n4 = randomiza(n);
        int n5 = randomiza(n);
        int n6 = randomiza(n);
        int n7 = randomiza(n);
        int n8 = randomiza(n);
        int n9 = randomiza(n);
        int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

        d1 = 11 - (mod(d1, 11));

        if (d1 >= 10)
            d1 = 0;

        int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

        d2 = 11 - (mod(d2, 11));

        String retorno = null;

        if (d2 >= 10)
            d2 = 0;
        retorno = "";

        if (comPontos)
            retorno = "" + n1 + n2 + n3 + '.' + n4 + n5 + n6 + '.' + n7 + n8 + n9 + '-' + d1 + d2;
        else
            retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;

        return retorno;
    }

    public String hora() {
        return (randomiza(14)+8)+":00:00";
    }

    public String data(int ano) {
        int mes = randomiza(11)+1;
        int dia = randomiza(27)+1;
        String mesDia = (mes<10? ("0"+mes):String.valueOf(mes))+"-"+
                (dia<10? ("0"+dia):String.valueOf(dia));
        return ano+"-"+mesDia;
    }
}
