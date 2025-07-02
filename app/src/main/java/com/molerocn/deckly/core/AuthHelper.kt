package com.molerocn.deckly.core

import android.content.Context
import android.content.SharedPreferences
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import android.util.Log
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import androidx.navigation.NavController
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.molerocn.deckly.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthHelper @Inject constructor(
    private val request: GetCredentialRequest,
    @ApplicationContext private val context: Context,
    private val userPreferences: SharedPreferences
) {

    /**
     * Este metodo abre el modal de inicio de sesion de google y retorna el token luego de
     * autenticarse
     *
     * @return token con los datos del usuario cifrados
     */
    suspend fun getTokenFromGoogle(): String {
        // TODO: implementar una clase que pueda almacenar un objeto y tambien un boolean,
        // TODO: algo parecido al Response de java que hiciste en anteriores proyectos de java como freelancer-server

        try {
            val credentialManager = CredentialManager.create(context)
            val result = credentialManager.getCredential(context, request) // obtener el token lanzando un modal (supongo)
            val credential = result.credential
            if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                return googleIdTokenCredential.idToken
            } else {
                Log.w("signin", "La credencial no es de tipo Google ID Token")
            }
        } catch (e: NoCredentialException) {
            Log.w("signin", "No hay cuentas disponibles: ${e.message}")
        } catch (e: GetCredentialException) {
            Log.e("signin", "Error al obtener credenciales: ${e.message}", e)
        } catch (e: Exception) {
            Log.e("signin", "Error desconocido: ${e.message}", e)
        }
        return ""
    }

    fun isLoggedIn() = userPreferences.getString("access_token", "")!!.isNotEmpty()

    fun signIn(content: Map<String, String>) {
        val editor = userPreferences.edit()
        editor.putString("name", content.get("name"))
        editor.putString("email", content.get("email"))
        editor.putString("picture", content.get("picture"))
        editor.putString("access_token", content.get("access_token"))
        editor.apply()
    }

    fun signOut(navController: NavController) {
        val editor = userPreferences.edit()
        editor.clear()
        editor.apply()
        // navController.navigate(R.id.action_global_profile_to_auth)
    }
}