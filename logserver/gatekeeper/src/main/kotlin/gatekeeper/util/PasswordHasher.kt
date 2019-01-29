package gatekeeper.util

import org.mindrot.jbcrypt.BCrypt

object PasswordHasher {
    fun hashPw(password: String): String{
        return BCrypt.hashpw(password, BCrypt.gensalt(12))
    }
}