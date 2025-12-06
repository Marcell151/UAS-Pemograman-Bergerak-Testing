package com.example.kantinkampus;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private EditText etEmail, etPassword;
    private TextView btnLogin, tvRegister;
    private DBHelper dbHelper;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Log.d(TAG, "onCreate started");

            // Initialize SessionManager and DBHelper
            sessionManager = new SessionManager(this);
            dbHelper = new DBHelper(this);

            Log.d(TAG, "SessionManager and DBHelper initialized");

            // Check if user already logged in
            if (sessionManager.isLoggedIn()) {
                Log.d(TAG, "User already logged in, redirecting...");
                redirectToHome();
                return;
            }

            setContentView(R.layout.activity_login);
            Log.d(TAG, "Layout set successfully");

            // Initialize views
            etEmail = findViewById(R.id.etEmail);
            etPassword = findViewById(R.id.etPassword);
            btnLogin = findViewById(R.id.btnLogin);
            tvRegister = findViewById(R.id.tvRegister);

            Log.d(TAG, "Views initialized");

            // Set listeners
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attemptLogin();
                }
            });

            tvRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });

            Log.d(TAG, "Listeners set successfully");

        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage(), e);
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void attemptLogin() {
        try {
            // Reset errors
            etEmail.setError(null);
            etPassword.setError(null);

            // Get values
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            boolean cancel = false;
            View focusView = null;

            // Validate password
            if (TextUtils.isEmpty(password)) {
                etPassword.setError("Password tidak boleh kosong");
                focusView = etPassword;
                cancel = true;
            } else if (password.length() < 6) {
                etPassword.setError("Password minimal 6 karakter");
                focusView = etPassword;
                cancel = true;
            }

            // Validate email
            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Email tidak boleh kosong");
                focusView = etEmail;
                cancel = true;
            } else if (!isEmailValid(email)) {
                etEmail.setError("Format email tidak valid");
                focusView = etEmail;
                cancel = true;
            }

            if (cancel) {
                focusView.requestFocus();
            } else {
                performLogin(email, password);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in attemptLogin: " + e.getMessage(), e);
            Toast.makeText(this, "Error saat login: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void performLogin(String email, String password) {
        try {
            User user = dbHelper.loginUser(email, password);

            if (user != null) {
                Log.d(TAG, "Login successful for user: " + user.getName());

                // Save session
                sessionManager.createLoginSession(user);

                // Show success message
                Toast.makeText(this, "✅ Login berhasil! Selamat datang, " + user.getName(),
                        Toast.LENGTH_SHORT).show();

                // Redirect based on role
                redirectToHome();
                finish();
            } else {
                Log.d(TAG, "Login failed - incorrect credentials");
                Toast.makeText(this, "❌ Email atau password salah!",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in performLogin: " + e.getMessage(), e);
            Toast.makeText(this, "Error saat memproses login: " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void redirectToHome() {
        try {
            Intent intent;
            if (sessionManager.isAdmin()) {
                Log.d(TAG, "Redirecting to Admin Dashboard");
                intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
            } else {
                Log.d(TAG, "Redirecting to Customer Home");
                intent = new Intent(LoginActivity.this, MainActivity.class);
            }
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Log.e(TAG, "Error in redirectToHome: " + e.getMessage(), e);
            Toast.makeText(this, "Error saat redirect: " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".");
    }

    @Override
    public void onBackPressed() {
        // Disable back button on login screen
        moveTaskToBack(true);
    }
}