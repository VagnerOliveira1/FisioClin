package com.rightside.fisioclin.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.DiagnosticoMedico;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.models.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class GeralUtils {


    public static void mostraImagemCircular(Context context, ImageView imageView, String url){
        Glide.with(context).load(url).circleCrop().into(imageView);
    }

    public static String capitalize(String palavra) {
        return palavra.substring(0,1).toUpperCase() + palavra.substring(1).toLowerCase();
    }

    public static void mostraAlerta(String titulo, String mensagem, Context context) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        alerta.setTitle(titulo).setMessage(mensagem).setNeutralButton("Ok", (dialog, which) -> dialog.cancel()).show();
    }

    public static void mostraMensagem(Context context, String mensagem) {
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }

    public static String retornaDiaSemana(String dia) {
        switch (dia) {
            case "seg":
                return "Segunda-feira";
            case "ter":
                return "Terça-feira";
            case "qua":
                return "Quarta-feira";
            case "qui":
                return "Quinta-feira";
            case "sex":
                return "Sexta-feira";
            case "sáb":
                return "Sábado";
        }
        return dia;
    }

    public static boolean isCampoVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    public static String domiciliar(boolean domiciliar) {
        if (domiciliar) {
            return "Consulta domiciliar";
        } else {
            return "";
        }
    }

    public static void gerarRelatorio(Context context, Paciente paciente, List<Consulta> consultas, Medico medico) {
        Document doc = null;
        OutputStream os = null;
        String relatorio = Environment.getExternalStorageDirectory().toString() + "/relatorio.pdf";

        try {
            doc = new Document(PageSize.A4, 42, 42, 42, 42);
            os = new FileOutputStream(relatorio);
            PdfWriter.getInstance(doc, os);
            doc.open();
            Font b = new Font(Font.FontFamily.HELVETICA, 15.0f, Font.UNDERLINE, BaseColor.RED);
            Font f = new Font(Font.FontFamily.HELVETICA,10.0f,Font.UNDERLINE, BaseColor.BLACK);
            doc.addTitle("FisioApp - Ficha de acompanhamento");
            Paragraph titulo = new Paragraph("Fisio Clin - Ficha de acompanhamento ", b);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            Paragraph nome = new Paragraph("Nome: " + paciente.getName() , f);
            Paragraph telefone = new Paragraph("Telefone: " + paciente.getPhoneNumber() , f);
            Paragraph data = new Paragraph("Data de nascimento: " + paciente.getDataNascimento(), f);
            Paragraph profissao  = new Paragraph("Profissão: " + paciente.getProfissao(), f);
            Paragraph email  = new Paragraph("Email: " + paciente.getEmail(), f);


            doc.add(titulo);
            doc.add(nome);
            doc.add(telefone);
            doc.add(data);
            doc.add(profissao);
            doc.add(email);
            Paragraph evolucao = new Paragraph("Consutas do paciente :");
            evolucao.setAlignment(Element.ALIGN_CENTER);
            doc.add(evolucao);

            for (Consulta consulta : consultas) {
                Paragraph horario = new Paragraph("Data: " + consulta.getHorario().getDataFormatada() + " Hora: " + consulta.getHorario().getHoraFormatada() + " Dia: " + GeralUtils.retornaDiaSemana(consulta.getHorario().getDiaDaSemanaFormatado()) , f);
                Paragraph comentario =  new Paragraph("Evolução: " + consulta.getComentarioPosConsulta(), f);
                Paragraph queixa = new Paragraph("Queixa: " + consulta.getPaciente().getDiagnosticoMedico().getQueixa(), f);
                Paragraph quebralinha = new Paragraph(" ");
                doc.add(queixa);
                doc.add(horario);
                doc.add(comentario);
                doc.add(quebralinha);

            }
            doc.close();
            os.close();

        }catch (Exception e) {

        }

    }

}
