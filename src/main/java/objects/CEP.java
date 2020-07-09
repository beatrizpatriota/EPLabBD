package objects;

import utils.Generator;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEP {
    String cep = "00000000";
    String cidade = "default";
    String estado = "default";
    String bairro = "default";
    String logradouro = "default";
    int num = 0;
    String complemento = "default";


    public CEP(String linha) {
        String[] arquivo = linha.split(";");

        Generator generator = new Generator();
        this.cep = arquivo[0];
        this.cidade = arquivo[1].split("/")[0].replaceAll("\'","");
        this.estado = arquivo[1].split("/")[1].replaceAll("\'","");
        this.bairro = arquivo[2].replaceAll("\'","");
        this.logradouro = arquivo[3].split(",")[0].replaceAll("\'","");
            this.num = extractNumber(arquivo[3]).isEmpty()?generator.randomiza(400) + 1:Integer.parseInt(extractNumber(arquivo[3]));
        this.complemento = generator.randomiza(100) > 30 ? "" : "apto " + (generator.randomiza(100) + 1);
    }

    private String extractNumber(String split) {
        LinkedList<String> numbers = new LinkedList<>();
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(split);
        int limit = 5;
        while (m.find() && limit<5) {
            numbers.add(m.group());
            limit++;
        }
        StringBuilder finalNumber = new StringBuilder();
        numbers.forEach(finalNumber::append);
        return finalNumber.toString();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
