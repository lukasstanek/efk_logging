import java.security.Permission

internal object SystemExitControl {

    class ExitTrappedException : SecurityException()

    fun forbidSystemExitCall() {
        val securityManager = object : SecurityManager() {

            override fun checkPermission(permission: Permission) {
                if (permission.name.contains("exitVM")) {
                    throw ExitTrappedException()
                }
            }
        }
        System.setSecurityManager(securityManager)
    }

    fun enableSystemExitCall() {
        System.setSecurityManager(null)
    }
}