package entidades;

import Excepciones.BancoException;

public class CuentaCorriente extends CuentaBancaria{


    public CuentaCorriente(float saldo, String numeroDeCuenta, String propietariio) {
        super(saldo, numeroDeCuenta, propietariio);
        this.tipoCuenta = "CC";
    }

    @Override
    public void consignarDinero(float dinero) {
        if (dinero > 0) {
            this.saldo += dinero;
            this.numeroDeConsignaciones++;
        }
    }

    @Override
    public void retirarDinero(float dinero) throws BancoException {
        if (dinero <= this.saldo && numeroDeRetiros <6) {
            this.saldo -= dinero;
            this.numeroDeRetiros++;
        } else{
            throw new BancoException("EL máximo de retiros es Cinco");
        }
    }

    @Override
    public void transferirDinero(String tipoDeCuenta, String numeroDeCuenta, float dinero){
        if (tipoDeCuenta.equals("cuenta de ahorros")){

            if (dinero <= this.saldo) {
                if (numeroDeRetiros > 3) {
                    this.saldo -= (dinero + (dinero * 0.01));
                    this.numeroDeRetiros++;
                } else {
                    this.saldo -= dinero;
                    this.numeroDeRetiros++;
                }
                //Update
            }else if(tipoDeCuenta.equals("cuenta corriente")){
                if (dinero <= this.saldo) {
                    float dineroTransferido =  this.saldo -= (dinero + (dinero * 0.015));
                    //Update -> repositorioCuenta.transferir(numeroDeCuenta, dineroTransferido)
                }

            }else{
                System.out.println("");
            }
        }
    }
}
