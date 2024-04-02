package ru.yangel.auth_data.storage

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import ru.yangel.core.customexception.AuthCollisionException
import ru.yangel.core.customexception.AuthException
import java.util.Locale
import kotlin.coroutines.cancellation.CancellationException


class GoogleAuthClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {

    private val auth = Firebase.auth


    fun isUserAlreadyLogin(): Boolean {
        return auth.currentUser != null
    }


    suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }


    suspend fun signInWithEmailAndPassword(email: String, password: String) {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
        } catch (e: FirebaseAuthException) {
            throw AuthException("Auth failed")
        } catch (e: Exception) {
            throw e
        }

    }


    suspend fun registerUser(email: String, password: String) {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
        } catch (e: FirebaseAuthUserCollisionException) {
            throw AuthCollisionException("Email already busy")
        } catch (e: Exception) {
            throw e
        }
    }


    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId("30859685322-bgusbut4v57rak6mv7j4gf35hivtvq8b.apps.googleusercontent.com")
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}