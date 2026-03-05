package com.integrador.zyga

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    // Declaración de vistas
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvForgotPassword: TextView
    private lateinit var ivTogglePassword: ImageView
    private lateinit var ivGoogleLogin: ImageView
    private lateinit var ivEmpresaLogin: ImageView

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializar vistas
        initViews()

        // Configurar listeners
        setupListeners()
    }

    private fun initViews() {
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        ivTogglePassword = findViewById(R.id.ivTogglePassword)
        ivGoogleLogin = findViewById(R.id.ivGoogleLogin)
        ivEmpresaLogin = findViewById(R.id.ivEmpresaLogin)
    }

    private fun setupListeners() {
        // Botón de ingresar
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (validateInputs(email, password)) {
                performLogin(email, password)
            }
        }

        // Texto de "Olvidaste tu contraseña?"
        tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Funcionalidad de recuperar contraseña", Toast.LENGTH_SHORT).show()
            // Aquí puedes navegar a la pantalla de recuperación
            // startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        // 👁️ Toggle para ver/ocultar contraseña
        ivTogglePassword.setOnClickListener {
            togglePasswordVisibility()
        }

        // Icono de Google
        ivGoogleLogin.setOnClickListener {
            Toast.makeText(this, "Login con Google", Toast.LENGTH_SHORT).show()
            // Aquí va tu lógica de autenticación con Google
        }

        // Icono de Empresa (REGISTRO)
        ivEmpresaLogin.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Función para mostrar/ocultar la contraseña
     */
    private fun togglePasswordVisibility() {
        // Guardar la posición actual del cursor
        val selectionStart = etPassword.selectionStart
        val selectionEnd = etPassword.selectionEnd

        isPasswordVisible = !isPasswordVisible

        if (isPasswordVisible) {
            // Mostrar contraseña
            etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            ivTogglePassword.setImageResource(R.drawable.ic_visibility) // Ojo abierto
        } else {
            // Ocultar contraseña
            etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            ivTogglePassword.setImageResource(R.drawable.ic_visibility) // Ojo tachado
        }

        // Restaurar la posición del cursor
        etPassword.setSelection(selectionStart, selectionEnd)
    }

    private fun validateInputs(email: String, password: String): Boolean {
        return when {
            email.isEmpty() -> {
                etEmail.error = "El email es requerido"
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                etEmail.error = "Email no válido"
                false
            }
            password.isEmpty() -> {
                etPassword.error = "La contraseña es requerida"
                false
            }
            else -> true
        }
    }

    private fun performLogin(email: String, password: String) {
        // 🔐 AQUÍ VA TU LÓGICA DE AUTENTICACIÓN
        // Ejemplo con credenciales quemadas (solo para prueba)
        if (email == "example@gmail.com" && password == "123456") {
            Toast.makeText(this, "¡Login exitoso!", Toast.LENGTH_SHORT).show()

            // Navegar a la pantalla principal
            // startActivity(Intent(this, MainActivity::class.java))
            // finish() // Cierra LoginActivity
        } else {
            Toast.makeText(this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}