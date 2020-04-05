package com.rightside.fisioclin.models;

import com.google.firebase.firestore.DocumentId;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Horario implements Serializable {
    private int hour;
    private int minute;
    private int year;
    private Date date;
    private int dayOfMonth;
    private int month;
    private String horaFormatada;
    private String dataFormatada;
    private String diaDaSemanaFormatado;
    private boolean marcado = false;
    private String horarioNumber;
    private boolean domiciliar = false;
    private Medico medico;
    private Endereco endereco;

    @DocumentId
    private String id;

    public Horario() {
    }

    public Horario(int hour, int minute, int year, int dayOfMonth, int month, String id, String horarioNumber, Medico medico, boolean domiciliar) {
        this.hour = hour;
        this.minute = minute;
        this.year = year;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.id = id;
        this.domiciliar = domiciliar;
        this.medico = medico;
        setDate();
        setDataFormatada();
        setHoraFormatada();
        setDiaDaSemanaFormatado();
        setMarcado(false);
        setHorarioNumber(horarioNumber);
    }

    public boolean isDomiciliar() {
        return domiciliar;
    }

    public void setDomiciliar(boolean domiciliar) {
        this.domiciliar = domiciliar;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHorarioNumber() {
        return horarioNumber;
    }

    public void setHorarioNumber(String horarioNumber) {
        this.horarioNumber = horarioNumber;
    }

    public int getHour() {
        return hour;
    }

    public String getHoraFormatada() {
        return horaFormatada;
    }


    public String getDataFormatada() {
        return dataFormatada;
    }


    public String getDiaDaSemanaFormatado() {
        return diaDaSemanaFormatado;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDataFormatada() {
        this.dataFormatada = new SimpleDateFormat("dd-MM-yyyy", new Locale("pt", "BR")).format(getDate());
    }

    public void setHoraFormatada() {
        this.horaFormatada = new SimpleDateFormat("HH:mm").format(getDate());
    }

    public void setDiaDaSemanaFormatado() {
        this.diaDaSemanaFormatado = new SimpleDateFormat("EEE", new Locale("pt", "BR")).format(getDate()).toLowerCase();
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public Calendar calendar() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, getYear());
        c.set(Calendar.MONTH, getMonth());
        c.set(Calendar.DAY_OF_MONTH, getDayOfMonth());
        c.set(Calendar.HOUR, getHour());
        c.set(Calendar.MINUTE, getMinute());
        return c;
    }


    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = calendar().getTime();
    }


    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public HashMap<String, Object> map() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("year", getYear());
        map.put("month", getMonth());
        map.put("dayOfMonth", getDayOfMonth());
        map.put("hour", getHour());
        map.put("minute", getMinute());
        map.put("dataFormatada", getDataFormatada());
        map.put("horaFormatada", getHoraFormatada());
        map.put("diaDaSemanaFormatado", getDiaDaSemanaFormatado());
        map.put("date", getDate());
        map.put("marcado", isMarcado());
        map.put("horarioNumber", getHorarioNumber());
        map.put("medico", getMedico());
        map.put("domiciliar", isDomiciliar());
        map.put("endereco", getEndereco());
        return map;
    }

    public String endereco() {
        return "Cidade: " + getEndereco().getCidade() + " " + "Bairro: " + getEndereco().getBairro() + " " + "Rua: " + getEndereco().getLogradouro() + " " + "Num: " + getEndereco().getNumero();
    }

}
