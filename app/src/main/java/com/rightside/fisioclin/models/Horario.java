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
    private Boolean isMarcado = false;
    @DocumentId
    private String id;


    public Horario(int hour, int minute, int year, int dayOfMonth, int month, String id) {
        this.hour = hour;
        this.minute = minute;
        this.year = year;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.id = id;
        setDate();
        setDataFormatada();
        setHoraFormatada();
        setDiaDaSemanaFormatado();
        setMarcado(false);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Horario() {
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
        this.dataFormatada = new SimpleDateFormat("dd-MM-yyyy", new Locale("pt","BR")).format(getDate());;
    }

    public void setHoraFormatada() {
        this.horaFormatada = new SimpleDateFormat("HH:mm").format(getDate());
    }

    public void setDiaDaSemanaFormatado() {
        this.diaDaSemanaFormatado = new SimpleDateFormat("EEEE", new Locale("pt","BR")).format(getDate());;
    }

    public Boolean getMarcado() {
        return isMarcado;
    }

    public void setMarcado(Boolean marcado) {
        isMarcado = marcado;
    }

    public Calendar calendar() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, getYear());
        c.set(Calendar.MONTH, getMonth());
        c.set(Calendar.DAY_OF_MONTH, getDayOfMonth());
        c.set(Calendar.HOUR, getHour());
        c.set(Calendar.MINUTE,getMinute());
        return c;
    }


    public Date getDate() {
        return date;
    }

    public void setDate( ) {
        this.date = calendar().getTime();
    }

    public HashMap<String, Object> map(){
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
        map.put("isMarcado",getMarcado());
        return map;
    }

}
