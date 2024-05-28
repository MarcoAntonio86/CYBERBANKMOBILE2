package com.example.bancodip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bancodip.R;
import com.example.bancodip.controller.ControllerBancoDados;
import com.example.bancodip.databinding.ActivityRegisterBinding;

import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private ControllerBancoDados controllerBancoDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        controllerBancoDados = new ControllerBancoDados(this);
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);

        binding.btnCriarConta.setOnClickListener(v -> {
            controllerBancoDados.open();

            String  nome = binding.NomeRegister.getText().toString().trim();
            String CPF = binding.CPFRegister.getText().toString().trim();
            String Senha = binding.SenhaRegister.getText().toString().trim();
            String SaldoTxT = binding.SaldoRegister.getText().toString().trim();

            if(!nome.isEmpty() && !CPF.isEmpty() && !SaldoTxT.isEmpty() && !Senha.isEmpty()){

                double Saldo = Double.parseDouble(SaldoTxT);
                double ChequeEspecial = Saldo * 4;
                double ChequeInicial = ChequeEspecial;

                try {
                    controllerBancoDados.insertData(nome, CPF, Senha, Saldo, ChequeEspecial, ChequeInicial);
                    intent.putExtra("nome", nome);
                    intent.putExtra("CPF", CPF);
                    intent.putExtra("Senha", Senha);
                    intent.putExtra("Saldo", Saldo);
                    intent.putExtra("ChequeEspecial", ChequeEspecial);
                    intent.putExtra("ChequeI", ChequeInicial);

                }catch (Exception e){
                    e.printStackTrace();
                } finally {
                    controllerBancoDados.close();
                    startActivity(intent);
                    finish();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_LONG).show();
            }





        });




    }
}