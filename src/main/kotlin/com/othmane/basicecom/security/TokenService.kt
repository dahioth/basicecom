package com.othmane.basicecom.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import javax.crypto.SecretKey


@Service
class TokenService {

    @Value("\${application.security.jwt.secret-key}") // TODO: Get From .env using System.getEnv("application.security.jwt.secret-key")
    private val secretKey: String? = null


    fun generateToken(
        userDetails: UserDetails,
        extraClaims: Map<String, Any> = emptyMap()
    ) : String{
        return Jwts
            .builder()
            .claims()
            .subject(userDetails.username)
            .add(extraClaims)
            .and()
            .signWith(getSignInKey())
            .compact()
    }

    private fun getSignInKey(): SecretKey {
        print(secretKey)
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }


    fun isJwtValid(jwtToken: String, userDetails: UserDetails): Boolean =
        userDetails.username == extractEmail(jwtToken)


    private fun extractClaims(jwtToken: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(getSignInKey())
            .build()

        return parser.parseSignedClaims(jwtToken).payload
    }

    fun extractEmail(jwtToken: String): String? =
        extractClaims(jwtToken).subject
}