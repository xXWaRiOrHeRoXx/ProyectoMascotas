package com.developerluisfm.mdapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactoActivity extends AppCompatActivity {

    EditText editTextNombre;
    EditText editTextCorreo;
    EditText editTextMensaje;

    Session session = null;
    ProgressDialog pdialog = null;
    String rec, subject, textMessage;

    private static final String username = "floresmiranda1593@gmail.com";
    private static final String password = "12345";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.contacto);
        toolbar.setLogo(R.drawable.ic_marca);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        editTextNombre = (EditText) findViewById(R.id.nombre);
        editTextCorreo = (EditText) findViewById(R.id.correo);
        editTextMensaje = (EditText) findViewById(R.id.mensaje);

        Button enviar = (Button) findViewById(R.id.enviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send();
                //String email = "floresmiranda1593@gmail.com";//emailEdit.getText().toString();
                //String subject = "pruebassss";//subjectEdit.getText().toString();
                //String message = "Pruebas de pruebas preubas";//messageEdit.getText().toString();
                //sendMail(email, subject, message);

                Email emails = new Email();
                emails.execute("floresmiranda1593@gmail.com", "prueba de prueba");

                try {
                    Object ob = emails.get();
                    if (ob != null) {
                        Snackbar.make(v,ob.toString(),Snackbar.LENGTH_LONG).show();
                    }else{
                        Snackbar.make(v,"Mensaje no enviado",Snackbar.LENGTH_LONG).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void sendMail(String email, String subject, String messageBody) {
        Session session = createSessionObject();

        try {
            Message message = createMessage(email, subject, messageBody, session);
            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private Message createMessage(String email, String subject, String messageBody, Session session) throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("floresmiranda1593@gmail.com", "Tiemen Schut"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, email));
        message.setSubject(subject);
        message.setText(messageBody);
        return message;
    }

    private Session createSessionObject() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private class SendMailTask extends AsyncTask<Message, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ContactoActivity.this, "Please wait", "Sending mail", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }






}
