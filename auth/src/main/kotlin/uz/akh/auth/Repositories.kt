package uz.akh.auth

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import uz.akh.auth.AccessToken
import uz.akh.auth.AuthClient
import uz.akh.auth.RefreshToken
import java.util.*

@Repository
interface AuthClientRepository : MongoRepository<AuthClient, String> {
    fun findByClientId(clientId: String?): AuthClient?
}

@Repository
interface AccessTokenRepository : MongoRepository<AccessToken, String> {
    fun findByTokenId(tokenId: String): AccessToken?
    fun findByAuthenticationId(authenticationId: String): AccessToken?
    fun deleteByTokenId(tokenId: String)
    fun deleteByRefreshToken(refreshToken: String)
    fun findAllByClientIdAndUsername(clientId: String, username: String): List<AccessToken>
    fun findAllByClientId(clientId: String): List<AccessToken>
    fun deleteByUsername(username: String)
    fun findAllByToken_ExpirationGreaterThan(token_expiration: Date): List<AccessToken>
}

@Repository
interface RefreshTokenRepository : MongoRepository<RefreshToken, String> {
    fun findByTokenId(tokenId: String): RefreshToken?
    fun deleteByTokenId(tokenId: String)
}