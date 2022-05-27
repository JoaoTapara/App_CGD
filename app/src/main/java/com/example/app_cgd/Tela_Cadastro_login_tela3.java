package com.example.app_cgd;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_cgd.DTO.DadosUsuarios;
import com.example.app_cgd.DTO.Sistemas;
import com.example.app_cgd.DTO.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Tela_Cadastro_login_tela3 extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Usuario u;

    private int dia;
    private int mes;
    private int ano;
    private String dpp;

    private EditText  edt_idade, edt_peso;
    private Button btn_cadastrar, dpk_dum;
    private TextView tv_dum;

    private String[] mensagens = {"Preencha todos os campos","Cadastro feito com sucesso","Deu Bosta"};

    String usuarioID;

    private DatePickerDialog datePickerDialog;

    public Tela_Cadastro_login_tela3() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_login_tela3);

        initDatePiker();
        IniciarComponente();
        dpk_dum.setText(getTodaysDate());




        dpk_dum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDatePiker(v);



            }
        });

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RecuperaDados(v);
            }
        });

    }

    private void RecuperaDados(View v) {


        String dum = dpk_dum.getText().toString();
        String idade = edt_idade.getText().toString();
        String peso = edt_peso.getText().toString();
        DadosUsuarios.dum = dum;
        DadosUsuarios.idade = idade;
        DadosUsuarios.peso = peso;

        u = new Usuario();
        u.setDum(dpk_dum.getText().toString());
        u.setIdade(edt_idade.getText().toString());
        u.setPeso(edt_peso.getText().toString());


        if(u.getDum().isEmpty() || u.getIdade().isEmpty() || u.getPeso().isEmpty()){
            Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(Color.WHITE);
            snackbar.setTextColor(Color.BLACK);
            snackbar.show();

        } else{

            CadastrarUsuario(v);

        }
    }

    private  void CadastrarUsuario(View v){

        String email = DadosUsuarios.email;
        String senha = DadosUsuarios.senha;

        mAuth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            u.setId(mAuth.getUid());;
                            u.salvar();
                            Logar();

                            Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(Color.WHITE);
                            snackbar.setTextColor(Color.BLACK);
                            snackbar.show();

                        }else {

                            String erro;

                            try{
                                throw  task.getException();

                            }catch (FirebaseAuthWeakPasswordException e){
                                erro = "Senha com no mínimo 6 caracteres";
                            }
                            catch (FirebaseAuthUserCollisionException e){
                                erro = "Usuario ja existente";
                            }
                            catch (FirebaseAuthInvalidCredentialsException e){
                                erro = "Digite um email valido";
                            }
                            catch (Exception e){
                                erro = "Erro ao cadastrar o usuario";
                            }

                            Snackbar snackbar = Snackbar.make(v,erro, Snackbar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(Color.WHITE);
                            snackbar.setTextColor(Color.BLACK);
                            snackbar.show();
                        }
                    }
                });
    }

    private String getTodaysDate(){

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);

    }

    private void initDatePiker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                month = month+1;
                String date = makeDateString(day, month, year);
                dpk_dum.setText(date);

                dia = day;
                mes = month;
                ano = year;

                Sistemas.dia = dia;
                Sistemas.mes = mes;
                Sistemas.ano = ano;

                CalculaDpp();
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());



    }

    private String makeDateString(int day, int month, int year) {

        return day + "/" + month + "/" + year;

    }

    private void openDatePiker(View v) {

        datePickerDialog.show();

    }

    private void CalculaDpp(){


        int ano_dpp = Sistemas.ano;
        int dia_dpp = Sistemas.dia + 7;

        switch (dia_dpp){

            case 32:
                dia_dpp = 1;
                break;
            case 33:
                dia_dpp = 2;
                break;
            case 34:
                dia_dpp = 3;
                break;
            case 35:
                dia_dpp = 4;
                break;
            case 36:
                dia_dpp = 5;
                break;
            case 37:
                dia_dpp = 6;
                break;
            case 38:
                dia_dpp = 7;
                break;
        }

        int mes_dpp = Sistemas.mes + 9;

        switch (mes_dpp){

            case 13:
                mes_dpp = 1;
                ano_dpp ++;
                break;
            case 14:
                mes_dpp = 2;
                ano_dpp ++;
                break;
            case 15:
                mes_dpp = 3;
                ano_dpp ++;
                break;
            case 16:
                mes_dpp = 4;
                ano_dpp ++;
                break;
            case 17:
                mes_dpp = 5;
                ano_dpp ++;
                break;
            case 18:
                mes_dpp = 6;
                ano_dpp ++;
                break;
            case 19:
                mes_dpp = 7;
                ano_dpp ++;
                break;
            case 20:
                mes_dpp = 8;
                ano_dpp ++;
                break;
            case 21:
                mes_dpp = 9;
                ano_dpp ++;
                break;
        }


        dpp = Makedpp(dia_dpp, mes_dpp, ano_dpp);
        Toast.makeText(this, "dpp" + dpp, Toast.LENGTH_SHORT).show();

        u = new Usuario();
        u.setDpp(dpp);
    }

    private String Makedpp(int dia_dpp, int mes_dpp, int ano_dpp){

        return dia_dpp + "/" + mes_dpp + "/" + ano_dpp;

    }



    private void IniciarComponente(){

        dpk_dum = findViewById(R.id.dpk_dum);

        tv_dum = findViewById(R.id.tv_dum);
        edt_idade = findViewById(R.id.edt_idade);
        edt_peso = findViewById(R.id.edt_peso);
        btn_cadastrar = findViewById(R.id.btn_cadastrar);
        mAuth = FirebaseAuth.getInstance();
    }

    private void Logar(){
        startActivity(new Intent(getApplicationContext(), Tela_Login.class));
    }
}
