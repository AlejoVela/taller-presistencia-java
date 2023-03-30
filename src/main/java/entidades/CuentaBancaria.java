package entidades;

import Excepciones.BancoException;

public abstract class CuentaBancaria {
    protected float saldo;
    protected String numeroDeCuenta;
    protected String propietario;
    protected String tipoCuenta;
    protected int numeroDeRetiros = 0;
    protected int numeroDeConsignaciones = 0;


    public CuentaBancaria(float saldo, String numeroDeCuenta, String propietariio) {
        this.saldo = saldo;
        this.numeroDeCuenta = numeroDeCuenta;
        this.propietario = propietariio;
    }

    public abstract void consignarDinero(float dinero);

    public abstract void transferirDinero(String tipoDeCuenta, String numeroDeCuenta, float dinero);

    public void retirarDinero (float dinero) throws BancoException {
        if (dinero <= this.saldo) {
            this.saldo -= dinero;
            this.numeroDeRetiros++;
        }else {
            throw new BancoException("Saldo insuficiente");
        }
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietariio) {
        this.propietario = propietariio;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public int getNumeroDeRetiros() {
        return numeroDeRetiros;
    }

    public void setNumeroDeRetiros(int numeroDeRetiros) {
        this.numeroDeRetiros = numeroDeRetiros;
    }

    public int getNumeroDeConsignaciones() {
        return numeroDeConsignaciones;
    }

    public void setNumeroDeConsignaciones(int numeroDeConsignaciones) {
        this.numeroDeConsignaciones = numeroDeConsignaciones;
    }
}