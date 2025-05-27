// objeto para poder encriptar el token de autenticacion
// package com.juancamr.deckly.core
// import java.security.KeyPairGenerator
// import java.security.KeyProperties
//
// object CryptoManager {
// /*
//  * Generate a new EC key pair entry in the Android Keystore by
//  * using the KeyPairGenerator API. The private key can only be
//  * used for signing or verification and only with SHA-256 or
//  * SHA-512 as the message digest.
//  */
// val kpg: KeyPairGenerator = KeyPairGenerator.getInstance(
//         KeyProperties.KEY_ALGORITHM_EC,
//         "AndroidKeyStore"
// )
// val parameterSpec: KeyGenParameterSpec = KeyGenParameterSpec.Builder(
//         alias,
//         KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY
// ).run {
//     setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
//     build()
// }
//
// kpg.initialize(parameterSpec)
//
// val kp = kpg.generateKeyPair()
// }