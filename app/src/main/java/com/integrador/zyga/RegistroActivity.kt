package com.integrador.zyga

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    // Todos los campos del formulario
    private lateinit var etNombre: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etTelefono: EditText
    private lateinit var etEmail: EditText
    private lateinit var etRedes: EditText
    private lateinit var etDomicilio: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var ivTogglePassword: ImageView
    private lateinit var ivToggleConfirmPassword: ImageView

    private var isPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        etNombre = findViewById(R.id.etNombre)
        etApellidos = findViewById(R.id.etApellidos)
        etTelefono = findViewById(R.id.etTelefono)
        etEmail = findViewById(R.id.etEmail)
        etRedes = findViewById(R.id.etRedes)
        etDomicilio = findViewById(R.id.etDomicilio)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        ivTogglePassword = findViewById(R.id.ivTogglePassword)
        ivToggleConfirmPassword = findViewById(R.id.ivToggleConfirmPassword)
    }

    private fun setupListeners() {
        btnRegistrar.setOnClickListener {
            if (validarRegistro()) {
                // Aquí guardas todos los datos
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                finish() // Vuelve al login
            }
        }

        // Toggle para ver/ocultar contraseña
        ivTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            togglePasswordVisibility(etPassword, ivTogglePassword, isPasswordVisible)
        }

        // Toggle para ver/ocultar confirmar contraseña
        ivToggleConfirmPassword.setOnClickListener {
            isConfirmPasswordVisible = !isConfirmPasswordVisible
            togglePasswordVisibility(etConfirmPassword, ivToggleConfirmPassword, isConfirmPasswordVisible)
        }
    }

    private fun togglePasswordVisibility(editText: EditText, imageView: ImageView, isVisible: Boolean) {
        val selectionStart = editText.selectionStart
        val selectionEnd = editText.selectionEnd

        if (isVisible) {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            imageView.setImageResource(R.drawable.ic_visibility)
        } else {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            imageView.setImageResource(R.drawable.ic_visibility)
        }
        editText.setSelection(selectionStart, selectionEnd)
    }

    private fun validarRegistro(): Boolean {
        val nombre = etNombre.text.toString().trim()
        val apellidos = etApellidos.text.toString().trim()
        val telefono = etTelefono.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val redes = etRedes.text.toString().trim()
        val domicilio = etDomicilio.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        return when {
            nombre.isEmpty() -> {
                etNombre.error = "El nombre es requerido"
                false
            }
            apellidos.isEmpty() -> {
                etApellidos.error = "Los apellidos son requeridos"
                false
            }
            telefono.isEmpty() -> {
                etTelefono.error = "El teléfono es requerido"
                false
            }
            email.isEmpty() -> {
                etEmail.error = "El email es requerido"
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                etEmail.error = "Email no válido"
                false
            }
            domicilio.isEmpty() -> {
                etDomicilio.error = "El domicilio es requerido"
                false
            }
            password.isEmpty() -> {
                etPassword.error = "La contraseña es requerida"
                false
            }
            password.length < 6 -> {
                etPassword.error = "Mínimo 6 caracteres"
                false
            }
            password != confirmPassword -> {
                etConfirmPassword.error = "Las contraseñas no coinciden"
                false
            }
            else -> true
        }
    }

    fun irALogin(view: View) {
        finish()
    }
}