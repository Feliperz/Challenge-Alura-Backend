package com.alura.conversor.felipe.componentes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Divisas {

    private String code;
    private Double number;
    private String convert;
    private Double valueConvert;


    public Divisas(DivisasApi divisa, Double monto) {
        this.code = divisa.base_code();
        this.valueConvert = Double.parseDouble(divisa.conversion_result());
        this.convert = divisa.target_code();
        this.number = monto;

    }


    @Override
    public String toString() {
        return "============================="
                +"\nDivisa a Convertir: "
                + this.code +
                "     Valor: " + this.number +
                "\nDivisa a Convertir: " + this.convert
                + "       Resultado: " + this.valueConvert
                + "\n ========================";
    }
}
